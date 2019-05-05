/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdd.casino.rest.entity;

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
import mdd.casino.jpa.entity.facade.SaleFacade;
import mdd.casino.jpa.entity.pojo.Sale;
import mdd.casino.util.BeanUtil;
import mdd.casino.util.JsonUtil;

/**
 * REST Web Service
 *
 * @author dagofonseca
 */
@Path("sale")
public class SaleRest extends AbstractRest<Sale> {

    @Context
    private UriInfo context;

    SaleFacade facade = BeanUtil.lookupFacadeBean(SaleFacade.class);

    public SaleRest() {
        super(Sale.class);
    }

    @Override
    public SaleFacade getFacade() {
        return facade;
    }

    @POST
    @Path("/sell")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String obj_json) {
        Sale obj = JsonUtil.jsonToObject(obj_json, Sale.class);
        StringBuilder err = new StringBuilder();
        facade.sell(obj, err);

        String result;
        if (!err.toString().isEmpty()) {
            result = "{\"error\":\"" + err.toString() + "\"}";
        } else {
            result = JsonUtil.objectToJson(obj);
        }

        return Response.status(200).entity(result).build();
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
        Sale obj = JsonUtil.jsonToObject(obj_json, Sale.class);
        Sale objOld = facade.find(new Integer(id));

        obj.setIdSale(objOld.getIdSale());
        obj.setCreatedAt(objOld.getCreatedAt());

        return updateDefault(obj);
    }

}