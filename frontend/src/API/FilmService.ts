import {AxiosResponse} from "axios";
import $api from "./index";
import {IPageResponse} from "../types/response/IPageResponse";
import {IFilm} from "../types/model/IFilm";

export default class FilmService {

    static async getByName(query: string): Promise<AxiosResponse<IPageResponse<IFilm>>> {
        return await $api.get('/films/search', {
                params: {name: query}
            }
        );
    }

    static async getAll(offset: number = 0, limit: number = 10): Promise<AxiosResponse<IPageResponse<IFilm>>> {
        return await $api.get("/films", {
                params: {
                    _page: offset,
                    _limit: limit
                }
            }
        );
    }

    static async getById(id: string): Promise<AxiosResponse<IFilm>> {
        return await $api.get<IFilm>(`/films/${id}`);
    }
};