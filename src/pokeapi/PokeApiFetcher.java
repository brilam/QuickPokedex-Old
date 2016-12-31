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

package pokeapi;

import pokedex.Pokemon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class is used to fetch information from PokeAPI, and this information is to be stored into
 * the Database.
 */
public class PokeApiFetcher {
  public static final String POKEMON_URL = "http://pokeapi.co/api/v2/pokemon/";
  private static final int FAILED = -1;

  /**
   * Returns the number of Pokemon in the Pokedex by making a GET request and parsing the count.
   * 
   * @return the number of Pokemon in the Pokedex
   */
  public static int getNumPokemon() {
    int count = FAILED;
    try {
      // Makes a URL object given the Pokemon url
      URL url = new URL(POKEMON_URL);
      // Gets the response of the GET request
      String response = getApiResponse(url);
      count = PokeApiParser.parseNumPokemon(response);
      return count;
    } catch (IOException e) {
      System.err.println("Uh-oh! Encountered an error: " + e.getMessage());
    }
    return FAILED;
  }
  
  /**
   * Returns a newly created Pokemon object to represent a Pokemon.
   * @param id the id of the Pokemon
   */
  public static void getPokemon(int id) {
    Pokemon pokemon = new Pokemon();
    try {
      // Makes a URL object given the Pokemon url
      URL url = new URL(POKEMON_URL + id);
      // Gets the response of the GET request
      String response = getApiResponse(url);
      System.out.println(response);
    } catch (IOException e) {
      System.err.println("Uh-oh! Encountered an error: " + e.getMessage());
    }
  }

  /**
   * Returns the API response from the GET request given a url.
   * @param url the URL to connect to and make a GET request
   * @return the response that the API provides back
   * @throws IOException any errors that occurs such as a bad HTTP response, parsing issues with
   *         JSON, etc.
   */
  private static String getApiResponse(URL url) throws IOException {
    // Makes a connection to the URL and makes a GET request
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestProperty("User-Agent", "");
    connection.setRequestMethod("GET");
    // Get the response
    InputStream inputStream = connection.getInputStream();
    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

    String currentLine;
    String response = "";
    // Loops through each line and concatenates it to our response
    while ((currentLine = br.readLine()) != null) {
      response += currentLine;
    }

    // Closes the BufferedReader and InputStream
    br.close();
    inputStream.close();
    return response;
  }
}
