package com.mjc.school.controller;

import com.mjc.school.service.errorsexceptions.NewsException;

import java.util.List;

public interface GeneralController<T> {
    T create(T t) throws NewsException;
    List<T> getAll();
    T getById(Long id) throws NewsException;
    T update(T t) throws NewsException;
    boolean delete(Long id) throws NewsException;
}
