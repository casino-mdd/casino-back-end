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
import mdd.casino.jpa.entity.dto.SaleDto;
import mdd.casino.jpa.entity.pojo.Client;
import mdd.casino.jpa.entity.pojo.Employee;
import mdd.casino.jpa.entity.pojo.Point;
import mdd.casino.jpa.entity.pojo.Sale;
import org.hibernate.impl.SessionImpl;

@Stateless
public class SaleFacade extends AbstractFacade<Sale> {
    
    @EJB
    ClientFacade clientFacade;
    
    @EJB
    EmployeeFacade employeeFacade;
    
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public SaleFacade() {
        super(Sale.class);
    }

    public void sell(Sale sale, StringBuilder err) {
        beginTransaction();
        SessionImpl sess = getSess();
        try {

            //1. Save Sale
            sale.setCreatedAt(new Date());
            sess.save(sale);

            //2. Add points
            int nPoints = 10;
            int nDaysExpired = 2 * 30;

            Point p = new Point();
            p.setTotalPoints(nPoints);
            p.setExpDate(new Date(new Date().getTime() + nDaysExpired * 24 * 60 * 60 * 1000));
            p.setCreatedAt(new Date());
            p.setIdSale(sale);
            sess.save(p);

            commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            err.append(e.toString());
            rollbackTransaction();
        }
        endTransaction();
    }
    
    public void sell(SaleDto dto, StringBuilder err) {
        Sale s = new Sale();
        Client c = clientFacade.findByIdentification(dto.getIdenNumClient());
        if (c == null){
            err.append("Cliente no encontrado");
            return;
        }
        Employee em = employeeFacade.findByIdentification(dto.getIdenNumEmployee());
        if (em == null){
            err.append("Empleado no encontrado");
            return;
        }
        
        s.setCost(dto.getCost());
        s.setIdClient(c);
        s.setIdEmployee(em);
        s.setIdOffice(em.getIdOffice());
        s.setPaymentMethod(dto.getPaymentMethod());
        s.setToken(dto.getToken());
        
        sell(s, err);
    }
}
