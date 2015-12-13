/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sdupoy.fp.service;


import edu.iit.sat.itmd4515.sdupoy.fp.domain.Agency;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Agent;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Employee;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Manager;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.security.Group;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.security.User;
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
     * Find an employee by its username
     * @param username the username of the agent
     * @return the agent with the associated username
     */
    public Employee findEmployeeByUsername(String username){
        TypedQuery<Employee> query = em.createNamedQuery("Employee.findByUsername", Employee.class);
        query.setParameter("uname", username);
        return query.getSingleResult();
    }
    
    /**
     * Find employee(s) by their name or part of it
     * @param name the name of the employee
     * @return the list of employees containing name
     */
    public List<Employee> findEmployeeByName(String name){
        TypedQuery<Employee> query = em.createNamedQuery("Employee.findByName", Employee.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }
    
    /**
     * Find an employee by its SSN
     * @param ssn the name of the employee
     * @return the list of employees containing name
     */
    public Employee findEmployeeBySsn(Long ssn){
        TypedQuery<Employee> query = em.createNamedQuery("Employee.findBySsn", Employee.class);
        query.setParameter("ssn", ssn);
        return query.getSingleResult();
    }
    
    /**
     * Find an agent by its username
     * @param username the username of the agent
     * @return the agent with the associated username
     */
    public Agent findAgentByUsername(String username){
        TypedQuery<Employee> query = em.createNamedQuery("Employee.findByUsername", Employee.class);
        query.setParameter("uname", username);
        return (Agent) query.getSingleResult();
    }
    
    /**
     * Find a manager by its username
     * @param username the username of the manager
     * @return the manager with the associated username
     */
    public Manager findManagerByUsername(String username){
        TypedQuery<Employee> query = em.createNamedQuery("Employee.findByUsername", Employee.class);
        query.setParameter("uname", username);
        return (Manager) query.getSingleResult();
    }
    
    /**
     * Find employees from an agency
     * @param agency the agency you want to find the employees
     * @return the list of emplyees of the agency
     */
    public List<Employee> findEmployeeByAgency(Agency agency){
        TypedQuery<Employee> query = em.createNamedQuery("Employee.findByAgency", Employee.class);
        query.setParameter("agency", agency);
        return query.getResultList();
    }
    
    /**
     * Create a new agent
     * @param agent
     */
    public void createNewAgent(Agent agent){
        Group g = em.createNamedQuery("Group.findGroup", Group.class).setParameter("groupname", "agents").getSingleResult();
        agent.getUser().addUserToGroup(g);
        em.persist(agent);
    }
    
    /**
     * Create a new manager
     * @param manager
     */
    public void createNewManager(Manager manager){
        Group g = em.createNamedQuery("Group.findGroup", Group.class).setParameter("groupname", "managers").getSingleResult();
        manager.getUser().addUserToGroup(g);
        em.persist(manager);
    }
    
    /**
     * Create a new admin
     * @param admin
     */
    public void createNewAdmin(User admin){
        Group g = em.createNamedQuery("Group.findGroup", Group.class).setParameter("groupname", "admins").getSingleResult();
        admin.addUserToGroup(g);
        em.persist(admin);
    }
    
    
    
    /**
     * Get the name of the group of an employee
     * @param employee
     * @return the string of the group name
     */
    public String getUsergroup(Employee employee){
        Group g = em.createNamedQuery("Group.findUserGroup", Group.class).setParameter("user", employee.getUser()).getSingleResult();
        return g.getGroupName();
    }

    @Override
    public void update(Employee newEmployee){
        Employee currentEmployee = em.getReference(Employee.class, newEmployee.getId());
        currentEmployee.setAddress(newEmployee.getAddress());
        currentEmployee.setFirstName(newEmployee.getFirstName());
        currentEmployee.setLastName(newEmployee.getLastName());
        currentEmployee.setSsn(newEmployee.getSsn());
        currentEmployee.setAgency(newEmployee.getAgency());
    }
    
    /**
     * Delete an employee and remove the link with the agency
     * @param employee
     * @param agency
     */
    public void deleteEmployee(Employee employee, Agency agency){
        agency = em.getReference(Agency.class, agency.getId());
        employee = em.getReference(Employee.class, employee.getId());
        agency.getEmployees().remove(employee);
        employee.setAgency(null);
        em.remove(employee);
    }
}
