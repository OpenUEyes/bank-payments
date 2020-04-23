package com.company.repositories;

import com.company.model.Deposit;

import java.sql.SQLException;
import java.util.Optional;

public class DepositRepository extends CrudRepository<Deposit> {

    @Override
    public void create(Deposit entity) throws Exception {
    }

    @Override
    public void update(Deposit entity) throws Exception {// TODO change to message
    }

    @Override
    public Optional<Deposit> findById(Long id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public Iterable<Deposit> findAll() throws SQLException {
        return null;
    }
}