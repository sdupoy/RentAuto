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
import edu.iit.sat.itmd4515.sdupoy.fp.service.EmployeeService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Simon
 */
@Named
@RequestScoped
public class EmployeeController extends AbstractController {

    @EJB CarService carService;
    @EJB EmployeeService employeeService;
    @Inject LoginController loginController;

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
        employee = employeeService.findEmployeeByUsername(loginController.getRemoteUser());
        agency = employee.getAgency();
        cars = agency.getCars();
        super.postConstruct();
        
    }

    private void refreshCars() {
        cars = employeeService.findEmployeeByUsername(loginController.getRemoteUser()).getAgency().getCars();
    }
    
    public String doHelp() {
        return "help";
    }
    
    public String doSettings() {
        return "settings";
    }
    
    /**
     * Prepare a new car to be created by the user
     * @return the page on which the new car will be created
     */
    public String doCreateCar() {
        car = new Car();
        return "carCreation";
    }

    /**
     * Handle the action from newCar view
     * @return the home page of car management
     */
    public String executeCreateCar() {
        carService.create(car, employee.getAgency());
        refreshCars();
        return "carManagementHome.xhtml";
    }
    
    public String doShowCar(Car car) {
        this.car = car;
        this.maintenances = car.getMaintenances();
        return "carDisplay";
    }

    public String doUpdateCar(Car car) {
        this.car = car;
        return "carEdition";
    }
    
    public String executeUpdateCar() {
        carService.update(car);
        refreshCars();

        return "carManagementHome";
    }

    public String doDeleteCar(Car car) {
        carService.delete(car, agency);
        refreshCars();
        return "carManagementHome";
    }
    
    public String doMaintainCar(Car car){
        this.car = car;
        this.maintenance = new Maintenance();
        System.out.println("Car id :" + car.getId());
        System.out.println("M id :" + maintenance.getId());
        return "carMaintenance";
    }
    
    public String executeCreateMaintenance(){
        System.out.println("Car id :" + car.getId());
        System.out.println("M id :" + maintenance.getId());
        carService.addMaintenance(car, maintenance);
        return "carManagementHome";
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
    
    
    
}
