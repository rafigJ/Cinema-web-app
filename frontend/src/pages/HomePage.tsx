import React, {useEffect, useState} from 'react';
import {useFetching} from "../hooks/useFetching";
import {IFilm} from "../types/model/IFilm";
import {Spin} from "antd";
import '@splidejs/react-splide/css';
import FilmsSlider from "../components/UI/FilmsSlider/FilmsSlider";
import SessionService from "../api/SessionService";
import dayjs from "dayjs";

const removeDuplicates = (arr: IFilm[]) => {
    const uniqueMap = new Map();
    arr.forEach(film => {
        if (!uniqueMap.has(film.id)) {
            uniqueMap.set(film.id, film);
        }
    });
    return Array.from(uniqueMap.values());
}

const HomePage = () => {
    const [todayFilms, setTodayFilms] = useState([] as IFilm[])
    const [tomorrowFilms, setTomorrowFilms] = useState([] as IFilm[])

    const [fetchFilms, isLoading, error] = useFetching(async () => {
        const today = dayjs().format("YYYY-MM-DD");
        let response = await SessionService.getAll(0, 35, today);
        let films = response.data.content.map(session => session.film);
        setTodayFilms(removeDuplicates(films))

        const tomorrowDay = dayjs().add(1, 'day').format("YYYY-MM-DD");
        response = await SessionService.getAll(0, 35, tomorrowDay);
        films = response.data.content.map(session => session.film);
        setTomorrowFilms(removeDuplicates(films))
    },)

    useEffect(() => {
        fetchFilms();
    }, []);

    if (isLoading) {
        return <div style={{display: 'flex', alignItems: "center", justifyContent: "center", minHeight: "80vh"}}>
            <Spin size="large"/>
        </div>
    }

    if (error !== '') {
        return (
            <div>{error}</div>
        );
    }

    return (
        <main className="main-page">
            <h1 className="main-title">Фильмы на сегодня</h1>
            {todayFilms.length
                ?
                <FilmsSlider films={todayFilms}/>
                :
                "На сегодня сеансы отсутствуют"
            }

            <h1 className="main-title">Фильмы на завтра</h1>
            {tomorrowFilms
                ?
                <FilmsSlider films={tomorrowFilms}/>
                :
                "На завтра сеансы отсутствуют"
            }
        </main>
    );
};

export default HomePage;