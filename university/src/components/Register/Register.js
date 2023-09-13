import { useRef, useState } from 'react';
import './Register.css'
import Apis, { endpoints } from '../../configs/Apis';
import { Alert, Form } from 'react-bootstrap';
import { createUserWithEmailAndPassword, updateProfile } from "firebase/auth";
import { auth, db, storage } from "../../firebase";
import { ref, uploadBytesResumable, getDownloadURL } from "firebase/storage";
import { doc, setDoc } from "firebase/firestore";
import { useNavigate } from 'react-router-dom';


const Register = () => {

    const [user, setUser] = useState({
        username: '',
        password: '',
        confirmPass: '',
        email: '',
        avatar: null,
    });

    const [errorMessage, setErrorMessage] = useState('');
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();
    const change = (e, field) => {
        setUser((prevUser) => ({
            ...prevUser,
            [field]: e.target.value,
        }));
    };
    const handleAvatarChange = (e) => {
        setUser((prevUser) => ({
            ...prevUser,
            avatar: e.target.files[0],
        }));
    };

    const handleRegister = async (e) => {
        try {
            e.preventDefault();
            setLoading(true);

            if (user.password !== user.confirmPass) {
                setErrorMessage('Mật khẩu xác nhận không khớp');
                setLoading(false);
                return;
            }

            const formData = new FormData();
            formData.append('username', user.username);
            formData.append('password', user.password);
            formData.append('email', user.email);
            formData.append('avatar', user.avatar);


            const response = await Apis.post(endpoints['register'], formData, {
                headers: {
                    'Content-Type': 'multipart/form-data', // Important: set content type for file upload
                },
            });

            if (response.status === 201) {
                const res = await createUserWithEmailAndPassword(auth, user.email, user.password);

                const date = new Date().getTime();
                const storageRef = ref(storage, `${user.username + date}`);

                await uploadBytesResumable(storageRef, user.avatar).then(() => {
                    getDownloadURL(storageRef).then(async (downloadURL) => {
                        try {
                            await updateProfile(res.user, {
                                displayName: user.username,
                                photoURL: downloadURL,
                            });

                            await setDoc(doc(db, "users", res.user.uid), {
                                uid: res.user.uid,
                                username: user.username,
                                email: user.email,
                                photoURL: downloadURL,
                            });

                            await setDoc(doc(db, "userChats", res.user.uid), {});
                            navigate("/");
                        } catch (err) {
                            console.log(err);
                            setErrorMessage('Đã xảy ra lỗi khi cập nhật hồ sơ');
                        }
                    });
                });
            } else {
                setErrorMessage(response.data);
            }
        } catch (err) {
            setErrorMessage(err.response?.data || err.message);
        } finally {
            setLoading(false);
        }
    };


    return <>
        <Form onSubmit={handleRegister} className="Auth-form-container">
            <div className="Auth-form">
                <div className="Auth-form-content">
                    {errorMessage && (
                        <Alert className='center' variant="danger">
                            {errorMessage}
                        </Alert>
                    )}

                    <h3 className="Auth-form-title">Đăng Ký</h3>
                    <div className="form-group mt-3">
                        <label>Tài khoản</label>
                        <input
                            required
                            value={user.username}
                            onChange={e => change(e, "username")}
                            type="text"
                            className="form-control mt-1"
                            placeholder="Nhập tài khoản"
                        />
                    </div>
                    <div className="form-group mt-3">
                        <label>Mật khẩu</label>
                        <input
                            required
                            value={user.password}
                            onChange={e => change(e, "password")}
                            type="password"
                            className="form-control mt-1"
                            placeholder="Nhập mật khẩu"
                        />
                    </div>
                    <div className="form-group mt-3">
                        <label>Xác nhận Mật khẩu</label>
                        <input
                            required
                            value={user.confirmPass}
                            onChange={e => change(e, "confirmPass")}
                            type="password"
                            className="form-control mt-1"
                            placeholder="Xác nhận mật khẩu"
                        />
                    </div>
                    <div className="form-group mt-3">
                        <label>Email</label>
                        <input
                            required
                            value={user.email}
                            onChange={e => change(e, "email")}
                            type="email"
                            className="form-control mt-1"
                            placeholder="Email"
                        />
                    </div>
                    <div className="form-group mt-3">
                        <label>Avatar</label>
                        <input
                            required
                            type="file"
                            onChange={handleAvatarChange}
                            accept="image/*"
                            className="form-control mt-1"
                            placeholder="Avatar"
                        />
                        <label htmlFor="file" >
                            <span>Add an avatar</span>
                        </label>
                    </div>
                    <button disabled={loading} type="submit">Đăng ký</button>
                    {loading && 'Uploading and compressing the image please wait...'}
                    {errorMessage && <span>Something went wrong</span>}
                </div>
            </div>
        </Form>
    </>
}

export default Register;


    //     const [user, setUser] = useState({
    //         "username": "",
    //         "password": "",
    //         "email": "",
    //         "confirmPass": ""
    //     });
    //     const avatar = useRef(null);
    //     const [username, setUsername] = useState();
    //     const [password, setPassword] = useState();
    //     const [email, setEmail] = useState();
    //     // const [avatar1, setAvatar] = useState();
    //     const [alertMessage, setAlertMessage] = useState();
    //     const [errorMessage, setErrorMessage] = useState();
    //     const [loading, setLoading] = useState(false);
    //     const navigate = useNavigate();
    //     const change = (evt, field) => {
    //         setUser(current => {
    //             return { ...current, [field]: evt.target.value };
    //         });
    //     }

    //     const register = (evt) => {
    //         evt.preventDefault();

    //         const process = async () => {
    //             let formData = new FormData();
    //             for (let field in user)
    //                 formData.append(field, user[field]);

    //             if (avatar.current.files.length > 0)
    //                 formData.append("avatar", avatar.current.files[0]);

    //             setLoading(true);
    //             let res = await Apis.post(endpoints['register'], formData);

    //             try {
    //                 setUsername(user.username);
    //                 setEmail(user.email);
    //                 setPassword(user.password);
    //                 //Create user
    //                 const res1 = await createUserWithEmailAndPassword(auth, email, password);

    //                 //Create a unique image name
    //                 const date = new Date().getTime();
    //                 const storageRef = ref(storage, `${username + date}`);

    //                 await uploadBytesResumable(storageRef, avatar).then(() => {
    //                     getDownloadURL(storageRef).then(async (downloadURL) => {
    //                         try {
    //                             //Update profile
    //                             await updateProfile(res1.user, {
    //                                 username,
    //                                 photoURL: downloadURL,
    //                             });
    //                             //create user on firestore
    //                             await setDoc(doc(db, "users", res1.user.uid), {
    //                                 uid: res1.user.uid,
    //                                 username,
    //                                 email,
    //                                 photoURL: downloadURL,
    //                             });

    //                             //create empty user chats on firestore
    //                             await setDoc(doc(db, "userChats", res1.user.uid), {});

    //                         } catch (err) {
    //                             console.log(err);
    //                             setErrorMessage(true);
    //                             setLoading(false);
    //                         }
    //                     });
    //                 });
    //             } catch (err) {
    //                 setErrorMessage(true);
    //                 setLoading(false);
    //             }
    //             if (res.status === 201) {
    //                 setAlertMessage('Đăng ký thành công!')
    //                 navigate("/");
    //             } else {
    //                 setErrorMessage(true);
    //                 setLoading(false);
    //             }
    //         }


    //         if (user.password !== user.confirmPass) {
    //             setErrorMessage("Mật khẩu KHÔNG khớp!");
    //         } else {
    //             process();
    //         }
    //     }