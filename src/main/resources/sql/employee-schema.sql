CREATE TABLE employee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    username VARCHAR(40) NOT NULL,
    password VARCHAR(20) NOT NULL,
    address VARCHAR(60) DEFAULT NULL,
    contact VARCHAR(60) DEFAULT NULL
)
