USE LIBRARY;

DELIMITER &&
CREATE TRIGGER check_book_stock_before_update BEFORE UPDATE ON BOOK FOR EACH ROW    
BEGIN    
	IF(NEW.Stock < 0) THEN
		SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Book stock cannot be negative';
	END IF;
END &&
DELIMITER ;

DELIMITER &&
CREATE TRIGGER auto_order_after_update AFTER UPDATE ON BOOK FOR EACH ROW    
BEGIN    
	# Checks if the update was on stock
    # TODO: Should I Check if there was an order already?
	IF(
		NEW.Stock < NEW.Threshold AND 
        OLD.Stock > NEW.Stock AND
        NOT EXISTS(SELECT 1 FROM LIBRARY_ORDER WHERE ISBN = NEW.ISBN AND Quantity = NEW.Threshold)) 
	THEN
		INSERT INTO LIBRARY_ORDER 
		VALUES (0, NEW.ISBN, NEW.Threshold);
	END IF;
END &&
DELIMITER ;

DELIMITER &&
CREATE TRIGGER update_stock_when_order_confirm BEFORE DELETE ON LIBRARY_ORDER FOR EACH ROW    
BEGIN
	UPDATE BOOK
    SET Stock = Stock + OLD.Quantity
    WHERE ISBN = OLD.ISBN;
END &&
DELIMITER ;

DELIMITER &&
CREATE TRIGGER decrease_stock_when_user_order_placed BEFORE INSERT ON USER_ORDER_BOOKS FOR EACH ROW
BEGIN
	IF EXISTS(
		SELECT 1
        FROM BOOK
        WHERE ISBN = NEW.ISBN AND Stock >= NEW.Quantity)
	THEN
		SET NEW.PRICE_OF_UNIT = (SELECT PRICE FROM BOOK WHERE ISBN = NEW.ISBN);
		UPDATE BOOK
        SET Stock = Stock - NEW.Quantity
        WHERE ISBN = NEW.ISBN;
	ELSE
		SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Not enough book stock';
	END IF;
END &&
DELIMITER ;