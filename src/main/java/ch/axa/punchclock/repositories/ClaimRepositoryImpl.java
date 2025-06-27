package ch.axa.punchclock.repositories;

import org.springframework.beans.factory.annotation.Autowired;

import ch.axa.punchclock.models.Claim;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class ClaimRepositoryImpl implements ClaimRepositoryCustom {
     @Autowired
    private EntityManager em;

    @Override
    public Iterable<Claim> findByEstimatedAmount(Double estimateedAmount) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Claim> cq = cb.createQuery(Claim.class);

        Root<Claim> claim = cq.from(Claim.class);

        cq.where(cb.greaterThanOrEqualTo(claim.get("estimatedAmount"), estimateedAmount));

        return em.createQuery(cq).getResultList();
    }

}
