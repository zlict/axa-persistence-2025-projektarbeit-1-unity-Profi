package ch.axa.punchclock.repositories;

import org.springframework.data.repository.CrudRepository;
import ch.axa.punchclock.models.Partner;

public interface PartnerRepository extends CrudRepository<Partner, Long> {
    
}
