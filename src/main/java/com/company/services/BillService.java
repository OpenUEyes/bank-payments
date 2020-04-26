package com.company.services;

import com.company.model.Bill;
import com.company.repositories.BillRepository;

import java.sql.SQLException;
import java.util.Optional;

public class BillService implements CrudService<Bill> {

    private final BillRepository repository = new BillRepository();

    @Override
    public Optional<String> create(Bill bill) throws SQLException {
        repository.create(bill);
        return Optional.empty();
    }

    @Override
    public void update(Bill bill) throws SQLException {
        repository.update(bill);
    }

    @Override
    public Optional<Bill> findById(Long id) throws SQLException {
        return repository.findById(id);
    }

    @Override
    public Iterable<Bill> findAll() throws SQLException {
        return repository.findAll();
    }

    public Iterable<Bill> findAllByAccountId(Long id) throws SQLException {
        return repository.findAllByAccountId(id);
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        repository.deleteById(id);
    }

    public Optional<String> transfer(Long senderId, Long recipientId, Double amount) throws SQLException {
        return repository.transfer(senderId, recipientId,  amount);
    }

    public Optional<String> send(Long senderId, Double amount) throws SQLException {
        return repository.send(senderId, amount);
    }
}