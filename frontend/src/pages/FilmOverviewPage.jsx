import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import {useFetching} from "../hooks/useFetching";
import FilmService from "../API/FilmService";
import FilmOverviewContainer from "../components/UI/FilmOverviewContainer/FilmOverviewContainer";

const FilmOverviewPage = () => {
    const params = useParams();
    const [film, setFilm] = useState({});

    const [fetchFilmById, isLoading, error] = useFetching(async (id) => {
        const response = await FilmService.getById(id);
        setFilm(response.data);
    })

    useEffect(() => {
        fetchFilmById(params.id);
    }, []);

    return (
        <main className="main-page">
            {
                isLoading ?
                    "Загрузка..."
                    :
                    <FilmOverviewContainer
                        name={film.name}
                        year={film.year}
                        poster={film.poster}
                        description={film.description}
                        genreArray={film.genres}
                    />
            }
        </main>
    );
};

export default FilmOverviewPage;