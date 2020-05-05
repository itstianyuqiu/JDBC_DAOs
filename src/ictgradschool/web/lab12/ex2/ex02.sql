# actor
SELECT actor_fname,actor_lname,i.* FROM pfilms_actor a JOIN pfilms_participates_in i
    ON a.actor_id = i.actor_id;
SELECT aa.*,film_title FROM pfilms_film f JOIN
  (SELECT actor_fname,actor_lname,i.* FROM pfilms_actor a JOIN pfilms_participates_in i
      ON a.actor_id = i.actor_id) aa ON aa.film_id=f.film_id;

SELECT ff.*,role_name FROM pfilms_role r JOIN
  (SELECT aa.*,film_title FROM pfilms_film f JOIN
    (SELECT actor_fname,actor_lname,i.* FROM pfilms_actor a JOIN pfilms_participates_in i
        ON a.actor_id = i.actor_id) aa ON aa.film_id=f.film_id) ff ON  ff.role_id=r.role_id
WHERE actor_fname LIKE "%Keanu%" OR actor_lname LIKE "%Keanu%";

SELECT actor_fname,actor_lname,film_title,role_name,p.* FROM pfilms_participates_in p
  JOIN pfilms_actor pa ON p.actor_id = pa.actor_id
  JOIN pfilms_film f ON p.film_id = f.film_id
  JOIN pfilms_role r ON p.role_id = r.role_id
WHERE actor_fname LIKE "%Keanu%" OR actor_lname LIKE "%Keanu%";
# film
SELECT actor_fname,actor_lname,film_title,role_name,p.* FROM pfilms_participates_in p
  JOIN pfilms_actor pa ON p.actor_id = pa.actor_id
  JOIN pfilms_film f ON p.film_id = f.film_id
  JOIN pfilms_role r ON p.role_id = r.role_id
WHERE film_title LIKE "%Angry Birds%";
# genre
