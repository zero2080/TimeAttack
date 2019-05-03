
-----------------------------------------------------계정----------------------------------------------------
DROP TABLE MEMBER;

DROP TABLE ADMIN;
                --ADMIN(운영자)
CREATE TABLE ADMIN (AID VARCHAR2(20) PRIMARY KEY,
                    APW VARCHAR2(20) NOT NULL,
                    AGRADE NUMBER(1) NOT NULL,
                    ANICKNAME VARCHAR2(20),
                    ANAME VARCHAR2(20) NOT NULL);
                    
                --���(�����)
CREATE TABLE MEMBER (MID VARCHAR2(20) PRIMARY KEY,      --멤버ID
                     MPW VARCHAR2(20) NOT NULL,         --비번
                     MNAME VARCHAR2(20) NOT NULL,       --이름
                     MNICKNAME VARCHAR2(20) NOT NULL UNIQUE,   --별명
                     MEMAIL VARCHAR2(100) NOT NULL,      --EMAIL
                     MGRADE NUMBER(2) DEFAULT 7 REFERENCES MEMBER_GRADE(MGRADE),        --등급
                     MPOINT NUMBER(5) DEFAULT 0,        --포인트
                     MRDATE DATE DEFAULT SYSDATE,       --가입일
                     MSTATUS NUMBER DEFAULT 0);         --회원상태(활동/탈퇴/강퇴)
                      
ALTER TABLE MEMBER MODIFY(MEMAIL VARCHAR2(100));
select * from member;
-------------------------------------------------------등급 테이블----------------------------------------------
DROP TABLE MEMBER_GRADE;
CREATE TABLE MEMBER_GRADE (MGRADE NUMBER(1) PRIMARY KEY,
                         LOPOINT NUMBER(6) NOT NULL,
                         HIPOINT NUMBER(6) NOT NULL);
                         
INSERT INTO MEMBER_GRADE VALUES(7, 0, 1999);
INSERT INTO MEMBER_GRADE VALUES(6, 2000, 5999);
INSERT INTO MEMBER_GRADE VALUES(5, 6000, 9999);
INSERT INTO MEMBER_GRADE VALUES(4, 10000, 24999);
INSERT INTO MEMBER_GRADE VALUES(3, 25000, 49999);
INSERT INTO MEMBER_GRADE VALUES(2, 50000, 99999);
INSERT INTO MEMBER_GRADE VALUES(1, 100000, 999999);

SELECT * FROM MEMBER_GRADE;

--관리자 계정 추가
INSERT INTO ADMIN (AID, APW, AGRADE, ANICKNAME, ANAME) VALUES ('ADMIN','1234',1,'ADMIN','������');

--회원가입
INSERT INTO MEMBER (MID, MPW, MNAME, MNICKNAME, MEMAIL, MGRADE, MPOINT, MRDATE, MSTATUS) VALUES ('AAAA', '1234', 'QEWR', 'HAHAHA','ASD@ASDF.COM',7,0,SYSDATE,0);
INSERT INTO MEMBER (MID, MPW, MNAME, MNICKNAME, MEMAIL, MGRADE, MPOINT, MRDATE, MSTATUS) VALUES ('BBBB', '1234', '바나나', '킥','ASD@ASDF.COM',7,0,SYSDATE,0);
INSERT INTO MEMBER (MID, MPW, MNAME, MNICKNAME, MEMAIL, MGRADE, MPOINT, MRDATE, MSTATUS) VALUES ('CCCC', '1234', '토마토', 'PAHAHA','ASD@ASDF.COM',7,0,SYSDATE,0);
INSERT INTO MEMBER (MID, MPW, MNAME, MNICKNAME, MEMAIL, MGRADE, MPOINT, MRDATE, MSTATUS) VALUES ('blackbear2080', '1234', '개봉박살', '','ASD@ASDF.COM',7,0,SYSDATE,0);
--수정
UPDATE MEMBER SET MPW='4321', MNAME='ABCD', MNICKNAME='ZERO', MEMAIL='1234@4321.COM' WHERE MID = 'AAAA';
SELECT * FROM MEMBER;
--자진탈퇴
UPDATE MEMBER SET MSTATUS=1 WHERE MID='CCCC';
--관리자 강퇴
UPDATE MEMBER SET MSTATUS=2 WHERE MID='CCCC';

COMMIT;

--ID 중복조회
SELECT * FROM MEMBER WHERE MID='AAAA';

--로그인 및 개인정보조회
SELECT * FROM MEMBER WHERE MID='AAAA' AND MPW='4321';

--회원검색
SELECT * FROM MEMBER WHERE MID LIKE '%'||'A'||'%' OR MNICKNAME LIKE '%'||'Z'||'%';

--------------------------------------------게임정보등록 테이블------------------------------------------------
DROP TABLE GAMELIST;
DROP SEQUENCE GL_SEQ;

CREATE SEQUENCE GL_SEQ MAXVALUE 9999999 NOCYCLE NOCACHE;

CREATE TABLE GAMELIST (GNUMBER NUMBER(8) PRIMARY KEY,
                       GPLATFORM VARCHAR2(100) NOT NULL,
                       AID VARCHAR2(20) REFERENCES ADMIN(AID) NOT NULL,
                       GTITLE VARCHAR2(50) NOT NULL,
                       GGENRE VARCHAR2(100) NOT NULL,
                       GGENRE2 VARCHAR2(100),
                       GIMG VARCHAR2(50) DEFAULT 'NOIMG.JPG' NOT NULL,
                       GDIFFICULTY VARCHAR2(100),
                       GCORP VARCHAR2(100) NOT NULL,
                       GMODE VARCHAR2(1000) NOT NULL,
                       GRDATE DATE DEFAULT SYSDATE NOT NULL,
                       GDATE DATE);
ALTER TABLE GAMELIST MODIFY(GCORP VARCHAR2(100));
SELECT * FROM GAMELIST WHERE GNUMBER=1;
commit;
SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM GAMELIST ORDER BY GNUMBER) A) WHERE RN BETWEEN 1 AND 3;
SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM GAMELIST ORDER BY GNUMBER) A) WHERE RN BETWEEN 1 AND 3
and gtitle like '%'||upper('2')||'%';

delete gamelist where gnumber between 3 and 12;
commit;
--게임등록
INSERT INTO GAMELIST (GNUMBER, GPLATFORM ,AID ,GTITLE ,GGENRE ,GGENRE2 ,GIMG ,GDIFFICULTY ,GCORP ,GMODE ,GRDATE ,GDATE)
                VALUES (GL_SEQ.NEXTVAL,'PC,PS4,XBOXONE','ADMIN','RESIDENT EVIL2:RE','HORROR','SERVIVAL','NOIMG.JPG','easy,normal,hardcore','capcom','leon A,leon B,clare A,clare B,tufu,4th servival,if',SYSDATE,'2019-01-25');
          
UPDATE GAMELIST SET GPLATFORM = 'PC,PS4,XBOXONE', AID='ADMIN',GTITLE='RESIDENT EVIL2:RE',GGENRE='HORROR',GGENRE2='SERVIVAL',GIMG='NOIMG.JPG',GDIFFICULTY='EASY,NORMAL,HARDCORE',GCORP='CAPCOM',GMODE='LEON A,LEON B,CLARE A,CLARE B,TUFU,4TH SERRVIVAL,IF',GDATE='2019-01-25' WHERE GNUMBER=1;
UPDATE GAMELIST SET GMODE='leon A,leon B,clare A,clare B,tufu,4th servival,if' WHERE GNUMBER=1;
SELECT * FROM GAMELIST;



                --TimeAttack
--개인기록
DROP TABLE TIME_TABLE;
DROP SEQUENCE TT_SEQ;
CREATE SEQUENCE TT_SEQ MAXVALUE 999999 NOCYCLE NOCACHE;

CREATE TABLE TIME_TABLE (TNUMBER NUMBER(8) PRIMARY KEY,
                         MID VARCHAR2(20) REFERENCES MEMBER(MID) NOT NULL,
                         MNICKNAME VARCHAR2(20),
                         GNUMBER NUMBER(8) REFERENCES GAMELIST(GNUMBER) NOT NULL,
                         TPLATFORM VARCHAR2(20) NOT NULL,
                         TTMST TIMESTAMP NOT NULL,
                         TDIFFICULTY VARCHAR2(50),
                         TMODE VARCHAR2(50),
                         TSTATUS NUMBER(1) NOT NULL,
                         TRDATE DATE DEFAULT SYSDATE NOT NULL,
                         TLINK VARCHAR2(1000) NOT NULL);
commit;


COMMIT;
select * from time_table where tPLATFORM='';
SELECT * FROM TIME_TABLE WHERE TPLATFORM IN ('PC') AND TMODE IN ('%%') AND TDIFFICULTY IN ('%%') AND TSTATUS=2 AND GNUMBER=1;
SELECT * FROM TIME_TABLE WHERE TPLATFORM LIKE '%'||'P'||'%' AND TDIFFICULTY LIKE '%'||'H'||'%' AND TMODE LIKE '%'||'l'||'%' AND GNUMBER=1 AND TSTATUS=2 ORDER BY TTMST;
SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM TIME_TABLE WHERE TSTATUS=0 ORDER BY TRDATE) A) WHERE RN BETWEEN 1 AND 2;
COMMIT;
select * from time_table;

--기록 등록
INSERT INTO TIME_TABLE (TNUMBER,MID,MNICKNAME,GNUMBER,TPLATFORM,TTMST,TDIFFICULTY,TMODE,TSTATUS,TRDATE,TLINK)
                VALUES (TT_SEQ.NEXTVAL,?,?,?,?,TO_TIMESTAMP(?,'HH24:MI:SS'),?,?,0,SYSDATE,?);
                
INSERT INTO TIME_TABLE (TNUMBER,MID,MNICKNAME,GNUMBER,TPLATFORM,TTMST,TDIFFICULTY,TMODE,TSTATUS,TRDATE,TLINK)
                VALUES (TT_SEQ.NEXTVAL,'AAAA','ZERO',1,'PC',TO_TIMESTAMP('01:17:25','HH:MI:SS'),'HARDCORE','leon A',0,SYSDATE,'https://youtu.be/KXjndMZAblo');
                
SELECT TO_CHAR(TTMST,'HH:MI:SS') FROM TIME_TABLE;

commit;
--------------------------------------------게시판 테이블------------------------------------------------
                --FREE_BOARD
DROP TABLE FREE_BOARD;

DROP SEQUENCE FB_SEQ;

--자유게시판 시퀀스
CREATE SEQUENCE FB_SEQ MAXVALUE 9999999 NOCYCLE NOCACHE;

--자유게시판
CREATE TABLE FREE_BOARD (FBNUMBER NUMBER(10) PRIMARY KEY,
                         MID VARCHAR2(20) NOT NULL REFERENCES MEMBER(MID),
                         MNICKNAME VARCHAR2(20),
                         FBTITLE VARCHAR2(30) NOT NULL,
                         FBCONTENT CLOB,
                         FBFILE VARCHAR2(20),
                         FBRDATE DATE DEFAULT SYSDATE NOT NULL,
                         FBREADCOUNT NUMBER(5) DEFAULT 0 NOT NULL,
                         FBIP VARCHAR2(20) NOT NULL,
                         FBGROUP NUMBER(10) DEFAULT 0 NOT NULL,
                         FBSTEP NUMBER(3) DEFAULT 0 NOT NULL,
                         FBLV NUMBER(3) DEFAULT 0 NOT NULL);

                --QNA
--QNA게시판
CREATE TABLE QNA_BOARD (QNUMBER NUMBER(10) PRIMARY KEY,
                        MID VARCHAR2(20) NOT NULL REFERENCES MEMBER(MID),
                        ANICKNAME VARCHAR2(20),
                        QTITLE VARCHAR2(30) NOT NULL,
                        QCONTENT CLOB,
                        QFILE VARCHAR2(20),
                        QRDATE DATE DEFAULT SYSDATE NOT NULL,
                        QREADCOUNT NUMBER(5) DEFAULT 0 NOT NULL,
                        QIP VARCHAR2(20) NOT NULL,
                        QGROUP NUMBER(10) DEFAULT 0 NOT NULL,
                        QSTEP NUMBER(3) DEFAULT 0 NOT NULL,
                        QLV NUMBER(3) DEFAULT 0 NOT NULL);
                --공지게시판
DROP TABLE NOTICE;
DROP SEQUENCE NTC_SEQ;

CREATE SEQUENCE NTC_SEQ MAXVALUE 99999 NOCYCLE NOCACHE;

CREATE TABLE NOTICE (NNUMBER NUMBER(5) PRIMARY KEY,
                     AID VARCHAR2(20) REFERENCES ADMIN(AID) NOT NULL,
                     NTITLE VARCHAR2(30) NOT NULL,
                     NCONTENT CLOB NOT NULL,
                     NFILE VARCHAR2(20),
                     NREADCOUNT NUMBER(4) DEFAULT 0 NOT NULL,
                     NRDATE DATE DEFAULT SYSDATE NOT NULL,
                     NGROUP NUMBER(5) NOT NULL,
                     NSTEP NUMBER(3) NOT NULL,
                     NLV NUMBER(2) NOT NULL);
                     
INSERT INTO NOTICE (NNUMBER, AID, NTITLE, NCONTENT, NFILE, NREADCOUNT, NRDATE, NGROUP, NSTEP, NLV)
                VALUES(NTC_SEQ.NEXTVAL,'ADMIN','TEST01-NOTICE','TEST01-NOTICE',NULL,0,SYSDATE,NTC_SEQ.CURRVAL,0,0);
SELECT * FROM NOTICE;

-----------------------------------------------댓글 테이블---------------------------------------------------
                -- 자유게시판 댓글
DROP TABLE FB_REPLY;

DROP SEQUENCE FR_SEQ;
CREATE SEQUENCE FR_SEQ MAXVALUE 99999 NOCYCLE NOCACHE;

CREATE TABLE FB_REPLY (FRNUMBER NUMBER(10) PRIMARY KEY,          --댓글번호 시퀀스
                       FBNUMBER NUMBER(10) REFERENCES FREE_BOARD(FBNUMBER),  --게시판글번호 참조
                       MID VARCHAR2(20) REFERENCES MEMBER(MID),  --아이디
                       MNICKNAME VARCHAR2(20),
                       FRCONTENT VARCHAR2(200) NOT NULL,          --본문
                       FRGROUP NUMBER(10) NOT NULL,              --댓글 그룹(댓글의 댓글)
                       FRIP VARCHAR2(20) NOT NULL,
                       FRDATE DATE DEFAULT SYSDATE NOT NULL);     --등록일

commit;
                --QNA리플
DROP TABLE QNA_REPLY;

DROP SEQUENCE QR_SEQ;

CREATE SEQUENCE QR_SEQ MAXVALUE 999999 NOCYCLE NOCACHE;

CREATE TABLE QNA_REPLY (QRNUMBER NUMBER(10) PRIMARY KEY,
                        QNUMBER NUMBER(10) REFERENCES QNA_BOARD(QNUMBER) NOT NULL,
                        MID VARCHAR2(20) NOT NULL,
                        MNICKNAME VARCHAR2(20),
                        QRCONTENT VARCHAR2(200) NOT NULL,
                        QRGROUP NUMBER(10) NOT NULL,
                        QRIP VARCHAR2(20) NOT NULL,
                        QRDATE DATE DEFAULT SYSDATE NOT NULL);
                        
                --TIME TABLE 리플
DROP TABLE TT_REPLY;
DROP SEQUENCE TR_SEQ;

CREATE SEQUENCE TR_SEQ MAXVALUE 999999 NOCYCLE NOCACHE;

CREATE TABLE TT_REPLY (TRNUMBER NUMBER(10) PRIMARY KEY,
                       MID VARCHAR2(20) REFERENCES MEMBER(MID) NOT NULL,
                       TNUMBER NUMBER(10) REFERENCES TIME_TABLE(TNUMBER) NOT NULL,
                       TRCONTENT VARCHAR2(200) NOT NULL,
                       TRGROUP NUMBER(10) NOT NULL,
                       TRIP VARCHAR2(20) NOT NULL,
                       TRDATE DATE DEFAULT SYSDATE NOT NULL);
select * from tt_reply;
commit;
INSERT INTO TT_REPLY (TRNUMBER,MID, TNUMBER,TRCONTENT, TRGROUP,TRIP,TRDATE)
    VALUES (TR_SEQ.NEXTVAL,'AAAA',6,'ù��� �ͽΔ�',TR_SEQ.CURRVAL,'172.1.0.1',SYSDATE);
--------------------------------------------기능 -----------------------------------------------------------
SELECT COUNT(*) FROM FREE_BOARD;
commit;
--페이징처리 & 리스트(+해당글 댓글 갯수)
SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT C.*,B.RCOUNT FROM FREE_BOARD C,(SELECT FBNUMBER, COUNT(*) RCOUNT FROM FB_REPLY GROUP BY FBNUMBER) B WHERE C.FBNUMBER=B.FBNUMBER(+) ORDER BY FBGROUP DESC, FBSTEP) A) WHERE RN BETWEEN 1 AND 5;
SELECT FBNUMBER,COUNT(*) FROM FB_REPLY GROUP BY FBNUMBER;
SELECT FBNUMBER, FBTITLE, FBCONTENT, MID, TO_CHAR(FBRDATE,'YY-MM-DD HH:MI:SS'),FBGROUP,FBSTEP,FBLV FROM FREE_BOARD ORDER BY FBGROUP DESC, FBNUMBER;
--�� ����
UPDATE FREE_BOARD SET FBREADCOUNT=FBREADCOUNT+1 WHERE FBNUMBER=1;               --조회수
UPDATE MEMBER SET MPOINT = MPOINT+1 WHERE MID='AAAA';                           --조회수 1당 1포인트

SELECT * FROM FREE_BOARD WHERE FBNUMBER = 1;                                     --본문정보
SELECT FRNUMBER, FBNUMBER, MID, FRCONTENT,TO_CHAR(FRDATE,'YYYY-MM-DD HH:MI:SS'), FRIP
    FROM FB_REPLY 
        WHERE FBNUMBER = 1 
            ORDER BY FRNUMBER , FRDATE;                                           --댓글정보

SELECT A.*, FRNUMBER , FRGROUP,R.MID RE_MID, FRCONTENT, TO_CHAR(FRDATE,'YYYY-MM-DD HH:MI:SS') FRDATE, FRIP FROM ( --본문 + 댓글
    SELECT * FROM FREE_BOARD) A, (
        SELECT * FROM FB_REPLY
            ORDER BY FRGROUP, FRNUMBER) R 
                 WHERE A.FBNUMBER=R.FBNUMBER(+) AND A.FBNUMBER=1;

--새글 작성
INSERT INTO FREE_BOARD (FBNUMBER, MID, MNICKNAME, FBTITLE, FBCONTENT, FBFILE, FBRDATE, FBREADCOUNT, FBIP, FBGROUP, FBSTEP, FBLV)
            VALUES (FB_SEQ.NEXTVAL, 'AAAA','ZERO','TEST01','CONTENT',NULL,SYSDATE, 0,'172.0.0.1',FB_SEQ.CURRVAL, 0, 0);
UPDATE MEMBER SET MPOINT=MPOINT+10 WHERE MID='AAAA';

--답글 작성
UPDATE FREE_BOARD SET FBSTEP = FBSTEP+1 WHERE FBGROUP=3 AND FBSTEP>0;  --답글 스텝 +1(FBGROUP이 0이 아닐때)
INSERT INTO FREE_BOARD (FBNUMBER, MID, MNICKNAME, FBTITLE, FBCONTENT, FBFILE, FBRDATE, FBREADCOUNT, FBIP,FBGROUP, FBSTEP, FBLV) --답글의 답글
            VALUES (FB_SEQ.NEXTVAL, 'CCCC','RARARA','3번글','3번글',NULL,SYSDATE, 0,'172.0.0.1',3, 1, 1);

INSERT INTO FREE_BOARD (FBNUMBER, MID, FBTITLE, FBCONTENT, FBFILE, FBRDATE, FBREADCOUNT, FBGROUP, FBSTEP, FBLV) --����� ���
            VALUES (FB_SEQ.NEXTVAL, 'CCCC','3번글','3번글',NULL,SYSDATE, 0,3, 2, 2);

SELECT * FROM FREE_BOARD;
--댓글 작성
INSERT INTO FB_REPLY (FRNUMBER, FBNUMBER, MID, FRIP,FRGROUP, FRCONTENT, FRDATE)
            VALUES (FR_SEQ.NEXTVAL,1,'CCCC','172.0.0.1',FR_SEQ.CURRVAL,'CONTENT',SYSDATE);
UPDATE MEMBER SET MPOINT=MPOINT+5 WHERE MID='CCCC';

INSERT INTO FB_REPLY (FRNUMBER, FBNUMBER, MID, FRIP, FRGROUP, FRCONTENT, FRDATE)
            VALUES (FR_SEQ.NEXTVAL,2,'AAAA','172.0.0.1',FR_SEQ.CURRVAL,'2번글의 댓글',SYSDATE);
UPDATE MEMBER SET MPOINT=MPOINT+5 WHERE MID='AAAA';

SELECT * FROM FREE_BOARD;
--댓글의 댓글
INSERT INTO FB_REPLY (FRNUMBER, FBNUMBER, MID, FRGROUP, FRCONTENT, FRDATE)
            VALUES (FR_SEQ.NEXTVAL, 1, 'AAAA', 2,'댓글의 댓글', SYSDATE);
UPDATE MEMBER SET MPOINT=MPOINT+2 WHERE MID='AAAA';

SELECT * FROM FB_REPLY ORDER BY FRGROUP, FRNUMBER;        --댓글 정렬 최신글이 제일 밑으로 추가/ 댓글의 댓글은 댓글의 밑으로 들어가고 최신댓글 또한 밑으로 추가됨

SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM NOTICE ORDER BY NGROUP DESC, NSTEP) A) WHERE RN BETWEEN 1 AND 10;
--글 수정
UPDATE FREE_BOARD SET FBTITLE='TEST01-UPDATE', FBCONTENT = 'TESTCONTENT-UPDATE', FBFILE='TEST.JPG' WHERE FBNUMBER=1;
--굴 삭제
DELETE FREE_BOARD WHERE FBNUMBER=1;

--댓글 삭제
DELETE FB_REPLY WHERE FRNUMBER=3;
SELECT * FROM REPLY_BOARD WHERE RNUMBER=2 AND BNUMBER=1 AND TO_CHAR(RDATE,'YYMMDDHHMISS')='190307112126';
SELECT * FROM REPLY_BOARD;

--답글달기
commit;

select * from admin;

select * from free_board;
