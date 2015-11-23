 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sdupoy.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
public class Reservation implements Serializable, Comparable {
    //----------------------//
    //      Attributes      //
    //----------------------//
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERV_ID", nullable = false)
    private Long id;
    
    @Temporal(TemporalType.DATE)   
    private Date startDate;
    
    @Temporal(TemporalType.DATE)
    private Date endDate;
    
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
    }
    
    /**
     * Constructor with dates
     * @param start the Date of the beginning of the reservation
     * @param end the end date
    */
    public Reservation(Date start, Date end){
        this.startDate = start;
        this.endDate = end;
    }
    
    /**
     * Get the value of id
     * @return the value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the value of startDate
     * @return the value of startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Set the value of startDate
     * @param startDate new value of startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Get the value of endDate
     * @return the value of endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Set the value of endDate
     * @param endDate new value of endDate
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
