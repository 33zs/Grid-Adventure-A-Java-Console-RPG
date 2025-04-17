package uoa.assignment.game;
import uoa.assignment.character.Monster;
import uoa.assignment.character.GameCharacter;
import uoa.assignment.character.Player;

/**
 * Represents the main game logic and state. This class is responsible for initializing
 * the game, handling the game rounds, and determining the game's end condition.
 */
public class Game {
    private Map map;
    private GameLogic gameLogic;
    public boolean validateInput;

    // ANSI Color Codes for console output for better visual representation
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_GREY = "\u001B[37m";


    /**
     * Constructor for the Game class.
     * Initializes the game with the given map dimensions.
     * @param height The height of the game map.
     * @param width  The width of the game map.
     * @throws IllegalArgumentException if map size is non-positive.
     */
    public Game(int height, int width) {
        validateMapSize(height, width);
        initializeGame(height, width, "Player", 1);
    }


    /**
     * Constructor for the Game class with player name.
     * Initializes the game with the given map dimensions and player's name.
     * @param height The height of the game map.
     * @param width  The width of the game map.
     * @param playerName Optional parameter for the player's name.
     * @throws IllegalArgumentException if map size is non-positive.
     */
    public Game(int height, int width, String playerName) {
        validateMapSize(height, width);
        initializeGame(height, width, playerName, 1);
    }


    /**
     * Constructor for the Game class with difficulty level.
     * Initializes the game with the given map dimensions, player name, and difficulty level.
     * @param height The height of the game map.
     * @param width The width of the game map.
     * @param playerName The name of the player.
     * @param difficulty The difficulty level of the game.
     * @throws IllegalArgumentException if map size is non-positive or difficulty level is invalid.
     */
    public Game(int height, int width, String playerName, int difficulty) {
        validateMapSize(height, width);
        validateDifficultyLevel(difficulty);
        initializeGame(height, width, playerName, difficulty);
    }


    /**
     * Validates the map size.
     * @param height The height of the map.
     * @param width The width of the map.
     * @throws IllegalArgumentException if height or width is non-positive.
     */
    private void validateMapSize(int height, int width) {
        if (height <= 0 || width <= 0) {
            throw new IllegalArgumentException("Map size must be positive.");
        }
    }


    /**
     * Validates the difficulty level.
     * @param difficulty The game difficulty level.
     * @throws IllegalArgumentException if difficulty level is outside the range 1-4.
     */
    private void validateDifficultyLevel(int difficulty) {
        if (difficulty < 1 || difficulty > 4) {
            throw new IllegalArgumentException("Invalid difficulty level. Please choose a difficulty between 1 and 4.");
        }
    }


    /**
     * Initializes the game with specified parameters.
     * @param height The height of the map.
     * @param width The width of the map.
     * @param playerName The player's name.
     * @param difficulty The game difficulty.
     */
    private void initializeGame(int height, int width, String playerName, int difficulty) {
        this.map = new Map(height, width, playerName);
        this.gameLogic = new GameLogic();
        adjustGameDifficulty(difficulty);
        this.validateInput = true;
        this.map.printLayout();
    }


    /**
     * Adjusts game parameters based on the difficulty level.
     * This method is used to scale the difficulty of monsters in the game.
     * @param difficulty The difficulty level.
     */
    private void adjustGameDifficulty(int difficulty) {
        for (GameCharacter character : this.map.characters) {
            if (character instanceof Monster) {
                ((Monster) character).setDifficulty(difficulty);
            } else if (character instanceof Player) {
                ((Player) character).setDifficulty(difficulty);
            }
        }
    }


    /**
     * Returns the current game map.
     * @return The current game map.
     */
    public Map getMap() {
        return this.map;
    }


    /**
     * Processes the next round of the game based on the player's input.
     * First, it moves the player character based on the input. Then, it iterates through all monsters
     * and moves them if they are still alive. If a monster is defeated, it marks their position with an 'x'.
     * After moving the characters, it checks if the game is over.
     *
     * @param input The player's move input.
     * @return true if the game is over (either all monsters are defeated or the player is dead), false otherwise.
     */
    public boolean nextRound(String input) {
        System.out.println(ANSI_GREEN+"Starting interaction..."+ANSI_RESET);
        // Attempt to move the player character. If the move is valid, proceed with the round.
        if (this.gameLogic.moveCharacter(input, this.map, this.map.characters[0])) {
            // Flag to check if all monsters are defeated.
            boolean allMonstersDefeated = true;

            // Iterate over all characters in the game.
            for (int i = 1; i < this.map.characters.length; i++) {
                GameCharacter character = this.map.characters[i];
                // Check if the character is a Monster.
                if (character instanceof Monster) {
                    Monster monster = (Monster) character;
                    // If the monster is still alive, it makes a move.
                    if (monster.getHealth() > 0) {
                        String monsterMove = monster.decideMove();
                        System.out.println("Monster" + i + " is moving " + monsterMove);
                        this.gameLogic.moveCharacter(monsterMove, this.map, monster);
                        // Since at least one monster is alive, the game is not over.
                        allMonstersDefeated = false;
                    } else {
                        // If the monster is defeated, mark its position with an 'x'.
                        this.map.layout[character.row][character.column] = "x";
                    }
                }
            }
            // Print the health status of all characters.
            printHealthStatus();
            // Check if the game is over and return the result.
            return checkGameOver(allMonstersDefeated);
        } else {
            // If the player's move input is invalid, do not proceed with the round.
            this.validateInput = false;
        }
        // Return false to indicate that the game is still ongoing and has not reached a conclusion.
        return false;

    }


    /**
     * Prints the health status of the player and all monsters.
     * This is used to give a quick overview of the game state to the player.
     */
    private void printHealthStatus() {
        System.out.println();
        System.out.println(ANSI_YELLOW + "Current Health Status:" + ANSI_RESET);
        System.out.println("Health "+this.map.characters[0].sayName()+": " + ANSI_RED+this.map.characters[0].getHealth() + ANSI_RESET);
        for (int i = 1; i < this.map.characters.length; i++) {
            System.out.println("Health Monster" + i + ": " + ANSI_RED+this.map.characters[i].getHealth()+ ANSI_RESET);
        }
        System.out.println();
    }


    /**
     * Checks if the game is over, either by the player winning or dying.
     * @param allMonstersDefeated Flag indicating if all monsters have been defeated.
     * @return true if the game is over, false otherwise.
     */
    private boolean checkGameOver(boolean allMonstersDefeated) {
        if (allMonstersDefeated) {
            System.out.println(ANSI_GREEN +"YOU HAVE WON!" + ANSI_RESET);
            this.map.printLayout();
            return true;
        }
        if (this.map.characters[0].getHealth() <= 0) {
            System.out.println(ANSI_RED +"YOU HAVE DIED!"+ ANSI_RESET);
            this.map.printLayout();
            return true;
        }
        this.map.printLayout();
        return false;
    }
}

