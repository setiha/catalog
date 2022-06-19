package com.howtodoinjava.hibernate.test.dto;

import jakarta.persistence.Query;
import org.hibernate.Session;
import java.util.List;
import java.util.Scanner;

public class ProfessorDAO {

    public void addProfessor(Professor professor, Session session){
        session.beginTransaction();
        session.persist(professor);
        session.getTransaction().commit();
    }

    public void addProfessor(Professor professor, Session session, Integer classID){
        addProfessorToClass(session, classID, professor);
        session.beginTransaction();
        session.persist(professor);
        session.getTransaction().commit();
    }

    private void addProfessorToClass(Session session, Integer classID, Professor professor) {
        Query hqlQuery = session.createQuery("from Class c where c.id = :id");
        hqlQuery.setParameter("id", classID);
        List<Class> classes = hqlQuery.getResultList();
        if (classes.get(0).getProfessor()==null) {
            classes.get(0).setProfessor(professor);
        } else {
            System.out.print("Class " + classes.get(0).getName() + " has a professor (" + classes.get(0).getProfessor().getFirstName() + " " +classes.get(0).getProfessor().getLastName() + "). Do you want to replace the professor?\n"
                    + "1. Yes\n"
                    + "2. No\n"
                    + "Select: ");
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            if (option == 1){
                classes.get(0).setProfessor(professor);
            }
        }

    }

    public void updateProfessor(Session session, String firstName, String lastName, int experience) {

        session.beginTransaction();
        Query hqlQuery = session.createQuery("from Professor p where p.firstName = :firstName and p.lastName = :lastName");

        hqlQuery.setParameter("firstName", firstName);
        hqlQuery.setParameter("lastName", lastName);

        List<Professor> professors = hqlQuery.getResultList();
        professors.forEach(p -> {
            p.setExperience(experience);
            session.persist(p);
        });

        session.getTransaction().commit();
    }

    public void displayAllProfessors (Session session){
        session.beginTransaction();
        List<Professor> professors = session.createQuery("from Professor", Professor.class).list();
        System.out.println(professors);
        session.getTransaction().commit();
    }

    public List<Professor> displayProfessorWithMostExperience (Session session){
        session.beginTransaction();
        List<Professor> professors = session.createQuery("FROM Professor " +
                "WHERE experience IN (SELECT MAX(experience) " +
                "from Professor)", Professor.class).list();
        System.out.println(professors);

        System.out.println(professors);
        session.getTransaction().commit();

        return professors;
    }

}