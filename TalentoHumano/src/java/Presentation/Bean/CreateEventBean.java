/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import BusinessLogic.Controller.HandleEvent;

/**
 *
 * @author Alejandro
 */
@ManagedBean
@ViewScoped
public class CreateEventBean {

    String EventName;
    String ContractFilter;
    String message;

    /**
     * @return the EventName
     */
    public String getEventName() {
        return EventName;
    }

    /**
     * @param EventName the EventName to set
     */
    public void setEventName(String EventName) {
        this.EventName = EventName;
    }

    /**
     * @return the ContractFilter
     */
    public String getContractFilter() {
        return ContractFilter;
    }

    /**
     * @param ContractFilter the ContractFilter to set
     */
    public void setContractFilter(String ContractFilter) {
        this.ContractFilter = ContractFilter;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public void createEvent() {
        HandleEvent handleEvent=new HandleEvent();
        setMessage(handleEvent.doEvent(EventName, ContractFilter));
    }
}
