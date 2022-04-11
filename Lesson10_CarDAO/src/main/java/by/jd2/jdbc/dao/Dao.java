package by.jd2.jdbc.dao;

public interface Dao<T> {

    void save(T t);

    T selectById(int id, T t);

    void update(T t);

    void delete(T t);

}
