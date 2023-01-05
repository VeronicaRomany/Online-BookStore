INSERT INTO BOOK
VALUES('1234567890124', "book1", 'J.K Rowling', CURDATE(), 20, 'Science', 10000, 3, '/');

INSERT INTO BOOK
VALUES('1234567890125', "book2", 'J.K Rowling', CURDATE(), 20, 'Science', 10000, 3, '/');

INSERT INTO BOOK
VALUES('1234567890126', "book3", 'J.K Rowling', CURDATE(), 20, 'Science', 10000, 3, '/');

INSERT INTO BOOK
VALUES('1234567890127', "book4", 'J.K Rowling', CURDATE(), 20, 'Science', 10000, 3, '/');

INSERT INTO BOOK
VALUES('1234567890128', "book5", 'J.K Rowling', CURDATE(), 20, 'Science', 10000, 3, '/');

INSERT INTO BOOK
VALUES('1234567890129', "book6", 'J.K Rowling', CURDATE(), 20, 'Science', 10000, 3, '/');

INSERT INTO BOOK
VALUES('1234567890130', "book7", 'J.K Rowling', CURDATE(), 20, 'Science', 10000, 3, '/');




INSERT INTO `User`
VALUES('user1', '12345678', 'user1', 'name', 'user1@name.com', '012345678', 'street', 0);
            
INSERT INTO `User`
VALUES('user2', '12345678', 'user2', 'name', 'user2@name.com', '012345679', 'street', 0);

INSERT INTO `User`
VALUES('user3', '12345678', 'user3', 'name', 'user3@name.com', '012345675', 'street', 0);

INSERT INTO `User`
VALUES('user4', '12345678', 'user4', 'name', 'user4@name.com', '012345674', 'street', 0);

INSERT INTO `User`
VALUES('user5', '12345678', 'user5', 'name', 'user5@name.com', '012345673', 'street', 0);

INSERT INTO `User`
VALUES('user6', '12345678', 'user6', 'name', 'user6@name.com', '012345672', 'street', 0);

INSERT INTO `User`
VALUES('user7', '12345678', 'user7', 'name', 'user7@name.com', '012345671', 'street', 0);



INSERT INTO user_order(`Id`,`Username`,`TOTAL`,dt) VALUES(9,'user2',0,'2022-12-14 00:00:00');
INSERT INTO user_order(`Id`,`Username`,`TOTAL`,dt) VALUES(2,'user3',0,'2022-12-14 00:00:00');
INSERT INTO user_order(`Id`,`Username`,`TOTAL`,dt) VALUES(3,'user4',0,'2022-12-14 00:00:00');
INSERT INTO user_order(`Id`,`Username`,`TOTAL`,dt) VALUES(4,'user2',0,'2022-12-13 00:00:00');
INSERT INTO user_order(`Id`,`Username`,`TOTAL`,dt) VALUES(5,'user3',0,'2022-12-15 00:00:00');
INSERT INTO user_order(`Id`,`Username`,`TOTAL`,dt) VALUES(6,'user5',0,'2022-12-16 00:00:00');
INSERT INTO user_order(`Id`,`Username`,`TOTAL`,dt) VALUES(7,'user1',0,'2022-12-16 00:00:00');
INSERT INTO user_order(`Id`,`Username`,`TOTAL`,dt) VALUES(8,'user1',0,'2022-12-18 00:00:00');


INSERT INTO user_order_books(`Id`,`ISBN`,`Quantity`,`PRICE_OF_UNIT`) VALUES(1,'1234567890127',4,30);
INSERT INTO user_order_books(`Id`,`ISBN`,`Quantity`,`PRICE_OF_UNIT`) VALUES(1,'1234567890125',2,35);
INSERT INTO user_order_books(`Id`,`ISBN`,`Quantity`,`PRICE_OF_UNIT`) VALUES(1,'1234567890126',3,20);
INSERT INTO user_order_books(`Id`,`ISBN`,`Quantity`,`PRICE_OF_UNIT`) VALUES(2,'1234567890124',4,30);
INSERT INTO user_order_books(`Id`,`ISBN`,`Quantity`,`PRICE_OF_UNIT`) VALUES(2,'1234567890127',4,30);
INSERT INTO user_order_books(`Id`,`ISBN`,`Quantity`,`PRICE_OF_UNIT`) VALUES(2,'1234567890128',5,10);
INSERT INTO user_order_books(`Id`,`ISBN`,`Quantity`,`PRICE_OF_UNIT`) VALUES(3,'1234567890124',2,30);
INSERT INTO user_order_books(`Id`,`ISBN`,`Quantity`,`PRICE_OF_UNIT`) VALUES(3,'1234567890125',4,30);
INSERT INTO user_order_books(`Id`,`ISBN`,`Quantity`,`PRICE_OF_UNIT`) VALUES(4,'1234567890124',1,30);
INSERT INTO user_order_books(`Id`,`ISBN`,`Quantity`,`PRICE_OF_UNIT`) VALUES(5,'1234567890128',3,30);
INSERT INTO user_order_books(`Id`,`ISBN`,`Quantity`,`PRICE_OF_UNIT`) VALUES(6,'1234567890127',2,30);
INSERT INTO user_order_books(`Id`,`ISBN`,`Quantity`,`PRICE_OF_UNIT`) VALUES(6,'1234567890128',1,5);
INSERT INTO user_order_books(`Id`,`ISBN`,`Quantity`,`PRICE_OF_UNIT`) VALUES(7,'1234567890124',1,30);
INSERT INTO user_order_books(`Id`,`ISBN`,`Quantity`,`PRICE_OF_UNIT`) VALUES(7,'1234567890126',2,25);
INSERT INTO user_order_books(`Id`,`ISBN`,`Quantity`,`PRICE_OF_UNIT`) VALUES(8,'1234567890124',2,15);

