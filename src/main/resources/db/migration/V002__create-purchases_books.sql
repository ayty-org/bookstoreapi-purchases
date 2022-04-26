CREATE TABLE purchase_books_uuid(
    id SERIAL8 PRIMARY KEY,

    books_uuid uuid NOT NULL,
    purchase_id BIGINT NOT NULL REFERENCES purchases(id)
)