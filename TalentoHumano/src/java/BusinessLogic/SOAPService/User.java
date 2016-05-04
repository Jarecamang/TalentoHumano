/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.SOAPService;

/**
 *
 * @author arqsoft2016
 */
public class User {

    private Integer document;
    private String firstname;
    private String lastname;

    /**
     * @return the document
     */
    public Integer getDocument() {
        return document;
    }

    public String getStringDocument() {
        return document.toString();
    }

    /**
     * @param document the document to set
     */
    public void setDocument(Integer document) {
        this.document = document;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public User(Integer document, String firstname, String lastname) {
        this.document = document;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
