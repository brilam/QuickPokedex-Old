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

import com.google.gson.stream.JsonReader;

import pokedex.Pokemon;

import java.io.IOException;
import java.io.StringReader;

/**
 * A class used to parse the JSON from the response after making a PokeAPI GET request.
 */
public class PokeApiParser {
  private static final int NUM_OBJECTS = 17;

  /**
   * Given an API response made from the Pokemon or Types URL, parse the number of Pokemon in the
   * Pokedex or the number of types of Pokemon.
   * 
   * @param response the response from the API made from http://pokeapi.co/api/v2/pokemon/
   * @return the count (number of Pokemon in the Pokedex or number of types)
   * @throws IOException errors with parsing
   */
  public static int parseCount(String response) throws IOException {
    int count;
    // Creates a StringReader to read the API response string
    StringReader strReader = new StringReader(response);
    // Creates a JSON reader to read the JSON from the API response string
    JsonReader jsonReader = new JsonReader(strReader);

    // Begins to parse the object
    jsonReader.beginObject();
    // Parses the first name (which is count)
    jsonReader.nextName();
    // Gets the value of count
    count = Integer.parseInt(jsonReader.nextString());
    // Close the readers since we are done reading
    jsonReader.close();
    strReader.close();
    return count;
  }

  /**
   * Given an API response made from the Pokemon URL with Pokemon ID, parses the Pokemon and returns
   * a Pokemon.
   * 
   * @param response the response from the API made from http://pokeapi.co/api/v2/pokemon/id
   * @return the newly created pokemon
   * @throws IOException errors when parsing
   */
  public static Pokemon parsePokemon(String response) throws IOException {
    // Creates a StringReader to read the API response string
    StringReader strReader = new StringReader(response);
    // Creates a JSON reader to read the JSON from the API response string
    JsonReader jsonReader = new JsonReader(strReader);

    // Creates a new Pokemon
    Pokemon pokemon = new Pokemon();

    // Begins the object
    jsonReader.beginObject();

    // for (int index = 0; index < NUM_OBJECTS; index++) {
    String name = jsonReader.nextName();
    switch (name) {
      case "forms":
        jsonReader.beginArray();
        jsonReader.beginObject();
        jsonReader.nextName();
        jsonReader.nextString();
        if (jsonReader.nextName().equals("name")) {
          String pokemonName = jsonReader.nextString();
          System.out.println(pokemonName);
          pokemon.setName(pokemonName);
        }
        break;
      case "abilities":
        jsonReader.beginArray();
      default:
        // Never reached
    }
    // }
    // Close the readers since we are done reading
    jsonReader.close();
    strReader.close();
    return pokemon;
  }
}
