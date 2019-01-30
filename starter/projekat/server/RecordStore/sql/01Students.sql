drop database social_network;

create database social_network;

use social_network;



-- insert users
-- password is 12345 (bcrypt encoded) 
insert into security_user (username, password, first_name, last_name) values 
	('pera', '$2a$04$4pqDFh9SxLAg/uSH59JCB.LwIS6QoAjM9qcE7H9e2drFuWhvTnDFi', 'Pera', 'Peric');
-- password is abcdef (bcrypt encoded)
insert into security_user (username, password, first_name, last_name) values 
	('petar', '$2a$04$Yr3QD6lbcemnrRNLbUMLBez2oEK15pdacIgfkvymQ9oMhqsEE56EK', 'Petar', 'Petrovic');
    
insert into security_user (username, password, first_name, last_name) values 
('marko', '$2a$04$Yr3QD6lbcemnrRNLbUMLBez2oEK15pdacIgfkvymQ9oMhqsEE56EK', 'Marko', 'Markovic');

insert into security_user (username, password, first_name, last_name) values 
('ivan', '$2a$04$Yr3QD6lbcemnrRNLbUMLBez2oEK15pdacIgfkvymQ9oMhqsEE56EK', 'Ivan', 'Ivanovic');

insert into security_user (username, password, first_name, last_name) values 
('bojana', '$2a$04$Yr3QD6lbcemnrRNLbUMLBez2oEK15pdacIgfkvymQ9oMhqsEE56EK', 'Bojana', 'Milic');

insert into security_user (username, password, first_name, last_name) values 
('laza', '$2a$04$Yr3QD6lbcemnrRNLbUMLBez2oEK15pdacIgfkvymQ9oMhqsEE56EK', 'Laza', 'Lazic');

insert into security_user (username, password, first_name, last_name) values 
('mika', '$2a$04$Yr3QD6lbcemnrRNLbUMLBez2oEK15pdacIgfkvymQ9oMhqsEE56EK', 'Mika', 'Mikic');

insert into security_user (username, password, first_name, last_name) values 
('tamara', '$2a$04$Yr3QD6lbcemnrRNLbUMLBez2oEK15pdacIgfkvymQ9oMhqsEE56EK', 'Tamara', 'Popovic');

-- insert authorities
insert into security_authority (name) values ('ROLE_USER'); -- normal user

-- insert mappings between users and authorities
insert into security_user_authority (user_id, authority_id) values (1, 1); -- admin has ROLE_ADMIN
insert into security_user_authority (user_id, authority_id) values (2, 1); -- petar has ROLE_USER
insert into security_user_authority (user_id, authority_id) values (3, 1); -- petar has ROLE_USER
insert into security_user_authority (user_id, authority_id) values (4, 1); -- admin has ROLE_ADMIN
insert into security_user_authority (user_id, authority_id) values (5, 1); -- petar has ROLE_USER
insert into security_user_authority (user_id, authority_id) values (6, 1); -- petar has ROLE_USER
insert into security_user_authority (user_id, authority_id) values (7, 1); -- petar has ROLE_USER
insert into security_user_authority (user_id, authority_id) values (8, 1); -- petar has ROLE_USER

INSERT INTO `social_network`.`user` (`security_user_id`) VALUES ('1');
INSERT INTO `social_network`.`user` (`security_user_id`) VALUES ('2');
INSERT INTO `social_network`.`user` (`security_user_id`) VALUES ('3');
INSERT INTO `social_network`.`user` (`security_user_id`) VALUES ('4');
INSERT INTO `social_network`.`user` (`security_user_id`) VALUES ('5');
INSERT INTO `social_network`.`user` (`security_user_id`) VALUES ('6');
INSERT INTO `social_network`.`user` (`security_user_id`) VALUES ('7');
INSERT INTO `social_network`.`user` (`security_user_id`) VALUES ('8');
