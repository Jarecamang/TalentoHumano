/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import javax.faces.bean.ManagedBean;
import BusinessLogic.Controller.HandlePosition;
import javax.faces.bean.RequestScoped;
import DataAccess.Entity.Position;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alejandro
 */
public class DataBean {
    
    public void populateData(){
        HandlePosition positionObject = new HandlePosition();
        positionObject.getPositions();   
    }
}
