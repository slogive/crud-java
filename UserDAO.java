package com.example.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
  public int insertUser(User user) throws SQLException {
    String query = "INSERT INTO users (name, email) VALUES (?, ?)";
    int generatedId = -1;

    try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
      statement.setString(1, user.getName());
      statement.setString(2, user.getEmail());
      int affectedRows = statement.executeUpdate();

      if (affectedRows > 0) {
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
          if (generatedKeys.next()) {
            generatedId = generatedKeys.getInt(1);
          }
        }
      }
    }
    return generatedId;
  }

  public User getUser(int id) throws SQLException {
    String query = "SELECT * FROM users WHERE id = ? AND deleted = false";

    try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setInt(1, id);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        return new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("email"));
      }
    }

    return null;
  }

  public List<User> getAllUsers() throws SQLException {
    List<User> users = new ArrayList<>();
    String query = "SELECT * FROM users";

    try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery()) {
      while (resultSet.next()) {
        users.add(new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("email")));
      }
    }

    return users;
  }

  public void updateUser(User user) throws SQLException {
    String query = "UPDATE users SET name = ?, email = ? WHERE id = ?";

    try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setString(1, user.getName());
      statement.setString(2, user.getEmail());
      statement.setInt(3, user.getId());
      statement.executeUpdate();
    }
  }

  public void deleteUser(int id) throws SQLException {
    /**
     * Hard delete.
     */
    // String query = "DELETE FROM users WHERE id = ?";
    /**
     * Soft delete.
     */
    String query = "UPDATE users SET deleted = true WHERE id = ?";

    try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setInt(1, id);
      statement.executeUpdate();
    }
  }
}
