import './styles/App.css';
import AppRouter from "./components/AppRouter";
import {BrowserRouter} from "react-router-dom";
import {useEffect, useState} from "react";
import {AuthContext} from "./context";
import {IUser} from "./types/model/IUser";
import {useFetching} from "./hooks/useFetching";
import AuthService from "./API/AuthService";

function App() {
    const [user, setUser] = useState<IUser>({} as IUser);
    const [isAuth, setIsAuth] = useState<boolean>(false)

    const [fetchUser] = useFetching(async () => {
        const response = await AuthService.userCredentials();
        setUser({...response.data});
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
            user,
            isAuth,
            setIsAuth,
            setUser
        }}>
            <BrowserRouter>
                <AppRouter/>
            </BrowserRouter>
        </AuthContext.Provider>
    );
}

export default App;
