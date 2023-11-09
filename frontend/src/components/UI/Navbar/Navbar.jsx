import React from 'react';
import logo from '../img/logo.png';

const Navbar = () => {
    return (
        <div className="navbar">

            <img src={logo} width="239px" height="50px" alt=""/>
            {/*todo Link with img*/}
            <div className="navbar__links">
                <ul className="navbar_list">
                    <li className="navbar_item"><a>Главная</a></li>
                    <li className="navbar_item"><a>Фильмы</a></li>
                    <li className="navbar_item"><a>Другое</a></li>
                </ul>
            </div>
        </div>
    );
};

export default Navbar;