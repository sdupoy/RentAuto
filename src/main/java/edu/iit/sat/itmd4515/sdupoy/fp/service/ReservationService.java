/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sdupoy.fp.service;

import edu.iit.sat.itmd4515.sdupoy.fp.domain.Client;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Reservation;
import java.sql.Date;
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
        return em.createNamedQuery("Reservation.findAll", Reservation.class).getResultList();
    }
    
    /**
     * Find all reservations made by a client
     * @param client
     * @return
     */
    public List<Reservation> findByClient(Client client) {
        return em.createNamedQuery("Reservation.findByClient", Reservation.class).setParameter("client", client).getResultList();
    }
    
    /**
     * Find all upcoming reservations
     * @param date
     * @return
     */
    public List<Reservation> findUpcoming(Date date) {
        return em.createNamedQuery("Reservation.findUpcoming", Reservation.class).setParameter("date", date).getResultList();
    }
    
    /**
     * Find all current reservations
     * @param date
     * @return
     */
    public List<Reservation> findCurrent(Date date) {
        return em.createNamedQuery("Reservation.findCurrent", Reservation.class).setParameter("date", date).getResultList();
    }

    /**
     * Find all past reservations
     * @param date
     * @return
     */
    public List<Reservation> findPast(Date date) {
        return em.createNamedQuery("Reservation.findPast", Reservation.class).setParameter("date", date).getResultList();
    }
    
}
