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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dagofonseca
 */
@Entity
@Table(name = "reward")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reward.findAll", query = "SELECT r FROM Reward r")
    , @NamedQuery(name = "Reward.findByIdReward", query = "SELECT r FROM Reward r WHERE r.idReward = :idReward")
    , @NamedQuery(name = "Reward.findByName", query = "SELECT r FROM Reward r WHERE r.name = :name")
    , @NamedQuery(name = "Reward.findByPointsNeed", query = "SELECT r FROM Reward r WHERE r.pointsNeed = :pointsNeed")
    , @NamedQuery(name = "Reward.findByIsAvailable", query = "SELECT r FROM Reward r WHERE r.isAvailable = :isAvailable")})
public class Reward implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_reward")
    private Integer idReward;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "points_need")
    private int pointsNeed;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_available")
    private boolean isAvailable;
    @JoinColumn(name = "id_office", referencedColumnName = "id_office")
    @ManyToOne(optional = false)
    private Office idOffice;

    public Reward() {
    }

    public Reward(Integer idReward) {
        this.idReward = idReward;
    }

    public Reward(Integer idReward, String name, int pointsNeed, boolean isAvailable) {
        this.idReward = idReward;
        this.name = name;
        this.pointsNeed = pointsNeed;
        this.isAvailable = isAvailable;
    }

    public Integer getIdReward() {
        return idReward;
    }

    public void setIdReward(Integer idReward) {
        this.idReward = idReward;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPointsNeed() {
        return pointsNeed;
    }

    public void setPointsNeed(int pointsNeed) {
        this.pointsNeed = pointsNeed;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
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
        hash += (idReward != null ? idReward.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reward)) {
            return false;
        }
        Reward other = (Reward) object;
        if ((this.idReward == null && other.idReward != null) || (this.idReward != null && !this.idReward.equals(other.idReward))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mdd.casino.jpa.entity.pojo.Reward[ idReward=" + idReward + " ]";
    }

}
