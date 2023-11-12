import axios from "axios";

export default class FilmService {

    static async getByName(name) {

        const response = await axios.get(`http://localhost:8080/films?query=${name}`);
        return response;
    }

};