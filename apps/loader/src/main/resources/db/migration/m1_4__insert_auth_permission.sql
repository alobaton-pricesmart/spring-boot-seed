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