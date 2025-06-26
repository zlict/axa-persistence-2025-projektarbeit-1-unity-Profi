package ch.axa.punchclock.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "claimHandler")
public class ClaimHandler {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "claimHandler_id")
    private Long claimHandlerId;

    @NotBlank
    private String employeeNumber;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String department;


    @ManyToMany(mappedBy = "claimHandlers")
    private Set<Claim> claims = new HashSet<>();

    public ClaimHandler() {}

    public Long getClaimHandlerId() {
        return claimHandlerId;
    }


    public void setClaimHandlerId(Long claimHandlerId) {
        this.claimHandlerId = claimHandlerId;
    }


    public String getEmployeeNumber() {
        return employeeNumber;
    }


    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getDepartment() {
        return department;
    }


    public void setDepartment(String department) {
        this.department = department;
    }

    public Set<Claim> getClaims() {
        return claims;
    }

    public void setClaims(Set<Claim> claims) {
        this.claims = claims;
    }


   


  
    
}
