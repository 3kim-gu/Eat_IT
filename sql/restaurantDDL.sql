
DROP TABLE restaurant;

CREATE TABLE restaurant(
	rname varchar2(50) PRIMARY KEY,
	category varchar2(10) NOT NULL,
	food	varchar2(50) NOT NULL,
	price	number(10) NOT NULL,
	distance number(3) NOT NULL,
	waiting_time number(3) NOT NULL, 
	is_able_group VARCHAR2(5) NOT NULL, -- Oracle은 Boolean 타입 존재 x // Boolean 대체 (True/False)
	score  number(3, 2) NOT NULL,  -- -9.99 ~ 9.99
	review VARCHAR2(1000), -- 약 500자 저장가능, NULL 허용 
	url VARCHAR2(2083) NOT NULL 
);

SELECT * FROM restaurant;



