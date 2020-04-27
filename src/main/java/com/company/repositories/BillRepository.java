package com.company.repositories;

import com.company.external.database.ConnectionPull;
import com.company.model.Bill;
import com.company.model.Type;
import lombok.extern.log4j.Log4j;

import java.sql.*;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

@Log4j
public class BillRepository extends CrudRepository<Bill> {

    private static final String SQL_CREATE = "INSERT INTO bill(type, balance, validity, account_id) VALUES(?, ?, ?, ?)";
    private static final String SQL_UPDATE_FULL = "UPDATE bill SET type = ?, balance = ?, validity = ? WHERE id = ?";
    private static final String SQL_UPDATE_BALANCE = "UPDATE bill SET balance = ? WHERE id = ?";
    private static final String SQL_FIND_ALL = "SELECT * FROM bill";
    private static final String SQL_FIND_BY_BILL_ID = "SELECT * FROM bill WHERE (id) = (?)";
    private static final String SQL_FIND_BY_ACCOUNT_ID = "SELECT * FROM bill WHERE (account_id) = (?)";
    private static final String SQL_DELETE = "DELETE FROM bill WHERE (id) = (?)";

    @Override
    public void create(Bill bill) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPull.getConnection();

            try (PreparedStatement ps = connection.prepareStatement(SQL_CREATE)) {
                ps.setString(1, Type.UNSIGNED.toString().toUpperCase());
                ps.setDouble(2, bill.getBalance());
                ps.setDate(3, Date.valueOf(bill.getValidity()));
                ps.setLong(4, bill.getAccountId());
                ps.executeUpdate();
            }

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            log.warn(exception.getMessage());
            throw exception;
        } finally {
            ConnectionPull.closeConnection(connection);
        }
    }

    @Override
    public void update(Bill bill) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPull.getConnection();

            try (PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_FULL)) {
                ps.setObject(1, bill.getType());
                ps.setDouble(2, bill.getBalance());
                ps.setDate(3, Date.valueOf(bill.getValidity()));
                ps.setLong(4, bill.getId());
                ps.executeUpdate();
            }

        } catch (SQLException exception) {
            log.warn(exception.getMessage());
            throw exception;
        } finally {
            ConnectionPull.closeConnection(connection);
        }
    }

    public void updateBalance(Double balance, Long billId) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPull.getConnection();

            try (PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_BALANCE)) {
                ps.setDouble(1, balance);
                ps.setLong(2, billId);
                ps.executeUpdate();
            }

        } catch (SQLException exception) {
            log.warn(exception.getMessage());
            throw exception;
        } finally {
            ConnectionPull.closeConnection(connection);
        }
    }

    @Override
    public Optional<Bill> findById(Long id) throws SQLException {
        Connection connection = null;
        Optional<Bill> resultBill = Optional.empty();
        try {
            connection = ConnectionPull.getConnection();

            try (PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_BILL_ID)) {
                ps.setLong(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    resultBill = Optional.of(Bill.builder()
                            .id(rs.getLong(1))
                            .number(String.format("%016d", rs.getLong(1)))
                            .type(Type.valueOf(rs.getString(2)))
                            .balance(rs.getDouble(3))
                            .validity(((Date) rs.getObject(4)).toLocalDate())
                            .build());
                }
            }

        } catch (SQLException exception) {
            log.warn(exception.getMessage());
            throw exception;
        } finally {
            ConnectionPull.closeConnection(connection);
        }

        return resultBill;
    }

    @Override
    public Iterable<Bill> findAll() throws SQLException {
        Connection connection = null;
        Set<Bill> resultSet = new TreeSet<>();
        try {
            connection = ConnectionPull.getConnection();

            try (PreparedStatement ps = connection.prepareStatement(SQL_FIND_ALL)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    resultSet.add(Bill.builder()
                            .id(rs.getLong(1))
                            .number(String.format("%016d", rs.getLong(1)))
                            .type(Type.valueOf(rs.getString(2)))
                            .balance(rs.getDouble(3))
                            .validity(((Date) rs.getObject(4)).toLocalDate())
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

    public Iterable<Bill> findAllByAccountId(Long id) throws SQLException {
        Connection connection = null;
        Set<Bill> resultSet = new TreeSet<>();
        try {
            connection = ConnectionPull.getConnection();

            try (PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_ACCOUNT_ID)) {
                ps.setLong(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    resultSet.add(Bill.builder()
                            .id(rs.getLong(1))
                            .number(String.format("%016d", rs.getLong(1)))
                            .type(Type.valueOf(rs.getString(2)))
                            .balance(rs.getDouble(3))
                            .validity(((Date) rs.getObject(4)).toLocalDate())
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

    public Optional<String> transfer(Long senderId, Long recipientId, Double amount) throws SQLException {
        Connection connection = null;
        Optional<String> result = Optional.empty();
        try {
            connection = ConnectionPull.getConnection();

            connection.setAutoCommit(false);

            Double senderBalance = 0.0;
            try (PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_BILL_ID)) {
                ps.setLong(1, senderId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    senderBalance = rs.getDouble(3);
                }
            }
            if (senderBalance < amount) {
                result = Optional.of("Not enough money! You have " + senderBalance + " USD but need " + amount + " USD!!");
                return result;
            }
            try (PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_BALANCE)) {
                ps.setDouble(1, senderBalance - amount);
                ps.setLong(2, senderId);
                ps.executeUpdate();
            }

            Double recipientBalance = 0.0;
            try (PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_BILL_ID)) {
                ps.setLong(1, recipientId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    recipientBalance = rs.getDouble(3);
                }
            }
            try (PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_BALANCE)) {
                ps.setDouble(1, recipientBalance + amount);
                ps.setLong(2, recipientId);
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

    public Optional<String> send(Long senderId, Double amount) throws SQLException {
        Connection connection = null;
        Optional<String> result = Optional.empty();
        try {
            connection = ConnectionPull.getConnection();

            connection.setAutoCommit(false);

            Double senderBalance = 0.0;
            try (PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_BILL_ID)) {
                ps.setLong(1, senderId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    senderBalance = rs.getDouble(3);
                }
            }
            if (senderBalance < amount) {
                result = Optional.of("Not enough money! You have " + senderBalance + " USD but need " + amount + " USD!!");
                return result;
            }
            try (PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_BALANCE)) {
                ps.setDouble(1, senderBalance - amount);
                ps.setLong(2, senderId);
                ps.executeUpdate();
            }

            // External payment service. Send money to card of another bank

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
}