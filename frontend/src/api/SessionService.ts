import {AxiosResponse} from "axios";
import $api from "./index";
import {IPageResponse} from "../types/response/IPageResponse";
import {ISession} from "../types/model/ISession";

export default class SessionService {

    static async getAll(offset: number = 0, limit: number = 10,
                        startDate: string, endDate = startDate): Promise<AxiosResponse<IPageResponse<ISession>>> {
        return await $api.get("/sessions", {
                params: {
                    _page: offset,
                    _limit: limit,
                    start: startDate,
                    end: endDate
                }
            }
        );
    }

    static async getById(id: string): Promise<AxiosResponse<ISession>> {
        return await $api.get<ISession>(`/sessions/${id}`);
    }
};