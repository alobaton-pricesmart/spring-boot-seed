INSERT INTO `auth_user` (nickname, name, last_name, email, roles, password,  locked, enabled) 
VALUES ('admin', 'Administrador', NULL, 'alobaton.restrepo@gmail.com', JSON_ARRAY('ADMIN'), '$2y$12$RG/bCsnVfmegi5X33V78YeYwBoBofn1a9RLnkft4nNf89oos1c.E.', false, true);