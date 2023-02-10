package jdbc.dao;

import jdbc.models.BaseEntity;
import jdbc.models.Personne;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IDao<ID,T extends BaseEntity> {
    boolean create(T object);
    T findById(ID id);
    List<T> findAll();
    boolean update(T object);
    boolean delete(ID id);

    T mapResultToObject(ResultSet rs) throws SQLException, IllegalAccessException, ClassNotFoundException;
}
