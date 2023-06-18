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

CREATE TABLE search_results
(
  id INT PRIMARY KEY AUTO_INCREMENT,
  outbound_departure_airport VARCHAR(3),
  outbound_arrival_airport VARCHAR(3),
  outbound_departure_date VARCHAR(25),
  outbound_arrival_date VARCHAR(25),

  inbound_departure_airport VARCHAR(3),
  inbound_arrival_airport VARCHAR(3),
  inbound_departure_date VARCHAR(25),
  inbound_arrival_date VARCHAR(25),

  carrier VARCHAR(50),
  price VARCHAR(10),

  search_id INT,

  date_created DATE,
  user_created VARCHAR(50),
  date_updated DATE,
  user_updated VARCHAR(50)
);

ALTER TABLE search_results ADD FOREIGN KEY (search_id) REFERENCES flights_search(id);