import {IUser} from "../model/IUser";

export interface AuthResponse {
    user: IUser;
    token: string;
}
