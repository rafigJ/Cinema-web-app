import React, {useContext, useState} from 'react';
import {AuthContext} from "../../../context";
import '../Auth.css'
import Button from "../../UI/Button/Button";

const LoginForm = ({deactivate, setModalActive}) => {
    const {setIsAuth} = useContext(AuthContext);

    const [loginEmail, setLoginEmail] = useState('');
    const [loginPassword, setLoginPassword] = useState('');

    const login = event => {
        event.preventDefault();
        setIsAuth(true);
        setModalActive(false);
        localStorage.setItem('auth', "true");
    };

    return (
        <div className="auth-container">
            <h1 className="auth-container__title">Вход</h1>
            <form className="auth-container__form">
                <input className="auth-container__input"
                       value={loginEmail}
                       type="email"
                       placeholder="Эл.почта"
                       onChange={e => setLoginEmail(e.target.value)}
                />
                <input className="auth-container__input"
                       value={loginPassword}
                       type="password"
                       placeholder="Пароль"
                       onChange={e => setLoginPassword(e.target.value)}
                />
                <div className="auth-container__button">
                    <Button onClick={login}>Войти</Button>
                </div>
            </form>
            <span className="auth-container__change">
                    Не зарегистрированы?
                    <strong className="auth-container__change-hyperlink"
                            onClick={deactivate}>Зарегистрироваться</strong>
            </span>
        </div>
    );
};

export default LoginForm;