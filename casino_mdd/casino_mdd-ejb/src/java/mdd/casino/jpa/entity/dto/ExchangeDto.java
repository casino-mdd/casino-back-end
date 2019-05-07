/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdd.casino.jpa.entity.dto;

import java.util.Date;

/**
 *
 * @author dagofonseca
 */
public class ExchangeDto {

    private String idenNumClient;
    private String idenNumEmployee;
    private Integer idReward;
    private String client;
    private String reward;
    private String employee;
    private Date date;

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

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
