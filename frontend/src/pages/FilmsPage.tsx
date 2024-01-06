import React, {FC, useEffect, useState} from 'react';
import FilmService from "../API/FilmService";
import FilmGrid from "../components/UI/user/FilmGridUI/FilmGrid/FilmGrid";
import {useFetching} from "../hooks/useFetching";
import '../styles/App.css'
import {IFilm} from "../types/model/IFilm";
import {Spin} from "antd";


const FilmsPage: FC = () => {
    const [films, setFilms] = useState([] as IFilm[])

    const [fetchFilms, isLoading, error] = useFetching(async () => {
        const response = await FilmService.getAll(2, 20);
        setFilms(response.data.content)
    },)

    useEffect(() => {
        fetchFilms();
    }, []);

    if (isLoading) {
        return <Spin fullscreen/>
    }

    if (error !== '') {
        return <p>{error}</p>
    }

    return (
        <main className="main-page">
            <FilmGrid title="Фильмы" films={films}/>
        </main>
    );
};

export default FilmsPage;