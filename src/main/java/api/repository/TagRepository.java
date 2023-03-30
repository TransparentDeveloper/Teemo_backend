package api.repository;

import api.domain.Chatroom;
import api.domain.Gender;
import api.domain.Member;
import api.domain.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagRepository {
    @PersistenceContext EntityManager em;

    public Long save(Tag tag){
        em.persist(tag);
        return tag.getId();
    }

    public Tag find(Long id) {
        return em.find(Tag.class, id);
    }

    public List<Tag> findAll(double latitude, double longitude, int age){
        return em.createQuery(
                "SELECT t FROM Tag t " +
                        "WHERE t.targetAgeLower <= :age " +
                        "AND t.targetAgeUpper >= :age " +
                        "AND ABS(t.latitude - :latitude) < 0.009" +
                        "AND ABS(t.longitude - :longitude) < 0.012",
                        Tag.class)
                .setParameter("age",age)
                .setParameter("age",age)
                .setParameter("latitude",latitude)
                .setParameter("longitude",longitude)
                .getResultList();
    }


}
