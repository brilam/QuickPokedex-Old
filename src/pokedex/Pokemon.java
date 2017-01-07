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
  // All fields for a Pokemon
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
  private List<Integer> types;
  
  /**
   * A class used to build a Pokemon.
   */
  public static class PokemonBuilder {
    // Required field
    private int id;
    // Optional fields
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
    private List<Integer> types;
    
    /**
     * Builds a Pokemon with the specified fields.
     * @return a newly created Pokemon with the specified fields
     */
    public Pokemon build() {
      return new Pokemon(this);
    }

    /**
     * Sets the Pokemon ID for the Pokemon to be built.
     * @param id the ID of the Pokemon to be built
     */
    public PokemonBuilder(int id) {
      this.id = id;
    }
    
    /**
     * Returns the PokemonBuilder instance after setting the name.
     * @param name the name to be set
     * @return the PokemonBuilder instance to be used to build a Pokemon
     */
    public PokemonBuilder setName(String name) {
      this.name = name;
      return this;
    }
    
    /**
     * Returns the PokemonBuilder instance after setting the base experience.
     * @param baseExperience the base experience to be set
     * @return the PokemonBuilder instance to be used to build a Pokemon
     */
    public PokemonBuilder setBaseExperience(int baseExperience) {
      this.baseExperience = baseExperience;
      return this;
    }
    
    /**
     * Returns the PokemonBuilder instance after setting the weight.
     * @param weight the weight to be set
     * @return the PokemonBuilder instance to be used to build a Pokemon
     */
    public PokemonBuilder setWeight(double weight) {
      this.weight = weight;
      return this;
    }
    
    /**
     * Returns the PokemonBuilder instance after setting the abilities.
     * @param abilities the abilities to be set
     * @return the PokemonBuilder instance to be used to build a Pokemon
     */
    public PokemonBuilder setAbilities(List<String> abilities) {
      this.abilities = abilities;
      return this;
    }
    
    /**
     * Returns the PokemonBuilder instance after setting the moves.
     * @param moves the moves to be set
     * @return the PokemonBuilder instance to be used to build a Pokemon
     */
    public PokemonBuilder setMoves(List<String> moves) {
      this.moves = moves;
      return this;
    }
    
    /**
     * Returns the PokemonBuilder instance after setting the health points.
     * @param hp the health points to be set
     * @return the PokemonBuilder instance to be used to build a Pokemon
     */
    public PokemonBuilder setHp(int hp) {
      this.hp = hp;
      return this;
    }
    
    /**
     * Returns the PokemonBuilder instance after setting the attack points.
     * @param attack the attack points to be set
     * @return the PokemonBuilder instance to be used to build a Pokemon
     */
    public PokemonBuilder setAttack(int attack) {
      this.attack = attack;
      return this;
    }
    
    /**
     * Returns the PokemonBuilder instance after setting the defense points.
     * @param defense the defense points to be set
     * @return the PokemonBuilder instance to be used to build a Pokemon
     */
    public PokemonBuilder setDefense(int defense) {
      this.defense = defense;
      return this;
    }
    
    /**
     * Returns the PokemonBuilder instance after setting the special attack points.
     * @param specialAttack the special attack points to be set
     * @return the PokemonBuilder instance to be used to build a Pokemon
     */
    public PokemonBuilder setSpecialAttack(int specialAttack) {
      this.specialAttack = specialAttack;
      return this;
    }
    
    /**
     * Returns the PokemonBuilder instance after setting the special defense points.
     * @param specialDefense the special defense points to be set
     * @return the PokemonBuilder instance to be used to build a Pokemon
     */
    public PokemonBuilder setSpecialDefense(int specialDefense) {
      this.specialDefense = specialDefense;
      return this;
    }
   
    /**
     * Returns the PokemonBuilder instance after setting the speed.
     * @param speed the speed to be set
     * @return the PokemonBuilder instance to be used to build a Pokemon
     */
    public PokemonBuilder setSpeed(int speed) {
      this.speed = speed;
      return this;
    }

    /**
     * Returns the PokemonBuilder instance after setting the height.
     * @param height the height to be set
     * @return the PokemonBuilder instance to be used to build a Pokemon
     */
    public PokemonBuilder setHeight(double height) {
      this.height = height;
      return this;
    }
    
    /**
     * Returns the PokemonBuilder instance after setting the types.
     * @param height the types to be set
     * @return the PokemonBuilder instance to be used to build a Pokemon
     */
    public PokemonBuilder setTypes(List<Integer> types) {
      this.types = types;
      return this;
    }
  }
  
  /**
   * Creates a Pokemon with the specified fields indicated by the PokemonBuilder.
   * @param builder the PokemonBuilder object which contains all the stats of the Pokemon
   */
  private Pokemon(PokemonBuilder builder) {
    id = builder.id;
    name = builder.name;
    baseExperience = builder.baseExperience;
    height = builder.height;
    weight = builder.weight;
    abilities = builder.abilities;
    moves = builder.moves;
    hp = builder.hp;
    attack = builder.attack;
    defense = builder.defense;
    specialAttack = builder.specialAttack;
    specialDefense = builder.specialDefense;
    speed = builder.speed;
    types = builder.types;
  }
  
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
   * Returns the Pokemon's name.
   * @return the name of the Pokemon
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the base experience required to level up.
   * @return the baseExperience required to level up
   */
  public int getBaseExperience() {
    return baseExperience;
  }

  /**
   * Returns the weight of the Pokemon.
   * @return the weight of the Pokemon
   */
  public double getWeight() {
    return weight;
  }

  /**
   * Returns the abilities of the Pokemon.
   * @return the abilities of the Pokemon
   */
  public List<String> getAbilities() {
    return abilities;
  }

  /**
   * Returns a list of all possible moves that the Pokemon can learn.
   * @return the list of moves that the Pokemon can learn
   */
  public List<String> getMoves() {
    return moves;
  }

  /**
   * Return the base health points of the Pokemon.
   * @return the base health points of the Pokemon
   */
  public int getHp() {
    return hp;
  }
  
  /**
   * Returns the base attack points of the Pokemon.
   * @return the base attack points of the Pokemon.
   */
  public int getAttack() {
    return attack;
  }

  /**
   * Returns the base defense points of the Pokemon.
   * @return the base defense points of the Pokemon
   */
  public int getDefense() {
    return defense;
  }

  /**
   * Returns the base special attack of the Pokemon.
   * @return the base specialAttack of the Pokemon
   */
  public int getSpecialAttack() {
    return specialAttack;
  }
 
  /**
   * Returns the base special defense points of the Pokemon.
   * @return the base special defense points of the Pokemon
   */
  public int getSpecialDefense() {
    return specialDefense;
  }

  /**
   * Returns the base speed points of the Pokemon.
   * @return the base speed points of the Pokemon
   */
  public int getSpeed() {
    return speed;
  }

  /**
   * Returns the types of the Pokemon.
   * @return the types of the Pokemon
   */
  public List<Integer> getTypes() {
    return types;
  }

  /**
   * Adds the type to the types of the Pokemon.
   * @param type the ID of the type to add to the types
   */
  public void addType(int type) {
    types.add(type);
  }

  /**
   * Adds the move to the list of moves that the Pokemon can learn.
   * @param move the move that a Pokemon can learn
   */
  public void addMoves(String move) {
    moves.add(move);
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
  
  @Override
  public String toString() {
    return getName();
  }
  
  
}
