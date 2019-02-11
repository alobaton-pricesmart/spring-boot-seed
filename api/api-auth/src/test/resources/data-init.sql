-- Force mode to MySQL
SET MODE MYSQL;

-- Creates schema PUBLIC if not exists
CREATE SCHEMA IF NOT EXISTS `PUBLIC`;

-- Create JSON type for MySQL compatibility
CREATE DOMAIN IF NOT EXISTS json AS other;