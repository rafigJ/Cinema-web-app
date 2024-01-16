import React, {FC, useState} from 'react';
import './FilmCard.css'
import {useNavigate} from "react-router-dom";
import {IFilm} from "../../../../types/model/IFilm";

interface FilmCardProps {
    film: IFilm;
}

const FilmCard: FC<FilmCardProps> = ({film}) => {
    const [isActive, setIsActive] = useState(false)
    const keyGeneration = (filmId: number, genreId: number) => filmId.toString() + genreId.toString()
    const navigate = useNavigate();

    return (
        <article className="film-container"
                 onMouseEnter={() => setIsActive(true)}
                 onMouseLeave={() => setIsActive(false)}
                 onMouseDown={() => navigate(`/films/${film.id}`)}
        >
            <img className={isActive ?
                "film-container__poster film-container__poster_active" :
                "film-container__poster"} alt="Постер" src={film.poster}/>
            <div className={
                isActive ? "detail-box" : "detail-box detail-box_hidden"
            }>
                <h4>{film.name}, {film.year}</h4>
                <ul className="detail-box__genre-list">
                    {film.genres.map(genre =>
                        <li className="detail-box__genre-item" key={keyGeneration(film.id, genre.id)}>{genre.name}</li>
                    )}
                </ul>
            </div>
        </article>
    );
};

export default FilmCard;