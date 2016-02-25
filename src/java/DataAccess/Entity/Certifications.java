/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "certifications")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Certifications.findAll", query = "SELECT c FROM Certifications c"),
    @NamedQuery(name = "Certifications.findByPkID", query = "SELECT c FROM Certifications c WHERE c.pkID = :pkID"),
    @NamedQuery(name = "Certifications.findByName", query = "SELECT c FROM Certifications c WHERE c.name = :name"),
    @NamedQuery(name = "Certifications.findByDate", query = "SELECT c FROM Certifications c WHERE c.date = :date"),
    @NamedQuery(name = "Certifications.findByPlace", query = "SELECT c FROM Certifications c WHERE c.place = :place")})
public class Certifications implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pkID")
    private Integer pkID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "place")
    private String place;
    @ManyToMany(mappedBy = "certificationsCollection")
    private Collection<Areaofinterest> areaofinterestCollection;
    @JoinColumn(name = "fkuserID", referencedColumnName = "pkID")
    @ManyToOne(optional = false)
    private User fkuserID;
    @OneToMany(mappedBy = "fkcertificationID")
    private Collection<Notifications> notificationsCollection;

    public Certifications() {
    }

    public Certifications(Integer pkID) {
        this.pkID = pkID;
    }

    public Certifications(Integer pkID, String name, Date date, String place) {
        this.pkID = pkID;
        this.name = name;
        this.date = date;
        this.place = place;
    }

    public Integer getPkID() {
        return pkID;
    }

    public void setPkID(Integer pkID) {
        this.pkID = pkID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @XmlTransient
    public Collection<Areaofinterest> getAreaofinterestCollection() {
        return areaofinterestCollection;
    }

    public void setAreaofinterestCollection(Collection<Areaofinterest> areaofinterestCollection) {
        this.areaofinterestCollection = areaofinterestCollection;
    }

    public User getFkuserID() {
        return fkuserID;
    }

    public void setFkuserID(User fkuserID) {
        this.fkuserID = fkuserID;
    }

    @XmlTransient
    public Collection<Notifications> getNotificationsCollection() {
        return notificationsCollection;
    }

    public void setNotificationsCollection(Collection<Notifications> notificationsCollection) {
        this.notificationsCollection = notificationsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkID != null ? pkID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Certifications)) {
            return false;
        }
        Certifications other = (Certifications) object;
        if ((this.pkID == null && other.pkID != null) || (this.pkID != null && !this.pkID.equals(other.pkID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Entity.Certifications[ pkID=" + pkID + " ]";
    }
    
}
