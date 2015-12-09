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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Simon
 */
@Entity
@NamedQueries({
        @NamedQuery(name="Group.findGroup", query="select g from Group g where g.groupName = :groupname")
})
@Table(name = "sec_group")
public class Group implements Serializable {
    @Id
    private String groupName;
    private String groupDescription;
    
    @ManyToMany(mappedBy = "userGroups")
    List<User> users = new ArrayList<>();

    /**
     * Empty constructor
     */
    public Group() {
    }

    /**
     * create a new group with a group name and a group password
     * @param groupName
     * @param groupDescription
     */
    public Group(String groupName, String groupDescription) {
        this.groupName = groupName;
        this.groupDescription = groupDescription;
    }

    /**
     * Get all the uers that a group contains
     * @return list of users
     */
    public List<User> getGroupMembers(){
        return this.users;
    }
    
    /**
     * Set all the users that belong to a group
     * @param members
     */
    public void setGroupMembers(List<User> members){
        this.users = members;
    }
    
    /**
     * Get the value of groupDescription
     *
     * @return the value of groupDescription
     */
    public String getGroupDescription() {
        return groupDescription;
    }

    /**
     * Set the value of groupDescription
     *
     * @param groupDescription new value of groupDescription
     */
    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }


    /**
     * Get the value of groupName
     *
     * @return the value of groupName
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Set the value of groupName
     *
     * @param groupName new value of groupName
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}
