/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdd.casino.rest.entity;

import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mdd.casino.jpa.entity.facade.ClientFacade;
import mdd.casino.jpa.entity.pojo.Client;
import mdd.casino.util.BeanUtil;
import mdd.casino.util.JsonUtil;

/**
 * REST Web Service
 *
 * @author dagofonseca
 */
@Path("client")
public class ClientRest extends AbstractRest<Client> {

    @Context
    private UriInfo context;

    ClientFacade facade = BeanUtil.lookupFacadeBean(ClientFacade.class);

    public ClientRest() {
        super(Client.class);
    }

    @Override
    public ClientFacade getFacade() {
        return facade;
    }

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String obj_json) {
        Client obj = JsonUtil.jsonToObject(obj_json, Client.class);
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
        Client obj = JsonUtil.jsonToObject(obj_json, Client.class);
        Client objOld = facade.find(new Integer(id));

        obj.setIdClient(objOld.getIdClient());
        obj.setCreatedAt(objOld.getCreatedAt());
        obj.setUpdatedAt(new Date());

        return updateDefault(obj);
    }

}
