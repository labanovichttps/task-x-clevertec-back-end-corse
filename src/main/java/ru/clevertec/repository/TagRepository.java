package ru.clevertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.clevertec.entity.Tag;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByName(String name);

    @Query(value = "select tag.id, tag.name\n" +
                   "from tag\n" +
                   "where tag.id = (select xd.tag_id\n" +
                   "                from (select gift_certificate_tag.tag_id\n" +
                   "                      from orders\n" +
                   "                               join (SELECT u.id, sum(orders.total_price) total\n" +
                   "                                     from orders\n" +
                   "                                              join users u on u.id = orders.user_id\n" +
                   "                                     group by u.id\n" +
                   "                                     order by total desc\n" +
                   "                                     limit 1) max_price_user\n" +
                   "                                    on max_price_user.id = orders.user_id\n" +
                   "                               join gift_certificate_tag\n" +
                   "                                    on orders.certificate_id = gift_certificate_tag.gift_certificate_id) xd\n" +
                   "                group by tag_id\n" +
                   "                order by count(tag_id) desc\n" +
                   "                limit 1);", nativeQuery = true)
    Optional<Tag> findTheMostWidelyTag();
}
