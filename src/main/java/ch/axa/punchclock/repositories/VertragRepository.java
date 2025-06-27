package ch.axa.punchclock.repositories;

import org.springframework.data.repository.CrudRepository;
import ch.axa.punchclock.models.Vertrag;

public interface VertragRepository extends CrudRepository<Vertrag, Long> {
    
}
