package ch.axa.punchclock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ch.axa.punchclock.models.Partner;
import ch.axa.punchclock.repositories.PartnerRepository;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/partners")
@Tag(name = "Partner API", description = "API for managing partners")
public class APIPartnerController {

  @Autowired
  private PartnerRepository partnerRepository;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Create a new partner", description = "Creates a new partner and returns the created entity.")
  public Partner create(@RequestBody @Valid Partner partner) {
    return partnerRepository.save(partner);
  }

  @GetMapping
  @Operation(summary = "Get all partners", description = "Returns a list of all partners.")
  public Iterable<Partner> index() {
    return partnerRepository.findAll();
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get partner by ID", description = "Returns a partner by its ID.")
  public ResponseEntity<Partner> read(@Parameter(description = "ID of the partner to retrieve") @PathVariable Long id) {
    return ResponseEntity.of(partnerRepository.findById(id));
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update a partner", description = "Updates an existing partner by its ID.")
  public Partner update(@Parameter(description = "ID of the partner to update") @PathVariable Long id, @RequestBody @Valid Partner partner) {
    partner.setPartnerId(id);
    return partnerRepository.save(partner);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a partner", description = "Deletes a partner by its ID.")
  public ResponseEntity<Partner> delete(@Parameter(description = "ID of the partner to delete") @PathVariable Long id) {
    var partner = partnerRepository.findById(id);
    if(partner.isPresent()) {
      partnerRepository.delete(partner.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
