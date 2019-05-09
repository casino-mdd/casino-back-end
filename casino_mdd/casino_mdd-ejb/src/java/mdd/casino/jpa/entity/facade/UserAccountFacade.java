/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdd.casino.jpa.entity.facade;

import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import mdd.casino.jpa.entity.dto.UserAccountDto;
import mdd.casino.jpa.entity.pojo.Employee;
import mdd.casino.jpa.entity.pojo.Office;
import mdd.casino.jpa.entity.pojo.Person;
import mdd.casino.jpa.entity.pojo.UserAccount;
import mdd.casino.util.HashUtil;

@Stateless
public class UserAccountFacade extends AbstractFacade<UserAccount> {

    @EJB
    EmployeeFacade emplFacade;
    @EJB
    PersonFacade personFacade;

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

    public UserAccount findByIdentification(String identification) {
        String hql = "SELECT c FROM UserAccount c WHERE c.idEmployee.idPerson.identificationNumber = '" + identification + "'";
        return objectFromHQL(hql);
    }

    public void createDto(UserAccountDto dto, StringBuilder err) {

        UserAccount us = findByIdentification(dto.getIdtentificationNumEmpl());
        if (us != null) {
            err.append("La identificacion ya existe como usuario!");
            return;
        }
        Person pe = personFacade.findByEmail(dto.getEmail());
        if (pe != null) {
            err.append("El email ya está registrado");
            return;
        }

        try {

            UserAccount u = new UserAccount();
            u.setCreatedAt(new Date());
            u.setIsActive(true);
            u.setPassword(HashUtil.md5(dto.getPassword()));
            u.setProfile(dto.getProfile());
            u.setUpdatedAt(new Date());
            u.setUsername(dto.getUsername());

            Employee empl = emplFacade.findByIdentification(dto.getIdtentificationNumEmpl());
            if (empl != null) {
                u.setIdEmployee(empl);
                create(u);
                return;
            }

            Person p = personFacade.findByIdentificacioin(dto.getIdtentificationNumEmpl());
            if (p == null) {
                p = new Person();
                p.setAge(dto.getAge());
                p.setCreatedAt(new Date());
                p.setEmail(dto.getEmail());
                p.setGender(dto.getGender());
                p.setIdentificationNumber(dto.getIdtentificationNumEmpl());
                p.setName(dto.getName());
                p.setSurname(dto.getSurname());
                p.setPhone(dto.getPhone());
                p.setUpdatedAt(new Date());
                personFacade.create(p);
            }
            Employee e = new Employee();
            e.setAdmitionDate(new Date());
            e.setCreatedAt(new Date());
            e.setIdOffice(new Office(dto.getIdoffice()));
            e.setIdPerson(p);
            e.setPosition(dto.getPosition());
            e.setUpdatedAt(new Date());
            emplFacade.create(e);

            u.setIdEmployee(e);
            create(u);

        } catch (Exception e) {
            e.printStackTrace();
            err.append(e.toString());
        }

    }
    
    public UserAccountDto parseUserAccount(UserAccount u) {

        String status = (u.getIsActive()) ? "Activo" : "Inactivo";
        UserAccountDto dto = new UserAccountDto();

        dto.setCreatedAt(u.getCreatedAt());
        dto.setUsername(u.getUsername());
        dto.setIdUserAccount(u.getIdUserAccount());
        dto.setPassword(u.getPassword());
        dto.setPosition(u.getIdEmployee().getPosition());
        dto.setUpdatedAt(u.getUpdatedAt());
        dto.setStatus(status);
        dto.setProfile(u.getProfile());

        return dto;
    }
}
