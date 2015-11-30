/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sdupoy.fp.web;

import edu.iit.sat.itmd4515.sdupoy.fp.domain.Car;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Client;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Reservation;
import edu.iit.sat.itmd4515.sdupoy.fp.service.CarService;
import edu.iit.sat.itmd4515.sdupoy.fp.service.ClientService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Simon
 */
@Named
@RequestScoped
public class ClientController extends AbstractController {
    @EJB CarService carService;
    @EJB ClientService clientService;
    @Inject LoginController loginController;
    
    private List<Car> cars;
    private Car car;
    private List<Reservation> reservations;
    private Reservation reservation;
    private Client client;


    public ClientController() {
    }
    
    
    @Override
    @PostConstruct
    protected void postConstruct() {
        client = clientService.findByUsername(loginController.getRemoteUser());
        super.postConstruct();
        
    }

    public String doHelp() {
        return "help";
    }
    
    public String doSettings() {
        return "settings";
    }
}
