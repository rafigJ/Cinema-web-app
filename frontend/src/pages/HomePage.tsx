import React, {useEffect, useState} from 'react';
import {useFetching} from "../hooks/useFetching";
import FilmService from "../api/FilmService";
import {IFilm} from "../types/model/IFilm";
import {Spin} from "antd";
import FilmGrid from "../components/user/FilmGridUI/FilmGrid/FilmGrid";

const HomePage = () => {
    const [films, setFilms] = useState([] as IFilm[])

    const [fetchFilms, isLoading, error] = useFetching(async () => {
        const response = await FilmService.getByName("Криминальное");
        setFilms(response.data.content)
    },)

    useEffect(() => {
        fetchFilms();
    }, []);

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
        </main>
    );
};

export default HomePage;