/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import BusinessLogic.Controller.HandleContract;
import java.util.Date;

/**
 *
 * @author Alejandro
 */
@ManagedBean
@ViewScoped
public class RenewContractBean {
    
    int contractSalary;
    String contractType;
    Date contractFinalDate;
    String contractMessage;
    int contractPosition;
    long doccumentUser;

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

    public String getContractMessage() {
        return contractMessage;
    }

    public void setContractMessage(String contractMessage) {
        this.contractMessage = contractMessage;
    }

    public int getContractPosition() {
        return contractPosition;
    }

    public void setContractPosition(int contractPosition) {
        this.contractPosition = contractPosition;
    }

    public long getDoccumentUser() {
        return doccumentUser;
    }

    public void setDoccumentUser(long doccumentUser) {
        this.doccumentUser = doccumentUser;
    }
    
    public void renewContract(){
        
        HandleContract createContract = new HandleContract();
        
        
        
        contractMessage = createContract.renewContract(contractSalary,contractType,contractFinalDate,doccumentUser,contractPosition);
        
    }
    
}
