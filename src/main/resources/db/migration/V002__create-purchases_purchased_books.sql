CREATE TABLE purchases_purchased_books(
    id SERIAL8 PRIMARY KEY,

    book_id uuid NOT NULL REFERENCES books(uuid),
    purchase_id uuid NOT NULL REFERENCES purchases(uuid)
)