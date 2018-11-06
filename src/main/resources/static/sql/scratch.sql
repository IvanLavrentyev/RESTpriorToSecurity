insert into rest_db.user (user_name, user_login, user_password) values ('name1', 'login1', 'password1');
insert into rest_db.user (user_name, user_login, user_password) values ('name2', 'login2', 'password2');
insert into rest_db.user (user_name, user_login, user_password) values ('name3', 'login3', 'password3');

insert into rest_db.role(role_id,role_description) values (1, 'ADMIN');
insert into rest_db.role(role_id,role_description) values (2, 'USER');

create table user_role (
  user_id bigint not null,
  role_id bigint not null,
  primary key (user_id, role_id),
  constraint user_id_fk foreign key (user_id) references rest_db.user (user_id),
  constraint role_id_fk foreign key (role_id) references rest_db.role (role_id)
);

alter table rest_db.user drop column roles;

insert into rest_db.user_role(user_id, role_id) values (2, 1);
insert into rest_db.user_role(user_id, role_id) values (2, 2);

drop table rest_db.role;
drop table rest_db.user;
drop table rest_db.user_role;