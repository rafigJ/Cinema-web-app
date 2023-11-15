import React from 'react';
import FilmItem from "../components/UI/FilmItem/FilmItem";

const HomePage = () => {
    return (
        <main style={{display: "flex",
            alignItems: "center",
            justifyContent: "center",
            top:"0", left:"0",
            width: "100vw", height: "80vh"}}>

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
        </main>
    );
};

export default HomePage;