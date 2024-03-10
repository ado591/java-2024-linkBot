--liquibase formatted sql
--changeset ado591:03-create-chatLink.sql
CREATE TABLE IF NOT EXISTS chat_link
(
    chat_id BIGINT NOT NULL REFERENCES chat (id) ON DELETE CASCADE,
    link_id BIGINT NOT NULL REFERENCES link (id) ON DELETE CASCADE

);
