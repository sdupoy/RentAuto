/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sdupoy.fp.domain;

import edu.iit.sat.itmd4515.sdupoy.fp.domain.security.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Simon
 */
@Entity
@NamedQueries({
        @NamedQuery(name="Client.findByLastName", query="select c from Client c where c.lastName = :lname"),
        @NamedQuery(name="Client.findByUsername", query="select c from Client c where c.user.username = :uname"),
        @NamedQuery(name="Client.findAll", query ="select c from Client c")
})
public class Client implements Serializable, Comparable {
    //----------------------//
    //      Attributes      //
    //----------------------//
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLIENT_ID", nullable = false)
    private Long id;
    private String firstName;
    private String lastName;
    private String mailAddress;
    private String billingAddress;
    
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="username")
    private User user;
    
    @OneToMany(mappedBy = "client")
    private List<Reservation> reservations = new ArrayList<>();
    
    @OneToMany(mappedBy = "client")
    private List<Bill> bills = new ArrayList<>();
    
    //----------------------//
    //      Methods         //
    //----------------------//
    /**
     * Default constructor (empty)
     */
    public Client() {
    }
    
    /**
     * Constructor with basic client info
     * @param fname client's first name
     * @param lname client's last name
     */
    public Client(String fname, String lname){
        this.firstName = fname;
        this.lastName = lname;
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
     * Get the value of firstName
     * @return the value of firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the value of firstName
     * @param firstName new value of firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the value of lastName
     * @return the value of lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the value of lastName
     * @param lastName new value of lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the value of user
     *
     * @return the value of user
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the value of user
     *
     * @param user new value of user
     */
    public void setUser(User user) {
        this.user = user;
    }
    
    /**
     * Get the value of mailAddress
     * @return the value of mailAddress
     */
    public String getMailAddress() {
        return mailAddress;
    }

    /**
     * Set the value of mailAddress
     * @param mailAddress new value of mailAddress
     */
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    

    /**
     * Get the value of billingAddress
     * @return the value of billingAddress
     */
    public String getBillingAddress() {
        return billingAddress;
    }

    /**
     * Set the value of billingAddress
     * @param billingAddress new value of billingAddress
     */
    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
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
     * Helper to add a reservation for a client
     * @param r a reservation to add
     */
    public void addReservation(Reservation r){
        if(!this.reservations.contains(r)){
            this.reservations.add(r);
        }
        r.setClient(this);
    }
    
    /**
     * Helper to cancel a reservation for a client
     * @param r the reservation to cancel
     */
    public void cancelReservation(Reservation r){
        if(this.reservations.contains(r)){
            this.reservations.remove(r);
        }
        r.setClient(null);
    }

    /**
     * Get the value of bills
     * @return the value of bills
     */
    public List<Bill> getBills() {
        return bills;
    }

    /**
     * Set the value of bills
     * @param bills new value of bills
     */
    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }
    
    /**
     * Helper to add a bill for a client
     * @param b a bill to add
     */
    public void addBill(Bill b){
        if(!this.bills.contains(b)){
            this.bills.add(b);
        }
        b.setClient(this);
    }
    
    /**
     * Helper to delete a bill for a client
     * @param b the bill to delete
     */
    public void deleteBill(Bill b){
        if(this.bills.contains(b)){
            this.bills.remove(b);
        }
        b.setClient(null);
    }
    
    /**
     * toString function 
     * @return a string containing all attributes of Client class
     */
    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", mailAddress=" + mailAddress + ", billingAddress=" + billingAddress + '}';
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
