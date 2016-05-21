
package Presentation.Bean;

import BusinessLogic.Controller.HandleUser;
import DataAccess.DAO.ContractDAO;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * @author Edwin
 */

@ManagedBean
@RequestScoped
public class UserSalaryBean { 

    private double salarySmallerThanOneM;
    private double salaryBetweenOneMAndTwoM;
    private double salaryBetweenTwoMAndThreeM;
    private double salaryBetweenThreeMAndFourM;
    private double salaryBetweenFourMAndFiveM;
    private double salaryBiggerThanFiveM;
    @EJB
    private ContractDAO contractDAO;
    
    public UserSalaryBean() {
        
    }
    
    public UserSalaryBean(double salarySmallerThanOneM, double salaryBetweenOneMAndTwoM, double salaryBetweenTwoMAndThreeM, double salaryBetweenThreeMAndFourM, double salaryBetweenFourMAndFiveM, double salaryBiggerThanFiveM) {
        this.salarySmallerThanOneM = salarySmallerThanOneM;
        this.salaryBetweenOneMAndTwoM = salaryBetweenOneMAndTwoM;
        this.salaryBetweenTwoMAndThreeM = salaryBetweenTwoMAndThreeM;
        this.salaryBetweenThreeMAndFourM = salaryBetweenThreeMAndFourM;
        this.salaryBetweenFourMAndFiveM = salaryBetweenFourMAndFiveM;
        this.salaryBiggerThanFiveM = salaryBiggerThanFiveM;
    }

    public double getSalarySmallerThanOneM() {
        return salarySmallerThanOneM;
    }

    public double getSalaryBetweenOneMAndTwoM() {
        return salaryBetweenOneMAndTwoM;
    }

    public double getSalaryBetweenTwoMAndThreeM() {
        return salaryBetweenTwoMAndThreeM;
    }

    public double getSalaryBetweenThreeMAndFourM() {
        return salaryBetweenThreeMAndFourM;
    }

    public double getSalaryBetweenFourMAndFiveM() {
        return salaryBetweenFourMAndFiveM;
    }

    public double getSalaryBiggerThanFiveM() {
        return salaryBiggerThanFiveM;
    }
    
    public UserSalaryBean getSalaries(){
        HandleUser obj = new HandleUser();
        return obj.getSalaries(contractDAO);
    }
    
}
