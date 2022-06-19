package com.howtodoinjava.hibernate.test.dto;

import jakarta.persistence.Query;
import org.hibernate.Session;


import java.util.Arrays;
import java.util.List;

public class ClassDAO {
    public void addClass(Class studentClass, Session session){
        session.beginTransaction();
        session.persist(studentClass);
        session.getTransaction().commit();
    }

    public void displayAllStudents(Session session,String className){
        session.beginTransaction();
        Query hqlQuery = session.createQuery("from Class c where c.name = :name");

        hqlQuery.setParameter("name", className);

        List<Class> classes = hqlQuery.getResultList();

        classes.forEach(c -> System.out.println(c.studentList));
    }

    public void displayAllClasses (Session session){
        session.beginTransaction();
        List<Class> classes = session.createQuery("from Class", Class.class).list();
        System.out.println(classes);
    }
    public List<Class> getAllClasses(Session session){
        session.beginTransaction();
        return session.createQuery("from Class", Class.class).list();
    }

    public void addClassFromFile(Session session){

    }

}