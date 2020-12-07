CREATE TABLE trustgroup (
    id BIGINT NOT NULL,
    name character varying(500) NOT NULL,
	description character varying(500) NOT NULL,
    created_date timestamp NOT NULL,
    updated_date timestamp,
    created_by character varying(500) NOT NULL,
    updated_by character varying(500),
	CONSTRAINT PK_trust_group_id PRIMARY KEY (id)
);


CREATE TABLE permission (
    id BIGINT NOT NULL,
    name character varying(500) NOT NULL,
	permission_value boolean NOT NULL,
	description character varying(500) NOT NULL,
    created_date timestamp NOT NULL,
    updated_date timestamp,
    created_by character varying(500) NOT NULL,
    updated_by character varying(500),
	CONSTRAINT PK_permission_id PRIMARY KEY (id)
);

CREATE TABLE trustgroup_flavour (
    id BIGINT NOT NULL,
	role character varying(500) NOT NULL,
	flavour_description character varying(500) NOT NULL,
    created_date timestamp NOT NULL,
    updated_date timestamp,
    expiry_date timestamp,
    created_by character varying(500) NOT NULL,
    updated_by character varying(500),
	CONSTRAINT PK_trustgroup_flavour PRIMARY KEY (id)
);

CREATE TABLE trustgroup_permissions_def (
    id BIGINT NOT NULL,
    tg_flavour_id BIGINT NOT NULL,
    trust_group_id BIGINT NOT NULL,
	permission_id BIGINT NOT NULL,
    created_date timestamp NOT NULL,
    updated_date timestamp,
    expiry_date timestamp,
    created_by character varying(500) NOT NULL,
    updated_by character varying(500),
	CONSTRAINT PK_trustgroup_permissions_def_id PRIMARY KEY (id),
	CONSTRAINT tg_uinque UNIQUE(id,tg_flavour_id,trust_group_id),
	CONSTRAINT FK_trust_group_id FOREIGN KEY (trust_group_id) REFERENCES trustgroup(id),
	CONSTRAINT FK_tg_flavour_id FOREIGN KEY (tg_flavour_id) REFERENCES trustgroup_flavour(id),
	CONSTRAINT FK_permission_id FOREIGN KEY (permission_id) REFERENCES permission(id)
);

CREATE TABLE department (
    id BIGINT NOT NULL,
    name character varying(500) NOT NULL,
    created_date timestamp NOT NULL,
    updated_date timestamp,
    created_by character varying(500) NOT NULL,
    updated_by character varying(500),
	CONSTRAINT PK_dept_id PRIMARY KEY (id)
);


CREATE TABLE department_trustgroups (
    id BIGINT NOT NULL,
	dept_id BIGINT NOT NULL,
    tg_flavour_id BIGINT NOT NULL,
    org_collab_id BIGINT NOT NULL,
	created_date timestamp NOT NULL,
    updated_date timestamp,
    expiry_date timestamp,
    created_by character varying(500) NOT NULL,
    updated_by character varying(500),
	CONSTRAINT PK_department_trustgroups_id PRIMARY KEY (id),
	CONSTRAINT FK_dept_id FOREIGN KEY (dept_id) REFERENCES department(id),
	CONSTRAINT dept_tg_uinque UNIQUE(tg_flavour_id,dept_id , org_collab_id),
	CONSTRAINT FK_tg_flavour_id1 FOREIGN KEY (tg_flavour_id) REFERENCES trustgroup_flavour(id)
	);

CREATE SEQUENCE PERMISSION_ID_SEQ INCREMENT 100 START 1;
CREATE SEQUENCE trustgroup_id_seq INCREMENT 100 START 1;
CREATE SEQUENCE dept_id_seq INCREMENT 100 START 1;
CREATE SEQUENCE trustgroup_flavour_id_seq INCREMENT 100 START 1;
CREATE SEQUENCE dept_tg_id_seq INCREMENT 100 START 1;
CREATE SEQUENCE trustgroup_permission_id_seq INCREMENT 100 START 1;



