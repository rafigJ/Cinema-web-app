import React from 'react';
import FilmOverviewContainer from "../components/UI/FilmOverviewContainer/FilmOverviewContainer";

const HomePage = () => {
    return (
        <main className="main-page">
            <FilmOverviewContainer
                id="235"
                description="Готэм, начало 1980-х годов. Комик Артур Флек живет с больной матерью, которая с детства учит его «ходить с улыбкой». Пытаясь нести в мир хорошее и дарить людям радость, Артур сталкивается с человеческой жестокостью и постепенно приходит к выводу, что этот мир получит от него не добрую улыбку, а ухмылку злодея Джокера."
                poster="https://kinopoiskapiunofficial.tech/images/posters/kp/1048334.jpg"
                genreArray={[
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