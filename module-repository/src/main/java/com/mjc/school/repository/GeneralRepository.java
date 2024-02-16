package com.mjc.school.repository;

import java.util.List;

public interface GeneralRepository<T> {
    T create(T t);
    List<T> getAll();
    T getById(Long id);
    T update(T t);
    boolean delete(Long id);
}
