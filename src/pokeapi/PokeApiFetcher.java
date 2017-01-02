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
import util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to fetch information from PokeAPI, and this information is to be stored into
 * the Database.
 */
public class PokeApiFetcher {
  public static final String API_URL = "http://pokeapi.co/api/v2/";
  public static final String POKEMON_URL = "pokemon/";
  public static final String TYPES_URL = "type/";
  private static final int FAILED = -1;

  /**
   * Returns the number of Pokemon in the Pokedex by making a GET request to the 
   * Pokemon URL of PokeAPI and parsing the count.
   * @return the number of Pokemon in the Pokedex
   */
  public static int getNumPokemon() {
    int numPokemon = FAILED;
    try {
      // Makes a URL object given the Pokemon url
      URL url = new URL(API_URL + POKEMON_URL);
      // Gets the response of the GET request
      String response = getApiResponse(url);
      numPokemon = PokeApiParser.parseCount(response);
    } catch (IOException e) {
      System.err.println("Uh-oh! Encountered an error: " + e.getMessage());
    }
    return numPokemon;
  }
  
  /**
   * Returns the number of types of Pokemon in the Pokedex by making a GET request
   * to the Types URL of PokeAPI and parsing the count.
   * @return the number of types of Pokemon
   */
  public static int getNumTypes() {
    int numPokemon =  FAILED;
    try {
      // Makes a URL object given the Pokemon url
      URL url = new URL(API_URL + TYPES_URL);
      // Gets the response of the GET request
      String response = getApiResponse(url);
      numPokemon = PokeApiParser.parseCount(response);
    } catch (IOException e) {
      System.err.println("Uh-oh! Encountered an error: " + e.getMessage());
    }
    return numPokemon;
  }
  
  /**
   * Returns a Pair of the Pokemon types (left side is ID, right side is
   * type name).
   * @return a Pair of Pokemon types (left side is ID, right side is
   *         type name).
   */
  public static List<Pair<Integer,String>> getTypes() {
    List<Pair<Integer,String>> types = new ArrayList<>();
    try {
      // Makes a URL object given the Pokemon url
      URL url = new URL(API_URL + TYPES_URL);
      // Gets the response of the GET request
      String response = getApiResponse(url);
      // Gets an ArrayList of types as pairs
      types = PokeApiParser.parseTypes(response);
      
    } catch (IOException e) {
      System.err.println("Uh-oh! Encountered an error: " + e.getMessage());
    }
    return types;
  }
  
  /**
   * Returns a newly created Pokemon object to represent a Pokemon.
   * @param id the id of the Pokemon
   */
  public static Pokemon getPokemon(int id) {
    Pokemon pokemon = new Pokemon();
    try {
      // Makes a URL object given the Pokemon url
      URL url = new URL(API_URL + POKEMON_URL + id);
      // Gets the response of the GET request
      String response = getApiResponse(url);
      pokemon = PokeApiParser.parsePokemon(response, id);
    } catch (IOException e) {
      System.err.println("Uh-oh! Encountered an error: " + e.getMessage());
    }
    return pokemon;
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
  
  public static void main(String[] args) {
    getTypes();
  }
}
