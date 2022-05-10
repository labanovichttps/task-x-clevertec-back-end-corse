drop table if exists gift_certificate_tag;
drop table if exists tag;
drop table if exists orders;
drop table if exists gift_certificate;
drop table if exists users;


create table gift_certificate
(
    id               BIGSERIAL PRIMARY KEY,
    name             VARCHAR(255)   NOT NULL,
    description      VARCHAR(255)   NOT NULL,
    price            DECIMAL(19, 2) NOT NULL,
    duration         BIGINT         NOT NULL,
    create_date      TIMESTAMP      NOT NULL,
    last_update_date TIMESTAMP
);

CREATE TABLE tag
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) not null
);

create table gift_certificate_tag
(
    gift_certificate_id bigint not null
        constraint gift_certificate_tag_gift_certificate_id_fkey
            references gift_certificate
            on delete cascade,
    tag_id              bigint not null
        constraint gift_certificate_tag_tag_id_fkey
            references tag
            on delete cascade,
    constraint gift_certificate_tag_pkey
        primary key (gift_certificate_id, tag_id)
);

create table users
(
    id    bigserial primary key,
    login varchar(255) unique not null
);

create table orders
(
    id             bigserial
        primary key,
    user_id        bigint         not null
        references users,
    certificate_id bigint         not null
        references gift_certificate,
    order_date     timestamp      not null,
    total_price    numeric(19, 2) not null
);