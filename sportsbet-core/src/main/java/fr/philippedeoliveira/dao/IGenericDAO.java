package fr.philippedeoliveira.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Interface to be extended by any DAO interface
 * 
 * @author waddle
 *
 */
public interface IGenericDAO<T, ID extends Serializable> {

    /**
     * Delete an object
     * 
     * @param entity
     *            objet
     */
    void delete(T entity);

    /**
     * Gets an object by its ID
     * 
     * @param id
     *            id
     * @return objet
     */
    T getById(ID id);

    /**
     * Saves an object
     * 
     * @param entity
     *            objet
     * @return objet
     */
    T makePersistent(T entity);

    /**
     * Updates an object
     * 
     * @param entity
     *            objet
     * @return objet
     */
    public T update(T entity);

    /**
     * flush the session
     */
    void flush();

    /**
     * Get all the objects of type T
     */
    public List<T> findAll();
}