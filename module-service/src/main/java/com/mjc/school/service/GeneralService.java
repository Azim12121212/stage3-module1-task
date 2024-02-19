package com.mjc.school.service;

import com.mjc.school.service.errorsexceptions.NewsException;

import java.util.List;

public interface GeneralService<T> {
    T create(T t) throws NewsException;
    List<T> readAll();
    T readById(Long id) throws NewsException;
    T update(T t) throws NewsException;
    Boolean delete(Long id) throws NewsException;
}
