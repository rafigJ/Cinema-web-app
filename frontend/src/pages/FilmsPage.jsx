import React, {useEffect, useState} from 'react';
import FilmService from "../API/FilmService";
import FilmGrid from "../components/UI/FilmGrid/FilmGrid";
import {useFetching} from "../hooks/useFetching";
import '../styles/App.css'

const FilmsPage = () => {
    const [films, setFilms] = useState([])

    const [fetchFilms, isLoading, error] = useFetching(async () => {
        const response = await FilmService.getAll(10, 20);
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