package org.weewelchie.qr.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class QRCodeDataService implements IQRCodeDataService{

    @Autowired
    QRCodeDataDAO dao;

    public QRCodeData create(QRCodeData qrCodeData)
    {
        return dao.save(qrCodeData);
    }

    public List<QRCodeData> findAll()
    {
        return dao.findAll();
    }

    @Override
    public QRCodeData findByData(String data) {
        return dao.findByContent(data);
    }

    public void delete(QRCodeData qr)
    {
        dao.delete(qr);
        dao.flush();
    }
}
