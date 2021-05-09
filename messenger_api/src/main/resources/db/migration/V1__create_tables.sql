create sequence emessenger_db_message_seq start with 1 increment by 1;

create sequence emessenger_db_topic_seq start with 1 increment by 1;

create sequence emessenger_db_user_seq start with 1 increment by 1;

create table messages (
	id bigint not null, 
	attachments blob, 
	content varchar(65535), 
	sender varchar(255), 
	timestamp bigint, 
	optlock bigint, 
	topic_id bigint, 
	primary key (id)
);

create table topic (
	id bigint not null, 
	name varchar(255), 
	optlock bigint, 
	primary key (id)
);

create table topic_messages (
	topic_id bigint not null, 
	message_id bigint not null
);

create table topic_subscribers (
	topic_id bigint not null, 
	user_id bigint not null
);

create table user (
	id bigint not null, 
	address varchar(200), 
	email varchar(50), 
	mobile_no varchar(20), 
	name varchar(255), 
	password varchar(120), 
	optlock bigint, 
	primary key (id)
);

alter table topic_messages add constraint emessenger_db_topic_messages_message_id_uk unique (message_id);

alter table user add constraint emessenger_db_user_email_uk unique (email);

alter table messages add constraint emessenger_db_messages_topic_id_fk foreign key (topic_id) references topic;

alter table topic_messages add constraint emessenger_db_topic_messages_message_id_fk foreign key (message_id) references messages;

alter table topic_messages add constraint emessenger_db_topic_messages_topic_id_fk foreign key (topic_id) references topic;

alter table topic_subscribers add constraint emessenger_db_topic_subscribers_user_id_fk foreign key (user_id) references user;

alter table topic_subscribers add constraint emessenger_db_topic_subscribers_topic_id_fk foreign key (topic_id) references topic;