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
public class ExchangeDto {
    
    private String idenNumClient;
    private String idenNumEmployee;
    private Integer idReward;

    public String getIdenNumClient() {
        return idenNumClient;
    }

    public void setIdenNumClient(String idenNumClient) {
        this.idenNumClient = idenNumClient;
    }

    public String getIdenNumEmployee() {
        return idenNumEmployee;
    }

    public void setIdenNumEmployee(String idenNumEmployee) {
        this.idenNumEmployee = idenNumEmployee;
    }

    public Integer getIdReward() {
        return idReward;
    }

    public void setIdReward(Integer idReward) {
        this.idReward = idReward;
    }
    
    
}
