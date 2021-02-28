CREATE TABLE teams(
                      TeamID LONG PRIMARY KEY AUTO_INCREMENT,
                      TeamName VARCHAR(50),
                      Continent VARCHAR(50),
                      Played INT,
                      Won INT,
                      Drawn INT,
                      Lost INT
);