import React from 'react';
import {Navigate, Route, Routes} from "react-router-dom";
import HomePage from "../pages/HomePage";
import FilmsPage from "../pages/FilmsPage";
import FilmOverviewPage from "../pages/FilmOverviewPage";


const AppRouter = () => {
    return (
        <Routes>
            <Route index path="/" element={<HomePage/>}/>
            <Route exact path="/films" element={<FilmsPage/>}/>
            <Route exact path="/films/:id" element={<FilmOverviewPage/>}/>
            <Route path="*" element={<Navigate replace to="/"/>}/>
        </Routes>
    );
};

export default AppRouter;