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
