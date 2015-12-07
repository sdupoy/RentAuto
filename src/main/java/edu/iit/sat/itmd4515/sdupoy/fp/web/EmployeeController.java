/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sdupoy.fp.web;

import edu.iit.sat.itmd4515.sdupoy.fp.domain.Agency;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Car;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Employee;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Maintenance;
import edu.iit.sat.itmd4515.sdupoy.fp.service.CarService;
import edu.iit.sat.itmd4515.sdupoy.fp.service.MaintenanceService;
import edu.iit.sat.itmd4515.sdupoy.fp.service.EmployeeService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Simon
 */
@ManagedBean(name = "employeeController")
@RequestScoped
public class EmployeeController extends AbstractController {

    private static final Logger LOG = Logger.getLogger(EmployeeController.class.getName());

    @EJB CarService carService;
    @EJB EmployeeService employeeService;
    @EJB MaintenanceService maintenanceService;
    
    @ManagedProperty(value="#{loginController}")
    private LoginController loginController;

    private List<Car> cars;
    private Car car;
    private List<Maintenance> maintenances;
    private Maintenance maintenance;
    private Employee employee;
    private Agency agency;

    
    public EmployeeController() {
    }
    
    
    @Override
    @PostConstruct
    protected void postConstruct() {
        car = new Car();
        maintenance = new Maintenance();
        maintenances = new ArrayList<>();
        employee = employeeService.findEmployeeByUsername(loginController.getRemoteUser());
        agency = employee.getAgency();
        refreshCars();
        super.postConstruct();
    }

    private void refreshCars() {
        cars = employeeService.findEmployeeByUsername(loginController.getRemoteUser()).getAgency().getCars();
        for (Car car1 : cars){
            for (Maintenance maintenance1 : car1.getMaintenances()){
                maintenances.add(maintenance1);
            }
        }
    }
    
    public String doHelp() {
        return "help" + FACES_REDIRECT;
    }
    
    public String doSettings() {
        return "settings" + FACES_REDIRECT;
    }
    
    /**
     * Prepare a new car to be created by the user
     * @return the page on which the new car will be created
     */
    public String doCreateCar() {
        car = new Car();
        return "carCreation" + FACES_REDIRECT;
    }

    /**
     * Handle the action from newCar view
     * @return the home page of car management
     */
    public String executeCreateCar() {
        carService.create(car, employee.getAgency());
        refreshCars();
        return "carManagementHome" + FACES_REDIRECT;
    }
    
    public String doShowCar(Car car) {
        this.car = car;
        this.maintenances = car.getMaintenances();
        return "carDisplay";
    }

    public String doUpdateCar(Car car) {
        LOG.info("preparing update ");
        LOG.info( car.toString());
        this.car = car;
        return "carEdition";
    }
    
    public String executeUpdateCar() {
        LOG.info("executing update ");
        LOG.info( car.toString());
        carService.update(car);
        refreshCars();
        return "carManagementHome" + FACES_REDIRECT;
    }

    public String doDeleteCar(Car car) {
        LOG.info("executing deletion ");
        LOG.info( car.toString());
        carService.delete(car, agency);
        refreshCars();
        return "carManagementHome" + FACES_REDIRECT;
    }
    
    public String doDeleteMaintenance(Maintenance maintenance) {
        LOG.info("Preparing to delete ");
        LOG.info( maintenance.toString());
        this.car = maintenance.getCar();
        maintenanceService.deleteMaintenance(maintenance, car);
        return "carMaintenanceHome" + FACES_REDIRECT;
    }
    
    public String doMaintainCar(Car car){
        LOG.info("Preparing to add a new maintenance on ");
        LOG.info( car.toString());
        this.car = car;
        this.maintenance = new Maintenance();
        return "carMaintenance";
    }
    
    public String executeCreateMaintenance(){
        LOG.info("Creating a new maintenance on ");
        LOG.info( car.toString());
        maintenanceService.addMaintenance(car, maintenance);
        return "carMaintenanceHome" + FACES_REDIRECT;
    }
    
    public String doUpdateMaintenance(Maintenance maintenance) {
        LOG.info("Preparing to update ");
        LOG.info( maintenance.toString());
        this.maintenance = maintenance;
        return "carMaintenanceEdition";
    }
    
    public String executeUpdateMaintenance() { 
        maintenanceService.updateMaintenance(maintenance);
        return "carMaintenanceHome" + FACES_REDIRECT;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Maintenance> getMaintenances() {
        return maintenances;
    }

    public void setMaintenances(List<Maintenance> maintenances) {
        this.maintenances = maintenances;
    }

    public Maintenance getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(Maintenance maintenance) {
        this.maintenance = maintenance;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }
    
    

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
    
    
    
}
