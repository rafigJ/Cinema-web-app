export interface IUser {
    name: string;
    email: string;
    role: 'USER' | 'ADMIN';
}