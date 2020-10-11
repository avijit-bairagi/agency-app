 create table locations (id bigint not null auto_increment, name varchar(255), primary key (id)) engine=InnoDB;
 create table posts (id bigint not null auto_increment, created_at datetime(6) not null, created_by varchar(255) not null, updated_at datetime(6) not null, updated_by varchar(255) not null, privacy_type varchar(255) not null, reason TEXT, location_id bigint, user_id bigint not null, primary key (id)) engine=InnoDB;
 create table users (id bigint not null auto_increment, created_at datetime(6) not null, created_by varchar(255) not null, updated_at datetime(6) not null, updated_by varchar(255) not null, email varchar(255), first_name varchar(255), last_name varchar(255), password varchar(255), post_id bigint, primary key (id)) engine=InnoDB;
 alter table users add constraint UK_r53o2ojjw4fikudfnsuuga336 unique (password);
 alter table posts add constraint FK1vpruxtho87bsysr5g2jpntnr foreign key (location_id) references locations (id);
 alter table posts add constraint FK5lidm6cqbc7u4xhqpxm898qme foreign key (user_id) references users (id);
 alter table users add constraint FK5na1mqr6obdc53ool8egadvar foreign key (post_id) references posts (id);