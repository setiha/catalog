package com.howtodoinjava.hibernate.test.dto;

import com.howtodoinjava.hibernate.test.HibernateUtil;
import org.hibernate.Session;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        int menuOption;
        do {
            displayMenu();
            menuOption = menuSelect();
            switch (menuOption) {
                case 1 -> {
                    StudentDAO studentDAO = new StudentDAO();
                    studentDAO.addStudent(insertStudent(), session, Integer.parseInt(getInput("Class id: ")));
                }
                case 2 -> {
                    ProfessorDAO professorDAO = new ProfessorDAO();
                    professorDAO.addProfessor(insertProfessor(), session, Integer.parseInt(getInput("Class id: ")));
                }
                case 3 -> {
                    ClassDAO classDAO = new ClassDAO();
                    classDAO.addClass(insertClass(), session);
                }
                case 4 -> {
                    StudentDAO studentDAO = new StudentDAO();
                    studentDAO.updateStudent(session, getInput("First name:"), getInput("Last name:"), Double.parseDouble(getInput("New Grade:")));

                }
                case 5 -> {
                    ProfessorDAO professorDAO = new ProfessorDAO();
                    professorDAO.updateProfessor(session, getInput("First name:"), getInput("Last name;"), Integer.parseInt(getInput("Experience:")));
                }
                case 6 -> {
                    ClassDAO classDAO = new ClassDAO();
                    classDAO.displayAllStudents(session, getInput("Class name:"));
                }
                case 7 -> {
                    ClassDAO schoolClassDAO = new ClassDAO();
                   schoolClassDAO.displayAllClasses(session);
                }
                case 8 -> {
                    StudentDAO studentDAO = new StudentDAO();
                    studentDAO.displayAllStudentsAboveGrade(session, Double.parseDouble(getInput("Grade:")));
                }
                case 9 -> {
                    ProfessorDAO professorDAO = new ProfessorDAO();
                    professorDAO.displayAllProfessors(session);
                }
                case 10 -> {
                    ProfessorDAO professorDAO = new ProfessorDAO();
                    System.out.println(professorDAO.displayProfessorWithMostExperience(session));
                }
                case 11 -> {
                    RewardDAO rewardDAO = new RewardDAO();
                    rewardDAO.addReward(session,Integer.parseInt(getInput("Student id")),insertReward());
                }
                case 12 -> {
                    ClassDAO classDAO = new ClassDAO();
                    List<Class> classes = classDAO.getAllClasses(session);

                    try (FileWriter fw
                                 = new FileWriter("backup/backup.txt")) {
                        classes.forEach(c -> {
                            try {
                                fw.write(c.toString());
                                fw.write("\n\n....\n\n");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                case 0 -> System.out.println("Closing catalog");
                default -> System.out.println("\nOption not available. Please select again\n");
            }

        } while (menuOption != 0);

        HibernateUtil.shutdown();
    }

    public static void displayMenu() {
        System.out.println("Catalog\n");
        System.out.println("1. Add Student");
        System.out.println("2. Add Professor");
        System.out.println("3. Add Class");
        System.out.println("4. Update Student");
        System.out.println("5. Update Professor");
        System.out.println("6. Display all students form a class");
        System.out.println("7. Display all classes");
        System.out.println("8. Display all students based on grade");
        System.out.println("9. Display all professors\n");
        System.out.println("10. Display the professor with most experience\n");
        System.out.println("11. Add a reward");
        System.out.println("12. Add new file");
        System.out.println("0. Exit\n");
        System.out.print("Select:");
    }

    public static int menuSelect() {
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        try {
            option = scanner.nextInt();
        } catch (Exception e) {
            return option;
        }
        return option;
    }

    public static Student insertStudent() {

        Student student = new Student();

        System.out.println("Insert student details");

        student.setFirstName(getInput("First name:"));
        student.setLastName(getInput("Last name:"));
        student.setGrade(Double.parseDouble(getInput("Grade:")));

        return student;
    }



    public static Professor insertProfessor() {
        Professor professor = new Professor();

        System.out.println("Insert professor details");

        professor.setFirstName(getInput("First name:"));
        professor.setLastName(getInput("Last name:"));
        professor.setExperience(Integer.parseInt(getInput("Experience:")));

        return professor;
    }

    public static Class insertClass() {
        Class schoolClass = new Class();

        System.out.println("Insert class details");
        schoolClass.setName(getInput("Class name:"));
        schoolClass.setProfile(getInput("Class profile:"));

        return schoolClass;
    }

    public static Reward insertReward() {
        Reward schoolReward = new Reward();

        System.out.println("Insert reward details");
        schoolReward.setName(getInput("Reward s name:"));

        return schoolReward;
    }

    public static String getInput(String message) {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();

    }

    public static Integer getClassId(String classId){
        System.out.print(classId);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
