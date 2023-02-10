package objets.generic;

import serialization.Annuaire;

import java.io.IOException;

public interface Serializer {
    Annuaire importAnnuaire() throws IOException, ClassNotFoundException;
    void exportAnnuaire(Annuaire annuaire);
}
