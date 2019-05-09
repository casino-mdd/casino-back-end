/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdd.casino.jpa.entity.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import mdd.casino.jpa.entity.dto.OfficeDto;
import mdd.casino.jpa.entity.dto.RewardDto;
import mdd.casino.jpa.entity.pojo.Office;
import mdd.casino.jpa.entity.pojo.Reward;

@Stateless
public class OfficeFacade extends AbstractFacade<Office> {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public OfficeFacade() {
        super(Office.class);
    }
    
    public OfficeDto parse(Office o, HashMap<Integer, List<Reward>> mapRewardByOffice) {
        OfficeDto dto = new OfficeDto();
        dto.setAddress(o.getAddress());
        dto.setCity(o.getCity());
        dto.setIdOffice(o.getIdOffice());
        dto.setName(o.getName());

        List<Reward> lstR = mapRewardByOffice.get(o.getIdOffice());
        if (lstR != null) {
            List<RewardDto> lstRDto = new ArrayList<>();
            for (Reward reward : lstR) {
                RewardDto dtoR = RewardFacade.parse(reward);
                lstRDto.add(dtoR);
            }
            dto.setRewards(lstRDto);
        }

        return dto;
    }
}
