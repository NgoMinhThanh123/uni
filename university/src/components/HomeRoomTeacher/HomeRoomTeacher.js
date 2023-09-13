import { Button, Form, Table } from "react-bootstrap";
import Apis, { authApi, endpoints } from "../../configs/Apis";
import React, { useContext, useEffect, useState } from "react";
import { MyUserContext } from "../../App";
import MySpinner from "../../layout/MySpinner";
import formatDate from "../FormatDare/FormatDate";
import "./HomeRoomTeacher.css"


const HomeRoomTeacher = () => {
    const [user] = useContext(MyUserContext);
    const [studentList, setStudentList] = useState([]);


    useEffect(() => {
        const getStudent = async () => {
            try {
                const lecturerUsername = user.username;
                const response = await authApi().get(endpoints['get-lecturer-by-username'].replace("{username}", lecturerUsername));
                console.log(response.data.id);
                const listStudent = await authApi().get(endpoints["student-home-room-teacher"].replace("{lecturerId}", response.data.id));

                if (listStudent.data) {
                    console.log(listStudent.data);
                    setStudentList(listStudent.data);
                }
            } catch (error) {
                console.error(error);
            }
        };

        getStudent();
    }, []);

    const extractedId = studentList.length > 0 ? studentList[0].classesId?.id || "N/A" : "N/A";

    if (studentList === null) {
        return <div className="text-hr-teacher">Không có lớp chủ nhiệm</div>;
    }

    return(
    <>
        <div className="text-hr-teacher">LỚP CHỦ NHIỆM</div>
        <div>Lớp: {extractedId}</div>
        <div className="table">
            {studentList.length > 0 && (<>
                <Table striped bordered hover>
                    <thead>
                        <tr>
                            <th style={{ textAlign: "center" }}>Mã số sinh viên</th>
                            <th style={{ textAlign: "center" }}>Tên sinh viên</th>
                            <th style={{ textAlign: "center" }}>Ngày sinh</th>
                            <th style={{ textAlign: "center" }}>Giới tính</th>
                            <th style={{ textAlign: "center" }}>Số điện thoại</th>
                        </tr>
                    </thead>
                    <tbody>
                        {studentList.map((student) => (
                            <tr key={student.id}>
                                <td style={{ textAlign: "center" }}>{student.id}</td>
                                <td style={{ textAlign: "center" }}>{student.name}</td>
                                <td style={{ textAlign: "center" }}>{formatDate(student.birthday)}</td>
                                <td style={{ textAlign: "center" }}>{student.gender === 1 ? "Nam" : "Nữ"}</td>
                                <td style={{ textAlign: "center" }}>{student.phone}</td>
                            </tr>
                        ))}
                    </tbody>
                </Table>
            </>)}

        </div>
    </>)
};

export default HomeRoomTeacher;






