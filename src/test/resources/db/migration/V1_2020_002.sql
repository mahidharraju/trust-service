


insert into trustgroup  values (trustgroup_id_seq.nextval, 'DO NOT TRUST' , 'this is default trust group' , now() , null , 'ADMIN' , null );
insert into trustgroup  values (trustgroup_id_seq.nextval, 'Top 100' , 'this is Top 100 trust group' , now() , null , 'ADMIN' , null );
insert into trustgroup  values (trustgroup_id_seq.nextval, 'Top 10000' , 'this is Top 10000 trust group' , now() , null , 'ADMIN' , null );
insert into trustgroup  values (trustgroup_id_seq.nextval, 'Top 30000' , 'this is Top 30000 trust group' , now() , null , 'ADMIN' , null );



insert into permission values (permission_id_seq.nextval, 'Can invite collaborators?', true,'this enables domain on the drive to edit and invite editors' , now(), null , 'ADMIN' , null);
insert into permission values (permission_id_seq.nextval, 'Can invite collaborators?', false,'this disables domain on the drive to edit and invite editors' , now(), null , 'ADMIN' , null);

insert into permission values (permission_id_seq.nextval, 'Can enable invite links?', true,'this enables domain on the drive to edit and invite editors' , now(), null , 'ADMIN' , null);
insert into permission values (permission_id_seq.nextval, 'Can enable invite links?', false,'this disables domain on the drive to edit and invite editors' , now(), null , 'ADMIN' , null);

insert into permission values (permission_id_seq.nextval, 'Can enable group invites?', true,'this enables domain on the drive to edit and invite editors' , now(), null , 'ADMIN' , null);
insert into permission values (permission_id_seq.nextval, 'Can enable group invites?', false,'this disables domain on the drive to edit and invite editors' , now(), null , 'ADMIN' , null);

insert into permission values (permission_id_seq.nextval, 'Can transfer ownership of files?', true,'this enables domain on the drive to edit and invite editors' , now(), null , 'ADMIN' , null);
insert into permission values (permission_id_seq.nextval, 'Can transfer ownership of files?', false,'this disables domain on the drive to edit and invite editors' , now(), null , 'ADMIN' , null);


insert into department values (dept_id_seq.nextval,'Base Template', now(), null,'ADMIN' , '');
insert into department values (dept_id_seq.nextval,'Sales', now(), null,'ADMIN' , '');
insert into department values (dept_id_seq.nextval,'Legal', now(), null,'ADMIN' , '');
insert into department values (dept_id_seq.nextval,'Finance', now(), null,'ADMIN' , '');
insert into department values (dept_id_seq.nextval,'Investor Relations', now(), null,'ADMIN' , '');


insert into trustgroup_flavour values (trustgroup_flavour_id_seq.nextval ,'null', 'This is One of the flavour for DO NOT TRUST trust group with out any of the permissions'
									  , now(),null , null, 'ADMIN', null);
insert into trustgroup_flavour values (trustgroup_flavour_id_seq.nextval ,'owner', 'This is One of the flavour for TOP 100 trust group with all of the permissions'
									  , now(),null , null, 'ADMIN', null);
insert into trustgroup_flavour values (trustgroup_flavour_id_seq.nextval ,'writer','This is One of the flavour for Top 10000 trust group with 3 of the permissions'
									  , now(),null , null, 'ADMIN', null);
insert into trustgroup_flavour values (trustgroup_flavour_id_seq.nextval ,'reader', 'This is One of the flavour for Top 30000 trust group with 3 of the permissions'
									  , now(),null , null, 'ADMIN', null);



insert into trustgroup_flavour values (trustgroup_flavour_id_seq.nextval ,'null', 'This is One of the flavour for DO NOT TRUST trust group with out any of the permissions'
									  , now(),null , null, 'ADMIN', null);
insert into trustgroup_flavour values (trustgroup_flavour_id_seq.nextval ,'writer', 'This is One of the flavour for TOP 100 trust group with Editor rights'
									  , now(),null , null, 'ADMIN', null);
insert into trustgroup_flavour values (trustgroup_flavour_id_seq.nextval ,'writer','This is One of the flavour for Top 10000 trust group with 2 of the permissions just a reader'
									  , now(),null , null, 'ADMIN', null);
insert into trustgroup_flavour values (trustgroup_flavour_id_seq.nextval ,'reader', 'This is One of the flavour for Top 30000 trust group with 2 of the permissions just a reader'
									  , now(),null , null, 'ADMIN', null);


