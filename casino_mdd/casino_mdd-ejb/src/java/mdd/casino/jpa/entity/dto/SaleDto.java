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
public class SaleDto {
    
    private String idenNumClient;
    private String idenNumEmployee;
    private int token;
    private double cost;
    private String paymentMethod;

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

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    
    
}
