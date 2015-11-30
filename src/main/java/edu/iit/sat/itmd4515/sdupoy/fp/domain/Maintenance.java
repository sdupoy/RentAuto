/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sdupoy.fp.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Simon
 */
@Entity
public class Maintenance implements Serializable, Comparable {
    //----------------------//
    //      Attributes      //
    //----------------------//
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MTN_ID", nullable = false)
    protected Long id;
    
    @ManyToOne(optional=false)
    @JoinColumn(name="CAR_ID",referencedColumnName="CAR_ID")
    private Car car;    
    
    @Temporal(TemporalType.DATE)
    private Date startDate;
    
    @Temporal(TemporalType.DATE)
    private Date endDate;

    //----------------------//
    //      Methods         //
    //----------------------//
    
    /**
     * Default constructor (empty)
     */
    public Maintenance() {
    }
    
    /**
     * Constructor with dates
     * @param start the date of the beginning of the maintenance
     * @param end the end date
     */
    public Maintenance(Date start, Date end){
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

    public void setId(Long id) {
        this.id = id;
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
     * @param car new value of car
     */
    public void setCar(Car car) {
        this.car = car;
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
     * toString function 
     * @return a string containing all attributes of Car class
     */
    @Override
    public String toString() {
        return "Maintenance{" + "id=" + id + ", car=" + car + ", startDate=" + startDate + ", endDate=" + endDate + '}';
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
