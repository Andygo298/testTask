DROP TABLE IF EXISTS TOWN;

CREATE TABLE TOWN (
                              id LONG AUTO_INCREMENT  PRIMARY KEY,
                              town_name VARCHAR(250) default NULL,
                              population LONG default NULL,
                              native_language VARCHAR(50) default NULL,
                              town_info VARCHAR(5000) default NULL

);

INSERT INTO TOWN (town_name,population,native_language,town_info) VALUES
('Minsk', 2145000,'Belorussian, Russian','This is a good town!!!'),
('Moskow', 22550245,'Russian','This is a good and very big town!!!'),
('Borisov', 125321,'Belorussian, Russian','This is a small town!!!'),
('Sochi', 145000,'Russian','This is a great town on the shore of the sea!!!'),
('Volgograd', 2051012,'Russian','This is the town on the shore of the river Volga!!!'),
('Hrodno', 714500,'Belorussian, Russian','This is a belorussian town on the east of Belarus!!!');