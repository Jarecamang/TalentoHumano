/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PCMiguel
 */
@Entity
@Table(name = "certificate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Certificate.findAll", query = "SELECT c FROM Certificate c"),
    @NamedQuery(name = "Certificate.findByPkID", query = "SELECT c FROM Certificate c WHERE c.pkID = :pkID"),
    @NamedQuery(name = "Certificate.findByType", query = "SELECT c FROM Certificate c WHERE c.type = :type"),
    @NamedQuery(name = "Certificate.findByAproved", query = "SELECT c FROM Certificate c WHERE c.aproved = :aproved"),
    @NamedQuery(name = "Certificate.findByDescripcion", query = "SELECT c FROM Certificate c WHERE c.descripcion = :descripcion")})
public class Certificate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pkID")
    private Integer pkID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aproved")
    private boolean aproved;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "fkuserID", referencedColumnName = "pkID")
    @ManyToOne(optional = false)
    private User fkuserID;

    public Certificate() {
    }

    public Certificate(Integer pkID) {
        this.pkID = pkID;
    }

    public Certificate(Integer pkID, String type, boolean aproved) {
        this.pkID = pkID;
        this.type = type;
        this.aproved = aproved;
    }

    public Integer getPkID() {
        return pkID;
    }

    public void setPkID(Integer pkID) {
        this.pkID = pkID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getAproved() {
        return aproved;
    }

    public void setAproved(boolean aproved) {
        this.aproved = aproved;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        if (!(object instanceof Certificate)) {
            return false;
        }
        Certificate other = (Certificate) object;
        if ((this.pkID == null && other.pkID != null) || (this.pkID != null && !this.pkID.equals(other.pkID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Entity.Certificate[ pkID=" + pkID + " ]";
    }
    
}
