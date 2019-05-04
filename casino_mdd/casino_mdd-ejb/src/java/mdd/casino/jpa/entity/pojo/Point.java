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
@Table(name = "point")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Point.findAll", query = "SELECT p FROM Point p")
    , @NamedQuery(name = "Point.findByIdPoint", query = "SELECT p FROM Point p WHERE p.idPoint = :idPoint")
    , @NamedQuery(name = "Point.findByTotalPoints", query = "SELECT p FROM Point p WHERE p.totalPoints = :totalPoints")
    , @NamedQuery(name = "Point.findByExpDate", query = "SELECT p FROM Point p WHERE p.expDate = :expDate")
    , @NamedQuery(name = "Point.findByCreatedAt", query = "SELECT p FROM Point p WHERE p.createdAt = :createdAt")})
public class Point implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_point")
    private Integer idPoint;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_points")
    private int totalPoints;
    @Basic(optional = false)
    @NotNull
    @Column(name = "exp_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "id_sale", referencedColumnName = "id_sale")
    @ManyToOne(optional = false)
    private Sale idSale;

    public Point() {
    }

    public Point(Integer idPoint) {
        this.idPoint = idPoint;
    }

    public Point(Integer idPoint, int totalPoints, Date expDate, Date createdAt) {
        this.idPoint = idPoint;
        this.totalPoints = totalPoints;
        this.expDate = expDate;
        this.createdAt = createdAt;
    }

    public Integer getIdPoint() {
        return idPoint;
    }

    public void setIdPoint(Integer idPoint) {
        this.idPoint = idPoint;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Sale getIdSale() {
        return idSale;
    }

    public void setIdSale(Sale idSale) {
        this.idSale = idSale;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPoint != null ? idPoint.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Point)) {
            return false;
        }
        Point other = (Point) object;
        if ((this.idPoint == null && other.idPoint != null) || (this.idPoint != null && !this.idPoint.equals(other.idPoint))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mdd.casino.jpa.entity.pojo.Point[ idPoint=" + idPoint + " ]";
    }
    
}
