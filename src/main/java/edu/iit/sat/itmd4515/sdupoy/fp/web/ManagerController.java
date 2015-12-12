/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sdupoy.fp.web;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Agency;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Agent;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Employee;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Manager;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.security.User;
import edu.iit.sat.itmd4515.sdupoy.fp.service.EmployeeService;
import static edu.iit.sat.itmd4515.sdupoy.fp.web.AbstractController.FACES_REDIRECT;
import java.util.ArrayList;
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
@ManagedBean(name = "managerController")
@RequestScoped
public class ManagerController extends AbstractController {

    private static final Logger LOG = Logger.getLogger(EmployeeController.class.getName());

    @EJB EmployeeService employeeService;
    
    @ManagedProperty(value="#{loginController}")
    private LoginController loginController;

    private List<Employee> employees;
    private Employee employee;
    private Agent agent;
    private Manager manager;
    private User user;
    private Agency agency;
    private String search;
    
    public ManagerController() {
    }
    
    @Override
    @PostConstruct
    protected void postConstruct() {
        user = new User();
        search = new String();
        agent = new Agent();
        employees = new ArrayList<>();
        employee = employeeService.findEmployeeByUsername(loginController.getRemoteUser());
        agency = employee.getAgency();
        refreshEmployees();
        super.postConstruct();
    }
    
    private void refreshEmployees() {
        employees = employeeService.findEmployeeByAgency(agency);
    }
    
    public String doCreateAgent() {
        LOG.info("Preparing to create an agent ");
        agent = new Agent();
        return "managerEmployeeCreation";
    }
    
    public String executeCreateAgent() {
        LOG.info("Create a new agent" + agent.toString() + "in the agency " + agency.toString() + "associated with " + user.toString());
        agent.setUser(new User(user.getUsername(), user.getPassword()));
        agency.addEmployee(agent);
        agent.setAgency(agency);
        employeeService.createNewAgent(agent);
        refreshEmployees();
        return "managerHumanResources";
    }
    
    public String searchEmployeeByName(){
        LOG.info("Preparing to search " + this.search);
        this.employees = employeeService.findEmployeeByName(search);
        LOG.info( "Search returned " + employees.toString());
        return "managerEmployeeSearchResult";
    }
    
    public String doShowEmployee(Employee employee) {
        this.employee = employee;
        LOG.info("Preparing to show " + this.employee.toString());;
        return "managerEmployeeDisplay";
    }
    
    public String doHumanResources() {
        LOG.info("Preparing to do human resources initialization ");
        this.manager = employeeService.findManagerByUsername(loginController.getRemoteUser());
        this.employees = employeeService.findEmployeeByAgency(this.agency);
        return "managerHumanResources";
    }
    
    public String doUpdateEmployee(Employee employee) {
        LOG.info("preparing update of employee");
        LOG.info( employee.toString());
        this.employee = employee;
        return "managerEmployeeEdition";
    }
    
    public String executeUpdateEmployee() {
        LOG.info("executing update of employee");
        LOG.info( employee.toString());
        employeeService.update(employee);
        return "managerEmployeeDisplay";
    }
    
    public String doDeleteEmployee(Employee employee) {
        LOG.info("Preparing to delete ");
        LOG.info( employee.toString());
        this.agency = employee.getAgency();
        //employeeService.deleteEmployee(employee, agency);
        return "managerHumanResources" + FACES_REDIRECT;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
    
    
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    
}
