-- Force MySQL Mode
SET MODE MYSQL;

-- Create JSON type for MySQL compatibility
CREATE DOMAIN IF NOT EXISTS json AS other;