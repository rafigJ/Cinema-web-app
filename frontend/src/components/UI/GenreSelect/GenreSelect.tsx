import React, {FC} from 'react';
import {ConfigProvider, Select, SelectProps, theme} from "antd";
import {allGenres} from "../../../helpers/genres";
import './GenreSelect.css'

/*
* Выпадающий multiselect из antd с темной темой для User интерфейса
*/
const GenreSelect: FC<SelectProps> = ({...props}) => {
    const genreOptions: SelectProps['options'] = allGenres.map(g => {
        return {
            label: g.name,
            value: g.id
        }
    })

    return (
        <ConfigProvider
            theme={{
                algorithm: theme.darkAlgorithm,
                token: {
                    colorTextPlaceholder: 'hsla(0, 0%, 100%, .15)',
                    colorPrimaryBg: '#2d303b',
                    colorBgContainer: '#2d303b',
                },
            }}
        >
            <Select
                popupClassName="popup-select"
                mode="tags"
                allowClear
                placeholder="Выберите жанры"
                options={genreOptions}
                {...props}
            />
        </ConfigProvider>
    );
};

export default GenreSelect;