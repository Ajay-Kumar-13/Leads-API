create table IF not exists public.leads(
    id uuid primary key default gen_random_uuid(),
    name varchar(50),
	address TEXT,
	phone varchar(10) UNIQUE,
	email varchar(50) UNIQUE,
	leadSource varchar(20),
	leadState varchar(10),
	leadSubSource varchar(10),
	leadType varchar(10),
	assignedTo uuid NULL
);