package banking.test;

import banking.Client;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestClienti {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();


        em.getTransaction().begin();
        // clear
       // em.createQuery("DELETE FROM clienti").executeUpdate();
        em.getTransaction().commit();

         Client c1 = new Client("Andrei","Cosmin","Petricani","0742929299","Romanaia","cchelariu","pass");
        Client c2 = new Client("Andrei","Cosmin","Petricani","0742929299","Romanaia","cchelariu","pass");
        Client c3 = new Client("Andrei","Cosmin","Petricani","0742929299","Romanaia","cchelariu","pass");
        List<Client> lstClienti = Stream.of(c1, c2, c3).collect(Collectors.toList());

        // Inainte de persistenta
        System.out.println("Inainte de persistenta");
        printEntitateClient(lstClienti);

        // Asociere context persistenta
        lstClienti.forEach(c -> em.persist(c));
        System.out.println("Dupa asociere context persistenta");
        printEntitateClient(lstClienti);

        // Sincronizare SQL: executie fraze DML-SQL
        em.getTransaction().begin();
        em.flush();
        System.out.println("Dupa Sincronizare SQL: executie fraze DML-SQL");
        printEntitateClient(lstClienti);

        // Efectuare transactie
        em.getTransaction().commit();
        System.out.println("Dupa efectuare tranzactie");
        printEntitateClient(lstClienti);

        System.out.println("End");

    }

    static void printEntitateClient( List<Client> clienti) {
        System.out.println("-------------------------------");
        clienti.stream().forEach(
                c -> System.out.println(c.getClass().getSimpleName() + ": " + c));
        System.out.println("-------------------------------");

    }

    static void clearSchema() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager emDDL = emf.createEntityManager();
        System.out.println(">>> CLEAR SCHEMA DROP TABLES");
        List<String> ddlDropCommands = new ArrayList<String>();

        ddlDropCommands.add("DROP TABLE clienti CASCADE");
        // clear-drop
        for (String ddl: ddlDropCommands)
            try {
                emDDL.getTransaction().begin();
                emDDL.createNativeQuery(ddl).executeUpdate();
                emDDL.getTransaction().commit();
            }catch(Exception ex) {
                System.out.println(ex.getMessage());
                emDDL.getTransaction().rollback();
            }

        emDDL.close();
        emf.close();
    }
}
