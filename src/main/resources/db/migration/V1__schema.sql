create table IF not exists public.leads(
    id uuid primary key default gen_random_uuid(),
    name varchar(50),
	address TEXT,
	phone varchar(10) UNIQUE,
	email varchar(50) UNIQUE,
	lead_source varchar(20),
	lead_state varchar(10),
	lead_sub_source varchar(10),
	lead_type varchar(10),
	assigned_to uuid NULL
);