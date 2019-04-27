/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdd.casino.jpa.entity.pojo;

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
 * @author dagofonseca
 */
@Entity
@Table(name = "machine")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Machine.findAll", query = "SELECT m FROM Machine m")
    , @NamedQuery(name = "Machine.findByIdMachine", query = "SELECT m FROM Machine m WHERE m.idMachine = :idMachine")
    , @NamedQuery(name = "Machine.findByName", query = "SELECT m FROM Machine m WHERE m.name = :name")
    , @NamedQuery(name = "Machine.findByCategory", query = "SELECT m FROM Machine m WHERE m.category = :category")
    , @NamedQuery(name = "Machine.findByUpdatedAt", query = "SELECT m FROM Machine m WHERE m.updatedAt = :updatedAt")
    , @NamedQuery(name = "Machine.findByCreatedAt", query = "SELECT m FROM Machine m WHERE m.createdAt = :createdAt")})
public class Machine implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_machine")
    private Integer idMachine;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "category")
    private String category;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "id_brand", referencedColumnName = "id_brand")
    @ManyToOne(optional = false)
    private Brand idBrand;
    @JoinColumn(name = "id_machine_category", referencedColumnName = "id_machine_category")
    @ManyToOne(optional = false)
    private MachineCategory idMachineCategory;
    @JoinColumn(name = "id_machine_status", referencedColumnName = "id_machine_status")
    @ManyToOne(optional = false)
    private MachineStatus idMachineStatus;
    @JoinColumn(name = "id_office", referencedColumnName = "id_office")
    @ManyToOne(optional = false)
    private Office idOffice;

    public Machine() {
    }

    public Machine(Integer idMachine) {
        this.idMachine = idMachine;
    }

    public Machine(Integer idMachine, String name, String category, Date updatedAt, Date createdAt) {
        this.idMachine = idMachine;
        this.name = name;
        this.category = category;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public Integer getIdMachine() {
        return idMachine;
    }

    public void setIdMachine(Integer idMachine) {
        this.idMachine = idMachine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Brand getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(Brand idBrand) {
        this.idBrand = idBrand;
    }

    public MachineCategory getIdMachineCategory() {
        return idMachineCategory;
    }

    public void setIdMachineCategory(MachineCategory idMachineCategory) {
        this.idMachineCategory = idMachineCategory;
    }

    public MachineStatus getIdMachineStatus() {
        return idMachineStatus;
    }

    public void setIdMachineStatus(MachineStatus idMachineStatus) {
        this.idMachineStatus = idMachineStatus;
    }

    public Office getIdOffice() {
        return idOffice;
    }

    public void setIdOffice(Office idOffice) {
        this.idOffice = idOffice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMachine != null ? idMachine.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Machine)) {
            return false;
        }
        Machine other = (Machine) object;
        if ((this.idMachine == null && other.idMachine != null) || (this.idMachine != null && !this.idMachine.equals(other.idMachine))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mdd.casino.jpa.entity.pojo.Machine[ idMachine=" + idMachine + " ]";
    }
    
}
