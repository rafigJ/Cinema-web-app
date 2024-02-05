import React from 'react';
import {Outlet} from 'react-router';
import {App, Layout} from "antd";
import SideBar from "../admin/SideBar/SideBar";
import TopicMenu from "../admin/TopicMenu";
import '../../styles/App.css'

export const WithAdminSidebar = () => (
    <App>
        <Layout style={{height: "100vh", overflow: "auto" }}>
            <SideBar menu={(
                <TopicMenu/>
            )}/>
            <Outlet/>
        </Layout>

    </App>
)
