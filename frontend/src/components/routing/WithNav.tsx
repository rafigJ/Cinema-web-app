import React from 'react';
import {Outlet} from 'react-router';
import Navbar from "../UI/Navbar/Navbar";

export const WithNav = () => {
    return (
        <>
            <Navbar />
            <Outlet />
        </>
    );
};