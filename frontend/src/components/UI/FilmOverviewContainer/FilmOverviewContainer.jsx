import React from 'react';
import './FilmOverviewContainer.css'
import Button from '../Button/Button';
import ticketIcon from './icons/ticket.svg';
import favIcon from './icons/add-to-favorites-icon.svg';
import AboutFilmSection from "../AboutFilmSection/AboutFilmSection";

const FilmOverviewContainer = ({name, year, poster, description, genreArray}) => {
    return (
        <div className="overview-container">
            <img className="overview-container__big-poster"
                 alt="Постер"
                 src={poster}
            />
            <div className="film-info">
                <h1 className="film-info__title">{name} ({year} г.)</h1>
                <div className="buttons-container">
                    <div className="ticket-btn">
                        <Button>
                            <img className="ticket-btn__icon" src={ticketIcon} alt='Расписание и билеты'/>
                            <span className="ticket-btn__text">Расписание и билеты</span>
                        </Button>
                    </div>
                    <div className="favorite-btn">
                        <Button isIconButton={true}>
                            <img className="favorite-btn__icon" src={favIcon} alt='Добавить в избранное'/>
                        </Button>
                    </div>
                </div>

                <AboutFilmSection year={year} description={description} genreArray={genreArray}/>
            </div>
        </div>
    );
};

export default FilmOverviewContainer;