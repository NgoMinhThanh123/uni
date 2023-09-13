import { Button, Form, Table } from "react-bootstrap";
import Apis, { authApi, endpoints } from "../../configs/Apis";
import React, { useContext, useEffect, useState } from "react";
import { MyUserContext } from "../../App";
import './Student.css'
import Papa from 'papaparse';
import 'jspdf-autotable';
import jsPDF from 'jspdf';
import 'jspdf-autotable'; 
import CustomFont from './font-times-new-roman.ttf'; 


const Student = () => {
    const [user] = useContext(MyUserContext);
    const [subjectList, setSubjectList] = useState([]);
    const [selectedSubject, setSelectedSubject] = useState(""); 
    const [selectedLecturer, setSelectedLecturer] = useState({});
    const [studentList, setStudentList] = useState([]);
    const [semesterList, setSemesterList] = useState([]);
    const [selectedSemester, setSelectedSemester] = useState("");
    const [csvData, setCsvData] = useState([]);
    

    useEffect(() => {
        async function fetchData() {
            try {
                const lecturerInfo = await fetchLecturerInfo();
                if (lecturerInfo && lecturerInfo.id) {
                    const lecturerId = lecturerInfo.id;
                    await fetchSubjectsByLecturerId(lecturerId);
                }
            } catch (err) {
                console.error(err);
            }
        }

        fetchData();
    }, []);

    const fetchLecturerInfo = async () => {
        try {
            const lecturerUsername = user.username;
            const response = await authApi().get(endpoints['get-lecturer-by-username'].replace("{username}", lecturerUsername));
            setSelectedLecturer(response.data);
            console.log('lecturer info:', response.data);
            return response.data;
        } catch (err) {
            console.error(err);
            return null;
        }
    }

    const fetchSubjectsByLecturerId = async (lecturerId) => {
        try {
            const response = await authApi().get(endpoints["get-subject-by-lecturerId"].replace("{lecturerId}", lecturerId));
            console.log(response.data);
            setSubjectList(response.data);

            const endpoint = endpoints["semester"]
                + `?lecturerId=${lecturerId}`;

            const semesterResponse = await authApi().get(endpoint);
            setSemesterList(semesterResponse.data);

            console.log('Semester info:', semesterResponse.data);
        } catch (error) {
            console.error(error);
            console.log(error);
            return null;
        }
    }


    const handleSubjectChange = async (event) => {
        const selectedSubjectId = event.target.value;
        setSelectedSubject(selectedSubjectId);
    };

    const handleSemesterChange = (event) => {
        const selectedSemesterId = event.target.value;
        setSelectedSemester(selectedSemesterId);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            const subjectId = selectedSubject;
            const lecturerId = selectedLecturer.id;
            const semesterId = selectedSemester;
            console.log("semesterId", semesterId)

            const endpoint = endpoints["get-list-scores"]
                + `?lecturerId=${lecturerId}&semesterId=${semesterId}&subjectId=${subjectId}`;

            const response = await authApi().get(endpoint);

            if (response.data) {
                console.log(response.data);
                setStudentList(response.data);
            }
        } catch (error) {
            console.error(error);
        }
    };
    const studentScores = {};

    studentList.forEach((student) => {
        const { studentId, studentName, scoreColumnName, scoreValue } = student;

        // Kiểm tra xem sinh viên đã có trong object chưa
        if (!studentScores[studentId]) {
            // Nếu chưa có, tạo thông tin cho sinh viên
            studentScores[studentId] = {
                studentId,
                studentName,
                scores: [{ column: scoreColumnName, value: scoreValue }],
            };
        } else {
            // Nếu đã có, cập nhật thông tin điểm
            studentScores[studentId].scores.push({ column: scoreColumnName, value: scoreValue });
        }
    });

    const handleExportCSV = async () => {
        try {
            const lecturerId = selectedLecturer.id;
            const subjectId = selectedSubject;
            const semesterId = selectedSemester;

            const endpoint = endpoints["get-list-scores"]
                + `?lecturerId=${lecturerId}&semesterId=${semesterId}&subjectId=${subjectId}`;

            const response = await authApi().get(endpoint);

            if (response.data) {
                const studentScores = {};

                response.data.forEach((student) => {
                    const { studentId, studentName, scoreColumnName, scoreValue } = student;

                    if (!studentScores[studentId]) {
                        studentScores[studentId] = {
                            studentId,
                            studentName,
                            scores: {},
                        };
                    }

                    studentScores[studentId].scores[scoreColumnName] = scoreValue;
                });

                const scoreColumns = Object.keys(studentScores[Object.keys(studentScores)[0]].scores);
                const semesterName = semesterList.find(semester => semester[0] === selectedSemester)[1];
                const semesterYear = semesterList.find(semester => semester[0] === selectedSemester)[2];

                const csvData = [
                    `${subjectList[0][1]},${semesterName},${semesterYear}`,
                    `Mã số sinh viên,Tên sinh viên,${scoreColumns.join(",")}`
                ];

                Object.values(studentScores).forEach((student) => {
                    const scores = scoreColumns.map(column => student.scores[column]).join(",");
                    csvData.push(`${student.studentId},${student.studentName},${scores}`);
                });

                const csvContent = csvData.join("\n");
                const blob = new Blob([csvContent], { type: "text/csv;charset=utf-8;" });
                const link = document.createElement("a");
                if (link.download !== undefined) {
                    const url = URL.createObjectURL(blob);
                    link.setAttribute("href", url);
                    link.setAttribute("download", "student_scores.csv");
                    link.style.visibility = "hidden";
                    document.body.appendChild(link);
                    link.click();
                    document.body.removeChild(link);
                }
            }
        } catch (error) {
            console.error("Export CSV error:", error);
        }
    };

    const handleFileChange = (event) => {
        const file = event.target.files[0];

        Papa.parse(file, {
            header: true,
            dynamicTyping: true,
            complete: (results) => {
                // Gán dữ liệu từ tệp CSV vào state
                setCsvData(results.data);
            },
            error: (error) => {
                console.error("CSV parsing error:", error);
            }
        });
    };

    const exportToPDF = () => {
        const doc = new jsPDF();
        doc.addFileToVFS('CustomFont.ttf', CustomFont);
        doc.addFont('CustomFont.ttf', 'CustomFont', 'normal');
        doc.setFont('CustomFont');
        // Add content to the PDF
        doc.text('Bảng điểm sinh viên', 10, 10);

        const studentId = Object.keys(studentScores)[0]; // Lấy mã số sinh viên đầu tiên (hoặc bạn có thể chọn mã số sinh viên khác)
        const firstStudent = studentScores[studentId]; // Lấy thông tin của sinh viên đó
        
        // Trích xuất tên cột điểm từ mảng scores của sinh viên
        const scoreColumns = firstStudent.scores.map(score => score.column);
        
        console.log('scoreColumns:', scoreColumns);
        const header = ["Mã số sinh viên", "Tên sinh viên", ...scoreColumns];

        const data = Object.values(studentScores).map(student => [
            student.studentId,
            student.studentName,
            ...scoreColumns.map(column => {
                const score = student.scores.find(s => s.column === column);
                return score ? score.value : '';
            })
        ]);

        doc.autoTable({
            head: [header],
            body: data,
        });

        // Save the PDF
        doc.save('student_scores.pdf');
    };


    return (
        <>
            <div className="studentHome">
                <Form onSubmit={handleSubmit}>
                    <div className="select">
                        <Form.Group controlId="subjectSelect">

                            <Form.Label>Chọn môn học:</Form.Label>
                            <Form.Control as="select" onChange={handleSubjectChange} value={selectedSubject}>
                                <option value="">Chọn môn học</option>
                                {subjectList.map((subject, index) => (
                                    <option key={index} value={subject[0]}>{subject[1]}</option>
                                ))}

                            </Form.Control>
                        </Form.Group>
                        <div className="semester">
                            <Form.Group controlId="semesterSelect">
                                <Form.Label>Chọn học kì:</Form.Label>
                                <Form.Control as="select" onChange={handleSemesterChange} value={selectedSemester}>
                                    <option value="">Chọn học kì</option>
                                    {semesterList.map((semester, index) => (
                                        <option key={index} value={semester[0]}>
                                            {`${semester[1]} - ${semester[2]}`}
                                        </option>
                                    ))}

                                </Form.Control>
                            </Form.Group>
                        </div>
                        <Button className="btnSubmit" type="submit">Tìm kiếm</Button>
                    </div>
                    <div className="btnFile">
                        <Button className="btnExport" type="submit" onClick={handleExportCSV}>Xuất file</Button>
                        <Button className="btnImport" type="submit">
                            <label className="whiteColor" htmlFor="csvFile" style={{ margin: 0 }}>
                                Đọc file
                            </label>
                            <input
                                id="csvFile"
                                type="file"
                                accept=".csv"
                                style={{ display: "none" }}
                                onChange={handleFileChange}
                            />
                        </Button>
                        <Button className="btnExportPDF" onClick={exportToPDF}>Xuất PDF</Button>
                    </div>
                    {csvData.length > 0 && (
                        <div>
                            <h3>Dữ liệu từ tệp CSV</h3>
                            <Table striped bordered hover>
                                <thead>
                                    <tr>
                                        {Object.keys(csvData[0]).map((column, index) => (
                                            <>
                                            <th key={index} className="text-center">{column}</th>
                                            </>
                                        ))}
                                    </tr>
                                </thead>
                                <tbody>
                                    {csvData.map((row, rowIndex) => (
                                        <tr key={rowIndex}>
                                            {Object.values(row).map((value, valueIndex) => (
                                                <td key={valueIndex} className="text-center">{value}</td>
                                            ))}
                                        </tr>
                                    ))}
                                </tbody>
                            </Table>
                        </div>
                    )}
                </Form>
                {studentList.length > 0 && (
                    <Table striped bordered hover>
                        <thead>
                            <tr>
                                <th className="text-center">Mã số sinh viên</th>
                                <th className="text-center">Tên sinh viên</th>
                                {Object.keys(studentScores[Object.keys(studentScores)[0]].scores).map((scoreColumn, index) => {
                                    const student = studentScores[Object.keys(studentScores)[0]];
                                    return (
                                        <th key={index} className="text-center">{student.scores[index].column}</th>
                                    );
                                })}
                            </tr>
                        </thead>
                        <tbody>
                            {Object.values(studentScores).map((student, index) => (
                                <tr key={index}>
                                    <td className="text-center">{student.studentId}</td>
                                    <td className="text-center">{student.studentName}</td>
                                    {student.scores.map((score, scoreIndex) => (
                                        <td key={scoreIndex} className="text-center">{score.value}</td>
                                    ))}
                                </tr>
                            ))}
                        </tbody>
                    </Table>
                )}
            </div>

        </>
    );
};

export default Student;






