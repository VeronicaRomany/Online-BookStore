USE LIBRARY;

DELIMITER &&
CREATE PROCEDURE search_user(
	_Username VARCHAR(20),
    _Order ENUM('A', 'D'),
    page_no INT,	-- starts from 1
    count_in_page INT
    )
BEGIN
	DECLARE start_index INT;
	DECLARE pattern VARCHAR(16);
    SET start_index = (page_no - 1) * count_in_page;
    SET pattern = CONCAT("%", _Username, "%");
	SELECT Username, FName, LName, Email, Phone, Is_Manager
    FROM `USER`
    WHERE Username LIKE pattern
    ORDER BY 
		(CASE WHEN _Order = 'A' THEN Username END) ASC,
        (CASE WHEN _Order = 'D' THEN Username END) DESC
	LIMIT start_index,count_in_page;
END &&
DELIMITER ;

DELIMITER &&
CREATE PROCEDURE search_book(
	_ISBN VARCHAR(13),
    _Title VARCHAR(120),
    _Publisher VARCHAR(60),
    _Pub_Year YEAR,
    _Price INT,
    _Category ENUM('Science','Art','Religion','History','Geography'),
    _Stock INT,
    _Author_name VARCHAR(60),
    page_no INT,	-- starts from 1
    count_in_page INT
    )
BEGIN
	DECLARE start_index INT;
    SET start_index = (page_no - 1) * count_in_page;
	SELECT *
    FROM BOOK
    WHERE
		(CASE 
			WHEN _Author_name IS NULL THEN TRUE
			ELSE ISBN IN (SELECT ISBN FROM AUTHOR WHERE `Name` LIKE CONCAT("%", _Author_name, "%"))
		END)
        AND
        (CASE
			WHEN _ISBN IS NULL THEN TRUE
            ELSE ISBN LIKE CONCAT("%", _ISBN, "%")
		END)
		AND
        (CASE
			WHEN _Title IS NULL THEN TRUE
            ELSE Title LIKE CONCAT("%", _Title, "%")
		END)
		AND
        (CASE
			WHEN _Publisher IS NULL THEN TRUE
            ELSE Publisher LIKE CONCAT("%", _Publisher, "%") 
		END)
        AND
        (CASE
			WHEN _Pub_Year IS NULL THEN TRUE
            ELSE Pub_Year = _Pub_Year
		END)
        AND
        (CASE
			WHEN _Price IS NULL THEN TRUE
            ELSE Price = _Price
		END)
        AND
        (CASE
			WHEN _Category IS NULL THEN TRUE
            ELSE Category = _Category
		END)
        AND
        (CASE
			WHEN _Stock IS NULL THEN TRUE
            ELSE Stock = _Stock
		END)
        LIMIT start_index,count_in_page
        ;
END &&
DELIMITER ;


DELIMITER &&
CREATE PROCEDURE search_book_by_isbn(
	_ISBN VARCHAR(13),
    _Order ENUM('A', 'D'),
    page_no INT,	-- starts from 1
    count_in_page INT
    )
BEGIN
	DECLARE start_index INT;
	DECLARE pattern VARCHAR(16);
    SET start_index = (page_no - 1) * count_in_page;
    SET pattern = CONCAT("%", _ISBN, "%");
	SELECT *
    FROM BOOK
    WHERE ISBN LIKE pattern
    ORDER BY 
		(CASE WHEN _Order = 'A' THEN ISBN END) ASC,
        (CASE WHEN _Order = 'D' THEN ISBN END) DESC
	LIMIT start_index,count_in_page;
END &&
DELIMITER ;


DELIMITER &&
CREATE PROCEDURE search_book_by_title(
	_Title VARCHAR(120),
    _Order ENUM('A', 'D'),
    page_no INT,	-- starts from 1
    count_in_page INT
    )
BEGIN
	DECLARE start_index INT;
	DECLARE pattern VARCHAR(122);
    SET start_index = (page_no - 1) * count_in_page;
    SET pattern = CONCAT("%", _Title, "%");
	SELECT *
    FROM BOOK
    WHERE Title LIKE pattern
    ORDER BY 
		(CASE WHEN _Order = 'A' THEN Title END) ASC,
        (CASE WHEN _Order = 'D' THEN Title END) DESC
	LIMIT start_index,count_in_page;
END &&
DELIMITER ;


DELIMITER &&
CREATE PROCEDURE search_book_by_publisher(
	_Publisher VARCHAR(60),
    _Order ENUM('A', 'D'),
    page_no INT,	-- starts from 1
    count_in_page INT
    )
BEGIN
	DECLARE start_index INT;
	DECLARE pattern VARCHAR(62);
    SET start_index = (page_no - 1) * count_in_page;
    SET pattern = CONCAT("%", _Publisher, "%");
	SELECT *
    FROM BOOK
    WHERE Publisher LIKE pattern
    ORDER BY 
		(CASE WHEN _Order = 'A' THEN Publisher END) ASC,
        (CASE WHEN _Order = 'D' THEN Publisher END) DESC
	LIMIT start_index,count_in_page;
END &&
DELIMITER ;


DELIMITER &&
CREATE PROCEDURE search_book_by_pub_year(
	_Pub_Year YEAR,
    _Method ENUM('=','>=','<='),
    _Order ENUM('A', 'D'),
    page_no INT,	-- starts from 1
    count_in_page INT
    )
BEGIN
	DECLARE start_index INT;
    SET start_index = (page_no - 1) * count_in_page;
	CASE
		WHEN _Method = '=' THEN
			(SELECT *
			FROM BOOK
			WHERE Pub_Year = _Pub_Year)
            ORDER BY 
				(CASE WHEN _Order = 'A' THEN Pub_Year END) ASC,
				(CASE WHEN _Order = 'D' THEN Pub_Year END) DESC
			LIMIT start_index,count_in_page;
		WHEN _Method = '>=' THEN
			(SELECT *
			FROM BOOK
			WHERE Pub_Year >= _Pub_Year)
            ORDER BY 
				(CASE WHEN _Order = 'A' THEN Pub_Year END) ASC,
				(CASE WHEN _Order = 'D' THEN Pub_Year END) DESC
			LIMIT start_index,count_in_page;
		WHEN _Method = '<=' THEN
			(SELECT *
			FROM BOOK
			WHERE Pub_Year <= _Pub_Year)
            ORDER BY 
				(CASE WHEN _Order = 'A' THEN Pub_Year END) ASC,
				(CASE WHEN _Order = 'D' THEN Pub_Year END) DESC
			LIMIT start_index,count_in_page;
	END CASE;
END &&
DELIMITER ;


DELIMITER &&
CREATE PROCEDURE search_book_by_price(
	_Price INT,
    _Method ENUM('=','>=','<='),
    _Order ENUM('A', 'D'),
    page_no INT,	-- starts from 1
    count_in_page INT
    )
BEGIN
	DECLARE start_index INT;
    SET start_index = (page_no - 1) * count_in_page;
	CASE
		WHEN _Method = '=' THEN
			(SELECT *
			FROM BOOK
			WHERE Price = _Price)
            ORDER BY 
				(CASE WHEN _Order = 'A' THEN Pub_Year END) ASC,
				(CASE WHEN _Order = 'D' THEN Pub_Year END) DESC
			LIMIT start_index,count_in_page;
		WHEN _Method = '>=' THEN
			(SELECT *
			FROM BOOK
			WHERE Price >= _Price)
            ORDER BY 
				(CASE WHEN _Order = 'A' THEN Pub_Year END) ASC,
				(CASE WHEN _Order = 'D' THEN Pub_Year END) DESC
			LIMIT start_index,count_in_page;
		WHEN _Method = '<=' THEN
			(SELECT *
			FROM BOOK
			WHERE Price <= _Price)
            ORDER BY 
				(CASE WHEN _Order = 'A' THEN Pub_Year END) ASC,
				(CASE WHEN _Order = 'D' THEN Pub_Year END) DESC
			LIMIT start_index,count_in_page;
	END CASE;
END &&
DELIMITER ;


DELIMITER &&
CREATE PROCEDURE search_book_by_stock(
	_Stock INT,
    _Method ENUM('=','>=','<='),
    _Order ENUM('A', 'D'),
    page_no INT,	-- starts from 1
    count_in_page INT
    )
BEGIN
	DECLARE start_index INT;
    SET start_index = (page_no - 1) * count_in_page;
	CASE
		WHEN _Method = '=' THEN
			(SELECT *
			FROM BOOK
			WHERE Stock = _Stock)
            ORDER BY 
				(CASE WHEN _Order = 'A' THEN Pub_Year END) ASC,
				(CASE WHEN _Order = 'D' THEN Pub_Year END) DESC
			LIMIT start_index,count_in_page;
		WHEN _Method = '>=' THEN
			(SELECT *
			FROM BOOK
			WHERE Stock >= _Stock)
            ORDER BY 
				(CASE WHEN _Order = 'A' THEN Pub_Year END) ASC,
				(CASE WHEN _Order = 'D' THEN Pub_Year END) DESC
			LIMIT start_index,count_in_page;
		WHEN _Method = '<=' THEN
			(SELECT *
			FROM BOOK
			WHERE Stock <= _Stock)
            ORDER BY 
				(CASE WHEN _Order = 'A' THEN Pub_Year END) ASC,
				(CASE WHEN _Order = 'D' THEN Pub_Year END) DESC
			LIMIT start_index,count_in_page;
	END CASE;
END &&
DELIMITER ;


DELIMITER &&
CREATE PROCEDURE search_book_by_category(
	_Category ENUM('Science','Art','Religion','History','Geography'),
    page_no INT,	-- starts from 1
    count_in_page INT
    )
BEGIN
	DECLARE start_index INT;
    SET start_index = (page_no - 1) * count_in_page;
	SELECT *
    FROM BOOK
    WHERE Category = _Category
	LIMIT start_index,count_in_page;
END &&
DELIMITER ;


DELIMITER &&
CREATE PROCEDURE search_book_by_author(
	_Author_name VARCHAR(60),
    page_no INT,	-- starts from 1
    count_in_page INT
    )
BEGIN
	DECLARE start_index INT;
	DECLARE pattern VARCHAR(62);
    SET start_index = (page_no - 1) * count_in_page;
    SET pattern = CONCAT("%", _Author_name, "%");
	SELECT *
    FROM BOOK NATURAL JOIN AUTHOR
    WHERE `Name` LIKE pattern
	LIMIT start_index,count_in_page;
END &&
DELIMITER ;