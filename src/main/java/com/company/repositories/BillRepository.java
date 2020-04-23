package com.company.repositories;

import com.company.external.database.ConnectionPull;
import com.company.model.Bill;
import com.company.model.Type;
import lombok.extern.log4j.Log4j;

import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

@Log4j
public class BillRepository extends CrudRepository<Bill> {

    private static final String SQL_CREATE = "INSERT INTO bill(type, balance, validity) VALUES(?, ?, ?)";
    private static final String SQL_UPDATE_FULL = "UPDATE bill SET type = ?, balance = ?, validity = ? WHERE id = ?";
    private static final String SQL_UPDATE_BALANCE = "UPDATE bill SET balance = ? WHERE id = ?";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM bill WHERE (id) = (?)";
    private static final String SQL_FIND_ALL = "SELECT * FROM bill";
    private static final String SQL_DELETE = "DELETE FROM bill WHERE (id) = (?)";

    @Override
    public void create(Bill bill) throws Exception {
        Connection connection = null;
        try {
            connection = ConnectionPull.getConnection();

            try (PreparedStatement ps = connection.prepareStatement(SQL_CREATE)) {
                ps.setObject(1, bill.getType());
                ps.setDouble(2, bill.getBalance());
                ps.setDate(3, Date.valueOf(bill.getValidity()));
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
    public void update(Bill bill) throws Exception {
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

    public void updateBalance(Double balance) throws Exception {
        Connection connection = null;
        try {
            connection = ConnectionPull.getConnection();

            try (PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_BALANCE)) {
                ps.setDouble(1, balance);
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

            try (PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_ID)) {
                ps.setLong(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    resultBill = Optional.of(Bill.builder()
                            .id(rs.getLong(1))
                            .type(Type.valueOf(rs.getString(2)))
                            .balance(rs.getDouble(3))
                            .validity((LocalDate) rs.getObject(4))
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

            try (PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_ID)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    resultSet.add(Bill.builder()
                            .id(rs.getLong(1))
                            .type(Type.valueOf(rs.getString(2)))
                            .balance(rs.getDouble(3))
                            .validity((LocalDate) rs.getObject(4))
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
}