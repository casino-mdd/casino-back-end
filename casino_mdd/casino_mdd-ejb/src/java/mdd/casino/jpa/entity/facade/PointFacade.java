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
                + "     AND p.expDate >='" + format.format(now) + "'";
        return numFromHQL(hql, new Long(0)).intValue();
    }

    /**
     *
     * @return Map<key: idclient, val: sumPoints>
     */
    public HashMap<Integer, Long> mapPointsByIdClient() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();

        String hql = "SELECT sum(p.totalPoints),p.idSale.idClient.idClient "
                + "   FROM Point p "
                + "     WHERE p.expDate >='" + format.format(now) + "'"
                + " GROUP BY p.idSale.idClient.idClient";

        HashMap<Integer, Long> map = new HashMap<>();
        List<Object[]> lst = findGeneric(hql);
        for (Object[] o : lst) {
            map.put((Integer) o[1], (Long) o[0]);
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
