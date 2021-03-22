
-- auth_client_details
 INSERT INTO `auth_client_details`( client_id, resource_ids, secret_required, client_secret, scoped, scope, authorized_grant_types, registered_redirect_uri, authorities, access_token_validity_seconds, refresh_token_validity_seconds, auto_approve, additional_information, created , created_by)
VALUES( 'api-client', JSON_ARRAY ('resource_id'), true, '$2a$10$Nh913vrYBSxp2Rf3uq8hjOAAlsIbon8erZE0.vTSh/RmJb7xlmVpm', true, JSON_ARRAY ( 'write', 'read' ), JSON_ARRAY ( 'authorization_code', 'client_credentials', 'implicit', 'password', 'refresh_token', 'auth_password' ), NULL, NULL, 3600, 7200, true, NULL, NOW(), 'admin' );
-- auth_user
 ALTER TABLE auth_user ADD UNIQUE (email);

INSERT INTO `auth_user`( nickname, name, last_name, email, password, locked, enabled, created , created_by)
VALUES( 'admin', 'Administrador', NULL, 'admin@domain.com', '$2a$10$n0XSnNTqHVwO44KHR5R5.u5SgZEbwZ4lEYSiGbBEMK49FKonqWEx2', false, true, NOW(), 'admin' );
-- auth_role
INSERT INTO `auth_role` ( id, created, created_by, description, group_id, parent_id )
VALUES ( 'ADMIN', NOW(), 'admin', '{"en": "Administrator", "es": "Administrador"}', NULL, NULL );
-- auth_user_role
INSERT INTO `auth_user_role` (nickname, role_id, created, created_by, last_modified, modified_by)
VALUES('admin', 'ADMIN', NOW(), 'admin', NULL, NULL);
-- auth_permission
INSERT INTO `auth_permission` ( id, description , created, created_by)
VALUES ( 'read:user', JSON_OBJECT (), NOW(), 'admin' );

INSERT INTO `auth_permission`( id, description, created, created_by)
VALUES( 'read:users', JSON_OBJECT (), NOW(), 'admin' );

INSERT INTO `auth_permission`( id, description , created, created_by)
VALUES( 'create:user', JSON_OBJECT (), NOW(), 'admin' );

INSERT INTO `auth_permission`( id, description , created, created_by)
VALUES( 'update:user', JSON_OBJECT (), NOW(), 'admin' );

INSERT INTO `auth_permission`( id, description , created, created_by)
VALUES( 'delete:user', JSON_OBJECT (), NOW(), 'admin' );

INSERT INTO `auth_permission`( id, description , created, created_by)
VALUES( 'read:role', JSON_OBJECT (), NOW(), 'admin' );

INSERT INTO `auth_permission`( id, description , created, created_by)
VALUES( 'read:roles', JSON_OBJECT (), NOW(), 'admin' );

INSERT INTO `auth_permission`( id, description , created, created_by)
VALUES( 'create:role', JSON_OBJECT (), NOW(), 'admin' );

INSERT INTO `auth_permission`( id, description , created, created_by)
VALUES( 'update:role', JSON_OBJECT (), NOW(), 'admin' );

INSERT INTO `auth_permission`( id, description , created, created_by)
VALUES( 'delete:role', JSON_OBJECT (), NOW(), 'admin' );

INSERT INTO `auth_permission`( id, description , created, created_by)
VALUES( 'read:permission', JSON_OBJECT (), NOW(), 'admin' );

INSERT INTO `auth_permission`( id, description , created, created_by)
VALUES( 'read:permissions', JSON_OBJECT (), NOW(), 'admin' );

INSERT INTO `auth_permission` ( id, description , created, created_by)
VALUES ( 'create:permission', JSON_OBJECT (), NOW(), 'admin' );

INSERT INTO `auth_permission` ( id, description , created, created_by)
VALUES ( 'update:permission', JSON_OBJECT (), NOW(), 'admin' );

INSERT INTO `auth_permission` ( id, description , created, created_by)
VALUES ( 'delete:permission', JSON_OBJECT (), NOW(), 'admin' );

INSERT INTO `auth_permission` ( id, description , created, created_by)
VALUES ( 'read:client', JSON_OBJECT (), NOW(), 'admin' );

INSERT INTO `auth_permission` ( id, description , created, created_by)
VALUES ( 'read:clients', JSON_OBJECT (), NOW(), 'admin' );

INSERT INTO `auth_permission` ( id, description , created, created_by)
VALUES ( 'create:client', JSON_OBJECT (), NOW(), 'admin' );

INSERT INTO `auth_permission` ( id, description , created, created_by)
VALUES ( 'update:client', JSON_OBJECT (), NOW(), 'admin' );

INSERT INTO `auth_permission` ( id, description , created, created_by)
VALUES ( 'delete:client', JSON_OBJECT (), NOW(), 'admin' );

-- auth_role_permission
INSERT INTO `auth_role_permission` ( permission_id, role_id, created, created_by)
VALUES ( 'read:user', 'ADMIN', NOW(), 'admin' );

INSERT INTO `auth_role_permission` ( permission_id, role_id, created, created_by)
VALUES ( 'read:users', 'ADMIN', NOW(), 'admin' );

INSERT INTO `auth_role_permission` ( permission_id, role_id, created, created_by)
VALUES ( 'create:user', 'ADMIN', NOW(), 'admin' );

INSERT INTO `auth_role_permission` ( permission_id, role_id, created, created_by)
VALUES ( 'update:user', 'ADMIN', NOW(), 'admin' );

INSERT INTO `auth_role_permission` ( permission_id, role_id, created, created_by)
VALUES ( 'delete:user', 'ADMIN', NOW(), 'admin' );

INSERT INTO `auth_role_permission` ( permission_id, role_id, created, created_by)
VALUES ( 'read:role', 'ADMIN', NOW(), 'admin' );

INSERT INTO `auth_role_permission` ( permission_id, role_id, created, created_by)
VALUES ( 'read:roles', 'ADMIN', NOW(), 'admin' );

INSERT INTO `auth_role_permission` ( permission_id, role_id, created, created_by)
VALUES ( 'create:role', 'ADMIN', NOW(), 'admin' );

INSERT INTO `auth_role_permission` ( permission_id, role_id, created, created_by)
VALUES ( 'update:role', 'ADMIN', NOW(), 'admin' );

INSERT INTO `auth_role_permission` ( permission_id, role_id, created, created_by)
VALUES ( 'delete:role', 'ADMIN', NOW(), 'admin' );

INSERT INTO `auth_role_permission` ( permission_id, role_id, created, created_by)
VALUES ( 'read:permission', 'ADMIN', NOW(), 'admin' );

INSERT INTO `auth_role_permission` ( permission_id, role_id, created, created_by)
VALUES ( 'read:permissions', 'ADMIN', NOW(), 'admin' );

INSERT INTO `auth_role_permission` ( permission_id, role_id, created, created_by)
VALUES ( 'create:permission', 'ADMIN', NOW(), 'admin' );

INSERT INTO `auth_role_permission` ( permission_id, role_id, created, created_by)
VALUES ( 'update:permission', 'ADMIN', NOW(), 'admin' );

INSERT INTO `auth_role_permission` ( permission_id, role_id, created, created_by)
VALUES ( 'delete:permission', 'ADMIN', NOW(), 'admin' );

INSERT INTO `auth_role_permission` ( permission_id, role_id, created, created_by)
VALUES ( 'read:client', 'ADMIN', NOW(), 'admin' );

INSERT INTO `auth_role_permission` ( permission_id, role_id, created, created_by)
VALUES ( 'read:clients', 'ADMIN', NOW(), 'admin' );

INSERT INTO `auth_role_permission` ( permission_id, role_id, created, created_by)
VALUES ( 'create:client', 'ADMIN', NOW(), 'admin' );

INSERT INTO `auth_role_permission` ( permission_id, role_id, created, created_by)
VALUES ( 'update:client', 'ADMIN', NOW(), 'admin' );

INSERT INTO `auth_role_permission` ( permission_id, role_id, created, created_by)
VALUES ( 'delete:client', 'ADMIN', NOW(), 'admin' );
