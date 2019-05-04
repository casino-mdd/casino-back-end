/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdd.casino.rest.entity;

import java.util.Date;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import mdd.casino.jpa.entity.facade.EmployeeFacade;
import mdd.casino.jpa.entity.pojo.Employee;
import mdd.casino.jpa.entity.pojo.Person;
import mdd.casino.util.BeanUtil;
import mdd.casino.util.JsonUtil;

/**
 * REST Web Service
 *
 * @author dagofonseca
 */
@Path("employee")
public class EmployeeRest {

    @Context
    private UriInfo context;

    EmployeeFacade facade = BeanUtil.lookupFacadeBean(EmployeeFacade.class);

    /**
     * Creates a new instance of PersonRest
     */
    public EmployeeRest() {
    }
    
    @GET
    @Path("create/{obj_json}")
    @Produces(MediaType.APPLICATION_JSON)
    public String create(@PathParam("obj_json") String obj_json) {
        Employee obj = JsonUtil.jsonToObject(obj_json, Employee.class);
        obj.setCreatedAt(new Date());
        obj.setUpdatedAt(new Date());
        try{
            facade.create(obj);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return JsonUtil.objectToJson(obj);
    }
    
//    @GET
//    @Path("list")
//    @Produces(MediaType.APPLICATION_JSON)
//    public String list() {
//        List<Person> lst = facade.findAll();
//        return JsonUtil.objectToJson(lst);
//    }    
//    
//    @GET
//    @Path("find/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public String find(@PathParam("id") String id) {
//        Person p = facade.find(new Integer(id));
//        return JsonUtil.objectToJson(p);
//    }
//    
//    @GET
//    @Path("update/{obj_json}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public String update(@PathParam("obj_json") String obj_json) {
//        Person p = JsonUtil.jsonToObject(obj_json, Person.class);  
//        Person pOri =  facade.find(p.getIdPerson());
//        p.setUpdatedAt(new Date());
//        p.setCreatedAt(pOri.getCreatedAt());
//        try{
//            facade.edit(p);
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//        return JsonUtil.objectToJson(p);
//    }
}
