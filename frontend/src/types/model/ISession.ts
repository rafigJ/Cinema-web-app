import {IHall} from "./IHall";
import {IFilm} from "./IFilm";

export interface ISession {
    id: number;
    filmId: number;
    hallId: number
    film: IFilm;
    hall: IHall;
    date: string;
    time: string;
    price: number;
}

