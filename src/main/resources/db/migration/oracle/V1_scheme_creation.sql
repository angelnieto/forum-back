------------------------------------------------------------------------------
-- @TABLESPACES
------------------------------------------------------------------------------
CREATE TABLESPACE BME DATAFILE 'BME.dbf'
 SIZE 1G
 AUTOEXTEND ON NEXT 1G
 MAXSIZE UNLIMITED
 LOGGING
 ONLINE PERMANENT
 EXTENT MANAGEMENT LOCAL AUTOALLOCATE BLOCKSIZE 8K
 SEGMENT SPACE MANAGEMENT AUTO
 FLASHBACK ON;

------------------------------------------------------------------------------
-- @USERS
------------------------------------------------------------------------------
BEGIN  FOR r IN (select sid,serial# from v$session where username='BME')
  LOOP
    EXECUTE IMMEDIATE 'alter system kill session ''' || r.sid  || ',' || r.serial# || ''' immediate';
  END LOOP;
END;
/

ALTER SESSION SET "_ORACLE_SCRIPT"=TRUE;

ALTER SESSION SET "_ORACLE_SCRIPT"=TRUE;
DROP USER BME CASCADE;

ALTER SESSION SET "_ORACLE_SCRIPT"=TRUE;
CREATE USER BME
       IDENTIFIED BY BME
       DEFAULT TABLESPACE BME
       PROFILE DEFAULT
       ACCOUNT UNLOCK;

GRANT CONNECT TO BME;
GRANT RESOURCE TO BME;
ALTER USER BME DEFAULT ROLE ALL;
GRANT DROP ANY DIRECTORY TO BME;
GRANT UNLIMITED TABLESPACE TO BME;
GRANT CREATE ANY DIRECTORY TO BME;
GRANT CREATE TABLE TO BME;
GRANT CREATE VIEW TO BME;
GRANT CREATE TRIGGER TO BME;
GRANT CREATE PROCEDURE TO BME;
GRANT CREATE SYNONYM TO BME;