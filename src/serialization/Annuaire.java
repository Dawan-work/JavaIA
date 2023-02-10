package serialization;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class Annuaire implements Serializable { // JavaBean != Plain Old Java Object
    private String name;

    private Contact owner; // Must implements Serializable

    private List<Contact> contacts; // List implements Serializable (List type must implements Serializable)
    public Annuaire() {
    }

    public Annuaire(String name, Contact owner, List<Contact> contacts) {
        this.name = name;
        this.owner = owner;
        this.contacts = contacts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact getOwner() {
        return owner;
    }

    public void setOwner(Contact owner) {
        this.owner = owner;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return String.format("Name : %s\nOwner : %s\nContacts : {\n\t%s\n}",
                name,
                owner.getName(),
                contacts.stream()
                        .map(Objects::toString)
                        .collect(Collectors.joining("\n\t")));
    }

    public static Annuaire createAnnuaire() {
        List<Contact> contacts = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            contacts.add(new Contact("Personne " + i, random.nextLong(6000, Long.MAX_VALUE)));
        }
        return new Annuaire("Mon Annuaire", new Contact("Yanis", 549685), contacts);
    }
}
