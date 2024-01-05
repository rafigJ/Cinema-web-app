import React, {FC, useContext, useState} from 'react';
import {AuthContext} from "../../../context";
import '../Auth.css'
import Button from "../../UI/Button/Button";
import {useFetching} from "../../../hooks/useFetching";
import AuthService from "../../../API/AuthService";

interface AuthFormProps {
    deactivate: (event: React.MouseEvent<HTMLButtonElement>) => void;
    setModalActive: (item: boolean) => void;
}

const LoginForm: FC<AuthFormProps> = ({deactivate, setModalActive}) => {
    const {setUser, setIsAuth} = useContext(AuthContext);

    const [email, setEmail] = useState('test@gmail.com');
    const [password, setPassword] = useState('password1');

    const [login, isLoading, error] = useFetching(async () => {
        const response = await AuthService.login(email, password);
        setUser({...response.data});
        localStorage.setItem('token', response.data.token);
        setIsAuth(true);
        setModalActive(false);
    });

    const loginEvent = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
        login();
    };

    return (
        <div className="auth-container">
            <h1 className="auth-container__title">Вход</h1>
            {error !== '' &&
                <h4 className="auth-container__big-error">Ошибка авторизации </h4>
            }
            <form className="auth-container__form">
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
                    {isLoading ?
                        <div>Загрузка...</div>
                        :
                        <Button onClick={loginEvent}>Войти</Button>
                    }
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