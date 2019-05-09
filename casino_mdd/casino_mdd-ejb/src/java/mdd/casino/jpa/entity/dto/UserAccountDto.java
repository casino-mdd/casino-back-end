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
public class UserAccountDto {

    private Integer idUserAccount;
    private String username;
    private String password;
    private Date updatedAt;
    private Date createdAt;
    private String status;
    private String position;
    

    private String idtentificationNumEmpl;
    private String profile;
    private String name;
    private String surname;
    private int age;
    private String gender;
    private String email;
    private long phone;
    private Integer idoffice;

    public Integer getIdUserAccount() {
        return idUserAccount;
    }

    public void setIdUserAccount(Integer idUser) {
        this.idUserAccount = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getIdtentificationNumEmpl() {
        return idtentificationNumEmpl;
    }

    public void setIdtentificationNumEmpl(String idtentificationNumEmpl) {
        this.idtentificationNumEmpl = idtentificationNumEmpl;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public Integer getIdoffice() {
        return idoffice;
    }

    public void setIdoffice(Integer idoffice) {
        this.idoffice = idoffice;
    }

}
