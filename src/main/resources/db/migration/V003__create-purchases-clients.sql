CREATE TABLE purchases_clients(
    purchase_id uuid NOT NULL REFERENCES purchases(uuid) PRIMARY KEY,
    client_id uuid NOT NULL REFERENCES clients(uuid)
)