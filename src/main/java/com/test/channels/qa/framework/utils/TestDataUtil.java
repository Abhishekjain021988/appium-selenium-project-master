package com.test.channels.qa.framework.utils;

import com.test.channels.qa.framework.exception.FrameworkException;
import com.test.channels.qa.framework.pojo.TestData;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

/**
 * A utility class to connect to SQLite database
 * Functions include connecting, reading and writing to and from the database
 * @author AbhishekJain
 *
 */
@Log4j2
@SuppressWarnings("rawtypes")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestDataUtil {

    public static final String SELECTQUERY = "select * from TestData where id = ?";
    public static final String INSERTQUERY = "insert into TestData values(?,?,?)";
    public static final String UPDATEQUERY = "update TestData set date = ?, data = ? where id = ?";

    /**
     * Connect to the database
     */
    private static synchronized Connection connect() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + PropertyUtil.get("sqlite.db.url"));
            log.info("Driver is connecting to the database at "
                + System.getProperty("user.dir") + "/" + PropertyUtil.get("sqlite.db.url"));
            return connection;
        } catch(SQLException e) {
            log.error("No database file found!", e);
            throw new FrameworkException(e);
        }
    }

    /**
     * Read a record from the database
     * @param dataRef - Reference of the data id in the database
     */
    public static Optional<TestData> readData(String dataRef) {
        Connection connection = connect();
        ResultSet rs = null;
        TestData data;
        try(PreparedStatement statement = connection.prepareStatement(SELECTQUERY)) {
            statement.setString(1, dataRef);
            rs = statement.executeQuery();
            if(rs.next()) {
                data = TestData.builder()
                    .id(rs.getString("id"))
                        .date(rs.getDate("date").toLocalDate())
                            .data(rs.getString("data"))
                                .build();
                return Optional.of(data);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            log.error("Unable to fetch data!", e);
            throw new FrameworkException(e);
        } finally {
            closeResultSet(rs);
            closeConnection(connection);
        }
    }

    /**
     * Insert or update a record in the database
     * @param data - Object that contains an id, date and some data
     */
    public static void updateData(TestData data) {
        Connection connection = connect();
        PreparedStatement statement = null;
        data.setDate(LocalDate.now());
        try {
            if(data.getId() == null) {
                statement = connection.prepareStatement(INSERTQUERY);
                statement.setString(1, data.getId());
                statement.setDate(2, Date.valueOf(data.getDate()));
                statement.setString(3, data.getData());
                statement.executeUpdate();
            } else {
                statement = connection.prepareStatement(UPDATEQUERY);
                statement.setDate(1, Date.valueOf(data.getDate()));
                statement.setString(2, data.getData());
                statement.setString(3, data.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            log.error("Unable to update record!", e);
            throw new FrameworkException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    /**
     * Close database result set
     * @param rs - Result set to execute database queries
     */
    public static void closeResultSet(ResultSet rs) {
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                log.error("Unable to close result set!", e);
                throw new FrameworkException(e);
            }
        }
    }

    /**
     * Close database statement
     * @param statement - statement that holds database queries
     */
    public static void closeStatement(PreparedStatement statement) {
        if(statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                log.error("Unable to close statement!", e);
                throw new FrameworkException(e);
            }
        }
    }

    /**
     * Close database connection
     * @param connection - The connection to the SQLite database
     */
    public static void closeConnection(Connection connection) {
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Unable to close connection!", e);
                throw new FrameworkException(e);
            }
        }
    }
}

