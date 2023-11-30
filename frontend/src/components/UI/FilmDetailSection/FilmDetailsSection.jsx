import React from 'react';
import './FilmDetailsSection.css'
import Button from '../Button/Button';
import ticketIcon from './icons/ticket.svg';
import favIcon from './icons/add-to-favorites-icon.svg';

const FilmDetailsSection = ({id, name, year, poster, description, genreArray}) => {
    return (
        <section className="overview-section">
            <img className="overview-section__big-poster"
                 alt="Постер"
                 src={poster}
            />
            <div className="film-info">
                <h1 className="film-info__title">{name} ({year} г.)</h1>
                <div className="film-info__buttons">
                    <Button>
                        <img className="film-info__ticket-icon" src={ticketIcon} alt='Расписание и билеты'/>
                        <span className="film-info__ticket-text">Расписание и билеты</span>
                    </Button>
                    <Button>
                        <img className="film-info__favorite-icon" src={favIcon} alt='Добавить в избранное'/>
                    </Button>
                </div>

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
            </div>
        </section>
    );
};

export default FilmDetailsSection;