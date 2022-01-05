package org.weewelchie.qr.test.jpa;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.weewelchie.qr.persistence.IScoutService;
import org.weewelchie.qr.persistence.PersistenceConfig;
import org.weewelchie.qr.persistence.Scout;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = PersistenceConfig.class)
public class ScoutServiceTest {

    @Autowired
    private IScoutService service;

    private String firstName = "Firstname";
    private String lastName = "LastName";
    private Date dob = new Date("01/01/2000");
    private String troop = "1st Troop";


    @Before
    public void tearDown()
    {
        List<Scout> scouts = service.findAll();
        for (Scout sc: scouts)
        {
            service.delete(sc);
        }
    }

    @Test
    public final void when_Null_is_used_then_InvalidDataAccessApiUsageException() {
        InvalidDataAccessApiUsageException thrown = Assertions.assertThrows(InvalidDataAccessApiUsageException.class, () -> {
            service.create(null);
        }, "InvalidDataAccessApiUsageException was expected");
    }

    @Test
    public void testCreate()
    {
        String firstName = "FirstNameCreated";
        Scout sc = new Scout(firstName,lastName,dob,troop);
        Scout scTest = service.create(sc);
        Assertions.assertEquals(firstName ,scTest.getFirstName());
        Assertions.assertEquals(lastName ,scTest.getLastName());
        Assertions.assertEquals(dob ,scTest.getDob());
        Assertions.assertEquals(troop ,scTest.getTroop());
        Assertions.assertNotNull(scTest.getId());
    }

    @Test
    public void TestDelete()
    {
        String firstName = "DeleteMe";
        Scout sc = new Scout(firstName,lastName,dob,troop);

        Scout scTest = service.create(sc);
        Assertions.assertNotNull(scTest);

        service.delete(scTest);

        List<Scout> scouts = service.findByName(firstName,lastName);
        Assertions.assertTrue(scouts.isEmpty() );
    }

    @Test
    public void testFindByName()
    {
        Scout sc = new Scout(firstName,lastName,dob,troop);
        service.create(sc);

        List<Scout> scTest = service.findByName(firstName,lastName);
        Assertions.assertEquals(scTest.get(0).getFirstName(),firstName);
        Assertions.assertEquals(scTest.get(0).getLastName(),lastName);
    }

    @Test
    public void findAll()
    {
        List<Scout> scouts = service.findAll();
        int origSize = scouts.size();
        for(int i = 0 ; i < 100 ; i++)
        {
            Scout sc = new Scout(firstName+ i,lastName,dob,troop);
            service.create(sc);
        }

        scouts = service.findAll();
        Assertions.assertEquals(100, scouts.size() - origSize);
    }

    @Test
    public void testCreateDuplicate()
    {

        DataIntegrityViolationException thrown = Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            service.create(new Scout(firstName,lastName,dob,troop));
            service.create(new Scout(firstName,lastName,dob,troop));
        }, "DataIntegrityViolationException was expected");

    }
}
