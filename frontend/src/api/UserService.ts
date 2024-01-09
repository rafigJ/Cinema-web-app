import {AxiosResponse} from "axios";
import $api from "./index";
import {IUser} from "../types/model/IUser";

export default class UserService {

    static async getUser(): Promise<AxiosResponse<IUser>> {
        return await $api.get<IUser>('/user');
    }

};