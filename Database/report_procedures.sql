USE LIBRARY;

DROP PROCEDURE IF EXISTS get_sales_report;
DROP PROCEDURE IF EXISTS get_top5_customers;
DROP PROCEDURE IF EXISTS get_top_selling_books;

DELIMITER &&
CREATE PROCEDURE get_sales_report()
BEGIN
    SELECT DAY(uo.dt) as day, MONTH(uo.dt) as month, COUNT(*) as 'count', SUM(uob.PRICE_OF_UNIT * uob.QUANTITY) as total
    FROM USER_ORDER as uo
    NATURAL JOIN USER_ORDER_BOOKS as uob
    WHERE uo.dt > (CURDATE() - INTERVAL 1 MONTH)
    GROUP BY DAY(uo.dt);
END &&
DELIMITER ;


DELIMITER &&
CREATE PROCEDURE get_top5_customers()
BEGIN
    SELECT uo.USERNAME as username, SUM(uob.PRICE_OF_UNIT * uob.QUANTITY) as amount
    FROM USER_ORDER as uo
    NATURAL JOIN USER_ORDER_BOOKS as uob
    WHERE uo.dt > (CURDATE() - INTERVAL 3 MONTH)
    GROUP BY uo.USERNAME
    ORDER BY amount DESC 
    LIMIT 5;
END &&
DELIMITER ;


DELIMITER &&
CREATE PROCEDURE get_top_selling_books()
BEGIN
    SELECT b.ISBN as isbn, b.TITLE as title, SUM(uob.QUANTITY) as amount
    FROM BOOK as b
    NATURAL JOIN USER_ORDER_BOOKS as uob
    NATURAL JOIN USER_ORDER as uo
    WHERE uo.dt > (CURDATE() - INTERVAL 3 MONTH)
    GROUP BY b.ISBN
    ORDER BY amount DESC
    LIMIT 10;
END &&
DELIMITER ;



call get_sales_report();

call get_top5_customers();

call get_top_selling_books();