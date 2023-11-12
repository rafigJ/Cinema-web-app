import React, {useContext, useState} from 'react';
import './Auth.css'
import {AuthContext} from "../../context";

const AuthForm = ({setModalActive}) => {
    const {setIsAuth} = useContext(AuthContext);
    const [isLoginForm, setIsLoginForm] = useState(true);

    const login = event => {
        event.preventDefault();
        setIsAuth(true);
        setModalActive(false);
        localStorage.setItem('auth', "true");
    };

    const activateLoginForm = event => {
        event.preventDefault();
        setIsLoginForm(true);
    };
    const deactivateLoginForm = event => {
        event.preventDefault();
        setIsLoginForm(false);
    };

    if (isLoginForm) {
        return (
            <div className="container">
                <h1>Вход</h1>
                <form className="form">
                    <input type="email" placeholder="Эл.почта"/>
                    <input type="password" placeholder="Пароль"/>
                    <button onClick={login}>Войти</button>
                    <button className="secondary-btn" onClick={deactivateLoginForm}>Зарегистрироваться</button>
                </form>
            </div>
        );
    }

    return (
        <div className="container">
            <h2>Создать аккаунт</h2>
            <form className="form">
                <input type="text" placeholder="Имя"/>
                <input type="text" placeholder="Фамилия"/>
                <input type="email" placeholder="Эл.почта"/>
                <input type="password" placeholder="Пароль"/>
                <button onClick={login}>Создать</button>
                <button className="secondary-btn" onClick={activateLoginForm}>Уже есть аккаунт</button>
            </form>
        </div>
    );
};

export default AuthForm;