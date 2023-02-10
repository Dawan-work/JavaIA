package jdbc.dao;

import jdbc.connector.DbConnector;
import jdbc.models.Personne;
import jdbc.models.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonneDao implements IPersonneDao {
    private final Connection connection;

    public PersonneDao() throws SQLException, ClassNotFoundException {
        this.connection = DbConnector.getInstance();
    }

    @Override
    public boolean create(Personne object) {
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO Personne(version,nom,prenom,role) VALUES(?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, object.getVersion());
            ps.setString(2, object.getNom());
            ps.setString(3, object.getPrenom());
            ps.setString(4, object.getRole().name());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) {
                object.setId(rs.getLong(1));
            }
            connection.commit();
            return true;
        } catch (SQLException e) {
            rollback(e);
            return false;
        }
    }

    private void rollback(Exception e) {
        try {
            e.printStackTrace(System.out);
            connection.rollback();
        } catch (SQLException ex) {
            e.printStackTrace();
        }
    }

    @Override
    public Personne findById(Long id) {
        Personne object = null;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * from Personne WHERE id = ?")) {
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            object = mapResultToPersonne(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public List<Personne> findAll() {
        List<Personne> all = new ArrayList<>();
        try (Statement s = connection.createStatement()) {
            ResultSet rs = s.executeQuery("SELECT * FROM Personne");
            while (rs.next()) {
                all.add(mapResultToPersonne(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return all;
    }

    @Override
    public boolean update(Personne object) {
        int updated = 0;
        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE Personne SET version = ?,nom = ?, prenom = ?, role = ? WHERE id = ?")) {
            ps.setInt(1, object.getVersion() + 1);
            ps.setString(2, object.getNom());
            ps.setString(3, object.getPrenom());
            ps.setString(4, object.getRole().name());
            ps.setLong(5, object.getId());
            updated = ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            rollback(e);
        }
        return updated > 0;
    }

    @Override
    public boolean delete(Long id) {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM Personne WHERE id = ?")) {
            ps.setLong(1,id);
            ps.executeQuery();
            connection.commit();
            return true;
        } catch (SQLException e) {
            rollback(e);
            return false;
        }
    }

    @Override
    public Personne mapResultToPersonne(ResultSet rs) throws SQLException {
        return rs == null
                ? null
                : new Personne(
                rs.getLong("id"),
                rs.getInt("version"),
                rs.getString("nom"),
                rs.getString("prenom"),
                Role.valueOf(rs.getString("role"))
        );
    }

    public static void main(String[] args) {
        try {
            PersonneDao personneDao = new PersonneDao();
            Personne toAdd = new Personne(0L, 0, "ADEKALOM", "Yanis", Role.ADMIN);
            personneDao.create(toAdd);
            System.out.println("After create : " + toAdd);
            Personne toUpdate = new Personne(0L, 0, "ADEKALOM", "Yanis", Role.ADMIN);
            personneDao.create(toUpdate);
            toUpdate.setPrenom("Jérémy");
            toUpdate.setRole(Role.CLIENT);
            personneDao.update(toUpdate);
            Personne toUpdateFindById = personneDao.findById(toUpdate.getId());
            System.out.println("After update & findById : " + toUpdateFindById);
            System.out.println("Before delete : ");
            personneDao.findAll().forEach(System.out::println);
            personneDao.delete(toAdd.getId());
            System.out.println("After delete : ");
            personneDao.findAll().forEach(System.out::println);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
