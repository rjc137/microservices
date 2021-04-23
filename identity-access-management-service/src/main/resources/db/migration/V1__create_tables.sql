create table activities (
	id bigint not null, 
		method varchar(255), 
		url varchar(255), 
		url_regex varchar(255), 
		primary key (id)
);
create table permission_activities (
	permission_id bigint not null, 
	activity_id bigint not null, 
	primary key (permission_id, activity_id)
);
create table permissions (
	id bigint not null, 
	created_at timestamp, 
	name varchar(255), 
	updated_at timestamp, 
	primary key (id)
);
create table role_permissions (
	role_id bigint not null, 
	permission_id bigint not null, 
	primary key (role_id, permission_id)
);
create table roles (
	id bigint not null, 
	created_at timestamp, 
	name varchar(255), 
	updated_at timestamp, 
	primary key (id)
);
create table user_roles (
	user_id bigint not null, 
	role_id bigint not null, 
	primary key (user_id, role_id)
);
create table users (
	id bigint not null, 
	email varchar(255), 
	primary key (id)
);

create sequence activities_sequence_generator start with 1 increment by 1;
create sequence permissions_sequence_generator start with 1 increment by 1;
create sequence roles_sequence_generator start with 1 increment by 1;
create sequence users_sequence_generator start with 1 increment by 1;

alter table permission_activities add constraint permission_activities_permission foreign key (activity_id) references activities;
alter table permission_activities add constraint permission_activities_activity foreign key (permission_id) references permissions;

alter table role_permissions add constraint role_permissions_permission foreign key (permission_id) references permissions;  
alter table role_permissions add constraint role_permissions_role foreign key (role_id) references roles;

alter table user_roles add constraint user_roles_permission foreign key (role_id) references roles; 
alter table user_roles add constraint user_roles_user foreign key (user_id) references users;
