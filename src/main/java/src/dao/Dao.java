package src.dao;

import java.util.List;

public interface Dao<T> {

    T getById(int id);

    List<T> getAll();

    void save(T t);

    T update(T existing, T updated);

}