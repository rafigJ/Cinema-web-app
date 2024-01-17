import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import {useFetching} from "../hooks/useFetching";
import FilmService from "../api/FilmService";
import {IFilm} from "../types/model/IFilm";
import {Footer} from "antd/es/layout/layout";
import SessionOverviewContainer
    from "../components/user/FilmOverviewUI/SessionOverviewContainer/SessionOverviewContainer";
import FilmOverviewContainer from "../components/user/FilmOverviewUI/FilmOverviewContainer/FilmOverviewContainer";

const FilmOverviewPage = () => {
    const params = useParams();
    const [film, setFilm] = useState({} as IFilm);

    const [fetchFilmById, isLoading, error, isError] = useFetching(async (id) => {
        const response = await FilmService.getById(id);
        setFilm(response.data);
    })

    useEffect(() => {
        fetchFilmById(params.id);
    }, [params.id]);

    if (isLoading) {
        return <div>Загрузка...</div>
    }

    if (isError) {
        return <div>{error}</div>
    }

    return (
        <main className="main-page">
            <FilmOverviewContainer
                film={film}
            />
            <SessionOverviewContainer
                filmId={film.id}
            />
            <Footer style={{background: "transparent"}}/>
        </main>
    );
};

export default FilmOverviewPage;