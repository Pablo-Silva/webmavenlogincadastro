SET SERVEROUTPUT ON

DECLARE
	v_value 				NUMBER := 0;
	v_usuario_table			VARCHAR2(50) := 'USUARIO_TABLE';
	v_usuario_sequence		VARCHAR2(50) := 'USUARIO_SEQUENCE';
	v_owner					VARCHAR2(50) := 'ALUNO';
BEGIN
	
-- DROP SCHEME

	SELECT COUNT(*) 
		INTO v_value
			FROM ALL_TABLES WHERE TABLE_NAME = v_usuario_table AND OWNER = v_owner;

	IF v_value = 1 THEN
		EXECUTE IMMEDIATE 'DROP TABLE ' || v_owner || '.' || v_usuario_table || ' CASCADE CONSTRAINT';
		DBMS_OUTPUT.PUT_LINE('TABLE: ' || v_owner || '.' || v_usuario_table || ' DELETED WITH SUCCESS.');
	ELSE
		DBMS_OUTPUT.PUT_LINE('TABLE: ' || v_owner || '.' || v_usuario_table || ' DOES NOT EXIST.');
	END IF;
	
	SELECT COUNT(*) 
		INTO v_value
			FROM USER_SEQUENCES WHERE SEQUENCE_NAME = v_usuario_sequence;
	
	IF v_value = 1 THEN
		EXECUTE IMMEDIATE 'DROP SEQUENCE ' || v_usuario_sequence;	
		DBMS_OUTPUT.PUT_LINE('SEQUENCE: ' || v_usuario_sequence || ' DELETED WITH SUCCESS.');
	ELSE
		DBMS_OUTPUT.PUT_LINE('SEQUENCE: ' || v_usuario_sequence || ' DOES NOT EXIST.');
	END IF;
				
END;
/

