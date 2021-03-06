create table airport
(
    id               integer      not null,
    cost             integer      not null,
    deposit_cost     integer      not null,
    is_enable_to_buy bit          not null,
    is_in_deposit    bit          not null,
    name             varchar(255) not null,
    rent_cost        integer      not null,
    gamer_id         integer,
    primary key (id)
) engine = InnoDB;

create table city
(
    id               integer      not null,
    color            integer,
    cost             integer      not null,
    deposit_cost     integer      not null,
    home_cost        integer      not null,
    home_count       integer      not null,
    is_enable_to_buy bit          not null,
    is_in_deposit    bit          not null,
    name             varchar(255) not null,
    rent_cost        integer      not null,
    gamer_id         integer,
    primary key (id)
) engine = InnoDB;

create table communal
(
    id               integer      not null,
    cost             integer      not null,
    deposit_cost     integer      not null,
    is_enable_to_buy bit          not null,
    is_in_deposit    bit          not null,
    name             varchar(255) not null,
    rent_cost        integer      not null,
    gamer_id         integer,
    primary key (id)
) engine = InnoDB;

create table gamer
(
    id    integer      not null,
    money integer      not null,
    name  varchar(255) not null,
    primary key (id)
) engine = InnoDB;

create table hibernate_sequence
(
    next_val bigint
) engine = InnoDB;

insert into hibernate_sequence
values (1);

alter table airport
    add constraint airport_gamer_fk
        foreign key (gamer_id)
            references gamer (id);

alter table city
    add constraint city_gamer_fk
        foreign key (gamer_id)
            references gamer (id);

alter table communal
    add constraint communal_gamer_fk
        foreign key (gamer_id)
            references gamer (id)