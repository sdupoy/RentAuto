/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sdupoy.fp.domain.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Simon
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "User.findByUsername", query = "select u from User u where u.username = :search")
})
@Table(name = "sec_user")
public class User implements Serializable {
    @Id
    private String username;
    private String password;
    
    @ManyToMany
    @JoinTable(name="sec_user_group",
            joinColumns = @JoinColumn(name ="username"),
            inverseJoinColumns = @JoinColumn(name="groupname"))
    List<Group> userGroups = new ArrayList<>();
    
    /**
     * Empty constructor
     */
    public User() {
    }

    /**
     * create a new user with user and password
     * @param username
     * @param password
     */
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
    
    @PrePersist
    @PreUpdate
    private void hashPassword(){
        String digestPassword = DigestUtils.sha256Hex(this.password);
        this.password = digestPassword;
    }

    /**
     * Add the user to a group
     * @param g
     */
    public void addUserToGroup(Group g){
        if(!this.userGroups.contains(g)){
            this.userGroups.add(g);
        }
        if(!g.getGroupMembers().contains(this)){
            g.getGroupMembers().add(this);
        }
    }
    
    /**
     * Get all the groups that the user belongs to
     * @return list of groups
     */
    public List<Group> getUserGroups(){
        return this.userGroups;
    }
    
    /**
     * Set the list of all groups that a user belongs to
     * @param userGroups
     */
    public void setUserGroups(List<Group> userGroups){
        this.userGroups = userGroups;
    }
    
    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the value of username
     *
     * @return the value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the value of username
     *
     * @param username new value of username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + ", userGroups=" + userGroups + '}';
    }

    
}
