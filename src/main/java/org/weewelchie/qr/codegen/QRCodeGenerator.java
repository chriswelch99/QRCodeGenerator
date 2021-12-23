package org.weewelchie.qr.codegen;

import net.glxn.qrgen.javase.QRCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.weewelchie.qr.persistence.QRCodeData;
import org.weewelchie.qr.persistence.QRCodeDataService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class QRCodeGenerator implements QRCodes{

    private static final Logger LOGGER = LoggerFactory.getLogger( QRCodeGenerator.class );

    @Autowired
    QRCodeDataService qrCodeDataService;

    //Generate QR Code data and hash
    @Override
    public List<QRCodeData> generateCodeData(String data, Integer numCodes) {
         //Generate QRCode content based on SeedData and numCodes
        List<QRCodeData> qrCodeDataList = new ArrayList<QRCodeData>();
        for (int i = 1 ; i <= numCodes;i++)
        {
            try {
                String str = data + "-" + Calendar.getInstance().getTimeInMillis() + "-" + i;
                QRCodeData qr = new QRCodeData(str,false);
                //Add to the DB here
                qrCodeDataService.create(qr);
                qr = qrCodeDataService.findByData(str);
                qrCodeDataList.add(qr);
            }
            catch (Exception exp)
            {
                LOGGER.error("Exception generating QRCode", exp.getMessage());
            }
        }
        return qrCodeDataList;
    }


    @Override
    public List<BufferedImage> generateQRCodeRange(String seedData, Integer numCodes) {
        //Generate QRCode content based on SeedData and numCodes
        List<BufferedImage> qrCodeData = new ArrayList<BufferedImage>();
        for (int i = 0 ; i < numCodes;i++)
        {
            try {
                qrCodeData.add(generateQRCodeImage(seedData + i));
            }
            catch (Exception exp)
            {
                LOGGER.error("Exception generating QRCode", exp.getMessage());
            }
        }
        return qrCodeData;
    }

    public static BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
        ByteArrayOutputStream stream = QRCode
                .from(barcodeText)
                .withSize(250, 250)
                .stream();
        ByteArrayInputStream bis = new ByteArrayInputStream(stream.toByteArray());

        return ImageIO.read(bis);
    }

    @Override
    public String getQRCodeData(QRCode qrCode) {

        return qrCode.toString();
    }

    @Override
    public List<QRCodeData> getAllCodes() {
        return qrCodeDataService.findAll();
    }
}
