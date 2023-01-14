package banking.jpa;

import banking.Client;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class ClientRepositoryImpl implements ClientRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private String sqlDefaultText = "SELECT o FROM Client o";

    public ClientRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public ClientRepositoryImpl() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        this.entityManager = emf.createEntityManager();
    }

    //-------------------------------------------------------------//
    /* Operatii de cautare conventionale */


    @Override
    public Client getClientDupaUsername(String username) {
        return this.entityManager
                .createQuery(sqlDefaultText + " WHERE o.username = :username", Client.class)
                .setParameter("numeProiect", username)
                .getSingleResult();
    }

    @Override
    public List<Client> getClientiDupaUsername(String username) {
        return this.entityManager
                .createQuery(sqlDefaultText + " WHERE o.username LIKE :username", Client.class)
                .setParameter("username", username)
                .getResultList();
    }

    @Override
    public void refresh(Client client) {
        this.entityManager.refresh(client);
    }

    @Override
    public void remove(Client client) {

        Client clientPersistent = entityManager.find(Client.class, client.getId());
        try {
            entityManager.getTransaction().begin();
            if (clientPersistent != null)
                this.entityManager.remove(client);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            if (entityManager.getTransaction().isActive())
                entityManager.getTransaction().rollback();
            throw new RuntimeException(ex.getMessage());
        }

    }

    @Override
    public void add(Client client) {
        try {
            Client clientPersistent = entityManager.find(Client.class, client.getUsername());
            entityManager.getTransaction().begin();
            if (clientPersistent != null) {
                System.out.println(">>>> to update-merge client..." + client);
                this.entityManager.merge(client);
            } else {
                this.entityManager.persist(client);
                System.out.println(">>>> to insert proiect..." + client);
            }
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            if (entityManager.getTransaction().isActive())
                entityManager.getTransaction().rollback();
            throw new RuntimeException(ex.getMessage());
        }

    }

    @Override
    public Client getClientDupaId(Integer id) {
        Client c = this.entityManager.find(Client.class, id);
        this.entityManager.refresh(c);
        return c;
    }


    @Override
    public Collection<Client> getAll() {
        List<Client> result = this.entityManager
                .createQuery(this.sqlDefaultText, Client.class)
                .getResultList();

        TreeSet<Client> entitatiOrdonate = new TreeSet<>(Comparator.comparing(Client::getUsername));
        entitatiOrdonate.addAll(result);

        return entitatiOrdonate;
    }

    @Override
    public void removeAll() {
        try {
            entityManager.getTransaction().begin();
        //    entityManager.createQuery("DELETE FROM 'Clienti'").executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            if (entityManager.getTransaction().isActive())
                entityManager.getTransaction().rollback();
            throw new RuntimeException(ex.getMessage());
        }

    }

    @Override
    public Client build(Integer idClient, String firstName, String lastName, String address, String phone, String tara, String username, String password) {
        return null;
        //todo: implement save
    }

    @Override
    public Client build() {
        return null;
    }
}
