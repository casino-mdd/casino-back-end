/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdd.casino.jpa.entity.dto;

/**
 *
 * @author dagofonseca
 */
public class RewardDto {
    
    private Integer idReward;
    private String name;
    private int pointsNeed;
    private String identNumberEmployee;
    private boolean isAvailable;

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

    public String getIdentNumberEmployee() {
        return identNumberEmployee;
    }

    public void setIdentNumberEmployee(String identNumberEmployee) {
        this.identNumberEmployee = identNumberEmployee;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
        
}
