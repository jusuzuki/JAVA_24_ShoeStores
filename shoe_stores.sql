--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: brands; Type: TABLE; Schema: public; Owner: jusuzuki; Tablespace: 
--

CREATE TABLE brands (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE brands OWNER TO jusuzuki;

--
-- Name: brands_id_seq; Type: SEQUENCE; Schema: public; Owner: jusuzuki
--

CREATE SEQUENCE brands_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE brands_id_seq OWNER TO jusuzuki;

--
-- Name: brands_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: jusuzuki
--

ALTER SEQUENCE brands_id_seq OWNED BY brands.id;


--
-- Name: stores; Type: TABLE; Schema: public; Owner: jusuzuki; Tablespace: 
--

CREATE TABLE stores (
    id integer NOT NULL,
    name character varying,
    address character varying,
    area character varying
);


ALTER TABLE stores OWNER TO jusuzuki;

--
-- Name: stores_brands; Type: TABLE; Schema: public; Owner: jusuzuki; Tablespace: 
--

CREATE TABLE stores_brands (
    id integer NOT NULL,
    brand_id integer,
    store_id integer
);


ALTER TABLE stores_brands OWNER TO jusuzuki;

--
-- Name: stores_brands_id_seq; Type: SEQUENCE; Schema: public; Owner: jusuzuki
--

CREATE SEQUENCE stores_brands_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE stores_brands_id_seq OWNER TO jusuzuki;

--
-- Name: stores_brands_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: jusuzuki
--

ALTER SEQUENCE stores_brands_id_seq OWNED BY stores_brands.id;


--
-- Name: stores_id_seq; Type: SEQUENCE; Schema: public; Owner: jusuzuki
--

CREATE SEQUENCE stores_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE stores_id_seq OWNER TO jusuzuki;

--
-- Name: stores_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: jusuzuki
--

ALTER SEQUENCE stores_id_seq OWNED BY stores.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: jusuzuki
--

ALTER TABLE ONLY brands ALTER COLUMN id SET DEFAULT nextval('brands_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: jusuzuki
--

ALTER TABLE ONLY stores ALTER COLUMN id SET DEFAULT nextval('stores_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: jusuzuki
--

ALTER TABLE ONLY stores_brands ALTER COLUMN id SET DEFAULT nextval('stores_brands_id_seq'::regclass);


--
-- Data for Name: brands; Type: TABLE DATA; Schema: public; Owner: jusuzuki
--

COPY brands (id, name) FROM stdin;
1	Salomon
2	North Face
3	New Balance
4	Merrell
\.


--
-- Name: brands_id_seq; Type: SEQUENCE SET; Schema: public; Owner: jusuzuki
--

SELECT pg_catalog.setval('brands_id_seq', 4, true);


--
-- Data for Name: stores; Type: TABLE DATA; Schema: public; Owner: jusuzuki
--

COPY stores (id, name, address, area) FROM stdin;
3	REI	1405 NW Johnson St	NW
5	The Shoe Store	1603 NE Alberta Street	NE
6	Dick's Sporting Goods	1140 N Hayden Meadows 	N
\.


--
-- Data for Name: stores_brands; Type: TABLE DATA; Schema: public; Owner: jusuzuki
--

COPY stores_brands (id, brand_id, store_id) FROM stdin;
5	1	3
6	2	3
7	3	3
8	3	3
\.


--
-- Name: stores_brands_id_seq; Type: SEQUENCE SET; Schema: public; Owner: jusuzuki
--

SELECT pg_catalog.setval('stores_brands_id_seq', 10, true);


--
-- Name: stores_id_seq; Type: SEQUENCE SET; Schema: public; Owner: jusuzuki
--

SELECT pg_catalog.setval('stores_id_seq', 6, true);


--
-- Name: brands_pkey; Type: CONSTRAINT; Schema: public; Owner: jusuzuki; Tablespace: 
--

ALTER TABLE ONLY brands
    ADD CONSTRAINT brands_pkey PRIMARY KEY (id);


--
-- Name: stores_brands_pkey; Type: CONSTRAINT; Schema: public; Owner: jusuzuki; Tablespace: 
--

ALTER TABLE ONLY stores_brands
    ADD CONSTRAINT stores_brands_pkey PRIMARY KEY (id);


--
-- Name: stores_pkey; Type: CONSTRAINT; Schema: public; Owner: jusuzuki; Tablespace: 
--

ALTER TABLE ONLY stores
    ADD CONSTRAINT stores_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: jusuzuki
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM jusuzuki;
GRANT ALL ON SCHEMA public TO jusuzuki;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

