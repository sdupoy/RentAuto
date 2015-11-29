/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sdupoy.fp.service;

import edu.iit.sat.itmd4515.sdupoy.fp.domain.Agency;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Agent;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Car;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Client;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Manager;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Reservation;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.security.Group;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.security.User;
import java.util.GregorianCalendar;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Simon
 */
@Singleton
@Startup
public class StartUpBean {
    @PersistenceContext(name="sdupoyPU")
    EntityManager em;
    
    @EJB ClientService clientService;
    @EJB AgencyService agencyService;
    @EJB CarService carService;
    @EJB EmployeeService employeeService;
    @EJB ReservationService resaService;
    
    /**
     * Populating the database with a startup bean
     */
    public StartUpBean() {
    }

    @PostConstruct
    private void dataSetup(){
        // This will populate the database with entities with relations between them, so we are sure that mapping will be good
        
        Group agents = new Group("agents", "This group is for agents only");
        Group managers = new Group("managers", "This group is for managers only");
        Group clients = new Group("clients", "This group is for clients only");
        Group admins = new Group("admins", "This group is for administrators only");
        em.persist(agents);
        em.persist(managers);
        em.persist(clients);
        em.persist(admins);
        
        User admin = new User("admin", "admin");
        admin.addUserToGroup(admins);
        em.persist(admin);
        
        Agency agency1 = new Agency();
        agency1.setLocation("Chicago");
        Agency agency2 = new Agency();
        agency2.setLocation("Paris Charles De Gaulle");
        
        Agent agent1 = new Agent();
        agent1.setFirstName("Bob");
        agent1.setLastName("Dylan");
        agent1.setAddress("33 State St. Chicago, IL");
        agent1.setSsn(123456798L);
        agent1.setUser(new User("agent1", "aOne"));
        agent1.setAgency(agency1);
        agency1.addEmployee(agent1);
        
        Agent agent2 = new Agent();
        agent2.setFirstName("John");
        agent2.setLastName("Lenon");
        agent2.setAddress("33 Michigan Avenue Chicago, IL");
        agent2.setSsn(987654321L);
        agent2.setUser(new User("agent2", "aTwo"));
        agent2.setAgency(agency2);
        agency2.addEmployee(agent2);
        
        Agent agent3 = new Agent();
        agent3.setFirstName("Keith");
        agent3.setLastName("Richards");
        agent3.setAddress("33 Wells St. Chicago, IL");
        agent3.setSsn(465789132L);
        agent3.setUser(new User("agent3", "aThree"));
        agent3.setAgency(agency1);
        agency1.addEmployee(agent3);
        
        Manager manager1 = new Manager();
        manager1.setFirstName("Sylvester");
        manager1.setLastName("Stallone");
        manager1.setAddress("1 Adams St. Chicago, IL");
        manager1.setSsn(369258147L);
        manager1.setUser(new User("manager1", "manaOne"));
        manager1.setAgency(agency1);
        agency1.addEmployee(manager1);
        
        Client cl1 = new Client("Pau", "Gasol");
        cl1.setBillingAddress("3 Monroe St. Chicago, IL");
        cl1.setMailAddress("pgasol@bulls.com");
        cl1.setUser(new User("client1", "clOne"));
        Reservation r1 = new Reservation(new GregorianCalendar(2014, 12, 10).getTime(), new GregorianCalendar(2014, 12, 12).getTime());
        cl1.addReservation(r1);
        r1.setClient(cl1);
        resaService.create(r1);
        
        cl1.getUser().addUserToGroup(clients);
        clientService.create(cl1);
       
        agent1.getUser().addUserToGroup(agents);
        agent2.getUser().addUserToGroup(agents);
        agent3.getUser().addUserToGroup(agents);
        manager1.getUser().addUserToGroup(managers);
        
        employeeService.create(agent1);
        employeeService.create(agent2);
        employeeService.create(agent3);
        employeeService.create(manager1);
        
        Car c1 = new Car("132 465", "Toyata", "Corolla", 0L);
        c1.setAgency(agency1);
        agency1.addCar(c1);
        carService.create(c1);
        Car c2 = new Car("465 465", "Porsche", "Cayenne S", 27583L, "GPS, Bluetooth");
        c2.setAgency(agency1);
        agency1.addCar(c2);
        carService.create(c2);
        Car c3 = new Car("132 132", "Audi", "A5", 12365L);
        c3.setAgency(agency2);
        agency2.addCar(c3);
        carService.create(c3);
        
        
        
        agencyService.create(agency1);
        agencyService.create(agency2);
    }
}
