/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdd.casino.jpa.entity.facade;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import mdd.casino.jpa.entity.pojo.Point;

@Stateless
public class PointFacade extends AbstractFacade<Point> {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public PointFacade() {
        super(Point.class);
    }

    public int sumPointsAviable(Integer idclient) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();

        String hql = "SELECT sum(p.totalPoints) "
                + "   FROM Point p "
                + "  WHERE p.idSale.idClient.idClient=" + idclient
                + "     AND p.expDate >='" + format.format(now) + "'"
                + "     AND p.isActive=true";
        return numFromHQL(hql, new Long(0)).intValue();
    }

    /**
     *
     * @return Map<key: idsale, val: sumPoints>
     */
    public HashMap<Integer, Point> mapPointsByIdSale() {
       List<Point> lstS = findAll();
       HashMap<Integer, Point> map=new HashMap<>();
        for (Point point : lstS) {
            map.put(point.getIdSale().getIdSale(), point);
        }
        return map;
    }

    public List<Point> listPointsAviable(Integer idclient) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();

        String hql = "SELECT p "
                + "   FROM Point p "
                + "  WHERE p.idSale.idClient.idClient=" + idclient
                + "     AND p.expDate >='" + format.format(now) + "'";
        return findList(hql);
    }

}
