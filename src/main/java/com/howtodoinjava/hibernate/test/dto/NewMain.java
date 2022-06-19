package com.howtodoinjava.hibernate.test.dto;

import com.howtodoinjava.hibernate.test.HibernateUtil;
import org.hibernate.Session;

public class NewMain {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();


        Student booking6 = session.find(Student.class, 1);
        booking6.setGrade(9.55);
        session.persist(booking6);
        /*Booking booking7 = session.find(Booking.class, 2);
        booking7.setNumarCarti(100);
        session.persist(booking7);


        List<Booking> from_school = session.createQuery("from Booking ", Booking.class).list();
        System.out.println(from_school);

        Query hqlQuery = session.createQuery("from Booking b where b.numarCarti > 100");
        List<Booking> booking = hqlQuery.getResultList();
        System.out.println(booking);*/


        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }
}
