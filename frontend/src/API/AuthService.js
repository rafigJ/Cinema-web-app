import axios from "axios";

export default class AuthService {

    static async getById(id) {
        const response = await axios.get(`https://jsonplaceholder.typicode.com/posts/${id}`);
        return response;
    }

    static async getCommentsById(id) {
        const response = await axios.get(`https://jsonplaceholder.typicode.com/posts/${id}/comments`);
        return response;
    }
};