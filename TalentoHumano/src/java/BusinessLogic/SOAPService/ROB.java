/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.SOAPService;

import java.util.ArrayList;

/**
 *
 * @author PCMiguel
 */
public class ROB {
 
    private boolean success;
    private String err_message;
    private ArrayList<String> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErr_message() {
        return err_message;
    }

    public void setErr_message(String err_message) {
        this.err_message = err_message;
    }

    public ArrayList<String> getData() {
        return data;
    }

    public void setData(ArrayList<String> data) {
        this.data = data;
    }

    public ROB(boolean success, String err_message, ArrayList<String> data) {
        this.success = success;
        this.err_message = err_message;
        this.data = data;
    }
}