create table gift_certificate
(
    id               BIGSERIAL PRIMARY KEY,
    name             VARCHAR(255) NOT NULL,
    description      VARCHAR(255),
    price            DECIMAL(19, 2),
    duration         BIGINT,
    create_date      TIMESTAMP,
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
)
