package com.company.repositories;

import com.company.external.database.ConnectionPull;
import com.company.model.Credit;
import com.company.model.Type;
import lombok.extern.log4j.Log4j;

import java.sql.*;
import java.util.Optional;

@Log4j
public class CreditRepository extends CrudRepository<Credit> {

    private static final String SQL_CREATE = "INSERT INTO credit(id, debt, `limit`, percentage, start, deadline) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SQL_FIND_BY_CREDIT_ID = "SELECT * FROM credit WHERE (id) = (?)";
    private static final String SQL_FIND_BY_CREDIT_ID_ACCOUNT_ID = "SELECT * FROM credit c JOIN bill b ON c.id = b.id WHERE (c.id, b.account_id) = (?, ?)";
    private static final String SQL_FIND_BY_BILL_ID = "SELECT * FROM bill WHERE (id) = (?)";
    private static final String SQL_UPDATE_BILL = "UPDATE bill SET type = ?, balance = ?  WHERE id = ?";

    @Override
    public Optional<String> create(Credit credit) throws SQLException {
        Optional<String> result = Optional.empty();

        Connection connection = null;
        try {
            connection = ConnectionPull.getConnection();

            connection.setAutoCommit(false);

            final long billId = credit.getId();

            try (PreparedStatement ps = connection.prepareStatement(SQL_CREATE)) {
                ps.setLong(1, billId);
                ps.setDouble(2, credit.getDebt());
                ps.setDouble(3, credit.getLimit());
                ps.setDouble(4, credit.getPercentage());
                ps.setDate(5, Date.valueOf(credit.getStart()));
                ps.setDate(6, Date.valueOf(credit.getDeadline()));
                ps.executeUpdate();
            }

            Double recipientBalance = 0.0;
            try (PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_BILL_ID)) {
                ps.setLong(1, billId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    recipientBalance = rs.getDouble(3);
                }
            }
            try (PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_BILL)) {
                ps.setString(1, Type.CREDIT.toString().toUpperCase());
                ps.setDouble(2, recipientBalance + credit.getDebt());
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
    public void update(Credit credit) throws SQLException {
    }

    @Override
    public Optional<Credit> findById(Long id) throws SQLException {
        Connection connection = null;
        Optional<Credit> resultCredit = Optional.empty();
        try {
            connection = ConnectionPull.getConnection();

            try (PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_CREDIT_ID)) {
                ps.setLong(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    resultCredit = Optional.of(Credit.builder()
                            .id(rs.getLong(1))
                            .debt(rs.getDouble(2))
                            .limit(rs.getDouble(3))
                            .percentage(rs.getDouble(4))
                            .start(((Date) rs.getObject(5)).toLocalDate())
                            .deadline(((Date) rs.getObject(6)).toLocalDate())
                            .build());
                }
            }

        } catch (SQLException exception) {
            log.warn(exception.getMessage());
            throw exception;
        } finally {
            ConnectionPull.closeConnection(connection);
        }

        return resultCredit;
    }

    public Optional<Credit> findById(Long creditId, Long accountId) throws SQLException {
        Connection connection = null;
        Optional<Credit> resultCredit = Optional.empty();
        try {
            connection = ConnectionPull.getConnection();

            try (PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_CREDIT_ID_ACCOUNT_ID)) {
                ps.setLong(1, creditId);
                ps.setLong(2, accountId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    resultCredit = Optional.of(Credit.builder()
                            .id(rs.getLong(1))
                            .debt(rs.getDouble(2))
                            .limit(rs.getDouble(3))
                            .percentage(rs.getDouble(4))
                            .start(((Date) rs.getObject(5)).toLocalDate())
                            .deadline(((Date) rs.getObject(6)).toLocalDate())
                            .build());
                }
            }

        } catch (SQLException exception) {
            log.warn(exception.getMessage());
            throw exception;
        } finally {
            ConnectionPull.closeConnection(connection);
        }

        return resultCredit;
    }

    @Override
    public Iterable<Credit> findAll() throws SQLException {
        return null;
    }
}