import {createContext, Dispatch, SetStateAction} from "react";
import {AuthResponse} from "../types/response/AuthResponse";

interface AuthContextProps {
    authCredential: AuthResponse;
    isAuth: boolean;
    setIsAuth: Dispatch<SetStateAction<boolean>>;
    setAuthCredential: Dispatch<SetStateAction<AuthResponse>>;
}

export const AuthContext = createContext<AuthContextProps>({} as AuthContextProps);
