import React, {FC, useState} from 'react';
import './Auth.css'
import LoginForm from "./LoginForm/LoginForm";
import RegistrationForm from "./RegistrationForm/RegistrationForm";

interface AuthFormProps {
    setModalActive: (item: boolean) => void;
}

const AuthForm: FC<AuthFormProps> = ({setModalActive}) => {
    const [isLoginForm, setIsLoginForm] = useState(true);

    const activateLoginForm = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
        setIsLoginForm(true);
    };
    const deactivateLoginForm = (event: React.MouseEvent<HTMLButtonElement>) => {
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