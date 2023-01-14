package banking;



import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "clienti")
public class Client extends Persoana {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    @OneToMany(
            mappedBy = "client",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private ArrayList<Tranzactii> tranzactiiList = new ArrayList<>();

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Cont cont;

    public Client() {
        super();
    }

    public Client(String firstName, String lastName, String address, String phone, String tara, String username, String password, ArrayList<Tranzactii> tranzactiiList) {
        super(firstName, lastName, address, phone, tara);
        this.username = username;
        this.password = password;
        this.tranzactiiList = tranzactiiList;
    }

    public Client(Integer id,String firstName, String lastName, String address, String phone, String tara, String username, String password) {
        super(firstName, lastName, address, phone, tara);
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Client(String firstName, String lastName, String address, String phone, String tara, String username, String password) {
        super(firstName, lastName, address, phone, tara);
        this.username = username;
        this.password = password;
    }



    public void addTranzactie(Tranzactii tranzactii1) {
        tranzactiiList.add(tranzactii1);
        tranzactii1.setClient(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Tranzactii> getTranzactiiList() {
        return tranzactiiList;
    }

    public void setTranzactiiList(ArrayList<Tranzactii> tranzactiiList) {
        this.tranzactiiList = tranzactiiList;
    }
}
