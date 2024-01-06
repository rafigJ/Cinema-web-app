import React, {FC} from 'react';
import "./SessionTableRow.css"
import {ISession} from "../../../../../types/model/ISession";
import SessionButton from "../SessionButton/SessionButton";

interface SessionsTableRowProps {
    hallId: number;
    sessions: ISession[];
}

const SessionTableRow: FC<SessionsTableRowProps> = ({hallId, sessions}) => {
    const currentHallSessions = sessions.filter(s => s.hall.id === hallId);
    return (
        <tr>
            <td className="hall-title">Зал {currentHallSessions[0].hall.name}</td>
            <td>
                <ul className="sessions-row-list" style={{display: "flex"}}>
                    {
                        currentHallSessions.map(s => (
                            <li key={s.id} className="sessions-row-list__item">
                                <SessionButton time={s.time} price={s.price}/>
                            </li>
                        ))
                    }
                </ul>
            </td>
        </tr>
    );
};

export default SessionTableRow;