package com.company.repositories;

import com.company.external.database.ConnectionPull;
import com.company.model.Account;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Log4j
public class AccountRepository implements CrudRepository<Account> {

    private static final String SQL_SAVE = "INSERT INTO account(login, password, email, name, surname, phone_number)" +
            " VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SQL_SAVE_CHECK_LOGIN = "SELECT id FROM account WHERE (login) = (?)";
    private static final String SQL_SAVE_CHECK_EMAIL = "SELECT id FROM account WHERE (email) = (?)";
    private static final String SQL_SAVE_CHECK_PHONE_NUMBER = "SELECT id FROM account WHERE (phone_number) = (?)";

    @Override
    public Optional<String> save(Account account) throws Exception {
        Connection connection = null;
        Optional<String> message = Optional.empty();
        try {
            connection = ConnectionPull.getConnection();

            connection.setAutoCommit(false);

            try (PreparedStatement ps = connection.prepareStatement(SQL_SAVE_CHECK_LOGIN)) {
                ps.setString(1, account.getLogin());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    message = Optional.of("Login already exists, please try another.");
                    log.warn("START");
                    return message;
                }
            }
            try (PreparedStatement ps = connection.prepareStatement(SQL_SAVE_CHECK_EMAIL)) {
                ps.setString(1, account.getEmail());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    message = Optional.of("Email already exists, please try another.");
                    return message;
                }
            }
            try (PreparedStatement ps = connection.prepareStatement(SQL_SAVE_CHECK_PHONE_NUMBER)) {
                ps.setString(1, account.getPhoneNumber());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    message = Optional.of("Phone number already exists, please try another.");
                    return message;
                }
            }

            try (PreparedStatement ps = connection.prepareStatement(SQL_SAVE)) {
                ps.setString(1, account.getLogin());
                ps.setString(2, account.getPassword());
                ps.setString(3, account.getEmail());
                ps.setString(4, account.getName());
                ps.setString(5, account.getSurname());
                ps.setString(6, account.getPhoneNumber());
                ps.executeUpdate();
            }
            connection.commit();

        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            log.warn(e.getMessage());
            throw e;
        } finally {
            log.warn("END");
            if (connection != null) {
                connection.setAutoCommit(true);
            }
            ConnectionPull.closeConnection(connection);
        }
        return message;
    }

    @Override
    public Optional<Account> findById(Long id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public Iterable<Account> findAll() throws SQLException {
        return null;
    }

    @Override
    public void deleteById(Long id) throws SQLException {

    }
}