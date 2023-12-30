import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import {useFetching} from "../hooks/useFetching";
import FilmService from "../API/FilmService";
import FilmOverviewContainer from "../components/UI/FilmOverviewContainer/FilmOverviewContainer";
import {IFilm} from "../types/model/IFilm";

const FilmOverviewPage = () => {
    const params = useParams();
    const [film, setFilm] = useState({} as IFilm);

    const [fetchFilmById, isLoading, error] = useFetching(async (id) => {
        const response = await FilmService.getById(id);
        setFilm(response.data);
    })

    useEffect(() => {
        fetchFilmById(params.id);
    }, []);

    if (isLoading) {
        return <p>Загрузка...</p>
    }

    if (error !== '') {
        return <p>{error}</p>
    }
    return (
        <main className="main-page">
            <FilmOverviewContainer
                film={film}
            />
        </main>
    );
};

export default FilmOverviewPage;