-- public.products definição

-- Drop table

-- DROP TABLE public.products;

CREATE TABLE plan_control.products (
                                 speed_download int4 NULL,
                                 speed_upload int4 NULL,
                                 taxa_adesao numeric(38, 2) NULL,
                                 value numeric(38, 2) NULL,
                                 value_wifi numeric(38, 2) NULL,
                                 category_uuid uuid NOT NULL,
                                 created_at timestamp(6) NOT NULL,
                                 "uuid" uuid NOT NULL,
                                 updated_at timestamp(6) NULL,
                                 description varchar(255) NULL,
                                 "name" varchar(255) NULL,
                                 CONSTRAINT products_pkey PRIMARY KEY (uuid)
);

-- Permissions

ALTER TABLE plan_control.products OWNER TO postgres;
GRANT ALL ON TABLE plan_control.products TO postgres;


-- public.products chaves estrangeiras

ALTER TABLE plan_control.products ADD CONSTRAINT fkog2rp4qthbtt2lfyhfo32lsw9 FOREIGN KEY (category_uuid) REFERENCES plan_control.categories(uuid);