import React, {useContext, useState} from 'react';
import '../Auth.css'
import {AuthContext} from "../../../context";
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
            <h2>Создать аккаунт</h2>
            <form className="auth-container__form">
                <input
                    value={name}
                    type="text"
                    placeholder="Имя"
                    onChange={e => setName(e.target.value)}
                />
                <input
                    value={email}
                    type="email"
                    placeholder="Эл.почта"
                    onChange={e => setEmail(e.target.value)}
                />
                <input
                    value={password}
                    type="password"
                    placeholder="Пароль"
                    onChange={e => setPassword(e.target.value)}
                />
                <button onClick={register}>Создать</button>
                <button className="secondary-btn" onClick={activate}>Уже есть аккаунт</button>
            </form>
        </div>
    );
};

export default RegistrationForm;