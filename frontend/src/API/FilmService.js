import axios from "axios";

export default class FilmService {

    static async getByName(query) {
        return await axios.get(`http://localhost:8080/api/v1/films/search`, {
                params: {query: query},
                headers: {
                    'Content-Type': 'application/json',
                },
            }
        );
    }

    static async getAllFilms(offset = 0, limit = 10) {
        return await axios.get(`http://localhost:8080/api/v1/films`, {
                params: {
                    offset: offset,
                    limit: limit
                },
                headers: {
                    'Content-Type': 'application/json',
                },
            }
        );
    }

};