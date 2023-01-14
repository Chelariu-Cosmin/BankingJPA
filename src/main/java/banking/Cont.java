package banking;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.util.List;

@Entity
@Table(name = "cont")
public class Cont {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String denumireBanca;

    private String moneda;

    private String IBAN;

    private String CSV;

    private String sold;

    private boolean status;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;


    @OneToMany(
            mappedBy = "cont",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Tranzactii> lstTranzactii;

    public Cont() {
    }

    public List<Tranzactii> getLstTranzactii() {
        return lstTranzactii;
    }

    public void setLstTranzactii(List<Tranzactii> lstTranzactii) {
        this.lstTranzactii = lstTranzactii;
    }

    public Cont(String denumireBanca, String moneda, String IBAN, String CSV, String sold, boolean status) {
        this.denumireBanca = denumireBanca;
        this.moneda = moneda;
        this.IBAN = IBAN;
        this.CSV = CSV;
        this.sold = sold;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenumireBanca() {
        return denumireBanca;
    }

    public void setDenumireBanca(String denumireBanca) {
        this.denumireBanca = denumireBanca;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCSV() {
        return CSV;
    }

    public void setCSV(String CSV) {
        this.CSV = CSV;
    }

    public String getSold() {
        return sold;
    }

    public void setSold(String sold) {
        this.sold = sold;
    }
}
