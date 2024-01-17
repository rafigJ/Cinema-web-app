import React, {FC} from 'react';
import {ConfigProvider, Pagination, PaginationProps, theme} from "antd";

const CustomPagination: FC<PaginationProps> = ({...props}) => {
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
            <Pagination {...props}/>
        </ConfigProvider>
    );
};

export default CustomPagination;