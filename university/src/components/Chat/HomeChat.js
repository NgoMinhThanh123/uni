import React from "react"
import SideBar from "./SideBar"
import Chat from "./Chat"
import './HomeChat.css'
const HomeChat = () => {
    return (
        <div className="home">
            <div className="container">
                <SideBar />
                <Chat />
            </div>
        </div>
    )
}

export default HomeChat