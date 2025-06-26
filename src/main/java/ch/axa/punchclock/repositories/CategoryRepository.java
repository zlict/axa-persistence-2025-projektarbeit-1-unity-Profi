package ch.axa.punchclock.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ch.axa.punchclock.models.Customer;

public interface CategoryRepository extends CrudRepository<Customer, Long> {
    

}
