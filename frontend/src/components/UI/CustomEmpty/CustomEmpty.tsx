import React, {FC} from 'react';
import {Empty} from "antd";

interface CustomEmptyProps {
    description: string
}

const CustomEmpty: FC<CustomEmptyProps> = ({description}) => {
    return (
        <Empty description={<h3 style={{color: "whitesmoke"}}> {description} </h3>}/>
    );
};

export default CustomEmpty;