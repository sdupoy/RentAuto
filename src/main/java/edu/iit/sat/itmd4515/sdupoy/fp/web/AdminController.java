/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sdupoy.fp.web;

import edu.iit.sat.itmd4515.sdupoy.fp.domain.Agency;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Agent;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Client;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Employee;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Manager;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.security.User;
import edu.iit.sat.itmd4515.sdupoy.fp.service.AgencyService;
import edu.iit.sat.itmd4515.sdupoy.fp.service.ClientService;
import edu.iit.sat.itmd4515.sdupoy.fp.service.EmployeeService;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Simon
 */
@ManagedBean(name = "adminController")
@RequestScoped
public class AdminController extends AbstractController {

    private static final Logger LOG = Logger.getLogger(AdminController.class.getName());

    @EJB
    EmployeeService employeeService;
    @EJB
    ClientService clientService;
    @EJB
    AgencyService agencyService;

    @ManagedProperty(value = "#{loginController}")
    private LoginController loginController;
    
    private Employee employee;
    private List<Employee> employees;
    private Manager manager;
    private Agent agent;
    private Client client;
    private User user;
    private List<Agency> agencies;
    private Agency agency;
    private String search;

    /**
     * Default constructor (empty)
     */
    public AdminController() {
    }

    /**
     * Overriding the post construct method
     */
    @Override
    @PostConstruct
    protected void postConstruct() {
        user = new User();
        search = new String();
        manager = new Manager();
        agent = new Agent();
        agencies = agencyService.findAll();
        super.postConstruct();
    }
    
    /**
     * Prepare a new user to be created by the user
     * @return the page on which the new user will be created
     */
    public String doCreateUser() {
        LOG.info("Preparing to create a user ");
        user = new User();
        client = new Client();
        manager = new Manager();
        agent = new Agent();
        return "adminUserCreation"+ FACES_REDIRECT;
    }
    
    /**
     * Handle the action of creating a new admin
     * @return to admin home page
     */
    public String executeCreateAdmin() {
        LOG.info("Create a new admin");
        LOG.info(this.user.toString());
        employeeService.createNewAdmin(user);
        return "adminHome";
    }
    
    /**
     * Handle the action of creating a new manager
     * @return to admin home page
     */
    public String executeCreateManager() {
        LOG.info("Create a new manager and associated user");
        LOG.info(this.manager.toString());
        LOG.info(this.user.toString());
        manager.setUser(new User(user.getUsername(), user.getPassword()));
        agency.addEmployee(manager);
        manager.setAgency(agency);
        employeeService.createNewManager(manager);
        return "adminHome";
    }
    
    /**
     * Handle the action of creating a new agent
     * @return to admin home page
     */
    public String executeCreateAgent() {
        LOG.info("Create a new agent" + agent.toString() + "in the agency " + agency.toString() + "associated with " + user.toString());
        agent.setUser(new User(user.getUsername(), user.getPassword()));
        agency.addEmployee(agent);
        agent.setAgency(agency);
        employeeService.createNewAgent(agent);
        return "adminHome" + FACES_REDIRECT;
    }
    
    /**
     * handle the action of creating a new client
     * @return to admin home page
     */
    public String executeCreateClient() {
        client.setUser(new User(user.getUsername(), user.getPassword()));
        LOG.info("Create a new client" + this.client.toString() + "associated with " + this.user.toString());
        clientService.createNewClient(client);
        return "adminHome" + FACES_REDIRECT;
    }
    
    /**
     * preparing to update an user
     * @param user
     * @return to the page where info will be edited
     */
    public String doUpdateUser(User user) {
        LOG.info("preparing update of user");
        LOG.info( user.toString());
        this.user = user;
        return "adminUserEdition";
    }
    
    /**
     * Search a user by its username
     * @param search
     * @return to the display results page
     */
    public String doSearchUser(String search) {
        LOG.info("Preparing to search " + this.search);
        this.employee = employeeService.findEmployeeByUsername(search);
        LOG.info( "Search returned " + employee.toString());
        return "adminUserSearchResult";
    }

    /**
     * get the login controller
     * @return
     */
    public LoginController getLoginController() {
        return loginController;
    }

    /**
     * set the login controller
     * @param loginController
     */
    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

   
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public List<Agency> getAgencies() {
        return agencies;
    }

    public void setAgencies(List<Agency> agencies) {
        this.agencies = agencies;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

}
