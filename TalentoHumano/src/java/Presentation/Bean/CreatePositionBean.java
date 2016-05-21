/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import BusinessLogic.Controller.HandlePosition;
import DataAccess.DAO.PositionDAO;
import javax.ejb.EJB;
/**
 *
 * @author Alejandro
 */
@ManagedBean
@ViewScoped
public class CreatePositionBean {
    
    String positionName;
    String message;
    @EJB
    private PositionDAO positionDAO;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
    
    public void createPosition(){
        HandlePosition createPosition = new HandlePosition();
        message = createPosition.doCreate(positionDAO, positionName);
        
    }
    
}
