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

import java.io.IOException;
import java.io.StringReader;

/**
 * A class used to parse the JSON from the response after making
 * a PokeAPI GET request.
 */
public class PokeApiParser {
  /**
   * Given an API response made from http://pokeapi.co/api/v2/pokemon/,
   * parse the number of Pokemon in the Pokedex.
   * @param response the response from the API made from http://pokeapi.co/api/v2/pokemon/
   * @return the number of Pokemon in the Pokedex
   * @throws IOException errors with parsing
   */
  public static int parseNumPokemon(String response) throws IOException {
    int count;
    // Reading
    StringReader strReader = new StringReader(response);
    JsonReader jsonReader = new JsonReader(strReader);
    
    // Begins parse the object
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
}
