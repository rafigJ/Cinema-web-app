import {IFilm, IGenre} from "../../../types/model/IFilm";
import React, {Dispatch, SetStateAction} from "react";
import {useNavigate} from "react-router-dom";
import type {ColumnsType} from "antd/es/table";
import {App, Button, Table, Tag} from "antd";
import {DeleteOutlined, EditOutlined} from "@ant-design/icons";
import {useFetching} from "../../../hooks/useFetching";
import FilmService from "../../../api/FilmService";
import {IUser} from "../../../types/model/IUser";


interface UserTableProps {
    data: IUser[];
    setData: Dispatch<SetStateAction<IUser[]>>;
}

const UserTable: React.FC<UserTableProps> = ({data, setData}) => {
    // const navigate = useNavigate();
    // const columns: ColumnsType<IUser> = [
    //     {
    //         key: '1', title: 'ID', dataIndex: 'id',
    //     },
    //     {
    //         key: '2', title: 'Название', dataIndex: 'name',
    //     },
    //     {
    //         key: '3', title: 'Год', dataIndex: 'year',
    //     },
    //     {
    //         key: '4', title: 'Постер', dataIndex: 'poster',
    //         render: (poster: string) =>
    //             (<Button key={poster} type={"link"} target="_blank" href={poster}>Открыть</Button>)
    //     },
    //     {
    //         key: '5', title: 'Жанры', dataIndex: 'genres',
    //         render: (genres: IGenre[]) => (
    //             <span>
    //             {genres.map((genre) =>
    //                 <Tag key={genre.id} color="geekblue">
    //                     {genre.name}
    //                 </Tag>
    //             )}
    //             </span>
    //         )
    //     },
    //     {
    //         key: '6', title: 'Действия',
    //         render: (_, film: IFilm) => (
    //             <>
    //                 <EditOutlined onClick={() => navigate(`/admin/films/edit/${film.id}`)}/>
    //                 <DeleteOutlined onClick={() => onDelete(film)} style={{color: "red", marginLeft: "12px"}}/>
    //             </>
    //         )
    //     },
    // ];
    //
    // const {message} = App.useApp();
    //
    // const [remove, isLoading, error] = useFetching(async (film: IFilm) => {
    //     await FilmService.deleteFilm(film.id);
    //     message.success(`Фильм ${film.id} ${film.name} удалён!`)
    // });
    //
    // const onDelete = (film: IFilm) => {
    //     remove(film);
    //     setData((prev) => prev.filter(f => f.id !== film.id))
    // }
    //
    // if (error !== '') {
    //     message.success("Ошибка удаления")
    // }

    // return <Table loading={isLoading} rowKey={(user) => user.id} columns={columns} dataSource={data}/>;
    return <div></div>;
}


export default UserTable;