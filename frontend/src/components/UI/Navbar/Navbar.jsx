import React, {useContext, useState} from 'react';
import logo from './img/logo.png';
import userLogo from './img/user.png';
import {Link, useNavigate} from "react-router-dom";
import NavbarElement from "../NavbarElement/NavbarElement";
import './Navbar.css';
import Modal from "../Modal/Modal";
import AuthForm from "../../Authorization/AuthForm";
import {AuthContext} from "../../../context";

const Navbar = () => {
    const [modalActive, setModalActive] = useState(false);
    const {isAuth} = useContext(AuthContext);
    const navigate = useNavigate();

    const buttonClick = () => isAuth ? navigate('/films') : setModalActive(true);


    return (
        <header className="headerContainer">

            <div className="left">
                <Link to="/">
                    <img src={logo} width="239px" height="50px" alt=""/>
                </Link>
            </div>

            <div className="right">
                <nav className="right__nav">
                    <ul>
                        <NavbarElement to="/">Главная</NavbarElement>
                        <NavbarElement to="/films">Фильмы</NavbarElement>
                        <NavbarElement to="/users">Другое</NavbarElement>
                    </ul>
                </nav>
                <div className="right__acc">
                    <button className="btn" onClick={buttonClick}>
                        <img src={userLogo} alt=''/>
                        <span>{isAuth ? "Профиль" : "Войти"}</span>
                    </button>
                </div>
            </div>
            <Modal active={modalActive} setActive={setModalActive}>
                <AuthForm setModalActive={setModalActive}/>
            </Modal>
        </header>
    );
};

export default Navbar;