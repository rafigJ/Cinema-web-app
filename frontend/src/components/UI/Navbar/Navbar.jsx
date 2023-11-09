import React from 'react';
import logo from '../img/logo.png';

const Navbar = () => {
    return (
        <div className="navbar">

            <img src={logo} width="239px" height="50px" alt=""/>
            {/*todo Link with img*/}
            <div className="navbar__links">
                <a>Главная</a>
                <a>Фильмы</a>
            </div>
        </div>
    );
};

export default Navbar;