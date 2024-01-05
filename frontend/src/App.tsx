import './styles/App.css';
import AppRouter from "./components/AppRouter";
import {BrowserRouter} from "react-router-dom";
import {useEffect, useState} from "react";
import {AuthContext} from "./context";
import {IUser} from "./types/model/IUser";

function App() {
    const [user, setUser] = useState<IUser>({} as IUser);
    const [isAuth, setIsAuth] = useState<boolean>(false)


    useEffect(() => {
        const token = localStorage.getItem('token');
        if (token !== null) {

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
