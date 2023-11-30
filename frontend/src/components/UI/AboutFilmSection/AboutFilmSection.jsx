import React from 'react';
import './AboutFilmSection.css'

const AboutFilmSection = ({year, description, genreArray=[]}) => {
    return (
        <section className="film-detail">
            <h2 className="film-detail__title">О фильме</h2>
            <span className="film-detail__year">Год производства: {year}</span>

            <span className="film-genres">Жанр:{' '}
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
    );
};

export default AboutFilmSection;