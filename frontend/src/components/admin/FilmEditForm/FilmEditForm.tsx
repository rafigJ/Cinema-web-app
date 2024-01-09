import React, {FC, useEffect, useState} from 'react';
import {App, Spin} from "antd";
import {IFilm} from "../../../types/model/IFilm";
import {useFetching} from "../../../hooks/useFetching";
import FilmService from "../../../api/FilmService";
import {useNavigate, useParams} from "react-router-dom";
import FilmForm from "../FilmCreateForm/FilmForm";

const FilmEditForm: FC = () => {
    const navigate = useNavigate();
    const {message} = App.useApp();
    const params = useParams();
    const [film, setFilm] = useState({} as IFilm);

    const [fetchFilm, isLoadingFetch, errorFetch] =  useFetching(async (id: string) => {
        const response = await FilmService.getById(id);
        setFilm(response.data)
    });

    useEffect(() => {
        fetchFilm(params.id);
    }, [params.id]);

    const [editFilm, isLoading, error] = useFetching(async (film: IFilm) => {
        const response = await FilmService.updateFilm(film);
        navigate('admin/films');
        message.success('Фильм обновлен под id: ' + response.data.id);
    })

    if (isLoading || isLoadingFetch) {
        return <Spin fullscreen/>
    }

    if (errorFetch !== '') {
        message.error(errorFetch)
    }

    if (error !== '') {
        message.error(error);
    }

    return (
        <FilmForm film={film} onSubmit={editFilm}/>
    );
};

export default FilmEditForm;