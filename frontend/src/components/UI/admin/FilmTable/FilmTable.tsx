import React from 'react';
import {Button, Table, Tag} from 'antd';
import type {ColumnsType} from 'antd/es/table';
import {IFilm, IGenre} from "../../../../types/model/IFilm";

const columns: ColumnsType<IFilm> = [
    {
        key: '1',
        title: 'ID',
        dataIndex: 'id',
    },
    {
        key: '2',
        title: 'Название',
        dataIndex: 'name',
    },
    {
        key: '3',
        title: 'Год',
        dataIndex: 'year',
    },
    {
        key: '4',
        title: 'Постер',
        dataIndex: 'poster',
        render: (poster: string) =>
            (<Button key={poster} type={"link"} target="_blank" href={poster}>Открыть</Button>)
    },
    {
        key: '5',
        title: 'Жанры',
        dataIndex: 'genres',
        render: (genres: IGenre[]) => (
            <span>
                {genres.map((genre) =>
                    <Tag key={genre.id} color="geekblue">
                        {genre.name}
                    </Tag>
                )}
            </span>
        )
    }
];


interface DemoProps {
    data: IFilm[];
}

const FilmTable: React.FC<DemoProps> = ({data}) =>{
    return <Table<IFilm> rowKey={(film) => film.id} columns={columns} dataSource={data}/>
}


export default FilmTable;