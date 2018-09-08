-- CREATE TABLESPACE TBS_RECON
--   DATAFILE 'tbs_recon.dat' 
--     SIZE 10M
--   ONLINE;

-- CREATE USER xe
--   IDENTIFIED BY xe
--   DEFAULT TABLESPACE TBS_RECON
--   QUOTA 10M on TBS_RECON;

-- GRANT create session TO xe;
-- GRANT create table TO xe;
-- GRANT create view TO xe;
-- GRANT create any trigger TO xe;
-- GRANT create any procedure TO xe;
-- GRANT create sequence TO xe;
-- GRANT create synonym TO xe;
DROP SEQUENCE MSG_SEQ;
Create sequence MSG_SEQ start with 1
increment by 1
minvalue 1;

DROP SEQUENCE UN_RECON_MSG_SEQ;
Create sequence UN_RECON_MSG_SEQ start with 1
increment by 1
minvalue 1;

DROP TABLE RECON_TABLE;
CREATE TABLE RECON_TABLE
   (
    ID NUMBER,	
    REF_NUM VARCHAR2(40 BYTE), 
	  SWIFT_MSG CLOB, 
    HASH_VALUE VARCHAR2(100 BYTE),
    SOURCE VARCHAR(50 BYTE),
	  CREATED_DATE TIMESTAMP, 
	  MODIFIED_DATE TIMESTAMP,
    RECON_STATUS VARCHAR2(20 BYTE)
   );

DROP TABLE UNRECONCILED_MSGS;
CREATE TABLE UNRECONCILED_MSGS
   (
    ID NUMBER,	
    REF_NUM VARCHAR2(40 BYTE), 
	SWIFT_MSG CLOB, 
    HASH_VALUE VARCHAR2(100 BYTE),
    SOURCE VARCHAR(50 BYTE),
	CREATED_DATE TIMESTAMP, 
	MODIFIED_DATE TIMESTAMP,
    RECON_STATUS VARCHAR2(20 BYTE)
   );