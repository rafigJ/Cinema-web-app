import React, {FC} from "react";
import {Menu} from "antd";
import type {MenuProps} from 'antd/es/menu';
import {FundOutlined, TeamOutlined, UnorderedListOutlined} from "@ant-design/icons";

type MenuItem = Required<MenuProps>['items'][number];

function getItem(
    label: React.ReactNode,
    key?: React.Key | null,
    icon?: React.ReactNode,
    children?: MenuItem[],
): MenuItem {
    return {
        key,
        icon,
        children,
        label,
    } as MenuItem;
}

const items: MenuItem[] = [
    getItem('Статистика', '1', <FundOutlined/>),
    getItem('Пользователи', '2', <TeamOutlined/>),
    getItem('Фильмы', '3', <UnorderedListOutlined/>),
    getItem('Сеансы', '4', <UnorderedListOutlined/>),
    getItem('Билеты', '5', <UnorderedListOutlined/>),
    getItem("Выйти", '6', <UnorderedListOutlined/>)
];

const TopicMenu: FC = () => (
    <Menu items={items} theme="dark" mode="inline"/>
);

export default TopicMenu;