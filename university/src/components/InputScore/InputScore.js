import { Button, Form, Table } from "react-bootstrap";
import Apis, { authApi, endpoints } from "../../configs/Apis";
import React, { useContext, useEffect, useState } from "react";
import { MyUserContext } from "../../App";
import formatDate from "../FormatDare/FormatDate";

const InputScore = () => {
    const [user] = useContext(MyUserContext);
    const [subjectList, setSubjectList] = useState([]);
    const [selectedSubject, setSelectedSubject] = useState("");
    const [selectedLecturer, setSelectedLecturer] = useState({});
    const [studentList, setStudentList] = useState([]);
    const [semesterList, setSemesterList] = useState([]);
    const [selectedSemester, setSelectedSemester] = useState("");
    const [quatrinh, setQuatrinh] = useState({});
    const [giuaki, setGiuaki] = useState({});
    const [cuoiki, setCuoiki] = useState({});
    const [isEditMode, setIsEditMode] = useState(false);
    const [studentColumnIds, setStudentColumnIds] = useState({
        'Quá trình': 1,
        'Giữa kì': 2,
        'Cuối kì': 3,
    });

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
            return response.data;
        } catch (err) {
            console.error(err);
            return null;
        }
    }

    const fetchSubjectsByLecturerId = async (lecturerId) => {
        try {
            const response = await authApi().get(endpoints["get-subject-by-lecturerId"].replace("{lecturerId}", lecturerId));
            setSubjectList(response.data);

            const endpoint = endpoints["semester"]
                + `?lecturerId=${lecturerId}`;

            const semesterResponse = await authApi().get(endpoint);
            setSemesterList(semesterResponse.data);

        } catch (error) {
            console.error(error);
            console.log(error);
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

            const endpoint = endpoints["get-list-student"]
                + `?lecturerId=${lecturerId}&subjectId=${subjectId}&semesterId=${semesterId}`;

            const response = await authApi().get(endpoint);

            if (response.data) {
                console.log(response.data);
                setStudentList(response.data);
            }
        } catch (error) {
            console.error(error);
        }
    };

    const handleEdit = () => {
        setIsEditMode(true);
    };
    const ExitHandleEdit = () => {
        setIsEditMode(false);
    };

    const SaveScore = async (studentId, scoreColumnId) => {
        try {
            const subjectId = selectedSubject;
            const semesterId = selectedSemester;

            const scoreValue = parseFloat(
                scoreColumnId === 1 ? quatrinh[studentId] || 0 :
                    scoreColumnId === 2 ? giuaki[studentId] || 0 :
                        cuoiki[studentId] || 0
            );

            const formData = new FormData();
            formData.append("subjectId", subjectId);
            formData.append("semesterId", semesterId);
            formData.append("studentId", studentId);
            formData.append("scoreValue", scoreValue);
            formData.append("scoreColumnId", scoreColumnId);

            const response = await authApi().post(endpoints["add-score"], formData, {
                headers: {
                    "Content-Type": "multipart/form-data",
                },
            });


            if (response.status === 201) {
                alert(`Lưu điểm thành công!`);

                setQuatrinh({ ...quatrinh, [studentId]: "" });
                setQuatrinh({ ...giuaki, [studentId]: "" });
                setQuatrinh({ ...cuoiki, [studentId]: "" });
            } else {
                alert(`Lưu điểm thất bại!!!`);
            }
        } catch (error) {
            console.error(error);
        }
    };

    const getScoreValue = (scoreDto, columnName) => {
        const score = scoreDto.find(item => item.scoreColumnName === columnName);
        return score ? score.scoreValue : "";
    };

    const handleSendMail = async() => {
        try {
            const lecturerId = selectedLecturer.id;
            const subjectId = selectedSubject;
            const semesterId = selectedSemester;

            console.log("semesterId", semesterId)

            const endpoint = endpoints["send-mail"]
                + `?lecturerId=${lecturerId}&subjectId=${subjectId}&semesterId=${semesterId}`;
            
            console.log(endpoint);

            const response = await authApi().post(endpoint);
            console.log("response code: " + response);


            if (response.status === 200) {
                alert("Gửi mail thành công")
                
            }else{
                alert("Đã có lỗi xảy ra, vui lòng thử lại sau!");
            }
        } catch (error) {
            console.error(error);
        }
    }


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
                        {isEditMode ? (<>
                       <Button className="btnSubmit" onClick={ExitHandleEdit}>Thoát</Button>
                       <Button className="btnSubmit" onClick={handleSendMail}>Gửi mail</Button>
                            </>
                        ) : (
                            <Button className="btnSubmit" onClick={handleEdit}>Nhập điểm</Button>
                        )}
                    </div>
                </Form>
                {studentList.length > 0 && (
                    <Table striped bordered hover>
                        <thead>
                            <tr>
                                <th className="text-center">Mã số sinh viên</th>
                                <th className="text-center">Tên sinh viên</th>
                                <th className="text-center">Ngày sinh</th>
                                <th className="text-center">Quá trình</th>
                                <th className="text-center">Giữa kì</th>
                                <th className="text-center">Cuối kì</th>
                                {isEditMode && (
                                    <>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                    </>
                                )}
                            </tr>
                        </thead>
                        <tbody>
                            {studentList.map((student, index) => (
                                <tr key={index}>
                                    <td className="text-center">{student.studentId}</td>
                                    <td className="text-center">{student.studentName}</td>
                                    <td className="text-center">{formatDate(student.studentBithday)}</td>
                                    <td className="text-center">
                                        {isEditMode ? (
                                            <input
                                                type="number"
                                                value={quatrinh[student.studentId] || ""}
                                                onChange={(e) => setQuatrinh({ ...quatrinh, [student.studentId]: e.target.value })}
                                            />
                                        ) : (
                                            getScoreValue(student.scoreDto, 'Quá trình')
                                        )}
                                    </td>
                                    <td className="text-center">
                                        {isEditMode ? (
                                            <input
                                                type="number"
                                                value={giuaki[student.studentId] || ""}
                                                onChange={(e) => setGiuaki({ ...giuaki, [student.studentId]: e.target.value })}
                                            />
                                        ) : (
                                            getScoreValue(student.scoreDto, 'Giữa kì')
                                        )}
                                    </td>
                                    <td className="text-center">
                                        {isEditMode ? (
                                            <input
                                                type="number"
                                                value={cuoiki[student.studentId] || ""}
                                                onChange={(e) => setCuoiki({ ...cuoiki, [student.studentId]: e.target.value })}
                                            />
                                        ) : (
                                            getScoreValue(student.scoreDto, 'Cuối kì')
                                        )}
                                    </td>
                                    {isEditMode && (<>
                                        <td>
                                            <Button onClick={() => SaveScore(student.studentId, studentColumnIds['Quá trình'])}>Lưu điểm QT</Button>
                                        </td>
                                        <td>
                                            <Button onClick={() => SaveScore(student.studentId, studentColumnIds['Giữa kì'])}>Lưu điểm GK</Button>
                                        </td>
                                        <td>
                                            <Button onClick={() => SaveScore(student.studentId, studentColumnIds['Cuối kì'])}>Lưu điểm CK</Button>
                                        </td>
                                    </>)}

                                </tr>
                            ))}
                        </tbody>
                    </Table>
                )}
            </div>
        </>
    );
}

export default InputScore;