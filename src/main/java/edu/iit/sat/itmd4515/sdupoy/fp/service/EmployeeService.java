/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sdupoy.fp.service;


import edu.iit.sat.itmd4515.sdupoy.fp.domain.Agent;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Employee;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Manager;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Simon
 */
@Stateless
public class EmployeeService extends AbstractService<Employee> {
    
    /**
     * Constructor of the superclass
     */
    public EmployeeService() {
        super(Employee.class);
    }
    
    /**
     * Overriding the supermethod
     * @return the list of employees
     */
    @Override
    public List<Employee> findAll() {
        return em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
    }
    
    /**
     *
     * @param username the username of the agent
     * @return the agent with the associated username
     */
    public Employee findEmployeeByUsername(String username){
        TypedQuery<Employee> query = em.createNamedQuery("Employee.findByUsername", Employee.class);
        query.setParameter("uname", username);
        return query.getSingleResult();
    }
    
    /**
     *
     * @param username the username of the agent
     * @return the agent with the associated username
     */
    public Agent findAgentByUsername(String username){
        TypedQuery<Employee> query = em.createNamedQuery("Employee.findByUsername", Employee.class);
        query.setParameter("uname", username);
        return (Agent) query.getSingleResult();
    }
    
    /**
     *
     * @param username the username of the manager
     * @return the manager with the associated username
     */
    public Manager findManagerByUsername(String username){
        TypedQuery<Employee> query = em.createNamedQuery("Employee.findByUsername", Employee.class);
        query.setParameter("uname", username);
        return (Manager) query.getSingleResult();
    }

}
