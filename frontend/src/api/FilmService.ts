import {AxiosResponse} from "axios";
import $api from "./index";
import {IPageResponse} from "../types/response/IPageResponse";
import {IFilm} from "../types/model/IFilm";
import {ISession} from "../types/model/ISession";

export default class FilmService {

    static async getAll(offset: number = 0, limit: number = 10,
                        sort: "ID" | "NAME" | "YEAR" | null = null): Promise<AxiosResponse<IPageResponse<IFilm>>> {
        return await $api.get("/films", {
                params: {
                    _page: offset,
                    _limit: limit,
                    sort: sort
                }
            }
        );
    }

    static async getById(id: string): Promise<AxiosResponse<IFilm>> {
        return await $api.get<IFilm>(`/films/${id}`);
    }

    static async getSessionsById(id: string, startDate: string, endDate = startDate): Promise<AxiosResponse<ISession[]>> {
        return await $api.get<ISession[]>(`/films/${id}/sessions`, {
            params: {
                start: startDate,
                end: endDate
            }
        })
    }

    static async createFilm(film: IFilm): Promise<AxiosResponse<IFilm>> {
        return await $api.post<IFilm>(`/films`, film)
    }

    static async updateFilm(film: IFilm): Promise<AxiosResponse<IFilm>> {
        return await $api.put<IFilm>(`/films/${film.id}`, film)
    }

    static async deleteFilm(id: number) {
        await $api.delete(`/films/${id}`)
    }
};