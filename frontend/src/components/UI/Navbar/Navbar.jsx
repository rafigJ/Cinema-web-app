import React, {useState} from 'react';
import logo from './img/logo.png';
import userLogo from './img/user.png';
import {Link} from "react-router-dom";
import NavbarElement from "./NavbarElement";
import './Navbar.css';
import Modal from "../Modal/Modal";
import LoginForm from "../../Authorization/LoginForm";

const Navbar = () => {
    const [modalActive, setModalActive] = useState(false)
    return (
        <div className="header">
            <Link to="/">
                <img src={logo} width="239px" height="50px" alt=""/>
            </Link>
            <nav className="nav">
                <ul>
                    <NavbarElement to="/">Главная</NavbarElement>
                    <NavbarElement to="/films">Фильмы</NavbarElement>
                    <NavbarElement to="/users">Другое</NavbarElement>
                </ul>
            </nav>
            <div className="right_nav">
                <button className="btn" onClick={() => setModalActive(true)}>
                    <img src={userLogo} alt=''/>
                    <span>Войти</span>
                </button>
            </div>
            <Modal active={modalActive} setActive={setModalActive}>
                <LoginForm/>
            </Modal>
        </div>
    );
};

export default Navbar;