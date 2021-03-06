/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sdupoy.fp.web;

import edu.iit.sat.itmd4515.sdupoy.fp.domain.Agency;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Bill;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Car;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Client;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Employee;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Maintenance;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.Reservation;
import edu.iit.sat.itmd4515.sdupoy.fp.domain.security.User;
import edu.iit.sat.itmd4515.sdupoy.fp.service.CarService;
import edu.iit.sat.itmd4515.sdupoy.fp.service.ClientService;
import edu.iit.sat.itmd4515.sdupoy.fp.service.MaintenanceService;
import edu.iit.sat.itmd4515.sdupoy.fp.service.EmployeeService;
import edu.iit.sat.itmd4515.sdupoy.fp.service.ReservationService;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Simon
 */
@ManagedBean(name = "employeeController")
@RequestScoped
public class EmployeeController extends AbstractController {

    private static final Logger LOG = Logger.getLogger(EmployeeController.class.getName());

    @EJB
    CarService carService;
    @EJB
    EmployeeService employeeService;
    @EJB
    MaintenanceService maintenanceService;
    @EJB
    ReservationService reservationService;
    @EJB
    ClientService clientService;

    @ManagedProperty(value = "#{loginController}")
    private LoginController loginController;

    private List<Car> cars;
    private Car car;
    private List<Maintenance> maintenances;
    private Maintenance maintenance;
    private List<Reservation> reservations;
    private Reservation reservation;
    private List<Bill> bills;
    private Bill bill;
    private List<Client> clients;
    private Client client;
    private Employee employee;
    private Agency agency;
    private User user;
    private String search;

    /**
     * Default constructor (empty)
     */
    public EmployeeController() {
    }

    /**
     * Overriding the post construct
     */
    @Override
    @PostConstruct
    protected void postConstruct() {
        car = new Car();
        user = new User();
        client = new Client();
        maintenance = new Maintenance();
        maintenances = new ArrayList<>();
        reservations = new ArrayList<>();
        bills = new ArrayList<>();
        employee = employeeService.findEmployeeByUsername(loginController.getRemoteUser());
        agency = employee.getAgency();
        search = new String();
        refreshCars();
        refreshClients();
        super.postConstruct();
    }

    /**
     * updating the list of cars (and their associated maintenances)
     */
    private void refreshCars() {
        cars = employeeService.findEmployeeByUsername(loginController.getRemoteUser()).getAgency().getCars();
        for (Car car1 : cars) {
            for (Maintenance maintenance1 : car1.getMaintenances()) {
                maintenances.add(maintenance1);
            }
        }
    }

    /**
     * updating the list of clients
     */
    private void refreshClients() {
        clients = clientService.findAll();
    }

    /**
     * Sending to the help page
     *
     * @return help page
     */
    public String doHelp() {
        return "help" + FACES_REDIRECT;
    }

    /**
     * Sending to the settings page
     *
     * @return settings page
     */
    public String doSettings() {
        return "settings" + FACES_REDIRECT;
    }

    /**
     * Prepare a new car to be created by the user
     *
     * @return the page on which the new car will be created
     */
    public String doCreateCar() {
        car = new Car();
        return "carCreation" + FACES_REDIRECT;
    }

    /**
     * Handle the action from newCar view
     *
     * @return the home page of car management
     */
    public String executeCreateCar() {
        carService.create(car, employee.getAgency());
        refreshCars();
        return "carManagementHome" + FACES_REDIRECT;
    }

    /**
     * Show car details
     *
     * @param car
     * @return car details display
     */
    public String doShowCar(Car car) {
        this.car = car;
        this.maintenances = car.getMaintenances();
        return "carDisplay";
    }

    /**
     * preparing to update a car
     *
     * @param car
     * @return the page where the car will be edited
     */
    public String doUpdateCar(Car car) {
        LOG.info("preparing update ");
        LOG.info(car.toString());
        this.car = car;
        return "carEdition";
    }

    /**
     * handle the action of updating a car
     *
     * @return a partial home page
     */
    public String executeUpdateCar() {
        LOG.info("executing update ");
        LOG.info(car.toString());
        carService.update(car);
        refreshCars();
        return "carManagementHome" + FACES_REDIRECT;
    }

    /**
     * Delete a car
     *
     * @param car
     * @return a partial home page
     */
    public String doDeleteCar(Car car) {
        LOG.info("executing deletion ");
        LOG.info(car.toString());
        carService.delete(car, agency);
        refreshCars();
        return "carManagementHome" + FACES_REDIRECT;
    }

    /**
     * Delete a maintenance
     *
     * @param maintenance
     * @return a partial home page
     */
    public String doDeleteMaintenance(Maintenance maintenance) {
        LOG.info("Preparing to delete ");
        LOG.info(maintenance.toString());
        this.car = maintenance.getCar();
        maintenanceService.deleteMaintenance(maintenance, car);
        return "carMaintenanceHome" + FACES_REDIRECT;
    }

    /**
     * Preparing to add a maintenance
     *
     * @param car
     * @return the page where maitnenance details will be entered
     */
    public String doMaintainCar(Car car) {
        LOG.info("Preparing to add a new maintenance on ");
        LOG.info(car.toString());
        this.car = car;
        this.maintenance = new Maintenance();
        return "carMaintenance";
    }

    /**
     * handle the creation of a maintenance
     *
     * @return a partial home page
     */
    public String executeCreateMaintenance() {
        LOG.info("Creating a new maintenance on ");
        LOG.info(car.toString());
        maintenanceService.addMaintenance(car, maintenance);
        return "carMaintenanceHome" + FACES_REDIRECT;
    }

    /**
     * preparing to update a maintenance
     *
     * @param maintenance
     * @return page of maintenance edition
     */
    public String doUpdateMaintenance(Maintenance maintenance) {
        LOG.info("Preparing to update ");
        LOG.info(maintenance.toString());
        this.maintenance = maintenance;
        return "carMaintenanceEdition";
    }

    /**
     * Handle the edition of the maintenance
     *
     * @return a partial home page
     */
    public String executeUpdateMaintenance() {
        maintenanceService.updateMaintenance(maintenance);
        return "carMaintenanceHome" + FACES_REDIRECT;
    }

    /**
     * Searching a car by its license plate
     *
     * @return the car details page
     */
    public String searchCarByLP() {
        LOG.info("Preparing to search " + this.search);
        this.car = carService.findByLicensePlate(this.search);
        LOG.info("Search returned " + car.toString());
        this.maintenances = car.getMaintenances();
        return "carDisplay";
    }

    /**
     * Searching cars by its maker
     *
     * @return the results displays
     */
    public String searchCarByMaker() {
        LOG.info("Preparing to search " + this.search);
        this.cars = carService.findByMaker(search);
        LOG.info("Search returned " + cars.toString());
        return "carSearchResult";
    }

    /**
     * Searching cars by its model
     *
     * @return the results displays
     */
    public String searchCarByModel() {
        LOG.info("Preparing to search " + this.search);
        this.cars = carService.findByModel(search);
        LOG.info("Search returned " + cars.toString());
        return "carSearchResult";
    }

    /**
     * Searching cars by its options
     *
     * @return the results displays
     */
    public String searchCarByOptions() {
        LOG.info("Preparing to search " + this.search);
        this.cars = carService.findByOptions(search);
        LOG.info("Search returned " + cars.toString());
        return "carSearchResult";
    }

    /**
     * Prepare a new client to be created by the user
     *
     * @return the page on which the new client will be created
     */
    public String doCreateClient() {
        LOG.info("Preparing to create a client ");
        client = new Client();
        return "clientCreation" + FACES_REDIRECT;
    }

    /**
     * Handle the creation of a client
     *
     * @return a partial home page
     */
    public String executeCreateClient() {
        client.setUser(new User(user.getUsername(), user.getPassword()));
        LOG.info("Create a new client" + this.client.toString() + "associated with " + this.user.toString());
        clientService.createNewClient(client);
        refreshClients();
        return "clientManagementHome" + FACES_REDIRECT;
    }

    /**
     * Searching a client by its name
     *
     * @return page of results display
     */
    public String searchClientByName() {
        LOG.info("Preparing to search " + this.search);
        this.clients = clientService.findByName(search);
        LOG.info("Search returned " + clients.toString());
        return "clientSearchResult";
    }

    /**
     * Show a client details
     *
     * @param client
     * @return page of client details
     */
    public String doShowClient(Client client) {
        this.client = client;
        LOG.info("Preparing to show " + this.client.toString());
        LOG.info("Preparing to show " + this.client.getReservations().toString());
        LOG.info("Preparing to show " + this.client.getBills().toString());
        return "clientDisplay";
    }

    /**
     * Prepare a new reservation to be created by the user
     *
     * @return the page on which the new reservation will be created
     */
    public String doCreateReservation() {
        LOG.info("Preparing to create a reservation ");
        reservation = new Reservation();
        return "reservationCreation" + FACES_REDIRECT;
    }

    /**
     * Handle the creation of a new reservation
     *
     * @return a partial home page
     */
    public String executeCreateReservation() {
        reservation.setCar(car);
        reservation.setClient(client);
        LOG.info("Creating the reservation " + this.reservation.toString());
        LOG.info("By " + this.reservation.getClient().toString());
        LOG.info("On " + this.reservation.getCar().toString());

        reservationService.create(reservation);
        return "clientManagementHome" + FACES_REDIRECT;
    }

    /**
     * Find the curretn and upcoming event
     *
     * @return a partial home page
     */
    public String doClientManagementHome() {
        Date date = new Date(System.currentTimeMillis());
        LOG.info("Getting current reservations for the date" + date.toString());
        this.reservations = reservationService.findUpcoming(date);
        this.reservations.addAll(reservationService.findCurrent(date));
        LOG.info("Getting current reservations " + this.reservations.toString());
        return "clientManagementHome";
    }

    /**
     * Updating a client
     *
     * @param client
     * @return the page where the client is edited
     */
    public String doUpdateClient(Client client) {
        LOG.info("preparing update of client");
        LOG.info(client.toString());
        this.client = client;
        return "clientEdition";
    }

    /**
     * Handling the update
     *
     * @return client details display
     */
    public String executeUpdateClient() {
        LOG.info("executing update of client");
        LOG.info(client.toString());
        clientService.update(client);
        return "clientDisplay";
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Maintenance> getMaintenances() {
        return maintenances;
    }

    public void setMaintenances(List<Maintenance> maintenances) {
        this.maintenances = maintenances;
    }

    public Maintenance getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(Maintenance maintenance) {
        this.maintenance = maintenance;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

}
