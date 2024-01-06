import React, {FC} from 'react';
import './FilmGrid.css'
import FilmGridItem from "../FilmGridItem/FilmGridItem";
import {IFilm} from "../../../../../types/model/IFilm";

interface FilmGridProps {
    title: string;
    films: IFilm[];
}

const FilmGrid:FC<FilmGridProps> = ({title, films = [] as IFilm[]}) => {
    return (
        <>
            <div className="title-container">
                <h1 className="title-container__title">{title} {films.length}</h1>
            </div>
            <section className="film-grid">
                {films.map(film =>
                    <FilmGridItem
                        key={film.id}
                        film={film}
                    />
                )}
            </section>
        </>
    );
};

export default FilmGrid;