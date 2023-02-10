package jdbc.utils;

import jdbc.connector.DbConnector;
import jdbc.dao.Dao;
import jdbc.models.*;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

public class TableManager {
    private static final Map<String,String> SQL_TYPES = Map.of(
            "String","VARCHAR(255)",
            "int","INT(10)",
            "double","DECIMAL(10,2)",
            "Role", "VARCHAR(15)",
            "long", "INT(10) NOT NULL AUTO_INCREMENT"
    );
    public static void createTable(Class<? extends BaseEntity> clazz) {
        try (Statement s = DbConnector.getInstance().createStatement()) {
            String SQL = String.format(
                    "CREATE TABLE IF NOT EXISTS %s\n(%s, PRIMARY KEY ( id ))",
                    clazz.getSimpleName(),
                    getTableStructure(clazz)
            );
            s.execute(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String getTableStructure(Class<?> clazz) {
        return getAllFields(clazz).stream().map(field ->
                field.getName() + " " +
                        Objects.toString(SQL_TYPES.get(field.getType().getSimpleName()),
                                "TEXT")
        ).collect(Collectors.joining(", "));
    }
    public static String getCreateReq(Class<?> clazz) {
        List<Field> fields = getAllFields(clazz);
        return String.format("INSERT INTO %s(%s) VALUES(%s)",
                clazz.getSimpleName(),
                fields.stream().map(Field::getName).collect(Collectors.joining(",")),
                fields.stream().map(field -> "?").collect(Collectors.joining(",")));
    }
    public static String getUpdateReq(Class<?> clazz, BaseEntity object) {
        List<Field> fields = getAllFields(clazz);
        return String.format("UPDATE %s SET %s WHERE id = %d",
                clazz.getSimpleName(),
                fields.stream().skip(1).map(field -> field.getName()+ " = ?")
                        .collect(Collectors.joining(", ")),
                object.getId());
    }
    public static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        Class<?> parentClass = clazz.getSuperclass();
        if(parentClass != null) {
            fields.addAll(getAllFields(parentClass));
        }
        Field[] fieldsArray = clazz.getDeclaredFields(); // Class fields
        fields.addAll(Arrays.stream(fieldsArray).toList());
        return fields;
    }

    public static void main(String[] args) {
        // getAllFields(Personne.class).stream().map(field -> field.getType().getSimpleName()).forEach(System.out::println);
        createTable(Personne.class);
        createTable(Marchandise.class);
        createTable(Facture.class);
        System.out.println(getCreateReq(Marchandise.class));
        System.out.println(getUpdateReq(Marchandise.class, new Marchandise(12L,5,"une marchandise",1234.25)));
        try {
            Dao<Personne> personneDao = new Dao<>(Personne.class);
            Dao<Marchandise> marchandiseDao = new Dao<>(Marchandise.class);
            Dao<Facture> factureDao = new Dao<>(Facture.class);

            Personne toAdd = new Personne(0L, 0, "ADEKALOM", "Yanis", Role.ADMIN);
            personneDao.create(toAdd);
            Marchandise marchandise = new Marchandise(0L,5,"une marchandise",34.25);
            marchandiseDao.create(marchandise);
            Facture facture = new Facture(0L, 0, toAdd,marchandise, 7);
            factureDao.create(facture);
            factureDao.findAll().forEach(fact -> System.out.printf("Client : %s, Merch : %s, Total : %.2f €\n",
                    fact.getClient().getNom(),
                    fact.getProduit().getDesignation(),
                    fact.getQuantite() * fact.getProduit().getCout()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
