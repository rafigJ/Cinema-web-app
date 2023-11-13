import React, {useState} from 'react';
import './Auth.css'
import LoginForm from "./LoginForm/LoginForm";
import RegistrationForm from "./RegistrationForm/RegistrationForm";

const AuthForm = ({setModalActive}) => {
    const [isLoginForm, setIsLoginForm] = useState(true);

    const activateLoginForm = event => {
        event.preventDefault();
        setIsLoginForm(true);
    };
    const deactivateLoginForm = event => {
        event.preventDefault();
        setIsLoginForm(false);
    };


    return (
        isLoginForm ?
            <LoginForm setModalActive={setModalActive} deactivate={deactivateLoginForm} />
            :
            <RegistrationForm setModalActive={setModalActive} activate={activateLoginForm} />
    );
};

export default AuthForm;