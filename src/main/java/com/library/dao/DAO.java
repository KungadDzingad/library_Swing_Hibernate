package com.library.dao;

import java.util.List;

public interface DAO<T> {
    public List<T> getAll();
    public void save(T t);
    void delete (T t);
}
