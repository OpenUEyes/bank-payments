package com.company.services;

import com.company.model.Credit;
import com.company.repositories.CreditRepository;

import java.sql.SQLException;
import java.util.Optional;

public class CreditService implements CrudService<Credit> {

    private final CreditRepository repository = new CreditRepository();

    @Override
    public Optional<String> create(Credit credit) throws SQLException {
        return repository.create(credit);
    }

    @Override
    public void update(Credit credit) throws SQLException {

    }

    @Override
    public Optional<Credit> findById(Long id) throws SQLException {
        return repository.findById(id);
    }

    public Optional<Credit> findById(Long billId, Long accountId) throws SQLException {
        return repository.findById(billId, accountId);
    }

    @Override
    public Iterable<Credit> findAll() throws SQLException {
        return null;
    }

    @Override
    public void deleteById(Long id) throws SQLException {

    }
}