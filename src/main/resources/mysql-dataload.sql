
DROP TABLE IF EXISTS OFFICERS;
CREATE TABLE OFFICERS (

  id  BIGINT NOT NULL  AUTO_INCREMENT,
  officer_rank VARCHAR(30) NOT NULL,
  first_name  VARCHAR (30) NOT NULL,
  last_name   VARCHAR(30) NOT NULL,
  PRIMARY KEY (id)

);


INSERT INTO  OFFICERS (officer_rank, first_name, last_name) VALUES ('CAPTAIN', 'Cherno', 'Jallow');
INSERT INTO OFFICERS (officer_rank, first_name, last_name) VALUES ('CAPTAIN', 'Lamin', 'Cham');
INSERT INTO OFFICERS (officer_rank, first_name, last_name) VALUES ('CAPTAIN', 'Ebrima', 'Jobe');
INSERT INTO OFFICERS (officer_rank, first_name, last_name) VALUES ('CAPTAIN', 'Samba', 'Faal');
INSERT INTO OFFICERS (officer_rank, first_name, last_name) VALUES ('CAPTAIN', 'August', 'Gomez');