/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sdupoy.service;

import edu.iit.sat.itmd4515.sdupoy.domain.Agency;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Simon
 */
@Stateless
public class AgencyService extends AbstractService<Agency> {

    /**
     * Constructor of the superclass
     */
    public AgencyService() {
        super(Agency.class);
    }

    /**
     * Overriding the findAll of the superclass
     * @return  the list of all agencies
     */
    @Override
    public List<Agency> findAll() {
        return em.createNamedQuery("Agency.findAll", Agency.class).getResultList();
    }
    
    /**
     *  Find an agency by its location
     * @param location
     * @return the list of agencies in this location
     */
    public List<Agency> findByLocation(String location) {
        TypedQuery<Agency> query = em.createNamedQuery("Agency.findByLocation", Agency.class);
        query.setParameter("loc", location);
        return query.getResultList();
    }
   
}
