import React from 'react';
import logo from './img/logo.png';
import userLogo from './img/user.png';
import {Link} from "react-router-dom";
import NavbarElement from "./NavbarElement";
import './Navbar.css';

const Navbar = () => {
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
                <button className="btn">
                    <img src={userLogo} alt=''/>
                    <span >Войти</span>
                </button>
            </div>
        </div>
    );
};

export default Navbar;