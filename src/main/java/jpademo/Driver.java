/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpademo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author jolley
 */
public class Driver {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("studentsJPA");
        EntityManager em = emf.createEntityManager();
        
        //start of insertion
        em.getTransaction().begin();
        
        Major major = new Major();
        major.setName("English");
        
        
        //tells the entity manager to put this in the db and keep track of it
        em.persist(major);
        
        //end of insertion
        em.getTransaction().commit(); 
        
        
        //Major major = em.find(Major.class, 1);
        Query query = em.createQuery("Select m from Major m");
        List<Major> majors = query.getResultList();
        
        for (Major m : majors) {
            System.out.println("Major: " + m.getName());
            
            for (Student s: m.getStudents())
                System.out.println("\tStudent: " + s.getName());
        }
        
        
    }
}
