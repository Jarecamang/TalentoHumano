/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.SOAPService;

import java.util.List;

/**
 *
 * @author arqsoft2016
 */
public class Course {

    private List<BusinessLogic.SOAPService.User> users;
    private Integer id;
    private String description;

    /**
     * @return the users
     */
    public List<BusinessLogic.SOAPService.User> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<BusinessLogic.SOAPService.User> users) {
        this.users = users;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public String getStringId() {
        return id.toString();
    }

    public Course(List<User> users, Integer id, String description) {
        this.users = users;
        this.id = id;
        this.description = description;
    }
}
