package fr.philippedeoliveira.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.philippedeoliveira.beans.BusinessUnit;
import fr.philippedeoliveira.dao.IBUDAO;
import fr.philippedeoliveira.services.IBUService;

@Service
public class BUServiceImpl implements IBUService {
    @Inject
    private IBUDAO buDAO;

    @Override
    @Transactional
    public BusinessUnit getBuById(Integer id) {
        return buDAO.getById(id);
    }

    @Override
    @Transactional
    public BusinessUnit findBuByCode(String buCode) {
        return buDAO.findBuByCode(buCode);
    }

    @Override
    @Transactional
    public List<BusinessUnit> getAllBus() {
        return buDAO.findAll();
    }

    public void setBuDAO(IBUDAO buDAO) {
        this.buDAO = buDAO;
    }
}
