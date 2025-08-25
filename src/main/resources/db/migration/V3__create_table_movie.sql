CREATE TABLE movie(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    release_date DATE,
    rating NUMERIC,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP
)