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

package application;

import database.Database;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import pokeapi.PokeApiFetcher;
import util.Pair;

/**
 * This class is intended to simulate the Android application on
 * an initial run. It will create the database, and populate the
 * database.
 */
public class Application {
  /**
   * Runs the program (meant to simulate the Android application).
   * @param args no arguments needed
   */
  public static void main(String[] args) {
    Connection connection = null;
    try {
      if (hasPokedex()) {
        connection = Database.getConnection(connection);
        System.out.println("Pokedex is ready.");
      } else {
        connection = Database.createDatabase();
        List<Pair<Integer, String>> types = PokeApiFetcher.getTypes();
        Database.populateTypesTable(connection, types);
        Database.populatePokemonTable(connection);
      }
    } catch (SQLException e) {
      System.err.println("Uh-oh! Encountered an error: " + e.getMessage());
    }
  }
 
  /**
   * Returns whether or not pokedex.db exists.
   * @return whether or not pokedex.db exists
   */
  public static boolean hasPokedex() {
    File file = new File(Database.DATABASE_FILE);
    return file.exists();
  }
}
