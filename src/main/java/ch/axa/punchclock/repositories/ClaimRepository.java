package ch.axa.punchclock.repositories;

import org.springframework.data.repository.CrudRepository;
import ch.axa.punchclock.models.Claim;

public interface ClaimRepository extends CrudRepository<Claim, Long> {
    
}
