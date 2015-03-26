/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import java.util.ArrayList;
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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryJPA");
        EntityManager em = emf.createEntityManager();

        //start of insertion
        Query query = em.createQuery("Select b from Book b");
        
        
        List<Book> books = query.getResultList();
        for (Book b : books) {
            System.out.println("Book: " + b.getTitle());

        }
    }
}
