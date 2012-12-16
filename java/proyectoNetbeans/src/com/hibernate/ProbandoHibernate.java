package com.hibernate;

import java.util.*;
import org.hibernate.Session;
import com.hibernate.HibernateUtil;
import com.hibernate.beans.Event;


public class ProbandoHibernate {

    public static void main (String[] args){
        //Creamos un evento Fiesta
        crearEvento("Fiesta", new Date());

    }
    
    
    public static void crearEvento(String title, Date date){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
        Event e = new Event();
        e.setTitle(title);
        e.setDate(date);
        
        session.save(e);
        
        session.getTransaction().commit();
    }
}
