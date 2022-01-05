package org.weewelchie.qr.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ScoutService implements IScoutService {

    @Autowired
    ScoutDAO dao;

    @Override
    public Scout create(Scout data) {
       return dao.save(data);
    }

    @Override
    public List<Scout> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Scout> findByName(String firstName, String lastName) throws EntityNotFoundException{
        return dao.findByName(firstName,lastName);
    }

    @Override
    public void delete(Scout p)  {
            dao.delete(p);
            dao.flush();
    }

    @Override
    public Scout findByID(Long id) throws EntityNotFoundException{
        return dao.getById(id);
    }

    @Override
    public Scout update(Scout scout) {
        return dao.saveAndFlush(scout);
    }

}
