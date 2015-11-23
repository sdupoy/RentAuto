/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sdupoy.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Simon
 */
@Entity
@DiscriminatorValue(value = "Manager")
public class Manager extends Employee {

    /**
     * Empty constructor
     */
    public Manager() {
    }
   
}
