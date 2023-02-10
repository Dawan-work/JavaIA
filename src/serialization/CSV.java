package serialization;

import objets.generic.Serializer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CSV implements Serializer {
    private static final String FILENAME = "src/serialization/Annuaire.csv";
    private static final String DELIMITER = ";";
    private static final String CONTACT_DELIMITER = ",";
    private static final String SEPARATOR = "\n";

    public static void main(String[] args) {
        CSV serializer = new CSV();
        Annuaire annuaire;
        try {
            annuaire = serializer.importAnnuaire();
        } catch (IOException e) {
            System.out.println("Can't import Annuaire.\nExporting a new one then trying again");
            serializer.exportAnnuaire(Annuaire.createAnnuaire());
            try {
                annuaire = serializer.importAnnuaire();
            } catch (IOException ex) {
                System.out.println("Can't import Annuaire.\nExiting program");
                return;
            }
        }
        System.out.println(annuaire);
    }

    @Override
    public void exportAnnuaire(Annuaire annuaire) {
        try (FileWriter fw = new FileWriter(FILENAME)) { // Try with Resources // using
            fw.append(annuaire.getName());
            fw.append(DELIMITER);
            appendContact(annuaire.getOwner(), fw);
            fw.append(SEPARATOR);
            annuaire.getContacts().forEach(person -> {
                try {
                    appendContact(person,fw);
                    fw.append(SEPARATOR);
                } catch (IOException e) {
                    System.out.println("Fail to append : " + person);
                }
            });
        } catch (IOException e) {
            System.out.println("Something went wrong : " + e.getMessage());;
        }
    }

    private static void appendContact(Contact contact, FileWriter fw) throws IOException {
        fw.append(contact.getName());
        fw.append(CONTACT_DELIMITER);
        fw.append(Objects.toString(contact.getTel()));
    }

    @Override
    public Annuaire importAnnuaire() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line = br.readLine();
            String[] annuaireInfos = line.split(DELIMITER);
            String name = annuaireInfos[0];
            Contact owner = readContact(annuaireInfos[1]);
            List<Contact> contacts = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                contacts.add(readContact(line));
            }
            return new Annuaire(name,owner,contacts);
        }
    }

    private static Contact readContact(String contactString) {
        String[] contactInfo = contactString.split(CONTACT_DELIMITER);
        return new Contact(contactInfo[0], Long.parseLong(contactInfo[1]));
    }
}
