-- DROP SCHEMA public;

CREATE SCHEMA public AUTHORIZATION postgres;

COMMENT ON SCHEMA public IS 'standard public schema';

-- DROP SEQUENCE public.business_object_object_id_seq;

CREATE SEQUENCE public.business_object_object_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.favourite_favourite_id_seq;

CREATE SEQUENCE public.favourite_favourite_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.label_label_id_seq;

CREATE SEQUENCE public.label_label_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.source_system_source_id_seq;

CREATE SEQUENCE public.source_system_source_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.user_user_id_seq;

CREATE SEQUENCE public.user_user_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
	CACHE 1
	NO CYCLE;-- public."label" definition

-- Drop table

-- DROP TABLE public."label";

CREATE TABLE public."label" (
                                label_id bigserial NOT NULL,
                                "name" varchar NOT NULL,
                                CONSTRAINT label_pk PRIMARY KEY (label_id)
);
CREATE UNIQUE INDEX label_label_id_uindex ON public.label USING btree (label_id);
CREATE UNIQUE INDEX label_name_uindex ON public.label USING btree (name);


-- public.source_system definition

-- Drop table

-- DROP TABLE public.source_system;

CREATE TABLE public.source_system (
                                      source_id bigserial NOT NULL,
                                      "name" varchar NOT NULL,
                                      CONSTRAINT source_system_pk PRIMARY KEY (source_id)
);
CREATE UNIQUE INDEX source_system_name_uindex ON public.source_system USING btree (name);
CREATE UNIQUE INDEX source_system_source_id_uindex ON public.source_system USING btree (source_id);


-- public."user" definition

-- Drop table

-- DROP TABLE public."user";

CREATE TABLE public."user" (
                               user_id bigserial NOT NULL,
                               first_name varchar NULL,
                               last_name varchar NULL,
                               username varchar NOT NULL,
                               email varchar NOT NULL,
                               "password" varchar NOT NULL,
                               CONSTRAINT user_pk PRIMARY KEY (user_id)
);
CREATE UNIQUE INDEX "user_e-mail_uindex" ON public."user" USING btree (email);
CREATE UNIQUE INDEX user_username_uindex ON public."user" USING btree (username);


-- public.business_object definition

-- Drop table

-- DROP TABLE public.business_object;

CREATE TABLE public.business_object (
                                        object_id bigserial NOT NULL,
                                        "name" varchar NOT NULL,
                                        description varchar NULL,
                                        source_id int8 NOT NULL,
                                        CONSTRAINT business_object_pk PRIMARY KEY (object_id),
                                        CONSTRAINT business_object_source_fk FOREIGN KEY (source_id) REFERENCES public.source_system(source_id)
);
CREATE UNIQUE INDEX business_object_object_id_uindex ON public.business_object USING btree (object_id);


-- public.favourite definition

-- Drop table

-- DROP TABLE public.favourite;

CREATE TABLE public.favourite (
                                  favourite_id bigserial NOT NULL,
                                  user_id int8 NOT NULL,
                                  object_id int8 NOT NULL,
                                  CONSTRAINT favourite_pk PRIMARY KEY (favourite_id),
                                  CONSTRAINT favourite_business_object_object_id_fk FOREIGN KEY (object_id) REFERENCES public.business_object(object_id),
                                  CONSTRAINT favourite_user_user_id_fk FOREIGN KEY (user_id) REFERENCES public."user"(user_id)
);
CREATE UNIQUE INDEX favourite_favourite_id_uindex ON public.favourite USING btree (favourite_id);


-- public.bo_2_bo definition

-- Drop table

-- DROP TABLE public.bo_2_bo;

CREATE TABLE public.bo_2_bo (
                                object_id_1 int8 NOT NULL,
                                object_id_2 int8 NOT NULL,
                                CONSTRAINT bo_2_bo_business_object_object_id_fk FOREIGN KEY (object_id_1) REFERENCES public.business_object(object_id),
                                CONSTRAINT bo_2_bo_business_object_object_id_fk_2 FOREIGN KEY (object_id_2) REFERENCES public.business_object(object_id)
);


-- public.bo_2_label definition

-- Drop table

-- DROP TABLE public.bo_2_label;

CREATE TABLE public.bo_2_label (
                                   label_id int8 NOT NULL,
                                   object_id int8 NOT NULL,
                                   CONSTRAINT bo_2_label_business_object_object_id_fk FOREIGN KEY (object_id) REFERENCES public.business_object(object_id),
                                   CONSTRAINT bo_2_label_business_object_object_id_fk_2 FOREIGN KEY (object_id) REFERENCES public.business_object(object_id),
                                   CONSTRAINT bo_2_label_label_label_id_fk FOREIGN KEY (label_id) REFERENCES public."label"(label_id)
);
