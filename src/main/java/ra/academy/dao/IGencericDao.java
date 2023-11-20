package ra.academy.dao;

import java.util.List;

public interface IGencericDao <T, E>{
    List<T> findAll();
    T findById(E id);
    boolean save(T t);
    void deleteById(E id);
}
