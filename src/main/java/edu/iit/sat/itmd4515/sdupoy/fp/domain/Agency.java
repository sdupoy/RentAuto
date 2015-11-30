/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sdupoy.fp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Simon
 */
@Entity
@NamedQueries({
        @NamedQuery(name="Agency.findAll", query ="select a from Agency a"),
        @NamedQuery(name="Agency.findByLocation", query="select a from Agency a where a.location = :loc")
})
public class Agency implements Serializable, Comparable {
    //----------------------//
    //      Attributes      //
    //----------------------//
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AGENCY_ID", nullable = false)
    protected Long id;
    private String location;
    
    @OneToMany
    private List<Employee> employees = new ArrayList<>();
    
    @OneToMany
    private List<Car> cars = new ArrayList<>();

    //----------------------//
    //      Methods         //
    //----------------------//
    
    /**
     * Default constructor (empty)
     */
    public Agency() {
    }
    
    /**
     * Get the value of id
     * @return the value of id
     */
    public Long getId() {
        return id;
    }    

    
    public void setId(Long id) {
        this.id = id;
    }
    
    

    /**
     * Get the value of location
     * @return the value of location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Set the value of location
     * @param location new value of location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Get the value of employees
     * @return the value of employees, the list of employees linked to this agency
     */
    public List<Employee> getEmployees() {
        return employees;
    }

    /**
     *  Set the value of employees
     * @param employees the list of employees
     */
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    /**
     * Method to add an employee to an agency
     * @param e an employee to add
     */
    public void addEmployee(Employee e){
        if(!this.employees.contains(e)){
            this.employees.add(e);
        }
    }

    /**
     * Method to remove (fire) an employee from an agency. This will delete the employee from the list.
     * @param e the employee to remove
     */
    public void removeEmployee(Employee e){
        if(this.employees.contains(e)){
            this.employees.remove(e);
        }
    }
    
    /**
     * Get the value of cars
     * @return the value of cars, the list of cars linked to this agency.
     */
    public List<Car> getCars() {
        return cars;
    }

    /**
     * Set the value of cars
     * @param cars the value of cars, the list of cars to linked to this agency.
     */
    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
    
    /**
     * Helper to add a car to an agency
     * @param c a car to add
     */
    public void addCar(Car c){
        if(!this.cars.contains(c)){
            this.cars.add(c);
        }
    }
    
    /**
     * Helper to remove a car from an agency. This will delete the car from the list.
     * @param c the car to remove
     */
    public void removeCar(Car c){
        if(this.cars.contains(c)){
            this.cars.remove(c);
        }
    }

    /**
     * toString function 
     * @return a string containing all attributes of Car class
     */
    @Override
    public String toString() {
        return "Agency{" + "id=" + id + ", location=" + location + ", employees=" + employees + ", cars=" + cars + '}';
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compareTo(Object o) {
        if(this.equals(o)){
            return 1;
        } else {
            return -1;
        }
    }
    
}
