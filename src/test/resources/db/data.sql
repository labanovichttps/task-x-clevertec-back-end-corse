-- insert into tag (id, name)
-- values (1, 'SPORT'),
--        (2, 'IT'),
--        (3, 'EDUCATION'),
--        (4, 'BEAUTY'),
--        (5, 'LITERATURE');
--
-- select setval('tag_id_seq', (select max(id) from tag));
--
-- insert into gift_certificate (id, name, description, price, duration, create_date, last_update_date)
-- values (1, 'FOOTBALL', 'Certificate about football', 124.99, 365, '2001-12-12', null),
--        (2, 'BASKETBALL', 'Certificate about basketball', 99.99, 120, '2010-08-12', null),
--        (3, 'BROWS', 'Certificate about make brows', 35.99, 102, '2020-01-04', '2021-04-05'),
--        (4, 'NAILS', 'Certificate about make nails', 33.99, 20, '2022-04-11', null),
--        (5, 'WRITER-2020', 'Certificate about football', 799.99, 365, '2020-12-12', '2022-01-12'),
--        (6, 'COOK-2022', 'Certificate about make dishes', 255.99, 365, '2022-01-03', null);
--
-- select setval('gift_certificate_id_seq', (select max(id) from gift_certificate));
--
-- insert into gift_certificate_tag (gift_certificate_id, tag_id)
-- values (1, 1),
--        (1, 3),
--        (2, 1),
--        (2, 3),
--        (3, 4),
--        (4, 4),
--        (5, 5),
--        (5, 3),
--        (6, 3);
--
-- insert into users (id, login)
-- values (1, 'tsimafeilabanovich'),
--        (2, 'alexandrpetukhov'),
--        (3, 'mikhailneznayfamilii'),
--        (4, 'olganeznayfamilii');
--
-- select setval('users_id_seq', (select max(id) from users));

insert into orders (id, user_id, certificate_id, order_date, total_price)
values (1, 1, 5, '2022-05-04 15:02:44.384000', 799.99),
       (2, 1, 6, '2020-05-04 15:02:44.384000', 255.99),
       (3, 2, 1, '2019-05-04 15:02:44.384000', 124.99),
       (4, 2, 2, '2022-05-04 15:02:44.384000', 99.99),
       (5, 4, 3, '2022-05-04 15:02:44.384000', 35.99);

select setval('orders_id_seq', (select max(id) from orders));