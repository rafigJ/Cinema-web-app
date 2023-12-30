import React, {FC} from 'react';
import './AboutFilmSection.css'
import {IFilm} from "../../../types/model/IFilm";

interface AboutFilmSectionProps {
    film: IFilm;
}

const AboutFilmSection: FC<AboutFilmSectionProps> = ({film = {} as IFilm}) => {
    return (
        <section className="film-detail">
            <h2 className="film-detail__title">О фильме</h2>
            <span className="film-detail__year">Год производства: {film.year}</span>

            <span className="film-genres">Жанр:{' '}
                <ul className="film-genres__list">
                    {film.genres?.map(genre =>
                        <li key={genre.id}
                            className="film-genres__list-item">
                            {genre.name}
                        </li>
                    )}
                </ul>
            </span>

            <span className="film-detail__description">{film.description}</span>
        </section>
    );
};

export default AboutFilmSection;