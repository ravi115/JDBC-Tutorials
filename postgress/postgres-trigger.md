## This tutorial shows how to create trigger in postgres database.

**Let's understand this trigger concept by simple example**

- create a table called user having three fields mainly, id, username, password.

            
            drop table if exists user;

              create table user(
              id int PRIMARY KEY,
              username VARCHAR (50) NULL,
              password VARCHAR (50) NULL
            );

- create a trigger on the above user table that whenever there is a change in password the trigger should get executed.
  - To create a trigger, we need to first create a stored procedure.
  
        CREATE OR REPLACE FUNCTION PASSWORD_CHANGE_NOTIFY() RETURNS TRIGGER AS $$
        BEGIN
            IF (TG_OP = 'UPDATE' OR TG_OP = 'INSERT') THEN		
              PERFORM pg_notify('password_change', TG_TABLE_NAME || '$' || TG_OP || '$' || 'password' || '$' || NEW.password);
              RETURN NEW;
            ELSIF (TG_OP = 'DELETE') THEN
              PERFORM pg_notify('password_change', TG_TABLE_NAME || '$' || TG_OP || '$' || 'password' || '$' || OLD.password);
              RETURN OLD;
            END IF;
        END;
        $$ LANGUAGE plpgsql;

  - In the above stored procedure, we can see that the trigger name is **_password_change_** with which postgres will generate then trigger notification.
  
  - Below code creates the actual trigger on the table user.
  
          DROP TRIGGER IF EXISTS USER_PASSWORD_CHANGE ON user;
          
          CREATE TRIGGER USER_PASSWORD_CHANGE
          AFTER INSERT OR UPDATE OR DELETE ON user
              FOR EACH ROW EXECUTE PROCEDURE PASSWORD_CHANGE_NOTIFY();
  
