CREATE TABLE IF NOT EXISTS emp_table
(
  user_name       VARCHAR(50) PRIMARY KEY,
  first_name      VARCHAR(50),
  last_name       VARCHAR(50),
  email_address   VARCHAR(250)
);

insert into emp_table (user_name, first_name, last_name) values ('testuser1', 'Test', 'User 1');
insert into emp_table (user_name, first_name, last_name) values ('testuser2', 'Test', 'User 2');
insert into emp_table (user_name, first_name, last_name) values ('testuser3', 'Test', 'User 3');

