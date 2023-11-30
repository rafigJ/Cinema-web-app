import React from 'react';
import './FilmDetailsSection.css'

const FilmDetailsSection = ({id, name, year, poster, description, genreArray}) => {
    return (
        <section className="overview-section">
            <img className="overview-section__big-poster"
                 alt="Постер"
                 src={poster}
            />
            <div className="film-info">
                <h1 className="film-info__title">{name}</h1>
                <div className="film-info__buttons"></div>

                <section className="film-detail">
                    <h2 className="film-detail__title">О фильме</h2>
                    <span className="film-detail__year">Год производства: {year}</span>

                    <span className="film-genres"> Жанры:
                        <ul className="film-genres__list">
                            {genreArray.map(genre =>
                                <li key={genre.id}
                                    className="film-genres__list-item">
                                    {genre.name}
                                </li>
                            )}
                        </ul>
                    </span>

                    <span className="film-detail__description">{description}</span>
                </section>
            </div>
        </section>
    );
};

export default FilmDetailsSection;