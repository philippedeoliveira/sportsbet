package fr.philippedeoliveira.dao.impl;

import org.springframework.stereotype.Repository;

import fr.philippedeoliveira.beans.BusinessUnit;
import fr.philippedeoliveira.dao.IBUDAO;

@Repository
public class HibernateBUDAO extends GenericHibernateDAO<BusinessUnit, Integer> implements IBUDAO {
    public HibernateBUDAO() {
        super(BusinessUnit.class);
    }

    @Override
    public BusinessUnit findBuByCode(String buCode) {
        Integer buId = (Integer) (getCurrentSession().createSQLQuery(
                "select id from businessUnitCodeToId where buCode=?").setParameter(0, buCode).uniqueResult());
        if (buId != null) {
            return this.getById(buId);
        } else {
            return null;
        }
    }
}
