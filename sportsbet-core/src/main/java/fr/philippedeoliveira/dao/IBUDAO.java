package fr.philippedeoliveira.dao;

import fr.philippedeoliveira.beans.BusinessUnit;

public interface IBUDAO extends IGenericDAO<BusinessUnit, Integer> {

    /**
     * Search the business unit through the matching table
     * 
     * @param buCode
     * @return
     */
    BusinessUnit findBuByCode(String buCode);

}
