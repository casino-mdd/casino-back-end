/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdd.casino.jpa.entity.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import mdd.casino.jpa.entity.pojo.Reward;

@Stateless
public class RewardFacade extends AbstractFacade<Reward> {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public RewardFacade() {
        super(Reward.class);
    }
    
    public List<Reward> listByOffice(Integer idOffice){
        String hql = "SELECT r FROM Reward r WHERE r.idOffice.idOffice = "+ idOffice + " order by r.pointNeed desc";
        return findList(hql);
    }

}
