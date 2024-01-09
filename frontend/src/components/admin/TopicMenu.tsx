import React, {FC} from "react";
import {Menu} from "antd";
import type {MenuProps} from 'antd/es/menu';
import {FundOutlined, TeamOutlined, UnorderedListOutlined} from "@ant-design/icons";
import {useNavigate} from "react-router-dom";

type MenuItem = Required<MenuProps>['items'][number];

function getItem(
    label: React.ReactNode,
    key?: React.Key | null,
    icon?: React.ReactNode,
    danger = false,
    disabled = false
): MenuItem {
    return {
        key,
        icon,
        label,
        danger,
        disabled,
    } as MenuItem;
}

const items: MenuItem[] = [
    getItem('Cinema Now', '1', null, false, true),
    getItem('Статистика', '/admin/statistics', <FundOutlined/>),
    getItem('Пользователи', '/admin/users', <TeamOutlined/>),
    getItem('Фильмы', '/admin/films', <UnorderedListOutlined/>),
    getItem('Сеансы', '/admin/sessions', <UnorderedListOutlined/>),
    getItem('Билеты', '/admin/tickets', <UnorderedListOutlined/>),
    getItem("Выйти", '/', <UnorderedListOutlined/>, true)
];

const TopicMenu: FC = () => {
    const navigate = useNavigate()
    return (
        <Menu
            onClick={(key) => {
                navigate(key.key);
            }}
            items={items}
            theme="dark"
            mode="inline"/>
    )
};

export default TopicMenu;