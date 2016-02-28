/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import BusinessLogic.Controller.HandleUser;
import BusinessLogic.Controller.HandleContract;
import java.util.Date;
/**
 *
 * @author Alejandro
 */
@ManagedBean
@ViewScoped
public class createUserBean {
    
    String name;
    String lastname;
    int age;
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
    String contractMessage;
    int contractPosition;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
    public void createUser(){   
        HandleUser createUser = new HandleUser();
        String messageTest = createUser.doCreate(name, lastname, age, address, trainingLevel, Long.toString(phone), email, username, password1, password2, role, Long.toString(identifyCard));
        message = messageTest.split("/")[0];
        int userId = Integer.parseInt(messageTest.split("/")[1]);
        HandleContract createContract = new HandleContract();
        contractMessage = createContract.doCreate(contractSalary,contractType,contractFinalDate,userId, name,contractPosition);
    }
}
