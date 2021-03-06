-- noinspection SqlNoDataSourceInspectionForFile

alter table school_class

drop constraint FKo2hs1gj2j67ms7el2ayct6shu;


alter table school_class_students

drop constraint FKdykt4g4jmudusye40ikoy9a6d;


alter table school_class_students

drop constraint FKb6tb71qforo3ogi2nybpgfd9j;



drop table if exists school_class cascade;




drop table if exists school_class_students cascade;




drop table if exists student cascade;




drop table if exists teacher cascade;




drop sequence if exists hibernate_sequence;

 create sequence hibernate_sequence start 1 increment 1;




create table school_class (

id int8 not null,

name varchar(255),

teacher_id int8,

primary key (id)

);




create table school_class_students (

school_classes_id int8 not null,

students_id int8 not null

);




create table student (

id int8 not null,

dob date,

email varchar(255),

name varchar(255),

surname varchar(255),

primary key (id)

);




create table teacher (

id int8 not null,

dob date,

email varchar(255),

name varchar(255),

surname varchar(255),

primary key (id)

);




alter table school_class

add constraint FKo2hs1gj2j67ms7el2ayct6shu

foreign key (teacher_id)

references teacher;




alter table school_class_students

add constraint FKdykt4g4jmudusye40ikoy9a6d

foreign key (students_id)

references student;




alter table school_class_students

add constraint FKb6tb71qforo3ogi2nybpgfd9j

foreign key (school_classes_id)

references school_class;