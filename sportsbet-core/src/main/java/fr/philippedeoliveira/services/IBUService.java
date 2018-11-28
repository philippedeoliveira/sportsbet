package fr.philippedeoliveira.services;

import java.util.List;

import fr.philippedeoliveira.beans.BusinessUnit;

public interface IBUService {
    BusinessUnit getBuById(Integer id);

    BusinessUnit findBuByCode(String buCode);

    /**
     * Return all the Business Unit with at least one better
     * 
     * @return
     */
    List<BusinessUnit> getAllBus();

}
