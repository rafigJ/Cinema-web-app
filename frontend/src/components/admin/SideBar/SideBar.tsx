import {Layout} from "antd";
import React, {FC} from "react";

interface SideBarProps {
    menu: React.ReactElement;
}

const SideBar: FC<SideBarProps> = ({menu}) => {
    return (
        <Layout.Sider
            breakpoint="lg"
            collapsedWidth="0"
        >
            {menu}
        </Layout.Sider>
    );
};

export default SideBar;