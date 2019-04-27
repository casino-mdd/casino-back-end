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
@Table(name = "client_category")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClientCategory.findAll", query = "SELECT c FROM ClientCategory c")
    , @NamedQuery(name = "ClientCategory.findByIdClientCategory", query = "SELECT c FROM ClientCategory c WHERE c.idClientCategory = :idClientCategory")
    , @NamedQuery(name = "ClientCategory.findByName", query = "SELECT c FROM ClientCategory c WHERE c.name = :name")})
public class ClientCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_client_category")
    private Integer idClientCategory;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClientCategory")
    private Collection<Client> clientCollection;

    public ClientCategory() {
    }

    public ClientCategory(Integer idClientCategory) {
        this.idClientCategory = idClientCategory;
    }

    public ClientCategory(Integer idClientCategory, String name) {
        this.idClientCategory = idClientCategory;
        this.name = name;
    }

    public Integer getIdClientCategory() {
        return idClientCategory;
    }

    public void setIdClientCategory(Integer idClientCategory) {
        this.idClientCategory = idClientCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Client> getClientCollection() {
        return clientCollection;
    }

    public void setClientCollection(Collection<Client> clientCollection) {
        this.clientCollection = clientCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClientCategory != null ? idClientCategory.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClientCategory)) {
            return false;
        }
        ClientCategory other = (ClientCategory) object;
        if ((this.idClientCategory == null && other.idClientCategory != null) || (this.idClientCategory != null && !this.idClientCategory.equals(other.idClientCategory))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mdd.casino.jpa.entity.pojo.ClientCategory[ idClientCategory=" + idClientCategory + " ]";
    }
    
}
