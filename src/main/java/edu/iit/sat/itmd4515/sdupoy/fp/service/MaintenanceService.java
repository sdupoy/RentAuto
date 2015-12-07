/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sdupoy.fp.service;

import edu.iit.sat.itmd4515.sdupoy.fp.domain.Car;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Maintenance;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Simon
 */
@Stateless
public class MaintenanceService extends AbstractService<Maintenance>{

    /**
     * Constructor of the superclass
     */
    public MaintenanceService() {
        super(Maintenance.class);
    }

    /**
     * Overriding the supermethod
     * @return the list of reservations
     */
    @Override
    public List<Maintenance> findAll() {
        return em.createNamedQuery("Maitenance.findAll", Maintenance.class).getResultList();
    }
    
    public void addMaintenance(Car car, Maintenance m){
        car = em.getReference(Car.class, car.getId());
        car.addMaintenance(m);
        m.setCar(car);
        em.persist(m);
        em.merge(car);
    }
    
    public void deleteMaintenance(Maintenance maintenance, Car car){
        car = em.getReference(Car.class, car.getId());
        maintenance = em.getReference(Maintenance.class, maintenance.getId());
        car.getMaintenances().remove(maintenance);
        maintenance.setCar(null);
        em.remove(maintenance);
    }
    
    public void updateMaintenance(Maintenance newM){
        Maintenance currentM = em.getReference(Maintenance.class, newM.getId());
        currentM.setTitle(newM.getTitle());
        currentM.setStartDate(newM.getStartDate());
        currentM.setEndDate(newM.getEndDate());
    }
    
}
