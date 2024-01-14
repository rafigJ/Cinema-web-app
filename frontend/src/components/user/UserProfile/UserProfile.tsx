import React, {FC, useContext, useEffect, useState} from 'react';
import {AuthContext} from "../../../context";
import {Button, Empty, Spin} from "antd";
import {useNavigate} from "react-router-dom";
import './UserProfile.css';
import {AuthResponse} from "../../../types/response/AuthResponse";
import {useFetching} from "../../../hooks/useFetching";
import {IUser} from "../../../types/model/IUser";
import UserService from "../../../api/UserService";

/**
 * Нужен для страницы пользователя
 * Необходим изменить дизайн данной страницы. Добавить отображение купленных билетов
 */
const UserProfile: FC = () => {
    const [user, setUser] = useState({} as IUser);
    const context = useContext(AuthContext);
    const navigate = useNavigate();

    const [fetchUser, isLoading, error] = useFetching(async () => {
        const response = await UserService.getUser();
        setUser(response.data);
    });

    useEffect(() => {
        fetchUser();
    }, [context.isAuth])

    if (!context.isAuth) {
        return <Empty description={<h3 style={{color: "whitesmoke"}}> Авторизуйтесь, для доступа к данным профиля </h3>}/>
    }

    if (isLoading) {
        return <Spin fullscreen/>
    }

    if (error !== '') {
        localStorage.removeItem('token');
        context.setAuthCredential({} as AuthResponse);
        context.setIsAuth(false);
        setUser({} as IUser);
        return <h1>{error}</h1>;
    }

    return (
        <div className="profile-container">
            <h1>Имя {user.name}</h1>
            <span className="profile-container__info-row">
                <h3 className="profile-container__text">Email: {user.email}</h3>
                <h3 className="profile-container__text">Роль: {context.authCredential.role}</h3>
                <h3 className="profile-container__text">Количество денег: {user.money}</h3>
            </span>

            <Button className="profile-container__exit-btn" type="primary" danger onClick={() => {
                localStorage.removeItem('token');
                context.setAuthCredential({} as AuthResponse);
                navigate('/');
                context.setIsAuth(false);
                setUser({} as IUser);
            }}>
                Выйти
            </Button>
        </div>
    );
};

export default UserProfile;