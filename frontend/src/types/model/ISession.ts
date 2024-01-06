import {IHall} from "./IHall";

export interface ISession {
    id: number;
    filmId: number;
    filmName: string;
    hall: IHall;
    date: string;
    time: string;
    price: number;
}

