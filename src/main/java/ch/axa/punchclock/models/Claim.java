package ch.axa.punchclock.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "claim")
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "claim_id")
    private Long claimId;

    @NotNull
    @Column(name = "damage_date")
    private LocalDate damageDate;

    @NotNull
    @Column(name = "report_date")
    private LocalDate reportDate;

    @Column(length = 5000)
    private String description;

    @Column
    private String status;

    @Column(name = "estimated_amount")
    private Double estimatedAmount;

    @ManyToOne
    @JoinColumn(name = "vertrag_id", nullable = false)
    @JsonIgnoreProperties({"vertraege"})
    private Vertrag vertrag;

    @ManyToMany
    @JoinTable(
        name = "claim_claimHandler",
        joinColumns = @JoinColumn(name = "claim_id"),
        inverseJoinColumns = @JoinColumn(name = "claimHandler_id"))
    @JsonIgnoreProperties({"claims"})
    private Set<ClaimHandler> claimHandlers;

    @ManyToOne
    @JoinColumn(name = "partner_id")
    @JsonIgnoreProperties({"claims"})
    private Partner partner;

    public Claim() {}

    public Long getClaimId() {
        return claimId;
    }

    public void setClaimId(Long claimId) {
        this.claimId = claimId;
    }

    public LocalDate getDamageDate() {
        return damageDate;
    }

    public void setDamageDate(LocalDate damageDate) {
        this.damageDate = damageDate;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getEstimatedAmount() {
        return estimatedAmount;
    }

    public void setEstimatedAmount(Double estimatedAmount) {
        this.estimatedAmount = estimatedAmount;
    }

    public Vertrag getVertrag() {
        return vertrag;
    }

    public void setVertrag(Vertrag vertrag) {
        this.vertrag = vertrag;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public Set<ClaimHandler> getClaimHandlers() {
        return claimHandlers;
    }

    public void setClaimHandlers(Set<ClaimHandler> claimHandler) {
        this.claimHandlers = claimHandler;
    }
}
