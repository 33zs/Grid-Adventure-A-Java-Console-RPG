package uoa.assignment.character;

import java.util.Random;
/**
 * This class represents a Player character in the game.
 * It extends the GameCharacter class and defines specific behaviors for a Player.
 */

public class Player extends GameCharacter{
    private static final int DEFAULT_DIFFICULTY = 1; // Default difficulty level
    public int attackPoint=-50;// Default attackPoint
    public double defenseSuccessRate=0.3;// Default defenseSuccessRate

    /**
     * Constructor for a Player with a name.
     * Initializes the Player with default difficulty.
     *
     * @param name The name of the Player.
     */
    public Player(String name) {
        super(name);
        setDifficulty(DEFAULT_DIFFICULTY);
    }


    /**
     * Default constructor for a Player.
     */
    public Player(){
        // Inherits default behavior from GameCharacter
    }


    /**
     * Sets the difficulty level of the player with corresponding attack and defense parameters.
     *
     * @param difficultyLevel The difficulty level to be set.
     */
    public void setDifficulty(int difficultyLevel) {
        switch (difficultyLevel) {
            case 1: // Easy
                attackPoint = -50;
                defenseSuccessRate = 0.3;
                break;
            case 2: // Medium
                attackPoint = -40;
                defenseSuccessRate = 0.35;
                break;
            case 3: // Hard
                attackPoint = -25;
                defenseSuccessRate = 0.3;
                break;
            case 4: // Very Hard
                attackPoint = -35;
                defenseSuccessRate = 0.4;
                break;
            default:
                throw new IllegalArgumentException("Invalid difficulty level: " + difficultyLevel);
        }
    }


    /**
     * Attacks another character if they fail to defend. Deducts health based on attack power.
     *
     * @param character The character to be attacked.
     */
    @Override
    public void hurtCharacter(GameCharacter character) {
        if (!character.successfulDefense()) {
            int newHealth = character.getHealth() + attackPoint; // Deducts health based on attack power
            character.setHealth(newHealth);
        }
    }


    /**
     * Randomly decides if the Player successfully defends an attack.
     *
     * @return true if the defense is successful, false otherwise.
     */
    @Override
    public boolean successfulDefense() {
        Random random = new Random();
        return random.nextDouble() < defenseSuccessRate; // Chance of successful defense
    }
}
