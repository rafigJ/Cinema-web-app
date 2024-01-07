import React, {FC, useEffect, useState} from 'react';
import {IFilm} from "../../../types/model/IFilm";
import {useFetching} from "../../../hooks/useFetching";
import FilmService from "../../../API/FilmService";
import {Spin} from "antd";
import CrudTableLayout from "../CrudTableLayout/CrudTableLayout";
import FilmTable from "../../../components/admin/FilmTable/FilmTable";

const CrudFilmsPage: FC = () => {
    const [data, setDataSource] = useState([] as IFilm[])

    const [fetchFilms, isLoading] = useFetching(async () => {
        const response = await FilmService.getAll(0, 10000);
        setDataSource(response.data.content)
    },)

    useEffect(() => {
        fetchFilms();
    }, []);

    if (isLoading) {
        return (<Spin fullscreen/>)
    }

    return (
        <CrudTableLayout title="Фильмы" crudTable={
            <FilmTable data={data}/>
        }/>
    );
};

export default CrudFilmsPage;