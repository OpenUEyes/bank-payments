package com.company.repositories;
import java.sql.SQLException;
import java.util.Optional;

public interface CrudRepository<T> {

    Optional<String> create(T entity) throws SQLException;

    void update(T entity) throws SQLException;

    Optional<T> findById(Long id) throws SQLException;

    Iterable<T> findAll() throws SQLException;

    void deleteById(Long id) throws SQLException;
}