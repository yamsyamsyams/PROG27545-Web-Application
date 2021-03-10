INSERT INTO sec_user (email, encryptedPassword, enabled)
VALUES ('ahmad.soleimani@sheridancollege.ca', '$2a$10$Etp6oZzxQgVduD4TfDGHpub2UQOTenXCSFT95gAzErLNHzRmQ1Roe', 1);

INSERT INTO sec_user (email, encryptedPassword, enabled)
VALUES ('guest.guest@sheridancollege.ca', '$2a$10$XipvJzA.xbQkeb525qVbhe6fJEK90jCKzm1Lqmj7/R7G23BPCT1Jy', 1);

 
INSERT INTO sec_role (roleName)
VALUES ('ROLE_ADMIN');
 
INSERT INTO sec_role (roleName)
VALUES ('ROLE_USER');
 

 
INSERT INTO user_role (userId, roleId)
VALUES (1, 1);
 
INSERT INTO user_role (userId, roleId)
VALUES (1, 2);
 
INSERT INTO user_role (userId, roleId)
VALUES (2, 2);

