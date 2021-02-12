--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1
-- Dumped by pg_dump version 13.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: actions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.actions (
    id integer NOT NULL,
    description character varying(255),
    expiry_date date,
    pharmacy_actions integer
);


ALTER TABLE public.actions OWNER TO postgres;

--
-- Name: address; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.address (
    address_id integer NOT NULL,
    country character varying(255),
    number integer,
    postal_code integer,
    street character varying(255),
    town character varying(255)
);


ALTER TABLE public.address OWNER TO postgres;

--
-- Name: authority; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.authority (
    id integer NOT NULL,
    name character varying(255)
);


ALTER TABLE public.authority OWNER TO postgres;

--
-- Name: authority_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.authority_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.authority_id_seq OWNER TO postgres;

--
-- Name: authority_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.authority_id_seq OWNED BY public.authority.id;


--
-- Name: complaint; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.complaint (
    id integer NOT NULL,
    answer character varying(255),
    answered boolean,
    massage character varying(255),
    subject character varying(255),
    version bigint,
    dermatologist_id integer,
    patient_id integer NOT NULL,
    pharmacist_id integer,
    pharmacy_id integer
);


ALTER TABLE public.complaint OWNER TO postgres;

--
-- Name: confirmation_token; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.confirmation_token (
    token_id bigint NOT NULL,
    confirmation_token character varying(255),
    created_date timestamp without time zone,
    person_user_id integer
);


ALTER TABLE public.confirmation_token OWNER TO postgres;

--
-- Name: consulting; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.consulting (
    consulting_id integer NOT NULL,
    cancelled boolean,
    date date,
    duration double precision,
    information character varying(255),
    price double precision,
    showed_up boolean,
    start_time time without time zone,
    patient_id integer,
    pharmacist_id integer
);


ALTER TABLE public.consulting OWNER TO postgres;

--
-- Name: dermatologists_pharmacies; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.dermatologists_pharmacies (
    dermatologist_id integer NOT NULL,
    pharmacy_id integer NOT NULL
);


ALTER TABLE public.dermatologists_pharmacies OWNER TO postgres;

--
-- Name: eprescriptions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.eprescriptions (
    id integer NOT NULL,
    code character varying(255) NOT NULL,
    date date NOT NULL,
    pharmacy_id integer NOT NULL,
    patient_id integer NOT NULL
);


ALTER TABLE public.eprescriptions OWNER TO postgres;

--
-- Name: examination; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.examination (
    id integer NOT NULL,
    cancelled boolean,
    information character varying(255),
    showed_up boolean,
    examination_id integer,
    patient_id integer NOT NULL
);


ALTER TABLE public.examination OWNER TO postgres;

--
-- Name: examination_schedule; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.examination_schedule (
    id integer NOT NULL,
    date date,
    duration bigint,
    finished boolean,
    price double precision,
    start_time time without time zone,
    dermatologist_id integer NOT NULL,
    pharmacy_id integer
);


ALTER TABLE public.examination_schedule OWNER TO postgres;

--
-- Name: friday_schedule; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.friday_schedule (
    id integer NOT NULL,
    end_time time without time zone,
    start_time time without time zone
);


ALTER TABLE public.friday_schedule OWNER TO postgres;

--
-- Name: grading; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.grading (
    id integer NOT NULL,
    grade integer,
    graded_id integer,
    type character varying(255),
    patient_id integer NOT NULL
);


ALTER TABLE public.grading OWNER TO postgres;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- Name: holiday_schedule_dermatologist; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.holiday_schedule_dermatologist (
    id integer NOT NULL,
    approved character varying(255),
    end_date date,
    massage character varying(255),
    start_date date,
    type character varying(255),
    dermatologist_id integer NOT NULL
);


ALTER TABLE public.holiday_schedule_dermatologist OWNER TO postgres;

--
-- Name: holiday_schedule_pharmacist; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.holiday_schedule_pharmacist (
    id integer NOT NULL,
    approved character varying(255),
    end_date date,
    massage character varying(255),
    start_date date,
    type character varying(255),
    version integer,
    pharmacist_id integer NOT NULL
);


ALTER TABLE public.holiday_schedule_pharmacist OWNER TO postgres;

--
-- Name: lack_medicine; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.lack_medicine (
    id integer NOT NULL,
    name_medicine character varying(255),
    name_pharmacy character varying(255)
);


ALTER TABLE public.lack_medicine OWNER TO postgres;

--
-- Name: loyalty_program; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.loyalty_program (
    id integer NOT NULL,
    consulting_points double precision,
    examination_points double precision,
    gold_limit double precision,
    golden_discount double precision,
    regular_discount double precision,
    regular_limit double precision,
    silver_discount double precision,
    silver_limit double precision,
    version bigint
);


ALTER TABLE public.loyalty_program OWNER TO postgres;

--
-- Name: mark; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mark (
    id integer NOT NULL,
    mark_five integer,
    mark_four integer,
    mark_one integer,
    mark_three integer,
    mark_two integer,
    patients_mark integer,
    patient_id integer,
    pharmacy_id integer
);


ALTER TABLE public.mark OWNER TO postgres;

--
-- Name: mark_dermatologist; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mark_dermatologist (
    id integer NOT NULL,
    mark_five integer,
    mark_four integer,
    mark_one integer,
    mark_three integer,
    mark_two integer,
    patients_mark integer,
    dermatologist_id integer,
    patient_id integer
);


ALTER TABLE public.mark_dermatologist OWNER TO postgres;

--
-- Name: mark_medication; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mark_medication (
    id integer NOT NULL,
    mark_five integer,
    mark_four integer,
    mark_one integer,
    mark_three integer,
    mark_two integer,
    patients_mark integer,
    medication_id integer,
    patient_id integer
);


ALTER TABLE public.mark_medication OWNER TO postgres;

--
-- Name: mark_pharmacist; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mark_pharmacist (
    id integer NOT NULL,
    mark_five integer,
    mark_four integer,
    mark_one integer,
    mark_three integer,
    mark_two integer,
    patients_mark integer,
    patient_id integer,
    pharmacist_id integer
);


ALTER TABLE public.mark_pharmacist OWNER TO postgres;

--
-- Name: medication; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.medication (
    id integer NOT NULL,
    code bigint,
    form character varying(255),
    issuance_regime character varying(255),
    loyalty_points double precision,
    mark double precision,
    name character varying(255),
    type character varying(255),
    way_of_selling character varying(255),
    specification_id integer
);


ALTER TABLE public.medication OWNER TO postgres;

--
-- Name: medication_in_order; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.medication_in_order (
    id integer NOT NULL,
    quantity integer,
    medication_id integer,
    order_id integer
);


ALTER TABLE public.medication_in_order OWNER TO postgres;

--
-- Name: medication_price; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.medication_price (
    id integer NOT NULL,
    date date,
    price double precision,
    quantity integer,
    medication_id integer,
    pharmacy_id integer
);


ALTER TABLE public.medication_price OWNER TO postgres;

--
-- Name: medication_price_history; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.medication_price_history (
    id integer NOT NULL,
    end_date date NOT NULL,
    medication integer NOT NULL,
    pharmacy integer NOT NULL,
    price double precision NOT NULL,
    start_date date NOT NULL
);


ALTER TABLE public.medication_price_history OWNER TO postgres;

--
-- Name: medication_reservation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.medication_reservation (
    id integer NOT NULL,
    cancelled boolean,
    collected boolean,
    date_of_reservation date,
    date_of_take_over date,
    reservation_code uuid,
    medicine integer,
    patient_id integer,
    pharmacy_id integer NOT NULL
);


ALTER TABLE public.medication_reservation OWNER TO postgres;

--
-- Name: medications_alternatives; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.medications_alternatives (
    medication_id integer NOT NULL,
    medication_alternative_id integer NOT NULL
);


ALTER TABLE public.medications_alternatives OWNER TO postgres;

--
-- Name: medications_eprescription; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.medications_eprescription (
    id integer NOT NULL,
    code bigint,
    name character varying(255),
    quantity integer,
    e_prescription_medication integer
);


ALTER TABLE public.medications_eprescription OWNER TO postgres;

--
-- Name: monday_schedule; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.monday_schedule (
    id integer NOT NULL,
    end_time time without time zone,
    start_time time without time zone
);


ALTER TABLE public.monday_schedule OWNER TO postgres;

--
-- Name: my_seq_genv2; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.my_seq_genv2
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.my_seq_genv2 OWNER TO postgres;

--
-- Name: my_seqv2; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.my_seqv2
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.my_seqv2 OWNER TO postgres;

--
-- Name: offer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.offer (
    id integer NOT NULL,
    date_of_delivery date,
    status character varying(255),
    summary_price double precision,
    order_id integer,
    supplier integer
);


ALTER TABLE public.offer OWNER TO postgres;

--
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    id integer NOT NULL,
    date date,
    status character varying(255),
    pharmacy_admin_id integer
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- Name: patients_medication_alergy; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.patients_medication_alergy (
    id integer NOT NULL,
    description character varying(255),
    medication_id integer NOT NULL,
    patient_id integer NOT NULL
);


ALTER TABLE public.patients_medication_alergy OWNER TO postgres;

--
-- Name: patients_subscriptions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.patients_subscriptions (
    patient_id integer NOT NULL,
    pharmacy_id integer NOT NULL
);


ALTER TABLE public.patients_subscriptions OWNER TO postgres;

--
-- Name: person_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.person_user (
    type character varying(31) NOT NULL,
    id integer NOT NULL,
    email character varying(255),
    enabled boolean,
    first_logged boolean,
    last_password_reset_date timestamp without time zone,
    firstname character varying(255),
    password character varying(255),
    phone_number character varying(255),
    surname character varying(255),
    mark_dermatologist double precision,
    discount double precision,
    loyalty_category character varying(255),
    penalties integer,
    points integer,
    mark_pharmacist double precision,
    main_admin boolean,
    address_address_id integer,
    pharmacy_pharmacist integer,
    pharmacy_pharmacy_admin integer
);


ALTER TABLE public.person_user OWNER TO postgres;

--
-- Name: pharmacy_table; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pharmacy_table (
    id integer NOT NULL,
    consulting_price double precision,
    description character varying(255),
    mark double precision,
    pharmacy_name character varying(255),
    address_id integer NOT NULL
);


ALTER TABLE public.pharmacy_table OWNER TO postgres;

--
-- Name: prescription; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.prescription (
    id integer NOT NULL,
    date date NOT NULL,
    duration_of_therapy double precision NOT NULL,
    information character varying(255) NOT NULL,
    taken boolean NOT NULL,
    patient_id integer NOT NULL,
    pharmacy_id integer NOT NULL
);


ALTER TABLE public.prescription OWNER TO postgres;

--
-- Name: prescription_medications; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.prescription_medications (
    medication_id integer NOT NULL,
    prescription_id integer NOT NULL
);


ALTER TABLE public.prescription_medications OWNER TO postgres;

--
-- Name: saturday_schedule; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.saturday_schedule (
    id integer NOT NULL,
    end_time time without time zone,
    start_time time without time zone
);


ALTER TABLE public.saturday_schedule OWNER TO postgres;

--
-- Name: selling_statistics; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.selling_statistics (
    id integer NOT NULL,
    date date,
    medication_code bigint,
    price double precision,
    quantity integer
);


ALTER TABLE public.selling_statistics OWNER TO postgres;

--
-- Name: specification; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.specification (
    id integer NOT NULL,
    contra_indications character varying(255),
    manufacturer character varying(255),
    recommended_consumption character varying(255),
    structure character varying(255)
);


ALTER TABLE public.specification OWNER TO postgres;

--
-- Name: sunday_schedule; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sunday_schedule (
    id integer NOT NULL,
    end_time time without time zone,
    start_time time without time zone
);


ALTER TABLE public.sunday_schedule OWNER TO postgres;

--
-- Name: supplier_medications; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.supplier_medications (
    id integer NOT NULL,
    code bigint,
    name character varying(255),
    quantity integer,
    reserved_quantity integer,
    supplier_id integer
);


ALTER TABLE public.supplier_medications OWNER TO postgres;

--
-- Name: thursday_schedule; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.thursday_schedule (
    id integer NOT NULL,
    end_time time without time zone,
    start_time time without time zone
);


ALTER TABLE public.thursday_schedule OWNER TO postgres;

--
-- Name: tuesday_schedule; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tuesday_schedule (
    id integer NOT NULL,
    end_time time without time zone,
    start_time time without time zone
);


ALTER TABLE public.tuesday_schedule OWNER TO postgres;

--
-- Name: user_authority; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_authority (
    user_id integer NOT NULL,
    authority_id integer NOT NULL
);


ALTER TABLE public.user_authority OWNER TO postgres;

--
-- Name: wednesday_schedule; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.wednesday_schedule (
    id integer NOT NULL,
    end_time time without time zone,
    start_time time without time zone
);


ALTER TABLE public.wednesday_schedule OWNER TO postgres;

--
-- Name: working_hours_dermatologist; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.working_hours_dermatologist (
    id integer NOT NULL,
    dermatologist_id integer,
    friday_schedule integer NOT NULL,
    monday_schedule integer,
    pharmacy_id integer,
    saturday_schedule integer NOT NULL,
    sunday_schedule integer NOT NULL,
    thursday_schedule integer,
    tuesday_schedule integer,
    wednesday_schedule integer
);


ALTER TABLE public.working_hours_dermatologist OWNER TO postgres;

--
-- Name: working_hours_pharmacist; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.working_hours_pharmacist (
    id integer NOT NULL,
    friday_schedule integer NOT NULL,
    monday_schedule integer,
    pharmacist_id integer,
    saturday_schedule integer NOT NULL,
    sunday_schedule integer NOT NULL,
    thursday_schedule integer,
    tuesday_schedule integer,
    wednesday_schedule integer
);


ALTER TABLE public.working_hours_pharmacist OWNER TO postgres;

--
-- Name: authority id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authority ALTER COLUMN id SET DEFAULT nextval('public.authority_id_seq'::regclass);


--
-- Data for Name: actions; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.actions (id, description, expiry_date, pharmacy_actions) VALUES (1, 'Andol on sale 20%', '2021-05-06', 11);
INSERT INTO public.actions (id, description, expiry_date, pharmacy_actions) VALUES (2, 'Defrnol -10%`', '2021-05-06', 11);


--
-- Data for Name: address; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.address (address_id, country, number, postal_code, street, town) VALUES (2, 'Serbia', 2, 21000, 'Street 2', 'Novi Sad');
INSERT INTO public.address (address_id, country, number, postal_code, street, town) VALUES (4, 'Serbia', 423, 22222, 'Beogradska', 'Beograd');
INSERT INTO public.address (address_id, country, number, postal_code, street, town) VALUES (6, 'Serbia', 43, 21000, 'Puskinova', 'Novi Sad');
INSERT INTO public.address (address_id, country, number, postal_code, street, town) VALUES (8, 'Serbia', 432, 432, 'Niska', 'Nis');
INSERT INTO public.address (address_id, country, number, postal_code, street, town) VALUES (10, 'Serbia', 32, 21000, 'Gogoljeva', 'Novi Sad');
INSERT INTO public.address (address_id, country, number, postal_code, street, town) VALUES (12, 'Serbia', 9, 21000, 'Puskinova', 'Novi Sad');
INSERT INTO public.address (address_id, country, number, postal_code, street, town) VALUES (14, 'Serbia', 22, 21000, 'Petra Drapsina', 'Novi Sad');
INSERT INTO public.address (address_id, country, number, postal_code, street, town) VALUES (16, 'Serbia', 2, 21000, 'Pavla Simica', 'Novi Sad');
INSERT INTO public.address (address_id, country, number, postal_code, street, town) VALUES (18, 'Serbia', 2, 21000, 'Gogoljeva', 'Novi Sad');
INSERT INTO public.address (address_id, country, number, postal_code, street, town) VALUES (20, 'Serbia', 4, 21000, 'Mise Dimitrijevica', 'Novi Sad');
INSERT INTO public.address (address_id, country, number, postal_code, street, town) VALUES (22, 'Serbia', 123, 21476, 'Fruskogorska', 'Nis');
INSERT INTO public.address (address_id, country, number, postal_code, street, town) VALUES (24, 'Serbia', 33, 21000, 'Bulevar Oslobodjenja', 'Novi Sad');
INSERT INTO public.address (address_id, country, number, postal_code, street, town) VALUES (26, 'Serbia', 444, 21000, 'Partizanska', 'Novi Sad');
INSERT INTO public.address (address_id, country, number, postal_code, street, town) VALUES (28, 'Serbia', 22, 21000, 'Kisacka', 'Novi Sad');
INSERT INTO public.address (address_id, country, number, postal_code, street, town) VALUES (32, 'Serbia', 33, 21000, 'Brace Ribnikar', 'Novi Sad');
INSERT INTO public.address (address_id, country, number, postal_code, street, town) VALUES (34, 'Serbia', 45678, 21000, 'Kosovska', 'Novi Sad');
INSERT INTO public.address (address_id, country, number, postal_code, street, town) VALUES (36, 'Serbia', 3, 21000, 'Njegoseva', 'Novi Sad');
INSERT INTO public.address (address_id, country, number, postal_code, street, town) VALUES (37, 'Serbia', 3, 21000, 'Njegoseva', 'Novi Sad');
INSERT INTO public.address (address_id, country, number, postal_code, street, town) VALUES (38, 'Serbia', 3, 21000, 'Njegoseva', 'Novi Sad');
INSERT INTO public.address (address_id, country, number, postal_code, street, town) VALUES (40, 'Serbia', 33, 21000, 'Bulevar Despota Stefana', 'Novi Sad');
INSERT INTO public.address (address_id, country, number, postal_code, street, town) VALUES (42, 'Serbia', 33, 21000, 'Novosadskog Sajma', 'Novi Sad');
INSERT INTO public.address (address_id, country, number, postal_code, street, town) VALUES (44, 'Serbia', 12, 21000, 'Nikole Pasica', 'Novi Sad');
INSERT INTO public.address (address_id, country, number, postal_code, street, town) VALUES (46, 'Serbia', 12, 21000, 'Alekse Santica', 'Novi Sad');
INSERT INTO public.address (address_id, country, number, postal_code, street, town) VALUES (48, 'Serbia', 234, 21000, 'Bulevar Oslobodjenja', 'Novi Sad');
INSERT INTO public.address (address_id, country, number, postal_code, street, town) VALUES (50, 'Serbia', 54678, 21000, 'Fruskogorska', '343536');
INSERT INTO public.address (address_id, country, number, postal_code, street, town) VALUES (51, 'Serbia', 3, 21000, 'Njegoseva', 'Novi Sad');
INSERT INTO public.address (address_id, country, number, postal_code, street, town) VALUES (52, 'Serbia', 45678, 21000, 'Kosovska', 'Novi Sad');
INSERT INTO public.address (address_id, country, number, postal_code, street, town) VALUES (56, 'Serbia', 3, 123, 'Niska', 'Nis');
INSERT INTO public.address (address_id, country, number, postal_code, street, town) VALUES (57, 'Serbia', 45678, 21000, 'Kosovska', 'Novi Sad');
INSERT INTO public.address (address_id, country, number, postal_code, street, town) VALUES (58, 'Serbia', 45678, 21000, 'Kosovska', 'Novi Sad');
INSERT INTO public.address (address_id, country, number, postal_code, street, town) VALUES (59, 'Serbia', 45678, 21000, 'Kosovska', 'Novi Sad');
INSERT INTO public.address (address_id, country, number, postal_code, street, town) VALUES (60, 'Serbia', 4, 21000, 'Mise Dimitrijevica', 'Novi Sad');
INSERT INTO public.address (address_id, country, number, postal_code, street, town) VALUES (30, 'Serbia', 422, 21000, 'Bulevar Oslobodjenjaa', 'Novi Sad');


--
-- Data for Name: authority; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.authority (id, name) VALUES (1, 'ROLE_SYSTEM_ADMIN');
INSERT INTO public.authority (id, name) VALUES (2, 'ROLE_DERMATOLOGIST');
INSERT INTO public.authority (id, name) VALUES (3, 'ROLE_PHARMACY_ADMIN');
INSERT INTO public.authority (id, name) VALUES (4, 'ROLE_SUPPLIER');
INSERT INTO public.authority (id, name) VALUES (5, 'ROLE_PATIENT');
INSERT INTO public.authority (id, name) VALUES (6, 'ROLE_PHARMACIST');


--
-- Data for Name: complaint; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.complaint (id, answer, answered, massage, subject, version, dermatologist_id, patient_id, pharmacist_id, pharmacy_id) VALUES (342, '', false, 'I am not satisfied with your service!', 'PHARMACY', 0, NULL, 33, NULL, 17);
INSERT INTO public.complaint (id, answer, answered, massage, subject, version, dermatologist_id, patient_id, pharmacist_id, pharmacy_id) VALUES (341, 'I am so sorry!', true, 'Complaint', 'PHARMACY', 1, NULL, 33, NULL, 17);
INSERT INTO public.complaint (id, answer, answered, massage, subject, version, dermatologist_id, patient_id, pharmacist_id, pharmacy_id) VALUES (343, '', false, 'I am not satisfies!', 'PHARMACY', 0, NULL, 35, NULL, 11);
INSERT INTO public.complaint (id, answer, answered, massage, subject, version, dermatologist_id, patient_id, pharmacist_id, pharmacy_id) VALUES (480, '', false, 'I am not satisfied with Mila!', 'PHARMACIST', 0, NULL, 33, 43, NULL);
INSERT INTO public.complaint (id, answer, answered, massage, subject, version, dermatologist_id, patient_id, pharmacist_id, pharmacy_id) VALUES (481, '', false, 'Pharmacy is bad!', 'PHARMACY', 0, NULL, 33, NULL, 13);
INSERT INTO public.complaint (id, answer, answered, massage, subject, version, dermatologist_id, patient_id, pharmacist_id, pharmacy_id) VALUES (482, '', false, 'Pharmacy is bad!', 'PHARMACY', 0, NULL, 33, NULL, 17);


--
-- Data for Name: confirmation_token; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.confirmation_token (token_id, confirmation_token, created_date, person_user_id) VALUES (22, '7db54b30-1032-49dd-a040-7b3857ff2530', '2021-02-12 15:50:58.026', 33);
INSERT INTO public.confirmation_token (token_id, confirmation_token, created_date, person_user_id) VALUES (23, '92499561-2878-4e67-bfcb-bbe9fd57ab9b', '2021-02-12 15:52:38.705', 35);


--
-- Data for Name: consulting; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.consulting (consulting_id, cancelled, date, duration, information, price, showed_up, start_time, patient_id, pharmacist_id) VALUES (121, false, '2021-12-02', 20, '', 150, false, '11:00:00', 33, 41);
INSERT INTO public.consulting (consulting_id, cancelled, date, duration, information, price, showed_up, start_time, patient_id, pharmacist_id) VALUES (474, false, '2021-02-22', 20, '', 100, false, '13:00:00', 33, 47);
INSERT INTO public.consulting (consulting_id, cancelled, date, duration, information, price, showed_up, start_time, patient_id, pharmacist_id) VALUES (475, false, '2021-02-22', 20, '', 100, false, '13:30:00', 33, 47);
INSERT INTO public.consulting (consulting_id, cancelled, date, duration, information, price, showed_up, start_time, patient_id, pharmacist_id) VALUES (361, false, '2021-02-16', 5, 'Good', 100, true, '10:00:00', 33, 43);
INSERT INTO public.consulting (consulting_id, cancelled, date, duration, information, price, showed_up, start_time, patient_id, pharmacist_id) VALUES (461, false, '2021-02-15', 5, 'Good', 100, true, '08:00:00', 33, 43);
INSERT INTO public.consulting (consulting_id, cancelled, date, duration, information, price, showed_up, start_time, patient_id, pharmacist_id) VALUES (478, false, '2021-02-17', 10, '', 100, false, '10:15:00', 35, 43);


--
-- Data for Name: dermatologists_pharmacies; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.dermatologists_pharmacies (dermatologist_id, pharmacy_id) VALUES (3, 17);
INSERT INTO public.dermatologists_pharmacies (dermatologist_id, pharmacy_id) VALUES (7, 17);
INSERT INTO public.dermatologists_pharmacies (dermatologist_id, pharmacy_id) VALUES (5, 11);
INSERT INTO public.dermatologists_pharmacies (dermatologist_id, pharmacy_id) VALUES (3, 11);
INSERT INTO public.dermatologists_pharmacies (dermatologist_id, pharmacy_id) VALUES (49, 13);
INSERT INTO public.dermatologists_pharmacies (dermatologist_id, pharmacy_id) VALUES (49, 17);
INSERT INTO public.dermatologists_pharmacies (dermatologist_id, pharmacy_id) VALUES (55, 11);
INSERT INTO public.dermatologists_pharmacies (dermatologist_id, pharmacy_id) VALUES (5, 15);


--
-- Data for Name: eprescriptions; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.eprescriptions (id, code, date, pharmacy_id, patient_id) VALUES (333, '455386', '2021-02-12', 17, 33);
INSERT INTO public.eprescriptions (id, code, date, pharmacy_id, patient_id) VALUES (336, '789386', '2021-02-12', 15, 33);


--
-- Data for Name: examination; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.examination (id, cancelled, information, showed_up, examination_id, patient_id) VALUES (470, false, NULL, false, 443, 33);
INSERT INTO public.examination (id, cancelled, information, showed_up, examination_id, patient_id) VALUES (471, false, NULL, false, 352, 33);
INSERT INTO public.examination (id, cancelled, information, showed_up, examination_id, patient_id) VALUES (472, false, NULL, false, 427, 33);
INSERT INTO public.examination (id, cancelled, information, showed_up, examination_id, patient_id) VALUES (473, false, NULL, false, 446, 33);


--
-- Data for Name: examination_schedule; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.examination_schedule (id, date, duration, finished, price, start_time, dermatologist_id, pharmacy_id) VALUES (426, '2021-02-17', 25, false, 1200, '13:00:00', 5, 11);
INSERT INTO public.examination_schedule (id, date, duration, finished, price, start_time, dermatologist_id, pharmacy_id) VALUES (428, '2021-02-17', 25, false, 1200, '13:00:00', 55, 11);
INSERT INTO public.examination_schedule (id, date, duration, finished, price, start_time, dermatologist_id, pharmacy_id) VALUES (429, '2021-02-17', 25, false, 1200, '14:00:00', 55, 11);
INSERT INTO public.examination_schedule (id, date, duration, finished, price, start_time, dermatologist_id, pharmacy_id) VALUES (430, '2021-02-17', 25, false, 1200, '14:00:00', 3, 11);
INSERT INTO public.examination_schedule (id, date, duration, finished, price, start_time, dermatologist_id, pharmacy_id) VALUES (431, '2021-02-17', 25, false, 1200, '14:00:00', 5, 11);
INSERT INTO public.examination_schedule (id, date, duration, finished, price, start_time, dermatologist_id, pharmacy_id) VALUES (432, '2021-05-08', 25, false, 1200, '14:00:00', 5, 11);
INSERT INTO public.examination_schedule (id, date, duration, finished, price, start_time, dermatologist_id, pharmacy_id) VALUES (433, '2021-05-28', 25, false, 1200, '14:00:00', 5, 11);
INSERT INTO public.examination_schedule (id, date, duration, finished, price, start_time, dermatologist_id, pharmacy_id) VALUES (434, '2021-03-25', 25, false, 1200, '14:00:00', 5, 11);
INSERT INTO public.examination_schedule (id, date, duration, finished, price, start_time, dermatologist_id, pharmacy_id) VALUES (445, '2021-04-10', 25, false, 999, '00:15:00', 49, 13);
INSERT INTO public.examination_schedule (id, date, duration, finished, price, start_time, dermatologist_id, pharmacy_id) VALUES (455, '2021-02-17', 25, false, 1200, '22:00:00', 7, 17);
INSERT INTO public.examination_schedule (id, date, duration, finished, price, start_time, dermatologist_id, pharmacy_id) VALUES (456, '2021-02-17', 25, false, 1200, '21:30:00', 7, 17);
INSERT INTO public.examination_schedule (id, date, duration, finished, price, start_time, dermatologist_id, pharmacy_id) VALUES (457, '2021-02-17', 25, false, 1200, '20:30:00', 7, 17);
INSERT INTO public.examination_schedule (id, date, duration, finished, price, start_time, dermatologist_id, pharmacy_id) VALUES (458, '2021-02-17', 25, false, 1200, '19:30:00', 7, 17);
INSERT INTO public.examination_schedule (id, date, duration, finished, price, start_time, dermatologist_id, pharmacy_id) VALUES (443, '2021-03-03', 25, true, 999, '00:15:00', 49, 13);
INSERT INTO public.examination_schedule (id, date, duration, finished, price, start_time, dermatologist_id, pharmacy_id) VALUES (352, '2021-02-22', 15, true, 150, '10:15:00', 55, 11);
INSERT INTO public.examination_schedule (id, date, duration, finished, price, start_time, dermatologist_id, pharmacy_id) VALUES (427, '2021-02-17', 25, true, 1200, '13:00:00', 3, 11);
INSERT INTO public.examination_schedule (id, date, duration, finished, price, start_time, dermatologist_id, pharmacy_id) VALUES (446, '2021-05-22', 25, true, 999, '00:15:00', 49, 13);
INSERT INTO public.examination_schedule (id, date, duration, finished, price, start_time, dermatologist_id, pharmacy_id) VALUES (444, '2021-03-25', 25, false, 999, '00:15:00', 49, 13);


--
-- Data for Name: friday_schedule; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.friday_schedule (id, end_time, start_time) VALUES (366, '16:00:00', '08:00:00');
INSERT INTO public.friday_schedule (id, end_time, start_time) VALUES (374, '16:00:00', '08:00:00');
INSERT INTO public.friday_schedule (id, end_time, start_time) VALUES (382, '14:00:00', '07:00:00');
INSERT INTO public.friday_schedule (id, end_time, start_time) VALUES (390, '19:00:00', '00:00:00');
INSERT INTO public.friday_schedule (id, end_time, start_time) VALUES (398, '19:00:00', '00:00:00');
INSERT INTO public.friday_schedule (id, end_time, start_time) VALUES (406, '19:00:00', '08:00:00');
INSERT INTO public.friday_schedule (id, end_time, start_time) VALUES (414, '19:00:00', '08:00:00');
INSERT INTO public.friday_schedule (id, end_time, start_time) VALUES (422, '19:00:00', '08:00:00');
INSERT INTO public.friday_schedule (id, end_time, start_time) VALUES (439, '19:00:00', '00:00:00');
INSERT INTO public.friday_schedule (id, end_time, start_time) VALUES (451, '23:00:00', '16:00:00');


--
-- Data for Name: grading; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: holiday_schedule_dermatologist; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.holiday_schedule_dermatologist (id, approved, end_date, massage, start_date, type, dermatologist_id) VALUES (490, 'WAIT_FOR_RESPONSE', '2021-04-09', NULL, '2021-04-01', '', 3);
INSERT INTO public.holiday_schedule_dermatologist (id, approved, end_date, massage, start_date, type, dermatologist_id) VALUES (489, 'APPROVED', '2021-03-13', NULL, '2021-03-05', '', 3);


--
-- Data for Name: holiday_schedule_pharmacist; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.holiday_schedule_pharmacist (id, approved, end_date, massage, start_date, type, version, pharmacist_id) VALUES (487, 'WAIT_FOR_RESPONSE', '2021-04-10', NULL, '2021-03-06', 'ABSENCE', 0, 43);
INSERT INTO public.holiday_schedule_pharmacist (id, approved, end_date, massage, start_date, type, version, pharmacist_id) VALUES (488, 'WAIT_FOR_RESPONSE', '2021-04-10', NULL, '2021-04-22', 'VACATION', 0, 43);
INSERT INTO public.holiday_schedule_pharmacist (id, approved, end_date, massage, start_date, type, version, pharmacist_id) VALUES (486, 'REFUSED', '2021-03-13', 'There is not enough time', '2021-02-25', 'VACATION', 1, 43);


--
-- Data for Name: lack_medicine; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.lack_medicine (id, name_medicine, name_pharmacy) VALUES (476, 'Cough', 'Benu');
INSERT INTO public.lack_medicine (id, name_medicine, name_pharmacy) VALUES (477, 'Cough', 'Benu');


--
-- Data for Name: loyalty_program; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.loyalty_program (id, consulting_points, examination_points, gold_limit, golden_discount, regular_discount, regular_limit, silver_discount, silver_limit, version) VALUES (1, 100, 10, 100, 15, 1, 0, 5, 50, 0);


--
-- Data for Name: mark; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.mark (id, mark_five, mark_four, mark_one, mark_three, mark_two, patients_mark, patient_id, pharmacy_id) VALUES (53, 0, 1, 0, 0, 0, 4, 35, 11);
INSERT INTO public.mark (id, mark_five, mark_four, mark_one, mark_three, mark_two, patients_mark, patient_id, pharmacy_id) VALUES (54, 0, 0, 0, 1, 0, 3, 35, 15);


--
-- Data for Name: mark_dermatologist; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: mark_medication; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: mark_pharmacist; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: medication; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.medication (id, code, form, issuance_regime, loyalty_points, mark, name, type, way_of_selling, specification_id) VALUES (2, 111, 'Capsule', 'when neccessery', 3, 0, 'Brufen', 'Antibiotik', NULL, 3);
INSERT INTO public.medication (id, code, form, issuance_regime, loyalty_points, mark, name, type, way_of_selling, specification_id) VALUES (4, 222, 'Tablet', 'Sometimes', 5, 0, 'Paracetamol', 'Antibiotik', NULL, 5);
INSERT INTO public.medication (id, code, form, issuance_regime, loyalty_points, mark, name, type, way_of_selling, specification_id) VALUES (6, 333, 'Tablet', 'Sometimes', 2, 0, 'Analgin', 'Antibiotik', NULL, 7);
INSERT INTO public.medication (id, code, form, issuance_regime, loyalty_points, mark, name, type, way_of_selling, specification_id) VALUES (8, 444, 'Pill', 'Sometimes', 6, 0, 'Pancef', 'Antihistamine', NULL, 9);
INSERT INTO public.medication (id, code, form, issuance_regime, loyalty_points, mark, name, type, way_of_selling, specification_id) VALUES (10, 555, 'Tablet', 'Sometimes', 7, 0, 'Defrinol', 'For addiction', NULL, 11);
INSERT INTO public.medication (id, code, form, issuance_regime, loyalty_points, mark, name, type, way_of_selling, specification_id) VALUES (12, 666, 'Syrup', 'Sometimes', 6, 0, 'Cough', 'Antibiotik', NULL, 13);
INSERT INTO public.medication (id, code, form, issuance_regime, loyalty_points, mark, name, type, way_of_selling, specification_id) VALUES (14, 777, 'Capsule', 'Sometimes', 4, 0, 'Panadol', 'Antihistamine', NULL, 15);
INSERT INTO public.medication (id, code, form, issuance_regime, loyalty_points, mark, name, type, way_of_selling, specification_id) VALUES (16, 888, 'Capsule', 'Sometimes', 10, 0, 'Razadyne', 'For dementia', NULL, 17);
INSERT INTO public.medication (id, code, form, issuance_regime, loyalty_points, mark, name, type, way_of_selling, specification_id) VALUES (18, 999, 'Tablet', 'Sometimes', 10, 0, 'Buspirone', 'Antihistamine', NULL, 19);
INSERT INTO public.medication (id, code, form, issuance_regime, loyalty_points, mark, name, type, way_of_selling, specification_id) VALUES (20, 1010, 'Powder', 'Sometimes', 2, 0, 'Kosili', 'Antibiotik', NULL, 21);


--
-- Data for Name: medication_in_order; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (123, 200, 18, 122);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (124, 200, 12, 122);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (125, 50, 2, 122);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (126, 200, 6, 122);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (127, 200, 16, 122);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (128, 200, 14, 122);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (129, 200, 20, 122);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (130, 200, 4, 122);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (131, 200, 10, 122);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (133, 300, 2, 132);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (134, 300, 10, 132);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (136, 300, 6, 135);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (137, 300, 8, 135);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (138, 300, 12, 135);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (139, 300, 4, 135);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (140, 300, 14, 135);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (141, 300, 2, 135);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (142, 300, 18, 135);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (143, 300, 10, 135);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (144, 300, 20, 135);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (145, 300, 16, 135);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (147, 500, 16, 146);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (148, 500, 2, 146);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (149, 500, 6, 146);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (150, 500, 14, 146);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (151, 500, 20, 146);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (153, 600, 12, 152);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (154, 600, 20, 152);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (155, 600, 8, 152);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (156, 600, 18, 152);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (157, 600, 2, 152);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (158, 600, 14, 152);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (159, 600, 16, 152);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (160, 600, 6, 152);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (161, 600, 4, 152);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (162, 600, 10, 152);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (164, 350, 4, 163);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (165, 350, 8, 163);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (166, 350, 2, 163);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (167, 350, 14, 163);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (169, 500, 18, 168);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (170, 500, 2, 168);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (171, 500, 14, 168);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (172, 500, 12, 168);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (173, 500, 6, 168);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (174, 500, 10, 168);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (175, 500, 16, 168);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (176, 500, 4, 168);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (177, 500, 8, 168);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (178, 500, 20, 168);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (180, 300, 18, 179);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (181, 300, 2, 179);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (182, 300, 8, 179);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (183, 300, 16, 179);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (744, 50, 6, 743);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (745, 50, 2, 743);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (749, 30, 16, 748);
INSERT INTO public.medication_in_order (id, quantity, medication_id, order_id) VALUES (750, 30, 10, 748);


--
-- Data for Name: medication_price; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.medication_price (id, date, price, quantity, medication_id, pharmacy_id) VALUES (294, '2021-03-22', 332, 500, 8, 17);
INSERT INTO public.medication_price (id, date, price, quantity, medication_id, pharmacy_id) VALUES (255, '2021-04-07', 654, 600, 10, 15);
INSERT INTO public.medication_price (id, date, price, quantity, medication_id, pharmacy_id) VALUES (221, '2021-02-21', 555, 500, 2, 13);
INSERT INTO public.medication_price (id, date, price, quantity, medication_id, pharmacy_id) VALUES (220, '2021-03-10', 444, 500, 20, 13);
INSERT INTO public.medication_price (id, date, price, quantity, medication_id, pharmacy_id) VALUES (237, '2021-03-13', 534, 200, 14, 11);
INSERT INTO public.medication_price (id, date, price, quantity, medication_id, pharmacy_id) VALUES (258, '2021-03-13', 4567, 600, 12, 15);
INSERT INTO public.medication_price (id, date, price, quantity, medication_id, pharmacy_id) VALUES (293, '2021-03-11', 456, 500, 12, 17);
INSERT INTO public.medication_price (id, date, price, quantity, medication_id, pharmacy_id) VALUES (295, '2021-03-04', 345, 500, 20, 17);
INSERT INTO public.medication_price (id, date, price, quantity, medication_id, pharmacy_id) VALUES (289, '2021-03-12', 555, 500, 14, 17);
INSERT INTO public.medication_price (id, date, price, quantity, medication_id, pharmacy_id) VALUES (230, '2021-03-13', 333, 200, 20, 11);
INSERT INTO public.medication_price (id, date, price, quantity, medication_id, pharmacy_id) VALUES (288, '2021-03-11', 555, 500, 10, 17);
INSERT INTO public.medication_price (id, date, price, quantity, medication_id, pharmacy_id) VALUES (261, '2021-03-13', 222, 600, 20, 15);
INSERT INTO public.medication_price (id, date, price, quantity, medication_id, pharmacy_id) VALUES (233, '2021-03-13', 333, 200, 18, 11);
INSERT INTO public.medication_price (id, date, price, quantity, medication_id, pharmacy_id) VALUES (219, '2021-03-13', 333, 500, 16, 13);
INSERT INTO public.medication_price (id, date, price, quantity, medication_id, pharmacy_id) VALUES (256, '2021-03-12', 233, 600, 18, 15);
INSERT INTO public.medication_price (id, date, price, quantity, medication_id, pharmacy_id) VALUES (222, '2021-03-13', 222, 500, 6, 13);
INSERT INTO public.medication_price (id, date, price, quantity, medication_id, pharmacy_id) VALUES (287, '2021-03-13', 111, 500, 18, 17);
INSERT INTO public.medication_price (id, date, price, quantity, medication_id, pharmacy_id) VALUES (218, '2021-03-06', 123, 500, 14, 13);
INSERT INTO public.medication_price (id, date, price, quantity, medication_id, pharmacy_id) VALUES (232, '2021-04-01', 423, 200, 12, 11);
INSERT INTO public.medication_price (id, date, price, quantity, medication_id, pharmacy_id) VALUES (231, '2021-03-30', 143, 200, 4, 11);
INSERT INTO public.medication_price (id, date, price, quantity, medication_id, pharmacy_id) VALUES (253, '2021-03-09', 222, 600, 16, 15);
INSERT INTO public.medication_price (id, date, price, quantity, medication_id, pharmacy_id) VALUES (235, '2021-03-12', 345, 200, 10, 11);
INSERT INTO public.medication_price (id, date, price, quantity, medication_id, pharmacy_id) VALUES (236, '2021-03-13', 343, 199, 16, 11);
INSERT INTO public.medication_price (id, date, price, quantity, medication_id, pharmacy_id) VALUES (260, '2021-03-10', 144, 599, 4, 15);
INSERT INTO public.medication_price (id, date, price, quantity, medication_id, pharmacy_id) VALUES (290, '2021-03-12', 333, 499, 16, 17);
INSERT INTO public.medication_price (id, date, price, quantity, medication_id, pharmacy_id) VALUES (292, '2021-03-13', 555, 499, 4, 17);
INSERT INTO public.medication_price (id, date, price, quantity, medication_id, pharmacy_id) VALUES (291, '2021-03-12', 546, 495, 6, 17);
INSERT INTO public.medication_price (id, date, price, quantity, medication_id, pharmacy_id) VALUES (296, '2021-03-11', 444, 490, 2, 17);
INSERT INTO public.medication_price (id, date, price, quantity, medication_id, pharmacy_id) VALUES (257, '2021-03-13', 444, 595, 14, 15);
INSERT INTO public.medication_price (id, date, price, quantity, medication_id, pharmacy_id) VALUES (262, '2021-04-01', 444, 590, 2, 15);
INSERT INTO public.medication_price (id, date, price, quantity, medication_id, pharmacy_id) VALUES (254, '2021-03-13', 300, 594, 6, 15);
INSERT INTO public.medication_price (id, date, price, quantity, medication_id, pharmacy_id) VALUES (259, '2021-03-13', 111, 594, 8, 15);
INSERT INTO public.medication_price (id, date, price, quantity, medication_id, pharmacy_id) VALUES (234, '2021-04-09', 324, 250, 6, 11);
INSERT INTO public.medication_price (id, date, price, quantity, medication_id, pharmacy_id) VALUES (238, '2021-03-13', 222, 100, 2, 11);


--
-- Data for Name: medication_price_history; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.medication_price_history (id, end_date, medication, pharmacy, price, start_date) VALUES (228, '2021-04-29', 6, 13, 500, '2021-02-12');
INSERT INTO public.medication_price_history (id, end_date, medication, pharmacy, price, start_date) VALUES (239, '2021-04-10', 20, 11, 300, '2021-02-12');
INSERT INTO public.medication_price_history (id, end_date, medication, pharmacy, price, start_date) VALUES (240, '2021-04-02', 20, 11, 500, '2021-02-12');
INSERT INTO public.medication_price_history (id, end_date, medication, pharmacy, price, start_date) VALUES (244, '2021-04-09', 16, 11, 500, '2021-02-12');
INSERT INTO public.medication_price_history (id, end_date, medication, pharmacy, price, start_date) VALUES (247, '2021-04-26', 4, 11, 400, '2021-02-12');
INSERT INTO public.medication_price_history (id, end_date, medication, pharmacy, price, start_date) VALUES (248, '2021-03-31', 18, 11, 100, '2021-02-12');
INSERT INTO public.medication_price_history (id, end_date, medication, pharmacy, price, start_date) VALUES (251, '2021-04-10', 6, 11, 400, '2021-02-12');
INSERT INTO public.medication_price_history (id, end_date, medication, pharmacy, price, start_date) VALUES (264, '2021-04-08', 4, 15, 300, '2021-02-12');
INSERT INTO public.medication_price_history (id, end_date, medication, pharmacy, price, start_date) VALUES (270, '2021-04-01', 16, 15, 300, '2021-02-12');
INSERT INTO public.medication_price_history (id, end_date, medication, pharmacy, price, start_date) VALUES (271, '2021-04-22', 10, 15, 400, '2021-02-12');
INSERT INTO public.medication_price_history (id, end_date, medication, pharmacy, price, start_date) VALUES (272, '2021-04-10', 2, 15, 300, '2021-02-12');
INSERT INTO public.medication_price_history (id, end_date, medication, pharmacy, price, start_date) VALUES (286, '2021-04-01', 2, 15, 444, '2021-02-12');
INSERT INTO public.medication_price_history (id, end_date, medication, pharmacy, price, start_date) VALUES (301, '2021-03-13', 18, 17, 111, '2021-02-12');
INSERT INTO public.medication_price_history (id, end_date, medication, pharmacy, price, start_date) VALUES (302, '2021-03-13', 16, 17, 343, '2021-02-12');
INSERT INTO public.medication_price_history (id, end_date, medication, pharmacy, price, start_date) VALUES (304, '2021-04-01', 12, 17, 423, '2021-02-12');
INSERT INTO public.medication_price_history (id, end_date, medication, pharmacy, price, start_date) VALUES (311, '2021-03-12', 16, 17, 333, '2021-02-12');
INSERT INTO public.medication_price_history (id, end_date, medication, pharmacy, price, start_date) VALUES (316, '2021-03-13', 14, 17, 534, '2021-02-12');
INSERT INTO public.medication_price_history (id, end_date, medication, pharmacy, price, start_date) VALUES (319, '2021-03-13', 12, 17, 4567, '2021-02-12');
INSERT INTO public.medication_price_history (id, end_date, medication, pharmacy, price, start_date) VALUES (320, '2021-03-13', 4, 17, 555, '2021-02-12');
INSERT INTO public.medication_price_history (id, end_date, medication, pharmacy, price, start_date) VALUES (321, '2021-03-11', 12, 17, 456, '2021-02-12');
INSERT INTO public.medication_price_history (id, end_date, medication, pharmacy, price, start_date) VALUES (322, '2021-03-04', 20, 17, 345, '2021-02-12');
INSERT INTO public.medication_price_history (id, end_date, medication, pharmacy, price, start_date) VALUES (323, '2021-03-12', 6, 17, 546, '2021-02-12');
INSERT INTO public.medication_price_history (id, end_date, medication, pharmacy, price, start_date) VALUES (324, '2021-03-12', 14, 17, 555, '2021-02-12');
INSERT INTO public.medication_price_history (id, end_date, medication, pharmacy, price, start_date) VALUES (325, '2021-03-11', 2, 17, 444, '2021-02-12');
INSERT INTO public.medication_price_history (id, end_date, medication, pharmacy, price, start_date) VALUES (326, '2021-03-11', 10, 17, 555, '2021-02-12');


--
-- Data for Name: medication_reservation; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.medication_reservation (id, cancelled, collected, date_of_reservation, date_of_take_over, reservation_code, medicine, patient_id, pharmacy_id) VALUES (330, NULL, NULL, '2021-02-12', '2021-03-13', 'cdd6f7a5-4d0f-4414-8209-486b85ad675a', 8, 33, 15);
INSERT INTO public.medication_reservation (id, cancelled, collected, date_of_reservation, date_of_take_over, reservation_code, medicine, patient_id, pharmacy_id) VALUES (331, NULL, NULL, '2021-02-12', '2021-03-11', '4634059d-676f-49a3-87ed-3692fa23c97a', 16, 33, 17);
INSERT INTO public.medication_reservation (id, cancelled, collected, date_of_reservation, date_of_take_over, reservation_code, medicine, patient_id, pharmacy_id) VALUES (332, NULL, NULL, '2021-02-12', '2021-03-13', '1d5c7996-41fc-439f-9f0e-a18b349a93cc', 4, 33, 17);


--
-- Data for Name: medications_alternatives; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.medications_alternatives (medication_id, medication_alternative_id) VALUES (6, 2);
INSERT INTO public.medications_alternatives (medication_id, medication_alternative_id) VALUES (8, 4);
INSERT INTO public.medications_alternatives (medication_id, medication_alternative_id) VALUES (10, 2);
INSERT INTO public.medications_alternatives (medication_id, medication_alternative_id) VALUES (14, 10);
INSERT INTO public.medications_alternatives (medication_id, medication_alternative_id) VALUES (16, 10);


--
-- Data for Name: medications_eprescription; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.medications_eprescription (id, code, name, quantity, e_prescription_medication) VALUES (334, 111, 'Brufen', 10, 333);
INSERT INTO public.medications_eprescription (id, code, name, quantity, e_prescription_medication) VALUES (335, 333, 'Analgin', 5, 333);
INSERT INTO public.medications_eprescription (id, code, name, quantity, e_prescription_medication) VALUES (337, 111, 'Brufen', 10, 336);
INSERT INTO public.medications_eprescription (id, code, name, quantity, e_prescription_medication) VALUES (338, 333, 'Analgin', 5, 336);
INSERT INTO public.medications_eprescription (id, code, name, quantity, e_prescription_medication) VALUES (339, 444, 'Pancef', 5, 336);
INSERT INTO public.medications_eprescription (id, code, name, quantity, e_prescription_medication) VALUES (340, 777, 'Panadol', 5, 336);


--
-- Data for Name: monday_schedule; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.monday_schedule (id, end_time, start_time) VALUES (362, '16:00:00', '08:00:00');
INSERT INTO public.monday_schedule (id, end_time, start_time) VALUES (370, '16:00:00', '08:00:00');
INSERT INTO public.monday_schedule (id, end_time, start_time) VALUES (378, '14:00:00', '07:00:00');
INSERT INTO public.monday_schedule (id, end_time, start_time) VALUES (386, '19:00:00', '00:00:00');
INSERT INTO public.monday_schedule (id, end_time, start_time) VALUES (394, '19:00:00', '00:00:00');
INSERT INTO public.monday_schedule (id, end_time, start_time) VALUES (402, '19:00:00', '08:00:00');
INSERT INTO public.monday_schedule (id, end_time, start_time) VALUES (410, '19:00:00', '08:00:00');
INSERT INTO public.monday_schedule (id, end_time, start_time) VALUES (418, '19:00:00', '08:00:00');
INSERT INTO public.monday_schedule (id, end_time, start_time) VALUES (435, '19:00:00', '00:00:00');
INSERT INTO public.monday_schedule (id, end_time, start_time) VALUES (447, '23:00:00', '16:00:00');


--
-- Data for Name: offer; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.offer (id, date_of_delivery, status, summary_price, order_id, supplier) VALUES (200, '2021-03-02', 'CREATED', 8253, 179, 29);
INSERT INTO public.offer (id, date_of_delivery, status, summary_price, order_id, supplier) VALUES (217, '2021-03-11', 'CREATED', 567890, 179, 31);
INSERT INTO public.offer (id, date_of_delivery, status, summary_price, order_id, supplier) VALUES (211, '2021-03-13', 'REFUSED', 333333, 122, 31);
INSERT INTO public.offer (id, date_of_delivery, status, summary_price, order_id, supplier) VALUES (196, '2021-04-14', 'REFUSED', 73324354, 135, 29);
INSERT INTO public.offer (id, date_of_delivery, status, summary_price, order_id, supplier) VALUES (212, '2021-03-31', 'REFUSED', 34256, 135, 31);
INSERT INTO public.offer (id, date_of_delivery, status, summary_price, order_id, supplier) VALUES (199, '2021-02-26', 'REFUSED', 6872, 146, 29);
INSERT INTO public.offer (id, date_of_delivery, status, summary_price, order_id, supplier) VALUES (213, '2021-03-06', 'ACCEPTED', 53453, 146, 31);
INSERT INTO public.offer (id, date_of_delivery, status, summary_price, order_id, supplier) VALUES (194, '2021-04-22', 'ACCEPTED', 7895, 122, 29);
INSERT INTO public.offer (id, date_of_delivery, status, summary_price, order_id, supplier) VALUES (197, '2021-03-13', 'ACCEPTED', 6872337, 152, 29);
INSERT INTO public.offer (id, date_of_delivery, status, summary_price, order_id, supplier) VALUES (214, '2021-04-10', 'REFUSED', 53533, 152, 31);
INSERT INTO public.offer (id, date_of_delivery, status, summary_price, order_id, supplier) VALUES (198, '2021-04-02', 'ACCEPTED', 99999, 168, 29);
INSERT INTO public.offer (id, date_of_delivery, status, summary_price, order_id, supplier) VALUES (216, '2021-03-13', 'REFUSED', 546789, 168, 31);
INSERT INTO public.offer (id, date_of_delivery, status, summary_price, order_id, supplier) VALUES (479, '2021-04-29', 'CREATED', 4445, 163, 29);
INSERT INTO public.offer (id, date_of_delivery, status, summary_price, order_id, supplier) VALUES (195, '2021-03-12', 'CREATED', 3000, 132, 29);
INSERT INTO public.offer (id, date_of_delivery, status, summary_price, order_id, supplier) VALUES (742, '2021-03-13', 'CREATED', 654, 132, 31);
INSERT INTO public.offer (id, date_of_delivery, status, summary_price, order_id, supplier) VALUES (215, '2021-03-13', 'CREATED', 543531, 163, 31);
INSERT INTO public.offer (id, date_of_delivery, status, summary_price, order_id, supplier) VALUES (746, '2021-03-12', 'ACCEPTED', 435, 743, 31);
INSERT INTO public.offer (id, date_of_delivery, status, summary_price, order_id, supplier) VALUES (747, '2021-03-13', 'REFUSED', 1234, 743, 29);
INSERT INTO public.offer (id, date_of_delivery, status, summary_price, order_id, supplier) VALUES (751, '2021-03-12', 'CREATED', 100, 748, 31);


--
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.orders (id, date, status, pharmacy_admin_id) VALUES (132, '2021-03-13', 'WAITING_OFFERS', 19);
INSERT INTO public.orders (id, date, status, pharmacy_admin_id) VALUES (135, '2021-04-09', 'WAITING_OFFERS', 23);
INSERT INTO public.orders (id, date, status, pharmacy_admin_id) VALUES (163, '2021-03-06', 'WAITING_OFFERS', 25);
INSERT INTO public.orders (id, date, status, pharmacy_admin_id) VALUES (179, '2021-03-28', 'WAITING_OFFERS', 27);
INSERT INTO public.orders (id, date, status, pharmacy_admin_id) VALUES (146, '2021-05-07', 'CLOSED', 23);
INSERT INTO public.orders (id, date, status, pharmacy_admin_id) VALUES (122, '2021-03-13', 'CLOSED', 19);
INSERT INTO public.orders (id, date, status, pharmacy_admin_id) VALUES (152, '2021-03-13', 'CLOSED', 25);
INSERT INTO public.orders (id, date, status, pharmacy_admin_id) VALUES (168, '2021-03-30', 'CLOSED', 27);
INSERT INTO public.orders (id, date, status, pharmacy_admin_id) VALUES (743, '2021-03-12', 'CLOSED', 19);
INSERT INTO public.orders (id, date, status, pharmacy_admin_id) VALUES (748, '2021-04-09', 'WAITING_OFFERS', 27);


--
-- Data for Name: patients_medication_alergy; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.patients_medication_alergy (id, description, medication_id, patient_id) VALUES (483, NULL, 4, 33);
INSERT INTO public.patients_medication_alergy (id, description, medication_id, patient_id) VALUES (484, NULL, 14, 33);
INSERT INTO public.patients_medication_alergy (id, description, medication_id, patient_id) VALUES (485, NULL, 10, 33);


--
-- Data for Name: patients_subscriptions; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.patients_subscriptions (patient_id, pharmacy_id) VALUES (33, 11);
INSERT INTO public.patients_subscriptions (patient_id, pharmacy_id) VALUES (33, 13);


--
-- Data for Name: person_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.person_user (type, id, email, enabled, first_logged, last_password_reset_date, firstname, password, phone_number, surname, mark_dermatologist, discount, loyalty_category, penalties, points, mark_pharmacist, main_admin, address_address_id, pharmacy_pharmacist, pharmacy_pharmacy_admin) VALUES ('SystemAdmin', 1, 'tamara@gmail.com', true, false, NULL, 'Tamara', '$2a$10$Y5dI4uaGAw/5Q1xCVRIweuG/WTZ4ZqB.XuAc/JYkcfw9yFNAEwC2W', '16161', 'Govorcin', NULL, NULL, NULL, NULL, NULL, NULL, true, 2, NULL, NULL);
INSERT INTO public.person_user (type, id, email, enabled, first_logged, last_password_reset_date, firstname, password, phone_number, surname, mark_dermatologist, discount, loyalty_category, penalties, points, mark_pharmacist, main_admin, address_address_id, pharmacy_pharmacist, pharmacy_pharmacy_admin) VALUES ('Dermatologist', 5, 'milos@gmail.com', true, true, NULL, 'Milos', '$2a$10$39l804JpHRJ7RmB3WOnD5eUAx4m2IBXZWJKXy4L.GkMQQZB88vs2a', '31243', 'Savic', 0, NULL, NULL, NULL, NULL, NULL, NULL, 6, NULL, NULL);
INSERT INTO public.person_user (type, id, email, enabled, first_logged, last_password_reset_date, firstname, password, phone_number, surname, mark_dermatologist, discount, loyalty_category, penalties, points, mark_pharmacist, main_admin, address_address_id, pharmacy_pharmacist, pharmacy_pharmacy_admin) VALUES ('Dermatologist', 7, 'luna@gmail.com', true, true, NULL, 'Luna', '$2a$10$WvwCrE3o4S2MbRvzriJqN.HzEaDkW6dEw6Ff.mjjj3tWoZuDn1YVG', '435', 'Zivkovic', 0, NULL, NULL, NULL, NULL, NULL, NULL, 8, NULL, NULL);
INSERT INTO public.person_user (type, id, email, enabled, first_logged, last_password_reset_date, firstname, password, phone_number, surname, mark_dermatologist, discount, loyalty_category, penalties, points, mark_pharmacist, main_admin, address_address_id, pharmacy_pharmacist, pharmacy_pharmacy_admin) VALUES ('Patient', 33, 'anaanic774@gmail.com', true, false, NULL, 'Ana', '$2a$10$rkC3SOD3D0jn8AUazRnIceZwFltPksyq70hyjUR5S9ht.JV0Y3W2a', '45467', 'Anic', NULL, 1, 'REGULAR', 0, 0, NULL, NULL, 59, NULL, NULL);
INSERT INTO public.person_user (type, id, email, enabled, first_logged, last_password_reset_date, firstname, password, phone_number, surname, mark_dermatologist, discount, loyalty_category, penalties, points, mark_pharmacist, main_admin, address_address_id, pharmacy_pharmacist, pharmacy_pharmacy_admin) VALUES ('PharmacyAdmin', 19, 'maja@gmail.com', true, false, NULL, 'Maja', '$2a$10$T4cQQuJfczkfIQ19vNkyIO27oJgNEZ/dthCGJ6xO6/39wSwJFNQ4G', '432', 'Kokanovic', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 60, NULL, 11);
INSERT INTO public.person_user (type, id, email, enabled, first_logged, last_password_reset_date, firstname, password, phone_number, surname, mark_dermatologist, discount, loyalty_category, penalties, points, mark_pharmacist, main_admin, address_address_id, pharmacy_pharmacist, pharmacy_pharmacy_admin) VALUES ('Dermatologist', 3, 'dragana@gmail.com', true, false, NULL, 'Dragana', '$2a$10$ybcnXRu2QgQbVeCbNKE.A..ff8gICM7mcuhEKlt.pcfLj/G5FBctC', '345675', 'Dragic', 0, NULL, NULL, NULL, NULL, NULL, NULL, 4, NULL, NULL);
INSERT INTO public.person_user (type, id, email, enabled, first_logged, last_password_reset_date, firstname, password, phone_number, surname, mark_dermatologist, discount, loyalty_category, penalties, points, mark_pharmacist, main_admin, address_address_id, pharmacy_pharmacist, pharmacy_pharmacy_admin) VALUES ('Pharmacist', 39, 'marko@gmail.com', true, true, NULL, 'Marko', '$2a$10$pJOoAM67GqsHEkxzipnOiuTYoT7.uR9Wzci0yymcAVSiGsxEC./rm', '2456', 'Petrovic', NULL, NULL, NULL, NULL, NULL, 0, NULL, 40, 11, NULL);
INSERT INTO public.person_user (type, id, email, enabled, first_logged, last_password_reset_date, firstname, password, phone_number, surname, mark_dermatologist, discount, loyalty_category, penalties, points, mark_pharmacist, main_admin, address_address_id, pharmacy_pharmacist, pharmacy_pharmacy_admin) VALUES ('Pharmacist', 41, 'janko@gmail.com', true, true, NULL, 'Janko', '$2a$10$ane.7tVjbCeYZPAlDFVXQuICaEJZ/fE5Ez4x5.Y/MeKlVQGDAGWQa', '4567', 'Jankovic', NULL, NULL, NULL, NULL, NULL, 0, NULL, 42, 11, NULL);
INSERT INTO public.person_user (type, id, email, enabled, first_logged, last_password_reset_date, firstname, password, phone_number, surname, mark_dermatologist, discount, loyalty_category, penalties, points, mark_pharmacist, main_admin, address_address_id, pharmacy_pharmacist, pharmacy_pharmacy_admin) VALUES ('PharmacyAdmin', 23, 'nikola@gmail.com', true, false, NULL, 'Nikola', '$2a$10$7qvHVhCKcfv4XfX/t24Owe3vZCBmTjK1fZTKkKkuj6dRvkBTrrVIK', '432656', 'Simic', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 24, NULL, 13);
INSERT INTO public.person_user (type, id, email, enabled, first_logged, last_password_reset_date, firstname, password, phone_number, surname, mark_dermatologist, discount, loyalty_category, penalties, points, mark_pharmacist, main_admin, address_address_id, pharmacy_pharmacist, pharmacy_pharmacy_admin) VALUES ('PharmacyAdmin', 25, 'pavle@gmail.com', true, false, NULL, 'Pavle', '$2a$10$SjkfmpD.8wdkHCzr39jGEeCijnvQm8zWtxskhDxRPgAb1/nQgs/4y', '5678765', 'Jokic', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 26, NULL, 15);
INSERT INTO public.person_user (type, id, email, enabled, first_logged, last_password_reset_date, firstname, password, phone_number, surname, mark_dermatologist, discount, loyalty_category, penalties, points, mark_pharmacist, main_admin, address_address_id, pharmacy_pharmacist, pharmacy_pharmacy_admin) VALUES ('Pharmacist', 45, 'milan@gmail.com', true, true, NULL, 'Milan', '$2a$10$QkEnyEL87HVyak0Faye4x.dJKfW/EjKK8TIpc0a.kXHiKnaxKyzDW', '45678', 'Jokic', NULL, NULL, NULL, NULL, NULL, 0, NULL, 46, 15, NULL);
INSERT INTO public.person_user (type, id, email, enabled, first_logged, last_password_reset_date, firstname, password, phone_number, surname, mark_dermatologist, discount, loyalty_category, penalties, points, mark_pharmacist, main_admin, address_address_id, pharmacy_pharmacist, pharmacy_pharmacy_admin) VALUES ('PharmacyAdmin', 27, 'jovana@gmail.com', true, false, NULL, 'Jovana', '$2a$10$2.r6ZrcHK0FHas../VH.vurjZ0RrKUNOn/QhoSZqXqtWDTJ8HSD.K', '43234', 'Ivic', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 28, NULL, 17);
INSERT INTO public.person_user (type, id, email, enabled, first_logged, last_password_reset_date, firstname, password, phone_number, surname, mark_dermatologist, discount, loyalty_category, penalties, points, mark_pharmacist, main_admin, address_address_id, pharmacy_pharmacist, pharmacy_pharmacy_admin) VALUES ('Pharmacist', 47, 'nina@gmail.com', true, true, NULL, 'Nina', '$2a$10$80EDJfE15Ir5hIfSXG3Nk.LXG7ebzONUGEl64vIlwmEQ4OggKVSJC', '43567', 'Miljkovic', NULL, NULL, NULL, NULL, NULL, 0, NULL, 48, 17, NULL);
INSERT INTO public.person_user (type, id, email, enabled, first_logged, last_password_reset_date, firstname, password, phone_number, surname, mark_dermatologist, discount, loyalty_category, penalties, points, mark_pharmacist, main_admin, address_address_id, pharmacy_pharmacist, pharmacy_pharmacy_admin) VALUES ('Dermatologist', 49, 'ivana@gmail.com', true, true, NULL, 'Ivana', '$2a$10$27px/u0Elq/I7TPUGd.mg.bfzhqG9bvCSWNqgW3IMQQWnm84Nx/4C', '435678', 'Simic', 0, NULL, NULL, NULL, NULL, NULL, NULL, 50, NULL, NULL);
INSERT INTO public.person_user (type, id, email, enabled, first_logged, last_password_reset_date, firstname, password, phone_number, surname, mark_dermatologist, discount, loyalty_category, penalties, points, mark_pharmacist, main_admin, address_address_id, pharmacy_pharmacist, pharmacy_pharmacy_admin) VALUES ('Patient', 35, 'savicpetar774@gmail.com', true, false, NULL, 'Petar', '$2a$10$fIWJx2dd.ilA1cMx7mXF7ujjOvYE2M.fU0j0Huowy8.cX0UTtKFfm', '34567', 'Savic', NULL, 1, 'REGULAR', 0, 0, NULL, NULL, 51, NULL, NULL);
INSERT INTO public.person_user (type, id, email, enabled, first_logged, last_password_reset_date, firstname, password, phone_number, surname, mark_dermatologist, discount, loyalty_category, penalties, points, mark_pharmacist, main_admin, address_address_id, pharmacy_pharmacist, pharmacy_pharmacy_admin) VALUES ('Supplier', 29, 'ivic506@gmail.com', true, false, NULL, 'Ivan', '$2a$10$.CUEr4/.o7v0Xkx8lQoYE.IMg08MFIUb4sKusfs62A6poEXcd0H2W', '43242', 'Ivic', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 30, NULL, NULL);
INSERT INTO public.person_user (type, id, email, enabled, first_logged, last_password_reset_date, firstname, password, phone_number, surname, mark_dermatologist, discount, loyalty_category, penalties, points, mark_pharmacist, main_admin, address_address_id, pharmacy_pharmacist, pharmacy_pharmacy_admin) VALUES ('Supplier', 31, 'ourhospital9@gmail.com', true, false, NULL, 'Nikola', '$2a$10$izLKeS0laoTZZGgEitDNQOWd58DAygbm3.hXiQHnrZ0srsL80msmq', '56786', 'Nikolic', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 32, NULL, NULL);
INSERT INTO public.person_user (type, id, email, enabled, first_logged, last_password_reset_date, firstname, password, phone_number, surname, mark_dermatologist, discount, loyalty_category, penalties, points, mark_pharmacist, main_admin, address_address_id, pharmacy_pharmacist, pharmacy_pharmacy_admin) VALUES ('PharmacyAdmin', 21, 'lidija@gmail.com', true, false, NULL, 'Lidija', '$2a$10$hGik4Ib0SJLMonr08FeUp.ak3veLiujlaXO69RPDJid4FAsYm.25u', '42334', 'Vukovic', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 22, NULL, 11);
INSERT INTO public.person_user (type, id, email, enabled, first_logged, last_password_reset_date, firstname, password, phone_number, surname, mark_dermatologist, discount, loyalty_category, penalties, points, mark_pharmacist, main_admin, address_address_id, pharmacy_pharmacist, pharmacy_pharmacy_admin) VALUES ('SystemAdmin', 9, 'tanja@gmail.com', true, false, NULL, 'Tanja', '$2a$10$5TLrBbd9gQw/2uH6OIJ/BOlt0tHyROhB7U1.rpn.k5rmCkmzOjZE.', '435', 'Pavic', NULL, NULL, NULL, NULL, NULL, NULL, false, 10, NULL, NULL);
INSERT INTO public.person_user (type, id, email, enabled, first_logged, last_password_reset_date, firstname, password, phone_number, surname, mark_dermatologist, discount, loyalty_category, penalties, points, mark_pharmacist, main_admin, address_address_id, pharmacy_pharmacist, pharmacy_pharmacy_admin) VALUES ('Dermatologist', 55, 'marija@gmail.com', true, false, NULL, 'Marija', '$2a$10$ofYjEOBUQxGi202arBOpgOFGTqgYFlhwv5hu47R61URWagDB32DA.', '34567', 'Jovic', 0, NULL, NULL, NULL, NULL, NULL, NULL, 56, NULL, NULL);
INSERT INTO public.person_user (type, id, email, enabled, first_logged, last_password_reset_date, firstname, password, phone_number, surname, mark_dermatologist, discount, loyalty_category, penalties, points, mark_pharmacist, main_admin, address_address_id, pharmacy_pharmacist, pharmacy_pharmacy_admin) VALUES ('Pharmacist', 43, 'mila@gmail.com', true, false, NULL, 'Mila', '$2a$10$JEd1EG0r1CWzb025aAYabe2uk9fTX7XXgoU/lsQmbG8teVZuKgjHi', '123', 'Jovovic', NULL, NULL, NULL, NULL, NULL, 0, NULL, 44, 13, NULL);


--
-- Data for Name: pharmacy_table; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.pharmacy_table (id, consulting_price, description, mark, pharmacy_name, address_id) VALUES (13, 100, 'Founded in 2000.', 0, 'Benu', 14);
INSERT INTO public.pharmacy_table (id, consulting_price, description, mark, pharmacy_name, address_id) VALUES (17, 100, 'Founded in 2010.', 0, 'NovaMed', 18);
INSERT INTO public.pharmacy_table (id, consulting_price, description, mark, pharmacy_name, address_id) VALUES (11, 150, 'Founded in 1969.', 4, 'Jankovic', 12);
INSERT INTO public.pharmacy_table (id, consulting_price, description, mark, pharmacy_name, address_id) VALUES (15, 56, 'Founded in 2005.', 3, 'Expera', 16);


--
-- Data for Name: prescription; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: prescription_medications; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: saturday_schedule; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.saturday_schedule (id, end_time, start_time) VALUES (368, '00:00:00', '08:00:00');
INSERT INTO public.saturday_schedule (id, end_time, start_time) VALUES (376, '00:00:00', '08:00:00');
INSERT INTO public.saturday_schedule (id, end_time, start_time) VALUES (384, '00:00:00', '07:00:00');
INSERT INTO public.saturday_schedule (id, end_time, start_time) VALUES (392, '00:00:00', '00:00:00');
INSERT INTO public.saturday_schedule (id, end_time, start_time) VALUES (400, '00:00:00', '00:00:00');
INSERT INTO public.saturday_schedule (id, end_time, start_time) VALUES (408, '19:00:00', '08:00:00');
INSERT INTO public.saturday_schedule (id, end_time, start_time) VALUES (416, '19:00:00', '08:00:00');
INSERT INTO public.saturday_schedule (id, end_time, start_time) VALUES (424, '19:00:00', '08:00:00');
INSERT INTO public.saturday_schedule (id, end_time, start_time) VALUES (441, '19:00:00', '00:00:00');
INSERT INTO public.saturday_schedule (id, end_time, start_time) VALUES (453, '23:00:00', '16:00:00');


--
-- Data for Name: selling_statistics; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: specification; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.specification (id, contra_indications, manufacturer, recommended_consumption, structure) VALUES (3, 'Scratch', 'Hemofarm', 'when neccessery', '2ml of brufen');
INSERT INTO public.specification (id, contra_indications, manufacturer, recommended_consumption, structure) VALUES (5, 'Headache', 'Hemofarm', '3 times a day', '2ml of paracetamol');
INSERT INTO public.specification (id, contra_indications, manufacturer, recommended_consumption, structure) VALUES (7, 'Headache', 'Hemofarm', 'once a day', '2ml ibuprofen');
INSERT INTO public.specification (id, contra_indications, manufacturer, recommended_consumption, structure) VALUES (9, 'Headache', 'Hemofarm', '2 times a day', '2ml pancef');
INSERT INTO public.specification (id, contra_indications, manufacturer, recommended_consumption, structure) VALUES (11, 'Itching', 'Hemofarm', '2 times a day', '2ml of defrinol');
INSERT INTO public.specification (id, contra_indications, manufacturer, recommended_consumption, structure) VALUES (13, 'Itching', 'Hemofarm', '3 times a day', '2ml syrup');
INSERT INTO public.specification (id, contra_indications, manufacturer, recommended_consumption, structure) VALUES (15, 'Itching', 'Hemofarm', 'when neccessery', '2ml panadol');
INSERT INTO public.specification (id, contra_indications, manufacturer, recommended_consumption, structure) VALUES (17, 'Itching', 'Hemofarm', 'once a day', '2ml razadayne');
INSERT INTO public.specification (id, contra_indications, manufacturer, recommended_consumption, structure) VALUES (19, 'Headache', 'Hemofarm', 'once a day', '2ml buspirone');
INSERT INTO public.specification (id, contra_indications, manufacturer, recommended_consumption, structure) VALUES (21, 'Itching', 'Hemofarm', 'When necceseery', '10gr powder');


--
-- Data for Name: sunday_schedule; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.sunday_schedule (id, end_time, start_time) VALUES (367, '00:00:00', '00:00:00');
INSERT INTO public.sunday_schedule (id, end_time, start_time) VALUES (375, '00:00:00', '00:00:00');
INSERT INTO public.sunday_schedule (id, end_time, start_time) VALUES (383, '14:00:00', '00:00:00');
INSERT INTO public.sunday_schedule (id, end_time, start_time) VALUES (391, '00:00:00', '00:00:00');
INSERT INTO public.sunday_schedule (id, end_time, start_time) VALUES (399, '00:00:00', '00:00:00');
INSERT INTO public.sunday_schedule (id, end_time, start_time) VALUES (407, '19:00:00', '08:00:00');
INSERT INTO public.sunday_schedule (id, end_time, start_time) VALUES (415, '19:00:00', '08:00:00');
INSERT INTO public.sunday_schedule (id, end_time, start_time) VALUES (423, '19:00:00', '08:00:00');
INSERT INTO public.sunday_schedule (id, end_time, start_time) VALUES (440, '19:00:00', '00:00:00');
INSERT INTO public.sunday_schedule (id, end_time, start_time) VALUES (452, '23:00:00', '16:00:00');


--
-- Data for Name: supplier_medications; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.supplier_medications (id, code, name, quantity, reserved_quantity, supplier_id) VALUES (203, 333, 'Analgin', 299850, 300, 31);
INSERT INTO public.supplier_medications (id, code, name, quantity, reserved_quantity, supplier_id) VALUES (201, 111, 'Brufen', 2056, 850, 31);
INSERT INTO public.supplier_medications (id, code, name, quantity, reserved_quantity, supplier_id) VALUES (186, 333, 'Analgin', 299100, 400, 29);
INSERT INTO public.supplier_medications (id, code, name, quantity, reserved_quantity, supplier_id) VALUES (184, 111, 'Brufen', 242444, 850, 29);
INSERT INTO public.supplier_medications (id, code, name, quantity, reserved_quantity, supplier_id) VALUES (208, 888, 'Razadyne', 299570, 130, 31);
INSERT INTO public.supplier_medications (id, code, name, quantity, reserved_quantity, supplier_id) VALUES (205, 555, 'Defrinol', 300070, 130, 31);
INSERT INTO public.supplier_medications (id, code, name, quantity, reserved_quantity, supplier_id) VALUES (204, 444, 'Pancef', 299350, 650, 31);
INSERT INTO public.supplier_medications (id, code, name, quantity, reserved_quantity, supplier_id) VALUES (187, 444, 'Pancef', 298250, 650, 29);
INSERT INTO public.supplier_medications (id, code, name, quantity, reserved_quantity, supplier_id) VALUES (189, 666, 'Cough', 299100, 400, 29);
INSERT INTO public.supplier_medications (id, code, name, quantity, reserved_quantity, supplier_id) VALUES (192, 999, 'Buspirone', 298800, 100, 29);
INSERT INTO public.supplier_medications (id, code, name, quantity, reserved_quantity, supplier_id) VALUES (188, 555, 'Defrinol', 298800, 100, 29);
INSERT INTO public.supplier_medications (id, code, name, quantity, reserved_quantity, supplier_id) VALUES (191, 888, 'Razadyne', 298800, 100, 29);
INSERT INTO public.supplier_medications (id, code, name, quantity, reserved_quantity, supplier_id) VALUES (190, 777, 'Panadol', 298750, 50, 29);
INSERT INTO public.supplier_medications (id, code, name, quantity, reserved_quantity, supplier_id) VALUES (185, 222, 'Paracetamol', 298750, 50, 29);
INSERT INTO public.supplier_medications (id, code, name, quantity, reserved_quantity, supplier_id) VALUES (193, 1010, 'Kosili', 299100, 400, 29);
INSERT INTO public.supplier_medications (id, code, name, quantity, reserved_quantity, supplier_id) VALUES (207, 777, 'Panadol', 299550, 50, 31);
INSERT INTO public.supplier_medications (id, code, name, quantity, reserved_quantity, supplier_id) VALUES (209, 999, 'Buspirone', 300100, 100, 31);
INSERT INTO public.supplier_medications (id, code, name, quantity, reserved_quantity, supplier_id) VALUES (210, 1010, 'Kosili', 299900, 400, 31);
INSERT INTO public.supplier_medications (id, code, name, quantity, reserved_quantity, supplier_id) VALUES (202, 222, 'Paracetamol', 300050, 50, 31);
INSERT INTO public.supplier_medications (id, code, name, quantity, reserved_quantity, supplier_id) VALUES (206, 666, 'Cough', 300400, 400, 31);


--
-- Data for Name: thursday_schedule; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.thursday_schedule (id, end_time, start_time) VALUES (363, '16:00:00', '08:00:00');
INSERT INTO public.thursday_schedule (id, end_time, start_time) VALUES (371, '16:00:00', '08:00:00');
INSERT INTO public.thursday_schedule (id, end_time, start_time) VALUES (379, '14:00:00', '07:00:00');
INSERT INTO public.thursday_schedule (id, end_time, start_time) VALUES (387, '19:00:00', '00:00:00');
INSERT INTO public.thursday_schedule (id, end_time, start_time) VALUES (395, '19:00:00', '00:00:00');
INSERT INTO public.thursday_schedule (id, end_time, start_time) VALUES (403, '19:00:00', '08:00:00');
INSERT INTO public.thursday_schedule (id, end_time, start_time) VALUES (411, '19:00:00', '08:00:00');
INSERT INTO public.thursday_schedule (id, end_time, start_time) VALUES (419, '19:00:00', '08:00:00');
INSERT INTO public.thursday_schedule (id, end_time, start_time) VALUES (436, '19:00:00', '00:00:00');
INSERT INTO public.thursday_schedule (id, end_time, start_time) VALUES (448, '23:00:00', '16:00:00');


--
-- Data for Name: tuesday_schedule; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tuesday_schedule (id, end_time, start_time) VALUES (365, '16:00:00', '08:00:00');
INSERT INTO public.tuesday_schedule (id, end_time, start_time) VALUES (373, '16:00:00', '08:00:00');
INSERT INTO public.tuesday_schedule (id, end_time, start_time) VALUES (381, '14:00:00', '07:00:00');
INSERT INTO public.tuesday_schedule (id, end_time, start_time) VALUES (389, '19:00:00', '00:00:00');
INSERT INTO public.tuesday_schedule (id, end_time, start_time) VALUES (397, '19:00:00', '00:00:00');
INSERT INTO public.tuesday_schedule (id, end_time, start_time) VALUES (405, '19:00:00', '08:00:00');
INSERT INTO public.tuesday_schedule (id, end_time, start_time) VALUES (413, '19:00:00', '08:00:00');
INSERT INTO public.tuesday_schedule (id, end_time, start_time) VALUES (421, '19:00:00', '08:00:00');
INSERT INTO public.tuesday_schedule (id, end_time, start_time) VALUES (438, '19:00:00', '00:00:00');
INSERT INTO public.tuesday_schedule (id, end_time, start_time) VALUES (450, '23:00:00', '16:00:00');


--
-- Data for Name: user_authority; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO public.user_authority (user_id, authority_id) VALUES (3, 2);
INSERT INTO public.user_authority (user_id, authority_id) VALUES (5, 2);
INSERT INTO public.user_authority (user_id, authority_id) VALUES (7, 2);
INSERT INTO public.user_authority (user_id, authority_id) VALUES (9, 1);
INSERT INTO public.user_authority (user_id, authority_id) VALUES (19, 3);
INSERT INTO public.user_authority (user_id, authority_id) VALUES (21, 3);
INSERT INTO public.user_authority (user_id, authority_id) VALUES (23, 3);
INSERT INTO public.user_authority (user_id, authority_id) VALUES (25, 3);
INSERT INTO public.user_authority (user_id, authority_id) VALUES (27, 3);
INSERT INTO public.user_authority (user_id, authority_id) VALUES (31, 4);
INSERT INTO public.user_authority (user_id, authority_id) VALUES (29, 4);
INSERT INTO public.user_authority (user_id, authority_id) VALUES (39, 6);
INSERT INTO public.user_authority (user_id, authority_id) VALUES (41, 6);
INSERT INTO public.user_authority (user_id, authority_id) VALUES (43, 6);
INSERT INTO public.user_authority (user_id, authority_id) VALUES (45, 6);
INSERT INTO public.user_authority (user_id, authority_id) VALUES (47, 6);
INSERT INTO public.user_authority (user_id, authority_id) VALUES (49, 2);
INSERT INTO public.user_authority (user_id, authority_id) VALUES (35, 5);
INSERT INTO public.user_authority (user_id, authority_id) VALUES (55, 2);
INSERT INTO public.user_authority (user_id, authority_id) VALUES (33, 5);


--
-- Data for Name: wednesday_schedule; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.wednesday_schedule (id, end_time, start_time) VALUES (364, '16:00:00', '08:00:00');
INSERT INTO public.wednesday_schedule (id, end_time, start_time) VALUES (372, '16:00:00', '08:00:00');
INSERT INTO public.wednesday_schedule (id, end_time, start_time) VALUES (380, '14:00:00', '07:00:00');
INSERT INTO public.wednesday_schedule (id, end_time, start_time) VALUES (388, '19:00:00', '00:00:00');
INSERT INTO public.wednesday_schedule (id, end_time, start_time) VALUES (396, '19:00:00', '00:00:00');
INSERT INTO public.wednesday_schedule (id, end_time, start_time) VALUES (404, '19:00:00', '08:00:00');
INSERT INTO public.wednesday_schedule (id, end_time, start_time) VALUES (412, '19:00:00', '08:00:00');
INSERT INTO public.wednesday_schedule (id, end_time, start_time) VALUES (420, '19:00:00', '08:00:00');
INSERT INTO public.wednesday_schedule (id, end_time, start_time) VALUES (437, '19:00:00', '00:00:00');
INSERT INTO public.wednesday_schedule (id, end_time, start_time) VALUES (449, '23:00:00', '16:00:00');


--
-- Data for Name: working_hours_dermatologist; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.working_hours_dermatologist (id, dermatologist_id, friday_schedule, monday_schedule, pharmacy_id, saturday_schedule, sunday_schedule, thursday_schedule, tuesday_schedule, wednesday_schedule) VALUES (409, 55, 406, 402, 11, 408, 407, 403, 405, 404);
INSERT INTO public.working_hours_dermatologist (id, dermatologist_id, friday_schedule, monday_schedule, pharmacy_id, saturday_schedule, sunday_schedule, thursday_schedule, tuesday_schedule, wednesday_schedule) VALUES (417, 3, 414, 410, 11, 416, 415, 411, 413, 412);
INSERT INTO public.working_hours_dermatologist (id, dermatologist_id, friday_schedule, monday_schedule, pharmacy_id, saturday_schedule, sunday_schedule, thursday_schedule, tuesday_schedule, wednesday_schedule) VALUES (425, 5, 422, 418, 11, 424, 423, 419, 421, 420);
INSERT INTO public.working_hours_dermatologist (id, dermatologist_id, friday_schedule, monday_schedule, pharmacy_id, saturday_schedule, sunday_schedule, thursday_schedule, tuesday_schedule, wednesday_schedule) VALUES (442, 49, 439, 435, 13, 441, 440, 436, 438, 437);
INSERT INTO public.working_hours_dermatologist (id, dermatologist_id, friday_schedule, monday_schedule, pharmacy_id, saturday_schedule, sunday_schedule, thursday_schedule, tuesday_schedule, wednesday_schedule) VALUES (454, 7, 451, 447, 17, 453, 452, 448, 450, 449);


--
-- Data for Name: working_hours_pharmacist; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.working_hours_pharmacist (id, friday_schedule, monday_schedule, pharmacist_id, saturday_schedule, sunday_schedule, thursday_schedule, tuesday_schedule, wednesday_schedule) VALUES (369, 366, 362, 39, 368, 367, 363, 365, 364);
INSERT INTO public.working_hours_pharmacist (id, friday_schedule, monday_schedule, pharmacist_id, saturday_schedule, sunday_schedule, thursday_schedule, tuesday_schedule, wednesday_schedule) VALUES (377, 374, 370, 41, 376, 375, 371, 373, 372);
INSERT INTO public.working_hours_pharmacist (id, friday_schedule, monday_schedule, pharmacist_id, saturday_schedule, sunday_schedule, thursday_schedule, tuesday_schedule, wednesday_schedule) VALUES (385, 382, 378, 43, 384, 383, 379, 381, 380);
INSERT INTO public.working_hours_pharmacist (id, friday_schedule, monday_schedule, pharmacist_id, saturday_schedule, sunday_schedule, thursday_schedule, tuesday_schedule, wednesday_schedule) VALUES (393, 390, 386, 45, 392, 391, 387, 389, 388);
INSERT INTO public.working_hours_pharmacist (id, friday_schedule, monday_schedule, pharmacist_id, saturday_schedule, sunday_schedule, thursday_schedule, tuesday_schedule, wednesday_schedule) VALUES (401, 398, 394, 47, 400, 399, 395, 397, 396);


--
-- Name: authority_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.authority_id_seq', 300, true);


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 751, true);


--
-- Name: my_seq_genv2; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.my_seq_genv2', 51, true);


--
-- Name: my_seqv2; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.my_seqv2', 697, true);


--
-- Name: actions actions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.actions
    ADD CONSTRAINT actions_pkey PRIMARY KEY (id);


--
-- Name: address address_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT address_pkey PRIMARY KEY (address_id);


--
-- Name: authority authority_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authority
    ADD CONSTRAINT authority_pkey PRIMARY KEY (id);


--
-- Name: complaint complaint_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.complaint
    ADD CONSTRAINT complaint_pkey PRIMARY KEY (id);


--
-- Name: confirmation_token confirmation_token_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.confirmation_token
    ADD CONSTRAINT confirmation_token_pkey PRIMARY KEY (token_id);


--
-- Name: consulting consulting_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.consulting
    ADD CONSTRAINT consulting_pkey PRIMARY KEY (consulting_id);


--
-- Name: dermatologists_pharmacies dermatologists_pharmacies_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dermatologists_pharmacies
    ADD CONSTRAINT dermatologists_pharmacies_pkey PRIMARY KEY (dermatologist_id, pharmacy_id);


--
-- Name: eprescriptions eprescriptions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.eprescriptions
    ADD CONSTRAINT eprescriptions_pkey PRIMARY KEY (id);


--
-- Name: examination examination_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.examination
    ADD CONSTRAINT examination_pkey PRIMARY KEY (id);


--
-- Name: examination_schedule examination_schedule_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.examination_schedule
    ADD CONSTRAINT examination_schedule_pkey PRIMARY KEY (id);


--
-- Name: friday_schedule friday_schedule_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.friday_schedule
    ADD CONSTRAINT friday_schedule_pkey PRIMARY KEY (id);


--
-- Name: grading grading_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.grading
    ADD CONSTRAINT grading_pkey PRIMARY KEY (id);


--
-- Name: holiday_schedule_dermatologist holiday_schedule_dermatologist_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.holiday_schedule_dermatologist
    ADD CONSTRAINT holiday_schedule_dermatologist_pkey PRIMARY KEY (id);


--
-- Name: holiday_schedule_pharmacist holiday_schedule_pharmacist_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.holiday_schedule_pharmacist
    ADD CONSTRAINT holiday_schedule_pharmacist_pkey PRIMARY KEY (id);


--
-- Name: lack_medicine lack_medicine_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lack_medicine
    ADD CONSTRAINT lack_medicine_pkey PRIMARY KEY (id);


--
-- Name: loyalty_program loyalty_program_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.loyalty_program
    ADD CONSTRAINT loyalty_program_pkey PRIMARY KEY (id);


--
-- Name: mark_dermatologist mark_dermatologist_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mark_dermatologist
    ADD CONSTRAINT mark_dermatologist_pkey PRIMARY KEY (id);


--
-- Name: mark_medication mark_medication_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mark_medication
    ADD CONSTRAINT mark_medication_pkey PRIMARY KEY (id);


--
-- Name: mark_pharmacist mark_pharmacist_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mark_pharmacist
    ADD CONSTRAINT mark_pharmacist_pkey PRIMARY KEY (id);


--
-- Name: mark mark_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mark
    ADD CONSTRAINT mark_pkey PRIMARY KEY (id);


--
-- Name: medication_in_order medication_in_order_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medication_in_order
    ADD CONSTRAINT medication_in_order_pkey PRIMARY KEY (id);


--
-- Name: medication medication_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medication
    ADD CONSTRAINT medication_pkey PRIMARY KEY (id);


--
-- Name: medication_price_history medication_price_history_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medication_price_history
    ADD CONSTRAINT medication_price_history_pkey PRIMARY KEY (id);


--
-- Name: medication_price medication_price_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medication_price
    ADD CONSTRAINT medication_price_pkey PRIMARY KEY (id);


--
-- Name: medication_reservation medication_reservation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medication_reservation
    ADD CONSTRAINT medication_reservation_pkey PRIMARY KEY (id);


--
-- Name: medications_alternatives medications_alternatives_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medications_alternatives
    ADD CONSTRAINT medications_alternatives_pkey PRIMARY KEY (medication_id, medication_alternative_id);


--
-- Name: medications_eprescription medications_eprescription_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medications_eprescription
    ADD CONSTRAINT medications_eprescription_pkey PRIMARY KEY (id);


--
-- Name: monday_schedule monday_schedule_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.monday_schedule
    ADD CONSTRAINT monday_schedule_pkey PRIMARY KEY (id);


--
-- Name: offer offer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.offer
    ADD CONSTRAINT offer_pkey PRIMARY KEY (id);


--
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


--
-- Name: patients_medication_alergy patients_medication_alergy_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.patients_medication_alergy
    ADD CONSTRAINT patients_medication_alergy_pkey PRIMARY KEY (id);


--
-- Name: patients_subscriptions patients_subscriptions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.patients_subscriptions
    ADD CONSTRAINT patients_subscriptions_pkey PRIMARY KEY (patient_id, pharmacy_id);


--
-- Name: person_user person_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_user
    ADD CONSTRAINT person_user_pkey PRIMARY KEY (id);


--
-- Name: pharmacy_table pharmacy_table_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pharmacy_table
    ADD CONSTRAINT pharmacy_table_pkey PRIMARY KEY (id);


--
-- Name: prescription_medications prescription_medications_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prescription_medications
    ADD CONSTRAINT prescription_medications_pkey PRIMARY KEY (medication_id, prescription_id);


--
-- Name: prescription prescription_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prescription
    ADD CONSTRAINT prescription_pkey PRIMARY KEY (id);


--
-- Name: saturday_schedule saturday_schedule_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.saturday_schedule
    ADD CONSTRAINT saturday_schedule_pkey PRIMARY KEY (id);


--
-- Name: selling_statistics selling_statistics_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.selling_statistics
    ADD CONSTRAINT selling_statistics_pkey PRIMARY KEY (id);


--
-- Name: specification specification_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.specification
    ADD CONSTRAINT specification_pkey PRIMARY KEY (id);


--
-- Name: sunday_schedule sunday_schedule_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sunday_schedule
    ADD CONSTRAINT sunday_schedule_pkey PRIMARY KEY (id);


--
-- Name: supplier_medications supplier_medications_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.supplier_medications
    ADD CONSTRAINT supplier_medications_pkey PRIMARY KEY (id);


--
-- Name: thursday_schedule thursday_schedule_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.thursday_schedule
    ADD CONSTRAINT thursday_schedule_pkey PRIMARY KEY (id);


--
-- Name: tuesday_schedule tuesday_schedule_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tuesday_schedule
    ADD CONSTRAINT tuesday_schedule_pkey PRIMARY KEY (id);


--
-- Name: eprescriptions uk_66tok64wxrh3ah40ewrko46vv; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.eprescriptions
    ADD CONSTRAINT uk_66tok64wxrh3ah40ewrko46vv UNIQUE (code);


--
-- Name: working_hours_pharmacist uk_b30a56tkddl845g2spjojnhpw; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.working_hours_pharmacist
    ADD CONSTRAINT uk_b30a56tkddl845g2spjojnhpw UNIQUE (pharmacist_id);


--
-- Name: medication uk_pex4ncvrjs43bnm3oucqghy42; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medication
    ADD CONSTRAINT uk_pex4ncvrjs43bnm3oucqghy42 UNIQUE (code);


--
-- Name: person_user uk_rf06k3ssastbv4s069bxnfkug; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_user
    ADD CONSTRAINT uk_rf06k3ssastbv4s069bxnfkug UNIQUE (email);


--
-- Name: wednesday_schedule wednesday_schedule_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wednesday_schedule
    ADD CONSTRAINT wednesday_schedule_pkey PRIMARY KEY (id);


--
-- Name: working_hours_dermatologist working_hours_dermatologist_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.working_hours_dermatologist
    ADD CONSTRAINT working_hours_dermatologist_pkey PRIMARY KEY (id);


--
-- Name: working_hours_pharmacist working_hours_pharmacist_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.working_hours_pharmacist
    ADD CONSTRAINT working_hours_pharmacist_pkey PRIMARY KEY (id);


--
-- Name: eprescriptions fk11ymwjkds88y16p5df5jc3px0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.eprescriptions
    ADD CONSTRAINT fk11ymwjkds88y16p5df5jc3px0 FOREIGN KEY (patient_id) REFERENCES public.person_user(id);


--
-- Name: mark fk1uhfkwhfasv0bdho5680u6gg; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mark
    ADD CONSTRAINT fk1uhfkwhfasv0bdho5680u6gg FOREIGN KEY (patient_id) REFERENCES public.person_user(id);


--
-- Name: user_authority fk3cva0j6d4s82op3vm6es8f5e8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_authority
    ADD CONSTRAINT fk3cva0j6d4s82op3vm6es8f5e8 FOREIGN KEY (user_id) REFERENCES public.person_user(id);


--
-- Name: working_hours_dermatologist fk3xmlbbwsbws9q3y3f7e3qh0fa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.working_hours_dermatologist
    ADD CONSTRAINT fk3xmlbbwsbws9q3y3f7e3qh0fa FOREIGN KEY (thursday_schedule) REFERENCES public.thursday_schedule(id);


--
-- Name: medication_price fk40mi7xhw9qfnh0vwhdm4pyaqn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medication_price
    ADD CONSTRAINT fk40mi7xhw9qfnh0vwhdm4pyaqn FOREIGN KEY (pharmacy_id) REFERENCES public.pharmacy_table(id);


--
-- Name: patients_medication_alergy fk4u8nbwnyr3g7vrnwy7bf2tp1t; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.patients_medication_alergy
    ADD CONSTRAINT fk4u8nbwnyr3g7vrnwy7bf2tp1t FOREIGN KEY (patient_id) REFERENCES public.person_user(id);


--
-- Name: mark fk4xx2ag3oihptmf2f73wecd4wf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mark
    ADD CONSTRAINT fk4xx2ag3oihptmf2f73wecd4wf FOREIGN KEY (pharmacy_id) REFERENCES public.pharmacy_table(id);


--
-- Name: dermatologists_pharmacies fk595clgehw6lfsd0lk7mmi67ag; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dermatologists_pharmacies
    ADD CONSTRAINT fk595clgehw6lfsd0lk7mmi67ag FOREIGN KEY (dermatologist_id) REFERENCES public.person_user(id);


--
-- Name: consulting fk5f1v35mji0tpkj960met2f4ak; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.consulting
    ADD CONSTRAINT fk5f1v35mji0tpkj960met2f4ak FOREIGN KEY (pharmacist_id) REFERENCES public.person_user(id);


--
-- Name: medication_in_order fk5s2eq00r07gpxjuuhw6mkt2b2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medication_in_order
    ADD CONSTRAINT fk5s2eq00r07gpxjuuhw6mkt2b2 FOREIGN KEY (order_id) REFERENCES public.orders(id);


--
-- Name: working_hours_pharmacist fk5tgrinoe8pun6ew1iud4f7ib; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.working_hours_pharmacist
    ADD CONSTRAINT fk5tgrinoe8pun6ew1iud4f7ib FOREIGN KEY (thursday_schedule) REFERENCES public.thursday_schedule(id);


--
-- Name: medication_in_order fk60byx7hfss6hjkg0eeo6jvya0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medication_in_order
    ADD CONSTRAINT fk60byx7hfss6hjkg0eeo6jvya0 FOREIGN KEY (medication_id) REFERENCES public.medication(id);


--
-- Name: holiday_schedule_dermatologist fk6c0s0fpla02tmrd0xujexkk2m; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.holiday_schedule_dermatologist
    ADD CONSTRAINT fk6c0s0fpla02tmrd0xujexkk2m FOREIGN KEY (dermatologist_id) REFERENCES public.person_user(id);


--
-- Name: working_hours_dermatologist fk7ht2ax8nvt866xmgs38a2kgp2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.working_hours_dermatologist
    ADD CONSTRAINT fk7ht2ax8nvt866xmgs38a2kgp2 FOREIGN KEY (pharmacy_id) REFERENCES public.pharmacy_table(id);


--
-- Name: patients_subscriptions fk7ikq979vxwfm7llntx4uih0ww; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.patients_subscriptions
    ADD CONSTRAINT fk7ikq979vxwfm7llntx4uih0ww FOREIGN KEY (patient_id) REFERENCES public.person_user(id);


--
-- Name: working_hours_dermatologist fk844b3x7uhxq7nlf7oll2dts0p; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.working_hours_dermatologist
    ADD CONSTRAINT fk844b3x7uhxq7nlf7oll2dts0p FOREIGN KEY (tuesday_schedule) REFERENCES public.tuesday_schedule(id);


--
-- Name: offer fk95ckt6cqdcnrilgx2xhv3x8gv; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.offer
    ADD CONSTRAINT fk95ckt6cqdcnrilgx2xhv3x8gv FOREIGN KEY (order_id) REFERENCES public.orders(id);


--
-- Name: prescription_medications fk9lko4ptr84susi1104dp925he; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prescription_medications
    ADD CONSTRAINT fk9lko4ptr84susi1104dp925he FOREIGN KEY (prescription_id) REFERENCES public.prescription(id);


--
-- Name: medications_alternatives fkamt1pktydye05l8hunxyfca7a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medications_alternatives
    ADD CONSTRAINT fkamt1pktydye05l8hunxyfca7a FOREIGN KEY (medication_id) REFERENCES public.medication(id);


--
-- Name: examination fkark0stjkmbmfor5xxuxgid94v; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.examination
    ADD CONSTRAINT fkark0stjkmbmfor5xxuxgid94v FOREIGN KEY (examination_id) REFERENCES public.examination_schedule(id);


--
-- Name: grading fkc6ok3uymbyv0u7e7syypyv27i; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.grading
    ADD CONSTRAINT fkc6ok3uymbyv0u7e7syypyv27i FOREIGN KEY (patient_id) REFERENCES public.person_user(id);


--
-- Name: working_hours_dermatologist fkcklxxbru3r06xpabg5wyhtm5u; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.working_hours_dermatologist
    ADD CONSTRAINT fkcklxxbru3r06xpabg5wyhtm5u FOREIGN KEY (saturday_schedule) REFERENCES public.saturday_schedule(id);


--
-- Name: mark_pharmacist fkcqodt5yuqxn2ghcp8vfcmi8o7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mark_pharmacist
    ADD CONSTRAINT fkcqodt5yuqxn2ghcp8vfcmi8o7 FOREIGN KEY (pharmacist_id) REFERENCES public.person_user(id);


--
-- Name: consulting fkct3q6ra3d0fhhfluy30ihy7w6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.consulting
    ADD CONSTRAINT fkct3q6ra3d0fhhfluy30ihy7w6 FOREIGN KEY (patient_id) REFERENCES public.person_user(id);


--
-- Name: prescription fkcurdfo13jaj02sf9c3sjabag4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prescription
    ADD CONSTRAINT fkcurdfo13jaj02sf9c3sjabag4 FOREIGN KEY (patient_id) REFERENCES public.person_user(id);


--
-- Name: working_hours_dermatologist fkdtiqu3fveff0m680jco8aq3j0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.working_hours_dermatologist
    ADD CONSTRAINT fkdtiqu3fveff0m680jco8aq3j0 FOREIGN KEY (dermatologist_id) REFERENCES public.person_user(id);


--
-- Name: dermatologists_pharmacies fkes8ep3po2v3teo9wsm13i7k79; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dermatologists_pharmacies
    ADD CONSTRAINT fkes8ep3po2v3teo9wsm13i7k79 FOREIGN KEY (pharmacy_id) REFERENCES public.pharmacy_table(id);


--
-- Name: person_user fkfgwqxkxnfag3x9iqyse6jjdun; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_user
    ADD CONSTRAINT fkfgwqxkxnfag3x9iqyse6jjdun FOREIGN KEY (pharmacy_pharmacist) REFERENCES public.pharmacy_table(id);


--
-- Name: medication_price fkfstpw1nde8ov2mcewgyljhwxt; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medication_price
    ADD CONSTRAINT fkfstpw1nde8ov2mcewgyljhwxt FOREIGN KEY (medication_id) REFERENCES public.medication(id);


--
-- Name: examination_schedule fkghbixhf198m5l9bw3jdabt55u; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.examination_schedule
    ADD CONSTRAINT fkghbixhf198m5l9bw3jdabt55u FOREIGN KEY (pharmacy_id) REFERENCES public.pharmacy_table(id);


--
-- Name: complaint fkghyc7ymhn165def3j1105jg27; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.complaint
    ADD CONSTRAINT fkghyc7ymhn165def3j1105jg27 FOREIGN KEY (pharmacist_id) REFERENCES public.person_user(id);


--
-- Name: mark_dermatologist fkgojm77elvtl9yy3kbw61efadu; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mark_dermatologist
    ADD CONSTRAINT fkgojm77elvtl9yy3kbw61efadu FOREIGN KEY (dermatologist_id) REFERENCES public.person_user(id);


--
-- Name: user_authority fkgvxjs381k6f48d5d2yi11uh89; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_authority
    ADD CONSTRAINT fkgvxjs381k6f48d5d2yi11uh89 FOREIGN KEY (authority_id) REFERENCES public.authority(id);


--
-- Name: complaint fkh77rn9b97662ppqkal4k22bae; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.complaint
    ADD CONSTRAINT fkh77rn9b97662ppqkal4k22bae FOREIGN KEY (pharmacy_id) REFERENCES public.pharmacy_table(id);


--
-- Name: working_hours_dermatologist fkhn31nkf7f6fdjjk25ttlr7yb8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.working_hours_dermatologist
    ADD CONSTRAINT fkhn31nkf7f6fdjjk25ttlr7yb8 FOREIGN KEY (friday_schedule) REFERENCES public.friday_schedule(id);


--
-- Name: medication_reservation fkiatiyyut6saxc7y05rqdy93c6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medication_reservation
    ADD CONSTRAINT fkiatiyyut6saxc7y05rqdy93c6 FOREIGN KEY (pharmacy_id) REFERENCES public.pharmacy_table(id);


--
-- Name: actions fkigqgtajkjo4eucq0jcmhqkbc2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.actions
    ADD CONSTRAINT fkigqgtajkjo4eucq0jcmhqkbc2 FOREIGN KEY (pharmacy_actions) REFERENCES public.pharmacy_table(id);


--
-- Name: working_hours_pharmacist fkjf3h70nsdt5rwnhchcu7e1kub; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.working_hours_pharmacist
    ADD CONSTRAINT fkjf3h70nsdt5rwnhchcu7e1kub FOREIGN KEY (saturday_schedule) REFERENCES public.saturday_schedule(id);


--
-- Name: patients_medication_alergy fkjfjcyx72ajq719ieh7xpjdtx4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.patients_medication_alergy
    ADD CONSTRAINT fkjfjcyx72ajq719ieh7xpjdtx4 FOREIGN KEY (medication_id) REFERENCES public.medication(id);


--
-- Name: orders fkjo76qat5tjwkobifnokkrab6w; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fkjo76qat5tjwkobifnokkrab6w FOREIGN KEY (pharmacy_admin_id) REFERENCES public.person_user(id);


--
-- Name: complaint fkjvyh2pbhdg3ismim7mo08wjs2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.complaint
    ADD CONSTRAINT fkjvyh2pbhdg3ismim7mo08wjs2 FOREIGN KEY (dermatologist_id) REFERENCES public.person_user(id);


--
-- Name: working_hours_pharmacist fkk7knbs0sbuwl2ddcuwe65wjpl; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.working_hours_pharmacist
    ADD CONSTRAINT fkk7knbs0sbuwl2ddcuwe65wjpl FOREIGN KEY (monday_schedule) REFERENCES public.monday_schedule(id);


--
-- Name: mark_medication fkl03wnswl9wr4gqyo0kiibmda2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mark_medication
    ADD CONSTRAINT fkl03wnswl9wr4gqyo0kiibmda2 FOREIGN KEY (medication_id) REFERENCES public.medication(id);


--
-- Name: person_user fkly77ih3v5nj2vep645821bnwi; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_user
    ADD CONSTRAINT fkly77ih3v5nj2vep645821bnwi FOREIGN KEY (pharmacy_pharmacy_admin) REFERENCES public.pharmacy_table(id);


--
-- Name: supplier_medications fkm1jujn3m4w7a8wedkqa5h1jhx; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.supplier_medications
    ADD CONSTRAINT fkm1jujn3m4w7a8wedkqa5h1jhx FOREIGN KEY (supplier_id) REFERENCES public.person_user(id);


--
-- Name: working_hours_dermatologist fkm9jdg17j8u15cd2af9ju8hjv6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.working_hours_dermatologist
    ADD CONSTRAINT fkm9jdg17j8u15cd2af9ju8hjv6 FOREIGN KEY (monday_schedule) REFERENCES public.monday_schedule(id);


--
-- Name: working_hours_pharmacist fkmc8aefrw69yenta07twdnqjl; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.working_hours_pharmacist
    ADD CONSTRAINT fkmc8aefrw69yenta07twdnqjl FOREIGN KEY (wednesday_schedule) REFERENCES public.wednesday_schedule(id);


--
-- Name: medication fkmugiu0pe56i5y7k71jxd05qk9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medication
    ADD CONSTRAINT fkmugiu0pe56i5y7k71jxd05qk9 FOREIGN KEY (specification_id) REFERENCES public.specification(id);


--
-- Name: complaint fkn7a9v2vm0dhq04mx267w87w5k; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.complaint
    ADD CONSTRAINT fkn7a9v2vm0dhq04mx267w87w5k FOREIGN KEY (patient_id) REFERENCES public.person_user(id);


--
-- Name: prescription_medications fkndykus12of7fpsfm4hfyqmdwe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prescription_medications
    ADD CONSTRAINT fkndykus12of7fpsfm4hfyqmdwe FOREIGN KEY (medication_id) REFERENCES public.medication(id);


--
-- Name: mark_medication fknhx3goy7w765dsd8pxvx5yx7v; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mark_medication
    ADD CONSTRAINT fknhx3goy7w765dsd8pxvx5yx7v FOREIGN KEY (patient_id) REFERENCES public.person_user(id);


--
-- Name: medication_reservation fknlq73o9kkjig6rrup67ytreaw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medication_reservation
    ADD CONSTRAINT fknlq73o9kkjig6rrup67ytreaw FOREIGN KEY (patient_id) REFERENCES public.person_user(id);


--
-- Name: working_hours_dermatologist fkno3iwwjrmsuxkh8qkmrdkg2hb; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.working_hours_dermatologist
    ADD CONSTRAINT fkno3iwwjrmsuxkh8qkmrdkg2hb FOREIGN KEY (wednesday_schedule) REFERENCES public.wednesday_schedule(id);


--
-- Name: working_hours_dermatologist fknu2yf9r57pqy53nfx6xymy8es; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.working_hours_dermatologist
    ADD CONSTRAINT fknu2yf9r57pqy53nfx6xymy8es FOREIGN KEY (sunday_schedule) REFERENCES public.sunday_schedule(id);


--
-- Name: working_hours_pharmacist fko578jcegkgkjndvubjicclh5x; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.working_hours_pharmacist
    ADD CONSTRAINT fko578jcegkgkjndvubjicclh5x FOREIGN KEY (pharmacist_id) REFERENCES public.person_user(id);


--
-- Name: pharmacy_table fko68sou2ibkfnv64b5x6w4nv2n; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pharmacy_table
    ADD CONSTRAINT fko68sou2ibkfnv64b5x6w4nv2n FOREIGN KEY (address_id) REFERENCES public.address(address_id);


--
-- Name: mark_pharmacist fkobs2t4f9dwg2brin4d5h9eb3n; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mark_pharmacist
    ADD CONSTRAINT fkobs2t4f9dwg2brin4d5h9eb3n FOREIGN KEY (patient_id) REFERENCES public.person_user(id);


--
-- Name: working_hours_pharmacist fkoiqcwy529pikkyxjdoln9o12a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.working_hours_pharmacist
    ADD CONSTRAINT fkoiqcwy529pikkyxjdoln9o12a FOREIGN KEY (tuesday_schedule) REFERENCES public.tuesday_schedule(id);


--
-- Name: medications_eprescription fkovp7ybhyoctcodjitcocu476h; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medications_eprescription
    ADD CONSTRAINT fkovp7ybhyoctcodjitcocu476h FOREIGN KEY (e_prescription_medication) REFERENCES public.eprescriptions(id);


--
-- Name: confirmation_token fkpi7lchde6lrirrmw08lhs5tlw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.confirmation_token
    ADD CONSTRAINT fkpi7lchde6lrirrmw08lhs5tlw FOREIGN KEY (person_user_id) REFERENCES public.person_user(id);


--
-- Name: holiday_schedule_pharmacist fkpp3872i1txw73cns09w45ri39; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.holiday_schedule_pharmacist
    ADD CONSTRAINT fkpp3872i1txw73cns09w45ri39 FOREIGN KEY (pharmacist_id) REFERENCES public.person_user(id);


--
-- Name: mark_dermatologist fkq5fnvdgaskla6699go2dmrhfh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mark_dermatologist
    ADD CONSTRAINT fkq5fnvdgaskla6699go2dmrhfh FOREIGN KEY (patient_id) REFERENCES public.person_user(id);


--
-- Name: patients_subscriptions fkq625susnih1v9j9fg6k8ieilh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.patients_subscriptions
    ADD CONSTRAINT fkq625susnih1v9j9fg6k8ieilh FOREIGN KEY (pharmacy_id) REFERENCES public.pharmacy_table(id);


--
-- Name: examination fkraxt125j0py5antruvclr3icd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.examination
    ADD CONSTRAINT fkraxt125j0py5antruvclr3icd FOREIGN KEY (patient_id) REFERENCES public.person_user(id);


--
-- Name: person_user fkrbw30jcqrchecpnjkw9yha4un; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_user
    ADD CONSTRAINT fkrbw30jcqrchecpnjkw9yha4un FOREIGN KEY (address_address_id) REFERENCES public.address(address_id);


--
-- Name: offer fkrchaun8cpvcsjq3ifygnsuin0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.offer
    ADD CONSTRAINT fkrchaun8cpvcsjq3ifygnsuin0 FOREIGN KEY (supplier) REFERENCES public.person_user(id);


--
-- Name: working_hours_pharmacist fkrjtmwygptse6874nmpka3sfce; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.working_hours_pharmacist
    ADD CONSTRAINT fkrjtmwygptse6874nmpka3sfce FOREIGN KEY (sunday_schedule) REFERENCES public.sunday_schedule(id);


--
-- Name: prescription fks1teej4q7c4mibqytqn8oxln5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prescription
    ADD CONSTRAINT fks1teej4q7c4mibqytqn8oxln5 FOREIGN KEY (pharmacy_id) REFERENCES public.pharmacy_table(id);


--
-- Name: examination_schedule fks3xxpv269k9a8m7s5fmxadtpm; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.examination_schedule
    ADD CONSTRAINT fks3xxpv269k9a8m7s5fmxadtpm FOREIGN KEY (dermatologist_id) REFERENCES public.person_user(id);


--
-- Name: working_hours_pharmacist fks4941n10cswq8a54ifahigdby; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.working_hours_pharmacist
    ADD CONSTRAINT fks4941n10cswq8a54ifahigdby FOREIGN KEY (friday_schedule) REFERENCES public.friday_schedule(id);


--
-- Name: medications_alternatives fksbjkcedt7t691mcceu5kvr005; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medications_alternatives
    ADD CONSTRAINT fksbjkcedt7t691mcceu5kvr005 FOREIGN KEY (medication_alternative_id) REFERENCES public.medication(id);


--
-- Name: medication_reservation fktiva2wq8mkp16ifx6xmuwwhfm; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medication_reservation
    ADD CONSTRAINT fktiva2wq8mkp16ifx6xmuwwhfm FOREIGN KEY (medicine) REFERENCES public.medication(id);


--
-- PostgreSQL database dump complete
--

