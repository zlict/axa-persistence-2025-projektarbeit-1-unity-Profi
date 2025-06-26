package ch.axa.punchclock.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.axa.punchclock.models.Vertrag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Repository
public class EntryRepositoryImpl implements EntryRepositoryCustom {

    @Autowired
    private EntityManager em;


    @Override
    public List<Vertrag> findByDescriptionWithCriteria(String description) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Vertrag> cq = cb.createQuery(Vertrag.class);

        Root<Vertrag> user = cq.from(Vertrag.class);

        cq.where(cb.like(user.get("description"), "%" + description+"%"));
        
        return em.createQuery(cq).getResultList();
    }
 
}
