import React, {FC, useEffect, useState} from 'react';
import {useFetching} from "../../../hooks/useFetching";
import {Spin} from "antd";
import CrudTableLayout from "../CrudTableLayout/CrudTableLayout";
import UserTable from "../../../components/admin/UserTable/UserTable";
import {IUser} from "../../../types/model/IUser";
import AdminService from "../../../api/AdminService";

const CrudFilmsPage: FC = () => {
    const [data, setDataSource] = useState([] as IUser[])

    const [fetchUsers, isLoading] = useFetching(async () => {
        const response = await AdminService.getAllUsers(0, 10000);
        setDataSource(response.data.content)
    },)

    useEffect(() => {
        fetchUsers();
    }, []);

    if (isLoading) {
        return (<Spin fullscreen/>)
    }

    return (
        <CrudTableLayout title="Пользователи" crudTable={
            <>
                <UserTable data={data} setData={setDataSource}/>
            </>
        }/>
    );
};

export default CrudFilmsPage;