/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author PCMiguel
 */
@Entity
@Table(name = "contract")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contract.findAll", query = "SELECT c FROM Contract c"),
    @NamedQuery(name = "Contract.findByPkID", query = "SELECT c FROM Contract c WHERE c.pkID = :pkID"),
    @NamedQuery(name = "Contract.findBySalary", query = "SELECT c FROM Contract c WHERE c.salary = :salary"),
    @NamedQuery(name = "Contract.findByType", query = "SELECT c FROM Contract c WHERE c.type = :type"),
    @NamedQuery(name = "Contract.findByStartDate", query = "SELECT c FROM Contract c WHERE c.startDate = :startDate"),
    @NamedQuery(name = "Contract.findByEnddate", query = "SELECT c FROM Contract c WHERE c.enddate = :enddate"),
    @NamedQuery(name = "Contract.findByHealthEnterprise", query = "SELECT c FROM Contract c WHERE c.healthEnterprise = :healthEnterprise"),
    @NamedQuery(name = "Contract.findByStartHealthDate", query = "SELECT c FROM Contract c WHERE c.startHealthDate = :startHealthDate"),
    @NamedQuery(name = "Contract.findByPensionEnterprise", query = "SELECT c FROM Contract c WHERE c.pensionEnterprise = :pensionEnterprise"),
    @NamedQuery(name = "Contract.findByStartPensionDate", query = "SELECT c FROM Contract c WHERE c.startPensionDate = :startPensionDate"),
    @NamedQuery(name = "Contract.findByfkuserID", query = "SELECT c FROM Contract c WHERE c.fkuserID = :fkuserID"),
    @NamedQuery(name = "Contract.findBySalarySmallerThan", query = "SELECT c FROM Contract c WHERE c.salary < :salary"),
    @NamedQuery(name = "Contract.findBySalaryBiggerThan", query = "SELECT c FROM Contract c WHERE c.salary > :salary")})
public class Contract implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pkID")
    private Integer pkID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "salary")
    private double salary;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Column(name = "startDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "enddate")
    @Temporal(TemporalType.DATE)
    private Date enddate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "healthEnterprise")
    private String healthEnterprise;
    @Basic(optional = false)
    @NotNull
    @Column(name = "startHealthDate")
    @Temporal(TemporalType.DATE)
    private Date startHealthDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "pensionEnterprise")
    private String pensionEnterprise;
    @Basic(optional = false)
    @NotNull
    @Column(name = "startPensionDate")
    @Temporal(TemporalType.DATE)
    private Date startPensionDate;
    @JoinTable(name = "contractposition", joinColumns = {
        @JoinColumn(name = "fkcontractID", referencedColumnName = "pkID")}, inverseJoinColumns = {
        @JoinColumn(name = "fkpositionID", referencedColumnName = "pkID")})
    @ManyToMany
    private Set<Position> positionSet = new HashSet<>(0);
    @JoinColumn(name = "fkuserID", referencedColumnName = "pkID")
    @OneToOne(optional = false)
    private User fkuserID;

    public Contract() {
    }

    public Contract(Integer pkID) {
        this.pkID = pkID;
    }

    public Contract(Integer pkID, double salary, String type, Date startDate, String healthEnterprise, Date startHealthDate, String pensionEnterprise, Date startPensionDate) {
        this.pkID = pkID;
        this.salary = salary;
        this.type = type;
        this.startDate = startDate;
        this.healthEnterprise = healthEnterprise;
        this.startHealthDate = startHealthDate;
        this.pensionEnterprise = pensionEnterprise;
        this.startPensionDate = startPensionDate;
    }

    public Integer getPkID() {
        return pkID;
    }

    public void setPkID(Integer pkID) {
        this.pkID = pkID;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getHealthEnterprise() {
        return healthEnterprise;
    }

    public void setHealthEnterprise(String healthEnterprise) {
        this.healthEnterprise = healthEnterprise;
    }

    public Date getStartHealthDate() {
        return startHealthDate;
    }

    public void setStartHealthDate(Date startHealthDate) {
        this.startHealthDate = startHealthDate;
    }

    public String getPensionEnterprise() {
        return pensionEnterprise;
    }

    public void setPensionEnterprise(String pensionEnterprise) {
        this.pensionEnterprise = pensionEnterprise;
    }

    public Date getStartPensionDate() {
        return startPensionDate;
    }

    public void setStartPensionDate(Date startPensionDate) {
        this.startPensionDate = startPensionDate;
    }

    @XmlTransient
    public Set<Position> getPositionSet() {
        return positionSet;
    }

    public void setPositionSet(Set<Position> positionSet) {
        this.positionSet = positionSet;
    }

    public User getFkuserID() {
        return fkuserID;
    }

    public void setFkuserID(User fkuserID) {
        this.fkuserID = fkuserID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkID != null ? pkID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Contract)) {
            return false;
        }
        Contract other = (Contract) object;
        if ((this.pkID == null && other.pkID != null) || (this.pkID != null && !this.pkID.equals(other.pkID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Entity.Contract[ pkID=" + pkID + " ]";
    }
    
}
