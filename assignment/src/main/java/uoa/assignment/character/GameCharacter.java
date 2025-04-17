package uoa.assignment.character;
/**
 * This abstract class represents a generic game character in the game.
 * It defines basic properties and behaviors common to all characters, such as health, position, and interaction methods.
 */
public abstract class GameCharacter {

    private String name ;

    private int health=100;

    public int row;// Row position of the character on the game map

    public int column; // Column position of the character on the game map

    /**
     * Constructor for a game character with a specified name.
     * Initializes the character with default health of 100.
     *
     * @param name The name of the game character.
     */
    public GameCharacter(String name) {
        this.name = name;
        this.health = 100; // Default health is set to 100
    }


    /**
     * Default constructor for a game character.
     * Initializes the character with a null name and default health of 100.
     */
    public GameCharacter() {
        this(null);
    }


    /**
     * Abstract method for character interaction. Defines how this character can hurt another character.
     *
     * @param character The game character to interact with.
     */
    public abstract void hurtCharacter(GameCharacter character);


    /**
     * Abstract method to determine if this character successfully defends against an attack.
     *
     * @return true if the defense is successful, false otherwise.
     */
    public abstract boolean successfulDefense();


    /**
     * Returns the name of the character.
     *
     * @return The name of the character.
     */
    public String sayName() {
        return this.name;
    }


    /**
     * Sets the name of the character.
     *
     * @param name The new name of the character.
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Returns the health of the character.
     *
     * @return The current health of the character.
     */
    public int getHealth() {
        return this.health;
    }


    /**
     * Sets the health of this character. If the health value is below zero, it is set to zero.
     *
     * @param health The new health value of the character.
     */
    public void setHealth(int health) {
        this.health = Math.max(health, 0); // Ensure health does not go below zero
    }
}

