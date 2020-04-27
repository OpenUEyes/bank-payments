package com.company.services;

import com.company.model.Deposit;
import com.company.repositories.DepositRepository;

import java.sql.SQLException;
import java.util.Optional;

public class DepositService implements CrudService<Deposit> {

    private final DepositRepository repository = new DepositRepository();

    @Override
    public Optional<String> create(Deposit deposit) throws SQLException {
        return repository.put(deposit);
    }

    @Override
    public void update(Deposit deposit) throws SQLException {

    }

    @Override
    public Optional<Deposit> findById(Long id) throws SQLException {
        return repository.findById(id);
    }

    @Override
    public Iterable<Deposit> findAll() throws SQLException {
        return null;
    }

    @Override
    public void deleteById(Long id) throws SQLException {

    }
}