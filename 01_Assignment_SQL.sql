-- POPULATE TABLE 

-- Create Member Table
CREATE TABLE Member (
    Member_Id NUMBER(5) PRIMARY KEY,
    Member_Name VARCHAR2(30),
    Member_address VARCHAR2(50),
    Acc_Open_Date DATE,
    Membership_type VARCHAR2(20),
    Fees_paid NUMBER(4),
    Max_Books_Allowed NUMBER(2),
    Penalty_Amount NUMBER(7,2)
);

-- Create Books Table
CREATE TABLE Books (
    Book_No NUMBER(6) PRIMARY KEY,
    Book_Name VARCHAR2(30),
    Author_name VARCHAR2(30),
    Cost NUMBER(7,2),
    Category CHAR(10)
);

-- Create Issue Table
CREATE TABLE Issue (
    Lib_Issue_Id NUMBER(10) PRIMARY KEY,
    Book_No NUMBER(6),
    Member_Id NUMBER(5),
    Issue_Date DATE,
    Return_date DATE,
    FOREIGN KEY (Book_No) REFERENCES Books(Book_No),
    FOREIGN KEY (Member_Id) REFERENCES Member(Member_Id)
);

-- Populate Member Table
INSERT INTO Member VALUES (100, 'Ritesh Giri', '12 Lake Street', TO_DATE('2005-11-10','YYYY-MM-DD'), 'Lifetime', 2000, 10, 0.00);
INSERT INTO Member VALUES (101, 'Gina Riley', '89 West Avenue', TO_DATE('2006-06-21','YYYY-MM-DD'), 'Annual', 1200, 6, 50.00);
INSERT INTO Member VALUES (102, 'Mohan Kumar', '7 South Road', TO_DATE('2007-02-12','YYYY-MM-DD'), 'Half Yearly', 700, 3, 10.50);
INSERT INTO Member VALUES (103, 'Rajiv Singh', '44 Park Lane', TO_DATE('2006-09-15','YYYY-MM-DD'), 'Quarterly', 400, 2, 25.25);
INSERT INTO Member VALUES (104, 'Richard Singh', '22 Green Blvd', TO_DATE('2006-01-03','YYYY-MM-DD'), 'Annual', 1200, 5, 100.00);
INSERT INTO Member VALUES (105, 'Girish Rana', '17 Blue Plaza', TO_DATE('2008-04-12','YYYY-MM-DD'), 'Half Yearly', 600, 2, 80.00);

-- Populate Books Table
INSERT INTO Books VALUES (7001, 'SQL Fundamentals', 'Loni', 550.00, 'Database');
INSERT INTO Books VALUES (7002, 'Science For Kids', 'A. Nerd', 430.00, 'Science   ');
INSERT INTO Books VALUES (7003, 'Advanced SQL', 'Jon Loni', 680.00, 'Database');
INSERT INTO Books VALUES (7004, 'RDBMS Concepts', 'S. Gupta', 800.00, 'RDBMS     ');
INSERT INTO Books VALUES (7005, 'Fiction Galaxy', 'M. Star', 620.00, 'Fiction   ');
INSERT INTO Books VALUES (7006, 'Data Management', 'T. Lee', 499.99, 'Management');
INSERT INTO Books VALUES (7007, 'Oracle SQL Guide', 'Loni', 590.00, 'Database');
INSERT INTO Books VALUES (7008, 'SQL in Practice', 'K. Brown', 750.00, 'Database');
INSERT INTO Books VALUES (7009, 'Management 101', 'R. North', 550.00, 'Management');
INSERT INTO Books VALUES (7010, 'Galaxy Science', 'A. Nerd', 710.00, 'Science   ');

-- Populate Issue Table
INSERT INTO Issue VALUES (1000000001, 7001, 101, TO_DATE('2006-07-10','YYYY-MM-DD'), TO_DATE('2006-07-15','YYYY-MM-DD'));
INSERT INTO Issue VALUES (1000000002, 7002, 102, TO_DATE('2006-06-05','YYYY-MM-DD'), NULL);
INSERT INTO Issue VALUES (1000000003, 7003, 101, TO_DATE('2006-05-01','YYYY-MM-DD'), TO_DATE('2006-10-10','YYYY-MM-DD'));
INSERT INTO Issue VALUES (1000000004, 7005, 104, TO_DATE('2006-10-05','YYYY-MM-DD'), NULL); -- 7005 excluded in update
INSERT INTO Issue VALUES (1000000005, 7006, 103, TO_DATE('2006-09-01','YYYY-MM-DD'), NULL); -- 7006 excluded in update
INSERT INTO Issue VALUES (1000000006, 7007, 100, TO_DATE('2023-04-15','YYYY-MM-DD'), TO_DATE('2023-05-30','YYYY-MM-DD'));
INSERT INTO Issue VALUES (1000000007, 7008, 101, TO_DATE('2024-04-01','YYYY-MM-DD'), TO_DATE('2024-04-25','YYYY-MM-DD'));
INSERT INTO Issue VALUES (1000000008, 7010, 105, TO_DATE('2024-03-02','YYYY-MM-DD'), NULL);


-- QUERIES

-- 1) List all the books that are written by Author Loni and has price less then 600.
SELECT * FROM BOOKS WHERE AUTHOR_NAME = 'Loni' AND COST < 600;

-- 2) List the Issue details for the books that are not returned yet
SELECT * FROM ISSUE WHERE RETURN_DATE IS NULL;

-- 3) Update all the blank return_date with 31-Dec-06 excluding 7005 and 7006
UPDATE ISSUE SET RETURN_DATE = '31-DEC-2006' WHERE BOOK_NO NOT IN (7005, 7006);
SELECT * FROM ISSUE;

-- 4) List all the Issue details that have books issued for more then 30 days.
SELECT * FROM ISSUE WHERE TO_DATE(RETURN_DATE) - TO_DATE(ISSUE_DATE) > 30;

-- 5) List all the books that have price in range of 500 to 750 and has category as Database
SELECT * FROM BOOKS WHERE COST >= 500 AND COST <= 750 AND CATEGORY = 'Database';

-- 6) List all the books that belong to any one of the following categories Science, Database, Fiction, Management
SELECT * FROM BOOKS WHERE CATEGORY IN ('Science', 'Database', 'Fiction', 'Management');

-- 7) List all the members in the descending order of Penalty due on them
SELECT * FROM MEMBER ORDER BY PENALTY_AMOUNT DESC;

-- 8) List all the books in ascending order of category and descending order of price.
SELECT * FROM BOOKS ORDER BY CATEGORY ASC, COST DESC;

-- 9) List all the books that contain word SQL in the name of the book
SELECT * FROM BOOKS WHERE BOOK_NAME LIKE '%SQL%';

-- 10)	List all the members whose name starts with R or G and contains letter I in it
SELECT * FROM MEMBER WHERE MEMBER_NAME LIKE 'R%' OR MEMBER_NAME LIKE 'G%' AND MEMBER_NAME LIKE '%i%';

-- 11)	List the entire book name in Init cap and author in upper case in the descending order of the book name.
SELECT initcap(BOOK_NAME), upper(AUTHOR_NAME) FROM BOOKS ORDER BY BOOK_NAME DESC; 

-- 12)	List the Issue Details for all the books issue by member 101  with Issue_date and Return Date in following format. ‘Monday, July, 10, 2006’.
SELECT LIB_ISSUE_ID, BOOK_NO, MEMBER_ID, TO_CHAR(ISSUE_DATE, 'Day, Month, DD, YYYY') AS ISSUE_DATE, TO_CHAR(RETURN_DATE, 'Day, Month, DD, YYYY') AS RETURN_DATE  FROM ISSUE WHERE MEMBER_ID = 101; 

-- 13) List the data in the book table with category data displayed as D for Database, S for Science, R for RDBMS and O for all the others.
SELECT 
    BOOK_NO,
    BOOK_NAME,
    AUTHOR_NAME,
    COST,
    CASE 
        WHEN UPPER(CATEGORY) = 'DATABASE' THEN 'D'
        WHEN UPPER(CATEGORY) = 'SCIENCE' THEN 'S'
        WHEN UPPER(CATEGORY) = 'RDBMS' THEN 'R'
        ELSE 'O'
    END AS CATEGORY_CODE
FROM 
    BOOKS;


-- 14) List all the members that became the member in the year 2006.
SELECT * from MEMBER  WHERE TO_CHAR(acc_open_date, 'YYYY') = '2006';

-- 15) List the Lib_Issue_Id, Issue_Date, Return_Date and No of days Book was issued. Find the details of the member of the Library in the order of their joining the library.
SELECT * FROM MEMBER ORDER BY ACC_OPEN_DATE;

-- 16) Display the count of total no of books issued to Member 101.
SELECT COUNT(BOOK_NO) AS TOTAL_COUNT FROM ISSUE WHERE MEMBER_ID = 101;

-- 17) Display the total penalty due for all the members.
select SUM(penalty_amount) as total_pen from member;

-- 18) Display the total no of members
SELECT COUNT(member_id) from member;

-- 19) Display the total no of books issued
SELECT COUNT(BOOK_NO) FROM ISSUE;

-- 20) Display the average membership fees paid by all the members
SELECT AVG(FEES_PAID) FROM MEMBER;


-- 21) Display no of months between issue date and return date for all issue
select LIB_ISSUE_ID, TO_CHAR(issue_date, 'YYYY-MM-DD') AS issue_date_formatted,
  TO_CHAR(return_date, 'YYYY-MM-DD') AS return_date_formatted,
  MONTHS_BETWEEN(return_date, issue_date) AS months_between from issue;

-- 23) Display the length of member’s name
SELECT LENGTH(MEMBER_NAME) from MEMBER;

-- 24) Display the first 5 characters of  membership_type for all members
SELECT  SUBSTR(membership_type, 1, 5) AS membership_type_first5 from MEMBER;

-- 25) Display the last day of the month of issue date
SELECT TO_CHAR( LAST_DAY(issue_date),'Day, DD-MM-YYYY' ) AS last_day_of_month FROM issue;
