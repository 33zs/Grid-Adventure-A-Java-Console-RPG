package uoa.assignment.character;

import java.util.Random;
/**
 * This class represents a Monster character in the game.
 * It extends the GameCharacter class and defines specific behaviors for a Monster.
 */
public class Monster extends GameCharacter {
    private static final int DEFAULT_DIFFICULTY = 1; // Default difficulty level
    public int attackPoint=-20;// Default attackPoint
    public double defenseSuccessRate=0.5;// Default defenseSuccessRate


    /**
     * Constructor for a Monster with a name.
     * Initializes the Monster with default difficulty.
     *
     * @param name The name of the Monster.
     */
    public  Monster(String name) {
        super(name);
        setDifficulty(DEFAULT_DIFFICULTY);
    }


    /**
     * Default constructor for a Monster.
     */
    public Monster(){
        // Inherits default behavior from GameCharacter
    }


    /**
     * Sets the difficulty level of the Monster.
     * Adjusts the Monster's attack power and defense success rate based on the specified level.
     *
     * @param difficultyLevel The difficulty level to set.
     */
    public void setDifficulty(int difficultyLevel) {
        switch (difficultyLevel) {
            case 1: // Easy
                attackPoint = -20;
                defenseSuccessRate = 0.5;
                break;
            case 2: // Medium
                attackPoint = -25;
                defenseSuccessRate = 0.45;
                break;
            case 3: // Hard
                attackPoint = -35;
                defenseSuccessRate = 0.4;
                break;
            case 4: // Very Hard
                attackPoint = -50;
                defenseSuccessRate = 0.4;
                break;
            default:
                throw new IllegalArgumentException("Invalid difficulty level: " + difficultyLevel);
        }
    }


    /**
     * Attacks another character, reducing their health if they fail to defend.
     *
     * @param character The character to be attacked.
     */
    @Override
    public void hurtCharacter(GameCharacter character) {
        if (!character.successfulDefense()) {
            int newHealth = character.getHealth() + attackPoint;
            character.setHealth(newHealth);
        }
    }


    /**
     * Randomly decides if the Monster successfully defends an attack.
     *
     * @return true if the defense is successful, false otherwise.
     */
    @Override
    public boolean successfulDefense() {
        Random random = new Random();
        return random.nextDouble() < defenseSuccessRate;
    }


    /**
     * Decides the next move of the Monster randomly among "up", "down", "left", "right".
     *
     * @return A string representing the next move.
     */
    public String decideMove() {
        String[] moves = {"up", "down", "left", "right"};
        Random random = new Random();
        return moves[random.nextInt(moves.length)];
    }
}
