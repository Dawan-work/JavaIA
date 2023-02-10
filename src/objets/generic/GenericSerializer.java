package objets.generic;

import serialization.Annuaire;
import serialization.CSV;
import serialization.TXT;

import java.io.IOException;

public class GenericSerializer<T extends Serializer> {
    private final T serializer;

    public GenericSerializer(T serializer) {
        this.serializer = serializer;
    }

    public void serialize() {
        Annuaire annuaire;
        try {
            annuaire = serializer.importAnnuaire();
        } catch (Exception e) {
            System.out.println("Can't import Annuaire.\nExporting a new one then trying again");
            serializer.exportAnnuaire(Annuaire.createAnnuaire());
            try {
                annuaire = serializer.importAnnuaire();
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("Can't import Annuaire.\nExiting program");
                return;
            }
        }
        System.out.println(annuaire);
    }

    public static void main(String[] args) {
        GenericSerializer<Serializer> genericSerializer = new GenericSerializer<>(new CSV());
        System.out.println("CSV Serializer");
        genericSerializer.serialize();

        genericSerializer = new GenericSerializer<>(new TXT());
        System.out.println("TXT Serializer");
        genericSerializer.serialize();
    }
}
