package com.howtodoinjava.hibernate.test.dto;

import jakarta.persistence.Query;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class StudentDAO {

    public void addStudent(Student student, Session session, Integer classId) {
        addStudentToClass(session, classId, student);
        session.beginTransaction();
        session.persist(student);
        session.getTransaction().commit();
    }

    public void updateStudent(Session session, String firstName, String lastName, Double grade) {

        session.beginTransaction();
        Query hqlQuery = session.createQuery("from Student s where s.firstName = :firstName and s.lastName = :lastName");

        hqlQuery.setParameter("firstName", firstName);
        hqlQuery.setParameter("lastName", lastName);

        List<Student> students = hqlQuery.getResultList();
        students.forEach(s -> {
            s.setGrade(grade);
            session.persist(s);
        });
        session.getTransaction().commit();
    }

    public void displayAllStudentsAboveGrade(Session session, double grade){
        session.beginTransaction();
        Query hqlQuery = session.createQuery("from Student s where s.grade >= :grade");
        hqlQuery.setParameter("grade", grade);
        List<Student> students = hqlQuery.getResultList();
        System.out.println(students);
        session.getTransaction().commit();
    }

    public void addStudentToClass(Session session, Integer classId, Student student){
        Query hqlQuery = session.createQuery("from Class c where c.id = :id");
        hqlQuery.setParameter("id", classId);
        List<Class> classes = hqlQuery.getResultList();
        classes.forEach(c -> c.getStudentList().add(student));
    }
}
//    public void displayAllStudents(Session session, String className){
//        session.beginTransaction();
//        Class schoolClass = session.find(Class.class, )
//    }


   /* Query hqlQuery = session.createQuery("from Booking b where b.numarCarti > 100");
    List<Booking> booking = hqlQuery.getResultList();
        System.out.println(booking);*/