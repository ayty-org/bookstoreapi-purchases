
DROP TABLE purchase_books_uuid;

INSERT INTO purchases (uuid, client_uuid, amount, purchase_date, is_completed) VALUES ('12d51c0a-a843-46fc-8447-5fda559ec69b', '12d51c0a-a843-46fc-8447-5fda559ec69b', 100.00,'2020-11-14',true);
INSERT INTO purchases (uuid, client_uuid, amount, purchase_date, is_completed) VALUES ('df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14','df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14', 200.00,'2020-11-16',false);
INSERT INTO purchases (uuid, client_uuid, amount, purchase_date, is_completed) VALUES ('27eaa649-e8fa-4889-bd5a-ea6825b71e61','12d51c0a-a843-46fc-8447-5fda559ec69b', 100.00,'2020-11-14',true);
INSERT INTO purchases (uuid, client_uuid, amount, purchase_date, is_completed) VALUES ('27eaa649-e8fa-4889-bd5a-ea6825b71987','12d51c0a-a843-46fc-8447-5fda559ec69b', 100.00,'2020-11-14',true);

CREATE TABLE purchase_books_uuid(id SERIAL8 PRIMARY KEY,books_uuid uuid NOT NULL,purchase_id BIGINT NOT NULL REFERENCES purchases(id));


INSERT INTO purchase_books_uuid(purchase_id, books_uuid) VALUES (1,'12d51c0a-a843-46fc-8447-5fda559ec69b');
INSERT INTO purchase_books_uuid(purchase_id, books_uuid) VALUES (1,'df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14');
INSERT INTO purchase_books_uuid(purchase_id, books_uuid) VALUES (1,'27eaa649-e8fa-4889-bd5a-ea6825b71e61');
INSERT INTO purchase_books_uuid(purchase_id, books_uuid) VALUES (2,'12d51c0a-a843-46fc-8447-5fda559ec69b');
INSERT INTO purchase_books_uuid(purchase_id, books_uuid) VALUES (2,'df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14');
INSERT INTO purchase_books_uuid(purchase_id, books_uuid) VALUES (2,'27eaa649-e8fa-4889-bd5a-ea6825b71e61');
INSERT INTO purchase_books_uuid(purchase_id, books_uuid) VALUES (3,'12d51c0a-a843-46fc-8447-5fda559ec69b');
INSERT INTO purchase_books_uuid(purchase_id, books_uuid) VALUES (3,'df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14');
INSERT INTO purchase_books_uuid(purchase_id, books_uuid) VALUES (3,'27eaa649-e8fa-4889-bd5a-ea6825b71e61');