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
  
- **_Transaction Management in JDBC_**
  - Transaction represents a single unit of work.
  - The ACID properties describes the transaction management well. 
  - **ACID** stands for Atomicity, Consistency, isolation and durability.
	- **_Atomicity_** means either all successful or none.
	- **_Consistency_** ensures bringing the database from one consistent state to another consistent state.
	- **_Isolation_** ensures that transaction is isolated from other transaction.
	- **_Durability_** means once a transaction has been committed, it will remain so, even in the event of errors, power loss etc.
  - **Advantage of Transaction Mangaement**.
	- fast performance It makes the performance fast because database is hit at the time of commit.

  - ![see image](https://github.com/ravi115/JDBC-Tutorials/blob/master/JDBC-Tutorials/img_tr/Capture.PNG?raw=true "Title")
  - see the below code: - 
  	
	**_commit_**
	
		import java.sql.*;  
		
		class FetchRecords{  
		
		public static void main(String args[])throws Exception{
		
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/training","root","root");  
			con.setAutoCommit(false);  

			Statement stmt=con.createStatement();  
			stmt.executeUpdate("insert into user420 values(190,'abhi',40000)");  
			stmt.executeUpdate("insert into user420 values(191,'umesh',50000)");  

			con.commit();  
			con.close();  
			}
		}  
		
	**_RollBack_**

			import java.sql.*;  
			import java.io.*;  
			
			class TM{  
			
			public static void main(String args[]){  
				try{  

				Class.forName("com.mysql.cj.jdbc.Driver");  
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/training","root","root");  
				con.setAutoCommit(false);  

				PreparedStatement ps=con.prepareStatement("insert into user420 values(?,?,?)");  

				BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
				while(true){  

					System.out.println("enter id");  
					String s1=br.readLine();  
					int id=Integer.parseInt(s1);  

					System.out.println("enter name");  
					String name=br.readLine();  

					System.out.println("enter salary");  
					String s3=br.readLine();  
					int salary=Integer.parseInt(s3);  

					ps.setInt(1,id);  
					ps.setString(2,name);  
					ps.setInt(3,salary);  
					ps.executeUpdate();  

					System.out.println("commit/rollback");  
					String answer=br.readLine();  
					if(answer.equals("commit")){  
					con.commit();  
				}  
				if(answer.equals("rollback")){  
					con.rollback();  
				}  
				System.out.println("Want to add more records y/n");  
				String ans=br.readLine();  
				if(ans.equals("n")){  
				break;  
				}  

				}  
				con.commit();  
				System.out.println("record successfully saved");  

				con.close();//before closing connection commit() is called  
				}catch(Exception e){System.out.println(e);}  

				}
			}

 - **_Batch Processing_**
 	- Instead of executing a single query, we can execute a batch (group) of queries. It makes the performance fast.
	- The java.sql.Statement and java.sql.PreparedStatement interfaces provide methods for batch processing.
	- We can also batch operation in callable statement (stored procedure).
	- **Advantage of Batch Processing :** Fast Performance.
	- [click here to see the complete code for batch processing.](https://github.com/ravi115/JDBC-Tutorials/blob/master/JDBC-Tutorials/src/main/java/com/jdbc/tutorials/dao/BatchDAO.java)

 - **_Index_**
 	- A database index is a data structure that improves the speed of operations in a table.
	- Practically, indexes are also a type of tables, which keep primary key or index field and a pointer to each record into the actual table.
	- The users cannot see the indexes, they are just used to speed up queries and will be used by the Database Search Engine to locate records very fast.
	- **Advantages:** - 
		- the SELECT statements become fast on those tables. 

	- **Disadvantages:** -
		- The INSERT and UPDATE statements take more time on tables having indexes.
		- The reason is that while doing insert or update, a database needs to insert or update the index values as well.

	- In MySQL, An index can be created at the time of table creation using below syntax: - 
			
			CREATE TABLE person (
			    last_name VARCHAR(50) NOT NULL,
			    first_name VARCHAR(50) NOT NULL,
			    INDEX (last_name, first_name)
			);
	- Note:  The _problem_ about this index type is that you have to query for the leftmost value to use the index. So, if your index has two columns, say last_name and first_name, the order that you query these fields matters a lot.
	- CREATE INDEX enables to add indexes to existing tables. 
	- A multiple-column index can be created using multiple columns.
	- Syntax use to create index is : 
	- CREATE INDEX [index name] ON [table name]([column name]);
	- Create UNIQUE INDEX
		- Using CREATE UNIQUE INDEX, you can create an unique index in MySQL.
	- Example: - 
	
		CREATE  UNIQUE INDEX newautid ON newauthor(aut_id);
		The above MySQL statement will create an UNIQUE INDEX on 'aut_id' column for 'newauthor' table.

	- MySQL create UNIQUE INDEX with index type
	- Create UNIQUE INDEX with index type
	- In MySQL, you can specify the type of the INDEX with CREATE INDEX command to set a type for the index.
	
		CREATE  UNIQUE INDEX newautid ON newauthor(aut_id) USING BTREE;

	- [click here to see complete example of JDBC Index](https://github.com/ravi115/JDBC-Tutorials/blob/master/JDBC-Tutorials/src/main/java/com/jdbc/tutorials/dao/IndexDAO.java)
	
 - **_JDBC Connection Pooling_**
 	- Connection pooling is a process where we maintain a cache of database connections.
	- A connection pool operates by performing the work of creating connections ahead of time.
	- In the case of a JDBC connection pool, a pool of Connection objects are created at the time the application server starts. 
	- These objects are then managed by a pool manager that disperses connections as they are requested by clients and returns them to the pool when it determines the client is finished with the Connection object.
	- When the connection pool server starts, it creates a predetermined number of Connection objects.
	- [click here to see complete code JDBC Conncection Pool](https://github.com/ravi115/JDBC-Tutorials/blob/master/JDBC-Tutorials/src/main/java/com/jdbc/tutorials/pool/DBConnectionPool.java)

 - **_JDBC IMAGE_STORE_**
 	- we can store and retrive the image to or from database.
	- [click here to see how to store or retrive images from database](https://github.com/ravi115/JDBC-Tutorials/blob/master/JDBC-Tutorials/src/main/java/com/jdbc/tutorials/dao/ImageStoreDAO.java)


- ## **_Trigger_**
	- A trigger is set of actions that run automatically when a specified change operations Like: SQL  Insert, update or delete are performed on some table.
	- A trigger also handles the error. means if any error occurs before inserting then row won't get affected in actual table.
	- **When to use trigger:** 
		1. Enforce business rule.
		2. validate input data.
		3. log the activity happening on the particular table in some other table.
	
	- **we can configure or define a trigger with the help of below syntax**
		1. Compound statements (BEGIN / END)
		2. Variable declaration (DECLARE) and assignment (SET)
		3. Flow-of-control statements (IF, CASE, WHILE, LOOP, REPEAT, LEAVE, ITERATE)
		4. Condition declarations
		5. Handler declarations
	- **How to create a MySQL Trigger**
		1. Trigger is a database object associated with a specified table and it gets activated when a specified event (Insert, update or delete) occurs.
		2. the complete instruction to create a trigger:
			
			CREATE     
			[DEFINER = { user | CURRENT_USER }]     
			TRIGGER trigger_name     
			trigger_time trigger_event     
			ON tbl_name FOR EACH ROW     
			trigger_body
			trigger_time: { BEFORE | AFTER } 
			trigger_event: { INSERT | UPDATE | DELETE }
	
	- within the schema the trigger name should be unique.
	- basically we use BEFORE | AFTER Trigger command while defining the trigger for insert, update and delete events on a table.
	- 
			
