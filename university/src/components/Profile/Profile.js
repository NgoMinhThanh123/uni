import { useContext, useEffect, useState } from 'react';
import './Profile.css'
import {authApi, endpoints } from '../../configs/Apis';
import { MyUserContext } from '../../App';
import { AuthContext } from '../context/AuthContext';

const Profile = ({}) => {
    const [user] = useContext(MyUserContext);
    const [userInfo, SetUserInfo] = useState({});
    const [formattedBirthday, setFormattedBirthday] = useState("");
    const { currentUser } = useContext(AuthContext)
    useEffect(() => {
        const fetchUserInfo = async () => {
            try {
                let endpoint = "";

                if (user.role === "ROLE_SINHVIEN") {
                    endpoint = endpoints['get-student-by-username'];
                } else if (user.role === "ROLE_GIANGVIEN") {
                    endpoint = endpoints['get-lecturer-by-username'];
                }
                const response = await authApi().get(endpoint.replace("{username}", user.username));
                SetUserInfo(response.data);
                console.log(response);
                console.log(userInfo);

                if (response.data.birthday) {
                    const birthdayTimestamp = response.data.birthday;
                    const birthdayDate = new Date(birthdayTimestamp);

                    const formattedBirthday = `${birthdayDate.getDate()}/${birthdayDate.getMonth() + 1}/${birthdayDate.getFullYear()}`;
                    setFormattedBirthday(formattedBirthday);
                }
            } catch (error) {
                console.error(error);
            }
        };

        fetchUserInfo();
    }, [user.role, user.username]);

    // useEffect(() => {
    //     if (userInfo.birthday) {
    //         const birthdayTimestamp = userInfo.birthday;
    //         const birthdayDate = new Date(birthdayTimestamp);

    //         const formattedBirthday = `${birthdayDate.getDate()}/${birthdayDate.getMonth() + 1}/${birthdayDate.getFullYear()}`;
    //         setFormattedBirthday(formattedBirthday);
    //     }
    // }, [userInfo]);
    return <>
        <section className="section about-section gray-bg" id="about">
            <div className="container">
                <div className="row align-items-center flex-row-reverse">
                    <div className="col-lg-6">
                        <div className="about-text go-to">
                            <h3 className="dark-color">Giới thiệu</h3>
                            <h6 className="theme-color lead">Mô tả</h6>
                            <p>I <mark>design and develop</mark> services for customers of all sizes, specializing in creating stylish, modern websites, web services and online stores. My passion is to design digital user experiences through the bold interface and meaningful interactions.</p>
                            <div className="row about-list">
                                <div className="col-md-6">
                                    <div className="media">
                                        <label>Tên</label>
                                        <p>{userInfo.name}</p>
                                    </div>     
                                    <div className="media">
                                        <label>Ngày sinh</label>
                                        <p>{formattedBirthday}</p>
                                    </div>                                   
                                    <div className="media">
                                        <label>Địa chỉ</label>
                                        <p>{userInfo.address}</p>
                                    </div>
                                </div>
                                <div className="col-md-6">
                                    <div className="media">
                                        <label>E-mail</label>
                                        <p>{user.email}</p>
                                    </div>
                                    <div className="media">
                                        <label>Số điện thoại</label>
                                        <p>{userInfo.phone}</p>
                                    </div>
                                    <div className="media">
                                        <label>Khoa</label>
                                        <p>{userInfo.facultyId && userInfo.facultyId.name}</p>
                                    </div>
                                    <div className="media">
                                        <label>Chuyên ngành</label>
                                        <p>{userInfo.majorId && userInfo.majorId.name}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="col-lg-6">
                        <div className="about-avatar text-center">
                            <img src={user.avatar} title="" alt="" />
                        </div>
                    </div>
                </div>

            </div>
        </section>
    </>


}

export default Profile;