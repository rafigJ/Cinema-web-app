import React, {Dispatch, SetStateAction, useContext} from "react";
import type {ColumnsType} from "antd/es/table";
import {App, Select, SelectProps, Table} from "antd";
import {useFetching} from "../../../hooks/useFetching";
import {IUser} from "../../../types/model/IUser";
import {AuthContext} from "../../../context";
import AdminService from "../../../api/AdminService";
import dayjs from "dayjs";

const roleOptions: SelectProps['options'] = [
    {
        label: 'Админ',
        value: 'ADMIN',
    },
    {
        label: 'Пользователь',
        value: 'USER',
    }];

interface UserTableProps {
    data: IUser[];
    setData: Dispatch<SetStateAction<IUser[]>>;
}

const UserTable: React.FC<UserTableProps> = ({data, setData}) => {
    const {authCredential} = useContext(AuthContext);

    const columns: ColumnsType<IUser> = [
        {
            key: '1', title: 'UUID', dataIndex: 'uuid',
        },
        {
            key: '2', title: 'Имя пользователя', dataIndex: 'name',
        },
        {
            key: '3', title: 'E-mail', dataIndex: 'email',
        },
        {
            key: '4', title: 'Баланс', dataIndex: 'money',
        },
        {
            key: '5', title: 'Роль', dataIndex: 'role',
            render: (role: string, record: IUser) =>
                <Select
                    disabled={authCredential.email === record.email}
                    defaultValue={role}
                    options={roleOptions}
                    onChange={(value: string) => onChange(record, value)}
                />
        },
        {
            key: '6', title: 'Время создания', dataIndex: 'createTime'
        },
        {
            key: '7', title: 'Время обновления', dataIndex: 'updateTime'
        }
    ];

    const {message} = App.useApp();

    const [changeRole, isLoading, isError, error] = useFetching(async (user: IUser, role: "ADMIN" | "USER") => {
        await AdminService.updateUserRole(user.uuid, role);
        message.success(`Пользователь ${user.email} ${user.name} поменял роль на ${role}!`);
        setData((prev: IUser[]) => {
                // Обновляем "Время обновления" пользователя, в случае успеха. Чтобы лишний раз не идти в базу.
                prev[prev.indexOf(user)] = {...user, updateTime: dayjs().format("YYYY-MM-DD HH:mm")};
                return prev
            }
        )
    });

    const onChange = (user: IUser, role: string) => {
        changeRole(user, role);
    }

    if (isError) {
        message.error("Ошибка обновления роли " + error)
    }

    return <Table loading={isLoading} rowKey={(user) => user.uuid} columns={columns} dataSource={data}/>;
}


export default UserTable;