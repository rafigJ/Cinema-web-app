import React, {useEffect, useState} from 'react';
import FilmService from "../API/FilmService";
import FilmGrid from "../components/UI/FilmGrid/FilmGrid";
import {useFetching} from "../hooks/useFetching";

const FilmsPage = () => {
    const [films, setFilms] = useState([])

    const [fetchFilms, isFilmsLoading, filmError] = useFetching(async () => {
        const response = await FilmService.getAllFilms(0, 250);
        setFilms(response.data.content)
    },)

    useEffect(() => {
        fetchFilms();
    }, []);

    return (
        <main className="main-page">
            <FilmGrid title="Фильмы" filmArray={films}/>
        </main>
    );
};

export default FilmsPage;