import React, {FC, useContext} from 'react';
import {AuthContext} from "../../../context";
import {Button, Empty} from "antd";
import {useNavigate} from "react-router-dom";
import './UserProfile.css';
import {IUser} from "../../../types/model/IUser";

const UserProfile: FC = () => {
    const context = useContext(AuthContext);
    const navigate = useNavigate();
    if (!context.isAuth) {
        return <Empty description={<span> Авторизуйтесь, для доступа к данным профиля </span>}/>
    }

    const user = context.user;

    return (
        <div className="profile-container">
            <h1>Имя {user.name}</h1>
            <span className="profile-container__info-row">
                <h3 className="profile-container__text">Email: {user.email}</h3>
                <h3 className="profile-container__text">Роль: {user.role}</h3>
            </span>

            <Button className="profile-container__exit-btn" type="primary" danger onClick={() => {
                localStorage.removeItem('token');
                context.setUser({} as IUser);
                navigate('/');
                context.setIsAuth(false);
            }}>
                Выйти
            </Button>
        </div>
    );
};

export default UserProfile;