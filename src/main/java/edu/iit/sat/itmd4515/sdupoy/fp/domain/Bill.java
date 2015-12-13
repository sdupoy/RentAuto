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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Simon
 */
@Entity
public class Bill implements Serializable, Comparable {
    //----------------------//
    //      Attributes      //
    //----------------------//
    @Id @GeneratedValue
    @Column(name = "BILL_ID", nullable = false)
    protected Long id;
    private Boolean isPaid;
    private float amount;

    @ManyToOne(optional=false)
    @JoinColumn(name="CLIENT_ID",referencedColumnName="CLIENT_ID")
    private Client client;

    @Temporal(TemporalType.DATE)
    private Date billDate;
   
    //----------------------//
    //      Methods         //
    //----------------------//
    
    /**
     * Default constructor (empty)
     */
    public Bill() {
    }
    
    /**
     * Get the value of id
     * @return the value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the value of id
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    
    
    /**
     * Get the value of isPaid
     * @return the value of isPaid, true if the bill has been paid, false if not.
     */
    public Boolean getIsPaid() {
        return isPaid;
    }

    /**
     * Set the value of isPaid, true if the bill has been paid, false if not.
     * @param isPaid new value of isPaid
     */
    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }
    
    /**
     * Get the value of amount
     * @return the value of amount
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Set the value of amount
     * @param amount new value of amount
     */
    public void setAmount(float amount) {
        this.amount = amount;
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
     * Get the value of billDate
     * @return the value of billDate
     */
    public Date getBillDate() {
        return billDate;
    }

    /**
     * Set the value of billDate
     * @param billDate new value of billDate
     */
    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }
    
    /**
     * Set the value of billDate
     */
    @PrePersist
    @PreUpdate
    public void setEditingDate() {
        this.billDate = new Date();
    }

    /**
     * toString function 
     * @return a string containing all attributes of Car class
     */
    @Override
    public String toString() {
        return "Bill{" + "id=" + id + ", isPaid=" + isPaid + ", amount=" + amount + ", client=" + client + ", billDate=" + billDate + '}';
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
