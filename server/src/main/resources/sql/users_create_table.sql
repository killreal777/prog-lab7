CREATE TABLE IF NOT EXISTS users (
    user_name      VARCHAR(32)  UNIQUE PRIMARY KEY,
    user_password  VARCHAR(128)
);
