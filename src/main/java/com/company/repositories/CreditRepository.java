package com.company.repositories;

import com.company.model.Credit;

import java.sql.SQLException;
import java.util.Optional;

public class CreditRepository extends CrudRepository<Credit> {

    @Override
    public void create(Credit entity) throws Exception {
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
}