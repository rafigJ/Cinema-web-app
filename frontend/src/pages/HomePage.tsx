import React, {useEffect, useState} from 'react';
import {useFetching} from "../hooks/useFetching";
import FilmService from "../API/FilmService";
import FilmGrid from "../components/UI/FilmGrid/FilmGrid";
import {IFilm} from "../types/model/IFilm";

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
        return <p>Загрузка...</p>
    }

    if (error !== '') {
        return <p>{error}</p>
    }
    return (
        <main className="main-page">
            <FilmGrid title="Криминальное..." films={films}/>
        </main>
    );
};

export default HomePage;