/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sdupoy.fp.web;

import edu.iit.sat.itmd4515.sdupoy.fp.domain.Agency;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Agent;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Car;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Employee;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Manager;
import edu.iit.sat.itmd4515.sdupoy.fp.service.CarService;
import edu.iit.sat.itmd4515.sdupoy.fp.service.EmployeeService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
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
    private Employee employee;
    private Agency agency;

    
    public EmployeeController() {
    }
    
    
    @Override
    @PostConstruct
    protected void postConstruct() {
        car = new Car();
        employee = employeeService.findEmployeeByUsername(loginController.getRemoteUser());
        agency = employee.getAgency();
        cars = agency.getCars();
        super.postConstruct();
        
    }

    private void refreshCars() {
        cars = employeeService.findEmployeeByUsername(loginController.getRemoteUser()).getAgency().getCars();
        
    }
    
    /**
     * Prepare a new car to be created by the user
     * @return the page on which the new car will be created
     */
    public String doCreateCar() {
        car = new Car();
        return "createFarm";
    }

    /**
     * Handle the action from newCar view
     * @return the home page of car management
     */
    public String executeCreateCar() {
        carService.create(car, agency);
        refreshCars();
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    
    
}
