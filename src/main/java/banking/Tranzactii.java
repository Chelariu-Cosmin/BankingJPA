package banking;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "tranzactii")
public class Tranzactii {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String denumire;

    private String descriere;

    private Double total;

    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cont cont;


    public Tranzactii(String denumire, String descriere, Double total) {
        this.denumire = denumire;
        this.descriere = descriere;
        this.total = total;
    }

    public Tranzactii() {
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tranzactii that = (Tranzactii) o;

        if (getId() != that.getId()) return false;
        if (getDenumire() != null ? !getDenumire().equals(that.getDenumire()) : that.getDenumire() != null)
            return false;
        if (getDescriere() != null ? !getDescriere().equals(that.getDescriere()) : that.getDescriere() != null)
            return false;
        if (getTotal() != null ? !getTotal().equals(that.getTotal()) : that.getTotal() != null) return false;
        return getClient() != null ? getClient().equals(that.getClient()) : that.getClient() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getDenumire() != null ? getDenumire().hashCode() : 0);
        result = 31 * result + (getDescriere() != null ? getDescriere().hashCode() : 0);
        result = 31 * result + (getTotal() != null ? getTotal().hashCode() : 0);
        result = 31 * result + (getClient() != null ? getClient().hashCode() : 0);
        return result;
    }
}
