/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mdd.casino.jpa.entity.facade;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import org.hibernate.Transaction;
import org.hibernate.impl.SessionImpl;

/**
 *
 * @author root
 */
public abstract class AbstractFacade<T> {

    @PersistenceUnit
    private EntityManagerFactory emf;
    private Transaction tx;
    public SessionImpl sess;
    private EntityManager em_;

    protected EntityManager getEntityManager2() {
        return emf.createEntityManager();
    }
    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    protected void beginTransaction() {
        //Obteniendo sesion
        em_ = getEntityManager2();
        sess = (SessionImpl) em_.getDelegate();
        tx = sess.getTransaction();

        //Empezando Transaccion
        tx.begin();
    }

    protected void commitTransaction() {
        tx.commit();
    }

    protected void rollbackTransaction() {
        try {
            tx.rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void rollbackTransaction(StringBuilder err) {
        if (!err.toString().isEmpty()) {
            try {
                tx.rollback();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void endTransaction() {
        //Cerrando conexion
        if (em_ != null) {
            em_.close();
        }
    }

    protected SessionImpl getSess() {
        return sess;
    }

    public T create(T entity) throws Exception {

        //Obteniendo sesion
        beginTransaction();

        //Realizando Operacion
        try {
            sess.save(entity);
            commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();

            rollbackTransaction();
            System.out.println("FALLA, editando registro !" + e);

            throw e;
        }

        //Cerrando conexion
        endTransaction();
        return entity;
    }

    public T edit(T entity) throws Exception {

        //Obteniendo sesion
        beginTransaction();

        //Realizando Operacion
        try {
            sess.update(entity);

            commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();

            rollbackTransaction();
            System.out.println("FALLA, editando registro !" + e);

            throw e;
        }

        //Cerrando conexion
        endTransaction();
        return entity;

    }

    public void remove(T entity) {
        getEntityManager().remove(entity);
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    private List<T> findList(String hql, boolean all, int firstReg, int maxReg) {
        EntityManager em = getEntityManager();
        List l = new ArrayList();
        try {
            Query q = em.createQuery(hql);
            if (!all) {
                q.setMaxResults(maxReg);
                q.setFirstResult(firstReg);
            }
            l = q.getResultList();
        } catch (Exception e) {
            System.out.println("Supertree3 dice FALLA, ejecutando HQL: " + hql);
            e.printStackTrace();
        }
        return l;
    }

    public List<T> findList(String hql, int firstReg, int maxReg) {
        return findList(hql, false, firstReg, maxReg);
    }

    public List<T> findList(String hql) {
        return findList(hql, true, 0, 0);
    }

    protected long countNative(String sql) {
        EntityManager em = getEntityManager();

        long cuenta = 0;
        try {
            Query q = em.createNativeQuery(sql);

            Object result = q.getSingleResult();

            if (result != null) {
                cuenta = Long.parseLong(result.toString());
            }
        } catch (Exception e) {
            System.out.println("Supertree3 dice FALLA, ejecutando SQL: " + sql);
            e.printStackTrace();
        }
        return cuenta;
    }

    protected long countGeneric(String hql) {
        EntityManager em = getEntityManager();

        long cuenta = 0;
        try {
            Query q = em.createQuery(hql);

            Object result = q.getSingleResult();

            if (result != null) {
                cuenta = Long.parseLong(result.toString());
            }
        } catch (Exception e) {
            System.out.println("Supertree3 dice FALLA, ejecutando HQL: " + hql);
            e.printStackTrace();
        }
        return cuenta;
    }

    private List findGeneric(String hql, boolean all, int firstReg, int maxReg) {
        EntityManager em = getEntityManager();
        List l = new ArrayList();
        try {
            Query q = em.createQuery(hql);
            if (!all) {
                q.setMaxResults(maxReg);
                q.setFirstResult(firstReg);
            }
            l = q.getResultList();
        } catch (Exception e) {
            System.out.println("Supertree3 dice FALLA, ejecutando HQL: " + hql);
            e.printStackTrace();
        }
        return l;
    }

    protected List findGeneric(String hql, int firstReg, int maxReg) {
        return findGeneric(hql, false, firstReg, maxReg);
    }

    protected List findGeneric(String hql) {
        return findGeneric(hql, true, 0, 0);
    }

    private List<T> find(String sql, Class classResult, boolean all, int firstReg, int maxReg) {
        EntityManager em = getEntityManager();
        List l = new ArrayList();
        try {
            Query q = em.createNativeQuery(sql, classResult);
            if (!all) {
                q.setMaxResults(maxReg);
                q.setFirstResult(firstReg);
            }
            l = q.getResultList();
        } catch (Exception e) {
            System.out.println("Supertree3 dice FALLA, ejecutando SQL: " + sql);
            e.printStackTrace();
        }
        return l;
    }

    protected List<T> find(String sql, Class classResult, int firstReg, int maxReg) {
        return find(sql, classResult, false, firstReg, maxReg);
    }

    protected List<T> find(String sql, Class classResult) {
        return find(sql, classResult, true, 0, 0);
    }

    private List<T> findNative(String sql, boolean all, int firstReg, int maxReg) {
        EntityManager em = getEntityManager();
        List l2 = new ArrayList();
        try {
            Query q = em.createNativeQuery(sql);
            if (!all) {
                q.setMaxResults(maxReg);
                q.setFirstResult(firstReg);
            }
            l2 = q.getResultList();
        } catch (Exception e) {
            System.out.println("Supertree3 dice FALLA, ejecutando SQL: " + sql);
            e.printStackTrace();
        }
        return l2;
    }

    protected List<T> findNative(String sql, int firstReg, int maxReg) {
        return findNative(sql, false, firstReg, maxReg);
    }

    protected List<T> findNative(String sql) {
        return findNative(sql, true, 0, 0);
    }

    private List findNative(String sql, boolean all, int firstReg, int maxReg, Class cla) {
        EntityManager em = getEntityManager();
        List l2 = new ArrayList();
        try {
            Query q = em.createNativeQuery(sql, cla);
            if (!all) {
                q.setMaxResults(maxReg);
                q.setFirstResult(firstReg);
            }
            l2 = q.getResultList();
        } catch (Exception e) {
            System.out.println("Supertree3 dice FALLA, ejecutando SQL: " + sql);
            e.printStackTrace();
        }
        return l2;
    }

    protected List findNative(String sql, int firstReg, int maxReg, Class cla) {
        return findNative(sql, false, firstReg, maxReg, cla);
    }

    protected List findNative(String sql, Class cla) {
        return findNative(sql, true, 0, 0, cla);
    }

    private List findNativeGeneric(String sql, boolean all, int firstReg, int maxReg) {
        EntityManager em = getEntityManager();
        List l2 = new ArrayList();
        try {
            Query q = em.createNativeQuery(sql);
            if (!all) {
                q.setMaxResults(maxReg);
                q.setFirstResult(firstReg);
            }
            l2 = q.getResultList();
        } catch (Exception e) {
            System.out.println("Supertree3 dice FALLA, ejecutando SQL: " + sql);
            e.printStackTrace();
        }
        return l2;
    }

    protected List findNativeGeneric(String sql, int firstReg, int maxReg) {
        return findNativeGeneric(sql, false, firstReg, maxReg);
    }

    protected List findNativeGeneric(String sql) {
        return findNativeGeneric(sql, true, 0, 0);
    }

    protected int deleteUpdateNative(String sql) {
        EntityManager em = getEntityManager();
        Query q = em.createNativeQuery(sql);
        return q.executeUpdate();
    }

    public int delete(Integer iddr) {
        return deleteUpdateNative("DELETE FROM Descuento_Rul WHERE iddr=" + iddr);
    }

    //DELEGATE
    public List<T> findAll() {
        String hql = "SELECT obj FROM " + entityClass.getCanonicalName() + " obj ";
        return AbstractFacade.this.findList(hql);
    }

    public List<T> findAllOrderAsc(String campo) {
        String hql = "SELECT obj FROM " + entityClass.getCanonicalName() + " obj ORDER BY obj." + campo + " ASC ";
        return AbstractFacade.this.findList(hql);
    }

    public List<T> findAllOrderDesc(String campo) {
        String hql = "SELECT obj FROM " + entityClass.getCanonicalName() + " obj ORDER BY obj." + campo + " DESC ";
        return AbstractFacade.this.findList(hql);
    }

    public List<T> findBy(String campo, Object val) {
        String hql = "SELECT obj FROM " + entityClass.getCanonicalName() + " obj WHERE obj." + campo + " = '" + val + "' ORDER BY obj." + campo + " ASC";
        return AbstractFacade.this.findList(hql);
    }

    public T findObjectBy(String campo, Object val) {
        String hql = "SELECT obj FROM " + entityClass.getCanonicalName() + " obj WHERE obj." + campo + " = '" + val + "' ORDER BY obj." + campo + " ASC";
        List l = findList(hql, 0, 1);
        if (l.size() > 0) {
            return (T) l.get(0);
        } else {
            return null;
        }
    }

    protected <T> T numFromHQL(String hql, T valInit) {
        List l = findGeneric(hql);
        if (l.size() > 0 && l.get(0) != null) {
            return (T) l.get(0);
        } else {
            return valInit;
        }
    }

    protected <T> T numFromSQL(String sql, T valInit) {
        List l = findNative(sql);
        if (l.size() > 0 && l.get(0) != null) {
            return (T) l.get(0);
        } else {
            return valInit;
        }
    }

    protected T objectFromHQL(String hql) {
        List l = findGeneric(hql);
        if (l.size() > 0 && l.get(0) != null) {
            return (T) l.get(0);
        } else {
            return null;
        }
    }

    protected T objectFromSQL(String sql) {
        List l = findNative(sql, true, 0, 0, entityClass);
        if (l.size() > 0 && l.get(0) != null) {
            return (T) l.get(0);
        } else {
            return null;
        }
    }

}
