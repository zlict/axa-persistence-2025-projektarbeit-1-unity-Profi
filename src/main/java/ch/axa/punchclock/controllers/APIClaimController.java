package ch.axa.punchclock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ch.axa.punchclock.models.Claim;
import ch.axa.punchclock.repositories.ClaimRepository;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/claims")
@Tag(name = "Claim API", description = "API for managing claims")
public class APIClaimController {

  @Autowired
  private ClaimRepository claimRepository;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Create a new claim", description = "Creates a new claim and returns the created entity.")
  public Claim create(@RequestBody @Valid Claim claim) {
    return claimRepository.save(claim);
  }

  @GetMapping
  @Operation(summary = "Get all claims", description = "Returns a list of all claims.")
  public Iterable<Claim> index(@RequestParam(required = false) Double estimatedAmount, @RequestParam(required = false) Long customerId) {
     if (customerId != null) {
      return claimRepository.findByVertrag_Customer_Id(customerId);
    }
    if (estimatedAmount != null) {
      return claimRepository.findByEstimatedAmount(estimatedAmount);
    }
    return claimRepository.findAll();
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get claim by ID", description = "Returns a claim by its ID.")
  public ResponseEntity<Claim> read(@Parameter(description = "ID of the claim to retrieve") @PathVariable Long id) {
    return ResponseEntity.of(claimRepository.findById(id));
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update a claim", description = "Updates an existing claim by its ID.")
  public Claim update(@Parameter(description = "ID of the claim to update") @PathVariable Long id, @RequestBody @Valid Claim claim) {
    claim.setClaimId(id);
    return claimRepository.save(claim);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a claim", description = "Deletes a claim by its ID.")
  public ResponseEntity<Claim> delete(@Parameter(description = "ID of the claim to delete") @PathVariable Long id) {
    var claim = claimRepository.findById(id);
    if(claim.isPresent()) {
      claimRepository.delete(claim.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
