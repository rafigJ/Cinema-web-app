import React, {FC} from 'react';
import UserProfile from "../components/user/UserProfile/UserProfile";

const ProfilePage: FC = () => {
    return (
        <main className="main-page">
            <UserProfile/>
        </main>
    );
};

export default ProfilePage;