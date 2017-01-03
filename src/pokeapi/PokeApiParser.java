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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import pokedex.Pokemon;
import util.Pair;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * A class used to parse the JSON from the response after making a PokeAPI GET request.
 */
public class PokeApiParser {
  /**
   * Given an API response made to the Pokemon or Types URL, parse the number of Pokemon in the
   * Pokedex or the number of types of Pokemon.
   * 
   * @param response the response from the API made to http://pokeapi.co/api/v2/pokemon/
   * @return the count (number of Pokemon in the Pokedex or number of types)
   * @throws IOException errors with parsing
   */
  public static int parseCount(String response) throws IOException {
    // Creates a StringReader to read the API response string
    StringReader strReader = new StringReader(response);
    // Creates a JSON reader to read the JSON from the API response string
    JsonReader jsonReader = new JsonReader(strReader);
    // Creates a JsonParser object to parse JSON
    JsonParser jsonParser = new JsonParser();
    // Gets the count
    int count = jsonParser.parse(jsonReader).getAsJsonObject().get("count").getAsInt();
    // Closes the readers
    jsonReader.close();
    strReader.close();
    return count;
  }

  /**
   * Given an API response made to the Pokemon URL with Pokemon ID, parses the Pokemon and returns a
   * Pokemon.
   * 
   * @param response the response from the API made to http://pokeapi.co/api/v2/pokemon/id
   * @return the newly created pokemon
   * @throws IOException errors when parsing
   */
  public static Pokemon parsePokemon(String response, int id) throws IOException {
    // Creates a StringReader to read the API response string
    StringReader strReader = new StringReader(response);
    // Creates a JSON reader to read the JSON from the API response string
    JsonReader jsonReader = new JsonReader(strReader);
    // Creates a JsonParser object to parse JSON
    JsonParser jsonParser = new JsonParser();
    // Gets the results array
    JsonObject jsonObj =
        jsonParser.parse(jsonReader).getAsJsonObject();
    JsonObject formsObj = jsonObj.get("forms").getAsJsonArray().get(0).getAsJsonObject();
    String name = formsObj.get("name").getAsString();
    Double weight = jsonObj.get("weight").getAsDouble() / 10;
    Double height = jsonObj.get("height").getAsDouble() / 10;
    int baseExperience = jsonObj.get("base_experience").getAsInt();
    
    // Creates a new Pokemon with the information collected from the API
    Pokemon pokemon = new Pokemon();
    pokemon.setId(id);
    pokemon.setName(name);
    pokemon.setWeight(weight);
    pokemon.setHeight(height);
    pokemon.setBaseExperience(baseExperience);
    
    // Close the readers since we are done reading
    jsonReader.close();
    strReader.close();
    return pokemon;
  }

  /**
   * Given an API response made to the Types URL, parses the types, and returns a List of Pairs
   * (left side is the ID of the type, and right side is the type name).
   * @param response the response from the API made to http://pokeapi.co/api/v2/types
   * @return a List of Pairs (left side is the ID of the type, and right side is the type name).
   * @throws IOException errors when parsing
   */
  public static List<Pair<Integer, String>> parseTypes(String response) throws IOException {
    // Creates a StringReader to read the API response string
    StringReader strReader = new StringReader(response);
    // Creates a JSON reader to read the JSON from the API response string
    JsonReader jsonReader = new JsonReader(strReader);

    // Creates a JsonParser object to parse JSON
    JsonParser jsonParser = new JsonParser();
    // Gets the results array
    JsonArray jsonArray =
        jsonParser.parse(jsonReader).getAsJsonObject().get("results").getAsJsonArray();

    // Creates a List of type pairs
    List<Pair<Integer, String>> types = new ArrayList<>();

    for (int index = 0; index < jsonArray.size(); index++) {
      strReader = new StringReader(jsonArray.get(index).toString());
      jsonReader = new JsonReader(strReader);
      // Skips through the information we don't need
      jsonReader.beginObject();
      jsonReader.nextName();
      // Gets the URL which contains the ID number
      String url = jsonReader.nextString();
      // Parsing the substringed URL to get the ID
      int id = Integer
          .parseInt(url.substring(url.indexOf(PokeApiFetcher.TYPES_URL) + 5, url.length() - 1));
      // Skips through the name
      jsonReader.nextName();
      // Gets the name
      String typeName = jsonReader.nextString();
      Pair<Integer, String> type = new Pair<>(id, typeName);
      types.add(type);
    }
    // Close the readers since we are done reading
    jsonReader.close();
    strReader.close();
    return types;
  }
}
