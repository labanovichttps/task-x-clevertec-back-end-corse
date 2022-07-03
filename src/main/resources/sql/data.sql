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

-- insert into orders (user_id, certificate_id, order_date, total_price)
-- values (1, 5, '2022-05-06 15:05:44.384000', 799.99),
--        (1, 6, '2020-05-04 15:02:44.384000', 255.99),
--        (2, 1, '2019-05-04 15:02:44.384000', 124.99),
--        (2, 2, '2022-05-04 15:02:44.384000', 99.99),
--        (4, 3, '2022-05-04 15:02:44.384000', 35.99),
--        (4, 4, '2021-06-04 15:02:44.384000', 33.99);
