package jdbc.models;

import java.io.Serializable;

public class Facture extends BaseEntity implements Serializable {
    private Personne client;
    private Marchandise produit;
    private int quantite;

    public Facture() {
    }

    public Facture(long id, int version, Personne client, Marchandise produit, int quantite) {
        super(id, version);
        this.client = client;
        this.produit = produit;
        this.quantite = quantite;
    }

    public Personne getClient() {
        return client;
    }

    public void setClient(Personne client) {
        this.client = client;
    }

    public Marchandise getProduit() {
        return produit;
    }

    public void setProduit(Marchandise produit) {
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
