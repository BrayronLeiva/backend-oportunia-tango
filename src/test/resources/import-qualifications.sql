ALTER SEQUENCE qualifications_id_seq RESTART WITH 1;
ALTER TABLE qualifications ALTER COLUMN id SET DEFAULT nextval('qualifications_id_seq');
INSERT INTO public.qualifications (name) VALUES
        ('English'),
        ('Java'),
        ('Leading'),
        ('Spring Boot');