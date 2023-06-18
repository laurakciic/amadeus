CREATE TABLE flights_search
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    departure_code CHAR(3),
    destination_code CHAR(3),
    departure_date DATE,
    return_date DATE,
    adults INT,

    date_created DATE,
    user_created VARCHAR(50),
    date_updated DATE,
    user_updated VARCHAR(50)
);