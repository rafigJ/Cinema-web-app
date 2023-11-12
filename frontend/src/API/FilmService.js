import axios from "axios";

export default class FilmService {

    static async getByName(query) {
        return await axios.get(`http://localhost:8080/api/v1/films/search`, {
                params: {query: query},
                headers: {
                    'Access-Control-Allow-Origin': 'http://localhost:3000',
                    'Content-Type': 'application/json',
                },
            }
        );
    }

};