import React from 'react';
import FilmItem from "../components/UI/FilmItem/FilmItem";

const HomePage = () => {
    return (
        <main className="main-page">

            <FilmItem
                id="235"
                poster="https://kinopoiskapiunofficial.tech/images/posters/kp/1048334.jpg"
                genres={[
                    {
                        "id": 8,
                        "name": "драма"
                    },
                    {
                        "id": 11,
                        "name": "криминал"
                    },
                    {
                        "id": 19,
                        "name": "триллер"
                    }
                ]}
                name="Джокер"
                year="2019"
            />

            <input type={"email"} className="auth-container__input" placeholder="E-Mail"/>
        </main>
    );
};

export default HomePage;