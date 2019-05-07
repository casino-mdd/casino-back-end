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
import mdd.casino.jpa.entity.dto.ClientDto;
import mdd.casino.jpa.entity.pojo.Client;
import mdd.casino.jpa.entity.pojo.Person;
import org.hibernate.impl.SessionImpl;

@Stateless
public class ClientFacade extends AbstractFacade<Client> {

    @EJB
    PersonFacade personFacade;

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public ClientFacade() {
        super(Client.class);
    }

    public Client findByIdentification(String identification) {
        String hql = "SELECT c FROM Client c WHERE c.idPerson.identificationNumber = '" + identification + "'";
        return objectFromHQL(hql);
    }

    public Client createDto(ClientDto dto, StringBuilder err) {

        Person pe = personFacade.findByIdentificacioin(dto.getIdentificationNumber());
        if (pe != null) {
            err.append("El número de identificación ya existe:").append(dto.getIdentificationNumber());
            return null;
        }

        pe = personFacade.findByEmail(dto.getEmail());
        if (pe != null) {
            err.append("El email '").append(dto.getEmail()).append("' ya está registrado !");
            return null;
        }

        Client c = null;

        beginTransaction();
        SessionImpl sess = getSess();
        try {
            Person p = new Person();
            p.setAge(dto.getAge());
            p.setCreatedAt(new Date());
            p.setEmail(dto.getEmail());
            p.setGender(dto.getGender());
            p.setIdentificationNumber(dto.getIdentificationNumber());
            p.setName(dto.getName());
            p.setPhone(new Long(dto.getPhone()));
            p.setSurname(dto.getSurname());
            p.setUpdatedAt(new Date());
            sess.save(p);

            c = new Client();
            c.setCreatedAt(new Date());
            c.setIdPerson(p);
            c.setUpdatedAt(new Date());
            sess.save(c);

            commitTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
            err.append(ex.toString());
            rollbackTransaction();
        }
        endTransaction();

        if (c != null && c.getIdClient() != null) {
            c = find(c.getIdClient());
        }

        return c;

    }

}
