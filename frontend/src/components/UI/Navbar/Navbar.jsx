import React, {useContext, useState} from 'react';
import logo from './img/logo.png';
import userLogo from './img/user.png';
import {Link, useNavigate} from "react-router-dom";
import NavbarElement from "./NavbarElement";
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
        <div className="header">
            <Link to="/">
                <img src={logo} width="239px" height="50px" alt=""/>
            </Link>
            <nav className="left_nav">
                <ul>
                    <NavbarElement to="/">Главная</NavbarElement>
                    <NavbarElement to="/films">Фильмы</NavbarElement>
                    <NavbarElement to="/users">Другое</NavbarElement>
                </ul>
            </nav>

            <div className="right_nav">
                <button className="btn" onClick={buttonClick}>
                    <img src={userLogo} alt=''/>
                    {isAuth ?
                        <span>Профиль</span>
                        :
                        <span>Войти</span>
                    }
                </button>
            </div>
            <Modal active={modalActive} setActive={setModalActive}>
                <AuthForm setModalActive={setModalActive}/>
            </Modal>
        </div>
    );
};

export default Navbar;