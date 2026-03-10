package com.klef.fsad.exam.hibernateProject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Date;
import java.util.Scanner;

public class ClientDemo 
{
    public static void main(String[] args) 
    {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = factory.openSession();

        Scanner sc = new Scanner(System.in);

        try 
        {
            System.out.println("1. Insert Course");
            System.out.println("2. View Course by ID");

            int choice = sc.nextInt();

            if(choice == 1)
            {
                Course c = new Course("Java Programming",
                        "Core Java Concepts",
                        new Date(),
                        "Active");

                session.beginTransaction();
                session.save(c);
                session.getTransaction().commit();

                System.out.println("Course Inserted Successfully");
            }

            else if(choice == 2)
            {
                System.out.println("Enter Course ID:");
                int id = sc.nextInt();

                session.beginTransaction();
                Course c = session.get(Course.class, id);

                if(c != null)
                {
                    System.out.println("ID: " + c.getId());
                    System.out.println("Name: " + c.getName());
                    System.out.println("Description: " + c.getDescription());
                    System.out.println("Date: " + c.getDate());
                    System.out.println("Status: " + c.getStatus());
                }
                else
                {
                    System.out.println("Course Not Found");
                }

                session.getTransaction().commit();
            }

        } 
        finally 
        {
            session.close();
            factory.close();
        }
    }
}