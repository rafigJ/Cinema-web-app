import React, {FC, useEffect, useState} from 'react';
import CrudTableLayout from "./CrudTableLayout/CrudTableLayout";
import {Col, Empty, Row, Statistic} from "antd";
import {useFetching} from "../../hooks/useFetching";
import FilmService from "../../api/FilmService";
import AdminService from "../../api/AdminService";

const StatisticPage: FC = () => {
    const [filmCount, setFilmCount] = useState(0);
    const [userCount, setUserCount] = useState(0);
    const [ticketCount, setTicketCount] = useState(0);
    const [profitForYear, setProfitForYear] = useState(0);

    const [fetchData, isLoading, isError] = useFetching(async () => {
        const films = await FilmService.getAllFilms(0, 1);
        setFilmCount(films.data.totalElements);

        const users = await AdminService.getAllUsers(0, 1);
        setUserCount(users.data.totalElements);

        const tickets = await AdminService.getTickets(0, 1);
        setTicketCount(tickets.data.totalElements);

        const profitResponse = await AdminService.getProfitByPeriod("2024-01-01", "2025-01-01"); // todo убрать хардкод
        setProfitForYear(profitResponse.data.profit);
    })

    useEffect(() => {
        fetchData();
    }, []);

    if (isError) {
        return <CrudTableLayout title="Статистика" crudTable={<Empty/>}/>
    }

    return (
        <CrudTableLayout
            title="Статистика"
            crudTable={
                <Row gutter={16}>
                    <Col span={12}>
                        <Statistic loading={isLoading} title="Количество фильмов" value={filmCount}/>
                    </Col>
                    <Col span={12}>
                        <Statistic loading={isLoading} title="Количество активных пользователей" value={userCount}/>
                    </Col>
                    <Col span={12}>
                        <Statistic loading={isLoading} title="Количество проданных билетов" value={ticketCount}/>
                    </Col>
                    <Col span={12}>
                        <Statistic loading={isLoading} title="Прибыль за год" value={profitForYear}/>
                    </Col>
                </Row>
            }
        />
    );
};

export default StatisticPage;