/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdd.casino.jpa.entity.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import mdd.casino.jpa.entity.dto.UserAccountDto;
import mdd.casino.jpa.entity.pojo.UserAccount;

@Stateless
public class UserAccountFacade extends AbstractFacade<UserAccount> {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public UserAccountFacade() {
        super(UserAccount.class);
    }

    public UserAccount findByUsername(String username) {
        String hql = "SELECT u "
                + "  FROM UserAccount u "
                + " WHERE u.username='" + username.trim() + "'";
        return objectFromHQL(hql);

    }

    public UserAccount login(String username, String passwordMD5, StringBuilder err) {
        UserAccount u = findByUsername(username);
        if (u == null) {
            err.append("Usuario no encontrado.");
            return null;
        }
        if (!u.getPassword().equals(passwordMD5)) {
            err.append("Contraseña incorrecta.");
            return null;
        }
        if (!u.getIsActive()) {
            err.append("La cuenta no está activa.");
            return null;
        }

        return u;
    }
    
    public UserAccountDto parseUserAccount(UserAccount u){
        
        String status = (u.getIsActive()) ? "Activo" : "Inactivo";
        UserAccountDto dto = new UserAccountDto();
        
        dto.setCreatedAt(u.getCreatedAt());
        dto.setUsername(u.getUsername());
        dto.setIdUserAccount(u.getIdUserAccount());
        dto.setPassword(u.getPassword());
        dto.setPosition(u.getIdEmployee().getPosition());
        dto.setUpdatedAt(u.getUpdatedAt());        
        dto.setStatus(status);
               
        return dto;
    }

}
