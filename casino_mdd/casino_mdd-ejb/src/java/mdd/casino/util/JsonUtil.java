/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdd.casino.util;

import com.google.gson.Gson;
import java.util.Date;
import mdd.casino.jpa.entity.facade.PersonFacade;
import mdd.casino.jpa.entity.pojo.Employee;
import mdd.casino.jpa.entity.pojo.Office;
import mdd.casino.jpa.entity.pojo.Person;
import mdd.casino.jpa.entity.pojo.UserAccount;

/**
 *
 * @author inmoticamaster
 */
public class JsonUtil {

    public static <T> T jsonToObject(String json, Class<T> c) {
        T data = new Gson().fromJson(json, c);
        return data;
    }

    public static String objectToJson(Object obj) {
        return new Gson().toJson(obj);
    }

    public static void main(String... args) throws Exception {
        Person p = new Person();
        p.setName("Pedro");
        p.setAge(29);
        p.setEmail("pedro@correo.com");
        p.setGender(PersonFacade.GENDER_M);
        p.setSurname("Perez");
        p.setPhone(new Long("3105112233"));
        p.setIdentificationNumber("123456");
        
        Office o = new Office();
        o.setAddress("calle 123 #45-67");
        o.setCity("Bogotá");
        o.setName("Oficina Dragón");
        o.setCreatedAt(new Date());
        o.setUpdatedAt(new Date());
        
        
        Employee e = new Employee();
        e.setPosition("CAJERO");        
        e.setAdmitionDate(new Date());        
        e.setIdPerson(p);
        e.setIdOffice(o);
                        
       
        System.out.println(objectToJson(e));

        String json = "{\n"
                + "  \"username\": \"admin\",\n"
                + "  \"password\": \"123\"\n"
                + "}";
        
        JsonUtil.jsonToObject(json, UserAccount.class);

    }

}
