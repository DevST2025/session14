package ra.academy.service;

import java.util.List;

public interface IGenericService <T, E> {
    List<T> findAll();
    T findById(E id);
    boolean save(T t);
    void deleteById(E id);
}
