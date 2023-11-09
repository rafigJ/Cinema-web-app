import React from 'react';
import {Navigate, Route, Routes} from "react-router-dom";
import HomePage from "../pages/HomePage";


const AppRouter = () => {
    return (
        <Routes>
            <Route path="/" element={<HomePage/>}/>
            <Route path="*" element={<Navigate replace to="/"/>}/>
        </Routes>
    );
};

export default AppRouter;