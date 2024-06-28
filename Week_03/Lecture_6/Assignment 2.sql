CREATE DATABASE IF NOT EXISTS fptLectureSQL;

USE fptLectureSQL;
-- drop table customer;
-- drop table cashier;
-- drop table product;
-- drop table invoice;
-- drop table invoiceDetail;
CREATE TABLE IF NOT EXISTS customer (
    id VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    phone VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS cashier (
    id VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS product (
    id VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    price BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS invoice (
    id VARCHAR(255) NOT NULL,
    customerId VARCHAR(255) NOT NULL,
    cashierId VARCHAR(255) NOT NULL,
    amount INTEGER,
    created_date DATETIME,
    PRIMARY KEY (id),
    FOREIGN KEY (customerId) REFERENCES customer(id),
    FOREIGN KEY (cashierId) REFERENCES cashier(id)
);

CREATE TABLE IF NOT EXISTS invoiceDetail (
    id VARCHAR(255) NOT NULL,
    invoiceId VARCHAR(255) NOT NULL,
    productId VARCHAR(255) NOT NULL,
    productPrice BIGINT,
    amount INTEGER,
    quantity INTEGER,
    PRIMARY KEY (id),
    FOREIGN KEY (invoiceId) REFERENCES invoice(id),
    FOREIGN KEY (productId) REFERENCES product(id)
);

INSERT INTO customer (id, name, phone) VALUES
('C001', 'Dodo', '123-456-7890'),
('C002', 'Rindalamn', '987-654-3210'),
('C003', 'Ryan', '555-123-4567'),
('C004', 'Chandra', '111-222-3333'),
('C005', 'Hadi', '444-555-6666');

INSERT INTO cashier (id, name) VALUES
('CA001', 'Grace'),
('CA002', 'Hank');

INSERT INTO product (id, name, price) VALUES
('P001', 'Laptop', 1000000),
('P002', 'Mouse', 20000),
('P003', 'Keyboard', 50000),
('P004', 'Monitor', 150000),
('P005', 'Printer', 300000);

INSERT INTO invoice (id, customerId, cashierId, amount, created_date) VALUES
('I001', 'C001', 'CA001', 1020000, '2024-06-01 10:00:00'),
('I002', 'C002', 'CA002', 45000, '2024-06-02 11:30:00'),
('I003', 'C003', 'CA001', 1050000, '2024-06-03 14:20:00'),
('I004', 'C004', 'CA002', 1000000, '2024-06-04 09:00:00'),
('I005', 'C005', 'CA001', 320000, '2024-05-01 10:00:00'),
('I006', 'C001', 'CA002', 170000, '2024-05-02 11:30:00'),
('I007', 'C002', 'CA001', 1070000, '2024-05-03 14:20:00'),
('I008', 'C003', 'CA002', 1150000, '2024-04-01 09:00:00'),
('I009', 'C004', 'CA001', 350000, '2023-12-01 10:00:00'),
('I010', 'C005', 'CA002', 520000, '2023-11-02 11:30:00');

INSERT INTO invoiceDetail (id, invoiceId, productId, productPrice, amount, quantity) VALUES
('ID001', 'I001', 'P001', 1000000, 1000000, 1),
('ID002', 'I001', 'P002', 20000, 20000, 1),
('ID003', 'I002', 'P003', 45000, 45000, 1),
('ID004', 'I003', 'P004', 150000, 150000, 1),
('ID005', 'I003', 'P001', 1000000, 1000000, 1),
('ID006', 'I004', 'P001', 1000000, 1000000, 1),
('ID007', 'I005', 'P005', 300000, 300000, 1),
('ID008', 'I005', 'P002', 20000, 20000, 1),
('ID009', 'I006', 'P003', 50000, 50000, 1),
('ID010', 'I006', 'P004', 120000, 120000, 1),
('ID011', 'I007', 'P001', 1000000, 1000000, 1),
('ID012', 'I007', 'P003', 70000, 70000, 1),
('ID013', 'I008', 'P001', 1000000, 1000000, 1),
('ID014', 'I008', 'P005', 150000, 150000, 1),
('ID015', 'I009', 'P002', 20000, 20000, 1),
('ID016', 'I009', 'P003', 50000, 50000, 1),
('ID017', 'I010', 'P005', 300000, 300000, 1),
('ID018', 'I010', 'P004', 220000, 220000, 1);


select * from product;
select * from cashier;
select * from invoice;
select * from invoiceDetail;

-- Daily
SELECT DATE(created_date) AS date, SUM(amount) AS daily_revenue
FROM invoice
GROUP BY DATE(created_date);

-- Monthly
SELECT YEAR(created_date) AS year, MONTH(created_date) AS month, SUM(amount) AS monthly_revenue
FROM invoice
GROUP BY YEAR(created_date), MONTH(created_date);


-- Annual
SELECT YEAR(created_date) AS year, SUM(amount) AS annual_revenue
FROM invoice
GROUP BY YEAR(created_date);

CREATE VIEW customer_purchases AS
SELECT
    c.id AS customerID,
    c.name AS customerName,
    p.id AS productID,
    p.name AS productName,
    id.quantity,
    id.amount,
    i.created_date
FROM
    customer c
JOIN
    invoice i ON c.id = i.customerId
JOIN
    invoiceDetail id ON i.id = id.invoiceId
JOIN
    product p ON id.productId = p.id;

SELECT * FROM customer_purchases;

-- Function Revenue
SELECT 
    c.name AS cashierName, 
    sum(i.amount) AS totalAmount
FROM cashier c
Join invoice i ON c.id = i.cashierId
GROUP BY cashierName;



