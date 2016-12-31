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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pokedex.Pokedex;
import pokedex.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class PokedexTester {
  private static final int TEST_POKEDEX_SIZE = 2;
  private Pokedex pokedex;
  private Pokemon pokemon1;
  private Pokemon pokemon2;

  @Before
  public void setUp() {
    pokedex = new Pokedex(TEST_POKEDEX_SIZE);
    pokemon1 = new Pokemon();
    pokemon2 = new Pokemon();
    pokemon1.setName("Bulbasaur");
    pokemon2.setName("Ivysaur");
  }

  @After
  public void tearDown() {
    pokedex.clear();
  }

  @Test
  public void testAddOnePokemon() {
    pokedex.addPokemon(pokemon1);
    List<Pokemon> actualPokedex = new ArrayList<>();
    actualPokedex.add(pokemon1);
    Assert.assertEquals(pokedex.getPokedex(), actualPokedex);
  }

  @Test
  public void testAddMultiplePokemon() {
    pokedex.addPokemon(pokemon1);
    pokedex.addPokemon(pokemon2);
    List<Pokemon> actualPokedex = new ArrayList<>();
    actualPokedex.add(pokemon1);
    actualPokedex.add(pokemon2);
    Assert.assertEquals(pokedex.getPokedex(), actualPokedex);
  }

  @Test
  public void testAddToFullPokedex() {
    Pokemon pokemon3 = new Pokemon();
    pokedex.addPokemon(pokemon1);
    pokedex.addPokemon(pokemon2);
    pokedex.addPokemon(pokemon3);
    pokemon3.setName("Venasaur");
    List<Pokemon> actualPokedex = new ArrayList<>(2);
    actualPokedex.add(pokemon1);
    actualPokedex.add(pokemon2);
    actualPokedex.add(pokemon3);
    Assert.assertEquals(pokedex.getPokedex(), actualPokedex);
  }


}
