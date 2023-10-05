package org.example;

import java.io.Serializable;
import java.util.List;

public interface Repository<E extends Serializable, ID> {
    boolean add(E e);
    E get(ID id);
    List<E> getAll();

//    create to method remove with E, and ID
    boolean remove(E e);
    boolean  remove(ID id);
    boolean update(E e);
}
