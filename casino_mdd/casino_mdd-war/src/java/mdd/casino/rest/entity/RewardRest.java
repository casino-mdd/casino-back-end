/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdd.casino.rest.entity;

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
import mdd.casino.jpa.entity.dto.RewardDto;
import mdd.casino.jpa.entity.facade.RewardFacade;
import mdd.casino.jpa.entity.pojo.Reward;
import mdd.casino.util.BeanUtil;
import mdd.casino.util.JsonUtil;

/**
 * REST Web Service
 *
 * @author dagofonseca
 */
@Path("reward")
public class RewardRest extends AbstractRest<Reward> {

    @Context
    private UriInfo context;

    RewardFacade facade = BeanUtil.lookupFacadeBean(RewardFacade.class);

    public RewardRest() {
        super(Reward.class);
    }

    @Override
    public RewardFacade getFacade() {
        return facade;
    }

    @POST
    @Path("/createdto")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String obj_json) {
        RewardDto dto = JsonUtil.jsonToObject(obj_json, RewardDto.class);

        StringBuilder err = new StringBuilder();
        Reward c = facade.createDto(dto, err);
        String json;
        if (!err.toString().isEmpty()) {
            json = "{ \"error\": \"" + err.toString() + "\" }";
        } else {
            json = JsonUtil.objectToJson(c);
        }

        return Response.status(200).entity(json).build();
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
        Reward obj = JsonUtil.jsonToObject(obj_json, Reward.class);
        obj.setIdReward(new Integer(id));
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
