package banking.jpa;

import banking.Client;

import java.util.Collection;
import java.util.List;

public interface ClientRepository {

    Client getClientDupaUsername(String username);

    List<Client> getClientiDupaUsername(String username);

    void refresh(Client client);

    void remove(Client client);

    void add(Client client);

    Client getClientDupaId(Integer id);

    Collection<Client> getAll();

    void removeAll();

    Client build(Integer idClient, String firstName, String lastName, String address, String phone, String tara, String username, String password);

    Client build();
}
