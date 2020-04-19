package com.company.repositories;

import com.company.model.Credit;

import java.sql.SQLException;
import java.util.Optional;

public class CreditRepository implements CrudRepository<Credit> {

    @Override
    public Optional<String> create(Credit entity) throws Exception {
        return Optional.empty();
    }

    @Override
    public void update(Credit entity) throws Exception {// TODO change to message
    }

    @Override
    public Optional<Credit> findById(Long id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public Iterable<Credit> findAll() throws SQLException {
        return null;
    }

    @Override
    public void deleteById(Long id) throws SQLException {

    }
}