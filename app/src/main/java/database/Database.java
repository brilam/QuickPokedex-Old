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
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import pokeapi.PokeApiFetcher;
import pokedex.Pokemon;
import util.Pair;

/**
 * A class used to represent the database containing all of our information needed for our Pokedex.
 */
public class Database {
  public static final String DATABASE_FILE = "pokedex.db";

  /**
   * Creates a SQLite database file with the name specified in DATABASE_FILE. This SQLite database
   * will have three tables defined: pokemon, types, and pokemon_types.
   */
  public static Connection createDatabase() {
    Connection connection = null;
    try {
      // Create a database Connection
      connection = getConnection(connection);
      // Defines the schema
      defineSchema(connection);
    } catch (SQLException exception) {
      System.err.println("Error: " + exception.getMessage());
    }
    return connection;
  }
  
  /**
   * Returns the SQLite connection to the database.
   * @param connection an uninitialized Connection object
   * @return the SQLite connection to the database
   * @throws SQLException a SQL exception if there is any issue with getting the connection
   */
  public static Connection getConnection(Connection connection) throws SQLException {
    connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_FILE);
    return connection;
  }

  /**
   * Defines the schema with three tables: pokemon, types, and pokemon_types.
   * @param connection the connection to the database
   * @throws SQLException a SQL exception if there is an issue with executing a query
   */
  public static void defineSchema(Connection connection) throws SQLException {
    // Statement used for executing queries that define our database
    Statement statement = connection.createStatement();
    // Creates a pokemon table
    statement.executeUpdate("CREATE TABLE pokemon(id INTEGER PRIMARY KEY, name STRING, "
        + "base_experience INTEGER, height FLOAT, weight FLOAT, hp INTEGER, attack INTEGER, "
        + "defense INTEGER, special_attack INTEGER, special_defense INTEGER, speed INTEGER, "
        + "modified DATE)");
    // Creates a types table
    statement.executeUpdate("CREATE TABLE types(type_id INTEGER PRIMARY KEY, type STRING)");
    // Creates a Pokemon types table
    statement.executeUpdate("CREATE TABLE pokemon_types(pokemon_id INTEGER, "
        + "type_id INTEGER, FOREIGN KEY(pokemon_id) REFERENCES pokemon(id), "
        + "FOREIGN KEY(type_id) REFERENCES types(type_id))");
    // Closes statement after done defining schema
    statement.close();
  }

  /**
   * Inserts the types of Pokemon into the types table.
   * 
   * @param connection the connection to the database
   * @param types a List of all the types of Pokemon (type id, type name)
   * @throws SQLException a SQL exception if there is an issue with executing the query
   */
  public static void populateTypesTable(Connection connection, List<Pair<Integer, String>> types)
      throws SQLException {
    PreparedStatement ps = null;
    for (Pair<Integer, String> type : types) {
      // PreparedStatement used for inserting values into types tbale
      ps = connection.prepareStatement("INSERT INTO types(type_id, type) VALUES (?, ?)");
      // Sets the values to be inserted into the table
      ps.setInt(1, type.getLeft());
      ps.setString(2, type.getRight());
      ps.executeUpdate();
      // Closes each PreparedStatement after done executing
      ps.close();
    }
  }

  /**
   * Inserts the Pokemon into the pokemon table, and insert its types into pokemon_types 
   * table.
   * @param connection the connection to the database
   * @throws SQLException a SQL exception if there is an issue with executing the query
   */
  public static void populatePokemonTable(Connection connection) throws SQLException {
    PreparedStatement ps = null;
    int count = PokeApiFetcher.getNumPokemon();
    for (int index = 0; index < count; index++) {
      Pokemon pokemon = PokeApiFetcher.getPokemon(index + 1);
      // PreparedStatement used for inserting values into types table
      ps = connection.prepareStatement(
          "INSERT INTO pokemon(id, name, base_experience, height, weight, hp, "
          + "attack, defense, special_attack, special_defense, speed, modified) "
          + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
      // Sets the values to be inserted into the table
      ps.setInt(1, pokemon.getId());
      ps.setString(2, pokemon.getName());
      ps.setInt(3, pokemon.getBaseExperience());
      ps.setDouble(4, pokemon.getHeight());
      ps.setDouble(5, pokemon.getWeight());
      ps.setInt(6, pokemon.getHp());
      ps.setInt(7, pokemon.getAttack());
      ps.setInt(8, pokemon.getDefense());
      ps.setInt(9, pokemon.getSpecialAttack());
      ps.setInt(10, pokemon.getSpecialDefense());
      ps.setInt(11, pokemon.getSpeed());
      Date currentDate = new Date(new java.util.Date().getTime());
      ps.setDate(12, currentDate);
      ps.executeUpdate();
      
      // If the Pokemon has types, then populate the pokemon_types table
      if (pokemon.getTypes() != null) {
        populatePokemonTypeTable(connection, ps, pokemon);
      }
      
      // Closes each PreparedStatement after done executing
      ps.close();
    }
  }
  
  /**
   * Inserts the Pokemons' type(s) into the pokemon_types table.
   * @param connection the connection to the database
   * @param ps a PreparedStatement
   * @param pokemon the Pokemon with information to be inserted
   * @throws SQLException  a SQL exception if there is an issue with executing the query
   */
  private static void populatePokemonTypeTable(Connection connection, PreparedStatement ps, 
      Pokemon pokemon) throws SQLException {
    // Loops through all the types for the Pokemon
    for (int index = 0; index < pokemon.getTypes().size(); index++) {
      // Inserts the information into the pokemon_type table
      ps = connection.prepareStatement("INSERT INTO pokemon_types(pokemon_id, type_id) "
          + "VALUES (?, ?)");
      ps.setInt(1, pokemon.getId());
      ps.setInt(2, pokemon.getTypes().get(index));
      ps.executeUpdate();
      // Closes PreparedStatement after each insert
      ps.close();
    }
  }

  /**
   * Returns the number of rows (number of types) in the database.
   * @param connection the connection to the database
   * @return the number of rows (number of types) in the database
   * @throws SQLException a SQL exception if there is an issue with executing the query
   */
  public static int getNumTypes(Connection connection) throws SQLException {
    PreparedStatement ps = connection.prepareStatement("SELECT * FROM types");
    ResultSet results = ps.executeQuery();
    int numTypes = 0;
    // Loops through the number of rows in the ResultSet to find the number of types
    while (results.next()) {
      numTypes++;
    }
    // Closes up the ResultSet and PreparedStatement after everything is done
    results.close();
    ps.close();
    return numTypes;
  }
  
  /**
   * Returns the last completed Pokemon entry (when the name isn't MISSINGNO).
   * @param connection the connection to the database
   * @return the id of the last completed Pokemon entry
   * @throws SQLException a SQL exception if there is an issue with executing the query
   */
  public static int getLastCompletePokemon(Connection connection) throws SQLException {
    PreparedStatement ps = connection.prepareStatement("SELECT * FROM pokemon WHERE "
        + "name != \"MISSINGNO\" ORDER BY id DESC");
    ResultSet results = ps.executeQuery();
    int id = results.getInt("id");
    return id;
  }
  
  /**
   * Returns the number of rows (number of Pokemon) in the database.
   * @param connection the connection to the database
   * @return the number of rows (number of Pokemon) in the database
   * @throws SQLException a SQL exception if there is an issue with executing the query
   */
  public static int getNumPokemon(Connection connection) throws SQLException {
    PreparedStatement ps = connection.prepareStatement("SELECT * FROM pokemon");
    ResultSet results = ps.executeQuery();
    int numPokemon = 0;
    // Loops through the number of rows in the ResultSet to find the number of Pokemon
    while (results.next()) {
      numPokemon++;
    }
    // Closes up the ResultSet and PreparedStatement after everything is done
    results.close();
    ps.close();
    return numPokemon;
  }
}
