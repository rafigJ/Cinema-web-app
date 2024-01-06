import React, {useContext, useEffect, useState} from 'react';
import {useFetching} from "../hooks/useFetching";
import FilmService from "../API/FilmService";
import FilmGrid from "../components/UI/user/FilmGridUI/FilmGrid/FilmGrid";
import {IFilm} from "../types/model/IFilm";
import {AuthContext} from "../context";
import {Link} from "react-router-dom";
import {Spin} from "antd";

const HomePage = () => {
    const [films, setFilms] = useState([] as IFilm[])
    const {user, isAuth} = useContext(AuthContext);

    const [fetchFilms, isLoading, error] = useFetching(async () => {
        const response = await FilmService.getByName("Криминальное");
        setFilms(response.data.content)
    },)

    useEffect(() => {
        fetchFilms();
    }, []);

    console.log(user);
    if (isLoading) {
        return <div style={{display: 'flex', alignItems: "center", justifyContent: "center", minHeight: "80vh"}}>
            <Spin size="large"/>
        </div>
    }

    if (error !== '') {
        return (
            <div>{error}</div>
        );
    }
    return (
        <main className="main-page">
            <FilmGrid title="Криминальное..." films={films}/>
            {isAuth &&
                <ul>
                    <li>{user?.email}</li>
                    <li>{user?.role}</li>
                    <li>{user?.name}</li>
                </ul>
            }
        </main>
    );
};

export default HomePage;