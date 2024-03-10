--liquibase formatted sql
--changeset ado591:02-create_link.sql
CREATE TABLE IF NOT EXISTS link
(
    id BIGINT PRIMARY KEY,
    url VARCHAR(255) UNIQUE NOT NULL,
    service_name VARCHAR(255) NOT NULL,
    last_check_time TIMESTAMP WITH TIME ZONE NOT NULL

);
