/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sdupoy.domain;

import edu.iit.sat.itmd4515.sdupoy.domain.security.User;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 *
 * @author Simon
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "Position")
@NamedQueries({
        @NamedQuery(name="Employee.findByLastName", query="select e from Employee e where e.lastName = :lname"),
        @NamedQuery(name="Employee.findByUsername", query="select e from Employee e where e.user.username = :uname"),
        @NamedQuery(name="Employee.findByAgency", query="select e from Employee e where e.agency.location = :location"),
        @NamedQuery(name="Employee.findByAgencyAndUsername", query="select e from Employee e where e.agency.location = :location and e.user.username = :uname")
})
public abstract class Employee implements Serializable, Comparable {
    //----------------------//
    //      Attributes      //
    //----------------------//
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMP_ID", nullable = false)
    private Long id;
    
    private Long ssn;
    private String firstName;
    private String lastName;
    private String address;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="username")
    private User user;
    
    @ManyToOne
    @JoinColumn(name="agency")
    private Agency agency;
    

    //----------------------//
    //      Methods         //
    //----------------------//
    /**
     * Default constructor (empty)
     */
    public Employee() {
    }
    
    
    /**
     * Get the value of id
     * @return the value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the value of ssn
     * @return the value of ssn
     */
    public Long getSsn() {
        return ssn;
    }

    /**
     * Set the value of ssn
     * @param ssn new value of ssn
     */
    public void setSsn(Long ssn) {
        this.ssn = ssn;
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
     * Get the value of address
     * @return the value of address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set the value of address
     * @param address new value of address
     */
    public void setAddress(String address) {
        this.address = address;
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
     * toString function 
     * @return a string containing all attributes of Car class
     */
    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", ssn=" + ssn + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + '}';
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
