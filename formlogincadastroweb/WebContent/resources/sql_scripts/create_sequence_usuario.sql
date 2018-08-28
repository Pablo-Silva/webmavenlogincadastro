SET SERVEROUTPUT ON

DECLARE
	v_value 				NUMBER := 0;    
    v_owner                 VARCHAR2(50) := 'ALUNO';
    v_usuario_sequence		VARCHAR2(50) := 'USUARIO_SEQUENCE';
BEGIN

-- DROP SEQUENCE USARIO_SEQUENCE

    SELECT COUNT(*) 
		INTO v_value
			FROM USER_SEQUENCES WHERE SEQUENCE_NAME = v_usuario_sequence;
	
	IF v_value = 1 THEN
		EXECUTE IMMEDIATE 'DROP SEQUENCE ' || v_usuario_sequence;
		DBMS_OUTPUT.PUT_LINE('SEQUENCE: ' || v_owner || '.' || v_usuario_sequence || ' DELETED SUCCESSFULLY.');
	ELSE
		DBMS_OUTPUT.PUT_LINE('SEQUENCE: ' || v_owner || '.' || v_usuario_sequence || ' DOES NOT EXIST.');
	END IF;
    
-- CREATE SEQUENCE USARIO_SEQUENCE
    
    SELECT MAX(ID) 
        INTO v_value 
            FROM USUARIO_TABLE;
            
    DBMS_OUTPUT.PUT_LINE('v_value: ' || v_value);
            
    IF v_value > 0 THEN
    
        EXECUTE IMMEDIATE 'CREATE SEQUENCE ' || v_usuario_sequence || ' MINVALUE 1 MAXVALUE 1000000 INCREMENT BY 1 START WITH ' || (v_value + 1);
        
    ELSE 
    
        EXECUTE IMMEDIATE 'CREATE SEQUENCE ' || v_usuario_sequence || ' MINVALUE 1 MAXVALUE 1000000 INCREMENT BY 1 START WITH 1';
        
    END IF;
    
    SELECT COUNT(*) 
		INTO v_value
			FROM USER_SEQUENCES WHERE SEQUENCE_NAME = v_usuario_sequence;
    
    IF v_value = 1 THEN
    
		DBMS_OUTPUT.PUT_LINE('SEQUENCE: ' || v_usuario_sequence || ' CREATED SUCCESSFULLY');
        
	ELSE
		
        DBMS_OUTPUT.PUT_LINE('SEQUENCE: ' || v_usuario_sequence || ' WAS NOT POSSIBLE TO CREATE.');
        
	END IF;
    
END;
/

SELECT * FROM USER_SEQUENCES WHERE SEQUENCE_NAME = 'USUARIO_SEQUENCE';