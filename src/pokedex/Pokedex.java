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

package pokedex;

import pokeapi.PokeApiFetcher;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represent a Pokedex, an encyclopedia containing information about Pokemon.
 */
public class Pokedex {
  private static List<Pokemon> pokedex;

  /**
   * Creates a Pokedex that allows for the current total number of Pokemon.
   */
  public Pokedex() {
    pokedex = new ArrayList<>(PokeApiFetcher.getNumPokemon());
  }

  /**
   * Adds the given Pokemon to the Pokedex.
   * @param pokemon the Pokemon to be added to the Pokedex
   */
  public void addPokemon(Pokemon pokemon) {
    pokedex.add(pokemon);
  }

  @Override
  public String toString() {
    String pokedexPokemon = "";
    for (Pokemon pokemon : pokedex) {
      pokedexPokemon += pokemon.getName() + "\n";
    }
    return pokedexPokemon.substring(0, pokedexPokemon.length() - 1);
  }
}
