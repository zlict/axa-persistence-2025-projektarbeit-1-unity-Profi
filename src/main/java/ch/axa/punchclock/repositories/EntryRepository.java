package ch.axa.punchclock.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ch.axa.punchclock.models.Vertrag;

public interface EntryRepository extends CrudRepository<Vertrag, Long>, EntryRepositoryCustom {
    Iterable<Vertrag> findByCategory(@Param("categoryId") Long categoryId);
    Iterable<Vertrag> findByTags_Id(@Param("tagId")Long tagId);
    
    @Query(value = "SELECT * FROM entry e WHERE e.description LIKE %:searchDescription%", nativeQuery = true)
    Iterable<Vertrag> findByDescription(@Param("searchDescription") String searchDescription);
}