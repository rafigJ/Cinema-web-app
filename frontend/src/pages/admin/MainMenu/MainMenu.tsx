import React from 'react';
import {Layout} from "antd";
import SideBar from "../../../components/admin/SideBar/SideBar";
import TopicMenu from "../../../components/admin/TopicMenu";
import CrudFilmsPage from "../CrudPages/CrudFilmsPage";

const MainMenu = () => {
    // внедрить роутинг
    return (
        <main className="main-menu">
            <Layout style={{minHeight: '100vh'}}>
                <SideBar menu={(
                    <TopicMenu/>
                )}/>
                <CrudFilmsPage/>
            </Layout>
        </main>
    );
};

export default MainMenu;