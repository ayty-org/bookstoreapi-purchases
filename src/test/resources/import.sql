DROP TABLE books_categories;
DROP TABLE purchases_purchased_books;

INSERT INTO categories (name) VALUES('Romance');
INSERT INTO categories (name) VALUES('Action');
INSERT INTO categories (name) VALUES('Fantasy');

INSERT INTO clients (uuid, name, age, telephone, email, gender) VALUES ('12d51c0a-a843-46fc-8447-5fda559ec69b','Jenipapo', 19, '83996438691','jenipapo@coldmail.com','Male');
INSERT INTO clients (uuid, name, age, telephone, email, gender) VALUES ('df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14','Ana', 46,  '83996438691', 'ana@coldmail.com','Female');


INSERT INTO books (uuid, title, synopsis, isbn, publication_year, price, quantity_in_stock, author_name) VALUES ('12d51c0a-a843-46fc-8447-5fda559ec69b','JavaScript', 'Aprenda JavaScript', '9788533302273', '2001-03-14', 50.00, 23, 'JN Papo');
INSERT INTO books (uuid, title, synopsis, isbn, publication_year, price, quantity_in_stock, author_name) VALUES ('df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14','Angular JS', 'Aprenda a primeira versão do Angular', '9788533302273', '2000-04-15', 80.00, 4, 'Gu Gou');
INSERT INTO books (uuid, title, synopsis, isbn, publication_year, price, quantity_in_stock, author_name) VALUES ('27eaa649-e8fa-4889-bd5a-ea6825b71e61','Algoritmos', 'Entenda lógica de programação', '9788533302273', '2000-04-30', 100.00, 23, 'JN Papo');


CREATE TABLE books_categories(book_id uuid NOT NULL REFERENCES books(uuid),category_id BIGINT NOT NULL REFERENCES categories(id),PRIMARY KEY (book_id, category_id))

INSERT INTO books_categories (book_id, category_id) VALUES ('12d51c0a-a843-46fc-8447-5fda559ec69b',1);
INSERT INTO books_categories (book_id, category_id) VALUES ('12d51c0a-a843-46fc-8447-5fda559ec69b',2);
INSERT INTO books_categories (book_id, category_id) VALUES ('12d51c0a-a843-46fc-8447-5fda559ec69b',3);
INSERT INTO books_categories (book_id, category_id) VALUES ('df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14',1);
INSERT INTO books_categories (book_id, category_id) VALUES ('df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14',2);
INSERT INTO books_categories (book_id, category_id) VALUES ('df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14',3);
INSERT INTO books_categories (book_id, category_id) VALUES ('27eaa649-e8fa-4889-bd5a-ea6825b71e61',1);
INSERT INTO books_categories (book_id, category_id) VALUES ('27eaa649-e8fa-4889-bd5a-ea6825b71e61',2);
INSERT INTO books_categories (book_id, category_id) VALUES ('27eaa649-e8fa-4889-bd5a-ea6825b71e61',3);

CREATE TABLE purchases_purchased_books(id SERIAL8 PRIMARY KEY, book_id uuid NOT NULL REFERENCES books(uuid),purchase_id uuid NOT NULL REFERENCES purchases(uuid))

INSERT INTO purchases (uuid, client_id, amount, purchase_date, is_completed) VALUES ('12d51c0a-a843-46fc-8447-5fda559ec69b', '12d51c0a-a843-46fc-8447-5fda559ec69b', 100.00,'2020-11-14',true);
INSERT INTO purchases (uuid, client_id, amount, purchase_date, is_completed) VALUES ('df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14','df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14', 200.00,'2020-11-16',false);
INSERT INTO purchases (uuid, client_id, amount, purchase_date, is_completed) VALUES ('27eaa649-e8fa-4889-bd5a-ea6825b71e61','12d51c0a-a843-46fc-8447-5fda559ec69b', 100.00,'2020-11-14',true);
INSERT INTO purchases (uuid, client_id, amount, purchase_date, is_completed) VALUES ('27eaa649-e8fa-4889-bd5a-ea6825b71987','12d51c0a-a843-46fc-8447-5fda559ec69b', 100.00,'2020-11-14',true);


INSERT INTO purchases_purchased_books(purchase_id, book_id) VALUES ('12d51c0a-a843-46fc-8447-5fda559ec69b','12d51c0a-a843-46fc-8447-5fda559ec69b');
INSERT INTO purchases_purchased_books(purchase_id, book_id) VALUES ('12d51c0a-a843-46fc-8447-5fda559ec69b','df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14');
INSERT INTO purchases_purchased_books(purchase_id, book_id) VALUES ('12d51c0a-a843-46fc-8447-5fda559ec69b','27eaa649-e8fa-4889-bd5a-ea6825b71e61');
INSERT INTO purchases_purchased_books(purchase_id, book_id) VALUES ('df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14','12d51c0a-a843-46fc-8447-5fda559ec69b');
INSERT INTO purchases_purchased_books(purchase_id, book_id) VALUES ('df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14','df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14');
INSERT INTO purchases_purchased_books(purchase_id, book_id) VALUES ('df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14','27eaa649-e8fa-4889-bd5a-ea6825b71e61');
INSERT INTO purchases_purchased_books(purchase_id, book_id) VALUES ('27eaa649-e8fa-4889-bd5a-ea6825b71e61','12d51c0a-a843-46fc-8447-5fda559ec69b');
INSERT INTO purchases_purchased_books(purchase_id, book_id) VALUES ('27eaa649-e8fa-4889-bd5a-ea6825b71e61','df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14');
INSERT INTO purchases_purchased_books(purchase_id, book_id) VALUES ('27eaa649-e8fa-4889-bd5a-ea6825b71e61','27eaa649-e8fa-4889-bd5a-ea6825b71e61');