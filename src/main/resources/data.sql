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

