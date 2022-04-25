CREATE TABLE purchases(
    id SERIAL8 PRIMARY KEY ,
    uuid uuid UNIQUE NOT NULL DEFAULT uuid_generate_v4(),
    client_id uuid NOT NULL REFERENCES clients(uuid),
    amount DOUBLE PRECISION NOT NULL,
    purchase_date TIMESTAMP NOT NULL,
    is_completed BOOLEAN NOT NULL
);