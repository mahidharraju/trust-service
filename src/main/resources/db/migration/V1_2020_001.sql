CREATE TABLE trustgroup (
    trust_group_id uuid NOT NULL,
    name character varying(500) NOT NULL,
	description character varying(500) NOT NULL,
    "created_date" timestamp without time zone NOT NULL,
    "updated_date" timestamp without time zone,
    "created_by" character varying(500) NOT NULL,
    "updated_by" character varying(500),
	CONSTRAINT "PK_trust_group_id" PRIMARY KEY ("trust_group_id")
);


CREATE TABLE permission (
    permission_id uuid NOT NULL,
    name character varying(500) NOT NULL,
	"permission_value" boolean NOT NULL,
	description character varying(500) NOT NULL,
    "created_date" timestamp without time zone NOT NULL,
    "updated_date" timestamp without time zone,
    "created_by" character varying(500) NOT NULL,
    "updated_by" character varying(500),
	CONSTRAINT "PK_permission_id" PRIMARY KEY ("permission_id")
);

CREATE TABLE trustgroup_flavour (
    "tg_flavour_id" uuid NOT NULL,
	"role" character varying(500) NOT NULL,
	"flavour_description" character varying(500) NOT NULL,
    "created_date" timestamp without time zone NOT NULL,
    "updated_date" timestamp without time zone,
    "expiry_date" timestamp without time zone,
    "created_by" character varying(500) NOT NULL,
    "updated_by" character varying(500),
	CONSTRAINT "PK_trustgroup_flavour" PRIMARY KEY ("tg_flavour_id")
);

CREATE TABLE trustgroup_permissions_def (
    "id" uuid NOT NULL,
    "tg_flavour_id" uuid NOT NULL,
    "trust_group_id" uuid NOT NULL,
	"permission_id" uuid NOT NULL,
    "created_date" timestamp without time zone NOT NULL,
    "updated_date" timestamp without time zone,
    "expiry_date" timestamp without time zone,
    "created_by" character varying(500) NOT NULL,
    "updated_by" character varying(500),
	CONSTRAINT "PK_trustgroup_permissions_def_id" PRIMARY KEY ("id"),
	CONSTRAINT "tg_uinque" UNIQUE("id","tg_flavour_id","trust_group_id"),
	CONSTRAINT "FK_trust_group_id" FOREIGN KEY (trust_group_id) REFERENCES trustgroup(trust_group_id),
	CONSTRAINT "FK_tg_flavour_id" FOREIGN KEY (tg_flavour_id) REFERENCES trustgroup_flavour(tg_flavour_id),
	CONSTRAINT "FK_permission_id" FOREIGN KEY (permission_id) REFERENCES permission(permission_id)
);

CREATE TABLE department (
    dept_id uuid NOT NULL,
    name character varying(500) NOT NULL,
    "created_date" timestamp without time zone NOT NULL,
    "updated_date" timestamp without time zone,
    "created_by" character varying(500) NOT NULL,
    "updated_by" character varying(500),
	CONSTRAINT "PK_dept_id" PRIMARY KEY ("dept_id")
);


CREATE TABLE department_trustgroups (
    "id" uuid NOT NULL,
	"dept_id" uuid NOT NULL,
    "tg_flavour_id" uuid NOT NULL,
    "org_collab_id" uuid NOT NULL,
	"created_date" timestamp without time zone NOT NULL,
    "updated_date" timestamp without time zone,
    "expiry_date" timestamp without time zone,
    "created_by" character varying(500) NOT NULL,
    "updated_by" character varying(500),
	CONSTRAINT "PK_department_trustgroups_id" PRIMARY KEY ("id"),
	CONSTRAINT "FK_dept_id" FOREIGN KEY (dept_id) REFERENCES department(dept_id),
	CONSTRAINT "dept_tg_uinque" UNIQUE("tg_flavour_id","dept_id" , "org_collab_id"),
	CONSTRAINT "FK_tg_flavour_id" FOREIGN KEY (tg_flavour_id) REFERENCES trustgroup_flavour(tg_flavour_id)
	);


