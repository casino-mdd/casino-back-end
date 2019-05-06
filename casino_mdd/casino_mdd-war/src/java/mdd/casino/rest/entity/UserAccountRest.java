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
import mdd.casino.jpa.entity.dto.UserAccountDto;
import mdd.casino.jpa.entity.facade.UserAccountFacade;
import mdd.casino.jpa.entity.pojo.UserAccount;
import mdd.casino.util.BeanUtil;
import mdd.casino.util.HashUtil;
import mdd.casino.util.JsonUtil;

/**
 * REST Web Service
 *
 * @author dagofonseca
 */
@Path("userAccount")
public class UserAccountRest extends AbstractRest<UserAccount> {
    
    @Context
    private UriInfo context;
    
    UserAccountFacade facade = BeanUtil.lookupFacadeBean(UserAccountFacade.class);
    
    public UserAccountRest() {
        super(UserAccount.class);
    }
    
    @Override
    public UserAccountFacade getFacade() {
        return facade;
    }
    
    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String obj_json) {
        UserAccount obj = JsonUtil.jsonToObject(obj_json, UserAccount.class);
        obj.setCreatedAt(new Date());
        obj.setUpdatedAt(new Date());
        obj.setPassword(HashUtil.md5(obj.getPassword()));
        
        return createDefault(obj);
    }
        
    private UserAccountDto parseUserAccount(UserAccount u){
        
        String status = (u.getIsActive()) ? "Activo" : "Inactivo";
        UserAccountDto dto = new UserAccountDto();
        
        dto.setCreatedAt(u.getCreatedAt());
        dto.setUsername(u.getUsername());
        dto.setIdUserAccount(u.getIdUserAccount());
        dto.setPassword(u.getPassword());
        dto.setPosition(u.getIdEmployee().getPosition());
        dto.setUpdatedAt(u.getUpdatedAt());        
        dto.setStatus(status);
               
        return dto;
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
        List<UserAccount> lstOri = facade.findAll();
        List<UserAccountDto> lstDto = new ArrayList();
        for (UserAccount c : lstOri) {
            UserAccountDto dto = parseUserAccount(c);            
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
    @Path("finddto/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public String findByUsername(@PathParam("username") String username) {
        UserAccount u = facade.findByUsername(username);
        UserAccountDto dto = parseUserAccount(u);
        return JsonUtil.objectToJson(dto);
    }
    
    @PUT
    @Path("update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String update(@PathParam("id") String id, String obj_json) {
        UserAccount obj = JsonUtil.jsonToObject(obj_json, UserAccount.class);
        UserAccount objOld = facade.find(new Integer(id));
        
        obj.setIdUserAccount(objOld.getIdUserAccount());
        obj.setCreatedAt(objOld.getCreatedAt());
        obj.setUpdatedAt(new Date());
        
        return updateDefault(obj);
    }
    
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(String obj_json) {
        UserAccount obj = JsonUtil.jsonToObject(obj_json, UserAccount.class);
        StringBuilder err = new StringBuilder();
        UserAccount usLogin = facade.login(obj.getUsername(), HashUtil.md5(obj.getPassword()), err);
        String res;
        if (usLogin == null) {
            res = "{\"error\":\"" + err.toString() + "\"}";
        } else {
            //usLogin.getIdEmployee().setIdOffice(null);
            res = JsonUtil.objectToJson(usLogin);
        }
        
        return Response.status(200).entity(res).build();
    }
    
}
