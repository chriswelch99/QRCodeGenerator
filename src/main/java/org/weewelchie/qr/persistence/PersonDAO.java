package org.weewelchie.qr.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonDAO extends JpaRepository<Person,Long> {

    @Query("SELECT p FROM Person p WHERE LOWER(p.firstName) = LOWER(:firstName) AND LOWER(p.lastName) = LOWER(:lastName")
    default Person retrieveByName(@Param("firstName") String firstName, @Param("lastName") String lastName) {
        return null;
    }

}
