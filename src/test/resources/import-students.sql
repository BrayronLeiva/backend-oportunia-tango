ALTER SEQUENCE students_id_seq RESTART WITH 1;
ALTER TABLE students ALTER COLUMN id_student SET DEFAULT nextval('students_id_seq');
INSERT INTO students (name_student, identification, personal_info, experience, rating_student, user_id) VALUES
        ('Maikol', '1111','None','None',0.0,1),
        ('Laura', '1111','None','None',0.0,4),
        ('Andres', '3333','None','None',0.0,5),
        ('Brayron', '1111','None','None',0.0,2);




