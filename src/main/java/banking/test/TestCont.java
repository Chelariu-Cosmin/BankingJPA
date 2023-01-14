package banking.test;

import banking.Cont;
import banking.Tranzactii;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestCont {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();


        em.getTransaction().begin();
        // clear
//        em.createQuery("DELETE FROM cont e").executeUpdate();
//        em.createQuery("DELETE FROM 'tranzactii' ").executeUpdate();
        em.getTransaction().commit();

        Cont cont = new Cont("BCR", "RON", "IBAN", "CSV", "0", true);
        List<Tranzactii> lstTranzactii = new ArrayList<>();
        lstTranzactii.add(new Tranzactii("Apa", "fara", 3.5));
        lstTranzactii.add(new Tranzactii("Pepsi", "fara", 5.5));
        lstTranzactii.add(new Tranzactii("Vin", "fara", 23.5));
        cont.setLstTranzactii(lstTranzactii);

        // Inainte de persistenta
        System.out.println("Inainte de persistenta");
        printEntitateCont(cont);

        // Asociere context persistenta
        em.persist(cont);
        cont.getLstTranzactii().forEach(m -> em.persist(m));
        System.out.println("Dupa asociere context persistenta");
        printEntitateCont(cont);

        // Sincronizare SQL: executie fraze DML-SQL
        em.getTransaction().begin();
        em.flush();
        System.out.println("Dupa Sincronizare SQL: executie fraze DML-SQL");
        printEntitateCont(cont);

        // Efectuare transactie
        em.getTransaction().commit();
        System.out.println("Dupa efectuare tranzactie");
        printEntitateCont(cont);

        System.out.println("End");

    }

    static void printEntitateCont(Cont cont) {
        System.out.println("-------------------------------");
        System.out.println("ID Cont: " + cont.getId());
        System.out.println("Tranzactii cont");
        cont.getLstTranzactii().stream().forEach(
                m -> System.out.println("    - ID Cont: " + m.getId()
                        + ", Denumire tranzactie: " + m.getDenumire()));
        System.out.println("-------------------------------");
    }
}
