/*
 * Universidad Nacional de Costa Rica  2020
 *
 * mike@guzmanalan.com
 */

INSERT INTO public.users (id,create_date, email, enabled, first_name, last_name, password, token_expired)
VALUES (1,'2020-08-30 18:23:52.000000', 'mike@guzmanalan.com', true, 'Maikol',
        'Guzman', '1234$una', false),
       (2, '2024-04-25 10:00:00', 'sofia.mendez@example.com', true, 'Sofía',
        'Méndez', 'sofia123', false),
       (3, '2024-04-25 10:05:00', 'carlos.rojas@example.com', true, 'Carlos',
        'Rojas', 'carlos456', false),
       (4, '2024-04-25 10:10:00', 'laura.martinez@example.com', true, 'Laura',
        'Martínez', 'laura789', false),
       (5, '2024-04-25 10:15:00', 'andres.solis@example.com', true, 'Andrés',
        'Solis', 'andres321', false);