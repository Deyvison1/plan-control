-- public.categories definição

-- Drop table

-- DROP TABLE public.categories;

CREATE TABLE plan_control.categories (
                                   created_at timestamp(6) NOT NULL,
                                   "uuid" uuid NOT NULL,
                                   updated_at timestamp(6) NULL,
                                   description varchar(255) NULL,
                                   "name" varchar(255) NULL,
                                   "user_update_id" uuid NOT NULL,
                                   CONSTRAINT categories_pkey PRIMARY KEY (uuid)
);

-- Permissions

ALTER TABLE plan_control.categories OWNER TO postgres;
GRANT ALL ON TABLE plan_control.categories TO postgres;