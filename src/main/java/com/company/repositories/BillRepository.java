package com.company.repositories;

import com.company.model.Bill;

import java.sql.SQLException;
import java.util.Optional;

public class BillRepository implements CrudRepository<Bill> {

    @Override
    public Optional<String> save(Bill entity) throws Exception {
        return Optional.empty();
    }

    @Override
    public Optional<Bill> findById(Long id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public Iterable<Bill> findAll() throws SQLException {
        return null;
    }

    @Override
    public void deleteById(Long id) throws SQLException {

    }
}