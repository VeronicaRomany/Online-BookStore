USE LIBRARY;

DELIMITER &&
CREATE PROCEDURE confirm_library_order(Order_id INT)
BEGIN
	START TRANSACTION;
	DELETE FROM LIBRARY_ORDER
    WHERE ID = Order_id;
    COMMIT;
END &&
DELIMITER ;

DELIMITER &&
CREATE PROCEDURE get_library_orders()
BEGIN
	SELECT *
    FROM LIBRARY_ORDER NATURAL JOIN BOOK;
END &&
DELIMITER ;


DELIMITER &&
CREATE PROCEDURE create_library_order(ISBN CHAR(13), Quantity INT)
BEGIN
	START TRANSACTION;
	INSERT INTO LIBRARY_ORDER(ISBN, Quantity)
    VALUES(ISBN, Quantity);
    COMMIT;
END &&
DELIMITER ;


DELIMITER &&
CREATE PROCEDURE promote_user(
	Manger_Username VARCHAR(20),
    User_Username VARCHAR(20)
)
BEGIN
START TRANSACTION;
IF EXISTS(
	SELECT 1 FROM `USER` 
    WHERE 
		Username = Manger_Username AND
        Is_Manager)
THEN
	UPDATE `USER`
    SET Is_Manager = TRUE
    WHERE Username = User_Username;
    COMMIT;
ELSE
    ROLLBACK;
	SIGNAL SQLSTATE '45000'
	SET MESSAGE_TEXT = 'Incorrect Credinitals';
END IF;
END &&
DELIMITER ;


DELIMITER &&
CREATE PROCEDURE modify_book(
	Old_ISBN CHAR(13),
	New_ISBN CHAR(13),
    New_Title VARCHAR(120),
    New_Publisher VARCHAR(60),
    New_Pub_Year YEAR,
    New_Price INT,
    New_Category ENUM('Science','Art','Religion','History','Geography'),
    New_Stock INT,
    New_Threshold INT,
    New_Image_URL VARCHAR(200)
    )
BEGIN
    START TRANSACTION;
	UPDATE BOOK
    SET 
		ISBN = New_ISBN,
		Title = New_Title,
        Publisher = New_Publisher,
        Pub_Year = New_Pub_Year,
        Price = New_Price,
        Category = New_Category,
        Stock = New_Stock,
        Threshold = New_Threshold,
        Image_URL = New_Image_URL
	WHERE
		ISBN = Old_ISBN;
	DELETE FROM AUTHOR WHERE ISBN = Old_ISBN;
    COMMIT;
END &&
DELIMITER ;

DELIMITER &&
CREATE PROCEDURE add_publisher(
	`Name` VARCHAR(60),
    Address VARCHAR(120),
    Phone VARCHAR(16)
    )
BEGIN
    START TRANSACTION;
	INSERT INTO Publisher
    VALUES(`New_Name`, Address, Phone);
    COMMIT;
END &&
DELIMITER ;


DELIMITER &&
CREATE PROCEDURE add_book(
	ISBN CHAR(13),
    Title VARCHAR(120),
    Publisher VARCHAR(60),
    Pub_Year YEAR,
    Price INT,
    Category ENUM('Science','Art','Religion','History','Geography'),
    Stock INT,
    Threshold INT,
    Image_URL VARCHAR(200)
    )
BEGIN
	START TRANSACTION;
	INSERT INTO BOOK
    VALUES (ISBN, Title, Publisher, Pub_Year, Price, Category, Stock, Threshold, Image_URL);
    COMMIT;
END &&
DELIMITER ;

DELIMITER &&
CREATE PROCEDURE add_author(
	ISBN CHAR(13),
    `Name` VARCHAR(60)
    )
BEGIN
	START TRANSACTION;
	INSERT INTO AUTHOR
    VALUES (`Name`, ISBN);
    COMMIT;
END &&
DELIMITER ;

DELIMITER &&
CREATE PROCEDURE get_book_info(
	_ISBN CHAR(13)
    )
BEGIN
	SELECT * FROM BOOK WHERE ISBN = _ISBN;
END &&
DELIMITER ;

DELIMITER &&
CREATE PROCEDURE get_book_authors(
	_ISBN CHAR(13)
    )
BEGIN
	SELECT `Name` FROM AUTHOR WHERE ISBN = _ISBN;
END &&
DELIMITER ;