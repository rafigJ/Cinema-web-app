import React, {FC, useEffect, useState} from 'react';
import './FilmGrid.css'
import FilmCard from "../FilmCard/FilmCard";
import {IFilm} from "../../../../types/model/IFilm";
import {useFetching} from "../../../../hooks/useFetching";
import FilmService from "../../../../api/FilmService";
import {Button, Input, Space, Spin} from "antd";
import {SearchOutlined} from "@ant-design/icons";
import GenreSelect from "../../../UI/GenreSelect/GenreSelect";
import CustomEmpty from "../../../UI/CustomEmpty/CustomEmpty";
import CustomPagination from "../../../UI/CustomPagination/CustomPagination";

/**
 * Нужен для страницы с фильмами (FilmsPage) пользователя
 * Отображает простую сетку из фильмов
 */
// todo перенести fetch сюда, из-за поиска и фильтрации фильмов
const FilmGrid: FC = () => {
    const limit = 20;

    const [page, setPage] = useState(0);
    const [total, setTotal] = useState(0);
    const [films, setFilms] = useState([] as IFilm[]);
    const [query, setQuery] = useState<string | null>(null);
    const [genres, setGenres] = useState<number[] | null>(null);

    const [fetchFilms, isLoading, isError, error] = useFetching(async () => {
        if ((query !== null && query.length > 3) || query === null || query.length === 0) {
            const response = await FilmService.getAllFilms(page, limit, null, query, genres);
            setFilms(response.data.content);
            setTotal(response.data.totalElements);
            if (query !== null) setPage(0);
        }
    })

    useEffect(() => {
        fetchFilms();
    }, [genres, page]);

    return (
        <>
            <div className="title-container">
                <h1 className="title-container__title">Фильмы {total}</h1>
                <div className="filter-wrapper">
                    <Space.Compact style={{width: '100%'}}>
                        <Input className="filter-wrapper__input" size="large"
                               placeholder="Название фильма или сериала..."
                               onChange={e => setQuery(e.target.value)}
                               onPressEnter={fetchFilms}
                        />
                        <Button size="large" type="primary" icon={<SearchOutlined/>}
                                onClick={fetchFilms}/>
                    </Space.Compact>
                    <GenreSelect maxTagCount='responsive' size="large"
                                 onChange={(value: number[]) => setGenres(value)}/>
                </div>
            </div>
            {isLoading ? <Spin/>  // если загрузка, то Spin
                :
                isError ? <p>{error}</p> // иначе если ошибка, то Error
                    :
                    (<>
                        <section className="film-grid">
                            {films.length ?
                                films.map(film =>
                                    <FilmCard
                                        key={film.id}
                                        film={film}
                                    />)
                                :
                                <CustomEmpty description="Фильмы не найдены!"/>
                            }
                        </section>
                        <div className="pagination-wrapper">
                            <CustomPagination
                                defaultCurrent={page + 1}
                                current={page + 1}
                                onChange={(page) => setPage(page - 1)}
                                total={total}
                                defaultPageSize={limit}
                                showSizeChanger={false}
                                showQuickJumper={false}/>
                        </div>
                    </>)
            }
        </>
    );
};

export default FilmGrid;