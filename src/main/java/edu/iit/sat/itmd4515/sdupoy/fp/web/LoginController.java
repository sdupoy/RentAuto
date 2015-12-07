/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sdupoy.fp.web;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Simon
 */
@ManagedBean(name="loginController")
@RequestScoped
public class LoginController extends AbstractController{
    
    @NotNull(message="You must enter a username !")
    private String username;
    @NotNull(message = "You must enter your password !")
    private String password;

    
    public LoginController() {
    }
    
    @Override
    @PostConstruct
    protected void postConstruct(){
        super.postConstruct();
    }
    
    public String getRemoteUser(){
        return facesContext.getExternalContext().getRemoteUser();
    }
    
    public String doLogin(){
        HttpServletRequest req = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        try{
            req.login(username, password);
        } catch (ServletException ex){
            facesContext.addMessage(null, new FacesMessage("Login failed", "Wrong username or password !"));
            return "login.xhtml";
        }
        if(req.isUserInRole("AGENT")){
            return "/app/employee/agentHome.xhtml" + FACES_REDIRECT;
        } else if (req.isUserInRole("MANAGER")){
            return "/app/employee/managerHome.xhtml" + FACES_REDIRECT;
        } else if (req.isUserInRole("CLIENT")){
            return "/app/client/clientHome.xhtml" + FACES_REDIRECT;
        } else{
            return "error.xhtml" + FACES_REDIRECT;
        }
    }
    
    public String goHome(){
        HttpServletRequest req = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        if(req.isUserInRole("AGENT")){
            return "/app/employee/agentHome.xhtml" + FACES_REDIRECT;
        } else if (req.isUserInRole("MANAGER")){
            return "/app/employee/managerHome.xhtml" + FACES_REDIRECT;
        } else if (req.isUserInRole("CLIENT")){
            return "/app/client/clientHome.xhtml" + FACES_REDIRECT;
        } else{
            return "error.xhtml" + FACES_REDIRECT;
        }
        
    }
    
    public String doLogOut(){
        HttpServletRequest req = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        try{
            req.logout();
        } catch (ServletException ex){
            facesContext.addMessage(null, new FacesMessage("Logout failed"));
            return "login.xhtml" + FACES_REDIRECT;
        }
        return "/login.xhtml" + FACES_REDIRECT;
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

    
}
