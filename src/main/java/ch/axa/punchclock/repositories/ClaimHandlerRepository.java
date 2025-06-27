package ch.axa.punchclock.repositories;

import org.springframework.data.repository.CrudRepository;
import ch.axa.punchclock.models.ClaimHandler;

public interface ClaimHandlerRepository extends CrudRepository<ClaimHandler, Long> {
    
}
