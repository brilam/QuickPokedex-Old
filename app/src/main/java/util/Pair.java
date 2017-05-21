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

package util;

/**
 * A class used to represent a Pair of values (these values are related to one
 * another).
 */
public abstract class Pair<K, V> {
  private K left;
  private V right;
  
  /**
   * Initializes a Pair with a value on the left side
   * and one on the right side.
   * @param left the left side value
   * @param right the right side value
   */
  public Pair(K left, V right) {
    this.left = left;
    this.right = right;
  }
  
  /**
   * Returns the value on the left side of the Pair.
   * @return the left side of the Pair.
   */
  public K getLeft() {
    return left;
  }

  /**
   * Sets the value on the left side of the Pair.
   * @param left the left side of the Pair to set
   */
  public void setLeft(K left) {
    this.left = left;
  }

  /**
   * Returns the value on the right side of the Pair.
   * @return the right
   */
  public V getRight() {
    return right;
  }

  /**
   * Sets the value on right side of the Pair.
   * @param right the right side of the Pair to set
   */
  public void setRight(V right) {
    this.right = right;
  }
  
  @Override
  public String toString() {
    return String.format("(%s, %s)", getLeft(), getRight());
  }
}
