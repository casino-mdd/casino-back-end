/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdd.casino.rest.entity;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import mdd.casino.jpa.entity.facade.PersonFacade;
import mdd.casino.jpa.entity.pojo.Person;
import mdd.casino.util.BeanUtil;

/**
 * REST Web Service
 *
 * @author dagofonseca
 */
@Path("person")
public class PersonRest {

    @Context
    private UriInfo context;

    PersonFacade personFacade = (PersonFacade) BeanUtil.lookupPersonFacadeBean("PersonFacade");

    /**
     * Creates a new instance of PersonRest
     */
    public PersonRest() {
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        Person p=personFacade.find(0);
        return "hello "+p.getName();
    }
    

}
