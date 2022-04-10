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

CREATE TABLE IF NOT EXISTS gift_certificate_tag
(
    gift_certificate_id BIGINT REFERENCES gift_certificate (id) NOT NULL,
    tag_id              BIGINT REFERENCES tag (id)              NOT NULL,
    PRIMARY KEY (gift_certificate_id, tag_id)
    );