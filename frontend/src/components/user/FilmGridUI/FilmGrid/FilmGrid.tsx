import React, {FC, useEffect, useState} from 'react';
import './FilmGrid.css'
import FilmCard from "../FilmCard/FilmCard";
import {IFilm} from "../../../../types/model/IFilm";
import {useFetching} from "../../../../hooks/useFetching";
import FilmService from "../../../../api/FilmService";
import {Button, SelectProps, Spin, Space, Input} from "antd";
import {SearchOutlined} from "@ant-design/icons";
import {allGenres} from "../../../../helpers/genres";
import GenreSelect from "../../../UI/GenreSelect/GenreSelect";

/**
 * Нужен для страницы с фильмами (FilmsPage) пользователя
 * Отображает простую сетку из фильмов
 */
// todo перенести fetch сюда, из-за поиска и фильтрации фильмов
const FilmGrid: FC = () => {
    const limit = 20;

    const [page, setPage] = useState(0);
    const [films, setFilms] = useState([] as IFilm[]);
    const [searchQuery, setSearchQuery] = useState<string | null>(null);
    const [genres, setGenres] = useState<number[] | null>(null);

    const [fetchFilms, isLoading, error] = useFetching(async () => {
        if (searchQuery === null && genres === null) {
            const response = await FilmService.getAll(page, limit);
            setFilms(response.data.content);
        }
    },)


    useEffect(() => {
        fetchFilms();
    }, []);

    if (isLoading) {
        return <Spin fullscreen/>
    }

    if (error !== '') {
        return <p>{error}</p>
    }

    return (
        <>
            <div className="title-container">
                <h1 className="title-container__title">Фильмы {films.length}</h1>
                <div className="search-wrapper">
                    <Space.Compact style={{width: '100%'}}>
                        <Input className="search-wrapper__input" size="large"
                               placeholder="Название фильма или сериала..."
                               onChange={e => setSearchQuery(e.target.value)}/>
                        <Button size="large" type="primary" icon={<SearchOutlined/>}/>
                    </Space.Compact>
                    <GenreSelect maxTagCount='responsive' size="large" onChange={(value: number[]) => setGenres(value)}/>
                </div>
            </div>
            <section className="film-grid">
                {films.map(film =>
                    <FilmCard
                        key={film.id}
                        film={film}
                    />
                )}
            </section>
        </>
    );
};

export default FilmGrid;