import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import './styles/normalize.css'
import {ConfigProvider} from "antd";
import ru_RU from "antd/lib/locale/ru_RU";

const rootElement = document.getElementById('root');

if (!rootElement) {
    throw new Error('Root element with id "root" not found');
}

const root = ReactDOM.createRoot(rootElement);
root.render(
    <ConfigProvider locale={ru_RU}>
        <App/>
    </ConfigProvider>
);