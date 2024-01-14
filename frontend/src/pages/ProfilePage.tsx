import React, {FC} from 'react';
import UserProfile from "../components/user/UserProfile/UserProfile";
import {Footer} from "antd/es/layout/layout";

const ProfilePage: FC = () => {
    return (
        <main className="main-page">
            <UserProfile/>
            <Footer style={{background: "transparent"}}/>
        </main>
    );
};

export default ProfilePage;