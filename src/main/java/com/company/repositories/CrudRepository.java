package com.company.repositories;

import com.company.external.database.ConnectionPull;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

@Log4j
public abstract class CrudRepository<T> {

    private static final String SQL_DELETE = "DELETE FROM account WHERE (id) = (?)";

    abstract Optional<String> create(T entity) throws SQLException;

    abstract void update(T entity) throws SQLException;

    abstract Optional<T> findById(Long id) throws SQLException;

    abstract Iterable<T> findAll() throws SQLException;

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
}