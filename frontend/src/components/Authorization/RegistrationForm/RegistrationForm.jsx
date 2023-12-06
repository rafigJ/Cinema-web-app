import React, {useContext, useState} from 'react';
import '../Auth.css'
import {AuthContext} from "../../../context";
import Button from "../../UI/Button/Button";

const RegistrationForm = ({activate, setModalActive}) => {
    const {setIsAuth} = useContext(AuthContext);

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [name, setName] = useState('');

    const register = event => {
        event.preventDefault();
        setIsAuth(true);
        setModalActive(false);
        localStorage.setItem('auth', "true");
    }

    return (
        <div className="auth-container">
            <h1 className="auth-container__title">Создать аккаунт</h1>
            <form className="auth-container__form">
                <input className="auth-container__input"
                       value={name}
                       type="text"
                       placeholder="Имя"
                       onChange={e => setName(e.target.value)}
                />
                <input className="auth-container__input"
                       value={email}
                       type="email"
                       placeholder="Эл.почта"
                       onChange={e => setEmail(e.target.value)}
                />
                <input className="auth-container__input"
                       value={password}
                       type="password"
                       placeholder="Пароль"
                       onChange={e => setPassword(e.target.value)}
                />
                <div className="auth-container__button">
                    <Button onClick={register}>Создать</Button>
                </div>
            </form>
            <span className="auth-container__change">
                    Уже есть аккаунт?
                    <strong className="auth-container__change-hyperlink"
                            onClick={activate}>Войти</strong>
            </span>
        </div>
    );
};

export default RegistrationForm;