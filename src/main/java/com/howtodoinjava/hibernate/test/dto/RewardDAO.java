package com.howtodoinjava.hibernate.test.dto;

import jakarta.persistence.Query;
import org.hibernate.Session;

import java.util.List;

public class RewardDAO {
    public void addReward(Session session, Integer studentID, Reward reward){
        addRewardToStudent(session, studentID, reward);
        session.beginTransaction();
        session.persist(reward);
        session.getTransaction().commit();
    }


    public void addRewardToStudent(Session session, Integer studentID, Reward reward){
        Query hqlQuery = session.createQuery("from Student s where s.id = :id");
        hqlQuery.setParameter("id", studentID);
        List<Student> students = hqlQuery.getResultList();
        students.forEach(s -> s.getRewards().add(reward));
    }

}
