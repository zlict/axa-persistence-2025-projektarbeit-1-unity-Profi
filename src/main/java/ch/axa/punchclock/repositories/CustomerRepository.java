package ch.axa.punchclock.repositories;


import org.springframework.data.repository.CrudRepository;
import ch.axa.punchclock.models.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    
}
