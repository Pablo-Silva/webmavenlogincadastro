SET SERVEROUTPUT ON

DECLARE
	v_value 				NUMBER := 0;
	v_usuario_table			VARCHAR2(50) := 'USUARIO_TABLE';
	v_usuario_sequence		VARCHAR2(50) := 'USUARIO_SEQUENCE';
	v_owner					VARCHAR2(50) := 'ALUNO';
BEGIN
	
-- DROP SCHEME

-- DROP TABLE USUARIO_TABLE

	SELECT COUNT(*) 
		INTO v_value
			FROM ALL_TABLES WHERE TABLE_NAME = v_usuario_table AND OWNER = v_owner;

	IF v_value = 1 THEN
		EXECUTE IMMEDIATE 'DROP TABLE ' || v_owner || '.' || v_usuario_table || ' CASCADE CONSTRAINTS';
		DBMS_OUTPUT.PUT_LINE('TABLE: ' || v_owner || '.' || v_usuario_table || ' DELETED WITH SUCCESS.');
	ELSE
		DBMS_OUTPUT.PUT_LINE('TABLE: ' || v_owner || '.' || v_usuario_table || ' DOES NOT EXIST.');
	END IF;
	
-- DROP SEQUENCE USURIO_SEQUENCE
	
	SELECT COUNT(*) 
		INTO v_value
			FROM USER_SEQUENCES WHERE SEQUENCE_NAME = v_usuario_sequence;
	
	IF v_value = 1 THEN
		EXECUTE IMMEDIATE 'DROP SEQUENCE ' || v_usuario_sequence;	
		DBMS_OUTPUT.PUT_LINE('SEQUENCE: ' || v_usuario_sequence || ' DELETED WITH SUCCESS.');
	ELSE
		DBMS_OUTPUT.PUT_LINE('SEQUENCE: ' || v_usuario_sequence || ' DOES NOT EXIST.');
	END IF;
		
-- CREATE SCHEME

-- CREATE TABLE USUARIO_TABLE

	SELECT COUNT(*) 
		INTO v_value
			FROM ALL_TABLES WHERE TABLE_NAME = v_usuario_table AND OWNER = v_owner;

	IF v_value = 0 THEN
		EXECUTE IMMEDIATE 'CREATE TABLE ' || v_owner || '.' || v_usuario_table || ' (' ||
			'ID NUMBER NOT NULL, ' ||
			'NOME VARCHAR2(50) NOT NULL, ' ||
			'DOB DATE NULL, ' ||
			'SEXO VARCHAR2(1) NULL, ' ||
			'EMAIL VARCHAR2(30) NULL, ' ||
			'STATUS NUMBER NULL, ' ||
			'LOGIN VARCHAR2(20) NOT NULL, ' || 
			'SENHA VARCHAR2(20) NOT NULL ' ||
			')';
			
		EXECUTE IMMEDIATE 'ALTER TABLE ' || v_owner || '.' || v_usuario_table || ' ADD CONSTRAINT USUARIO_PK PRIMARY KEY (ID)';
		
		-- EXECUTE IMMEDIATE 'ALTER TABLE ' || v_owner || '.' || v_usuario_table || ' ADD CONSTRAINT CHECK_SEXO CHECK(SEXO IN (' || 'M' || ',' || 'F' || '))';
		
		EXECUTE IMMEDIATE 'ALTER TABLE ' || v_owner || '.' || v_usuario_table || ' ADD CONSTRAINT CHECK_SITUACAO CHECK(STATUS IN (1,2))';
        
        -- EXECUTE IMMEDIATE 'ALTER TABLE ' || v_owner || '.' || v_usuario_table || ' ADD CONSTRAINT LOGIN_UN UNIQUE (LOGIN)';

		DBMS_OUTPUT.PUT_LINE('TABLE: ' || v_owner || '.' || v_usuario_table || ' CREATED WITH SUCCESS.');
		
	ELSE
		DBMS_OUTPUT.PUT_LINE('TABLE: ' || v_owner || '.' || v_usuario_table || ' IT WAS NOT POSSIBLE TO CREATE.');
	END IF;

-- CREATE SEQUENCE USUARIO_SEQUENCE

	SELECT COUNT(*) 
		INTO v_value
			FROM USER_SEQUENCES WHERE SEQUENCE_NAME = v_usuario_sequence;
	
	IF v_value = 0 THEN
    
		EXECUTE IMMEDIATE 'CREATE SEQUENCE ' || v_owner || '.' || v_usuario_sequence || ' MINVALUE 1 MAXVALUE 1000000 INCREMENT BY 1 START WITH 1';

		DBMS_OUTPUT.PUT_LINE('SEQUENCIA: ' || v_usuario_sequence || ' DELETED WITH SUCCESS.');
        
	ELSE
		DBMS_OUTPUT.PUT_LINE('SEQUENCIA: ' || v_usuario_sequence || ' DOES NOT EXIST.');
	END IF;
		
END;
/

ALTER TABLE ALUNO.USUARIO_TABLE ADD CONSTRAINT CHECK_SEXO CHECK(SEXO IN ('M','F'));

DESC USUARIO_TABLE;

SELECT CONSTRAINT_NAME, CONSTRAINT_TYPE, SEARCH_CONDITION,
R_CONSTRAINT_NAME
FROM USER_CONSTRAINTS
WHERE TABLE_NAME = 'USUARIO_TABLE';

SELECT * FROM USER_SEQUENCES WHERE SEQUENCE_NAME = 'USUARIO_SEQUENCE';

