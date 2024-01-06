export interface ITicket {
    id: number;
    userUuid: string;
    sessionId: number;
    row: number;
    column: number;
    buyTime: string;
}