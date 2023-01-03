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
	IF(NEW.Stock < NEW.Threshold AND OLD.Stock > NEW.Stock) THEN
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