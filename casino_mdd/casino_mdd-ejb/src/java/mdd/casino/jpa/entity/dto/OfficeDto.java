/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdd.casino.jpa.entity.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author masterdev
 */
public class OfficeDto {

    private Integer idOffice;
    private String address;
    private String city;
    private String name;
    private List<RewardDto> rewards = new ArrayList<>();

    public Integer getIdOffice() {
        return idOffice;
    }

    public void setIdOffice(Integer idOffice) {
        this.idOffice = idOffice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RewardDto> getRewards() {
        return rewards;
    }

    public void setRewards(List<RewardDto> rewards) {
        this.rewards = rewards;
    }

}
