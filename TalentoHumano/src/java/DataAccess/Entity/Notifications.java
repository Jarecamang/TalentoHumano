/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PCMiguel
 */
@Entity
@Table(name = "notifications")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notifications.findAll", query = "SELECT n FROM Notifications n"),
    @NamedQuery(name = "Notifications.findByPkID", query = "SELECT n FROM Notifications n WHERE n.pkID = :pkID"),
    @NamedQuery(name = "Notifications.findByDescription", query = "SELECT n FROM Notifications n WHERE n.description = :description"),
    @NamedQuery(name = "Notifications.findByDate", query = "SELECT n FROM Notifications n WHERE n.date = :date")})
public class Notifications implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pkID")
    private Integer pkID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "fkcertificationID", referencedColumnName = "pkID")
    @ManyToOne
    private Certifications fkcertificationID;
    @JoinColumn(name = "fkuserID", referencedColumnName = "pkID")
    @ManyToOne(optional = false)
    private User fkuserID;

    public Notifications() {
    }

    public Notifications(Integer pkID) {
        this.pkID = pkID;
    }

    public Notifications(Integer pkID, String description, Date date) {
        this.pkID = pkID;
        this.description = description;
        this.date = date;
    }

    public Integer getPkID() {
        return pkID;
    }

    public void setPkID(Integer pkID) {
        this.pkID = pkID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Certifications getFkcertificationID() {
        return fkcertificationID;
    }

    public void setFkcertificationID(Certifications fkcertificationID) {
        this.fkcertificationID = fkcertificationID;
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
        if (!(object instanceof Notifications)) {
            return false;
        }
        Notifications other = (Notifications) object;
        if ((this.pkID == null && other.pkID != null) || (this.pkID != null && !this.pkID.equals(other.pkID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Entity.Notifications[ pkID=" + pkID + " ]";
    }
    
}
