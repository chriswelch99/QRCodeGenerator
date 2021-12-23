package org.weewelchie.qr.persistence;

import java.util.List;

public interface IQRCodeDataService {

    QRCodeData create(QRCodeData data);

    List<QRCodeData> findAll();

    QRCodeData findByData(String data);
}
