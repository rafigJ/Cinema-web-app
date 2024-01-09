import React, {FC} from 'react';
import {App, Spin} from "antd";
import {allGenres} from "../../../helpers/genres";
import {IFilm} from "../../../types/model/IFilm";
import {useFetching} from "../../../hooks/useFetching";
import FilmService from "../../../api/FilmService";
import {useNavigate} from "react-router-dom";
import FilmForm from "./FilmForm";


const FilmCreateForm: FC = () => {
    const navigate = useNavigate();
    const {message} = App.useApp();

    const [createFilm, isLoading, error] = useFetching(async (film: IFilm) => {
        const response = await FilmService.createFilm(film);
        navigate('admin/films');
        message.success('Фильм сохранен под id: ' + response.data.id);
    })

    if (isLoading) {
        return <Spin fullscreen/>
    }

    if (error !== '') {
        message.error(error);
    }

    return (
        <FilmForm onSubmit={createFilm}/>
    );
};

export default FilmCreateForm;