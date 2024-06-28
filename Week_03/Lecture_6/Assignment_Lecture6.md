# Normalization

`Data normalization` is the process or stages in database creation that aim to eliminate redundant/duplicate data and inconsistent data. The rule in data normalization is to divide large tables into smaller tables and connect them using relationships.

`Form data normalization` _Edgar Codd_ proposed the theory of data normalization by introducing the practice of the first normal form, followed by the second and third normal forms (Known as 1NF, 2NF, 3NF).

Example for `database` that need to be normalize

| OrderID | CustomerID | CustomerName | ProductID | ProductName | Quantity | Price | OrderDate  | CustomerAddress      |
| ------- | ---------- | ------------ | --------- | ----------- | -------- | ----- | ---------- | -------------------- |
| 1       | 101        | Alice        | P01       | Laptop      | 1        | 1000  | 2024-06-01 | 123 Apple St, CityA  |
| 2       | 102        | Bob          | P02       | Mouse       | 2        | 25    | 2024-06-02 | 456 Orange St, CityB |

- `1NF` (First Normal Form) : requires that the table have a primary key, and that each column contains atomic, indivisible values. From this definition, out example already satisfied the requirements

- `2NF` (Second Normal Form) : 2NF requires that the table is in 1NF and that all non-key attributes are fully dependent on the primary key. We need to remove partial dependencies. In this case, CustomerName and CustomerAddress depend only on CustomerID, and ProductName depends only on ProductID.

  - `Customers Table`
    | CustomerID | CustomerName | CustomerAddress |
    | ---------- | ------------ | -------------------- |
    | 101 | Alice | 123 Apple St, CityA |
    | 102 | Bob | 456 Orange St, CityB |

  - `Products Table`
    | ProductID | ProductName |
    |-----------|-------------|
    | P01 | Laptop |
    | P02 | Mouse |
    | P03 | Keyboard |

  - `Orders Table`
    | OrderID | CustomerID | ProductID | Quantity | Price | OrderDate |
    |---------|------------|-----------|----------|-------|------------|
    | 1 | 101 | P01 | 1 | 1000 | 2024-06-01 |
    | 2 | 102 | P02 | 2 | 25 | 2024-06-02 |

- `3NF` (Third Normal Form) : 3NF requires that the table is in 2NF and that all non-key attributes are not dependent on any other non-key attributes (no transitive dependencies). The tables created in 2NF already satisfy 3NF, as there are no transitive dependencies. The non-key attributes in each table depend only on the primary key of that table.

By normalizing the original Orders table into three separate tables (Customers, Products, and Orders), we have removed redundancy and potential inconsistencies. Now, each piece of information is stored only once, and the database is easier to maintain.

<br>

# Create a Manage Invoices to See Daily, Monthly, and Annual Revenue

- `Initialize Table`

  - Customer Table: Stores customer information with unique id, name, and phone.

    ```sql
    CREATE TABLE IF NOT EXISTS customer (
    id INT AUTO_INCREMENT,
    name VARCHAR(255),
    phone VARCHAR(255),
    PRIMARY KEY (id)
    );
    ```

    <br>

  - Cashier Table: Stores cashier information with unique id and name.

    ```sql
    CREATE TABLE IF NOT EXISTS cashier (
        id INT AUTO_INCREMENT,
        name VARCHAR(255),
        PRIMARY KEY (id)
    );
    ```

    <br>

  - Product Table: Stores product information with unique id, name, and price.

    ```sql
    CREATE TABLE IF NOT EXISTS product (
        id INT AUTO_INCREMENT,
        name VARCHAR(255),
        price BIGINT,
        PRIMARY KEY (id)
    );
    ```

    <br>

  - Invoice Table: Stores invoice information with unique id, references to customerId and cashierId, total amount, and the created_date.

    ```sql
    CREATE TABLE IF NOT EXISTS invoice (
        id INT AUTO_INCREMENT,
        customerId INT NOT NULL,
        cashierId INT NOT NULL,
        amount BIGINT,
        created_date DATETIME,
        PRIMARY KEY (id),
        FOREIGN KEY (customerId) REFERENCES customer(id),
        FOREIGN KEY (cashierId) REFERENCES cashier(id)
    );
    ```

    <br>

  - Invoice Detail Table: Stores details of each invoice item with unique id, references to invoiceId and productId, the productPrice, amount, and quantity.

    ```sql
    CREATE TABLE IF NOT EXISTS invoiceDetail (
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
    ```

  <br>

- `Inserting Sample Data`

  - Customer Table: Inserted 5 customers.

    ```sql
    INSERT INTO customer (name, phone) VALUES
    ('Dodo', '123-456-7890'),
    ('Rindalamn', '987-654-3210'),
    ('Ryan', '555-123-4567'),
    ('Chandra', '111-222-3333'),
    ('Hadi', '444-555-6666');
    ```

    <br>

  - Cashier Table: Inserted 2 cashiers.

    ```sql
    INSERT INTO cashier (name) VALUES
    ('Grace'),
    ('Hank');
    ```

    <br>

  - Product Table: Inserted 5 products.

    ```sql
    INSERT INTO product (name, price) VALUES
    ('Laptop', 1000000),
    ('Mouse', 20000),
    ('Keyboard', 50000),
    ('Monitor', 150000),
    ('Printer', 300000);
    ```

    <br>

  - Invoice Table: Inserted 10 invoices with varying dates and amounts.

    ```sql
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
    ```

    <br>

  - Invoice Detail Table: Inserted 18 invoice details, each linking invoices to specific products with quantities and prices.

    ```sql
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
    ```

    <br>

- `Revenue`

  - The query selects the date from the created_date column and sums the amount for each day. The results are grouped by date, giving the total revenue for each day.

    ```sql
    SELECT DATE(created_date) AS date, SUM(amount) AS daily_revenue
    FROM invoice
    GROUP BY DATE(created_date);
    ```

  <br>

  - The query selects the year and month from the created_date column and sums the amount for each month. The results are grouped by year and month, giving the total revenue for each month.

    ```sql
    SELECT YEAR(created_date) AS year, MONTH(created_date) AS month, SUM(amount) AS monthly_revenue
    FROM invoice
    GROUP BY YEAR(created_date), MONTH(created_date);
    ```

  <br>

  - The query selects the year from the created_date column and sums the amount for each year. The results are grouped by year, giving the total revenue for each year.

    ```sql
    SELECT YEAR(created_date) AS year, SUM(amount) AS annual_revenue
    FROM invoice
    GROUP BY YEAR(created_date);
    ```

- `Result`

  - Daily Revenue

    ![Alt text](img/2.1.png)

  <br>

  - Monthly Revenue

    ![Alt text](img/2.2.png)

  <br>

  - Annual Revenue

    ![Alt text](img/2.3.png)

<br>

# Create a View of List Product's Customer Bought and Function Calculating Revenue

`A view` in SQL is a virtual table that is stored in the database with an associated name. It is actually a composition of a table in the form of a predefined SQL query. A view can contain rows from an existing table (all or selected). A view can be created from one or many tables. Unless indexed, a view does not exist in a database.

### Syntax

```sql
CREATE VIEW view_name AS
SELECT column1, column2....
FROM table_name
WHERE [condition];
```

On this case we need to show list product's customer that will show customerId, customerName, productId, productName, quantity, amount, and created date Which means we have to use join to show all the columns above,

```sql
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
```

On that query we select all the columns that we need to show. Because we need to show a table that is more than one, we need to use join to make it happen.

- First, we initialize the `VIEW SQL` with the name of view of _customer_puchases_.
  ```sql
  CREATE VIEW customer_purchases AS
  SELECT
      -- c.id AS customerID,
      -- c.name AS customerName,
      -- p.id AS productID,
      -- p.name AS productName,
      -- id.quantity,
      -- id.amount,
      -- i.created_date
  FROM
      customer c
  ```

<br>

- Second, we call all the column from accross the table and use aliases to call the columns such as c.id that calling column id from table customer, p.id calling id from table product, id.quantity calling quantity from table invoiceDetailt. All that aliases will be explained on join query later on.

  ```sql
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
  ```

<br>

- Third, Join Cluses

  - First JOIN clause connects the customer table (aliased as c) with the invoice table (aliased as i). The condition ON c.id = i.customerId specifies that the id column from the customer table must match the customerId column from the invoice table. This join retrieves all invoices associated with each customer.

  - Second JOIN clause connects the invoice table (aliased as i) with the invoiceDetail table (aliased as id). The condition ON i.id = id.invoiceId specifies that the id column from the invoice table must match the invoiceId column from the invoiceDetail table. This join retrieves all details for each invoice, such as individual product purchases.

  - Third JOIN clause connects the invoiceDetail table (aliased as id) with the product table (aliased as p). The condition ON id.productId = p.id specifies that the productId column from the invoiceDetail table must match the id column from the product table. This join retrieves the product information for each item in the invoice details.

    ```sql
    FROM
        customer c
    JOIN
        invoice i ON c.id = i.customerId
    JOIN
        invoiceDetail id ON i.id = id.invoiceId
    JOIN
        product p ON id.productId = p.id;

    ```

Here is the result of that code above

```sql
SELECT * FROM customer_purchases;
```

![Alt text](img/2.4.png)

# Function to Show Revenue by Cashier ID

A Stored Function is a set of SQL statements that perform a specific operation and then return a single value. Similar to built-in functions in MySQL, a stored function can be called from within any MySQL statement. The MySQL CREATE FUNCTION statement is used to create both stored functions and user-defined functions.

Here is given task to create function to show revenue by cashier id and below is the query.

```sql
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
```

- Query above will `SELECT SUM(amount)` AS revenue: This is calculates the sum of the amount column for `invoices` associated with the given `cashier_id` and returns the result directly as revenue.
- FROM invoice WHERE cashierId = cashier_id: This part filters the rows from the invoice table to include only those where the `cashierId` matches the input parameter `cashier_id`.

<br>

## Create table Revenue Report

Create a table that contain id, year, month, day, and amount. Here is the query to create the table `revenue_report`,

```sql
CREATE TABLE revenue_report(
    id INT AUTO_INCREMENT,
    amount BIGINT,
    year INT NOT NULL,
    month INT DEFAULT NULL,
    day INT DEFAULT NULL,
    PRIMARY KEY (id)
);
```

On `revenue_report` we use year, month, and day as an INT where there will be a procedure to take as the parameters that will output for the amount of the revenue based on per day, month, or year. The difference is the year cannot be `NULL` since the year will be used not only in annual, but also in monthly and daily. Thus the year set to be `NOT NULL` whereas the month and day can be `NULL`.

<br>

## Create Store Procedures to Calculate Revenue

In this task we need to make three procedures to calculate revenue based on daily, monthy, ana annual.

- On annual there will be a year in `INT` value as `in parameter` in the procedure.

  Here is the implementations of annual procedure,

  ### Annual

  ```sql
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
  ```

  ### Explanations

  - Declares a variable revenue of type `BIGINT` to store the calculated total revenue.
  - `SELECT` statement calculates the total revenue `(SUM(amount))` for the invoices where the YEAR of `created_date` matches the `year`. The result is stored into the revenue variable.
  - After calculating revenue, this `INSERT` statement inserts a new row into the revenue_report table. It stores revenue in the amount column and year in the year column.

<br>

- On monthly there will be two parameters `INT`, year and monthly in that procedure.

  Here is the implementations of monthly procedure,

  ### Monthly

  ```sql
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
  ```

<br>

- On daily there will be a date in `DATE` value as `in parameter` to take daily in the procedure.

  Here is the implementations of daily procedure,

  ### Daily

  ```sql
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
  ```

After we call all the procedure from those three, the result will look like below,

![Alt text](img/2.5.png)
