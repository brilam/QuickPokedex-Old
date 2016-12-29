/**
 *      This file is part of QuickPokedex.
 * 
 *      @author Brian Lam
 *         
 *      This program is free software: you can redistribute it and/or modify 
 *      it under the terms of the GNU Affero General Public License as
 *      published by the Free Software Foundation version 3 as published by
 *      the Free Software Foundation. You may not use, modify or distribute 
 *      this program under any other version of the GNU Affero General Public
 *      License.
 *      
 *      This program is distributed in the hope that it will be useful, but
 *      WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *      Affero General Public License for more details. You should have
 *      received a copy of the GNU Affero General Public License along with
 *      this program. If not, see http://www.gnu.org/licenses.
 */

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A class used to represent the database containing all of our information
 * needed for our Pokedex.
 */
public class Database {
  public static final String DATABASE_FILE = "pokedex.db";

  /**
   * Creates a SQLite database file with the name specified in DATABASE_FILE.
   * This SQLite database will have three tables defined: pokemon, types, and
   * pokemon_types.
   */
  public static void createDatabase() {
    try {
      // Create a database Connection
      Connection connection =
          DriverManager.getConnection("jdbc:sqlite:" + DATABASE_FILE);
      defineSchema(connection);
    } catch (SQLException exception) {
      System.err.println("Error: " + exception.getMessage());
    }
  }

  /**
   * Defines the schema with three tables: pokemon, types, and pokemon_types.
   * 
   * @param connection the connection to the database
   * @throws SQLException a SQL exception if there is an issue with executing a
   *         query
   */
  public static void defineSchema(Connection connection) throws SQLException {
    // Statement used for executing queries that define our database
    Statement statement = connection.createStatement();
    // Creates a pokemon table
    statement.executeUpdate(
        "CREATE TABLE pokemon(id INTEGER PRIMARY KEY, name STRING, "
            + "base_experience INTEGER, height INTEGER, weight INTEGER)");
    // Creates a types table
    statement.executeUpdate(
        "CREATE TABLE types(type_id INTEGER PRIMARY KEY, type STRING)");
    // Creates a Pokemon types table
    statement.executeUpdate(
        "CREATE TABLE pokemon_types(pokemon_id INTEGER PRIMARY KEY, "
            + "type_id INTEGER UNIQUE, FOREIGN KEY(pokemon_id) REFERENCES pokemon(id), "
            + "FOREIGN KEY(type_id) REFERENCES types(type_id))");
  }

  public static void main(String[] args) {
    createDatabase();
  }

}
