import './styles/App.css';
import Navbar from "./components/UI/Navbar/Navbar";
import AppRouter from "./components/AppRouter";
import {BrowserRouter} from "react-router-dom";
import {useState} from "react";
import {AuthContext} from "./context";

function App() {
    const [isAuth, setIsAuth] = useState(false)

    return (
        <AuthContext.Provider value={{
            isAuth,
            setIsAuth
        }}>
            <BrowserRouter>
                <Navbar/>
                <AppRouter/>
            </BrowserRouter>
        </AuthContext.Provider>
    );
}

export default App;