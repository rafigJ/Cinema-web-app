import React, {FC, useState} from 'react';
import {Button, DatePicker, Flex, Form, Image, Input, Select, SelectProps} from "antd";
import {fallbackImage} from "./const/fallbackImage";
import {allGenres} from "../../../helpers/genres";
import {IFilm} from "../../../types/model/IFilm";
import dayjs from "dayjs";

interface FilmFormProps {
    onSubmit: (a: IFilm) => void
    film?: IFilm
}

const FilmForm: FC<FilmFormProps> = ({onSubmit, film = {} as IFilm}) => {
    const [poster, setPoster] = useState<string>(film?.poster);
    const genreOptions: SelectProps['options'] = allGenres.map(g => {
        return {
            label: g.name,
            value: g.id
        }
    })

    const onFinish = (values: any) => {
        const year = values['year'].format('YYYY');
        const actualGenres = values['genresSelect'].map((e: number) => allGenres[e - 1]);
        const newFilm = {
            id: film?.id,
            name: values.name,
            description: values.description,
            poster: values.poster,
            year: year,
            genres: actualGenres,
        } as IFilm;
        onSubmit(newFilm);
    };

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
            <Form style={{minWidth: "50vw"}} onFinish={onFinish}>
                <Form.Item initialValue={film?.name} name="name"
                           rules={[{required: true, message: 'Введите название фильма'}]}>
                    <Input placeholder="Название фильма" size="large"/>
                </Form.Item>
                <Form.Item initialValue={dayjs(film?.year)} name="year"
                           rules={[{required: true, message: 'Введите год выпуска'}]}>
                    <DatePicker style={{minWidth: "190px"}} placeholder="Год выпуска фильма" picker="year"/>
                </Form.Item>
                <Form.Item initialValue={film?.poster} name="poster" rules={[{
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
                <Form.Item initialValue={film?.genres?.map(g => g.id)} name="genresSelect"
                           rules={[{required: true, message: 'Введите жанры'}]}>
                    <Select
                        style={{minWidth: "190px"}}
                        mode="multiple"
                        allowClear
                        placeholder="Выберите жанры"
                        options={genreOptions}
                    />
                </Form.Item>
                <Form.Item initialValue={film?.description} name="description" style={{minWidth: "300px"}}
                           rules={[{required: true, message: 'Введите описание'}]}>
                    <Input.TextArea placeholder="Описание фильма"/>
                </Form.Item>
                <Form.Item>
                    <Button type="primary" htmlType="submit">
                        {
                            // Если фильм пришел пустым, значит форма нужна для обновления
                            Object.keys(film).length === 0
                                ?
                                "Сохранить фильм"
                                :
                                "Обновить фильм"
                        }
                    </Button>
                </Form.Item>
            </Form>
        </Flex>
    );
};

export default FilmForm;