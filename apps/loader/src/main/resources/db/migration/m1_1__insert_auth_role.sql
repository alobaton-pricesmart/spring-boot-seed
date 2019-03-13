
INSERT INTO `auth_role` (id, client_id, name, description, permissions) VALUES ('ADMIN', 'api-client', JSON_OBJECT('es', 'Administrador', 'en', 'Administrator'), JSON_OBJECT('es', 'Tiene acceso a todas las funcionalidades del sistema', 'en', 'Has access to all the systems features'),	'["*"]');
