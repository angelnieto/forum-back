-----------------------------------------------------------------------------
-- @TABLAS
------------------------------------------------------------------------------
CREATE TABLE BME.BME_MESSAGE (
    ID NUMBER(11) NOT NULL,
    TEXT VARCHAR2(200 CHAR) NOT NULL,
    TIME DATE DEFAULT SYSDATE NOT NULL,
    CATEGORY NUMBER(11) NOT NULL,
    CREATED_BY VARCHAR2(100 CHAR) NOT NULL
);
CREATE TABLE BME.BME_CATEGORY (
    ID NUMBER(11) NOT NULL,
    DESCRIPTION VARCHAR2(100 CHAR) NOT NULL
);
------------------------------------------------------------------------------
-- @COMMENTS
------------------------------------------------------------------------------
COMMENT ON TABLE BME.BME_MESSAGE IS 'Messages table';
COMMENT ON COLUMN BME.BME_MESSAGE.ID IS 'ID';
COMMENT ON COLUMN BME.BME_MESSAGE.TEXT IS 'Message';
COMMENT ON COLUMN BME.BME_MESSAGE.TIME IS 'Datetime';
COMMENT ON COLUMN BME.BME_MESSAGE.CATEGORY IS 'Category';
COMMENT ON COLUMN BME.BME_MESSAGE.CREATED_BY IS 'Author';

COMMENT ON TABLE BME.BME_CATEGORY IS 'Categories table';
COMMENT ON COLUMN BME.BME_CATEGORY.ID IS 'ID';
COMMENT ON COLUMN BME.BME_CATEGORY.DESCRIPTION IS 'Description';