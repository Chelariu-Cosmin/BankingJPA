package banking.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import banking.Client;
import banking.jpa.ClientRepositoryImpl;

public class TestCrud {
    public static void main(String[] args) {

        jpaRepository_test();
    }

    private static void jpaRepository_test() {

    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    	EntityManager em = emf.createEntityManager();
        ClientRepositoryImpl clientRepository = new ClientRepositoryImpl();
        Client client = null;

        // Cleanup: REMOVE: Stergere proiecte existente
        System.out.println("Remove clienti!");
        clientRepository.getAll().forEach(p -> clientRepository.remove(p));


        // READ: Afisare toate proiectele existente
        System.out.println("READ proiecte initiale!");
        clientRepository.getAll().forEach(p -> System.out.println("Proiect entitate persistenta: " + p));

        // Start Tranzactie: CREATE-UPDATE-DELETE ---------------- //
        System.out.println("Start Tranzactie: CREATE-UPDATE-DELETE ----------------");

        // CREATE: Salvare proiecte noi
        System.out.println("CREARE proiecte!");
        //BUILD method
        for(int i=1; i <= 4; i++){
            client = new ClientRepositoryImpl().build();
            clientRepository.add(client);
        }

        // UPDATE: Interogare proiect, modificare proiect, salvare proiect existent
        System.out.println("UPDATE proiecte!");
        client = clientRepository.getClientDupaId(1);
        client.setUsername(client.getUsername() + " - MODIFICAT");
        clientRepository.add(client);


        // DELETE: Stergere entitate
        System.out.println("DELETE proiect!");
        Client proiectToRemove = clientRepository.getClientDupaUsername("Matei");
        System.out.println(" - proiect to remove: " + proiectToRemove);
        clientRepository.remove(proiectToRemove);

        // END Tranzactie: CREATE-UPDATE-DELETE ---------------- //
        System.out.println("END Tranzactie: CREATE-UPDATE-DELETE ----------------");

        // READ: Afisare toate proiectele existente
        System.out.println("READ proiecte finale!");
        clientRepository.getAll().forEach(p -> System.out.println("Proiect entitate persistenta: " + p));
    }
}
