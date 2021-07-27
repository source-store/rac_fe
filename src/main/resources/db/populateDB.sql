DELETE
FROM user_roles;
DELETE
FROM users;
ALTER SEQUENCE global_seq RESTART WITH 1000;

INSERT INTO users (name, email, login, password)
VALUES ('User', 'user@yandex.ru', 'login1', 'password'),
       ('Admin', 'admin@yandex.ru', 'login2', '{noop}password2');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 1000),
       ('ADMIN', 1001);
