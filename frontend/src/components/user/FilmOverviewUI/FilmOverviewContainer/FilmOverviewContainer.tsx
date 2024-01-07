import React, {FC} from 'react';
import './FilmOverviewContainer.css'
import ticketIcon from './icons/ticket.svg';
import favIcon from './icons/add-to-favorites-icon.svg';
import AboutFilmSection from "../AboutFilmSection/AboutFilmSection";
import {IFilm} from "../../../../types/model/IFilm";
import Button from "../../../UI/Button/Button";

interface FilmOverviewContainerProps {
    film: IFilm;
}

/**
 * Нужен для страницы с фильмом (FilmOverviewPage) пользователя
 */
const FilmOverviewContainer: FC<FilmOverviewContainerProps> = ({film}) => {
    return (
        <div className="overview-container">
            <img className="overview-container__big-poster"
                 alt="Постер"
                 src={film.poster}
            />
            <div className="film-info">
                <h1 className="film-info__title">{film.name} ({film.year} г.)</h1>
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

                <AboutFilmSection film={film}/>
            </div>
        </div>
    );
};

export default FilmOverviewContainer;