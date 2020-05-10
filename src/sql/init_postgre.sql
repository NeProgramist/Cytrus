drop schema if exists cytrus cascade;
create schema cytrus;

alter schema cytrus owner to agb;

create table if not exists nodes
(
    id bigint not null
        constraint nodes_pkey
            primary key
);

alter table nodes owner to agb;

create table if not exists node_properties
(
    node_id bigint not null
        constraint fk_node_properties_node_id_id
            references nodes
            on update cascade on delete cascade,
    key varchar(64) not null,
    value varchar(512) not null
);

alter table node_properties owner to agb;

create table if not exists connections
(
    id bigint not null
        constraint connections_pkey
            primary key,
    target_id bigint not null
        constraint fk_connections_target_id_id
            references nodes
            on update cascade on delete cascade,
    source_id bigint not null
        constraint fk_connections_source_id_id
            references nodes
            on update cascade on delete cascade
);

alter table connections owner to agb;

create table if not exists connection_properties
(
    connection_id bigint not null
        constraint fk_connection_properties_connection_id_id
            references connections
            on update cascade on delete cascade,
    key varchar(64) not null,
    value varchar(512) not null
);

alter table connection_properties owner to agb;

