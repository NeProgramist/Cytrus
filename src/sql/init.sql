drop schema if exists cytrus cascade;
create schema cytrus;

create table cytrus.nodes
(
    id integer not null
        constraint pknodeid
            primary key
);

create table cytrus.node_properties
(
    key     varchar(64) not null,
    value   varchar(512) not null,
    node_id integer     not null
        constraint fknodeid
            references cytrus.nodes
            on update cascade on delete cascade
);

create table cytrus.connections
(
    id  integer  not null
        constraint pkconnectionid
            primary key,
    target_id integer not null
        constraint fktargetid
            references cytrus.nodes
            on update cascade on delete cascade,
    source_id integer not null
        constraint fksourceid
            references cytrus.nodes
            on update cascade on delete cascade
);

create table cytrus.connection_properties
(
    key           varchar(64) not null,
    value         varchar(512) not null,
    connection_id integer     not null
        constraint fkconnectionid
            references cytrus.connections
            on update cascade on delete cascade
);

create table cytrus.node_labels
(
    node_id integer     not null
        constraint node_labels_nodes_id_fk
            references cytrus.nodes
            on update cascade on delete cascade,
    label   varchar(64) not null
);
