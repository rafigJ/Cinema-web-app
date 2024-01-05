import {AxiosResponse} from "axios";
import $api from "./index";
import {AuthResponse} from "../types/response/AuthResponse";

export default class AuthService {

    static async login(email: string, password: string): Promise<AxiosResponse<AuthResponse>> {
        return await $api.post<AuthResponse>('/auth/login', {email, password});
    }

    static async register(name: string, email: string, password: string): Promise<AxiosResponse<AuthResponse>> {
        return await $api.post<AuthResponse>("/auth/register", {name, email, password});
    }

    static async userCredentials(): Promise<AxiosResponse<AuthResponse>> {
        return await $api.get("/auth");
    }
};