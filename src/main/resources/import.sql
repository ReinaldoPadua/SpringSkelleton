INSERT INTO usuarios (id,username, password, enabled) VALUES(1,'admin@gmail.com', '$2a$08$sFd33JOHRlbRYE4j1NDnZezggfaoRKGjAAXN10iIltiiTWhHX.q.2', true);
INSERT INTO usuarios (id,username, password, enabled) VALUES(2,'yamamoto@yahoo.com;br', '$2a$08$sFd33JOHRlbRYE4j1NDnZezggfaoRKGjAAXN10iIltiiTWhHX.q.2', true);
INSERT INTO usuarios (id,username, password, enabled) VALUES(3,'kon@hotmail.com', '$2a$08$sFd33JOHRlbRYE4j1NDnZezggfaoRKGjAAXN10iIltiiTWhHX.q.2', false);
INSERT INTO usuarios (id,username, password, enabled) VALUES(4,'ulquiorra@sad.com', '$2a$08$sFd33JOHRlbRYE4j1NDnZezggfaoRKGjAAXN10iIltiiTWhHX.q.2', false);

INSERT INTO AUTHORITY (id,AUTHORITY) VALUES(1,'ADMIN');
INSERT INTO AUTHORITY (id,AUTHORITY) VALUES(2,'USER');

INSERT INTO  AUTHORITIES_USERS (USUARIO_ID, AUTHORITY_ID) VALUES (1,1);
INSERT INTO  AUTHORITIES_USERS (USUARIO_ID, AUTHORITY_ID) VALUES (2,2);
INSERT INTO  AUTHORITIES_USERS (USUARIO_ID, AUTHORITY_ID) VALUES (3,2);
INSERT INTO  AUTHORITIES_USERS (USUARIO_ID, AUTHORITY_ID) VALUES (4,2);
