/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdd.casino.jpa.entity.facade;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import mdd.casino.jpa.entity.dto.ExchangeDto;
import mdd.casino.jpa.entity.pojo.Client;
import mdd.casino.jpa.entity.pojo.Employee;
import mdd.casino.jpa.entity.pojo.Exchange;
import mdd.casino.jpa.entity.pojo.Reward;
import org.hibernate.impl.SessionImpl;

@Stateless
public class ExchangeFacade extends AbstractFacade<Exchange> {

    @EJB
    PointFacade pointFacade;

    @EJB
    RewardFacade rewardFacade;

    @EJB
    ClientFacade clientFacade;

    @EJB
    EmployeeFacade employeeFacade;

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public ExchangeFacade() {
        super(Exchange.class);
    }

    public void exchangeReward(Exchange exchange, StringBuilder err) {
        //0. Validate
        Reward r = rewardFacade.find(exchange.getIdReward().getIdReward());
        int pointsWin = r.getPointsNeed();
        int np = pointFacade.sumPointsAviable(exchange.getIdClient().getIdClient());
        if (np < pointsWin) {
            err.append("Para reclamar el premio deben tenerse ").append(pointsWin).append(" punto(s), y el cliente tiene ").append(np);
            return;
        }

        beginTransaction();
        SessionImpl sess = getSess();
        try {

            //1. Save Sale
            exchange.setCreatedAt(new Date());
            sess.save(exchange);

            //2. Update reward
            String sql = "UPDATE reward"
                    + "    SET is_available=false "
                    + "  WHERE id_reward=" + exchange.getIdReward().getIdReward();
            sess.createSQLQuery(sql).executeUpdate();

            //3. Delete Points
            sql = "DELETE FROM point "
                    + " WHERE id_sale IN (SELECT id_sale "
                    + "                    FROM sale"
                    + "                   WHERE id_client=" + exchange.getIdClient().getIdClient() + ")";
            sess.createSQLQuery(sql).executeUpdate();

            commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            err.append(e.toString());
            rollbackTransaction();
        }
        endTransaction();
    }

    public void exchangeReward(ExchangeDto dto, StringBuilder err) {
        Exchange ex = new Exchange();
        Client c = clientFacade.findByIdentification(dto.getIdenNumClient());
        if (c == null) {
            err.append("Cliente no encontrado");
            return;
        }
        Employee em = employeeFacade.findByIdentification(dto.getIdenNumEmployee());
        if (em == null) {
            err.append("Empleado no encontrado");
            return;
        }

        ex.setIdClient(c);
        ex.setIdEmployee(em);
        ex.setIdReward(new Reward(dto.getIdReward()));
        exchangeReward(ex, err);
    }

    public List<Exchange> listByIdClient(Integer idclient) {
        String hql = "SELECT e FROM Exchange e WHERE e.idClient.idClient=" + idclient;
        return findList(hql);
    }
}
