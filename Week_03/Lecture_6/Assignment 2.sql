CREATE DATABASE IF NOT EXISTS fptLectureSQL;

USE fptLectureSQL;
DROP TABLE IF EXISTS invoiceDetail, invoice, product, cashier, customer, revenue_report;
CREATE TABLE customer (
    id INT AUTO_INCREMENT,
    name VARCHAR(255),
    phone VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE cashier (
    id INT AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE product (
    id INT AUTO_INCREMENT,
    name VARCHAR(255),
    price BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE invoice (
    id INT AUTO_INCREMENT,
    customerId INT NOT NULL,
    cashierId INT NOT NULL,
    amount BIGINT,
    created_date DATETIME,
    PRIMARY KEY (id),
    FOREIGN KEY (customerId) REFERENCES customer(id),
    FOREIGN KEY (cashierId) REFERENCES cashier(id)
);

CREATE TABLE invoiceDetail (
    id INT AUTO_INCREMENT,
    invoiceId INT NOT NULL,
    productId INT NOT NULL,
    productPrice BIGINT,
    quantity INT,
    amount BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (invoiceId) REFERENCES invoice(id),
    FOREIGN KEY (productId) REFERENCES product(id)
);

CREATE TABLE revenue_report(
    id INT AUTO_INCREMENT,
    amount BIGINT,
    year INT NOT NULL,
    month INT DEFAULT NULL,
    day INT DEFAULT NULL,
    PRIMARY KEY (id)
);

INSERT INTO customer (name, phone) VALUES
('Dodo', '123-456-7890'),
('Rindalamn', '987-654-3210'),
('Ryan', '555-123-4567'),
('Chandra', '111-222-3333'),
('Hadi', '444-555-6666');

INSERT INTO cashier (name) VALUES
('Grace'),
('Hank');

INSERT INTO product (name, price) VALUES
('Laptop', 1000000),
('Mouse', 20000),
('Keyboard', 50000),
('Monitor', 150000),
('Printer', 300000);

INSERT INTO invoice (customerId, cashierId, amount, created_date) VALUES
(1, 1, 1020000, '2024-06-01 10:00:00'),
(2, 2, 45000, '2024-06-02 11:30:00'),
(3, 1, 1050000, '2024-06-03 14:20:00'),
(4, 2, 1000000, '2024-06-04 09:00:00'),
(5, 1, 320000, '2024-05-01 10:00:00'),
(1, 2, 170000, '2024-05-02 11:30:00'),
(2, 1, 1070000, '2024-05-03 14:20:00'),
(3, 2, 1150000, '2024-04-01 09:00:00'),
(4, 1, 350000, '2023-12-01 10:00:00'),
(5, 2, 520000, '2023-11-02 11:30:00');

INSERT INTO invoiceDetail (invoiceId, productId, productPrice, quantity, amount) VALUES
(1, 1, 1000000, 1, 1000000),
(1, 2, 20000, 1, 20000),
(2, 3, 45000, 1, 45000),
(3, 4, 150000, 1, 150000),
(3, 1, 1000000, 1, 1000000),
(4, 1, 1000000, 1, 1000000),
(5, 5, 300000, 1, 300000),
(5, 2, 20000, 1, 20000),
(6, 3, 50000, 1, 50000),
(6, 4, 120000, 1, 120000),
(7, 1, 1000000, 1, 1000000),
(7, 3, 70000, 1, 70000),
(8, 1, 1000000, 1, 1000000),
(8, 5, 150000, 1, 150000),
(9, 2, 20000, 1, 20000),
(9, 3, 50000, 1, 50000),
(10, 5, 300000, 1, 300000),
(10, 4, 220000, 1, 220000);



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
DELIMITER $$

CREATE PROCEDURE revenueByCashier(IN cashier_id INT)
BEGIN
    SELECT SUM(amount) AS revenue
    FROM invoice
    WHERE cashierId = cashier_id;
END$$

DELIMITER ;

CALL revenueByCashier(1);
CALL revenueByCashier(2);

-- Create a function to store revenue by year, 
DROP PROCEDURE IF EXISTS revenueByYear;

DELIMITER $$
CREATE PROCEDURE revenueByYear(IN year INT)
BEGIN
	DECLARE revenue BIGINT;
    SELECT SUM(amount) INTO revenue
    FROM invoice
    WHERE year = YEAR(created_date);
    
    INSERT INTO revenue_report(amount, year)
    VALUES (revenue, year);
END$$
DELIMITER ;

CALL revenueByYear(2024);
CALL revenueByYear(2023);

SELECT * FROM revenue_report;

-- Create a function to store revenue by month,
DROP PROCEDURE IF EXISTS revenueByMonth;

DELIMITER $$
CREATE PROCEDURE revenueByMonth(IN month INT, year INT)
BEGIN
	DECLARE revenue BIGINT;
    SELECT SUM(amount) INTO revenue
    FROM invoice
    WHERE (month = MONTH(created_date)) AND (year = YEAR(created_date));
	
	INSERT INTO revenue_report(amount, year, month)
    VALUES (revenue, year, month);
END$$
DELIMITER ;

CALL revenueByMonth(5, 2024);
CALL revenueByMonth(6, 2024);
CALL revenueByMonth(11, 2023);
    
SELECT * FROM revenue_report;

-- Create a function to store revenue by day,
DROP PROCEDURE IF EXISTS revenueByDay; 

DELIMITER $$
CREATE PROCEDURE revenueByDay(IN date DATE)
BEGIN
	DECLARE revenue BIGINT;
    SELECT SUM(amount) INTO revenue
    FROM invoice
    WHERE date = DATE(created_date);
    
	INSERT INTO revenue_report(amount, year, month, day)
    VALUES (revenue, YEAR(date), MONTH(date), DAY(date));
END$$
DELIMITER ;

CALL revenueByDay('2024-06-01');
CALL revenueByDay('2024-06-02');
CALL revenueByDay('2024-06-03');
CALL revenueByDay('2024-06-04');

SELECT * FROM revenue_report;




