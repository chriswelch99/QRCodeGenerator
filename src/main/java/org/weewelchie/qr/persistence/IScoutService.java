package org.weewelchie.qr.persistence;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface IScoutService {

    Scout create(Scout data);

    List<Scout> findAll();

    List<Scout> findByName(String firstName, String lastName);

    void delete(Scout p);

    Scout findByID(Long id) throws EntityNotFoundException;

    Scout update(Scout scout);
}
