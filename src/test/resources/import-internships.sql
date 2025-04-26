ALTER SEQUENCE internships_id_seq RESTART WITH 1;
ALTER TABLE internships ALTER COLUMN id SET DEFAULT nextval('internships_id_seq');
INSERT INTO public.internships(details) VALUES ('Internship1'),('Internship2'),('Internship3');