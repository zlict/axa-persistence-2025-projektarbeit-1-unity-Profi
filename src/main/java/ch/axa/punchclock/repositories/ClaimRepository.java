package ch.axa.punchclock.repositories;

import org.springframework.data.repository.CrudRepository;
import ch.axa.punchclock.models.Claim;

public interface ClaimRepository extends CrudRepository<Claim, Long>, ClaimRepositoryCustom  {
    
    Iterable<Claim> findByVertrag_Customer_Id(Long customerId);
    Iterable<Claim> findByVertrag_Customer_firstName(String firstName);

}
