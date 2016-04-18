/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.NotificationDAO;
import DataAccess.Entity.Notifications;
import DataAccess.Entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Edwin
 */
public class HandleNotifications {
    
    public void getNotifications(){
        NotificationDAO nDAO = new NotificationDAO();
        List<Notifications> lista = nDAO.searchAll();
        List<Notifications> nueva = new ArrayList<>();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        User us = new User((Integer)ec.getSessionMap().get("userId"));
        for( Notifications noti : lista ){
            if( noti.getFkuserID().equals(us)){
                nueva.add(noti);
            }
        }
        ec.getSessionMap().put("notificationCertificationsList", nueva);
        }
    
}
