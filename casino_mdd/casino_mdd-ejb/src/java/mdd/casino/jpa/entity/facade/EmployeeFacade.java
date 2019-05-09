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
import mdd.casino.jpa.entity.dto.EmployeeDto;
import mdd.casino.jpa.entity.pojo.Employee;
import mdd.casino.jpa.entity.pojo.Office;
import mdd.casino.jpa.entity.pojo.Person;
import org.hibernate.impl.SessionImpl;

@Stateless
public class EmployeeFacade extends AbstractFacade<Employee> {

    @EJB
    PersonFacade personFacade;

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public EmployeeFacade() {
        super(Employee.class);
    }

    public Employee findByIdentification(String identification) {
        String hql = "SELECT c FROM Employee c WHERE c.idPerson.identificationNumber = '" + identification + "'";
        return objectFromHQL(hql);
    }

    public Employee createDto(EmployeeDto dto, StringBuilder err) {

        Person empl = personFacade.findByIdentificacioin(dto.getIdentificationNumber());
        if (empl != null) {
            err.append("El número de identificación ya existe:").append(dto.getIdentificationNumber());
            return null;
        }

        Person pe = personFacade.findByEmail(dto.getEmail());
        if (pe != null) {
            err.append("El email '").append(dto.getEmail()).append("' ya está registrado !");
            return null;
        }

        Employee e = null;

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

            e = new Employee();
            e.setAdmitionDate(new Date());
            e.setCreatedAt(new Date());
            e.setIdOffice(new Office(dto.getIdOffice()));
            e.setIdPerson(p);
            e.setPosition(dto.getPosition());
            e.setUpdatedAt(new Date());
            sess.save(e);

            commitTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
            err.append(ex.toString());
            rollbackTransaction();
        }
        endTransaction();

        if (e != null && e.getIdEmployee() != null) {
            e = find(e.getIdEmployee());
        }

        return e;

    }
        
    public EmployeeDto parseEmployee(Employee c) {
        EmployeeDto dto = new EmployeeDto();
        dto.setAdmissionDate(c.getAdmitionDate());
        dto.setEmail(c.getIdPerson().getEmail());
        dto.setIdEmployee(c.getIdEmployee());
        dto.setIdentificationNumber(c.getIdPerson().getIdentificationNumber());
        dto.setName(c.getIdPerson().getName() + " " + c.getIdPerson().getSurname());
        dto.setOffice(c.getIdOffice().getName());
        dto.setPhone(c.getIdPerson().getPhone() + "");
        dto.setPosition(c.getPosition());
        dto.setAge(c.getIdPerson().getAge());

        return dto;
    }
}
