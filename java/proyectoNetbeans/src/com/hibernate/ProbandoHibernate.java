package com.hibernate;

import java.util.*;
import org.hibernate.Session;
import com.hibernate.HibernateUtil;
import com.hibernate.beans.Event;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

//http://www.davidmarco.es/tutoriales/hibernate-reference/
public class ProbandoHibernate {

    public static void main (String[] args){
        //Creamos un evento Fiesta
        crearEvento("Fiesta", new Date());
        Event e = getUltimoEventoInsertado();
        modificarEvento(e,"NuevoTitulo", new Date());

    }
    
    
    public static void crearEvento(String title, Date date){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
        Event e = new Event();
        e.setTitle(title);
        e.setDate(date);
        
        session.save(e);
        
        session.getTransaction().commit();
        
        //session.close();
    }

    private static Event getUltimoEventoInsertado() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Event e = null;
        
        //La siguiente linea obtiene el ultimo objeto
        e = (Event)session.createCriteria(Event.class).addOrder(Order.desc("id")).setMaxResults(1).uniqueResult();
        //Las siguientes lineas lineas tambien obtienen el ultimo objeto
        /*Criteria criteria = session
            .createCriteria(Event.class)
            .setProjection(Projections.max("id"));
        Long id = (Long)criteria.uniqueResult();
        
        e = (Event)session.get(Event.class,id);
        */
        
        session.getTransaction().commit();
        //session.close();
        return e;
    }

    private static void modificarEvento(Event e, String nuevoTitulo, Date date) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        e.setTitle(nuevoTitulo);
        e.setDate(date);
        
        
        session.update(e);
        tx.commit();
    }
}
