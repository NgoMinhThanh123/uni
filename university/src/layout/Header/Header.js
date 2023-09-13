import { Button, Container, Nav, Navbar } from "react-bootstrap";
import { MyUserContext } from "../../App";
import { useContext } from "react";
import { Link } from "react-router-dom";
import './Header.css'

const Header = () => {
  const [user, dispatch] = useContext(MyUserContext);

  const logout = () => {
    dispatch({
      "type": "logout"
    })
  }

  return (
    <>
      <Navbar expand="lg" className="back-ground">
        <Container>
          <Navbar.Brand style={{color:"black"}} ><img className="default-logo dark-version" alt="TRƯỜNG ĐẠI HỌC MỞ TP HCM" src="https://ou.edu.vn/wp-content/uploads/2016/08/Logo.png" style={{height: "79px", width: "350px"}} /></Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="me-auto">
              <Link style={{color:"black", fontWeight:"bold"}} className="nav-link" to="/">Trang chủ</Link>
              <Link style={{color:"black", fontWeight:"bold"}} className="nav-link" to="/profile">Hồ sơ</Link>
              {user.role === "ROLE_GIANGVIEN" && (
                <Link style={{color:"black", fontWeight:"bold"}} className="nav-link"  to="/student">
                  Sinh viên
                </Link>
              )}
              {user.role === "ROLE_GIANGVIEN" && (
                <Link style={{color:"black", fontWeight:"bold"}} className="nav-link"  to="/homeroom-teacher">
                  Lớp chủ nhiệm
                </Link>
              )}
              {user.role === "ROLE_GIANGVIEN" && (
                <Link style={{color:"black", fontWeight:"bold"}} className="nav-link" to="/input-score">
                Nhập điểm
                </Link>
              )}
              {user.role === "ROLE_SINHVIEN" && (
                <Link style={{color:"black", fontWeight:"bold"}} className="nav-link" to="/score">
                  Điểm
                </Link>
              )}
              <Link style={{color:"black", fontWeight:"bold"}} className="nav-link" to="/posts">Diễn đàn</Link>
              <Link  style={{color:"black", fontWeight:"bold"}} className="nav-link" to="/chat">Chat</Link>
              <Link style={{color:"black", fontWeight:"bold"}} className="nav-link" >Chào {user.username}!</Link>
              <Link style={{color:"black", fontWeight:"bold"}} className="nav-link" variant="secondary" onClick={logout} to="/login">Đăng xuất</Link>
              
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
    </>
  )
}

export default Header