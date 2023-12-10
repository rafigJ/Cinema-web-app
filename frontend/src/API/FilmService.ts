import {AxiosResponse} from "axios";
import $api from "./index";
import {IFilm, IPageResponse} from "../types/types";

export default class FilmService {
    private static url = "http://192.168.1.110:8080"

    static async getByName(query: string): Promise<AxiosResponse<IFilm[]>> {
        return await $api.get<IFilm[]>('/films/search', {
                params: {query: query},
                headers: {
                    'Content-Type': 'application/json',
                },
            }
        );
    }

    static async getAll(offset: number = 0, limit: number = 10): Promise<AxiosResponse<IPageResponse<IFilm>>> {
        let axiosResponse = await $api.get("/films", {
                params: {
                    offset: offset,
                    limit: limit
                }
            }
        );
        return axiosResponse;
    }

    static async getById(id: string): Promise<AxiosResponse<IFilm>> {
        return await $api.get<IFilm>(`/films/${id}`);
    }
};