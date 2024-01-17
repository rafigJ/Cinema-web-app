import React, {FC} from 'react';
import '../styles/App.css'
import FilmGrid from "../components/user/FilmGridUI/FilmGrid/FilmGrid";
import {Footer} from "antd/es/layout/layout";


const FilmsPage: FC = () => {

    return (
        <main className="main-page">
            <FilmGrid/>
            <Footer style={{background: "transparent"}}/>
        </main>
    );
};

export default FilmsPage;