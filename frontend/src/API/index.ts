import axios from 'axios';

export const API_URL = `http://192.168.1.110:8080/api/v1/`

const $api = axios.create({
    baseURL: API_URL,
    headers: {
        'Content-Type': 'application/json',
    }
})

export default $api;