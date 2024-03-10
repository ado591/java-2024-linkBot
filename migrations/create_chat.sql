--liquibase formatted sql
--changeset ado591:01-create_chat.sql
CREATE TABLE IF NOT EXISTS chat
(
    id BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL
);
