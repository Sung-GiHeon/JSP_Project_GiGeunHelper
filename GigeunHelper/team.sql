show tables;

desc member_tb;
desc buy_tb;

drop table buy_tb;

CREATE TABLE buy_tb(
	buy_num INT PRIMARY KEY auto_increment,
	buy_product_num INT,
	buy_member_num INT,
	buy_date TIMESTAMP default now()
);

INSERT INTO buy_tb VALUES(null,1,2,now());

select * from mysql.db;

CREATE TABLE qna_board(
	qna_num INT PRIMARY KEY auto_increment,			
	qna_name VARCHAR(20) NOT NULL,					
	qna_title VARCHAR(50) NOT NULL,					
	qna_content TEXT NOT NULL,						
	qna_file VARCHAR(50),							
	qna_file_origin VARCHAR(50),					
	qna_writer_num INT, 							
	qna_date TIMESTAMP default now(),				
	FOREIGN KEY (qna_writer_num) REFERENCES member_tb(member_num)
);

CREATE TABLE notice_board(
	notice_num INT PRIMARY KEY auto_increment,
	notice_category VARCHAR(20),
	notice_author VARCHAR(20),
	notice_title VARCHAR(20),
	notice_content TEXT,
	notice_date TIMESTAMP default now()
);

DESC qna_board;

DESC product_tb;

DESC member_tb;

ALTER TABLE member_tb add member_name VARCHAR(45) NOT NULL;

ALTER TABLE product_tb add product_origin_credit int NOT NULL;

INSERT INTO member_tb values(null,'admin','admin','admin','','1000000',now(),'Y','관리자');
INSERT INTO member_tb values(null,'dkee3','12345','dkee3@naver.com','01041723337','100',now(),'Y','박금삼');

SELECT * FROM member_tb;

commit;
INSERT INTO product_tb values(null,'첫번째 사진입니다.','자연',3,'desert.jpg','desert.jpg',0,0,now(),1,'N',null);
INSERT INTO product_tb values(null,'첫번째 사진입니다.','Chtistmas',3,'desert.jpg','desert.jpg',0,0,now(),1,'N',null);
INSERT INTO product_tb values(null,'감사','꽃',5,'Chrysanthemum.jpg','Chrysanthemum.jpg',0,0,now(),1,'N',null);

SELECT * FROM product_tb WHERE product_num = 1;

DELETE FROM product_tb WHERE product_num = 1;

UPDATE product_tb SET product_readcount = 3 WHERE product_num = 22;

ALTER TABLE product_tb DROP FOREIGN KEY fk_bbs_id;

SET foreign_key_checks = 0;

DROP TABLE product_tb;

CREATE TABLE product_tb(
	product_num INT PRIMARY KEY auto_increment,			
	product_title VARCHAR(20) NOT NULL,					
	product_category VARCHAR(50) NOT NULL,
	product_size VARCHAR(50) NOT NULL,
	product_credit INT NOT NULL,					
	product_file VARCHAR(50),							
	product_origin_file VARCHAR(50),					
	product_readcount INT,
	product_likecount INT,
	product_member_num int,
	product_date TIMESTAMP default now(),
	product_free VARCHAR(1) default 'N',
	product_free_time TIMESTAMP 
);

desc product_tb;

DROP TABLE product_tb;

SELECT * FROM product_tb;



INSERT INTO product_tb values(1,'첫번째 사진입니다.','Chtistmas','1024X768',5,'Chrysanthemum.jpg','Chrysanthemum.jpg',0,0,1,now(),'N',null,5);
INSERT INTO product_tb values(null,'두번째 사진입니다.','Christmas','1024X768',5,'Chrysanthemum.jpg','Chrysanthemum.jpg',0,0,1,now(),'N',null);
INSERT INTO product_tb values(null,'세번째 사진입니다.','Christmas','1024X768',5,'Chrysanthemum.jpg','Chrysanthemum.jpg',0,0,1,now(),'N',null);
INSERT INTO product_tb values(null,'네번째 사진입니다.','Christmas','1024X768',5,'Chrysanthemum.jpg','Chrysanthemum.jpg',0,0,1,now(),'N',null);
INSERT INTO product_tb values(null,'다섯번째 사진입니다.','Christmas','1024X768',5,'Chrysanthemum.jpg','Chrysanthemum.jpg',0,0,1,now(),'N',null);
INSERT INTO product_tb values(null,'여섯번째 사진입니다.','Christmas','1024X768',5,'Chrysanthemum.jpg','Chrysanthemum.jpg',0,0,1,now(),'N',null);
INSERT INTO product_tb values(null,'일곱번째 사진입니다.','Christmas','1024X768',5,'Chrysanthemum.jpg','Chrysanthemum.jpg',0,0,1,now(),'N',null);
INSERT INTO product_tb values(null,'여덟번째 사진입니다.','Christmas','1024X768',5,'Chrysanthemum.jpg','Chrysanthemum.jpg',0,0,1,now(),'N',null);

CREATE TABLE like_tb(
	like_member_num INT,
	like_product_num INT
);

DESC like_tb;

DESC cart_tb;

DELETE FROM buy_tb; WHERE buy_member_num =2;

UPDATE member_tb set member_credit = 100 WHERE member_num =2;

SELECT * FROM member_tb;

DROP TABLE cart_tb;

SELECT * FROM buy_tb;

drop table like_tb;

CREATE TABLE cart_tb(
	cart_num INT PRIMARY KEY auto_increment,
	cart_member_num INT,
	cart_product_num INT
);

INSERT INTO cart_tb VALUES(null,2,1);


commit;