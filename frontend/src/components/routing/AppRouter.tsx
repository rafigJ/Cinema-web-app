import React from 'react';
import {Navigate, Route, Routes} from "react-router-dom";
import HomePage from "../../pages/HomePage";
import FilmsPage from "../../pages/FilmsPage";
import FilmOverviewPage from "../../pages/FilmOverviewPage";
import CrudFilmsPage from "../../pages/admin/CrudPages/CrudFilmsPage";
import {WithNav} from "./WithNav";
import {WithAdminSidebar} from "./WithAdminSidebar";
import ProfilePage from "../../pages/ProfilePage";
import CreateFilmPage from "../../pages/admin/CrudPages/CreateFilmPage";
import UpdateFilmPage from "../../pages/admin/CrudPages/UpdateFilmPage";
import CrudUsersPage from "../../pages/admin/CrudPages/CrudUsersPage";
import StatisticPage from "../../pages/admin/StatisticPage";


const AppRouter = () => {
    return (
        <Routes>
            <Route element={<WithNav/>}>
                <Route index path="/" element={<HomePage/>}/>
                <Route path="/films" element={<FilmsPage/>}/>
                <Route path="/films/:id" element={<FilmOverviewPage/>}/>
                <Route path="/profile" element={<ProfilePage/>}/>
            </Route>
            <Route element={<WithAdminSidebar/>}>
                <Route path="/admin/*" element={<Navigate replace to="/admin/films"/>}/>
                <Route path="/admin/films" element={<CrudFilmsPage/>}/>
                <Route path="/admin/films/create" element={<CreateFilmPage/>}/>
                <Route path="/admin/films/edit/:id" element={<UpdateFilmPage/>}/>
                <Route path="/admin/statistics" element={<StatisticPage/>}/>
                <Route path="/admin/users" element={<CrudUsersPage/>}/>
                <Route path="/admin/sessions" element={<div> Сеансы </div>}/>
                <Route path="/admin/tickets" element={<div> Билеты </div>}/>
            </Route>
            <Route path="*" element={<Navigate replace to="/"/>}/>
        </Routes>
    );
};


export default AppRouter;