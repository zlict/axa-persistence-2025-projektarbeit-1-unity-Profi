package ch.axa.punchclock.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ch.axa.punchclock.models.Vertrag;
import jakarta.inject.Inject;

@SpringBootTest
public class EntryRepositoryTest {
    @Inject
    private EntryRepository entryRepository;

@Test
  public void testIfEntryCanBeSaved1() {
      Vertrag entry = new Vertrag();
      entry.setDescription("Awesome");
      entry.setCheckIn(LocalDateTime.now());
      entry.setDuration(60 * 60 * 2);

      entryRepository.save(entry);
    
      assertEquals(entry.getDescription(), entryRepository.findById(entry.getId()).get().getDescription());
  }

  @Test
  public void testIfEntryCanBeSaved2() {
      Vertrag entry = new Vertrag();
      entry.setDescription("Awesome");
      entry.setCheckIn(LocalDateTime.now());
      entry.setDuration(60 * 60 * 2);

      entryRepository.save(entry);
    
      assertEquals(entry.getDuration(), entryRepository.findById(entry.getId()).get().getDuration());
  }

  @Test
  public void testIfEntryCanBeSaved3() {
      Vertrag entry = new Vertrag();
      entry.setDescription("Awesome");
      entry.setCheckIn(LocalDateTime.now());
      entry.setDuration(60 * 60 * 2);

      entryRepository.save(entry);
    
      assertSame(entry.getCheckIn(), entryRepository.findById(entry.getId()).get().getCheckIn());
  }

  @Test
  public void testIfEntryCanBeSaved4() {
      Vertrag entry = new Vertrag();
      entry.setDescription("Awesome");
      entry.setCheckIn(LocalDateTime.now());
      entry.setDuration(60 * 60 * 2);

      entryRepository.save(entry);
    
      assertEquals(entry.getId(), entryRepository.findById(entry.getId()).get().getId());
  }

  @Test
  public void testIfEntryCanBeSaved5() {
      Vertrag entry = new Vertrag();
      entry.setDescription("Awesome");
      entry.setCheckIn(LocalDateTime.now());
      entry.setDuration(60 * 60 * 2);

      entryRepository.save(entry);

      assertNotNull(entryRepository.findById(entry.getId()).get());
      
  }
  @Test
    public void testIfEntryCanBeUpdated() {
        Vertrag entry = new Vertrag();
        entry.setDescription("Initial");
        entry.setCheckIn(LocalDateTime.now());
        entry.setDuration(3600);
        entryRepository.save(entry);

        entry.setDescription("Updated");
        entryRepository.save(entry);

        Vertrag updated = entryRepository.findById(entry.getId()).get();
        assertEquals("Updated", updated.getDescription());
    }

    @Test
    public void testIfEntryCanBeListed() {
        Vertrag entry1 = new Vertrag();
        entry1.setDescription("First");
        entry1.setCheckIn(LocalDateTime.now());
        entry1.setDuration(3600);

        Vertrag entry2 = new Vertrag();
        entry2.setDescription("Second");
        entry2.setCheckIn(LocalDateTime.now());
        entry2.setDuration(7200);

        entryRepository.save(entry1);
        entryRepository.save(entry2);

        List<Vertrag> result = new ArrayList<Vertrag>();
        entryRepository.findAll().forEach(result::add);
        assertTrue(result.size() >= 2);
    }

    @Test
    public void testIfEntryCanBeDeleted() {
        Vertrag entry = new Vertrag();
        entry.setDescription("To be deleted");
        entry.setCheckIn(LocalDateTime.now());
        entry.setDuration(1800);

        entryRepository.save(entry);
        Long id = entry.getId();

        entryRepository.delete(entry);

        assertTrue(entryRepository.findById(id).isEmpty());
    }
}
