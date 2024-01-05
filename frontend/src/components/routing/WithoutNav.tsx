import React from 'react';
import {Outlet} from 'react-router';
import {Layout} from "antd";
import SideBar from "../UI/admin/SideBar/SideBar";
import TopicMenu from "../UI/admin/TopicMenu";

export const WithoutNav = () => (
    <main className="main-menu">
        <Layout style={{minHeight: '100vh'}}>
            <SideBar menu={(
                <TopicMenu/>
            )}/>
            <Outlet/>
        </Layout>
    </main>
)
