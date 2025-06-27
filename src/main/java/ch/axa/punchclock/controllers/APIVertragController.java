package ch.axa.punchclock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ch.axa.punchclock.models.Vertrag;
import ch.axa.punchclock.repositories.VertragRepository;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/vertraege")
@Tag(name = "Vertrag API", description = "API for managing contracts")
public class APIVertragController {

  @Autowired
  private VertragRepository vertragRepository;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Create a new contract", description = "Creates a new contract and returns the created entity.")
  public Vertrag create(@RequestBody @Valid Vertrag vertrag) {
    return vertragRepository.save(vertrag);
  }

  @GetMapping
  @Operation(summary = "Get all contracts", description = "Returns a list of all contracts.")
  public Iterable<Vertrag> index() {
    return vertragRepository.findAll();
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get contract by ID", description = "Returns a contract by its ID.")
  public ResponseEntity<Vertrag> read(@Parameter(description = "ID of the contract to retrieve") @PathVariable Long id) {
    return ResponseEntity.of(vertragRepository.findById(id));
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update a contract", description = "Updates an existing contract by its ID.")
  public Vertrag update(@Parameter(description = "ID of the contract to update") @PathVariable Long id, @RequestBody @Valid Vertrag vertrag) {
    vertrag.setVertragId(id);
    return vertragRepository.save(vertrag);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a contract", description = "Deletes a contract by its ID.")
  public ResponseEntity<Vertrag> delete(@Parameter(description = "ID of the contract to delete") @PathVariable Long id) {
    var vertrag = vertragRepository.findById(id);
    if(vertrag.isPresent()) {
      vertragRepository.delete(vertrag.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
