/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdd.casino.rest.entity;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author dagofonseca
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(mdd.casino.rest.entity.AccessControlResponseFilter.class);
        resources.add(mdd.casino.rest.entity.ClientRest.class);
        resources.add(mdd.casino.rest.entity.EmployeeRest.class);
        resources.add(mdd.casino.rest.entity.ExchangeRest.class);
        resources.add(mdd.casino.rest.entity.OfficeRest.class);
        resources.add(mdd.casino.rest.entity.PersonRest.class);
        resources.add(mdd.casino.rest.entity.SaleRest.class);
        resources.add(mdd.casino.rest.entity.UserAccountRest.class);
    }
    
}
