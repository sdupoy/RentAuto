/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sdupoy.service;

import edu.iit.sat.itmd4515.sdupoy.domain.Reservation;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Simon
 */
@Stateless
public class ReservationService extends AbstractService<Reservation>{

    /**
     * Constructor of the superclass
     */
    public ReservationService() {
        super(Reservation.class);
    }

    /**
     * Overriding the supermethod
     * @return the list of reservations
     */
    @Override
    public List<Reservation> findAll() {
        return em.createNamedQuery("Bill.findAll", Reservation.class).getResultList();
    }
}
