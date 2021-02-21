create table student(
    id varchar(32) comment '编号',
    name varchar(128) comment '名字'
);

create user 'bball'@'localhost' identified by '123456';

grant select,update,delete,insert on student to 'bball'@'localhost';

insert into student(id,name) values ('1','xiaoming');

create table emp(
      id INTEGER comment '编号',
      name varchar(128) comment '名字',
      age INTEGER comment '年龄'
);

grant select,update,delete,insert on emp to 'bball'@'localhost';
