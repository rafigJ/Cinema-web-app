import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import {useFetching} from "../hooks/useFetching";
import FilmService from "../API/FilmService";
import FilmOverviewContainer from "../components/UI/user/FilmOverviewUI/FilmOverviewContainer/FilmOverviewContainer";
import {IFilm} from "../types/model/IFilm";
import SessionOverviewContainer
    from "../components/UI/user/FilmOverviewUI/SessionOverviewContainer/SessionOverviewContainer";
import {Footer} from "antd/es/layout/layout";

const FilmOverviewPage = () => {
    const params = useParams();
    const [film, setFilm] = useState({} as IFilm);

    const [fetchFilmById, isLoading, error] = useFetching(async (id) => {
        const response = await FilmService.getById(id);
        setFilm(response.data);
    })

    useEffect(() => {
        fetchFilmById(params.id);
    }, [params.id]);

    if (isLoading) {
        return <div>Загрузка...</div>
    }

    if (error !== '') {
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