import axios from "axios";

export default class FilmService {
    static url = "http://192.168.1.110:8080"

    static async getByName(query) {
        return await axios.get(`${FilmService.url}/api/v1/films/search`, {
                params: {query: query},
                headers: {
                    'Content-Type': 'application/json',
                },
            }
        );
    }

    static async getAll(offset = 0, limit = 10) {
        return await axios.get(`${FilmService.url}/api/v1/films`, {
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

    static async getById(id) {
        return await axios.get(`${FilmService.url}/api/v1/films/${id}`, {
                headers: {
                    'Content-Type': 'application/json',
                },
            }
        );
    }
};