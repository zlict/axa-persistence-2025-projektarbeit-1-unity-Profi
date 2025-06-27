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

        Customer customer3 = new Customer();
        customer3.setFirstName("Lukas");
        customer3.setLastName("Schneider");
        customer3.setDateOfBirth(LocalDate.of(1978, 3, 22));
        customer3.setAdress("Bahnhofstrasse 3, 3000 Bern");
        customer3.setEmail("lukas@schneider.ch");

        Customer customer4 = new Customer();
        customer4.setFirstName("Anna");
        customer4.setLastName("Huber");
        customer4.setDateOfBirth(LocalDate.of(1992, 11, 5));
        customer4.setAdress("Seestrasse 8, 6000 Luzern");
        customer4.setEmail("anna@huber.ch");

        Customer customer5 = new Customer();
        customer5.setFirstName("Peter");
        customer5.setLastName("Müller");
        customer5.setDateOfBirth(LocalDate.of(1980, 7, 19));
        customer5.setAdress("Hauptstrasse 12, 7000 Chur");
        customer5.setEmail("peter@mueller.ch");

        Customer customer6 = new Customer();
        customer6.setFirstName("Julia");
        customer6.setLastName("Keller");
        customer6.setDateOfBirth(LocalDate.of(1995, 2, 14));
        customer6.setAdress("Parkweg 4, 5000 Aarau");
        customer6.setEmail("julia@keller.ch");

        Customer customer7 = new Customer();
        customer7.setFirstName("Simon");
        customer7.setLastName("Baumgartner");
        customer7.setDateOfBirth(LocalDate.of(1988, 9, 30));
        customer7.setAdress("Waldweg 7, 3600 Thun");
        customer7.setEmail("simon@baumgartner.ch");

        Customer customer8 = new Customer();
        customer8.setFirstName("Nina");
        customer8.setLastName("Fischer");
        customer8.setDateOfBirth(LocalDate.of(1975, 5, 12));
        customer8.setAdress("Blumenstrasse 9, 8400 Winterthur");
        customer8.setEmail("nina@fischer.ch");

        Customer customer9 = new Customer();
        customer9.setFirstName("David");
        customer9.setLastName("Weber");
        customer9.setDateOfBirth(LocalDate.of(1983, 12, 2));
        customer9.setAdress("Feldweg 2, 9500 Wil");
        customer9.setEmail("david@weber.ch");

        Customer customer10 = new Customer();
        customer10.setFirstName("Laura");
        customer10.setLastName("Meier");
        customer10.setDateOfBirth(LocalDate.of(1998, 8, 25));
        customer10.setAdress("Ringstrasse 6, 4000 Basel");
        customer10.setEmail("laura@meier.ch");

        customerRepository.saveAll(Arrays.asList(customer1, customer2, customer3, customer4, customer5, customer6, customer7, customer8, customer9, customer10));

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

        Partner partner3 = new Partner();
        partner3.setName("Sanitär Müller");
        partner3.setPartnerType("Sanitär");
        partner3.setAddress("Wasserweg 1, 6000 Luzern");
        partner3.setPhone("041 222 33 44");

        Partner partner4 = new Partner();
        partner4.setName("Elektro AG");
        partner4.setPartnerType("Elektriker");
        partner4.setAddress("Stromstrasse 2, 8000 Zürich");
        partner4.setPhone("044 555 66 77");

        Partner partner5 = new Partner();
        partner5.setName("Tischlerei Holz");
        partner5.setPartnerType("Tischler");
        partner5.setAddress("Holzweg 3, 7000 Chur");
        partner5.setPhone("081 333 44 55");

        Partner partner6 = new Partner();
        partner6.setName("Gartenbau Grün");
        partner6.setPartnerType("Gartenbau");
        partner6.setAddress("Gartenstrasse 4, 5000 Aarau");
        partner6.setPhone("062 444 55 66");

        Partner partner7 = new Partner();
        partner7.setName("IT Solutions");
        partner7.setPartnerType("IT");
        partner7.setAddress("Techpark 5, 4000 Basel");
        partner7.setPhone("061 555 66 77");

        Partner partner8 = new Partner();
        partner8.setName("Reinigungsservice Blitz");
        partner8.setPartnerType("Reinigung");
        partner8.setAddress("Sauberweg 6, 8400 Winterthur");
        partner8.setPhone("052 666 77 88");

        Partner partner9 = new Partner();
        partner9.setName("Schlosserei Stahl");
        partner9.setPartnerType("Schlosser");
        partner9.setAddress("Metallstrasse 7, 9500 Wil");
        partner9.setPhone("071 777 88 99");

        Partner partner10 = new Partner();
        partner10.setName("Dachdecker Meister");
        partner10.setPartnerType("Dachdecker");
        partner10.setAddress("Dachweg 8, 3600 Thun");
        partner10.setPhone("033 888 99 00");

        partnerRepository.saveAll(Arrays.asList(partner1, partner2, partner3, partner4, partner5, partner6, partner7, partner8, partner9, partner10));

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

        ClaimHandler handler3 = new ClaimHandler();
        handler3.setEmployeeNumber("AXA1003");
        handler3.setFirstName("Martin");
        handler3.setLastName("Graf");
        handler3.setDepartment("Technik");

        ClaimHandler handler4 = new ClaimHandler();
        handler4.setEmployeeNumber("AXA1004");
        handler4.setFirstName("Sabine");
        handler4.setLastName("Kunz");
        handler4.setDepartment("Recht");

        ClaimHandler handler5 = new ClaimHandler();
        handler5.setEmployeeNumber("AXA1005");
        handler5.setFirstName("Thomas");
        handler5.setLastName("Bachmann");
        handler5.setDepartment("Finanzen");

        ClaimHandler handler6 = new ClaimHandler();
        handler6.setEmployeeNumber("AXA1006");
        handler6.setFirstName("Daniela");
        handler6.setLastName("Schwarz");
        handler6.setDepartment("Schaden");

        ClaimHandler handler7 = new ClaimHandler();
        handler7.setEmployeeNumber("AXA1007");
        handler7.setFirstName("Stefan");
        handler7.setLastName("Frei");
        handler7.setDepartment("Kundenservice");

        ClaimHandler handler8 = new ClaimHandler();
        handler8.setEmployeeNumber("AXA1008");
        handler8.setFirstName("Monika");
        handler8.setLastName("Vogel");
        handler8.setDepartment("Technik");

        ClaimHandler handler9 = new ClaimHandler();
        handler9.setEmployeeNumber("AXA1009");
        handler9.setFirstName("Reto");
        handler9.setLastName("Baum");
        handler9.setDepartment("Recht");

        ClaimHandler handler10 = new ClaimHandler();
        handler10.setEmployeeNumber("AXA1010");
        handler10.setFirstName("Claudia");
        handler10.setLastName("Keller");
        handler10.setDepartment("Finanzen");

        claimHandlerRepository.saveAll(Arrays.asList(handler1, handler2, handler3, handler4, handler5, handler6, handler7, handler8, handler9, handler10));

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

        Vertrag vertrag3 = new Vertrag();
        vertrag3.setPolicyNumber("POL111111");
        vertrag3.setProductName("Reiseversicherung");
        vertrag3.setStartDate(LocalDate.of(2021, 4, 1));
        vertrag3.setEndDate(LocalDate.of(2024, 4, 1));
        vertrag3.setCustomer(customer3);

        Vertrag vertrag4 = new Vertrag();
        vertrag4.setPolicyNumber("POL222222");
        vertrag4.setProductName("Lebensversicherung");
        vertrag4.setStartDate(LocalDate.of(2020, 9, 1));
        vertrag4.setEndDate(LocalDate.of(2030, 9, 1));
        vertrag4.setCustomer(customer4);

        Vertrag vertrag5 = new Vertrag();
        vertrag5.setPolicyNumber("POL333333");
        vertrag5.setProductName("Unfallversicherung");
        vertrag5.setStartDate(LocalDate.of(2022, 2, 1));
        vertrag5.setEndDate(LocalDate.of(2027, 2, 1));
        vertrag5.setCustomer(customer5);

        Vertrag vertrag6 = new Vertrag();
        vertrag6.setPolicyNumber("POL444444");
        vertrag6.setProductName("Kfz-Versicherung");
        vertrag6.setStartDate(LocalDate.of(2023, 5, 1));
        vertrag6.setEndDate(LocalDate.of(2026, 5, 1));
        vertrag6.setCustomer(customer6);

        Vertrag vertrag7 = new Vertrag();
        vertrag7.setPolicyNumber("POL555555");
        vertrag7.setProductName("Rechtsschutzversicherung");
        vertrag7.setStartDate(LocalDate.of(2021, 7, 1));
        vertrag7.setEndDate(LocalDate.of(2024, 7, 1));
        vertrag7.setCustomer(customer7);

        Vertrag vertrag8 = new Vertrag();
        vertrag8.setPolicyNumber("POL666666");
        vertrag8.setProductName("Gebäudeversicherung");
        vertrag8.setStartDate(LocalDate.of(2022, 10, 1));
        vertrag8.setEndDate(LocalDate.of(2027, 10, 1));
        vertrag8.setCustomer(customer8);

        Vertrag vertrag9 = new Vertrag();
        vertrag9.setPolicyNumber("POL777777");
        vertrag9.setProductName("Tierversicherung");
        vertrag9.setStartDate(LocalDate.of(2023, 3, 1));
        vertrag9.setEndDate(LocalDate.of(2028, 3, 1));
        vertrag9.setCustomer(customer9);

        Vertrag vertrag10 = new Vertrag();
        vertrag10.setPolicyNumber("POL888888");
        vertrag10.setProductName("Zahnzusatzversicherung");
        vertrag10.setStartDate(LocalDate.of(2024, 1, 1));
        vertrag10.setEndDate(LocalDate.of(2029, 1, 1));
        vertrag10.setCustomer(customer10);

        vertragRepository.saveAll(Arrays.asList(vertrag1, vertrag2, vertrag3, vertrag4, vertrag5, vertrag6, vertrag7, vertrag8, vertrag9, vertrag10));

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

        Claim claim4 = new Claim();
        claim4.setDamageDate(LocalDate.of(2023, 7, 15));
        claim4.setReportDate(LocalDate.of(2023, 7, 16));
        claim4.setDescription("Sturmschaden am Dach");
        claim4.setStatus("offen");
        claim4.setEstimatedAmount(5000.0);
        claim4.setVertrag(vertrag3);
        claim4.setPartner(partner3);
        Set<ClaimHandler> handlers4 = new HashSet<>();
        handlers4.add(handler3);
        claim4.setClaimHandlers(handlers4);

        Claim claim5 = new Claim();
        claim5.setDamageDate(LocalDate.of(2022, 11, 2));
        claim5.setReportDate(LocalDate.of(2022, 11, 3));
        claim5.setDescription("Wasserschaden im Bad");
        claim5.setStatus("abgeschlossen");
        claim5.setEstimatedAmount(3200.0);
        claim5.setVertrag(vertrag4);
        claim5.setPartner(partner4);
        Set<ClaimHandler> handlers5 = new HashSet<>();
        handlers5.add(handler4);
        claim5.setClaimHandlers(handlers5);

        Claim claim6 = new Claim();
        claim6.setDamageDate(LocalDate.of(2024, 4, 10));
        claim6.setReportDate(LocalDate.of(2024, 4, 11));
        claim6.setDescription("Glasschaden am Fenster");
        claim6.setStatus("offen");
        claim6.setEstimatedAmount(900.0);
        claim6.setVertrag(vertrag5);
        claim6.setPartner(partner5);
        Set<ClaimHandler> handlers6 = new HashSet<>();
        handlers6.add(handler5);
        claim6.setClaimHandlers(handlers6);

        Claim claim7 = new Claim();
        claim7.setDamageDate(LocalDate.of(2023, 9, 8));
        claim7.setReportDate(LocalDate.of(2023, 9, 9));
        claim7.setDescription("Kfz-Unfall");
        claim7.setStatus("in Bearbeitung");
        claim7.setEstimatedAmount(4500.0);
        claim7.setVertrag(vertrag6);
        claim7.setPartner(partner6);
        Set<ClaimHandler> handlers7 = new HashSet<>();
        handlers7.add(handler6);
        claim7.setClaimHandlers(handlers7);

        Claim claim8 = new Claim();
        claim8.setDamageDate(LocalDate.of(2022, 8, 12));
        claim8.setReportDate(LocalDate.of(2022, 8, 13));
        claim8.setDescription("Rechtsstreit");
        claim8.setStatus("offen");
        claim8.setEstimatedAmount(2000.0);
        claim8.setVertrag(vertrag7);
        claim8.setPartner(partner7);
        Set<ClaimHandler> handlers8 = new HashSet<>();
        handlers8.add(handler7);
        claim8.setClaimHandlers(handlers8);

        Claim claim9 = new Claim();
        claim9.setDamageDate(LocalDate.of(2023, 12, 1));
        claim9.setReportDate(LocalDate.of(2023, 12, 2));
        claim9.setDescription("Gebäudeschaden durch Hagel");
        claim9.setStatus("abgeschlossen");
        claim9.setEstimatedAmount(7000.0);
        claim9.setVertrag(vertrag8);
        claim9.setPartner(partner8);
        Set<ClaimHandler> handlers9 = new HashSet<>();
        handlers9.add(handler8);
        claim9.setClaimHandlers(handlers9);

        Claim claim10 = new Claim();
        claim10.setDamageDate(LocalDate.of(2024, 6, 18));
        claim10.setReportDate(LocalDate.of(2024, 6, 19));
        claim10.setDescription("Tierbiss");
        claim10.setStatus("offen");
        claim10.setEstimatedAmount(600.0);
        claim10.setVertrag(vertrag9);
        claim10.setPartner(partner9);
        Set<ClaimHandler> handlers10 = new HashSet<>();
        handlers10.add(handler9);
        claim10.setClaimHandlers(handlers10);

        Claim claim11 = new Claim();
        claim11.setDamageDate(LocalDate.of(2024, 2, 28));
        claim11.setReportDate(LocalDate.of(2024, 3, 1));
        claim11.setDescription("Zahnschaden");
        claim11.setStatus("in Bearbeitung");
        claim11.setEstimatedAmount(1100.0);
        claim11.setVertrag(vertrag10);
        claim11.setPartner(partner10);
        Set<ClaimHandler> handlers11 = new HashSet<>();
        handlers11.add(handler10);
        claim11.setClaimHandlers(handlers11);

        Claim claim12 = new Claim();
        claim12.setDamageDate(LocalDate.of(2023, 5, 5));
        claim12.setReportDate(LocalDate.of(2023, 5, 6));
        claim12.setDescription("Fahrraddiebstahl");
        claim12.setStatus("offen");
        claim12.setEstimatedAmount(800.0);
        claim12.setVertrag(vertrag1);
        claim12.setPartner(partner3);
        Set<ClaimHandler> handlers12 = new HashSet<>();
        handlers12.add(handler2);
        handlers12.add(handler3);
        claim12.setClaimHandlers(handlers12);

        Claim claim13 = new Claim();
        claim13.setDamageDate(LocalDate.of(2022, 10, 10));
        claim13.setReportDate(LocalDate.of(2022, 10, 11));
        claim13.setDescription("Brandschaden Garage");
        claim13.setStatus("abgeschlossen");
        claim13.setEstimatedAmount(9500.0);
        claim13.setVertrag(vertrag2);
        claim13.setPartner(partner4);
        Set<ClaimHandler> handlers13 = new HashSet<>();
        handlers13.add(handler4);
        handlers13.add(handler5);
        claim13.setClaimHandlers(handlers13);

        Claim claim14 = new Claim();
        claim14.setDamageDate(LocalDate.of(2024, 1, 15));
        claim14.setReportDate(LocalDate.of(2024, 1, 16));
        claim14.setDescription("Wasserschaden Küche");
        claim14.setStatus("offen");
        claim14.setEstimatedAmount(2100.0);
        claim14.setVertrag(vertrag3);
        claim14.setPartner(partner5);
        Set<ClaimHandler> handlers14 = new HashSet<>();
        handlers14.add(handler6);
        claim14.setClaimHandlers(handlers14);

        Claim claim15 = new Claim();
        claim15.setDamageDate(LocalDate.of(2023, 8, 20));
        claim15.setReportDate(LocalDate.of(2023, 8, 21));
        claim15.setDescription("Sturmschaden Garten");
        claim15.setStatus("abgeschlossen");
        claim15.setEstimatedAmount(3300.0);
        claim15.setVertrag(vertrag4);
        claim15.setPartner(partner6);
        Set<ClaimHandler> handlers15 = new HashSet<>();
        handlers15.add(handler7);
        handlers15.add(handler8);
        claim15.setClaimHandlers(handlers15);

        claimRepository.saveAll(Arrays.asList(
            claim1, claim2, claim3, claim4, claim5, claim6, claim7, claim8, claim9, claim10,
            claim11, claim12, claim13, claim14, claim15
        ));
    }
}