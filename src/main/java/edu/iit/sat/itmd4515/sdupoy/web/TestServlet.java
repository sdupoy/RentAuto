/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sdupoy.web;

import edu.iit.sat.itmd4515.sdupoy.domain.Agency;
import edu.iit.sat.itmd4515.sdupoy.domain.Agent;
import edu.iit.sat.itmd4515.sdupoy.domain.Car;
import edu.iit.sat.itmd4515.sdupoy.domain.Client;
import edu.iit.sat.itmd4515.sdupoy.domain.Employee;
import edu.iit.sat.itmd4515.sdupoy.domain.Manager;
import edu.iit.sat.itmd4515.sdupoy.domain.Reservation;
import edu.iit.sat.itmd4515.sdupoy.service.AgencyService;
import edu.iit.sat.itmd4515.sdupoy.service.ClientService;
import edu.iit.sat.itmd4515.sdupoy.service.EmployeeService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Simon
 */
@WebServlet(name = "TestServlet", urlPatterns = {"/mp3/TestServlet"})
public class TestServlet extends HttpServlet {

    
    @EJB
    ClientService clientService;
    
    @EJB
    EmployeeService employeeService;
    
    @EJB
    AgencyService agencyService;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TestServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            out.println("<h1>Servlet TestServlet at " + request.getContextPath() + "</h1>");
            
            if (request.isUserInRole("AGENT")) {
                Agent a = employeeService.findAgentByUsername(request.getRemoteUser());
                out.println("<h1>Welcome agent: " +a.getFirstName() + " " + a.getLastName() + "</h1>");
                out.println("<p>Your address: " + a.getAddress() + "</p>");
                out.println("<p>Your SSN: " + a.getSsn() + "</p>");
                out.println("<p>Your agency: " + a.getAgency().getLocation() + "</p>");
                String listOfCars ="";
                for(Car c : a.getAgency().getCars()){
                    listOfCars += c.toString();
                    listOfCars += "<br>";
                }
                out.println("<h2>Car in your agency: </h2><p>" + listOfCars + "</p>");
            } else if (request.isUserInRole("MANAGER")) {
                Manager m = employeeService.findManagerByUsername(request.getRemoteUser());
                out.println("<h1>Welcome manager: " + m.getFirstName() + " " + m.getLastName() + "</h1>");
                out.println("<p>Your address: " + m.getAddress() + "</p>");
                out.println("<p>Your SSN: " + m.getSsn() + "</p>");
                out.println("<p>Your agency: " + m.getAgency().getLocation() + "</p>");
                String listOfEmployees ="";
                for(Employee e : m.getAgency().getEmployees()){
                    listOfEmployees += e.toString();
                    listOfEmployees += "<br>";
                }
                out.println("<h2>Employees in your agency: </h2><p>" + listOfEmployees + "</p>");
                String listOfCars ="";
                for(Car c : m.getAgency().getCars()){
                    listOfCars += c.toString();
                    listOfCars += "<br>";
                }
                out.println("<h2>Car in your agency: </h2><p>" + listOfCars + "</p>");
            } else if (request.isUserInRole("CLIENT")) {
                Client cl = clientService.findByUsername(request.getRemoteUser());
                out.println("<h1>Welcome Client: " + cl.getFirstName() + " " + cl.getLastName() + "</h1>");
                String listOfReservations ="";
                for(Reservation r : cl.getReservations()){
                    listOfReservations += r.toString();
                    listOfReservations += "<br>";
                }
                out.println("<h2>Reservations you made: </h2><p>" + listOfReservations + "</p>");
            } else {
                out.println("<h1>You are not in any roles.  Who are you?  Where am I?  What day is it?</h1>");
            }
            out.println("<a href=\"" + request.getContextPath() + "/mp3/LogOut\">Logout</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
