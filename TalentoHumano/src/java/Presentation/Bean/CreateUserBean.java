/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import javax.faces.bean.ManagedBean;
import BusinessLogic.Controller.HandleUser;
import BusinessLogic.Controller.HandleContract;
import DataAccess.DAO.ContractDAO;
import DataAccess.DAO.PositionDAO;
import DataAccess.DAO.UserDAO;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Alejandro
 */
@ViewScoped
@ManagedBean(name = "createUserBean")
public class CreateUserBean {

    String name;
    String lastname;
    Date dateBorn;
    String address;
    long phone;
    String email;
    String username;
    String password1;
    String password2;
    String message;
    String role;
    long identifyCard;
    String trainingLevel;
    int contractSalary;
    String contractType;
    Date contractFinalDate;
    Date contractStartDate;
    String healthEnterprise;
    Date startHealth;
    String pensionEnterprise;
    Date startPension;
    String contractMessage;
    int contractPosition;
    @EJB
    private UserDAO userDAO;
    @EJB
    ContractDAO contractDAO;
    @EJB
    PositionDAO positionDAO;

    public Date getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(Date contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public String getHealthEnterprise() {
        return healthEnterprise;
    }

    public void setHealthEnterprise(String healthEnterprise) {
        this.healthEnterprise = healthEnterprise;
    }

    public Date getStartHealth() {
        return startHealth;
    }

    public void setStartHealth(Date startHealth) {
        this.startHealth = startHealth;
    }

    public String getPensionEnterprise() {
        return pensionEnterprise;
    }

    public void setPensionEnterprise(String pensionEnterprise) {
        this.pensionEnterprise = pensionEnterprise;
    }

    public Date getStartPension() {
        return startPension;
    }

    public void setStartPension(Date startPension) {
        this.startPension = startPension;
    }

    public long getIdentifyCard() {
        return identifyCard;
    }

    public void setIdentifyCard(long identifyCard) {
        this.identifyCard = identifyCard;
    }

    public int getContractPosition() {
        return contractPosition;
    }

    public void setContractPosition(int contractPosition) {
        this.contractPosition = contractPosition;
    }

    public String getTrainingLevel() {
        return trainingLevel;
    }

    public void setTrainingLevel(String trainingLevel) {
        this.trainingLevel = trainingLevel;
    }

    public String getContractMessage() {
        return contractMessage;
    }

    public void setContractMessage(String contractMessage) {
        this.contractMessage = contractMessage;
    }

    public int getContractSalary() {
        return contractSalary;
    }

    public void setContractSalary(int contractSalary) {
        this.contractSalary = contractSalary;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public Date getContractFinalDate() {
        return contractFinalDate;
    }

    public void setContractFinalDate(Date contractFinalDate) {
        this.contractFinalDate = contractFinalDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDateBorn() {
        return dateBorn;
    }

    public void setDateBorn(Date dateBorn) {
        this.dateBorn = dateBorn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public void createUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println(startHealth);
        System.out.println(startPension);
        if (contractType.equals("Indefinido") && contractFinalDate != null) {
            message = "El usuario no pudo ser creado pues el contrato es indefinido pero tiene fecha de finalizacion";
            contractMessage = "No se pudo crear el contrato porque es indefinido pero tiene fecha de finalizacion";
        } else {
            HandleUser createUser = new HandleUser();
            String messageTest = createUser.doCreate(userDAO, name, lastname, dateBorn, address, trainingLevel, Long.toString(phone), email, username, password1, password2, role, Long.toString(identifyCard));
            if (messageTest.charAt(0) != 'U') {
                message = messageTest;
                contractMessage = "El contrato no pudo ser creado. La creacion de usuario fallo.";
            } else {
                message = messageTest.split("/")[0];
                int userId = Integer.parseInt(messageTest.split("/")[1]);
                HandleContract createContract = new HandleContract();
                contractMessage = createContract.doCreate(contractDAO,positionDAO,contractSalary, contractType, contractStartDate, contractFinalDate, healthEnterprise, startHealth, pensionEnterprise, startPension, userId, name, contractPosition);
                name = null;
                lastname = null;
                dateBorn = null;
                address = null; 
                phone = 0;
                email = null;
                username = null;
                password1 = null;
                password2 = null;
                identifyCard = 0;
                contractSalary = 0;
                contractFinalDate = null;
                contractStartDate = null;
                healthEnterprise = null;
                startHealth = null;
                pensionEnterprise = null;
                startPension = null;
            }
            context.addMessage(null, new FacesMessage("Usuario", message));
            context.addMessage(null, new FacesMessage("Contrato", contractMessage));
        }
    }
    
}
