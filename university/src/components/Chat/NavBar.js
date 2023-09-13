import { signOut } from "firebase/auth"
import { auth } from "../../firebase";
import React, { useContext } from "react"
import { AuthContext } from "../context/AuthContext";


const Navbar = () => {
    const { currentUser } = useContext(AuthContext)

    return (
        <div className='navbar'>
            <span className="logo">Lama Chat</span>
            <div className="user">
                {currentUser.photoURL && <img src={currentUser.photoURL} alt="" />}
                <span>{currentUser.username}</span>
                <button onClick={() => {
                    console.log("Logout button clicked");
                    signOut(auth);
                }}>logout</button>
            </div>
        </div>
    )
}

export default Navbar;