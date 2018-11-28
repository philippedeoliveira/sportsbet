'Add the new Id colmumn referencing the business unit id'
alter table users add column buId integer;
'make the join based on previous values of bu_code and the matching table'
update users u,businessUnitCodeToId bu set u.buId=bu.id where bu.buCode=u.businessUnit;
'break the relation'
update users set businessUnit=null;
'get the constraint name to drop it'
show create table users;
'drop the constraint'
alter table users drop foreign key users_ibfk_1;
'drop the table'
drop table businessUnit;
'use the other script to create the businessUnit table back'
'...'
'insert the bu reference data'
'...'
'add the foreign key integrity constraint'
alter table users add constraint foreign key (buId) references businessUnit(id);
'add the new user column for the full name'
alter table users add column fullName varchar(200);
'DONE !'