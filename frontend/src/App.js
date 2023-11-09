import './styles/App.css';
import Navbar from "./components/UI/Navbar/Navbar";
import AppRouter from "./components/AppRouter";
import HomePage from "./pages/HomePage";
import {BrowserRouter} from "react-router-dom";

function App() {
    return (
        <BrowserRouter>
            <Navbar/>
            <AppRouter/>
            <HomePage/>
        </BrowserRouter>
    );
}

export default App;
