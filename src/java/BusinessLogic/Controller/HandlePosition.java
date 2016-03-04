/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.PositionDAO;
import DataAccess.Entity.Position;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alejandro
 */
public class HandlePosition {

    public void getPositions() {
        PositionDAO pDAO = new PositionDAO();
        List<Position> lp = pDAO.searchAll();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.getSessionMap().put("positionList", lp);
        List<String[]> lc = new ArrayList<>();
        for (Position position : lp) {
            String[] t = new String[2];
            t[0] = position.getName();
            t[1] = String.valueOf(position.getContractCollection().size());
            lc.add(t);
        }
        ec.getSessionMap().put("amountList", lc);
    }

    public String doCreate(String name) {
        Position position = new Position();
        PositionDAO positionDAO = new PositionDAO();
        position.setName(name);
        Position positionObject = positionDAO.persist(position);
        if (positionObject != null) {
            getPositions();
            return "El cargo ha sido creado con nombre " + positionObject.getName();
        } else {
            return "El cargo no pudo ser creado.";
        }
    }

}
