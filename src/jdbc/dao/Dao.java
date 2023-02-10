package jdbc.dao;

import jdbc.connector.DbConnector;
import jdbc.models.BaseEntity;
import jdbc.models.Personne;
import jdbc.models.Role;
import jdbc.utils.TableManager;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dao<T extends BaseEntity> implements IDao<Long, T> {
    private final Connection connection;
    private final Class<T> clazz;

    public Dao(Class<T> clazz) throws SQLException, ClassNotFoundException {
        connection = DbConnector.getInstance();
        this.clazz = clazz;
    }

    private void rollback(Exception e) {
        try {
            e.printStackTrace(System.out);
            connection.rollback();
        } catch (SQLException ex) {
            e.printStackTrace();
        }
    }

    private void setValues(PreparedStatement ps, T object, boolean setId) throws IllegalAccessException, SQLException {
        List<Field> fields = TableManager.getAllFields(clazz);
        //if(!setId) fields = fields.subList(1,fields.size());
        if(!setId)fields.remove(0);
        for (Field field : fields) {
            boolean accessible = field.canAccess(object);
            if(!accessible) field.setAccessible(true);
            switch (field.getType().getSimpleName()) {
                case "int" -> ps.setInt(fields.indexOf(field) + 1, (int)field.get(object));
                case "double" -> ps.setDouble(fields.indexOf(field) + 1, (double)field.get(object));
                case "String", "Role" -> ps.setString(fields.indexOf(field) + 1, field.get(object).toString());
                case "long" -> ps.setLong(fields.indexOf(field) + 1, object.getId());
                default -> ps.setLong(fields.indexOf(field) + 1, ((BaseEntity)field.get(object)).getId());
            }
            field.setAccessible(accessible);
        }
    }

    @Override
    public boolean create(T object) {
        try (PreparedStatement ps = connection.prepareStatement(
                TableManager.getCreateReq(clazz),
                Statement.RETURN_GENERATED_KEYS)) {
            setValues(ps, object, true);
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) {
                object.setId(rs.getLong(1));
            }
            connection.commit();
            return true;
        } catch (Exception e) {
            rollback(e);
            return false;
        }
    }

    @Override
    public T findById(Long id) {
        T object = null;
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * from "+ clazz.getSimpleName() +" WHERE id = ?")) {
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            object = mapResultToObject(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public List<T> findAll() {
        List<T> all = new ArrayList<>();
        try (Statement s = connection.createStatement()) {
            ResultSet rs = s.executeQuery("SELECT * FROM " + clazz.getSimpleName());
            while (rs.next()) {
                all.add(mapResultToObject(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return all;
    }

    @Override
    public boolean update(T object) {
        int updated = 0;
        try (PreparedStatement ps = connection.prepareStatement(
                TableManager.getUpdateReq(clazz,object))) {
            setValues(ps, object, false);
            updated = ps.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            rollback(e);
        }
        return updated > 0;
    }

    @Override
    public boolean delete(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(
                "DELETE FROM " + clazz.getSimpleName() + " WHERE id = ?")) {
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
    public T mapResultToObject(ResultSet rs) throws SQLException, IllegalAccessException, ClassNotFoundException {
        if(rs == null)return null;
        T object;
        try {
            object = clazz.getConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }
        List<Field> fields = TableManager.getAllFields(clazz);
        for (Field field : fields) {
            boolean accessible = field.canAccess(object);
            if(!accessible) field.setAccessible(true);
            switch (field.getType().getSimpleName()) {
                case "int" -> field.set(object, rs.getInt(field.getName()));
                case "double" -> field.set(object, rs.getDouble(field.getName()));
                case "String" -> field.set(object, rs.getString(field.getName()));
                case "Role" -> field.set(object, Role.valueOf(rs.getString(field.getName())));
                case "long" -> field.set(object, rs.getLong(field.getName()));
                default -> field.set(object,
                        new Dao<>((Class<? extends BaseEntity>) field.getType()).findById(rs.getLong(field.getName())));
            }
            field.setAccessible(accessible);
        }
        return object;
    }
}
