package ch.axa.punchclock.repositories;

import ch.axa.punchclock.models.Claim;

public interface ClaimRepositoryCustom {

    
    Iterable<Claim> findByEstimatedAmount(Double estimatedAmount);
    
}
