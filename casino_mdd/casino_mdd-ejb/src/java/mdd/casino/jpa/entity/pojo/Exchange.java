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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dagofonseca
 */
@Entity
@Table(name = "exchange")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exchange.findAll", query = "SELECT e FROM Exchange e")
    , @NamedQuery(name = "Exchange.findByIdExchange", query = "SELECT e FROM Exchange e WHERE e.idExchange = :idExchange")
    , @NamedQuery(name = "Exchange.findByCreatedAt", query = "SELECT e FROM Exchange e WHERE e.createdAt = :createdAt")})
public class Exchange implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_exchange")
    private Integer idExchange;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "id_client", referencedColumnName = "id_client")
    @ManyToOne(optional = false)
    private Client idClient;
    @JoinColumn(name = "id_employee", referencedColumnName = "id_employee")
    @ManyToOne(optional = false)
    private Employee idEmployee;
    @JoinColumn(name = "id_reward", referencedColumnName = "id_reward")
    @ManyToOne(optional = false)
    private Reward idReward;

    public Exchange() {
    }

    public Exchange(Integer idExchange) {
        this.idExchange = idExchange;
    }

    public Exchange(Integer idExchange, Date createdAt) {
        this.idExchange = idExchange;
        this.createdAt = createdAt;
    }

    public Integer getIdExchange() {
        return idExchange;
    }

    public void setIdExchange(Integer idExchange) {
        this.idExchange = idExchange;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Client getIdClient() {
        return idClient;
    }

    public void setIdClient(Client idClient) {
        this.idClient = idClient;
    }

    public Employee getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Employee idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Reward getIdReward() {
        return idReward;
    }

    public void setIdReward(Reward idReward) {
        this.idReward = idReward;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExchange != null ? idExchange.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exchange)) {
            return false;
        }
        Exchange other = (Exchange) object;
        if ((this.idExchange == null && other.idExchange != null) || (this.idExchange != null && !this.idExchange.equals(other.idExchange))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mdd.casino.jpa.entity.pojo.Exchange[ idExchange=" + idExchange + " ]";
    }
    
}
