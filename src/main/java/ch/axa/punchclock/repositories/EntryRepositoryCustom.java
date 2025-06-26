package ch.axa.punchclock.repositories;

import java.util.List;

import ch.axa.punchclock.models.Vertrag;

public interface EntryRepositoryCustom {
        List<Vertrag> findByDescriptionWithCriteria(String description);
}