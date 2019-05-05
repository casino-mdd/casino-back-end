/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdd.casino.rest.entity;

import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
public class PersonRest extends AbstractRest<Person> {

    @Context
    private UriInfo context;

    PersonFacade facade = BeanUtil.lookupFacadeBean(PersonFacade.class);

    public PersonRest() {
        super(Person.class);
    }

    @Override
    public PersonFacade getFacade() {
        return facade;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        Person p = facade.find(0);
        return "hello " + p.getName();
    }

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String obj_json) {
        Person obj = JsonUtil.jsonToObject(obj_json, Person.class);
        obj.setCreatedAt(new Date());
        obj.setUpdatedAt(new Date());

        return createDefault(obj);
    }

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public String list() {
        return listDefault();
    }

    @GET
    @Path("find/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String find(@PathParam("id") String id) {
        return findDefault(new Integer(id));
    }

    @PUT
    @Path("update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String update(@PathParam("id") String id, String obj_json) {
        Person obj = JsonUtil.jsonToObject(obj_json, Person.class);
        Person objOld = facade.find(new Integer(id));

        obj.setIdPerson(objOld.getIdPerson());
        obj.setCreatedAt(objOld.getCreatedAt());
        obj.setUpdatedAt(new Date());

        return updateDefault(obj);
    }

    /*@PUT
    @Path("/prueba2/{param}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putMsg(@PathParam("param") String msg, String obj_json) {
        String output = "PUT: Jersey say : " + msg + obj_json;
        return Response.status(200).entity(output).build();
    }

    @DELETE
    @Path("/delete/{param}")
    public Response deleteMsg(@PathParam("param") String msg) {
        String output = "DELETE:Jersey say : " + msg;
        return Response.status(200).entity(output).build();
    }*/
}
