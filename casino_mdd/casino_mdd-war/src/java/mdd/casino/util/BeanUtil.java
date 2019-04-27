/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdd.casino.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import mdd.casino.jpa.entity.facade.PersonFacade;

/**
 *
 * @author dagofonseca
 */
public class BeanUtil {

    public static Object lookupPersonFacadeBean(String facadeName) {
        try {
            javax.naming.Context c = new InitialContext();
            return (PersonFacade) c.lookup("java:global/casino_mdd/casino_mdd-ejb/" + facadeName + "!mdd.casino.jpa.entity.facade." + facadeName);
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }

}
