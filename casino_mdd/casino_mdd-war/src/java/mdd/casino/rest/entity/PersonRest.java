/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdd.casino.rest.entity;

import java.util.Date;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import mdd.casino.jpa.entity.facade.PersonFacade;
import mdd.casino.jpa.entity.pojo.Person;
import mdd.casino.util.BeanUtil;
import mdd.casino.util.JsonUtil;

/**
 * REST Web Service
 *
 * @author dagofonseca
 */
@Path("person")
public class PersonRest {

    @Context
    private UriInfo context;

    PersonFacade personFacade = BeanUtil.lookupFacadeBean(PersonFacade.class);

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
    
    @GET
    @Path("create/{obj_json}")
    @Produces(MediaType.APPLICATION_JSON)
    public String create(@PathParam("obj_json") String obj_json) {
        Person p = JsonUtil.jsonToObject(obj_json, Person.class);
        p.setCreatedAt(new Date());
        p.setUpdatedAt(new Date());
        try{
            personFacade.create(p);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return JsonUtil.objectToJson(p);
    }
    
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public String list() {
        List<Person> lst = personFacade.findAll();
        return JsonUtil.objectToJson(lst);
    }    
    
    @GET
    @Path("find/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String find(@PathParam("id") String id) {
        Person p = personFacade.find(new Integer(id));
        return JsonUtil.objectToJson(p);
    }
    
    @GET
    @Path("update/{obj_json}")
    @Produces(MediaType.APPLICATION_JSON)
    public String update(@PathParam("obj_json") String obj_json) {
        Person p = JsonUtil.jsonToObject(obj_json, Person.class);  
        Person pOri =  personFacade.find(p.getIdPerson());
        p.setUpdatedAt(new Date());
        p.setCreatedAt(pOri.getCreatedAt());
        try{
            personFacade.edit(p);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return JsonUtil.objectToJson(p);
    }
}
