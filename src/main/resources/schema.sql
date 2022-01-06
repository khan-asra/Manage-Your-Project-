create table members (
 id           LONG NOT NULL Primary Key AUTO_INCREMENT,
  memberName    VARCHAR(128) NOT NULL
);

create table tasks (
  taskId       LONG NOT NULL Primary Key AUTO_INCREMENT,
  memberId   LONG ,	
  taskTitle    VARCHAR(128) NOT NULL,
  taskHolder  VARCHAR(128) ,
  task     VARCHAR(128)  
);


alter table tasks
  add constraint member_task_fk foreign key (memberId)
  references members (id);



insert into members (memberName)
values ( 'Stephen R. Covey');
 
insert into members (MemberName)
values ( 'Niccolo Machiavelli'); 
 
insert into tasks (memberId,taskTitle,taskHolder, task)
values (1,'An older book, but still a very .','Stephen R. Covey','Complete');
 
insert into tasks (memberId,taskTitle,taskHolder, task)
values (2,'but still a very .','Niccolo Machiavelli','Complete');
 
