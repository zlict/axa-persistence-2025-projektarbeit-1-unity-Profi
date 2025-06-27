package ch.axa.punchclock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ch.axa.punchclock.models.Customer;
import ch.axa.punchclock.repositories.CustomerRepository;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/customers")
@Tag(name = "Customer API", description = "API for managing customers")
public class APICustomerController {

  @Autowired
  private CustomerRepository customerRepository;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Create a new customer", description = "Creates a new customer and returns the created entity.")
  public Customer create(@RequestBody @Valid Customer customer) {
    return customerRepository.save(customer);
  }

  @GetMapping
  @Operation(summary = "Get all customers", description = "Returns a list of all customers.")
  public Iterable<Customer> index() {
    return customerRepository.findAll();
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get customer by ID", description = "Returns a customer by its ID.")
  public ResponseEntity<Customer> read(@Parameter(description = "ID of the customer to retrieve") @PathVariable Long id) {
    return ResponseEntity.of(customerRepository.findById(id));
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update a customer", description = "Updates an existing customer by its ID.")
  public Customer update(@Parameter(description = "ID of the customer to update") @PathVariable Long id, @RequestBody @Valid Customer customer) {
    customer.setId(id);
    return customerRepository.save(customer);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a customer", description = "Deletes a customer by its ID.")
  public ResponseEntity<Customer> delete(@Parameter(description = "ID of the customer to delete") @PathVariable Long id) {
    var customer = customerRepository.findById(id);
    if(customer.isPresent()) {
      customerRepository.delete(customer.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
