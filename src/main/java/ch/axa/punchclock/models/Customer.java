package ch.axa.punchclock.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "customer")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private long id;

    @NotBlank(message = "Customer Name darf nicht leer sein")
    @Column(name = "firstName")
    private String firstName;

    @NotBlank(message = "Customer Name darf nicht leer sein")
    @Column(name = "lastName")
    private String lastName;

    @NotNull(message = "Customer Date darf nicht leer sein")
    @Column(name= "dateOfBirth")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Customer Adresse darf nicht leer sein")
    @Column(name= "adress")
    private String adress;

    @NotBlank(message = "Customer Email darf nicht leer sein")
    @Column(name= "email")
    private String email;

    @OneToMany(mappedBy = "customer")
    @JsonIgnoreProperties({"customer"})
    private Set<Vertrag> vertraege = new HashSet<>();
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Vertrag> getVertraege() {
        return vertraege;
    }

    public void setVertraege(Set<Vertrag> vertraege) {
        this.vertraege = vertraege;
    }


 
    

}
