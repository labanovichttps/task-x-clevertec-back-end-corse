CREATE TABLE gift_certificate
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
    name VARCHAR(255) NOT NULL UNIQUE
);

create table gift_certificate_tag
(
    gift_certificate_id BIGINT NOT NULL
        CONSTRAINT gift_certificate_tag_gift_certificate_id_fkey
            REFERENCES gift_certificate
            ON DELETE CASCADE,
    tag_id              BIGINT NOT NULL
        CONSTRAINT gift_certificate_tag_tag_id_fkey
            REFERENCES tag
            ON DELETE CASCADE,
    CONSTRAINT gift_certificate_tag_pkey
        PRIMARY KEY (gift_certificate_id, tag_id)
);

CREATE TABLE users
(
    id    BIGSERIAL PRIMARY KEY,
    login VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE orders
(
    id             BIGSERIAL
        PRIMARY KEY,
    user_id        BIGINT         NOT NULL
        REFERENCES users,
    certificate_id BIGINT         NOT NULL
        REFERENCES gift_certificate,
    order_date     TIMESTAMP      NOT NULL,
    total_price    NUMERIC(19, 2) NOT NULL
);

insert into tag (name)
values ('SPORT'),
       ('IT'),
       ('EDUCATION'),
       ('BEAUTY'),
       ('LITERATURE');

insert into gift_certificate (name, description, price, duration, create_date, last_update_date)
values ('FOOTBALL', 'Certificate about football', 124.99, 365, '2001-12-12', null),
       ('BASKETBALL', 'Certificate about basketball', 99.99, 120, '2010-08-12', null),
       ('BROWS', 'Certificate about make brows', 35.99, 102, '2020-01-04', '2021-04-05'),
       ('NAILS', 'Certificate about make nails', 33.99, 20, '2022-04-11', null),
       ('WRITER-2020', 'Certificate about football', 799.99, 365, '2020-12-12', '2022-01-12'),
       ('COOK-2022', 'Certificate about make dishes', 255.99, 365, '2022-01-03', null);

insert into gift_certificate_tag (gift_certificate_id, tag_id)
values (1, 1),
       (1, 3),
       (2, 1),
       (2, 3),
       (3, 4),
       (4, 4),
       (5, 5),
       (5, 3),
       (6, 3);

insert into users (login)
values ('tsimafeilabanovich'),
       ('alexandrpetukhov'),
       ('mikhailneznayfamilii'),
       ('olganeznayfamilii');

insert into orders (user_id, certificate_id, order_date, total_price)
values (1, 5, '2022-05-04 15:02:44.384000', 799.99),
       (1, 6, '2020-05-04 15:02:44.384000', 255.99),
       (2, 1, '2019-05-04 15:02:44.384000', 124.99),
       (2, 2, '2022-05-04 15:02:44.384000', 99.99),
       (4, 3, '2022-05-04 15:02:44.384000', 35.99),
       (4, 4, '2022-05-04 15:02:44.384000', 33.99);
