package com.company.repositories;

import com.company.model.Deposit;

import java.sql.SQLException;
import java.util.Optional;

public class DepositRepository implements CrudRepository<Deposit> {

    @Override
    public Optional<String> save(Deposit entity) throws Exception {
        return Optional.empty();
    }

    @Override
    public Optional<Deposit> findById(Long id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public Iterable<Deposit> findAll() throws SQLException {
        return null;
    }

    @Override
    public void deleteById(Long id) throws SQLException {

    }
}