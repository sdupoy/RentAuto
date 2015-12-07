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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

/**
 *
 * @author Simon
 */
@Entity
@NamedQueries({
        @NamedQuery(name="Car.findByLicensePlate", query="select c from Car c where c.licensePlate = :LP"),
        @NamedQuery(name="Car.findByModel", query="select c from Car c where c.model = :model"),
        @NamedQuery(name="Car.findByMaker", query ="select c from Car c where c.maker = :maker"),
        @NamedQuery(name="Car.findAll", query ="select c from Car c")
})
public class Car implements Serializable, Comparable {
    //----------------------//
    //      Attributes      //
    //----------------------//
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CAR_ID", nullable = false)
    protected Long id;
    
    @Column(unique = true)
    @Size(min=5, max = 10, message = "Wrong size !")
    private String licensePlate;
    
    private Long nbKms;
    private String maker;
    private String model;
    private String options;
    
    @OneToMany(mappedBy = "car")
    private List<Reservation> reservations = new ArrayList<>();
    
    @OneToMany(mappedBy = "car")
    private List<Maintenance> maintenances = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name="agency")
    private Agency agency;
    
    
    //----------------------//
    //      Methods         //
    //----------------------//
    /**
     * Default constructor (empty)
     */
    public Car() {
    }
    
    /**
     * Constructor with basics car infos
     * @param licensePlate
     * @param model
     * @param maker
     * @param nbKms 
     */
    public Car(String licensePlate, String model, String maker, Long nbKms) {
        this.licensePlate = licensePlate;
        this.model = model;
        this.maker = maker;
        this.nbKms = nbKms;
        this.options = "None";
    }
    
    /**
     * Constructor with all car infos
     * @param licensePlate
     * @param model
     * @param maker
     * @param nbKms
     * @param options 
     */
    public Car(String licensePlate, String model, String maker, Long nbKms, String options) {
        this.licensePlate = licensePlate;
        this.model = model;
        this.maker = maker;
        this.nbKms = nbKms;
        this.options = options;
    }
    
    /**
     * Helper to add a reservation on a car
     * @param r a reservation to add
     */
    public void addReservation(Reservation r){
        if(!this.reservations.contains(r)){
            this.reservations.add(r);
        }
        r.setCar(this);
    }
    
    /**
     * Helper to cancel a reservation for a car
     * @param r the reservation to cancel
     */
    public void cancelReservation(Reservation r){
        if(this.reservations.contains(r)){
            this.reservations.remove(r);
        }
        r.setCar(null);
    }
    
    /**
     * Helper to add a maintenance on a car
     * @param m a maintenance to add
     */
    public void addMaintenance(Maintenance m){
        if(!this.maintenances.contains(m)){
            this.maintenances.add(m);
        }
        m.setCar(this);
    }
    
    /**
     * Helper to cancel a maintenance for a car
     * @param m the maintenance to cancel
     */
    public void cancelMaintenance(Maintenance m){
        if(this.maintenances.contains(m)){
            this.maintenances.remove(m);
        }
        m.setCar(null);
    }
    
    /**
     * Get the value of agency
     *
     * @return the value of agency
     */
    public Agency getAgency() {
        return agency;
    }

    /**
     * Set the value of agency
     *
     * @param agency new value of agency
     */
    public void setAgency(Agency agency) {
        this.agency = agency;
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
     * Get the value of licensePlate
     * @return the value of licensePlate
     */
    public String getLicensePlate() {
        return licensePlate;
    }

    /**
     * Set the value of licensePlate
     * @param licensePlate new value of licensePlate
     */
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    /**
     * Get the value of nbKms
     * @return the value of nbKms
     */
    public Long getNbKms() {
        return nbKms;
    }

    /**
     * Set the value of nbKms
     * @param nbKms new value of nbKms
     */
    public void setNbKms(Long nbKms) {
        this.nbKms = nbKms;
    }

    /**
     * Get the value of maker
     * @return the value of maker
     */
    public String getMaker() {
        return maker;
    }

    /**
     * Set the value of maker
     * @param maker new value of maker
     */
    public void setMaker(String maker) {
        this.maker = maker;
    }

    /**
     * Get the value of model
     * @return the value of model
     */
    public String getModel() {
        return model;
    }

    /**
     * Set the value of model
     * @param model new value of model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Get the value of options
     * @return the value of options
     */
    public String getOptions() {
        return options;
    }

    /**
     * Set the value of options
     * @param options new value of options
     */
    public void setOptions(String options) {
        this.options = options;
    }
    
    /**
     * Get the value of reservations
     * @return the value of reservations
     */
    public List<Reservation> getReservations() {
        return reservations;
    }

    /**
     * Set the value of reservations
     * @param reservations new value of reservations
     */
    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
    
    /**
     * Get the value of maintenances
     * @return the value of maintenances
     */
    public List<Maintenance> getMaintenances() {
        return maintenances;
    }

    /**
     * Set the value of maintenances
     * @param maintenances new value of maintenances
     */
    public void setMaintenances(List<Maintenance> maintenances) {
        this.maintenances = maintenances;
    }

    /**
     * toString function 
     * @return a string containing all attributes of Car class
     */
    @Override
    public String toString() {
        return "Car{" + "id=" + id + ", licensePlate=" + licensePlate + ", nbKms=" + nbKms + ", maker=" + maker + ", model=" + model + ", options=" + options + '}';
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
