/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdd.casino.rest.entity;

import java.util.List;
import javax.ws.rs.core.Response;
import mdd.casino.jpa.entity.facade.AbstractFacade;
import mdd.casino.util.JsonUtil;

/**
 *
 * @author masterdev
 */
public abstract class AbstractRest<T> {

    private Class<T> entityClass;

    protected abstract AbstractFacade getFacade();

    public AbstractRest(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected String listDefault() {
        List lst = getFacade().findAll();
        return JsonUtil.objectToJson(lst);
    }

    protected String findDefault(Object id) {
        Object obj = getFacade().find(id);
        return JsonUtil.objectToJson(obj);
    }

    protected String updateDefault(T obj) {
        try {
            getFacade().edit(obj);
        } catch (Exception e) {
            e.printStackTrace();
            //StringWriter errors = new StringWriter();
            //e.printStackTrace(new PrintWriter(errors));
            //return "{\"error\":\"" + errors.toString().replaceAll("\"", " ") + "\"}";
            return "{\"error\":\"" + e.toString() + "\"}";
        }
        return JsonUtil.objectToJson(obj);
    }

    protected Response createDefault(T obj) {
        String result = "";
        try {
            getFacade().create(obj);
        } catch (Exception e) {
            //e.printStackTrace();
            //StringWriter errors = new StringWriter();
            //e.printStackTrace(new PrintWriter(errors));
            //result = errors.toString().replaceAll("\"", " ").replaceAll("\n"," ").replaceAll("'"," ").replaceAll("\\:", " ");
            result = e.toString();
        }
        if (result.isEmpty()) {
            result = JsonUtil.objectToJson(obj);
        } else {
            result = "{\"error\":\"" + result + "\"}";
        }
        //System.out.println("JSON:" + result);

        return Response.status(200).entity(result).build();
    }
}
