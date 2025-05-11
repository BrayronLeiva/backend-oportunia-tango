ALTER SEQUENCE users_id_seq RESTART WITH 1;
ALTER TABLE users ALTER COLUMN id SET DEFAULT nextval('users_id_seq');

INSERT INTO public.users (create_date, email, enabled, first_name, last_name, password, token_expired)
VALUES ('2020-08-30 18:23:52.000000', 'mike@guzmanalan.com', true, 'Maikol',
        'Guzman', '$2a$10$rNuc31nzTm5.ajvMZEp.8.d8eCy4CRMfhl/x4PGhoBHHjlZ6k2o9m', false),
       ('2024-04-25 10:00:00', 'sofia.mendez@example.com', true, 'Sofía',
        'Méndez', '$2a$10$xNh8gcZovLbRQ9NXKp4Qje28oeF4rGiaNhnN0IUFqBumEplakk7yi', false),
       ('2024-04-25 10:05:00', 'carlos.rojas@example.com', true, 'Carlos',
        'Rojas', '$2a$10$Rsd91jhNhQv/gpKXrXjuiuZLEVW.EO9REr9mSTaZ.fXJyjeXdeSXG', false),
       ('2024-04-25 10:10:00', 'laura.martinez@example.com', true, 'Laura',
        'Martínez', '$2a$10$XjIV5KJKIx9eSsIfGmsP4u9C3iEB1MBwaNq9.SFU8Ytd5u84HUToO', false),
       ( '2024-04-25 10:15:00', 'andres.solis@example.com', true, 'Andrés',
        'Solis', '$2a$10$ezFTxLYZTg04BN9qMeCfG.IEvKP1wTIZm2GIkoO64clRW0x4lYkRC', false),
       ( '2024-04-26 11:15:00', 'pepe.mendez@example.com', true, 'Pepe',
        'Mendez', '$2a$10$ulNdX/6qMjELBuFL1QHvFO1mzKnapMjGhGsY.DvdBkeWTJ.L411cK', false),
       ('2024-04-26 12:00:00', 'mariana.torres@example.com', true, 'Mariana',
        'Torres', '$2a$10$P3zqEfjVQwu0zzjw7MkeaOjZgHtUAzpF8SC02nlXf0safaxUqOC2G', false),
       ('2024-04-26 12:30:00', 'diego.ramirez@example.com', true, 'Diego',
        'Ramírez', '$2a$10$o3vLov/lamF/Tah46dRmsukcdYSgiioI4LWlCXeZjPVZOwgdd9ove', false);

-- mike@guzmanalan.com, password: 1234
-- sofia.mendez@example.com, password: sofia123
-- carlos.rojas@example.com, password: carlos456
-- laura.martinez@example.com, password: laura789
-- andres.solis@example.com, password: andres321
-- pepe.mendez@example.com, password: pepe124
-- mariana.torres@example.com, password: mariana789
-- diego.ramirez@example.com, password: diego456


