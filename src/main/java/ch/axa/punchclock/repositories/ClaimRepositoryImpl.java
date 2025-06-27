package ch.axa.punchclock.repositories;

import org.springframework.beans.factory.annotation.Autowired;

import ch.axa.punchclock.models.Claim;
import ch.axa.punchclock.models.Customer;
import ch.axa.punchclock.models.Vertrag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
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

    @Override
public Iterable<Claim> findByFirstName(String firstName) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Claim> cq = cb.createQuery(Claim.class);

    Root<Claim> claim = cq.from(Claim.class);
    Join<Claim, Vertrag> vertrag = claim.join("vertrag");
    Join<Vertrag, Customer> customer = vertrag.join("customer");

    cq.where(cb.like(customer.get("firstName"), "%"+firstName+"%"));

    return em.createQuery(cq).getResultList();
}

}
