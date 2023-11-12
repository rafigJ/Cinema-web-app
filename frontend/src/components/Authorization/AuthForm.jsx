import React, {useContext, useState} from 'react';
import './Auth.css'
import {AuthContext} from "../../context";

const AuthForm = ({setModalActive}) => {
    const {setIsAuth} = useContext(AuthContext);
    const [isLoginForm, setIsLoginForm] = useState(true);

    const [loginEmail, setLoginEmail] = useState('');
    const [loginPassword, setLoginPassword] = useState('');

    const [regEmail, setRegEmail] = useState('');
    const [regPassword, setRegPassword] = useState('');
    const [name, setName] = useState('');
    const [lastName, setLastName] = useState('');

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
                    <button className="secondary-btn" onClick={deactivateLoginForm}>Зарегистрироваться</button>
                </form>
            </div>
        );
    }

    return (
        <div className="container">
            <h2>Создать аккаунт</h2>
            <form className="form">
                <input
                    value={name}
                    type="text"
                    placeholder="Имя"
                    onChange={e => setName(e.target.value)}
                />
                <input
                    value={lastName}
                    type="text"
                    placeholder="Фамилия"
                    onChange={e => setLastName(e.target.value)}
                />
                <input
                    value={regEmail}
                    type="email"
                    placeholder="Эл.почта"
                    onChange={e => setRegEmail(e.target.value)}
                />
                <input
                    value={regPassword}
                    type="password"
                    placeholder="Пароль"
                    onChange={e => setRegPassword(e.target.value)}
                />
                <button onClick={login}>Создать</button>
                <button className="secondary-btn" onClick={activateLoginForm}>Уже есть аккаунт</button>
            </form>
        </div>
    );
};

export default AuthForm;