/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdd.casino.util;

import javax.naming.Context;
import javax.naming.InitialContext;

/**
 *
 * @author dagofonseca
 */
public class BeanUtil {

    /*public static Object lookupPersonFacadeBean(String facadeName) {
        try {
            javax.naming.Context c = new InitialContext();
            return (PersonFacade) c.lookup("java:global/casino_mdd/casino_mdd-ejb/" + facadeName + "!mdd.casino.jpa.entity.facade." + facadeName);
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }*/
    public static <T> T lookupFacadeBean(Class<T> clase) {
        try {
            Context c = new InitialContext();
            String appName = "casino_mdd";//(String) c.lookup("java:app/AppName");

            return (T) c.lookup("java:global/" + appName + "/casino_mdd-ejb/" + clase.getSimpleName() + "!" + clase.getName() + "");
        } catch (Exception e) {
            //Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            System.out.println("FALLA, en LOOKUP llamando EJB->" + e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
