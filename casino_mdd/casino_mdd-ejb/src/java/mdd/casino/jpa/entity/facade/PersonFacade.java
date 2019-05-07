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
import mdd.casino.jpa.entity.pojo.Person;

@Stateless
public class PersonFacade extends AbstractFacade<Person> {

    public final static String GENDER_F = "F";
    public final static String GENDER_M = "M";

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public PersonFacade() {
        super(Person.class);
    }

    public Person findByEmail(String email) {
        String hql = "SELECT p "
                + "  FROM Person p "
                + " WHERE p.email='" + email + "'";
        return objectFromHQL(hql);
    }

     public Person findByIdentificacioin(String identificationNum) {
        String hql = "SELECT p "
                + "  FROM Person p "
                + " WHERE p.identificationNumber='" + identificationNum + "'";
        return objectFromHQL(hql);
    }

}
