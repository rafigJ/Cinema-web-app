import React from 'react';
import './Auth.css'

const LoginForm = () => {
    // const {isAuth, setIsAuth} = useContext(AuthContext);

    // const login = event => {
    //     event.preventDefault();
    //     // setIsAuth(true);
    //     // localStorage.setItem('auth', "true");
    // };
    return (
        <div className="box">
            <h1>Вход</h1>
            <form className="form">
                <input type="email" placeholder="Эл.почта"/>
                <input type="password" placeholder="Пароль"/>
                <button>Войти</button>
            </form>
        </div>
    );
};

export default LoginForm;