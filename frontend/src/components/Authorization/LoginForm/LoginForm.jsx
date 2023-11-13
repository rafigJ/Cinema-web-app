import React, {useContext, useState} from 'react';
import {AuthContext} from "../../../context";
import '../Auth.css'

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
        <div className="container">
            <h1>Вход</h1>
            <form className="form">
                <input value={loginEmail}
                       type="email"
                       placeholder="Эл.почта"
                       onChange={e => setLoginEmail(e.target.value)}
                />
                <input value={loginPassword}
                       type="password"
                       placeholder="Пароль"
                       onChange={e => setLoginPassword(e.target.value)}
                />
                <button onClick={login}>Войти</button>
                <button className="secondary-btn" onClick={deactivate}>Зарегистрироваться</button>
            </form>
        </div>
    );
};

export default LoginForm;