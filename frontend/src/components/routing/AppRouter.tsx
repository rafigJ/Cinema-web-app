import React from 'react';
import {Navigate, Route, Routes} from "react-router-dom";
import HomePage from "../../pages/HomePage";
import FilmsPage from "../../pages/FilmsPage";
import FilmOverviewPage from "../../pages/FilmOverviewPage";
import CrudFilmsPage from "../../pages/admin/CrudPages/CrudFilmsPage";
import {WithNav} from "./WithNav";
import {WithAdminSidebar} from "./WithAdminSidebar";


const AppRouter = () => {
    return (
        <Routes>
            <Route element={<WithNav/>}>
                <Route index path="/" element={<HomePage/>}/>
                <Route path="/films" element={<FilmsPage/>}/>
                <Route path="/films/:id" element={<FilmOverviewPage/>}/>
            </Route>
            <Route element={<WithAdminSidebar/>}>
                <Route path="/admin/*" element={<Navigate replace to="/admin/films"/>}/>
                <Route path="/admin/films" element={<CrudFilmsPage/>}/>
                <Route path="/admin/statistics" element={<div> Статистика </div>}/>
                <Route path="/admin/users" element={<div> Пользователи </div>}/>
                <Route path="/admin/sessions" element={<div> Сеансы </div>}/>
                <Route path="/admin/tickets" element={<div> Билеты </div>}/>
            </Route>
            <Route path="*" element={<Navigate replace to="/"/>}/>
        </Routes>
    );
};


export default AppRouter;