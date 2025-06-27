package ch.axa.punchclock.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "partner")
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "partner_id")
    private Long partnerId;

    @NotBlank
    private String name;

    @NotBlank
    private String partnerType;
    
    private String address;

    private String phone;

    @OneToMany(mappedBy = "partner")
    @JsonIgnoreProperties({"partner", "vertrag", "claimHandlers"})
    private Set<Claim> claims = new HashSet<>();

   
    public Partner() {}


    public Long getPartnerId() {
        return partnerId;
    }


    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getPartnerType() {
        return partnerType;
    }


    public void setPartnerType(String partnerType) {
        this.partnerType = partnerType;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }


    public Set<Claim> getClaims() {
        return claims;
    }


    public void setClaims(Set<Claim> claims) {
        this.claims = claims;
    }
    
    
}
