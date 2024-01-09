import './styles/App.css';
import AppRouter from "./components/routing/AppRouter";
import {BrowserRouter} from "react-router-dom";
import {useEffect, useState} from "react";
import {AuthContext} from "./context";
import {useFetching} from "./hooks/useFetching";
import AuthService from "./api/AuthService";
import dayjs from "dayjs";
import {AuthResponse} from "./types/response/AuthResponse";

function App() {
    dayjs.locale("ru")
    const [authCredential, setAuthCredential] = useState<AuthResponse>({} as AuthResponse);
    const [isAuth, setIsAuth] = useState<boolean>(false)

    const [fetchUser] = useFetching(async () => {
        const response = await AuthService.userCredentials();
        setAuthCredential({...response.data});
        setIsAuth(true);
    })

    useEffect(() => {
        const token = localStorage.getItem('token');
        if (token !== null) {
            fetchUser();
        }
    }, []);

    return (
        <AuthContext.Provider value={{
            authCredential,
            isAuth,
            setIsAuth,
            setAuthCredential
        }}>
            <BrowserRouter>
                <AppRouter/>
            </BrowserRouter>
        </AuthContext.Provider>
    );
}

export default App;
