
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


insert into trustgroup  values (uuid_generate_v4(), 'DO NOT TRUST' , 'this is default trust group' , now() , null , 'ADMIN' , null );
insert into trustgroup  values (uuid_generate_v4(), 'Top 100' , 'this is Top 100 trust group' , now() , null , 'ADMIN' , null );
insert into trustgroup  values (uuid_generate_v4(), 'Top 10000' , 'this is Top 10000 trust group' , now() , null , 'ADMIN' , null );
insert into trustgroup  values (uuid_generate_v4(), 'Top 30000' , 'this is Top 30000 trust group' , now() , null , 'ADMIN' , null );




insert into permission values (uuid_generate_v4(), 'Can invite collaborators?', true,'this enables domain on the drive to edit and invite editors' , now(), null , 'ADMIN' , null);
insert into permission values (uuid_generate_v4(), 'Can invite collaborators?', false,'this disables domain on the drive to edit and invite editors' , now(), null , 'ADMIN' , null);

insert into permission values (uuid_generate_v4(), 'Can enable invite links?', true,'this enables domain on the drive to edit and invite editors' , now(), null , 'ADMIN' , null);
insert into permission values (uuid_generate_v4(), 'Can enable invite links?', false,'this disables domain on the drive to edit and invite editors' , now(), null , 'ADMIN' , null);

insert into permission values (uuid_generate_v4(), 'Can enable group invites?', true,'this enables domain on the drive to edit and invite editors' , now(), null , 'ADMIN' , null);
insert into permission values (uuid_generate_v4(), 'Can enable group invites?', false,'this disables domain on the drive to edit and invite editors' , now(), null , 'ADMIN' , null);

insert into permission values (uuid_generate_v4(), 'Can transfer ownership of files?', true,'this enables domain on the drive to edit and invite editors' , now(), null , 'ADMIN' , null);
insert into permission values (uuid_generate_v4(), 'Can transfer ownership of files?', false,'this disables domain on the drive to edit and invite editors' , now(), null , 'ADMIN' , null);


insert into department values (uuid_generate_v4(),'Base Template', now(), null,'ADMIN' , '');
insert into department values (uuid_generate_v4(),'Sales', now(), null,'ADMIN' , '');
insert into department values (uuid_generate_v4(),'Legal', now(), null,'ADMIN' , '');
insert into department values (uuid_generate_v4(),'Finance', now(), null,'ADMIN' , '');
insert into department values (uuid_generate_v4(),'Investor Relations', now(), null,'ADMIN' , '');


insert into trustgroup_flavour values (uuid_generate_v4() ,'null', 'This is One of the flavour for DO NOT TRUST trust group with out any of the permissions'
									  , now(),null , null, 'ADMIN', null);
insert into trustgroup_flavour values (uuid_generate_v4() ,'owner', 'This is One of the flavour for TOP 100 trust group with all of the permissions'
									  , now(),null , null, 'ADMIN', null);
insert into trustgroup_flavour values (uuid_generate_v4() ,'writer','This is One of the flavour for Top 10000 trust group with 3 of the permissions'
									  , now(),null , null, 'ADMIN', null);
insert into trustgroup_flavour values (uuid_generate_v4() ,'reader', 'This is One of the flavour for Top 30000 trust group with 3 of the permissions'
									  , now(),null , null, 'ADMIN', null);
									  
									  
									  
insert into trustgroup_flavour values (uuid_generate_v4() ,'null', 'This is One of the flavour for DO NOT TRUST trust group with out any of the permissions'
									  , now(),null , null, 'ADMIN', null);
insert into trustgroup_flavour values (uuid_generate_v4() ,'writer', 'This is One of the flavour for TOP 100 trust group with Editor rights'
									  , now(),null , null, 'ADMIN', null);
insert into trustgroup_flavour values (uuid_generate_v4() ,'writer','This is One of the flavour for Top 10000 trust group with 2 of the permissions just a reader'
									  , now(),null , null, 'ADMIN', null);
insert into trustgroup_flavour values (uuid_generate_v4() ,'reader', 'This is One of the flavour for Top 30000 trust group with 2 of the permissions just a reader'
									  , now(),null , null, 'ADMIN', null);


