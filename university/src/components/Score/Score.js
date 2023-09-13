import React, { useContext, useState } from "react";
import { MyUserContext } from "../../App";
import { authApi, endpoints } from "../../configs/Apis";
import { useEffect } from "react";
import "./Score.css"

const Score = () => {
    const [user] = useContext(MyUserContext);
    const [subject, setSubject] = useState([]);
    const [semesters, setSemesters] = useState([]); // Mảng semesters
    const [scoreLists, setScoreLists] = useState([]); // Mảng scoreLists chứa thông tin điểm số của từng học kỳ
    const [err, setErr] = useState("");

    useEffect(() => {
        async function fetchData() {
            try {
                const studentUsername = user.username;
                const response = await authApi().get(endpoints['get-student-by-username'].replace("{username}", studentUsername));
                const studentId = response.data.id;
                const semesterResponse = await authApi().get(endpoints["semester-student"] + `?studentId=${studentId}`);
                console.log(semesterResponse.data);

                // Lưu thông tin về semesters vào state
                setSemesters(semesterResponse.data);

                const scoreLists = []; // Mảng để lưu thông tin điểm số của từng học kỳ

                for (const semester of semesterResponse.data) {
                    const semesterId = semester[0]; // Lấy id của học kỳ

                    const scoreEndpoint = endpoints["score-list"] + `?studentId=${studentId}&semesterId=${semesterId}`;
                    const scoreResponse = await authApi().get(scoreEndpoint);

                    console.log(scoreResponse.data);

                    // Lưu thông tin về điểm số của từng học kỳ vào mảng scoreLists
                    scoreLists.push(scoreResponse.data);
                }

                // Lưu mảng scoreLists vào state
                setScoreLists(scoreLists);
            } catch (err) {
                setErr(true);
            }
        }

        fetchData();
    }, [user]);

    console.log(scoreLists);

    return (
        <div>
            <h2 className="score">Thông tin điểm số</h2>
            {semesters.map((semester, semesterIndex) => (
                <div key={semesterIndex}>
                    <div className="semester">{`${semester[1]}-${semester[2]}`}</div>
                    <table className="score-table">
                        <thead>
                            <tr>
                                <th>STT</th>
                                <th>Tên môn học</th>
                                <th>Số tín chỉ</th>
                                <th>Quá trình</th>
                                <th>Giữa kì</th>
                                <th>Cuối kì</th>
                                <th>TK</th>
                            </tr>
                        </thead>
                        <tbody>
                            {scoreLists[semesterIndex]?.map((score, scoreIndex) => (
                                <tr key={scoreIndex}>
                                    <td>{scoreIndex + 1}</td>
                                    <td>{score.subjectName}</td>
                                    <td>{score.credit}</td>
                                    <td>
                                        {score.scoreDto && score.scoreDto.length > 0 ? (
                                            score.scoreDto[0].scoreColumnName === 'Quá trình' ? (
                                                <span>{score.scoreDto[0].scoreValue || "-"}</span>
                                            ) : null
                                        ) : null}
                                    </td>
                                    <td>
                                        {score.scoreDto && score.scoreDto.length > 1 ? (
                                            score.scoreDto[1].scoreColumnName === 'Giữa kì' ? (
                                                <span>{score.scoreDto[1].scoreValue || "-"}</span>
                                            ) : null
                                        ) : null}
                                    </td>
                                    <td>
                                        {score.scoreDto && score.scoreDto.length > 2 ? (
                                            score.scoreDto[2].scoreColumnName === 'Cuối kì' ? (
                                                <span>{score.scoreDto[2].scoreValue || "-"}</span>
                                            ) : null
                                        ) : null}
                                    </td>
                                    <td></td>
                                </tr>
                            ))}                     
                        </tbody>
                    </table>
                </div>
            ))}
        </div>
    );
}

export default Score;