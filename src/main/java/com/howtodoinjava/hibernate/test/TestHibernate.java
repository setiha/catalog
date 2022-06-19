package com.howtodoinjava.hibernate.test;

import com.howtodoinjava.hibernate.test.dto.*;
import jakarta.persistence.Query;
import org.hibernate.Session;


import java.util.List;

public class TestHibernate {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();


        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setEmail("ceva");
        employeeEntity.setFirstName("ceva");
        employeeEntity.setLastName("ceva");

        EmployeesEntityDao employeesEntityDao = new EmployeesEntityDao();
        employeesEntityDao.insertEmployee(employeeEntity, session);
        //dao data access object

        Query query = session.createQuery("from Student s where s.firstName = :firstName");
        query.setParameter("firstName", "Vasile");
        List<Student> students = query.getResultList();
        System.out.println(students);

        HibernateUtil.shutdown();
    }

}
