package com.company.services;

import com.company.model.Deposit;
import com.company.repositories.DepositRepository;

import java.sql.SQLException;
import java.util.Optional;

public class DepositService implements CrudService<Deposit> {

    private final DepositRepository repository = new DepositRepository();

    @Override
    public Optional<String> create(Deposit deposit) throws SQLException {
        return repository.create(deposit);
    }

    @Override
    public void update(Deposit deposit) throws SQLException {
        repository.update(deposit);
    }

    @Override
    public Optional<Deposit> findById(Long id) throws SQLException {
        return repository.findById(id);
    }

    public Optional<Deposit> findById(Long billId, Long accountId) throws SQLException {
        return repository.findById(billId, accountId);
    }

    @Override
    public Iterable<Deposit> findAll() throws SQLException {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        repository.deleteById(id);
    }
}