import React from 'react';
import './FilmGrid.css'
import FilmItem from "../FilmItem/FilmItem";

const FilmGrid = ({title, filmArray = []}) => {
    return (
        <section className="section">
            <div className="title-container">
                <h1 className="title">{title}</h1>
            </div>
            <div className="film-grid">
                {filmArray.map(film =>
                    <FilmItem
                        key={film.id}
                        id={film.id}
                        name={film.name}
                        poster={film.poster}
                        genres={film.genres}
                        year={film.year}
                    />
                )}
            </div>
        </section>
    );
};

export default FilmGrid;