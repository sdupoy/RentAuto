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

/**
 *
 * @author Simon
 */
@Entity
public class Maintenance extends Event {
    //----------------------//
    //      Attributes      //
    //----------------------//
    
    @ManyToOne(optional=false)
    @JoinColumn(name="CAR_ID",referencedColumnName="CAR_ID")
    private Car car; 
    
    private String title;

    //----------------------//
    //      Methods         //
    //----------------------//
    
    /**
     * Default constructor (empty)
     */
    public Maintenance() {
        super();
    }
    
    /**
     * Constructor with dates
     * @param start the date of the beginning of the maintenance
     * @param end the end date
     */
    public Maintenance(Date start, Date end){
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
     * @param car new value of car
     */
    public void setCar(Car car) {
        this.car = car;
    }
    
    
    /**
     * Get the value of title
     *
     * @return the value of title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the value of title
     *
     * @param title new value of title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * toString function 
     * @return a string containing all attributes of Car class
     */
    @Override
    public String toString() {
        return "Maintenance{" + "id=" + id + ", car=" + car + ", startDate=" + startDate + ", endDate=" + endDate + '}';
    }

}
