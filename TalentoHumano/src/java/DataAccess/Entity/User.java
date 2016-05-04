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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByPkID", query = "SELECT u FROM User u WHERE u.pkID = :pkID"),
    @NamedQuery(name = "User.findByIdentifyCard", query = "SELECT u FROM User u WHERE u.identifyCard = :identifyCard"),
    @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
    @NamedQuery(name = "User.findByLastname", query = "SELECT u FROM User u WHERE u.lastname = :lastname"),
    @NamedQuery(name = "User.findByDateBorn", query = "SELECT u FROM User u WHERE u.dateBorn = :dateBorn"),
    @NamedQuery(name = "User.findByAddress", query = "SELECT u FROM User u WHERE u.address = :address"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByLevelTraining", query = "SELECT u FROM User u WHERE u.levelTraining = :levelTraining"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pkID")
    private Integer pkID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "identifyCard")
    private String identifyCard;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "lastname")
    private String lastname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateBorn")
    @Temporal(TemporalType.DATE)
    private Date dateBorn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "address")
    private String address;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 16777215)
    @Column(name = "phone")
    private String phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "level_training")
    private String levelTraining;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "password")
    private String password;
    @ManyToMany(mappedBy = "userSet")
    private Set<Areaofinterest> areaofinterestSet = new HashSet<>(0);
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "fkuserID")
    private Contract contract;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkuserID")
    private Set<Certificate> certificateSet = new HashSet<>(0);
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkuserID")
    private Set<Certifications> certificationsSet = new HashSet<>(0);
    @JoinColumn(name = "fkroleID", referencedColumnName = "pkID")
    @ManyToOne(optional = false)
    private Role fkroleID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkuserID")
    private Set<Notifications> notificationsSet = new HashSet<>(0);

    public User() {
    }

    public User(Integer pkID) {
        this.pkID = pkID;
    }

    public User(Integer pkID, String identifyCard, String name, String lastname, Date dateBorn, String address, String phone, String email, String levelTraining, String username, String password) {
        this.pkID = pkID;
        this.identifyCard = identifyCard;
        this.name = name;
        this.lastname = lastname;
        this.dateBorn = dateBorn;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.levelTraining = levelTraining;
        this.username = username;
        this.password = password;
    }

    public Integer getPkID() {
        return pkID;
    }

    public void setPkID(Integer pkID) {
        this.pkID = pkID;
    }

    public String getIdentifyCard() {
        return identifyCard;
    }

    public void setIdentifyCard(String identifyCard) {
        this.identifyCard = identifyCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDateBorn() {
        return dateBorn;
    }

    public void setDateBorn(Date dateBorn) {
        this.dateBorn = dateBorn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLevelTraining() {
        return levelTraining;
    }

    public void setLevelTraining(String levelTraining) {
        this.levelTraining = levelTraining;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public Set<Areaofinterest> getAreaofinterestSet() {
        return areaofinterestSet;
    }

    public void setAreaofinterestSet(Set<Areaofinterest> areaofinterestSet) {
        this.areaofinterestSet = areaofinterestSet;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    @XmlTransient
    public Set<Certificate> getCertificateSet() {
        return certificateSet;
    }

    public void setCertificateSet(Set<Certificate> certificateSet) {
        this.certificateSet = certificateSet;
    }

    @XmlTransient
    public Set<Certifications> getCertificationsSet() {
        return certificationsSet;
    }

    public void setCertificationsSet(Set<Certifications> certificationsSet) {
        this.certificationsSet = certificationsSet;
    }

    public Role getFkroleID() {
        return fkroleID;
    }

    public void setFkroleID(Role fkroleID) {
        this.fkroleID = fkroleID;
    }

    @XmlTransient
    public Set<Notifications> getNotificationsSet() {
        return notificationsSet;
    }

    public void setNotificationsSet(Set<Notifications> notificationsSet) {
        this.notificationsSet = notificationsSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkID != null ? pkID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.pkID == null && other.pkID != null) || (this.pkID != null && !this.pkID.equals(other.pkID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Entity.User[ pkID=" + pkID + " ]";
    }
    
}
