/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.ContractDAO;
import DataAccess.DAO.PositionDAO;
import DataAccess.DAO.UserDAO;
import DataAccess.Entity.User;
import DataAccess.Entity.Contract;
import java.util.Date;

/**
 *
 * @author Alejandro
 */
public class HandleContract {

    public String doCreate(ContractDAO contractDAO, PositionDAO positionDAO, double salary, String type, Date startDate, Date endDate, String healthEnterprise, Date startHealth, String pensionEnterprise, Date startPension, Integer fkuserID, String userName, int contractPosition) {
        Contract contract = new Contract();
        User userObject = new User(fkuserID);

        contract.setSalary(salary);
        contract.setType(type);
        contract.setEnddate(endDate);
        contract.setStartDate(startDate);
        contract.setHealthEnterprise(healthEnterprise);
        contract.setStartHealthDate(startHealth);
        contract.setPensionEnterprise(pensionEnterprise);
        contract.setStartPensionDate(startPension);
        contract.setFkuserID(userObject);

        try {
            String d = endDate.toString();
            if (type.equals("Indefinido")) {
                return "El contrato no fue creado ya que se define fecha de finalización a un contrato Indefinido.";
            }
        } catch (NullPointerException e) {
            if (type.equals("Definido")) {
                return "El contrato no fue creado ya que no se define fecha de finalización a un contrato Definido.";
            }
        }

        contract.getPositionSet().add(positionDAO.searchByID(contractPosition));

        Contract contractObject = contractDAO.persist(contract);

        if (contractObject != null) {
            return "Contrato creado para el usuario " + userName;
        } else {
            return "El contrato no pudo ser creado.";
        }
    }

    public String renewContract(PositionDAO positionDAO, ContractDAO contractDAO, UserDAO userDAO, double salary, String type, Date startDate, Date finalDate, String healthEnterprise, Date startHealth, String pensionEnterprise, Date startPension, long userDoccument, int contractPosition) {
        User userObject = userDAO.searchByDoccument(userDoccument);

        if (userObject == null) {
            return "El documento de identidad ingresado no existe en la base de datos";
        }

        Contract contract = new Contract();
        contract.setSalary(salary);
        contract.setType(type);
        contract.setEnddate(finalDate);
        contract.setStartDate(startDate);
        contract.setHealthEnterprise(healthEnterprise);
        contract.setStartHealthDate(startHealth);
        contract.setPensionEnterprise(pensionEnterprise);
        contract.setStartPensionDate(startPension);
        contract.setFkuserID(userObject);

        try {
            String d = finalDate.toString();
            if (type.equals("Indefinido")) {
                return "El contrato no fue creado ya que se define fecha de finalización a un contrato Indefinido.";
            }
        } catch (NullPointerException e) {
            if (type.equals("Definido")) {
                return "El contrato no fue creado ya que no se define fecha de finalización a un contrato Definido.";
            }
        }

        contract.getPositionSet().add(positionDAO.searchByID(contractPosition));

        //busca contrato de este usuario
        Contract contratoUsuario = contractDAO.getUserContract(userObject);
        Contract contractObject = null;
        if(contratoUsuario == null){
            contractObject = contractDAO.persist(contract);
        }else{
            contract.setPkID(contratoUsuario.getPkID());
            contractObject = contractDAO.edit(positionDAO, contract, contractPosition);
        }

        if (contractObject
                != null) {
            return "Contrato creado para el usuario " + userObject.getName();
        } else {
            return "El contrato no pudo ser creado.";
        }
    }
}
