insert into user_role(role, description) VALUES ("ROLE_SUPERADMIN", "role for super admin");
insert into user_role(role, description) VALUES ("ROLE_ADMIN", "role for admin");
insert into user_role(role, description) VALUES ("ROLE_USER", "default role for user");

insert into user(id_user, first_name, last_name, login, password, pesel) VALUES (1, "xyz", "xyz", "admin", "admin", 111);
insert into user_roles(user_id_user, roles_id) VALUES (1, 1);