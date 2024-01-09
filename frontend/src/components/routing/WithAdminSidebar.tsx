import React from 'react';
import {Outlet} from 'react-router';
import {App, Layout} from "antd";
import SideBar from "../admin/SideBar/SideBar";
import TopicMenu from "../admin/TopicMenu";

export const WithAdminSidebar = () => (
    <App>
        <main className="main-menu">
            <Layout style={{minHeight: '100svh'}}>
                <SideBar menu={(
                    <TopicMenu/>
                )}/>
                <Outlet/>
            </Layout>
        </main>
    </App>
)
