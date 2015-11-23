/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sdupoy.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Simon
 * @param <T>
 */
public abstract class AbstractService<T> {
    
    /**
     * Persistence context for the entity manager
     */
    @PersistenceContext(unitName = "sdupoyPU")
    protected EntityManager em;

    private Class<T> entityClass;

    /**
     * Constructor for the abstract class
     * @param entityClass
     */
    public AbstractService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    /**
     * Persist the entity
     * @param entity
     */
    public void create(T entity){
        em.persist(entity);
    }
    
    /**
     * Find the entity associated with the id
     * @param id
     * @return the entity associated with the id parameter
     */
    public T find(Object id){
        return em.find(entityClass, id);
    }
    
    /**
     * Update the entity
     * @param entity
     */
    public void update(T entity){
        em.merge(entity);
    }
    
    /**
     * Delete the entity
     * @param entity
     */
    public void delete(T entity){
        em.remove(entity);
    }
    
    /**
     * Find all entities
     * @return a list of all entities
     */
    public abstract List<T> findAll();
    
}
