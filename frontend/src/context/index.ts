import {createContext, Dispatch, SetStateAction} from "react";
import {IUser} from "../types/model/IUser";

interface AuthContextProps {
    user: IUser;
    isAuth: boolean;
    setIsAuth: Dispatch<SetStateAction<boolean>>;
    setUser: Dispatch<SetStateAction<IUser>>;
}

export const AuthContext = createContext<AuthContextProps>({} as AuthContextProps);
