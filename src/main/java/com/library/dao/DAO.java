package com.library.dao;

import java.util.List;

public interface DAO<T> {
    public List<T> getAll();
    public void save(T t);
    void delete (T t);
    T refresh(T t);
    List<T>refresh(List<T> t);
}
