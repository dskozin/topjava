DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;
ALTER SEQUENCE meals_id_seq RESTART WITH 1;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, description, dateTime, calories) VALUES
  (100000, 'Затврак', '10/05/2015 10:00:00', 1000),
  (100000, 'Обед', '10/05/2015 15:00:00', 1000),
  (100000, 'Ужин', '10/05/2015 19:00:00', 800),
  (100000, 'Завтрак', '11/05/2015 9:30:00', 1500),
  (100000, 'Обед', '11/05/2015 13:15:00', 1800);

