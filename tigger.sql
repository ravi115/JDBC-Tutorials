
-- create a schema
drop schema if exists `trigger_demo`;
create schema `trigger_demo`;
use `trigger_demo`;

-- create a table called account
drop table if exists `account`;

create table `account` (
	
    `id` int not null,
    `first_name` varchar(256) default null,
    `last_name` varchar(256) default null,
    `email_Id` varchar(256) default null,
    `salary` float(10,5) default null,
    `last_updated_time` datetime null on update now(),
    primary key(`id`)
) engine InnoDB;

-- display the tableaccount_trigger
desc `account`;

-- insert few fields
insert into `account` (`id`, `first_name`, `last_name`, `email_Id`, `salary`) values
(1, 'ravi', 	'kumar', 	'ravi115ranjan@gmail.com', 	'65000.00'),
(2, 'monu', 	'sharma', 	'monu115sharma@gmail.com', 	'75000.00'),
(3, 'raj', 		'kumar', 	'raj115@gmail.com', 		'85000.00');

-- select query
select * from `account`; 

-- let's define a table to store the log history for any activity happend on account table.

drop table if exists `account_log`;
create table `account_log` (
	
    `id` int not null,
    `first_name` varchar(256) default null,
    `last_name` varchar(256) default null,
    `email_Id` varchar(256) default null,
    `salary` float(10,5) default null,
    `LOG_ACTIVITY` varchar(256) default null,
    `LOG_TIMESTAMP` datetime default null

) engine InnoDB;

/* Let's create the trigger for account table and store the SQL (Insert, update and delete) events 
   on this table to account_log table for each tuple gets affected.
*/


-- before creating the trigger, we must have to change the delimeter

DELIMITER $$

/*
	1. use AFTER INSERT
      - this will execute the trigger once the insertion is done on the actual table for which this trigger 
        has been created.
	  - below is the one way of definnig the trigger body.
*/
create trigger `account_after_trigger` AFTER INSERT on `account`
for each row
-- define the trigger body
	BEGIN
	
    insert into `account_log` (`id`, `first_name`, `last_name`, `email_Id`, `salary`, `LOG_ACTIVITY` , `LOG_TIMESTAMP`) 
    values(new.id, new.first_name, new.last_name, new.email_Id, new.salary, 'This is an insert log activity', now());
    
    END $$
    
-- change the delimeter back to the semicolon

 DELIMITER ;

-- insert a new recored and check the account_log table whiaccount_triggerch will inserted as the history record by trigger.
insert into `account` (`id`, `first_name`, `last_name`, `email_Id`, `salary`) values
(5, 'geet', 	'sagar', 	'geet.sagar@gmail.com', 	'95000.00');

select * from `account_log`;


-- 2. use BEFORE Insert
/*
	before trigger is used to perform some validation of input.
*/
-- change the delimeter
DELIMITER $$

create trigger `account_before_trigger` BEFORE INSERT on `account`
for each row

-- define the trigger body

BEGIN
	set new.first_name = TRIM(new.first_name); -- remove any spaces around first name.
    set new.last_name = UPPER(TRIM(new.last_name)); -- remove any spaces around last name and converts all letters into upper cases.
END;
$$

-- change the delimiter back to semicolon;
delimiter ;

-- insert new entries with spaces around first name and last name.
insert into `account` (`id`, `first_name`, `last_name`, `email_Id`, `salary`) values
(51, '    salman ', 	'khan    ', 	'salman.khan@gmail.com', 	'85000.00');
    
select * from `account`;    


-- 3. AFTER UPDATE
/*
	now we will see the after update trigger command.
*/
-- change the demilimter as usual.
DELIMITER $$

create trigger `account_after_update_trigger` AFTER UPDATE on `account`
for each row
-- define the trigger body
 
begin
	insert into `account_log` (`id`, `first_name`, `last_name`, `email_Id`, `salary`, `LOG_ACTIVITY` , `LOG_TIMESTAMP`) values
    (old.id, old.first_name, UPPER(old.last_name), new.email_Id, new.salary, 'This is an update activity', now());

end;
$$

-- change delimiter back to the semicolon.
DELIMITER ;

-- update any id to see effect in the account_log table
update `account` set email_Id = 'xyz@gmail.com', salary = 15005.02 where id = 2;

select * from `account` where id = 2;

select * from `account_log`;


-- 4. BEFORE DELETE
/*
	let's say we want to delete something from the original table and we want to maintain that deleted
    information in some other table
*/
-- change the delimiter
delimiter $$

create trigger `account_after_delete` BEFORE DELETE on `account`
for each row
-- define trigger body 
begin

	insert into `account_log` (`id`, `first_name`, `last_name`, `email_Id`, `salary`, `LOG_ACTIVITY` , `LOG_TIMESTAMP`) values
    (old.id, old.first_name, old.last_name, old.email_Id, old.salary, 'This is an delete activity', now());

end;
$$

-- change the delimiter back to semicolon;
delimiter ;

-- perform any delete operation
delete from `account` where id = 51;

-- check the deleted log history into account_log
select * from `account_log`;


