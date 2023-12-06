import React from 'react';
import {NavLink} from "react-router-dom";
import "./NavbarElement.css"

const NavbarElement = ({children, ...props}) => {
    return (
        <li className="navigation__list-item">
            <NavLink {...props}
                     className={({isActive}) => {
                         return isActive ? "navigation__link" : "navigation__link navigation__link_default"
                     }}>
                {children}
            </NavLink>
        </li>
    );
};

export default NavbarElement;