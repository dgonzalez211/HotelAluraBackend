-- Auto-generated SQL script #202307142308
INSERT INTO public.roles (id,"name")
SELECT 1,'ROLE_USER' WHERE NOT EXISTS (SELECT id FROM public.roles WHERE id = 1);
INSERT INTO public.roles (id,"name")
SELECT 2,'ROLE_ADMIN' WHERE NOT EXISTS (SELECT id FROM public.roles WHERE id = 2);

-- Auto-generated SQL script #202307142247
INSERT INTO public.users (id,email,first_name,last_name,"password",phone_number,username)
SELECT 1,'admin@hotelalura.com','Administrator','Alura','$2a$12$NXVGImJ6BHiDn0wD8gbKYOu3YKyjlCVaHfCF00G18d3Kl99qeIXXe','1234567890','admin'
WHERE NOT EXISTS (SELECT id FROM public.users WHERE id = 1);
INSERT INTO public.users (id,email,first_name,last_name,"password",phone_number,username)
SELECT 2,'user@hotelalura.com','User','Alura','$2a$12$NXVGImJ6BHiDn0wD8gbKYOu3YKyjlCVaHfCF00G18d3Kl99qeIXXe','9876543210','user'
    WHERE NOT EXISTS (SELECT id FROM public.users WHERE id = 2);

-- Auto-generated SQL script #202307142309
INSERT INTO public.users_roles (user_id,role_id)
SELECT 1,2 WHERE NOT EXISTS (SELECT user_id FROM public.users_roles WHERE user_id = 1);
INSERT INTO public.users_roles (user_id,role_id)
SELECT 2,1 WHERE NOT EXISTS (SELECT user_id FROM public.users_roles WHERE user_id = 2);

-- Auto-generated SQL script #202307142309
ALTER SEQUENCE public.roles_id_seq START 3;
ALTER SEQUENCE public.users_id_seq START 3;

INSERT INTO public.hotels (id, created_date,last_modified_date,address,email,"location",name,phone_number,rating,telephone_number)
SELECT 1, NULL,NULL,'Cl. 23 #1C-37, Comuna 2','hotel1@alurahotels.com','Santa Marta','AC Hotel By Alura Hotels','1234567890',4.5,'8736182736'
WHERE NOT EXISTS(SELECT id FROM public.hotels WHERE id = 1);

INSERT INTO public.hotels (id, created_date,last_modified_date,address,email,"location",name,phone_number,rating,telephone_number)
SELECT 2, NULL,NULL,'Bello Horizonte, Cra. 3 #142-60','hotel2@alurahotels.com','Santa Marta','Alura Resort Playa Dormida','3453453455',5.0,'2163123832'
WHERE NOT EXISTS(SELECT id FROM public.hotels WHERE id = 2);

INSERT INTO public.hotels (id, created_date,last_modified_date,address,email,"location",name,phone_number,rating,telephone_number)
SELECT 3, NULL,NULL,'Portal Del Genoves, Cl. 1A #25-40 Lote D7, Sabanilla','hotel3@alurahotels.com','Barranquilla','Alura Hotel IN','3492387202',3.0,'9182731233'
WHERE NOT EXISTS(SELECT id FROM public.hotels WHERE id = 3);

-- Auto-generated SQL script #202307201405
/*INSERT INTO public.rooms (id,available_from,available_to,description,"name",price,"type",hotel_id)
VALUES (1,'2023-07-20','2024-07-20','Amplia habitación con vista al mar.','Serenity Room',250,'DELUXE',1);
INSERT INTO public.rooms (id,available_from,available_to,description,"name",price,"type",hotel_id)
VALUES (2,'2023-07-20','2024-07-20','Lujosa suite con jacuzzi privado.','Royal Suite',400,'SUITE',1);
INSERT INTO public.rooms (id,available_from,available_to,description,"name",price,"type",hotel_id)
VALUES (3,'2023-07-20','2024-07-20','Exclusivo penthouse con terraza panorámica.','Éclat Penthouse',600,'LUXURY',2);
INSERT INTO public.rooms (id,available_from,available_to,description,"name",price,"type",hotel_id)
VALUES (4,'2023-07-20','2024-07-20','Habitación elegante con balcón privado.','Sea View Haven',230,'DELUXE',2);
INSERT INTO public.rooms (id,available_from,available_to,description,"name",price,"type",hotel_id)
VALUES (5,'2023-07-20','2024-07-20','Suite espaciosa con sala de estar independiente.','Elegance Suite',380,'SUITE',3);
INSERT INTO public.rooms (id,available_from,available_to,description,"name",price,"type",hotel_id)
VALUES (6,'2023-07-20','2024-07-20','Suite de lujo con vistas a la ciudad.','Majestic Suite',550,'LUXURY',3);*/

