package org.weewelchie.qr.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Repository
public interface ScoutDAO extends JpaRepository<Scout,Long> {

    @Query("SELECT s FROM Scout s WHERE LOWER(s.firstName) = LOWER(:firstName) AND LOWER(s.lastName) = LOWER(:lastName)")
    List<Scout> findByName(@Param("firstName") String firstName, @Param("lastName") String lastName) throws EntityNotFoundException;

}
