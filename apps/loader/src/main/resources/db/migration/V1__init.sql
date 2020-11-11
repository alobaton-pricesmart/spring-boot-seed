INSERT INTO `auth_client_details` (client_id, resource_ids, secret_required, client_secret, scoped, scope, authorized_grant_types, registered_redirect_uri, authorities, access_token_validity_seconds, refresh_token_validity_seconds, auto_approve, additional_information) 
VALUES ('api-client', JSON_ARRAY('resource_id'), true, '$2a$10$Nh913vrYBSxp2Rf3uq8hjOAAlsIbon8erZE0.vTSh/RmJb7xlmVpm', true, JSON_ARRAY('write', 'read'), JSON_ARRAY('authorization_code', 'client_credentials', 'implicit', 'password', 'refresh_token'), NULL, NULL, 3600, 7200, true, NULL);

INSERT INTO `auth_user` (nickname, name, last_name, email, roles, password,  locked, enabled) 
VALUES ('admin', 'Administrador', NULL, 'alobaton.restrepo@gmail.com', JSON_ARRAY('ADMIN'), '$2a$10$n0XSnNTqHVwO44KHR5R5.u5SgZEbwZ4lEYSiGbBEMK49FKonqWEx2', false, true);

INSERT INTO `auth_role` (id, group_id, description, parent_id) 
VALUES ('ADMIN', NULL, JSON_OBJECT('es', 'Tiene acceso a todas las funcionalidades del sistema', 'en', 'Has access to all the systems features'), NULL);

INSERT INTO `auth_permission` (id, description, client_id, role_id) 
VALUES ('read:user', JSON_OBJECT(), 'api-client', 'ADMIN');

INSERT INTO `auth_permission` (id, description, client_id, role_id) 
VALUES ('read:users', JSON_OBJECT(), 'api-client', 'ADMIN');

INSERT INTO `auth_permission` (id, description, client_id, role_id) 
VALUES ('create:user', JSON_OBJECT(), 'api-client', 'ADMIN');

INSERT INTO `auth_permission` (id, description, client_id, role_id) 
VALUES ('update:user', JSON_OBJECT(), 'api-client', 'ADMIN');

INSERT INTO `auth_permission` (id, description, client_id, role_id) 
VALUES ('delete:user', JSON_OBJECT(), 'api-client', 'ADMIN');

INSERT INTO `auth_permission` (id, description, client_id, role_id) 
VALUES ('read:role', JSON_OBJECT(), 'api-client', 'ADMIN');

INSERT INTO `auth_permission` (id, description, client_id, role_id) 
VALUES ('read:roles', JSON_OBJECT(), 'api-client', 'ADMIN');

INSERT INTO `auth_permission` (id, description, client_id, role_id) 
VALUES ('create:role', JSON_OBJECT(), 'api-client', 'ADMIN');

INSERT INTO `auth_permission` (id, description, client_id, role_id) 
VALUES ('update:role', JSON_OBJECT(), 'api-client', 'ADMIN');

INSERT INTO `auth_permission` (id, description, client_id, role_id) 
VALUES ('delete:role', JSON_OBJECT(), 'api-client', 'ADMIN');

INSERT INTO `auth_permission` (id, description, client_id, role_id) 
VALUES ('read:permission', JSON_OBJECT(), 'api-client', 'ADMIN');

INSERT INTO `auth_permission` (id, description, client_id, role_id) 
VALUES ('read:permissions', JSON_OBJECT(), 'api-client', 'ADMIN');

INSERT INTO `auth_permission` (id, description, client_id, role_id) 
VALUES ('create:permission', JSON_OBJECT(), 'api-client', 'ADMIN');

INSERT INTO `auth_permission` (id, description, client_id, role_id) 
VALUES ('update:permission', JSON_OBJECT(), 'api-client', 'ADMIN');

INSERT INTO `auth_permission` (id, description, client_id, role_id) 
VALUES ('delete:permission', JSON_OBJECT(), 'api-client', 'ADMIN');

INSERT INTO `auth_permission` (id, description, client_id, role_id) 
VALUES ('read:client', JSON_OBJECT(), 'api-client', 'ADMIN');

INSERT INTO `auth_permission` (id, description, client_id, role_id) 
VALUES ('read:clients', JSON_OBJECT(), 'api-client', 'ADMIN');

INSERT INTO `auth_permission` (id, description, client_id, role_id) 
VALUES ('create:client', JSON_OBJECT(), 'api-client', 'ADMIN');

INSERT INTO `auth_permission` (id, description, client_id, role_id) 
VALUES ('update:client', JSON_OBJECT(), 'api-client', 'ADMIN');

INSERT INTO `auth_permission` (id, description, client_id, role_id) 
VALUES ('delete:client', JSON_OBJECT(), 'api-client', 'ADMIN');
