DROP TABLE EMP_BOARDS CASCADE CONSTRAINT;


CREATE TABLE EMP_BOARDS (
      ID NUMBER NOT NULL
    , TITLE VARCHAR2(100) NOT NULL
    , CONTENT VARCHAR2(2000) NOT NULL
    , EMPID NUMBER NOT NULL
    , CREATEDATE DATE DEFAULT(SYSDATE)
    , VIEW_CNT NUMBER DEFAULT(0)
    , "LIKE" NUMBER DEFAULT(0)
    , CONSTRAINT EMP_BOARDS_ID_PK PRIMARY KEY (ID)
    , CONSTRAINT EMP_BOARDS_EMP_ID_FK FOREIGN KEY(EMPID) REFERENCES EMPLOYEES(EMPLOYEE_ID)
);

CREATE SEQUENCE EMP_BOARDS_SEQ NOCACHE; -- 캐시를 안 만듦
INSERT INTO EMP_BOARDS VALUE(EMP_BOARDS_SEQ.NEXTVAL, '텍스트 제목', '텍스트 내용', 100, SYSDATE)