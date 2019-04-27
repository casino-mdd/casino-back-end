/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdd.casino.jpa.entity.pojo;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dagofonseca
 */
@Entity
@Table(name = "client_has_office")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClientHasOffice.findAll", query = "SELECT c FROM ClientHasOffice c")
    , @NamedQuery(name = "ClientHasOffice.findByIdCo", query = "SELECT c FROM ClientHasOffice c WHERE c.idCo = :idCo")})
public class ClientHasOffice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_co")
    private Integer idCo;
    @JoinColumn(name = "id_client", referencedColumnName = "id_client")
    @ManyToOne(optional = false)
    private Client idClient;
    @JoinColumn(name = "id_office", referencedColumnName = "id_office")
    @ManyToOne(optional = false)
    private Office idOffice;

    public ClientHasOffice() {
    }

    public ClientHasOffice(Integer idCo) {
        this.idCo = idCo;
    }

    public Integer getIdCo() {
        return idCo;
    }

    public void setIdCo(Integer idCo) {
        this.idCo = idCo;
    }

    public Client getIdClient() {
        return idClient;
    }

    public void setIdClient(Client idClient) {
        this.idClient = idClient;
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
        hash += (idCo != null ? idCo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClientHasOffice)) {
            return false;
        }
        ClientHasOffice other = (ClientHasOffice) object;
        if ((this.idCo == null && other.idCo != null) || (this.idCo != null && !this.idCo.equals(other.idCo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mdd.casino.jpa.entity.pojo.ClientHasOffice[ idCo=" + idCo + " ]";
    }
    
}
