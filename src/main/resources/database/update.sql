INSERT INTO tz_roles (id, name, authority) VALUES
(2, 'Обычный пользователь', 'ROLE_USER'), (1, 'Администратор', 'ROLE_ADMIN');

INSERT INTO tz_users (id_role, name, login, email, pass) VALUES
(1, 'Власов Александр Сергеевич', 'sysdba', 'velesov7493@gmail.com', 'AB4154A7C451F56E9B7FF1537758DDD0C619F8BE');

INSERT INTO tj_posts (id_author, id_topic, caption, description) VALUES
(1, null, 'О чем этот форум?', 'Это учебный проект форума. Кто и о чем тут беседует - не имеет значения.'),
(1, null, 'Правила форума', 'Не материться!!!'),
(null, 1, '?', 'Правда? Ну ладно.');