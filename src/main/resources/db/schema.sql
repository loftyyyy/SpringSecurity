CREATE TABLE IF NOT EXISTS users (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     username VARCHAR(45) NOT NULL,
                                     password VARCHAR(45) NOT NULL,
                                     enabled INT NOT NULL
);

CREATE TABLE IF NOT EXISTS authorities (
                                           id INT AUTO_INCREMENT PRIMARY KEY,
                                           username VARCHAR(45) NOT NULL,
                                            authority VARCHAR(45) NOT NULL
);
