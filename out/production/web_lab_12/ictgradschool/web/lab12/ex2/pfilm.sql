DROP TABLE IF EXISTS pfilms_participates_in;
DROP TABLE IF EXISTS pfilms_produced_by;
DROP TABLE IF EXISTS pfilms_role;
DROP TABLE IF EXISTS pfilms_producer;
DROP TABLE IF EXISTS pfilms_film;
DROP TABLE IF EXISTS pfilms_genre;
DROP TABLE IF EXISTS pfilms_actor;

CREATE TABLE IF NOT EXISTS pfilms_actor (
  actor_id INT NOT NULL AUTO_INCREMENT,
  actor_fname VARCHAR(64) NOT NULL,
  actor_lname VARCHAR(64) NOT NULL,
  PRIMARY KEY(actor_id)
);

CREATE TABLE IF NOT EXISTS pfilms_genre (
  genre_name VARCHAR(64) NOT NULL,
  PRIMARY KEY(genre_name)
);

CREATE TABLE IF NOT EXISTS pfilms_film (
  film_id INT NOT NULL AUTO_INCREMENT,
  film_title VARCHAR(128) NOT NULL,
  genre_name VARCHAR(64) NOT NULL,
  PRIMARY KEY(film_id),
  FOREIGN KEY (genre_name) REFERENCES pfilms_genre(genre_name)
);

CREATE TABLE IF NOT EXISTS pfilms_producer (
  producer_id INT NOT NULL AUTO_INCREMENT,
  producer_fname VARCHAR(64) NOT NULL,
  producer_lname VARCHAR(64) NOT NULL,
  PRIMARY KEY(producer_id)
);

CREATE TABLE IF NOT EXISTS pfilms_role (
  role_id INT NOT NULL AUTO_INCREMENT,
  role_name VARCHAR(64) NOT NULL,
  PRIMARY KEY(role_id)
);


CREATE TABLE IF NOT EXISTS pfilms_produced_by (
  film_id INT NOT NULL,
  producer_id INT NOT NULL,
  PRIMARY KEY(film_id, producer_id),
  FOREIGN KEY (film_id) REFERENCES pfilms_film(film_id),
  FOREIGN KEY (producer_id) REFERENCES pfilms_producer(producer_id)
);

CREATE TABLE IF NOT EXISTS pfilms_participates_in (
  actor_id INT NOT NULL,
  film_id INT NOT NULL,
  role_id INT NOT NULL,
  PRIMARY KEY(actor_id, film_id),
  FOREIGN KEY (actor_id) REFERENCES pfilms_actor(actor_id),
  FOREIGN KEY (film_id) REFERENCES pfilms_film(film_id),
  FOREIGN KEY (role_id) REFERENCES pfilms_role(role_id)
);

INSERT INTO pfilms_actor (actor_fname, actor_lname) VALUES
  ('Jack', 'Black'),
  ('Jason', 'Sudeikis'),
  ('Peter', 'Dinklage'),
  ('Clay', 'Kaytis'),
  ('Keanu', 'Reeves');

INSERT INTO pfilms_genre (genre_name) VALUES
  ('Horror'),
  ('Surreal'),
  ('Action');

INSERT INTO pfilms_film (film_title, genre_name) VALUES
  ('Angry Birds', 'Horror'),
  ('The Matrix', 'Action'),
  ('John Wick', 'Action'),
  ('Rubber', 'Surreal'),
  ('Alice', 'Surreal');

INSERT INTO pfilms_producer (producer_fname, producer_lname) VALUES
  ('Megan', 'Ellison'),
  ('Michael', 'Bay');

INSERT INTO pfilms_role (role_name) VALUES
  ('Lead'),
  ('Support'),
  ('Director');

INSERT INTO pfilms_produced_by VALUES
  (1, 1),
  (2, 2),
  (3, 2),
  (4, 1),
  (5, 1),
  (5, 2);

INSERT INTO pfilms_participates_in VALUES
  (1, 2, 1),
  (1, 3, 2),
  (1, 4, 3),
  (2, 5, 1),
  (3, 5, 1),
  (4, 1, 1),
  (5, 1, 1),
  (5, 3, 2);