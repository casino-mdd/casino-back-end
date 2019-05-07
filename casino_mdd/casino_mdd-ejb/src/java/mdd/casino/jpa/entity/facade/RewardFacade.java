/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdd.casino.jpa.entity.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import mdd.casino.jpa.entity.dto.RewardDto;
import mdd.casino.jpa.entity.pojo.Employee;
import mdd.casino.jpa.entity.pojo.Reward;

@Stateless
public class RewardFacade extends AbstractFacade<Reward> {

    @EJB
    EmployeeFacade employeeFacade;

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public RewardFacade() {
        super(Reward.class);
    }

    public List<Reward> listByOffice(Integer idOffice) {
        String hql = "SELECT r FROM Reward r WHERE r.idOffice.idOffice = " + idOffice + " order by r.pointsNeed desc";
        return findList(hql);
    }

    public Reward createDto(RewardDto dto, StringBuilder err) {
        Employee em = employeeFacade.findByIdentification(dto.getIdentNumberEmployee());
        if (em == null) {
            err.append("Empleado no encontrado");
            return null;
        }
        Reward r = new Reward();
        r.setIdOffice(em.getIdOffice());
        r.setIsAvailable(true);
        r.setName(dto.getName());
        r.setPointsNeed(dto.getPointsNeed());
        try {
            create(r);
        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
        }
        return r;
    }

    /**
     *
     * @return MAP<Key: IdOffice, Val: List<Reward>>
     */
    public HashMap<Integer, List<Reward>> mapRewardByOffice() {
        String hql = "SELECT r "
                + " FROM Reward r "
                + " ORDER BY r.name ASC ";

        HashMap<Integer, List<Reward>> map = new HashMap<>();
        List<Reward> lstR = findList(hql);
        for (Reward r : lstR) {
            Integer k = r.getIdOffice().getIdOffice();
            List<Reward> lstV = map.get(k);
            if (lstV == null) {
                lstV = new ArrayList<>();
                map.put(k, lstV);
            }
            lstV.add(r);
        }
        return map;
    }

    public static RewardDto parse(Reward r) {
        RewardDto dto = new RewardDto();
        dto.setIdReward(r.getIdReward());
        dto.setIsAvailable(r.getIsAvailable());
        dto.setName(r.getName());
        dto.setPointsNeed(r.getPointsNeed());
        return dto;
    }
}
