import React from 'react';
import {Navigate, Route, Routes} from "react-router-dom";
import HomePage from "../pages/HomePage";
import FilmsPage from "../pages/FilmsPage";


const AppRouter = () => {
    return (
        <Routes>
            <Route index path="/" element={<HomePage/>}/>
            <Route path="/films" element={<FilmsPage/>}/>
            <Route path="*" element={<Navigate replace to="/"/>}/>
        </Routes>
    );
};

export default AppRouter;