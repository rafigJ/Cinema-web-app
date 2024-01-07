import React, {FC, useEffect, useMemo, useState} from 'react';
import "./SessionOverviewContainer.css"
import SessionTableRow from "../SessionTableRow/SessionTableRow";
import {Button, DatePicker, Empty, Spin} from "antd";
import {CaretRightOutlined} from "@ant-design/icons";
import dayjs from "dayjs";
import {useFetching} from "../../../../hooks/useFetching";
import FilmService from "../../../../API/FilmService";
import {ISession} from "../../../../types/model/ISession";

interface SessionOverviewContainerProps {
    filmId: number | string;
}

/**
 * Переводит дату в строковое представление для заголовка
 * пример: "Воскресенье, 7 января"
 */
const dateToString = (date: dayjs.Dayjs | null) => {
    if (date === null) {
        return "you should select date"
    }
    const days = [
        "Воскресенье", "Понедельник", "Вторник",
        "Среда", "Четверг", "Пятницу", "Субботу"
    ];

    const months = [
        "Января", "Февраля", "Марта", "Апреля",
        "Мая", "Июня", "Июля", "Августа", "Сентября",
        "Октября", "Ноября", "Декабря"
    ]

    return days[date.day()] + ", " + date.date() + " " + months[date.month()];
}

/**
 * Нужен для страницы с фильмом (FilmOverviewPage) пользователя
 */
const SessionOverviewContainer: FC<SessionOverviewContainerProps> = ({filmId}) => {
    const [sessions, setSessions] = useState<ISession[]>([] as ISession[]);
    const [isCalendarOpen, setIsCalendarOpen] = useState<boolean>(false);
    const [date, setDate] = useState<string>(new Date().toISOString().substring(0, 10));
    const [dateHeader, setDateHeader] = useState<string>(dateToString(dayjs()));

    const [fetchSessions, isLoading, error] = useFetching(async (id, date) => {
        const response = await FilmService.getSessionsById(id, date);
        setSessions(response.data)
    });

    useEffect(() => {
        if (date && filmId) {
            fetchSessions(filmId, date);
        }
    }, [date, filmId]);

    const hallIdsSet = useMemo(() => {
        const set = new Set<number>();
        sessions.forEach(s => set.add(s.hall.id));
        return Array.from(set.values());
    }, [sessions]);

    if (isLoading) {
        return <Spin/>;
    }

    if (error !== '') {
        return <div>sessionOverviewContainer error</div>;
    }

    return (
        <div className="sessions-container">
            <div className="date-container">
                <h1 className="date-container__title">Расписание на {dateHeader}</h1>
                <DatePicker
                    value={dayjs(date)}
                    open={isCalendarOpen}
                    style={{visibility: "hidden", width: 0}}
                    onOpenChange={open => setIsCalendarOpen(open)}
                    onChange={(date, stringDate) => {
                        setDate(stringDate);
                        setDateHeader(dateToString(date));
                    }}
                />
                <Button
                    type="text"
                    size="large"
                    icon={<CaretRightOutlined spin={isCalendarOpen} style={{color: "whitesmoke"}}/>}
                    onClick={() => setIsCalendarOpen(!isCalendarOpen)}
                />
            </div>
            {
                sessions.length
                    ?
                    <table className="sessions-container__table">
                        <tbody>
                        {hallIdsSet.map(hallId =>
                            <SessionTableRow key={hallId} hallId={hallId} sessions={sessions}/>
                        )}
                        </tbody>
                    </table>
                    :
                    <Empty description={false}/>
            }
        </div>
    );
};

export default SessionOverviewContainer;