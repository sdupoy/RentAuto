/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sdupoy.fp.service;


import edu.iit.sat.itmd4515.sdupoy.fp.domain.Client;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Simon
 */
@Stateless
public class ClientService extends AbstractService<Client> {
    
    /**
     * Constructor of the superclass
     */
    public ClientService() {
        super(Client.class);
    }
    
    /**
     * Overriding the supermethod
     * @return the list of all clients
     */
    @Override
    public List<Client> findAll() {
        return em.createNamedQuery("Client.findAll", Client.class).getResultList();
    }
    
    /**
     *
     * @param username
     * @return the user associated with the username
     */
    public Client findByUsername(String username){
        TypedQuery<Client> query = em.createNamedQuery("Client.findByUsername", Client.class);
        query.setParameter("uname", username);
        return query.getSingleResult();
    }
    
    /**
     *
     * @param lastName
     * @return the user associated with the lastName
     */
    public Client findByLastName(String lastName){
        TypedQuery<Client> query = em.createNamedQuery("Client.findByUsername", Client.class);
        query.setParameter("lname", lastName);
        return query.getSingleResult();
    }

}
