import React from 'react';
import logo from '../img/logo.png';
import userLogo from '../img/user.png';
import {Link} from "react-router-dom";
import classes from "./Navbar.module.css"
import NavbarElement from "./NavbarElement";

const Navbar = () => {
    return (
        <div className={classes.header}>
            <Link to="/">
                <img src={logo} width="239px" height="50px" alt=""/>
            </Link>
            <nav className={classes.nav}>
                <ul>
                    <NavbarElement to="/">Главная</NavbarElement>
                    <NavbarElement to="/films">Фильмы</NavbarElement>
                    <NavbarElement to="/users">Другое</NavbarElement>
                </ul>
            </nav>
            <div className={classes.right_nav}>
                <button className={classes.btn}>
                    <img src={userLogo} alt=''/>
                    Войти
                </button>
            </div>
        </div>
    );
};

export default Navbar;