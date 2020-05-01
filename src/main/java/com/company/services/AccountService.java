package com.company.services;

import com.company.model.Account;
import com.company.repositories.AccountRepository;

import java.sql.SQLException;
import java.util.Optional;

public class AccountService implements CrudService<Account> {

    private final AccountRepository repository = new AccountRepository();

    @Override
    public Optional<String> create(Account account) throws SQLException {
        Optional<String> result;
        result = repository.checkLogin(account.getLogin());
        if (result.isPresent()) {
            return result;
        }
        result = repository.checkEmail(account.getEmail());
        if (result.isPresent()) {
            return result;
        }
        result = repository.checkPhoneNumber(account.getPhoneNumber());
        if (result.isPresent()) {
            return result;
        }
        result = repository.create(account);

        return result;
    }

    @Override
    public void update(Account account) throws SQLException {
        repository.update(account);
    }

    @Override
    public Optional<Account> findById(Long id) throws SQLException {
        return repository.findById(id);
    }

    @Override
    public Iterable<Account> findAll() throws SQLException {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        repository.deleteById(id);
    }

    public Optional<Long> getId(String login, String password) throws SQLException {
        return repository.getId(login, password);
    }
}