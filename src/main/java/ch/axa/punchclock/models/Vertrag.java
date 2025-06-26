package ch.axa.punchclock.models;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "vertrag")
public class Vertrag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vertrag_id")
    private Long id;

    @NotBlank(message = "Policenummer darf nicht leer sein")
    @Column(name = "policy_number")
    private String policyNumber;

    @NotBlank(message = "Produktname darf nicht leer sein")
    @Column(name = "product_name")
    private String productName;

    @NotNull(message = "Startdatum darf nicht leer sein")
    @Column(name = "start_date")
    private LocalDate startDate;

    @NotNull(message = "Enddatum darf nicht leer sein")
    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = true)
    private Customer customer;

    public Vertrag() {}

    public Vertrag(String policyNumber, String productName, LocalDate startDate, LocalDate endDate, Customer customer) {
        this.policyNumber = policyNumber;
        this.productName = productName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customer = customer;
    }

    public Long getVertragId() {
        return id;
    }

    public void setVertragId(Long id) {
        this.id = id;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
