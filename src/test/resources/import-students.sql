ALTER SEQUENCE students_id_seq RESTART WITH 1;
ALTER TABLE students ALTER COLUMN id SET DEFAULT nextval('students_id_seq');
INSERT INTO students (name, identification, personal_info, experience, rating, user_id) VALUES
        ('Maikol', '1111','None','None',0.0,1),
        ('Laura', '1111','None','None',0.0,4),
        ('Andres', '1111','None','None',0.0,5),
        ('Brayron', '1111','None','None',0.0,2);
INSERT INTO students (id, name, identification, personal_info, experience, rating, user_id) VALUES
        (1, 'Maikol', '1111','None','None',0.0,1),
        (2, 'Laura', '2222','None','None',0.0,4),
        (3, 'Andres', '3333','None','None',0.0,5);



