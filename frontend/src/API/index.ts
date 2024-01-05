import axios from 'axios';

const API_URL = `http://localhost:8080/api/v1/`

const $api = axios.create({
    baseURL: API_URL,
    headers: {
        'Content-Type': 'application/json',
    }
})

$api.interceptors.request.use((config) => {
    let token = localStorage.getItem('token');
    if (token !== null) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});

export default $api;