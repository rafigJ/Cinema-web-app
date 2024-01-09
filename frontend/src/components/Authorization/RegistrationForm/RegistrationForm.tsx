import React, {FC, useContext, useState} from 'react';
import '../Auth.css'
import {AuthContext} from "../../../context";
import Button from "../../UI/Button/Button";
import {useFetching} from "../../../hooks/useFetching";
import AuthService from "../../../api/AuthService";

interface RegistrationFormProps {
    activate: (event: React.MouseEvent<HTMLButtonElement>) => void;
    setModalActive: (item: boolean) => void;
}

const RegistrationForm: FC<RegistrationFormProps> = ({activate, setModalActive}) => {
    const {setAuthCredential, setIsAuth} = useContext(AuthContext);

    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const [register, isLoading, error] = useFetching(async () => {
        const response = await AuthService.register(name, email, password);
        setAuthCredential(response.data);
        localStorage.setItem('token', response.data.token);
        setIsAuth(true);
        setModalActive(false);
    });

    const registerEvent = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
        register();
    }

    return (
        <div className="auth-container">
            <h1 className="auth-container__title">Создать аккаунт</h1>
            {error !== '' &&
                <h4 className="auth-container__big-error">Ошибка регистрации </h4>
            }
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
                    {isLoading ?
                        <div>Загрузка...</div>
                        :
                        <Button onClick={registerEvent}>Создать</Button>
                    }
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