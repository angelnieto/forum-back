------------------------------------------------------------------------------
-- @SEQUENCES
------------------------------------------------------------------------------
create sequence BME.SEQ_CATEGORY
  start with 1
  increment by 1
  maxvalue 100000
  minvalue 1
  nocycle;
  
create sequence BME.SEQ_MESSAGE
  start with 1
  increment by 1
  maxvalue 100000
  minvalue 1
  nocycle;