ALTER SEQUENCE locationcompany_id_seq RESTART WITH 1;
ALTER TABLE locations_company ALTER COLUMN id SET DEFAULT nextval('locationcompany_id_seq');
INSERT INTO public.locations_company(latitude, longitude, email, contact, company_id) VALUES
(9.935, -84.091, 'contact@techcorp.com', 88881234, 1),
(10.015, -84.200, 'info@healthplus.org', 88885678, 2),
(9.870, -84.120, 'hello@ecobuild.cr', 88883456, 3);