# Веб-приложение "Кинотеатр"

Этот проект представляет собой веб-приложение для покупки билетов в кинотеатр и учета покупок. Пользователи могут авторизироваться, и у них есть две роли: пользователь и администратор. 

## Основные функции
- Регистрация и авторизация пользователей по JWT токену.
- Разделение на роли: пользователь и администратор.
- Пользовательский интерфейс для покупки билетов.
- Интерфейс администратора для управления фильмами, сеансами и пользователями.

## Функциональные возможности
- **Пользователь:**
  - Просмотр списка фильмов и сеансов.
  - Покупка билетов на сеансы.
  - Просмотр истории покупок.

- **Администратор:**
  - Управление фильмами: Добавление, редактирование, удаление.
  - Управление сеансами: создание, редактирование, удаление.
  - Управление пользователями: изменение ролей.
  - Просмотр статистики: количество проданных билетов, прибыль за год, количество пользователей. 

## Технологии
- **Frontend:** 
  - TypeScript
  - React.js
  - React Router Dom
- **Backend:** 
  - Java 17
  - Spring Boot
  - Spring Data JPA
  - Spring Security
- **База данных:** PostgreSQL

## Демо
- [cinema-app.ru](http://cinema-app.ru)
- [api-docs](http://79.174.84.237:8080/swagger-ui/index.html)
