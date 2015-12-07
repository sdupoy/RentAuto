 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sdupoy.fp.domain;


import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Simon
 */
@Entity
@NamedQueries({
        @NamedQuery(name="Reservation.findByCar", query="select r from Reservation r where r.car = :car"),
        @NamedQuery(name="Reservation.findByClient", query="select r from Reservation r where r.client = :client"),
        @NamedQuery(name="Reservation.findByStartDate", query ="select r from Reservation r where r.startDate = :startDate"),
        @NamedQuery(name="Reservation.findByEndDate", query ="select r from Reservation r where r.endDate = :endDate")
})
public class Reservation extends Event {
    //----------------------//
    //      Attributes      //
    //----------------------//
    
    
    @ManyToOne(optional=false)
    @JoinColumn(name="CAR_ID",referencedColumnName="CAR_ID")
    private Car car;
    
    @ManyToOne(optional=false)
    @JoinColumn(name="CLIENT_ID",referencedColumnName="CLIENT_ID")
    private Client client;
    
    //----------------------//
    //      Methods         //
    //----------------------//
    
    /**
     * Default constructor (empty)
     */
    public Reservation() {
        super();
    }
    
    /**
     * Constructor with dates
     * @param start the Date of the beginning of the reservation
     * @param end the end date
    */
    public Reservation(Date start, Date end){
        super(start, end);
    }
    
    /**
     * Get the value of car
     * @return the value of car
     */
    public Car getCar() {
        return car;
    }

    /**
     * Set the value of car
     * @param car the value of car
     */
    public void setCar(Car car) {
        this.car = car;
    }
    
    /**
     * Get the value of client
     * @return the value of client
     */
    public Client getClient() {
        return client;
    }

    /**
     * Set the value of client
     * @param client the value of client
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * toString function 
     * @return a string containing all attributes of Car class
     */
    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", car=" + car + ", client=" + client + '}';
    }
 
}
