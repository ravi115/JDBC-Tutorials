delimiter $$

drop procedure if exists `training`.`insert_student` $$

create procedure `training`.`insert_student` (IN ID int, IN NAME varchar(250), IN AGE int, IN DATE date, IN UPDATED date)
begin
	insert into `student` (id, name, age, date, updated) values(ID, NAME, AGE, DATE, UPDATED);
end $$

Delimiter ; 


-- to do select operation --

delimiter $$

drop procedure if exists `training`.`select_operation` $$

create procedure `training`.`select_operation` (IN ID int)
begin
	select * from `student` where id = ID;
end $$

Delimiter ;


-- to do select operation to fetch first name --

delimiter $$

drop procedure if exists `training`.`getName` $$

create procedure `training`.`getName` (IN ID int, OUT First_Name varchar(250))
begin
	select name into First_Name from `student` where id = ID limit 1;
end $$

Delimiter ;

