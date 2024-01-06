import React, {useContext, useState} from 'react';
import logo from './img/logo.png';
import userLogo from './img/user.png';
import {Link, useNavigate} from "react-router-dom";
import NavbarElement from "../NavbarElement/NavbarElement";
import './Navbar.css';
import Modal from "../../../Modal/Modal";
import AuthForm from "../../../../Authorization/AuthForm";
import {AuthContext} from "../../../../../context";
import Button from "../../../Button/Button";

const Navbar = () => {
    const [modalActive, setModalActive] = useState(false);
    const {user, isAuth} = useContext(AuthContext);
    const navigate = useNavigate();

    const buttonClick = () => isAuth ? navigate('/films') : setModalActive(true);


    return (
        <header className="header">
            <div className="logo">
                <Link to="/">
                    <img src={logo} width="239px" height="50px" alt="logo"/>
                </Link>
            </div>

            <div className="navigation">
                <nav className="navigation__menu">
                    <ul className="navigation__list">
                        <NavbarElement to="/">Главная</NavbarElement>
                        <NavbarElement to="/films">Фильмы</NavbarElement>
                        {user.role === 'ADMIN' &&
                            <NavbarElement to="/admin">Админ-панель</NavbarElement>
                        }
                        <NavbarElement to="/users">Другое</NavbarElement>
                    </ul>
                </nav>
                <div className="navigation__user-menu">
                    <Button onClick={buttonClick}>
                        <img className="navigation__user-icon" src={userLogo} alt='Войти'/>
                        <span>{isAuth ? "Профиль" : "Войти"}</span>
                    </Button>
                </div>
            </div>
            <Modal active={modalActive} setActive={setModalActive}>
                <AuthForm setModalActive={setModalActive}/>
            </Modal>
        </header>
    );
};

export default Navbar;