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

import util.Pair;

/**
 * This class represent a PokemonType. The K being ID (integer),
 * and V being type name (String).
 */
public class PokemonTypePair<K, V> extends Pair<Integer,String> {
  /**
   * Creates a PokemonTypePair with id and typeName.
   * @param id the id of the Pokemon
   * @param typeName the type of the Pokemon
   */
  public PokemonTypePair(int id, String typeName) {
    super(id, typeName);
  }
  
  /**
   * Returns the ID of the PokemonTypePair.
   * @return the ID of the PokemonTypePair
   */
  public int getId() {
    return super.getLeft();
  }
  
  /**
   * Returns the type name of the PokemonTypePair.
   * @return the type name of the PokemonTypePair
   */
  public String getName() {
    return super.getRight();
  }
}