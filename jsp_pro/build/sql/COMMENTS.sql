CREATE TABLE comments(
	  id			NUMBER 			CONSTRAINT PK_COMMENTS_ID PRIMARY KEY
	, bid			NUMBER			CONSTRAINT FK_COMMENTS_BID REFERENCES EMP_BOARDS(ID)
	, content		VARCHAR2(1000)	CONSTRAINT NN_COMMENTS_CONTENT NOT NULL
	, empid			NUMBER			CONSTRAINT FK_COMMENTS_EMPID REFERENCES EMPLOYEES(EMPLOYEE_ID)
	, createdate	DATE			DEFAULT(SYSDATE)
	, deleted		VARCHAR2(1)		CONSTRAINT CK_COMMENTS_DELETED CHECK(deleted IN('Y', 'N'))
);

SELECT * FROM COMMENTS;
SELECT * FROM EMP_BOARDS;

CREATE SEQUENCE COMMENTS_SEQ NOCACHE;

/*
	특정 게시물에 대한 댓글 조회
*/

SELECT *
  FROM EMP_BOARDS B 
  JOIN COMMENTS C 
    ON B.ID = C.BID
  JOIN EMPLOYEES E 
    ON C.EMPID = E.EMPLOYEE_ID
 WHERE B.ID = 1; 
 
 /*
  * 댓글 추가 
  */
 INSERT INTO COMMENTS(ID, BID, CONTENT, EMPID, DELETEID)
 		VALUES(COMMENTS_SEQ.NEXTVAL, 1, '내용', 100, 'N');
 		
/*
 * 댓글 수정
 */
UPDATE COMMENTS
SET CONTENT = '내용 수정'
WHERE ID = 1;

/*
 * 댓글 삭제
 */
UPDATE COMMENTS
SET DELETED = 'Y'
WHERE ID = 1; 
 
 