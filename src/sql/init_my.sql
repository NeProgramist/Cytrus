drop schema if exists cytrus;
create schema if not exists cytrus collate utf8mb4_0900_ai_ci;
use cytrus;
create table if not exists nodes
(
	id bigint not null
		primary key
);

create table if not exists connections
(
	id bigint not null
		primary key,
	target_id bigint not null,
	source_id bigint not null,
	constraint fk_connections_source_id_id
		foreign key (source_id) references nodes (id)
			on update cascade on delete cascade,
	constraint fk_connections_target_id_id
		foreign key (target_id) references nodes (id)
			on update cascade on delete cascade
);

create table if not exists connection_properties
(
	connection_id bigint not null,
	`key` varchar(64) not null,
	value varchar(512) not null,
	constraint fk_connection_properties_connection_id_id
		foreign key (connection_id) references connections (id)
			on update cascade on delete cascade
);

create table if not exists node_properties
(
	node_id bigint not null,
	`key` varchar(64) not null,
	value varchar(512) not null,
	constraint fk_node_properties_node_id_id
		foreign key (node_id) references nodes (id)
			on update cascade on delete cascade
);
