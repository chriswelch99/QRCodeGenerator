package org.weewelchie.qr.test.jpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.weewelchie.qr.persistence.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = PersistenceConfig.class)
public class QrCodeDataDAOTest {

    private String data = "Test";
    @Autowired
    private QRCodeDataService service;

    @BeforeEach
    public void tearDown()
    {
        List<QRCodeData> qrCodeData = service.findAll();
        for (QRCodeData qr: qrCodeData)
        {
            service.delete(qr);
        }
    }

    @Test
    public void testCreateDefaultConstructor()
    {

        QRCodeData qrCodeData = new QRCodeData();
        qrCodeData.setContent(data);
        qrCodeData.setInUse(true);

        Assertions.assertEquals(data, qrCodeData.getContent());
        Assertions.assertEquals(true,qrCodeData.getInUse());

    }

    @Test
    public void testCreateInUse()
    {
        QRCodeData qrCodeData = new QRCodeData(data,true);

        Assertions.assertEquals(data, qrCodeData.getContent());
        Assertions.assertEquals(true,qrCodeData.getInUse());
    }

    @Test
    public void testCreateNotInUse()
    {
        QRCodeData qrCodeData = new QRCodeData(data,false);

        Assertions.assertEquals(data, qrCodeData.getContent());
        Assertions.assertEquals(false,qrCodeData.getInUse());
    }

    @Test
    public void createQRCodeData_in_db() {
        QRCodeData qrCodeData = service.create(new QRCodeData(data, false));
        Assertions.assertEquals(qrCodeData.getContent(), data);
        Assertions.assertEquals(qrCodeData.getInUse(),false);
    }

    @Test
    public final void when_Null_is_used_then_InvalidDataAccessApiUsageException() {
        InvalidDataAccessApiUsageException thrown = Assertions.assertThrows(InvalidDataAccessApiUsageException.class, () -> {
            service.create(null);
        }, "InvalidDataAccessApiUsageException was expected");

    }

    @Test
    public void testFindAll()
    {
        List<QRCodeData> qrCodes = new ArrayList<QRCodeData>();
        List<QRCodeData> qrCodesInDB = null;

        for (int i = 0; i< 10 ; i++)
        {
            qrCodes.add(service.create(new QRCodeData(data + i,false)));
        }

        qrCodesInDB = service.findAll();
        Assertions.assertEquals(qrCodesInDB.size(), 10);
    }

    @Test
    public void delete()
    {
        List<QRCodeData> qrCodes = new ArrayList<QRCodeData>();
        List<QRCodeData> qrCodesInDB = null;
        for (int i = 0; i< 10 ; i++)
        {
            qrCodes.add(service.create(new QRCodeData(data + i,false)));
        }

        qrCodesInDB = service.findAll();
        Assertions.assertEquals(qrCodesInDB.size(),10);

        service.delete(qrCodesInDB.get(0));
        qrCodesInDB = service.findAll();
        Assertions.assertEquals(qrCodesInDB.size(),9);

    }

    @Test
    public void findByData()
    {
        String data = "FindMe!";
        service.create(new QRCodeData(data,false));

        QRCodeData qr = service.findByData(data);
        Assertions.assertEquals(qr.getContent(),data);
    }
}

