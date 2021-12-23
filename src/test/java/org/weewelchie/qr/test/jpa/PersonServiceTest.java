package org.weewelchie.qr.test.jpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.weewelchie.qr.persistence.PersistenceConfig;
import org.weewelchie.qr.persistence.Person;
import org.weewelchie.qr.persistence.PersonService;

//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = PersistenceConfig.class)
public class PersonServiceTest {

    @Autowired
    private PersonService service;

   // @Test
    public final void whenInvalidEntityIsCreated_thenDataException() {
        DataIntegrityViolationException thrown = Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            service.create(new Person());
        }, "DataIntegrityViolationException was expected");

    }
}
