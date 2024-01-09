import React, {FC, useEffect, useState} from 'react';
import {IFilm} from "../../../types/model/IFilm";
import {useFetching} from "../../../hooks/useFetching";
import FilmService from "../../../api/FilmService";
import {Button, Spin} from "antd";
import CrudTableLayout from "../CrudTableLayout/CrudTableLayout";
import FilmTable from "../../../components/admin/FilmTable/FilmTable";
import {useNavigate} from "react-router-dom";

const CrudFilmsPage: FC = () => {
    const [data, setDataSource] = useState([] as IFilm[])
    const navigate = useNavigate();

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
            <>
                <Button onClick={() => navigate('/admin/films/create')} style={{marginBottom: "5px"}}>Добавить фильм</Button>
                <FilmTable data={data} setData={setDataSource}/>
            </>
        }/>
    );
};

export default CrudFilmsPage;