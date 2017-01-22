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

package unittests;

import org.junit.Assert;
import org.junit.Test;

import pokeapi.PokeApiParser;
import pokedex.Pokemon;
import pokedex.Pokemon.PokemonBuilder;
import pokedex.PokemonTypePair;
import util.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PokeApiParserTest {
  private static final int ACTUAL_COUNT = 811;
  private static final String COUNT_PATH = "src/unittests/resources/count.json";
  private static final String POKEMON_PATH = "src/unittests/resources/pokemon.json";
  /* Bulbasaur information (same Pokemon as in pokemon.json). 
    Correct as of Generation VI (Jan 22 2017) */
  private static final int ID = 1;
  private static final String NAME = "bulbasaur";
  private static final double WEIGHT = 6.9;
  private static final double HEIGHT = 0.7;
  private static final int BASE_EXPERIENCE = 64;
  private static final int SPEED = 45;
  private static final int SPECIAL_DEFENSE = 65;
  private static final int SPECIAL_ATTACK = 65;
  private static final int DEFENSE = 49;
  private static final int ATTACK = 49;
  private static final int HP = 45;
  private static final int TYPE1 = 4;
  private static final int TYPE2 = 12;

  
  /**
   * Tests for parsing the count using count.json and checks if the count
   * is correct.
   */
  @Test
  public void testParseCount() {
    try {
      // Reads the count.json file from resources
      BufferedReader br = new BufferedReader(new FileReader(COUNT_PATH));
      // Gets the JSON file as a String
      String response = jsonToString(br);
      // Gets the expected count and checks if it matches
      int expectedCount = PokeApiParser.parseCount(response);
      Assert.assertEquals(expectedCount, ACTUAL_COUNT);
      System.out.println("Testing for parsing count - PASSED!");
    } catch (IOException e) {
      System.err.println("Uh-oh! Cannot find count.json");
    } catch (AssertionError ae) {
      System.err.println("Testing for parsing count - FAILED!");
    }
  }
  
  /**
   * Tests the parsing of the first Pokemon in the Pokedex (known as Bulbasaur)
   * and checks if all the information is correct. This also tests parsePokemonTypes,
   * since parsePokemon also uses parsePokemonTypes.
   */
  @Test
  public void testParsePokemon() {
    try {
      // Reads the pokemon.json file from resources
      BufferedReader br = new BufferedReader(new FileReader(POKEMON_PATH));
      // Gets the JSON file as a String
      String response = jsonToString(br);
      // Gets the expected Pokemon and checks if it matches
      Pokemon expectedPokemon = PokeApiParser.parsePokemon(response, ID).build();
      // Creates the actual Pokemon and gets it
      Pokemon actualPokemon = createPokemon();  
      Assert.assertEquals(expectedPokemon, actualPokemon);
      System.out.println("Testing for parsing Pokemon - PASSED!");
    } catch (IOException e) {
      System.err.println("Uh-oh! Cannot find pokemon.json");
    } catch (AssertionError ae) {
      System.err.println("Testing for parsing Pokemon - FAILED!");
    }
  }
  
  @Test
  public void testParseTypes() {
    try {
      // Reads the types.json file from resources
      BufferedReader br = new BufferedReader(new FileReader(POKEMON_PATH));
      // Gets the JSON file as a String
      String response = jsonToString(br);
      // Gets the expected types and checks if it matches
      List<Pair<Integer, String>> expectedTypes = PokeApiParser.parseTypes(response);
      // Creates the actual types and gets it
    }
  }
  
  private String jsonToString(BufferedReader br) throws IOException {
    String currentLine;
    String response = "";
    // Loops through each line and concatenates it to our response
    while ((currentLine = br.readLine()) != null) {
      response += currentLine;
    }
    return response;
  }
  
  private Pokemon createPokemon() {
    PokemonBuilder pokemonInfo = new Pokemon.PokemonBuilder(ID);
    pokemonInfo.setName(NAME);
    pokemonInfo.setWeight(WEIGHT);
    pokemonInfo.setHeight(HEIGHT);
    pokemonInfo.setBaseExperience(BASE_EXPERIENCE);
    pokemonInfo.setSpeed(SPEED);
    pokemonInfo.setSpecialDefense(SPECIAL_DEFENSE);
    pokemonInfo.setSpecialAttack(SPECIAL_ATTACK);
    pokemonInfo.setDefense(DEFENSE);
    pokemonInfo.setAttack(ATTACK);
    pokemonInfo.setHp(HP);
    List<Integer> types = new ArrayList<>();
    types.add(TYPE1);
    types.add(TYPE2);
    pokemonInfo.setTypes(types);
    Pokemon pokemon = pokemonInfo.build();
    return pokemon;
  }
}
