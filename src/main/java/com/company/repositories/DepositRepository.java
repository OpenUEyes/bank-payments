package com.company.repositories;

import com.company.external.database.ConnectionPull;
import com.company.model.Deposit;
import com.company.model.Type;
import lombok.extern.log4j.Log4j;

import java.sql.*;
import java.util.Optional;

@Log4j
public class DepositRepository extends CrudRepository<Deposit> {

    private static final String SQL_CREATE = "INSERT INTO deposit(id, amount, rate, start, finish) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_FIND_BY_BILL_ID = "SELECT * FROM bill WHERE (id) = (?)";
    private static final String SQL_FIND_BY_DEPOSIT_ID = "SELECT * FROM deposit WHERE (id) = (?)";
    private static final String SQL_FIND_BY_DEPOSIT_ID_ACCOUNT_ID = "SELECT * FROM deposit d JOIN bill b ON d.id = b.id WHERE (d.id, b.account_id) = (?, ?)";
    private static final String SQL_UPDATE_BILL = "UPDATE bill SET type = ?, balance = ?  WHERE id = ?";

    @Override
    public Optional<String> create(Deposit deposit) throws SQLException {
        Connection connection = null;

        Optional<String> result = Optional.empty();
        try {
            connection = ConnectionPull.getConnection();

            connection.setAutoCommit(false);

            final long billId = deposit.getId();
            final double amount = deposit.getAmount();
            double recipientBalance = 0.0;
            try (PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_BILL_ID)) {
                ps.setLong(1, billId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    recipientBalance = rs.getDouble(3);
                }
            }

            if (recipientBalance < amount) {
                return Optional.of("Your balance is: " + recipientBalance + ", you can't put to deposit more than your balance");
            }

            try (PreparedStatement ps = connection.prepareStatement(SQL_CREATE)) {
                ps.setLong(1, billId);
                ps.setDouble(2, amount);
                ps.setDouble(3, deposit.getRate());
                ps.setDate(4, Date.valueOf(deposit.getStart()));
                ps.setDate(5, Date.valueOf(deposit.getFinish()));
                ps.executeUpdate();
            }

            try (PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_BILL)) {
                ps.setString(1, Type.DEPOSIT.toString().toUpperCase());
                ps.setDouble(2, recipientBalance - amount);
                ps.setLong(3, billId);
                ps.executeUpdate();
            }

            connection.commit();

        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            log.warn(e.getMessage());
            throw new SQLException();
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
            }
            ConnectionPull.closeConnection(connection);
        }
        return result;
    }

    @Override
    public void update(Deposit entity) throws SQLException {
    }

    @Override
    public Optional<Deposit> findById(Long id) throws SQLException {

        Connection connection = null;
        Optional<Deposit> resultDeposit = Optional.empty();
        try {
            connection = ConnectionPull.getConnection();

            try (PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_DEPOSIT_ID)) {
                ps.setLong(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    resultDeposit = Optional.of(Deposit.builder()
                            .id(rs.getLong(1))
                            .amount(rs.getDouble(2))
                            .rate(rs.getDouble(3))
                            .start(((Date) rs.getObject(4)).toLocalDate())
                            .finish(((Date) rs.getObject(5)).toLocalDate())
                            .build());
                }
            }

        } catch (SQLException exception) {
            log.warn(exception.getMessage());
            throw exception;
        } finally {
            ConnectionPull.closeConnection(connection);
        }

        return resultDeposit;
    }

    public Optional<Deposit> findById(Long depositId, Long accountId) throws SQLException {

        Connection connection = null;
        Optional<Deposit> resultDeposit = Optional.empty();
        try {
            connection = ConnectionPull.getConnection();

            try (PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_DEPOSIT_ID_ACCOUNT_ID)) {
                ps.setLong(1, depositId);
                ps.setLong(2, accountId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    resultDeposit = Optional.of(Deposit.builder()
                            .id(rs.getLong(1))
                            .amount(rs.getDouble(2))
                            .rate(rs.getDouble(3))
                            .start(((Date) rs.getObject(4)).toLocalDate())
                            .finish(((Date) rs.getObject(5)).toLocalDate())
                            .build());
                }
            }

        } catch (SQLException exception) {
            log.warn(exception.getMessage());
            throw exception;
        } finally {
            ConnectionPull.closeConnection(connection);
        }

        return resultDeposit;
    }

    @Override
    public Iterable<Deposit> findAll() throws SQLException {
        return null;
    }
}