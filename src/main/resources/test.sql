create table "user"
(
    first_name varchar,
    last_name  varchar,
    user_id    bigint  not null
        constraint user_pk
            primary key,
    username   varchar not null,
    email      varchar not null,
    password   varchar not null
);

alter table "user"
    owner to postgres;

create unique index user_username_uindex
    on "user" (username);

create unique index "user_e-mail_uindex"
    on "user" (email);

create table business_object
(
    object_id   bigint  not null
        constraint business_object_pk
            primary key,
    name        varchar not null,
    description varchar,
    source_id   bigint  not null
);

alter table business_object
    owner to postgres;

create unique index business_object_object_id_uindex
    on business_object (object_id);

create unique index business_object_source_id_uindex
    on business_object (source_id);

create table favourite
(
    favourite_id bigint not null
        constraint favourite_pk
            primary key,
    user_id      bigint not null
        constraint favourite_user_user_id_fk
            references "user",
    object_id    bigint not null
        constraint favourite_business_object_object_id_fk
            references business_object
);

alter table favourite
    owner to postgres;

create unique index favourite_favourite_id_uindex
    on favourite (favourite_id);

create unique index favourite_user_id_uindex
    on favourite (user_id);

create unique index favourite_object_id_uindex
    on favourite (object_id);

create table label
(
    label_id bigint  not null
        constraint label_pk
            primary key,
    name     varchar not null
);

alter table label
    owner to postgres;

create unique index label_name_uindex
    on label (name);

create unique index label_label_id_uindex
    on label (label_id);

create table bo_2_label
(
    label_id  bigint not null
        constraint bo_2_label_label_label_id_fk
            references label,
    object_id bigint not null
        constraint bo_2_label_business_object_object_id_fk
            references business_object
        constraint bo_2_label_business_object_object_id_fk_2
            references business_object
);

alter table bo_2_label
    owner to postgres;

create unique index bo_2_label_label_id_uindex
    on bo_2_label (label_id);

create unique index bo_2_label_object_id_uindex
    on bo_2_label (object_id);

create table source_system
(
    source_id bigint  not null
        constraint source_system_pk
            primary key,
    name      varchar not null
);

alter table source_system
    owner to postgres;

create unique index source_system_source_id_uindex
    on source_system (source_id);

create unique index source_system_name_uindex
    on source_system (name);

create table bo_2_source
(
    source_id bigint not null
        constraint bo_2_source_source_system_source_id_fk
            references source_system,
    object_id bigint not null
        constraint bo_2_source_business_object_object_id_fk
            references business_object
);

alter table bo_2_source
    owner to postgres;

create unique index bo_2_source_source_id_uindex
    on bo_2_source (source_id);

create unique index bo_2_source_object_id_uindex
    on bo_2_source (object_id);

create table bo_2_bo
(
    object_id_1 bigint not null
        constraint bo_2_bo_business_object_object_id_fk
            references business_object,
    object_id_2 bigint not null
        constraint bo_2_bo_business_object_object_id_fk_2
            references business_object
);

alter table bo_2_bo
    owner to postgres;

create unique index bo_2_bo_object_id_1_uindex
    on bo_2_bo (object_id_1);

create unique index bo_2_bo_object_id_2_uindex
    on bo_2_bo (object_id_2);

