import { useContext, useState } from 'react';
import './Login.css'
import { MyUserContext } from '../../App';
import Apis, { authApi, endpoints } from '../../configs/Apis';
import { Link, Navigate, useNavigate } from 'react-router-dom';
import { Alert, Form } from 'react-bootstrap';
import cookie from "react-cookies";
import { auth } from "../../firebase";
import { signInWithEmailAndPassword } from "firebase/auth";


const Login = () => {
    const [user, dispatch] = useContext(MyUserContext);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [err, setErr] = useState(false);
    const navigate = useNavigate();

    const getUserByUsername = async (username) => {
        try {
            const response = await Apis.get(endpoints["get-user"].replace("{username}", username));
            return response.data;
        } catch (error) {
            console.error(error);
            return null; // Hoặc xử lý lỗi theo cách bạn muốn
        }
    }

    const login = (evt) => {
        evt.preventDefault();

        const process = async () => {
            try {
                let res = await Apis.post(endpoints['login'], {
                    "username": username,
                    "password": password
                });
                cookie.save("token", res.data);

                let { data } = await authApi().get(endpoints['current-user']);
                cookie.save("user", data);

                dispatch({
                    "type": "login",
                    "payload": data
                });

                try {
                    const u = await getUserByUsername(username);
                    if (u) {
                        const studentUsername = u.username;

                        // Fetch student information
                        const studentInfo = await authApi().get(endpoints['get-student-by-username'].replace("{username}", studentUsername));

                        const userEmail = u.email; // Lấy email của người dùng
                        await signInWithEmailAndPassword(auth, userEmail, password);
                        navigate("/")
                    } else {
                        console.log('User not found');
                        setErr(true);
                    }

                } catch (err) {
                    setErr(true);
                }
            } catch (err) {
                console.error(err);
                setErr(true);
            }
        }

        process();
    }


    if (user !== null)
        return <Navigate to="/" />

    return <>
        <Form onSubmit={login} className="Auth-form-container">
            <div className="Auth-form">
            {err && (
                <Alert variant="danger">
                    Tên người dùng hoặc mật khẩu không chính xác. Vui lòng thử lại.
                </Alert>
            )}
                <div className="Auth-form-content">
                    <h3 className="Auth-form-title">Đăng nhập</h3>
                    <div className="form-group mt-3">
                        <label>Tài khoản</label>
                        <input
                            required
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            type="text"
                            className="form-control mt-1"
                            placeholder="Nhập tài khoản"
                        />
                    </div>
                    <div className="form-group mt-3">
                        <label>Mật khẩu</label>
                        <input
                            required
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            type="password"
                            className="form-control mt-1"
                            placeholder="Nhập mật khẩu"
                        />
                    </div>
                    <div className="d-grid gap-2 mt-3">
                        <button type="submit" className="btn btn-primary">
                            Đăng nhập
                        </button>
                    </div>
                    {err && <span>Something went wrong</span>}
                    <div className="login text-right mt-2">
                        <div className="forgot-password">
                            Quên <Link to="#">mật khẩu?</Link>
                        </div>
                        <div className="register-link">
                            <Link to="/register">Đăng ký</Link>
                        </div>
                    </div>
                </div>
            </div>
        </Form>
    </>
}

export default Login;
// const Login = () => {
//     const [user, dispatch] = useContext(MyUserContext);
//     const [username, setUsername] = useState('');
//     const [password, setPassword] = useState('');
//     const [err, setErr] = useState(false);
//     const navigate = useNavigate();

//     const getUserByUsername = async (username) => {
//         try {
//             const response = await Apis.get(endpoints["get-user"].replace("{username}", username));
//             return response.data;
//         } catch (error) {
//             console.error(error);
//             return null; // Hoặc xử lý lỗi theo cách bạn muốn
//         }
//     }


//     const login = (evt) => {
//         evt.preventDefault();

//         const process = async () => {
//             try {

//                 let res = await Apis.post(endpoints['login'], {
//                     "username": username,
//                     "password": password
//                 });

//                 cookie.save("token", res.data);

//                 let { data } = await authApi().get(endpoints['current-user']);
//                 cookie.save("user", data);

//                 console.info(data);

//                 dispatch({
//                     "type": "login",
//                     "payload": data,
//                     username: username
//                 });

//                 try {
//                     const u = await getUserByUsername(username);
//                     if (u) {
//                         // const studentUsername = u.username;
//                         // console.log('Student Username:', studentUsername);

//                         // // Fetch student information
//                         // const studentInfo = await authApi().get(endpoints['get-student-by-username'].replace("{username}", studentUsername));
//                         // console.log('Student info:', studentInfo);

//                         const userEmail = u.email; // Lấy email của người dùng
//                         console.log('User Email:', userEmail);
//                         await signInWithEmailAndPassword(auth, userEmail, password);
//                         navigate("/")
//                     } else {
//                         console.log('User not found');

//                     }

//                 } catch (err) {
//                     setErr(true);
//                 }

//             } catch (err) {
//                 console.error(err);
//                 setErr(true);
//             }
//         }

//         if(user !== null) {
//         process();
//     }

//     return <>
//         <Form onSubmit={login} className="Auth-form-container">
//             <div className="Auth-form">
//                 <div className="Auth-form-content">
//                     {err && (
//                         <Alert className='center' variant="danger">
//                             {err}
//                         </Alert>
//                     )}
//                     <h3 className="Auth-form-title">Đăng nhập</h3>
//                     <div className="form-group mt-3">
//                         <label>Tài khoản</label>
//                         <input
//                             required
//                             value={username}
//                             onChange={(e) => setUsername(e.target.value)}
//                             type="text"
//                             className="form-control mt-1"
//                             placeholder="Nhập tài khoản"
//                         />
//                     </div>
//                     <div className="form-group mt-3">
//                         <label>Mật khẩu</label>
//                         <input
//                             required
//                             value={password}
//                             onChange={(e) => setPassword(e.target.value)}
//                             type="password"
//                             className="form-control mt-1"
//                             placeholder="Nhập mật khẩu"
//                         />
//                     </div>
//                     <div className="d-grid gap-2 mt-3">
//                         <button type="submit" className="btn btn-primary">
//                             Đăng nhập
//                         </button>
//                     </div>
//                     <div className="error-message">{err && <span>Something went wrong</span>}</div>
//                     <div className="login text-right mt-2">
//                         <div className="forgot-password">
//                             Quên <Link to="#">mật khẩu?</Link>
//                         </div>
//                         <div className="register-link">
//                             <Link to="/register">Đăng ký</Link>
//                         </div>
//                     </div>
//                 </div>
//             </div>
//         </Form>
//     </>
// }
// }

// export default Login;