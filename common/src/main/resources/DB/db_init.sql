create table onesub.address
(
    id       bigint identity
        primary key,
    city     varchar(255),
    country  varchar(255),
    line_1   varchar(255),
    line_3   varchar(255),
    line_2   varchar(255),
    postcode varchar(255),
    province varchar(255)
)
go

create table onesub.discount
(
    id                bigint identity
        primary key,
    discount_percent  int,
    creation_date     datetime2(6),
    modification_date datetime2(6),
    is_deleted        bit,
    name              varchar(255)
)
go

create table onesub.organizations
(
    id   bigint identity
        primary key,
    name varchar(255)
)
go

create table onesub.roles
(
    id   bigint identity
        primary key,
    name varchar(255)
)
go

create table onesub.subscriptions
(
    id                bigint identity
        primary key,
    organization_id   bigint not null
        constraint sub_org_fk
            references onesub.organizations,
    creation_date     datetime2(6),
    modification_date datetime2(6),
    is_deleted        bit,
    name              varchar(255),
    price             bigint,
    discount_id       bigint
        constraint sub_discount
            references onesub.discount,
    is_available      bit
)
go

create table onesub.users
(
    id                bigint identity
        primary key,
    user_login        varchar(255),
    user_password     varchar(255),
    creation_date     datetime2(6),
    modification_date datetime2(6),
    email             varchar(255),
    first_name        varchar(255),
    is_deleted        bit,
    last_name         varchar(255),
    mobile_phone      varchar(255),
    address_id        bigint
        constraint users_address_fk
            references onesub.address,
    role_id           bigint
        constraint users_roles_fk
            references onesub.roles,
    discount_id       bigint
        constraint users_discount
            references onesub.discount
)
go

create table onesub.l_users_subscriptions
(
    id      bigint not null
        constraint l_users_subscriptions_pk
            primary key,
    sub_id  bigint
        constraint FK5d84locerag3nuhbahhxtrd58
            references onesub.subscriptions,
    user_id bigint
        constraint FKdegdpbnh0vhl454088byxsd9u
            references onesub.users
)
go

create table onesub.payments
(
    id                bigint identity
        primary key,
    amount            bigint,
    creation_date     datetime2(6),
    modification_date datetime2(6),
    provider          varchar(255),
    payment_status    varchar(255),
    order_id          bigint not null
        constraint payments_l_users_subscriptions_id_fk
            references onesub.l_users_subscriptions
)
go

