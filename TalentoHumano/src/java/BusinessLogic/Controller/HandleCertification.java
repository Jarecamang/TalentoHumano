/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.CertificationDAO;
import DataAccess.DAO.InterestAreaDAO;
import DataAccess.DAO.NotificationDAO;
import DataAccess.DAO.UserDAO;
import DataAccess.Entity.Areaofinterest;
import DataAccess.Entity.Certifications;
import DataAccess.Entity.Notifications;
import DataAccess.Entity.User;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Edwin
 */
public class HandleCertification { 
    
    public String createCertification(CertificationDAO ceDAO,UserDAO userDAO,InterestAreaDAO areaDAO,NotificationDAO notiDAO,String name){
        Certifications cert = new Certifications();
        Calendar fecha = new GregorianCalendar();
        int annio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        cert.setDate(new Date(annio-1900, mes, dia));
        cert.setName(name); 
        cert.setPlace("Departamento de Administracion");
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        cert.setFkuserID(new User((Integer) ec.getSessionMap().get("userId")));
        Certifications certObject = ceDAO.persist(cert);
        if ( certObject != null ){
             generateNotifications(userDAO,areaDAO,notiDAO,certObject.getPkID(), certObject.getName());
            return "La certificacion para el area "+name+" se creo satisfactoriamente";
        }else{
            return "La certificacion para el area "+name+" no pudo ser creada";
        }
        
    }
     
    public void generateNotifications(UserDAO usDAO,InterestAreaDAO areaDAO,NotificationDAO notiDAO,int idCertificacion, String nameCertification){
        Calendar fecha = new GregorianCalendar();
        int year = fecha.get(Calendar.YEAR);
        int month = fecha.get(Calendar.MONTH);
        int day = fecha.get(Calendar.DAY_OF_MONTH);
        List<User> userlist = usDAO.getAllUsers();
        List<Areaofinterest> areaslist = areaDAO.getAllAreasOfInterest();
        Notifications noti = new Notifications();
        for (User us : userlist){
            for (Areaofinterest area : areaslist) {
                if (area.getName().equals(nameCertification) && area.getUserSet().contains(us)) {
                    noti.setDate(new Date(year-1900, month, day)); 
                    noti.setDescription("El Departamento de Administracion ha creado una Certificacion para el area de : "+nameCertification);
                    noti.setFkcertificationID(new Certifications(idCertificacion));
                    noti.setFkuserID(new User(us.getPkID()));
                    Notifications notif = notiDAO.persist(noti);
                } 
            }
        }
    }
    
}
