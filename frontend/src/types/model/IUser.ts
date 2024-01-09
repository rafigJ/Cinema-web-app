export interface IUser {
    uuid: string;
    name: string;
    email: string;
    money: number;
    role: 'USER' | 'ADMIN';
    createTime: string;
    updateTime: string;
}