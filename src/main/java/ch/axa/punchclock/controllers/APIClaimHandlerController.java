package ch.axa.punchclock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ch.axa.punchclock.models.ClaimHandler;
import ch.axa.punchclock.repositories.ClaimHandlerRepository;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/claim-handlers")
@Tag(name = "ClaimHandler API", description = "API for managing claim handlers")
public class APIClaimHandlerController {

  @Autowired
  private ClaimHandlerRepository claimHandlerRepository;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Create a new claim handler", description = "Creates a new claim handler and returns the created entity.")
  public ClaimHandler create(@RequestBody @Valid ClaimHandler claimHandler) {
    return claimHandlerRepository.save(claimHandler);
  }

  @GetMapping
  @Operation(summary = "Get all claim handlers", description = "Returns a list of all claim handlers.")
  public Iterable<ClaimHandler> index() {
    return claimHandlerRepository.findAll();
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get claim handler by ID", description = "Returns a claim handler by its ID.")
  public ResponseEntity<ClaimHandler> read(@Parameter(description = "ID of the claim handler to retrieve") @PathVariable Long id) {
    return ResponseEntity.of(claimHandlerRepository.findById(id));
  }
  
  @PutMapping("/{id}")
  @Operation(summary = "Update a claim handler", description = "Updates an existing claim handler by its ID.")
  public ClaimHandler update(@Parameter(description = "ID of the claim handler to update") @PathVariable Long id, @RequestBody @Valid ClaimHandler claimHandler) {
    claimHandler.setClaimHandlerId(id);
    return claimHandlerRepository.save(claimHandler);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a claim handler", description = "Deletes a claim handler by its ID.")
  public ResponseEntity<ClaimHandler> delete(@Parameter(description = "ID of the claim handler to delete") @PathVariable Long id) {
    var claimHandler = claimHandlerRepository.findById(id);
    if(claimHandler.isPresent()) {
      claimHandlerRepository.delete(claimHandler.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
