/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sdupoy.fp.service;

import edu.iit.sat.itmd4515.sdupoy.fp.domain.Agency;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Car;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Maintenance;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Simon
 */
@Stateless
public class CarService extends AbstractService<Car> {
    
    /**
     * Constructor of the superclass
     */
    public CarService() {
        super(Car.class);
    }
    
    /**
     * Create a new car and associate it with an agency
     * @param car
     * @param agency 
     */
    public void create(Car car, Agency agency){
        agency = em.getReference(Agency.class, agency.getId());
        
        car.setAgency(agency);
        agency.addCar(car);
        
        em.persist(car);
    }
    
    public void delete(Car car, Agency agency){
        agency = em.getReference(Agency.class, agency.getId());
        car = em.getReference(Car.class, car.getId());
        
        agency.getCars().remove(car);
        car.setAgency(null);
        
        em.remove(car);
    }
    
    public void update(Car newCar){
        Car currentCar = em.getReference(Car.class, newCar.getId());
        
        currentCar.setLicensePlate(newCar.getLicensePlate());
        currentCar.setMaker(newCar.getMaker());
        currentCar.setModel(newCar.getModel());
        currentCar.setNbKms(newCar.getNbKms());
        currentCar.setOptions(newCar.getOptions());
        
    }
    
    public void addMaintenance(Car car, Maintenance m){
        car = em.getReference(Car.class, car.getId());
        
        car.addMaintenance(m);
        m.setCar(car);
        em.persist(m);
        em.merge(car);
    }
    
    /**
     * Overriding the findAll of the superclass
     * @return  the list of all cars
     */
    @Override
    public List<Car> findAll() {
        return em.createNamedQuery("Car.findAll", Car.class).getResultList();
    }
     
    /**
     * Finding cars by its model
     * @param model
     * @return the list of cars of a certain model
     */
    public List<Car> findByModel(String model) {
        TypedQuery<Car> query = em.createNamedQuery("Car.findByModel", Car.class);
        query.setParameter("model", model);
        return query.getResultList();
    }
    
    /**
     * Finding cars by its makers
     * @param maker
     * @return the list of cars of a certain maker
     */
    public List<Car> findByMaker(String maker) {
        TypedQuery<Car> query = em.createNamedQuery("Car.findByMaker", Car.class);
        query.setParameter("maker", maker);
        return query.getResultList();
    }
    
    /**
     * Finding a car by license plate
     * @param lp
     * @return the car of its associated license plate
     */
    public Car findByLicensePlate(String lp) {
        TypedQuery<Car> query = em.createNamedQuery("Car.findByLicensePlate", Car.class);
        query.setParameter("LP", lp);
        return query.getSingleResult();
    }
}
