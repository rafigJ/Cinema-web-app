import React, {FC, useState} from 'react';
import {App, Button, DatePicker, Flex, Form, Image, Input, Select, SelectProps, Spin} from "antd";
import {allGenres} from "../../../helpers/genres";
import {fallbackImage} from "./const/fallbackImage";
import {IFilm} from "../../../types/model/IFilm";
import {useFetching} from "../../../hooks/useFetching";
import FilmService from "../../../api/FilmService";
import {useNavigate} from "react-router-dom";

const FilmCreateForm: FC = () => {
    const [poster, setPoster] = useState<string>('');
    const navigate = useNavigate();
    const {message} = App.useApp();

    const genreOptions: SelectProps['options'] = allGenres.map(g => {
        return {
            label: g.name,
            value: g.id
        }
    })

    const onFinish = (values: any) => {
        const year = values['year'].format('YYYY');
        const genres = values['genresSelect'].map((e: number) => allGenres[e - 1]);
        const film = {
            ...values,
            year: year,
            genres: genres,
        } as IFilm;
        createFilm(film);
    };

    const [createFilm, isLoading, error] = useFetching(async (film: IFilm) => {
        const response = await FilmService.createFilm(film);
        navigate('admin/films');
        message.success('Фильм сохранен под id: ' + response.data.id);
    })

    if (isLoading) {
        return <Spin fullscreen/>
    }

    if (error !== '') {
        message.error(error);
    }

    return (
        <Flex gap="large">
            <div className="poster-container">
                <Image width="302px"
                       height="469px"
                       alt="Постер"
                       src={poster}
                       fallback={fallbackImage}
                />
            </div>
            <Form onFinish={onFinish}>
                <Form.Item name="name" rules={[{required: true, message: 'Введите название фильма'}]}>
                    <Input placeholder="Название фильма" size="large"/>
                </Form.Item>
                <Form.Item name="year" rules={[{required: true, message: 'Введите год выпуска'}]}>
                    <DatePicker style={{minWidth: "190px"}} placeholder="Год выпуска фильма" picker="year"/>
                </Form.Item>
                <Form.Item name="poster" rules={[{
                    type: 'url',
                    message: 'Введите url',
                }, {
                    required: true,
                    message: 'Введите ссылку на постер',
                }
                ]}>
                    <Input
                        placeholder="Постер фильма"
                        onChange={(e) => setPoster(e.target.value)}
                    />
                </Form.Item>
                <Form.Item name="genresSelect" rules={[{required: true, message: 'Введите жанры'}]}>
                    <Select
                        style={{minWidth: "190px"}}
                        mode="multiple"
                        allowClear
                        placeholder="Выберите жанры"
                        options={genreOptions}
                    />
                </Form.Item>
                <Form.Item name="description" style={{minWidth: "300px"}}
                           rules={[{required: true, message: 'Введите описание'}]}>
                    <Input.TextArea placeholder="Описание фильма"/>
                </Form.Item>
                <Form.Item>
                    <Button type="primary" htmlType="submit">Сохранить фильм</Button>
                </Form.Item>
            </Form>
        </Flex>
    );
};

export default FilmCreateForm;