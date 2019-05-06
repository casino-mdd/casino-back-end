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
import mdd.casino.jpa.entity.pojo.Client;

@Stateless
public class ClientFacade extends AbstractFacade<Client> {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public ClientFacade() {
        super(Client.class);
    }
    
    public Client findByIdentification(String identification){
        String hql = "SELECT c FROM Client c WHERE c.idPerson.identificationNumber = '" + identification + "'";
        return objectFromHQL(hql);
    }

}
