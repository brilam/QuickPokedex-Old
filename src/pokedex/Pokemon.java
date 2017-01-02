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

import java.util.ArrayList;
import java.util.List;

/**
 * A class used to represent a Pokemon in the Pokedex.
 */
public class Pokemon {
  private int id;
  private String name;
  private int baseExperience;
  private double height;
  private double weight;
  private List<String> abilities;
  private List<String> moves;
  private int hp;
  private int attack;
  private int defense;
  private int specialAttack;
  private int specialDefense;
  private int speed;
  private List<String> types;
  
  /**
   * Creates a Pokemon with an empty list of abilities, moves
   * and types.
   */
  public Pokemon() {
    abilities = new ArrayList<>();
    moves = new ArrayList<>();
    types = new ArrayList<>();
  }

  /**
   * Returns the Pokemon ID number in the Pokedex.
   * @return the id of the Pokemon
   */
  public int getId() {
    return id;
  }

  /**
   * Sets the Pokemon ID from the Pokedex.
   * @param id of the Pokemon to be set
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Returns the Pokemon's name.
   * @return the name of the Pokemon
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the Pokemon's name.
   * @param the name of the Pokemon to be set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Returns the base experience required to level up.
   * @return the baseExperience required to level up
   */
  public int getBaseExperience() {
    return baseExperience;
  }

  /**
   * Sets the base experience required to level up.
   * @param baseExperience required to level up to be set
   */
  public void setBaseExperience(int baseExperience) {
    this.baseExperience = baseExperience;
  }

  /**
   * Returns the weight of the Pokemon.
   * @return the weight of the Pokemon
   */
  public double getWeight() {
    return weight;
  }

  /**
   * Sets the weight of the Pokemon in kilograms.
   * @param weight the weight of the Pokemon in kilograms to be set
   */
  public void setWeight(double weight) {
    this.weight = weight;
  }

  /**
   * Returns the abilities of the Pokemon.
   * @return the abilities of the Pokemon
   */
  public List<String> getAbilities() {
    return abilities;
  }

  /**
   * Set the abilities of the Pokemon.
   * @param abilities the abilities of the Pokemon to be set
   */
  public void setAbilities(List<String> abilities) {
    this.abilities = abilities;
  }

  /**
   * Returns a list of all possible moves that the Pokemon can learn.
   * @return the list of moves that the Pokemon can learn
   */
  public List<String> getMoves() {
    return moves;
  }

  /**
   * Sets the list of moves that the Pokemon can learn.
   * @param moves the moves that the Pokemon can learn to be set
   */
  public void setMoves(List<String> moves) {
    this.moves = moves;
  }

  /**
   * Return the base health points of the Pokemon.
   * @return the base health points of the Pokemon
   */
  public int getHp() {
    return hp;
  }

  /**
   * Sets the base health points of the Pokemon.
   * @param hp the health points of the Pokemon to be set
   */
  public void setHp(int hp) {
    this.hp = hp;
  }

  /**
   * Returns the base attack points of the Pokemon.
   * @return the base attack points of the Pokemon.
   */
  public int getAttack() {
    return attack;
  }

  /**
   * Sets the base attack points of the Pokemon.
   * @param attack the base attack points of the Pokemon to be set
   */
  public void setAttack(int attack) {
    this.attack = attack;
  }

  /**
   * Returns the base defense points of the Pokemon.
   * @return the base defense points of the Pokemon
   */
  public int getDefense() {
    return defense;
  }

  /**
   * Sets the base defense points of the Pokemon.
   * @param defense the base defense points of the Pokemon to be set
   */
  public void setDefense(int defense) {
    this.defense = defense;
  }

  /**
   * Returns the base special attack of the Pokemon.
   * @return the base specialAttack of the Pokemon
   */
  public int getSpecialAttack() {
    return specialAttack;
  }

  /**
   * Sets the base special attack of the Pokemon.
   * @param specialAttack the base special attack points of the Pokemon to be set
   */
  public void setSpecialAttack(int specialAttack) {
    this.specialAttack = specialAttack;
  }

  /**
   * Returns the base special defense points of the Pokemon.
   * @return the base special defense points of the Pokemon
   */
  public int getSpecialDefense() {
    return specialDefense;
  }

  /**
   * Sets the base special defense points of the Pokemon.
   * @param specialDefense the base special defense points of the Pokemon to be set
   */
  public void setSpecialDefense(int specialDefense) {
    this.specialDefense = specialDefense;
  }

  /**
   * Returns the base speed points of the Pokemon.
   * @return the base speed points of the Pokemon
   */
  public int getSpeed() {
    return speed;
  }

  /**
   * Sets the base speed points of the Pokemon.
   * @param speed the base speed points of the Pokemon to be set
   */
  public void setSpeed(int speed) {
    this.speed = speed;
  }

  /**
   * Returns the types of the Pokemon.
   * @return the types of the Pokemon
   */
  public List<String> getTypes() {
    return types;
  }

  /**
   * Sets the types of the Pokemon.
   * @param types the types of the Pokemon to be set
   */
  public void setTypes(List<String> types) {
    this.types = types;
  }

  /**
   * Adds the type to the types of the Pokemon.
   * @param name the type to add to the types
   */
  public void addType(String type) {
    types.add(type);
  }

  /**
   * Adds the move to the list of moves that the Pokemon can learn.
   * @param move the move that a Pokemon can learn
   */
  public void addMoves(String move) {
    types.add(move);
  }

  /**
   * Adds the ability to the abilities of the Pokemon.
   * @param ability the ability to add to the list of abilities
   */
  public void addAbility(String ability) {
    abilities.add(ability);
  }
  
  /**
   * Returns the height of the Pokemon in metres.
   * @return the height of the Pokemon in metres
   */
  public double getHeight() {
    return height;
  }

  /**
   * Sets the height of the Pokemon in metres.
   * @param height the height of the Pokemon in metres to set
   */
  public void setHeight(double height) {
    this.height = height;
  }
  
  @Override
  public String toString() {
    return getName();
  }
}
