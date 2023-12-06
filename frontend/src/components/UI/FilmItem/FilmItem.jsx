import React, {useState} from 'react';
import './FilmItem.css'
import {useNavigate} from "react-router-dom";

const FilmItem = ({id, name, year, poster, genres}) => {
    const [isActive, setIsActive] = useState(false)
    const keyGeneration = (filmId, genreId) => filmId.toString() + genreId.toString()
    const navigate = useNavigate();

    return (
        <article className="film-container"
                 onMouseEnter={() => setIsActive(true)}
                 onMouseLeave={() => setIsActive(false)}
                 onMouseDown={() => navigate(`/films/${id}`)}
        >
            <img className={isActive ?
                "film-container__poster film-container__poster_active" :
                "film-container__poster"} alt="Постер" src={poster}/>
            <div className={
                isActive ? "detail-box" : "detail-box detail-box_hidden"
            }>
                <h4>{name}, {year}</h4>
                <ul className="detail-box__genre-list">
                    {genres.map(genre =>
                        <li className="detail-box__genre-item" key={keyGeneration(id, genre.id)}>{genre.name}</li>
                    )}
                </ul>
            </div>
        </article>
    );
};

export default FilmItem;