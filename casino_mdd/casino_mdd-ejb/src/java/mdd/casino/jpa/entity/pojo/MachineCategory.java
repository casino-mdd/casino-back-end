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
@Table(name = "machine_category")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MachineCategory.findAll", query = "SELECT m FROM MachineCategory m")
    , @NamedQuery(name = "MachineCategory.findByIdMachineCategory", query = "SELECT m FROM MachineCategory m WHERE m.idMachineCategory = :idMachineCategory")
    , @NamedQuery(name = "MachineCategory.findByName", query = "SELECT m FROM MachineCategory m WHERE m.name = :name")})
public class MachineCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_machine_category")
    private Integer idMachineCategory;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMachineCategory")
    private Collection<Machine> machineCollection;

    public MachineCategory() {
    }

    public MachineCategory(Integer idMachineCategory) {
        this.idMachineCategory = idMachineCategory;
    }

    public MachineCategory(Integer idMachineCategory, String name) {
        this.idMachineCategory = idMachineCategory;
        this.name = name;
    }

    public Integer getIdMachineCategory() {
        return idMachineCategory;
    }

    public void setIdMachineCategory(Integer idMachineCategory) {
        this.idMachineCategory = idMachineCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        hash += (idMachineCategory != null ? idMachineCategory.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MachineCategory)) {
            return false;
        }
        MachineCategory other = (MachineCategory) object;
        if ((this.idMachineCategory == null && other.idMachineCategory != null) || (this.idMachineCategory != null && !this.idMachineCategory.equals(other.idMachineCategory))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mdd.casino.jpa.entity.pojo.MachineCategory[ idMachineCategory=" + idMachineCategory + " ]";
    }
    
}
