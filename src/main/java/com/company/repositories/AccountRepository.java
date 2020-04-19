package com.company.repositories;

import com.company.external.database.ConnectionPull;
import com.company.model.Account;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

@Log4j
public class AccountRepository implements CrudRepository<Account> {

    private static final String SQL_SAVE_CHECK_LOGIN = "SELECT id FROM account WHERE (login) = (?)";
    private static final String SQL_SAVE_CHECK_EMAIL = "SELECT id FROM account WHERE (email) = (?)";
    private static final String SQL_SAVE_CHECK_PHONE_NUMBER = "SELECT id FROM account WHERE (phone_number) = (?)";
    private static final String SQL_SAVE = "INSERT INTO account(login, password, email, name, surname, phone_number)" +
            " VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE account SET login = ?, password = ?, email = ?, name = ?," +
            " surname = ?, phone_number = ? WHERE id = ?";

    private static final String SQL_FIND_BY_ID = "SELECT * FROM account WHERE (id) = (?)";
    private static final String SQL_FIND_ALL = "SELECT * FROM account";
    private static final String SQL_DELETE = "DELETE FROM account WHERE (id) = (?)";
    private static final String SQL_GET_ID = "SELECT id FROM account WHERE (login, password) = (?, ?)";

    @Override
    public Optional<String> create(Account account) throws Exception {
        Connection connection = null;
        Optional<String> resultMessage = Optional.empty();
        try {
            connection = ConnectionPull.getConnection();

//            connection.setAutoCommit(false);

            try (PreparedStatement ps = connection.prepareStatement(SQL_SAVE_CHECK_LOGIN)) {
                ps.setString(1, account.getLogin());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    resultMessage = Optional.of("Login already exists, please try another.");
                    log.warn("START");
                    return resultMessage;
                }
            }
            try (PreparedStatement ps = connection.prepareStatement(SQL_SAVE_CHECK_EMAIL)) {
                ps.setString(1, account.getEmail());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    resultMessage = Optional.of("Email already exists, please try another.");
                    return resultMessage;
                }
            }
            try (PreparedStatement ps = connection.prepareStatement(SQL_SAVE_CHECK_PHONE_NUMBER)) {
                ps.setString(1, account.getPhoneNumber());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    resultMessage = Optional.of("Phone number already exists, please try another.");
                    return resultMessage;
                }
            }

            try (PreparedStatement ps = connection.prepareStatement(SQL_SAVE)) {
                accountFill(account, ps);
                ps.executeUpdate();
            }
//            connection.commit();

        } catch (SQLException exception) {
            if (connection != null) {
                connection.rollback();
            }
            log.warn(exception.getMessage());
            throw exception;
        } finally {
            log.warn("END");
//            if (connection != null) {
//                connection.setAutoCommit(true);
//            }
            ConnectionPull.closeConnection(connection);
        }
        return resultMessage;
    }

    //TODO change name
    private void accountFill(Account account, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, account.getLogin());
        preparedStatement.setString(2, account.getPassword());
        preparedStatement.setString(3, account.getEmail());
        preparedStatement.setString(4, account.getName());
        preparedStatement.setString(5, account.getSurname());
        preparedStatement.setString(6, account.getPhoneNumber());
    }

    @Override
    public void update(Account account) throws Exception {
        Connection connection = null;
        try {
            connection = ConnectionPull.getConnection();

            try (PreparedStatement ps = connection.prepareStatement(SQL_UPDATE)) {
                accountFill(account, ps);
                ps.setLong(7, account.getId());
                ps.executeUpdate();
            }

        } catch (SQLException exception) {
            if (connection != null) {
                connection.rollback();
            }
            log.warn(exception.getMessage());
            throw exception;
        } finally {
            ConnectionPull.closeConnection(connection);
        }
    }

    @Override
    public Optional<Account> findById(Long id) throws SQLException {
        Connection connection = null;
        Optional<Account> resultAccount = Optional.empty();
        try {
            connection = ConnectionPull.getConnection();

            try (PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_ID)) {
                ps.setLong(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    resultAccount = Optional.of(Account.builder()
                            .id(rs.getLong(1))
                            .login(rs.getString(2))
                            .password(rs.getString(3))
                            .email(rs.getString(4))
                            .name(rs.getString(5))
                            .surname(rs.getString(6))
                            .phoneNumber(rs.getString(7))
                            .build());
                }
            }

        } catch (SQLException exception) {
            log.warn(exception.getMessage());
            throw exception;
        } finally {
            ConnectionPull.closeConnection(connection);
        }

        return resultAccount;
    }

    @Override
    public Iterable<Account> findAll() throws SQLException {
        Connection connection = null;
        Set<Account> resultSet = new TreeSet<>();
        try {
            connection = ConnectionPull.getConnection();

            try (PreparedStatement ps = connection.prepareStatement(SQL_FIND_ALL)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    resultSet.add(Account.builder()
                            .id(rs.getLong(1))
                            .login(rs.getString(2))
                            .password(rs.getString(3))
                            .email(rs.getString(4))
                            .name(rs.getString(5))
                            .surname(rs.getString(6))
                            .phoneNumber(rs.getString(7))
                            .build());
                }
            }

        } catch (SQLException exception) {
            log.warn(exception.getMessage());
            throw exception;
        } finally {
            ConnectionPull.closeConnection(connection);
        }

        return resultSet;
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPull.getConnection();

            try (PreparedStatement ps = connection.prepareStatement(SQL_DELETE)) {
                ps.setLong(1, id);
                ps.executeUpdate();
            }

        } catch (SQLException exception) {
            log.warn(exception.getMessage());
            throw exception;
        } finally {
            ConnectionPull.closeConnection(connection);
        }
    }

    public Optional<Long> getId(String login, String password) throws SQLException {
        Connection connection = null;
        Optional<Long> resultId = Optional.empty();
        try {            System.out.println("Before pull");

            connection = ConnectionPull.getConnection();

            try (PreparedStatement ps = connection.prepareStatement(SQL_GET_ID)) {
                ps.setString(1, login);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    long id = rs.getLong(1);
                    if (id > 0) {
                        resultId = Optional.of(id);
                    }
                }
            }

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            log.warn(exception.getMessage());
            throw exception;
        } finally {
            ConnectionPull.closeConnection(connection);
        }
        return resultId;
    }
}