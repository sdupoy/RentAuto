/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sdupoy.fp.service;

import edu.iit.sat.itmd4515.sdupoy.fp.domain.Bill;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Simon
 */
@Stateless
public class BillService extends AbstractService<Bill>{

    /**
     * Constructor of the superclass
     */
    public BillService() {
        super(Bill.class);
    }

    /**
     * Overriding the findAll of the superclass
     * @return  the list of all bills
     */
    @Override
    public List<Bill> findAll() {
        return em.createNamedQuery("Bill.findAll", Bill.class).getResultList();
    }
}
