/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdd.casino.rest.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import mdd.casino.jpa.entity.dto.OfficeDto;
import mdd.casino.jpa.entity.dto.RewardDto;
import mdd.casino.jpa.entity.facade.OfficeFacade;
import mdd.casino.jpa.entity.facade.RewardFacade;
import mdd.casino.jpa.entity.pojo.Office;
import mdd.casino.jpa.entity.pojo.Reward;
import mdd.casino.util.BeanUtil;
import mdd.casino.util.JsonUtil;

/**
 * REST Web Service
 *
 * @author dagofonseca
 */
@Path("office")
public class OfficeRest extends AbstractRest<Office> {

    @Context
    private UriInfo context;

    OfficeFacade facade = BeanUtil.lookupFacadeBean(OfficeFacade.class);
    RewardFacade rewardFacade = BeanUtil.lookupFacadeBean(RewardFacade.class);

    public OfficeRest() {
        super(Office.class);
    }

    @Override
    public OfficeFacade getFacade() {
        return facade;
    }

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String obj_json) {
        Office obj = JsonUtil.jsonToObject(obj_json, Office.class);
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
    @Path("listdto")
    @Produces(MediaType.APPLICATION_JSON)
    public String listDto() {
        List<Office> lst = facade.findAll();
        List<OfficeDto> lstDto = new ArrayList<>();

        HashMap<Integer, List<Reward>> mapRewardByOffice = rewardFacade.mapRewardByOffice();

        for (Office o : lst) {
            OfficeDto dtop = facade.parse(o, mapRewardByOffice);
            lstDto.add(dtop);
        }
        return JsonUtil.objectToJson(lstDto);
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
        Office obj = JsonUtil.jsonToObject(obj_json, Office.class);
        Office objOld = facade.find(new Integer(id));

        obj.setIdOffice(objOld.getIdOffice());
        obj.setCreatedAt(objOld.getCreatedAt());
        obj.setUpdatedAt(new Date());

        return updateDefault(obj);
    }   

}
