import React from 'react';
import {Outlet} from 'react-router';
import Navbar from "../user/NavbarUI/Navbar/Navbar";
import MyFooter from "../user/MyFooter/MyFooter";

export const WithNav = () => {
    return (
        <>
            <Navbar />
            <Outlet />
            <MyFooter/>
        </>
    );
};