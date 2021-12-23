package org.weewelchie.qr.codegen;

import net.glxn.qrgen.javase.QRCode;
import org.weewelchie.qr.persistence.QRCodeData;

import java.awt.image.BufferedImage;
import java.util.List;

public interface QRCodes {

    //public List<String> generateCodeData(String data, Integer numCodes);
    public List<QRCodeData> generateCodeData(String data, Integer numCodes);

    public List<BufferedImage> generateQRCodeRange(String seedData, Integer numCodes);

    public String getQRCodeData(QRCode qrCode);

    public List<QRCodeData> getAllCodes();
}

