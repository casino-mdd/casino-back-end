/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdd.casino.rest.entity;

import java.util.ArrayList;
import java.util.Date;
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
import mdd.casino.jpa.entity.dto.ClientDto;
import mdd.casino.jpa.entity.dto.ClientPointDto;
import mdd.casino.jpa.entity.dto.PointDto;
import mdd.casino.jpa.entity.dto.RewardDto;
import mdd.casino.jpa.entity.facade.ClientFacade;
import mdd.casino.jpa.entity.facade.EmployeeFacade;
import mdd.casino.jpa.entity.facade.PointFacade;
import mdd.casino.jpa.entity.facade.RewardFacade;
import mdd.casino.jpa.entity.pojo.Client;
import mdd.casino.jpa.entity.pojo.Employee;
import mdd.casino.jpa.entity.pojo.Point;
import mdd.casino.jpa.entity.pojo.Reward;
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
    EmployeeFacade empFacade = BeanUtil.lookupFacadeBean(EmployeeFacade.class);
    RewardFacade rewFacade = BeanUtil.lookupFacadeBean(RewardFacade.class);
    PointFacade pointFacade = BeanUtil.lookupFacadeBean(PointFacade.class);

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

    @POST
    @Path("/createdto")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDtop(String obj_json) {
        ClientDto obj = JsonUtil.jsonToObject(obj_json, ClientDto.class);

        StringBuilder err = new StringBuilder();
        Client c = facade.createDto(obj, err);
        String json;
        if (!err.toString().isEmpty()) {
            json = "{ \"error\": \"" + err.toString() + "\" }";
        } else {
            obj = facade.parseClient(c);
            json = JsonUtil.objectToJson(obj);
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
    @Path("listdto")
    @Produces(MediaType.APPLICATION_JSON)
    public String listDto() {
        List<Client> lstOri = facade.findAll();
        List<ClientDto> lstDto = new ArrayList();
        for (Client c : lstOri) {
            ClientDto dto = facade.parseClient(c);
            lstDto.add(dto);
        }
        return JsonUtil.objectToJson(lstDto);
    }

    @GET
    @Path("find/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String find(@PathParam("id") String id) {
        return findDefault(new Integer(id));
    }

    @GET
    @Path("finddto/{identificationNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public String findDto(@PathParam("identificationNumber") String identificationNumber) {
        Client c = facade.findByIdentification(identificationNumber);
        if (c == null) {
            return "{ \"error\": \"Número de identificación no está registrado\" }";
        }
        ClientDto dto = facade.parseClient(c);
        return JsonUtil.objectToJson(dto);
    }

    @GET
    @Path("clientpoints/{clientIdentification}/{employeeIdentification}")
    @Produces(MediaType.APPLICATION_JSON)
    public String find(@PathParam("clientIdentification") String clientIdentification,
            @PathParam("employeeIdentification") String employeeIdentification) {
        Client c = facade.findByIdentification(clientIdentification);
        Employee e = empFacade.findByIdentification(employeeIdentification);
        if (c == null || e == null) {
            return "{ \"error\": \"Número de identificación no está registrado\" }";
        }
        List<Reward> lstR = rewFacade.listByOffice(e.getIdOffice().getIdOffice());
        List<Point> lstP = pointFacade.listPointsAviable(c.getIdClient());
        ClientPointDto dto = facade.parseClientPoint(c, lstP, lstR);

        return JsonUtil.objectToJson(dto);

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
