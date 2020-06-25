-- SEQUENCE: public.users_id_seq1

-- DROP SEQUENCE public.users_id_seq1;

CREATE SEQUENCE public.users_id_seq1
    INCREMENT 1
    START 7
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.users_id_seq1
    OWNER TO postgres;

-- Table: public.users

-- DROP TABLE public.users;

CREATE TABLE public.users
(
    id bigint NOT NULL DEFAULT nextval('users_id_seq1'::regclass),
    email character varying(255) COLLATE pg_catalog."default",
    password character varying(255) COLLATE pg_catalog."default",
    phone character varying(11) COLLATE pg_catalog."default",
    username character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT users_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.users
    OWNER to postgres;


-- Table: public.role

-- DROP TABLE public.role;

CREATE TABLE public.role
(
    id bigint NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT role_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.role
    OWNER to postgres;
	
	
	


-- Table: public.users_roles

-- DROP TABLE public.users_roles;

CREATE TABLE public.users_roles
(
    user_id bigint NOT NULL,
    roles_id bigint NOT NULL,
    CONSTRAINT users_roles_pkey PRIMARY KEY (user_id, roles_id),
    CONSTRAINT fk15d410tj6juko0sq9k4km60xq FOREIGN KEY (roles_id)
        REFERENCES public.role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk2o0jvgh89lemvvo17cbqvdxaa FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.users_roles
    OWNER to postgres;
	
	
	