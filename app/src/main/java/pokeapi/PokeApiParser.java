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

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import pokedex.Pokemon;
import pokedex.Pokemon.PokemonBuilder;
import pokedex.PokemonTypePair;
import util.Pair;

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
  public static PokemonBuilder parsePokemon(String response, int id) throws IOException {
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

    // Creates a new Pokemon with ID, name, weight, height, and base experience
    PokemonBuilder pokemonInfo = new Pokemon.PokemonBuilder(id);
    pokemonInfo = pokemonInfo.setName(name).setWeight(weight)
            .setHeight(height).setBaseExperience(baseExperience);

    // Gets a JsonArray of the stats and parses and it returns back as a PokemonBuilder object
    JsonArray statsArray = jsonObj.get("stats").getAsJsonArray();
    pokemonInfo = parsePokemonStats(jsonObj, statsArray, pokemonInfo);

    /* Gets a JsonArray of the types and parses the Pokemon's types and returns it back as a
     * PokemonBuilder object */
    JsonArray typesArray = jsonObj.get("types").getAsJsonArray();
    pokemonInfo = parsePokemonTypes(typesArray, pokemonInfo);

    // Close the readers since we are done reading
    jsonReader.close();
    strReader.close();
    return pokemonInfo;
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
              .parseInt(url.substring(url.indexOf(PokeApiFetcher.TYPES_URL)
                      + PokeApiFetcher.TYPES_URL.length(), url.length() - 1));
      // Skips through the name
      jsonReader.nextName();
      // Gets the name
      String typeName = jsonReader.nextString();
      PokemonTypePair<Integer, String> type = new PokemonTypePair<>(id, typeName);
      types.add(type);
    }
    // Close the readers since we are done reading
    jsonReader.close();
    strReader.close();
    return types;
  }

  /**
   * Given the results array as a JSON object, a JSON array of the stats, and the PokemonBuilder
   * object which will be the Pokemon to be built, parses the stats from the API response, and
   * returns the updated PokemonBuilder object for which a Pokemon is to be built.
   * @param jsonObj the result array as a JSON object
   * @param statsArray a JSON array of the stats (speed, special defense, special attack, defense,
   *                   attack, hp)
   * @param pokemonInfo the PokemonBuilder object which is to be used to build a Pokemon
   * @return the updated PokemonBuilder object with stats to be used to build a Pokemon
   */
  private static PokemonBuilder parsePokemonStats(JsonObject jsonObj, JsonArray statsArray,
                                                  PokemonBuilder pokemonInfo) {
    // Loops through the number of stats
    for (int index = 0; index < statsArray.size(); index++) {
      JsonObject statElement = jsonObj.get("stats").getAsJsonArray().get(index).getAsJsonObject();
      String statName = statElement.get("stat").getAsJsonObject().get("name").getAsString();
      int baseStat = statElement.get("base_stat").getAsInt();
      // Sets the appropriate stat with base stat as the number of points
      switch (statName) {
        case "speed":
          pokemonInfo = pokemonInfo.setSpeed(baseStat);
          break;
        case "special-defense":
          pokemonInfo = pokemonInfo.setSpecialDefense(baseStat);
          break;
        case "special-attack":
          pokemonInfo = pokemonInfo.setSpecialAttack(baseStat);
          break;
        case "defense":
          pokemonInfo = pokemonInfo.setDefense(baseStat);
          break;
        case "attack":
          pokemonInfo = pokemonInfo.setAttack(baseStat);
          break;
        case "hp":
          pokemonInfo = pokemonInfo.setHp(baseStat);
          break;
        default: // Never reached as it will always be one of the cases above
          break;
      }
    }
    return pokemonInfo;
  }

  /**
   * Given the types array and the PokemonBuilder object which will be the Pokemon to be built,
   * parses the types of the Pokemon and returns the the updated PokemonBuilder object for which
   * a Pokemon is to be built.
   * @param typesArray a JSON array of the types
   * @param pokemonInfo the PokemonBuilder object which is to be used to build a Pokemon
   * @return the updated PokemonBuilder object with types to be used to build a Pokemon
   */
  private static PokemonBuilder parsePokemonTypes(JsonArray typesArray,
                                                  PokemonBuilder pokemonInfo) {
    List<Integer> types = new ArrayList<>();
    // Loops through all the types in the types array
    for (int index = 0; index < typesArray.size(); index++) {
      // Gets the type object
      JsonObject typeObj  = typesArray.get(index).getAsJsonObject().get("type").getAsJsonObject();
      // Gets the type url as a String (it contains the ID at the end of the URL)
      String typeUrl = typeObj.get("url").getAsString();
      // Substrings only the ID
      String type = typeUrl.substring(typeUrl.indexOf(PokeApiFetcher.TYPES_URL)
              + PokeApiFetcher.TYPES_URL.length(), typeUrl.length() - 1);
      // Adds the type to the list of types
      types.add(Integer.parseInt(type));
    }
    pokemonInfo.setTypes(types);
    return pokemonInfo;
  }
}
