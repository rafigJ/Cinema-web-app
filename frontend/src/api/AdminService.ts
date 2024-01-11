import {AxiosResponse} from "axios";
import $api from "./index";
import {IUser} from "../types/model/IUser";
import {IPageResponse} from "../types/response/IPageResponse";
import {ITicket} from "../types/model/ITicket";

export default class AdminService {

    static async getAllUsers(offset: number = 0, limit: number = 10): Promise<AxiosResponse<IPageResponse<IUser>>> {
        return await $api.get<IPageResponse<IUser>>('/admin/users', {
            params: {
                _page: offset,
                _limit: limit
            }
        });
    }

    static async updateUserRole(uuid: string, role: 'USER' | 'ADMIN'): Promise<AxiosResponse<IUser>> {
        return await $api.patch<IUser>(`/admin/users/${uuid}`, {
            role: role,
        });
    }

    static async getTickets(offset: number = 0, limit: number = 10): Promise<AxiosResponse<IPageResponse<ITicket>>> {
        return await $api.get<IPageResponse<ITicket>>('/admin/tickets', {
            params: {
                _page: offset,
                _limit: limit
            }
        });
    }

    static async getProfitByPeriod(startDate: string, endDate: string): Promise<AxiosResponse<{profit: number}>> {
        return await $api.get('/admin/tickets/profit', {
            params: {
                start: startDate,
                end: endDate
            }
        })
    }
};