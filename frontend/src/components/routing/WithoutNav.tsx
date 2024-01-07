import React from 'react';
import {Outlet} from 'react-router';
import {Layout} from "antd";
import SideBar from "../admin/SideBar/SideBar";
import TopicMenu from "../admin/TopicMenu";

export const WithoutNav = () => (
    <main className="main-menu">
        <Layout style={{minHeight: '100svh'}}>
            <SideBar menu={(
                <TopicMenu/>
            )}/>
            <Outlet/>
        </Layout>
    </main>
)
