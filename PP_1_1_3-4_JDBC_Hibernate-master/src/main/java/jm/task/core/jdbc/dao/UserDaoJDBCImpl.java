package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = null;
    public UserDaoJDBCImpl() {}

    public void createUsersTable() throws SQLException {
        try{
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS users " +
                            "(id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                            "name VARCHAR(20) NOT NULL,\n" +
                            "lastName VARCHAR(20),\n" +
                            "age INT NOT NULL"+ ")"
            );
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
            e.printStackTrace();
            }
        }
    }

    public void dropUsersTable() throws SQLException {
        try{
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(
                    "DROP TABLE IF EXISTS users"
            );
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        try{
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users (name, lastName, age) VALUES (? , ?, ?)"
            );
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) throws SQLException {
        try{
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM users WHERE Id = ?"
            );
            statement.setLong(1, id);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> allUsers = new ArrayList<>();
        try{
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM users"
            );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                Byte age = resultSet.getByte("age");
                allUsers.add(new User( name, lastName, age));
            }
            connection.commit();
            return allUsers;
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            return null;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void cleanUsersTable() throws SQLException {
        try{
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM users"
            );
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
