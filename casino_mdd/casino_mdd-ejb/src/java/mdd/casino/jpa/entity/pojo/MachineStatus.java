/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdd.casino.jpa.entity.pojo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dagofonseca
 */
@Entity
@Table(name = "machine_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MachineStatus.findAll", query = "SELECT m FROM MachineStatus m")
    , @NamedQuery(name = "MachineStatus.findByIdMachineStatus", query = "SELECT m FROM MachineStatus m WHERE m.idMachineStatus = :idMachineStatus")
    , @NamedQuery(name = "MachineStatus.findByDescription", query = "SELECT m FROM MachineStatus m WHERE m.description = :description")})
public class MachineStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_machine_status")
    private Integer idMachineStatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMachineStatus")
    private Collection<Machine> machineCollection;

    public MachineStatus() {
    }

    public MachineStatus(Integer idMachineStatus) {
        this.idMachineStatus = idMachineStatus;
    }

    public MachineStatus(Integer idMachineStatus, String description) {
        this.idMachineStatus = idMachineStatus;
        this.description = description;
    }

    public Integer getIdMachineStatus() {
        return idMachineStatus;
    }

    public void setIdMachineStatus(Integer idMachineStatus) {
        this.idMachineStatus = idMachineStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<Machine> getMachineCollection() {
        return machineCollection;
    }

    public void setMachineCollection(Collection<Machine> machineCollection) {
        this.machineCollection = machineCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMachineStatus != null ? idMachineStatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MachineStatus)) {
            return false;
        }
        MachineStatus other = (MachineStatus) object;
        if ((this.idMachineStatus == null && other.idMachineStatus != null) || (this.idMachineStatus != null && !this.idMachineStatus.equals(other.idMachineStatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mdd.casino.jpa.entity.pojo.MachineStatus[ idMachineStatus=" + idMachineStatus + " ]";
    }
    
}
