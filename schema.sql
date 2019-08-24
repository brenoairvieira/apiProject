CREATE SEQUENCE tbcliente_codigo_seq;

CREATE TABLE public.tbcliente
(
    nome text COLLATE pg_catalog."default" NOT NULL,
    idade integer NOT NULL,
    codigo integer NOT NULL DEFAULT nextval('tbcliente_codigo_seq'::regclass),
    temperatura_minima numeric NOT NULL,
    temperatura_maxima numeric NOT NULL,
    CONSTRAINT tbcliente_pkey PRIMARY KEY (codigo)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.tbcliente
    OWNER to "apiProject";