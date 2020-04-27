package com.company.services;

import com.company.model.Credit;
import com.company.repositories.CreditRepository;

import java.sql.SQLException;
import java.util.Optional;

public class CreditService implements CrudService<Credit> {

    private final CreditRepository repository = new CreditRepository();

    @Override
    public Optional<String> create(Credit credit) throws SQLException {
        repository.create(credit);
        return Optional.empty();
    }

    @Override
    public void update(Credit credit) throws SQLException {

    }

    @Override
    public Optional<Credit> findById(Long id) throws SQLException {
        return repository.findById(id);
    }

    @Override
    public Iterable<Credit> findAll() throws SQLException {
        return null;
    }

    @Override
    public void deleteById(Long id) throws SQLException {

    }
}