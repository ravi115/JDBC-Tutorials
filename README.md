# JDBC-Tutorials

**Below are the Complete Explanaton of JDBC API**

  - **_PreparedStatement_**
    - The PreparedStatement interface is a subinterface of Statement. It is used to execute parameterized query.
    - As you can see, we are passing parameter (?) for the values. Its value will be set by calling the setter methods of      PreparedStatement.
    - The performance of the application will be faster if you use PreparedStatement interface because query is compiled only once.
    - To see the complete code [click here](https://github.com/ravi115/JDBC-Tutorials/blob/master/JDBC-Tutorials/src/main/java/com/jdbc/tutorials/dao/BasicDAO.java).
    
  - **_Stored Procedure_**
     - A procedure (often called a stored procedure) is a subroutine like a subprogram in a regular computing language, stored in database.
     - A procedure has a name, a parameter list, and SQL statement(s).
     - Stored procedures are fast.
     - To create stored procedure in MySQL we use the below query.
              
              Create Procedure procedure_name(Procedure Parameter /*optional*/)()
              SQL Query( routine job in SQL query);
    - Example: [click here to see the stored procedure example](https://github.com/ravi115/JDBC-Tutorials/blob/master/Procedure_example.sql)
              
              create procedure find_student()
		          Select * from student;
    - The above SQL procedure is the basic procedure. If we want to add some more information to it we can use below syntax (known as characteristics):  -
            
              COMMENT 'string'      
              | LANGUAGE SQL      
              | [NOT] DETERMINISTIC      
              | { CONTAINS SQL | NO SQL | READS SQL DATA | MODIFIES SQL DATA }      
              | SQL SECURITY { DEFINER | INVOKER }
    - Example: - 
             
             Create procedure new_job()
             Comment ‘this is comment’
             Language SQL
             NOT DETERMINISTIC
             Contains SQL
             SQL Security definer
             Select * from student;

    - **MySQL Compound-statement:**
     1.	A compound statement is block that can contain : - 
        - 	another block.
        - 	Declaration of variables
        - 	Condition handlers
        - 	Cursor
        - 	Flow control construct such as loop and conditions.

     2.	Below are the following compound statements:  -
        - 	BEGIN…END Compound
        - 	Statement label
        - 	Declare
        - 	Variable in stored programs
        - 	Flow control statements
        - 	Cursor
        - 	Condition handling

    - **Syntax:**
    
              [begin_label:] 
              BEGIN    
              [statement_list]  
              END [end_label]    
              [begin_label:] 
              LOOP      
              statement_list  
              END LOOP 
              [end_label]    
              [begin_label:] 
              REPEAT            
              statement_list  
              UNTIL search_condition  
              END 
              REPEAT [end_label]    
              [begin_label:] 
              WHILE search_condition 
              DO           
              statement_list  
              END WHILE 
              [end_label]

    - Example:
    
            DELIMITER $$
            CREATE PROCEDURE my_procedure_Local_Variables()
            BEGIN   /* declare local variables */   
            DECLARE a INT DEFAULT 10;   
            DECLARE b, c INT;    /* using the local variables */   
            SET a = a + 100;   
            SET b = 2;   
            SET c = a + b;    
            BEGIN      /* local variable in nested block */      
            DECLARE c INT;             
            SET c = 5;       
            /* local variable c takes precedence over the one of the          
            same name declared in the enclosing block. */       
            SELECT a, b, c;   
            END;    
            SELECT a, b, c;
            END$$
    
    - **MySQL Procedure Parameter:**
     1. CREATE PROCEDURE sp_name () ...
        - this is no parameter stored procedure.
        
     2. CREATE PROCEDURE sp_name ([IN] param_name type)... 
        - the IN parameter passes a value into procedure. 
        - the procedure might modify the value, but the modification is not visible to the caller when the procedure returns. 

    Examples: - 
    
            mysql> CREATE PROCEDURE my_proc_IN (IN var1 INT)
            -> BEGIN
            -> SELECT * FROM jobs LIMIT var1;
            -> END$$
            
            ----- this is how we call procedure ----
	          CALL my_proc_in(2)$$

     3. CREATE PROCEDURE sp_name ([OUT] param_name type)... 
        - the OUT parameter passes a value from  the procedure back to the caller. 
        - it’s initial value is null with the procedure and its value visible to the caller.

    Examples:  -
    
            mysql> CREATE PROCEDURE my_proc_OUT (OUT highest_salary INT)
            -> BEGIN
            -> SELECT MAX(MAX_SALARY) INTO highest_salary FROM JOBS;
            -> END$$
            
            ----- This is how we call Stored procedure for OUT parameter
               CALL my_proc_OUT(@M)$$

    4. CREATE PROCEDURE sp_name ([INOUT] param_name type)...
        -	In the procedure each parameter is in parameter by default.
        -	To specify the parameter type we use OUT or INOUT parameter.
 
    - in java we acheive Stored Procedure using **CallableStatement API**.
    - CallableStatement interface is used to call the _stored procedures and functions_.
    - [click here to see the complete code for CallableStatement](https://github.com/ravi115/JDBC-Tutorials/blob/master/JDBC-Tutorials/src/main/java/com/jdbc/tutorials/dao/CallableDAO.java).
  
