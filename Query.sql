-- SQL schema for Online Examination System
-- Database: examdb

CREATE TABLE IF NOT EXISTS candidates (
                                          id          BIGSERIAL PRIMARY KEY,
                                          full_name   VARCHAR(120) NOT NULL,
    email       VARCHAR(120) NOT NULL UNIQUE,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT now()
    );

CREATE TABLE IF NOT EXISTS exams (
                                     id              BIGSERIAL PRIMARY KEY,
                                     title           VARCHAR(160) NOT NULL,
    candidate_id    BIGINT NOT NULL REFERENCES candidates(id) ON DELETE CASCADE,
    status          VARCHAR(30) NOT NULL DEFAULT 'NEW',
    score           INT,
    questions_text  TEXT NOT NULL DEFAULT '',
    created_at      TIMESTAMPTZ NOT NULL DEFAULT now()
    );
