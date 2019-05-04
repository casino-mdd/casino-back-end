/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdd.casino.util;

import com.google.gson.Gson;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import mdd.casino.jpa.entity.facade.PersonFacade;
import mdd.casino.jpa.entity.pojo.Employee;
import mdd.casino.jpa.entity.pojo.Person;

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
        p.setPhone(new Long(34567890));
               
       
        System.out.println(objectToJson(p));
      
    }

}
