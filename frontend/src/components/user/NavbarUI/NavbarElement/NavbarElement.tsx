import React, {FC, PropsWithChildren} from 'react';
import {NavLink} from "react-router-dom";
import "./NavbarElement.css"

interface NavbarElementProps extends PropsWithChildren {
    to: string;
}

const NavbarElement: FC<NavbarElementProps> = ({to, children}) => {
    return (
        <li className="navigation__list-item">
            <NavLink to={to}
                     className={({isActive}) => {
                         return isActive ? "navigation__link" : "navigation__link navigation__link_default"
                     }}>
                {children}
            </NavLink>
        </li>
    );
};

export default NavbarElement;