USE LIBRARY;

DELIMITER &&
CREATE PROCEDURE modify_user(
    Old_Username VARCHAR(20),
	New_Username VARCHAR(20),
    New_Password CHAR(60),
    New_FName VARCHAR(30),
    New_LNAME VARCHAR(30),
    New_Email VARCHAR(30),
    New_Phone VARCHAR(16),
    New_Address VARCHAR(120)
    )
BEGIN
	START TRANSACTION;
	UPDATE `USER`
    SET 
		Username = New_Username,
        `Password` = New_Password,
        FName = New_FName,
        LNAME = New_LNAME,
        Email = New_Email,
        Phone = New_Phone,
        Address = New_Address
	WHERE
		Username = Old_Username;
	COMMIT;
END &&
DELIMITER ;


DELIMITER &&
CREATE PROCEDURE create_user_order(
	_Username VARCHAR(20)
    )
BEGIN
	START TRANSACTION;
    INSERT INTO USER_ORDER VALUES (0, _Username);
    SELECT LAST_INSERT_ID();
END &&
DELIMITER ;

DELIMITER &&
CREATE PROCEDURE add_book_to_user_order(
	_Order_Id INT,
    _ISBN CHAR(13),
    _Quantity INT
)
BEGIN
	INSERT INTO USER_ORDER_BOOKS
    VALUES(_Order_Id, _ISBN, _Quantity);
END &&
DELIMITER ;


DELIMITER &&
CREATE PROCEDURE verify_user_order_info(
	_Order_Id VARCHAR(20),
    _Credit_Card_No CHAR(16),
    _CVC CHAR(3),
    _Expiry_Date DATE
    )
BEGIN
	DECLARE sum INT;
    
    SELECT SUM(Price*Quantity)
    FROM BOOK NATURAL JOIN USER_ORDER NATURAL JOIN USER_ORDER_BOOKS
    INTO sum;
    IF NOT EXISTS(
		SELECT 1
        FROM CREDIT_CARDS
        WHERE 
			Credit_Card_No = _Credit_Card_No AND
			CVC =  _CVC AND
            MONTH(Expiry_Date) = MONTH(_Expiry_Date) AND
            YEAR(Expiry_Date) = YEAR(_Expiry_Date) AND
            Balance >= sum)
	THEN
		ROLLBACK;
        SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Incorrect Credinitals';
	ELSE
		UPDATE CREDIT_CARDS
        SET Balance = Balance - sum
        WHERE Credit_Card_No = _Credit_Card_No;
		COMMIT;
	END IF;
END &&
DELIMITER ;