import React from 'react';
import classes from './NavbarElement.module.css'
import {NavLink} from "react-router-dom";

const NavbarElement = ({children, ...props}) => {
    return (
        <li className={classes.navbar_item}>
            <NavLink {...props} className={({isActive}) => isActive ? classes.active : classes.default}>
                {children}
            </NavLink>
        </li>
    );
};

export default NavbarElement;