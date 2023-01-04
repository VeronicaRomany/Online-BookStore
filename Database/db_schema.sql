DROP SCHEMA IF EXISTS LIBRARY;
CREATE SCHEMA LIBRARY;

USE LIBRARY;

CREATE TABLE Publisher (
	`Name` VARCHAR(60) PRIMARY KEY NOT NULL,
    Address VARCHAR(120) NOT NULL,
    Phone VARCHAR(16) NOT NULL
);

CREATE TABLE BOOK (
	ISBN CHAR(13) PRIMARY KEY NOT NULL,
    Title VARCHAR(120) NOT NULL,
    Publisher VARCHAR(60),
    Pub_Year YEAR NOT NULL,
    Price INT NOT NULL,
    Category ENUM('Science','Art','Religion','History','Geography'),
    Stock INT,
    Threshold INT,
    Image_URL VARCHAR(200),
    CONSTRAINT FK_BOOK_Publisher FOREIGN KEY (Publisher) REFERENCES Publisher(`Name`) ON DELETE SET NULL
);

CREATE TABLE AUTHOR (
	`Name` VARCHAR(60) NOT NULL,
    ISBN CHAR(13) NOT NULL,
    PRIMARY KEY(`Name`, ISBN),
    CONSTRAINT FK_AUTHOR_ISBN FOREIGN KEY (ISBN) REFERENCES BOOK(ISBN) ON DELETE CASCADE
);

CREATE TABLE LIBRARY_ORDER (
	ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    ISBN CHAR(13) NOT NULL,
    Quantity INT NOT NULL,
    CONSTRAINT FK_LIBRARY_ORDER_ISBN FOREIGN KEY (ISBN) REFERENCES BOOK(ISBN) ON DELETE CASCADE
);

CREATE TABLE `USER` (
	Username VARCHAR(20) PRIMARY KEY NOT NULL,
    `Password` CHAR(60) NOT NULL,
    FName VARCHAR(30) NOT NULL,
    LName VARCHAR(30) NOT NULL,
    Email VARCHAR(30) UNIQUE NOT NULL,
    Phone VARCHAR(16) UNIQUE NOT NULL,
    Address VARCHAR(120) NOT NULL,
    Is_Manager BOOL NOT NULL
);

CREATE TABLE USER_ORDER (
	Id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    Username VARCHAR(20) NOT NULL,
    TOTAL DOUBLE NOT NULL,
    dt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT FK_USER_ORDER_Username FOREIGN KEY (Username) REFERENCES `USER`(Username) ON DELETE CASCADE
);

CREATE TABLE USER_ORDER_BOOKS (
	Id INT NOT NULL,
    ISBN CHAR(13) NOT NULL,
    Quantity INT DEFAULT 1,
    PRICE_OF_UNIT DOUBLE DEFAULT 0,
    CONSTRAINT PK PRIMARY KEY(Id, ISBN),
    CONSTRAINT FK_USER_ORDER_BOOKS_Id FOREIGN KEY (Id) REFERENCES USER_ORDER(Id) ON DELETE CASCADE,
    CONSTRAINT FK_USER_ORDER_BOOKS_ISBN FOREIGN KEY (ISBN) REFERENCES BOOK(ISBN) ON DELETE CASCADE
);

CREATE TABLE CREDIT_CARDS (
	Credit_Card_No CHAR(16) PRIMARY KEY NOT NULL,
    Expiry_Date DATE NOT NULL,
    Balance INT NOT NULL DEFAULT 0
);