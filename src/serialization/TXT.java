package serialization;

import objets.generic.Serializer;

import java.io.*;

public class TXT implements Serializer {
    private static final String FILENAME = "src/serialization/Annuaire.txt";

    public static void main(String[] args) {
        TXT serializer = new TXT();
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

    @Override
    public void exportAnnuaire(Annuaire annuaire) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(annuaire);
        } catch (IOException e) {
            System.out.println("Something went wrong : " + e.getMessage());
        }
    }

    @Override
    public Annuaire importAnnuaire() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
           return (Annuaire) ois.readObject();
        }
    }
}
