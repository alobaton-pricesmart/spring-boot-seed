CREATE TABLE IF NOT EXISTS auth_client_details(
     client_id VARCHAR(256),
     resource_ids JSON NOT NULL,
     secret_required BOOLEAN,
     client_secret VARCHAR(256),
     scoped BOOLEAN,
     scope JSON,
     authorized_grant_types JSON,
     registered_redirect_uri JSON,
     authorities JSON,
     access_token_validity_seconds INT,
     refresh_token_validity_seconds INT,
     auto_approve BOOLEAN,
     additional_information JSON,
     created DATETIME,
     last_modified DATETIME,
     PRIMARY KEY (client_id)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS auth_password_token (
	nickname VARCHAR(256),
	token VARCHAR(256),
	expires_at DATETIME,
	CONSTRAINT id PRIMARY KEY (nickname, token)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS auth_role (
	id VARCHAR(256),
	name JSON,
	description JSON,
	parent_id VARCHAR(256),
	`view` VARCHAR(256),
	`create` VARCHAR(256),
	`edit` VARCHAR(256),
	`remove` VARCHAR(256),
	created DATETIME,
    last_modified DATETIME,
	PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS auth_user(
     nickname VARCHAR(256),
     name VARCHAR(256) NOT NULL,
     last_name VARCHAR(256) NOT NULL,
     email VARCHAR(256) NOT NULL,
     password VARCHAR(256) NOT NULL,
     roles JSON NOT NULL,
     locked BOOLEAN,
     enabled BOOLEAN DEFAULT true,
     created DATETIME,
     last_modified DATETIME,
     PRIMARY KEY (nickname)
) ENGINE=INNODB;

