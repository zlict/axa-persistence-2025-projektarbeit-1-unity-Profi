package ch.axa.punchclock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import ch.axa.punchclock.models.*;
import ch.axa.punchclock.repositories.*;

import java.time.LocalDate;
import java.util.*;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private VertragRepository vertragRepository;
    @Autowired
    private PartnerRepository partnerRepository;
    @Autowired
    private ClaimHandlerRepository claimHandlerRepository;
    @Autowired
    private ClaimRepository claimRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Kunden anlegen
        Customer customer1 = new Customer();
        customer1.setFirstName("Max");
        customer1.setLastName("Mustermann");
        customer1.setDateOfBirth(LocalDate.of(1990, 1, 1));
        customer1.setAdress("Musterstrasse 1, 8000 Zürich");
        customer1.setEmail("max@mustermann.ch");

        Customer customer2 = new Customer();
        customer2.setFirstName("Erika");
        customer2.setLastName("Musterfrau");
        customer2.setDateOfBirth(LocalDate.of(1985, 6, 15));
        customer2.setAdress("Beispielweg 2, 4000 Basel");
        customer2.setEmail("erika@musterfrau.ch");

        customerRepository.saveAll(Arrays.asList(customer1, customer2));

        // Partner anlegen
        Partner partner1 = new Partner();
        partner1.setName("Autohaus AG");
        partner1.setPartnerType("Werkstatt");
        partner1.setAddress("Industriestrasse 10, 8600 Dübendorf");
        partner1.setPhone("044 123 45 67");

        Partner partner2 = new Partner();
        partner2.setName("Maler GmbH");
        partner2.setPartnerType("Handwerker");
        partner2.setAddress("Handwerkerweg 5, 9000 St. Gallen");
        partner2.setPhone("071 987 65 43");

        partnerRepository.saveAll(Arrays.asList(partner1, partner2));

        // ClaimHandler anlegen
        ClaimHandler handler1 = new ClaimHandler();
        handler1.setEmployeeNumber("AXA1001");
        handler1.setFirstName("Hans");
        handler1.setLastName("Meier");
        handler1.setDepartment("Schaden");

        ClaimHandler handler2 = new ClaimHandler();
        handler2.setEmployeeNumber("AXA1002");
        handler2.setFirstName("Petra");
        handler2.setLastName("Schmidt");
        handler2.setDepartment("Kundenservice");

        claimHandlerRepository.saveAll(Arrays.asList(handler1, handler2));

        // Verträge anlegen
        Vertrag vertrag1 = new Vertrag();
        vertrag1.setPolicyNumber("POL123456");
        vertrag1.setProductName("Haftpflichtversicherung");
        vertrag1.setStartDate(LocalDate.of(2023, 1, 1));
        vertrag1.setEndDate(LocalDate.of(2024, 1, 1));
        vertrag1.setCustomer(customer1);

        Vertrag vertrag2 = new Vertrag();
        vertrag2.setPolicyNumber("POL654321");
        vertrag2.setProductName("Hausratversicherung");
        vertrag2.setStartDate(LocalDate.of(2022, 6, 1));
        vertrag2.setEndDate(LocalDate.of(2025, 6, 1));
        vertrag2.setCustomer(customer2);

        vertragRepository.saveAll(Arrays.asList(vertrag1, vertrag2));

        // Claims anlegen
        Claim claim1 = new Claim();
        claim1.setDamageDate(LocalDate.of(2023, 3, 10));
        claim1.setReportDate(LocalDate.of(2023, 3, 11));
        claim1.setDescription("Wasserschaden im Keller");
        claim1.setStatus("offen");
        claim1.setEstimatedAmount(2500.0);
        claim1.setVertrag(vertrag1);
        claim1.setPartner(partner1);
        Set<ClaimHandler> handlers1 = new HashSet<>();
        handlers1.add(handler1);
        claim1.setClaimHandlers(handlers1);

        Claim claim2 = new Claim();
        claim2.setDamageDate(LocalDate.of(2024, 2, 5));
        claim2.setReportDate(LocalDate.of(2024, 2, 6));
        claim2.setDescription("Brandschaden in der Küche");
        claim2.setStatus("in Bearbeitung");
        claim2.setEstimatedAmount(8000.0);
        claim2.setVertrag(vertrag2);
        claim2.setPartner(partner2);
        Set<ClaimHandler> handlers2 = new HashSet<>();
        handlers2.add(handler2);
        claim2.setClaimHandlers(handlers2);

        Claim claim3 = new Claim();
        claim3.setDamageDate(LocalDate.of(2024, 5, 20));
        claim3.setReportDate(LocalDate.of(2024, 5, 21));
        claim3.setDescription("Einbruchdiebstahl");
        claim3.setStatus("abgeschlossen");
        claim3.setEstimatedAmount(12000.0);
        claim3.setVertrag(vertrag1);
        claim3.setPartner(partner2);
        Set<ClaimHandler> handlers3 = new HashSet<>();
        handlers3.add(handler1);
        handlers3.add(handler2);
        claim3.setClaimHandlers(handlers3);

        claimRepository.saveAll(Arrays.asList(claim1, claim2, claim3));
    }
}