package com.example.jdbc;

import java.sql.SQLException;
import java.text.MessageFormat;

public class Main {
  public static void main(String[] args) {
    UserDAO userDAO = new UserDAO();
    try {
      /**
       * Insert a new user.
       */
      User user = new User(0, "Cesar Fonseca", "slogive@gmail.com");
      int newUserId = userDAO.insertUser(user);
      user.setId(newUserId);
      // System.out.println("New user id: " + newUserId + "\n");
      System.out.println(MessageFormat.format("New user id: {0}\n", newUserId));

      /**
       * Retrieve the user with id.
       */
      User userRetrieve = userDAO.getUser(newUserId);
      if (userRetrieve != null) {
        user.setEmail(userRetrieve.getEmail());
        user.setName(userRetrieve.getName());
        System.out.println(
            "Id: " + userRetrieve.getId() + ", " + "Name: " + userRetrieve.getName() + ", " + userRetrieve.getEmail()
                + "\n");
      } else {
        // System.out.println("User not found.\n");
        System.out.println(MessageFormat.format("User \"${0}\"", newUserId) + " not found.\n");
        System.exit(1);
      }

      /**
       * Update the user.
       */
      user.setName("Cesar Augusto Fonseca Rodriguez");
      userDAO.updateUser(user);
      // System.out.println("User " + user.getName() + " updated.\n");
      System.out.println(MessageFormat.format("User \"{0}\" updated.\n", user.getName()));

      /**
       * Delete the user.
       */
      userDAO.deleteUser(newUserId);
      // System.out.println("User " + user.getName() + " deleted.");
      System.out.println(MessageFormat.format("User \"{0}\" deleted.", user.getName()));

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
