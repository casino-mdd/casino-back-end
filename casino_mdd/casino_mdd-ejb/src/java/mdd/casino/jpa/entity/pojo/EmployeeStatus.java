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
@Table(name = "employee_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeeStatus.findAll", query = "SELECT e FROM EmployeeStatus e")
    , @NamedQuery(name = "EmployeeStatus.findByIdEmployeeStatus", query = "SELECT e FROM EmployeeStatus e WHERE e.idEmployeeStatus = :idEmployeeStatus")
    , @NamedQuery(name = "EmployeeStatus.findByDescription", query = "SELECT e FROM EmployeeStatus e WHERE e.description = :description")})
public class EmployeeStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_employee_status")
    private Integer idEmployeeStatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmployeeStatus")
    private Collection<Employee> employeeCollection;

    public EmployeeStatus() {
    }

    public EmployeeStatus(Integer idEmployeeStatus) {
        this.idEmployeeStatus = idEmployeeStatus;
    }

    public EmployeeStatus(Integer idEmployeeStatus, String description) {
        this.idEmployeeStatus = idEmployeeStatus;
        this.description = description;
    }

    public Integer getIdEmployeeStatus() {
        return idEmployeeStatus;
    }

    public void setIdEmployeeStatus(Integer idEmployeeStatus) {
        this.idEmployeeStatus = idEmployeeStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<Employee> getEmployeeCollection() {
        return employeeCollection;
    }

    public void setEmployeeCollection(Collection<Employee> employeeCollection) {
        this.employeeCollection = employeeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmployeeStatus != null ? idEmployeeStatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeeStatus)) {
            return false;
        }
        EmployeeStatus other = (EmployeeStatus) object;
        if ((this.idEmployeeStatus == null && other.idEmployeeStatus != null) || (this.idEmployeeStatus != null && !this.idEmployeeStatus.equals(other.idEmployeeStatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mdd.casino.jpa.entity.pojo.EmployeeStatus[ idEmployeeStatus=" + idEmployeeStatus + " ]";
    }
    
}
