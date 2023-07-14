package com.zimttech.diabeticscreener.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CrudContract<T> {
    Page<T> getAll(Pageable pageable);

    Optional<T> getOne(Long id);

    T create(T t);

    T update(Long id, T t);

    void delete(Long id);
}
