/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdd.casino.rest.entity;

import java.util.ArrayList;
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
import mdd.casino.jpa.entity.dto.ExchangeDto;
import mdd.casino.jpa.entity.facade.ClientFacade;
import mdd.casino.jpa.entity.facade.ExchangeFacade;
import mdd.casino.jpa.entity.pojo.Client;
import mdd.casino.jpa.entity.pojo.Exchange;
import mdd.casino.util.BeanUtil;
import mdd.casino.util.JsonUtil;

/**
 * REST Web Service
 *
 * @author dagofonseca
 */
@Path("exchange")
public class ExchangeRest extends AbstractRest<Exchange> {

    @Context
    private UriInfo context;

    ExchangeFacade facade = BeanUtil.lookupFacadeBean(ExchangeFacade.class);
    ClientFacade clientFacade = BeanUtil.lookupFacadeBean(ClientFacade.class);

    public ExchangeRest() {
        super(Exchange.class);
    }

    @Override
    public ExchangeFacade getFacade() {
        return facade;
    }

    @POST
    @Path("/exchangeReward")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String obj_json) {

        ExchangeDto dto = JsonUtil.jsonToObject(obj_json, ExchangeDto.class);

        StringBuilder err = new StringBuilder();
        facade.exchangeReward(dto, err);

        String result;
        if (!err.toString().isEmpty()) {
            result = "{\"error\":\"" + err.toString() + "\"}";
        } else {
            result = JsonUtil.objectToJson(dto);
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
        Exchange obj = JsonUtil.jsonToObject(obj_json, Exchange.class);
        Exchange objOld = facade.find(new Integer(id));

        obj.setIdExchange(objOld.getIdExchange());
        obj.setCreatedAt(objOld.getCreatedAt());

        return updateDefault(obj);
    }

    @GET
    @Path("listdto")
    @Produces(MediaType.APPLICATION_JSON)
    public String listDto() {
        List<Exchange> lst = facade.findAll();
        List<ExchangeDto> lstDto = new ArrayList<>();
        for (Exchange exchange : lst) {
            ExchangeDto dtop = parse(exchange);
            lstDto.add(dtop);
        }
        return JsonUtil.objectToJson(lstDto);
    }

    @GET
    @Path("finddto/{identificationNumClient}")
    @Produces(MediaType.APPLICATION_JSON)
    public String findDtoByClient(@PathParam("identificationNumClient") String identificationNumClient) {
        Client c = clientFacade.findByIdentification(identificationNumClient);
        if (c == null) {
            return "{ \"error\": \"Cliente no encontrado!\" }";
        }
        List<Exchange> lst = facade.listByIdClient(c.getIdClient());
        List<ExchangeDto> lstDto = new ArrayList<>();
        for (Exchange exchange : lst) {
            ExchangeDto dtop = parse(exchange);
            lstDto.add(dtop);
        }
        return JsonUtil.objectToJson(lstDto);
    }

    private ExchangeDto parse(Exchange e) {
        ExchangeDto dto = new ExchangeDto();
        dto.setClient(e.getIdClient().getIdPerson().getName() + " " + e.getIdClient().getIdPerson().getSurname());
        dto.setDate(e.getCreatedAt());
        dto.setEmployee(e.getIdEmployee().getIdPerson().getName() + " " + e.getIdEmployee().getIdPerson().getSurname());
        dto.setIdenNumClient(e.getIdClient().getIdPerson().getIdentificationNumber());
        dto.setIdenNumEmployee(e.getIdEmployee().getIdPerson().getIdentificationNumber());
        return dto;
    }

}
