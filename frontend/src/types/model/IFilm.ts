export interface IFilm {
    id: number;
    name: string;
    year: number;
    poster: string;
    description: string;
    genres: IGenre[];
}

export interface IGenre {
    id: number;
    name?: string;
}