package fr.philippedeoliveira.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import fr.philippedeoliveira.dao.IGenericDAO;

/**
 * Generic DAO using Hibernate framework
 * 
 * @author waddle
 *
 * @param <T> : Bean being handled by this DAO
 * @param <ID> : Primary key type of the bean being handled
 */

public abstract class GenericHibernateDAO<T, ID extends Serializable> implements IGenericDAO<T, ID> {
    /** Type of the bean being persisted */
	private final Class<T> persistentClass;

	@Inject
    private SessionFactory sessionFactory;

    public GenericHibernateDAO(Class<T> clazz) {
        this.persistentClass = clazz;
    }

    @Override
    @Transactional
    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    public List<T> findAll() {
        return getCurrentSession().createCriteria(persistentClass).list();
    }

    @Override
    public T getById(ID id) {
        return (T) getCurrentSession().get(persistentClass, id);
    }

    @Override
    @Transactional
    public T makePersistent(T entity) {
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    @Override
    @Transactional
    public T update(T entity) {
        return makePersistent(entity);
    }

    @Override
    public void flush() {
        getCurrentSession().flush();
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
