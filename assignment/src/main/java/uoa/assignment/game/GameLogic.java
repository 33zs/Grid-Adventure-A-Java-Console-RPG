package uoa.assignment.game;

import java.util.Scanner;

import uoa.assignment.character.GameCharacter;
import uoa.assignment.character.Player;
import uoa.assignment.character.Monster;

/**
 * This class handles the game logic, including character movements and interactions.
 * It provides methods to move characters and handle interactions like attacks.
 */
public class GameLogic {
    // ANSI Color Codes for enhanced console output readability
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_ORANGE = "\u001B[38;5;208m";


    /**
     * Moves a character based on the provided input.
     * @param input The direction in which the character should move.
     * @param gameMap The current game map.
     * @param character The character to be moved.
     * @return true if the move was successful, false otherwise.
     */
    public static boolean moveCharacter(String input, Map gameMap, GameCharacter character) {
        // Validate input
        if (!input.equals("up") && !input.equals("down") && !input.equals("left") && !input.equals("right")){
            System.out.println(ANSI_RED+"Use only keywords up, down, left, right"+ANSI_RESET);
            return false;
        } else {
            // Announce player movement
            if (character instanceof Player){
                System.out.println();
                System.out.println(character.sayName()+" is moving "+input);
            }
            // Move character based on input
            switch (input) {
                case "up":
                    moveUp(character, gameMap);
                    break;
                case "down":
                    moveDown(character, gameMap);
                    break;
                case "left":
                    moveLeft(character, gameMap);
                    break;
                case "right":
                    moveRight(character, gameMap);
                    break;
            }
            return true;
        }
    }


    /**
     * Moves the character to the right on the game map.
     * This method checks if the move is possible and performs the move if it is.
     * @param character The character to be moved.
     * @param gameMap The current state of the game map.
     */
    private static void moveRight(GameCharacter character, Map gameMap) {
        // Check if character is at the rightmost edge of the map
        if (character.column == gameMap.layout[0].length - 1) {
            System.out.println(ANSI_ORANGE+"You can't go right. You lose a move."+ANSI_RESET);
        } else {
            // Move the character right and update the map accordingly
            int oldColumn = character.column;
            character.column += 1;
            if (updateCharacterPosition(character, gameMap)) {
                gameMap.layout[character.row][oldColumn] = ".";
            } else {
                character.column -= 1; // Revert move if unsuccessful
            }
        }
    }


    /**
     * Moves the character to the left on the game map.
     * This method checks if the move is possible and performs the move if it is.
     * @param character The character to be moved.
     * @param gameMap The current state of the game map.
     */
    private static void moveLeft(GameCharacter character, Map gameMap) {
        // Check if character is at the leftmost edge of the map
        if (character.column == 0) {
            System.out.println(ANSI_ORANGE+"You can't go left. You lose a move."+ANSI_RESET);
        } else {
            // Move the character left and update the map accordingly
            int oldColumn = character.column;
            character.column -= 1;
            if (updateCharacterPosition(character, gameMap)) {
                gameMap.layout[character.row][oldColumn] = ".";
            } else {
                character.column += 1; // Revert move if unsuccessful
            }
        }
    }


    /**
     * Moves the character to the up on the game map.
     * This method checks if the move is possible and performs the move if it is.
     * @param character The character to be moved.
     * @param gameMap The current state of the game map.
     */
    private static void moveUp(GameCharacter character, Map gameMap) {
        // Check if character is at the upmost edge of the map
        if (character.row == 0) {
            System.out.println(ANSI_ORANGE+"You can't go up. You lose a move."+ANSI_RESET);
        } else {
            // Move the character up and update the map accordingly
            int oldRow = character.row;
            character.row -= 1;
            if (updateCharacterPosition(character, gameMap)) {
                gameMap.layout[oldRow][character.column] = ".";
            } else {
                character.row += 1; // Revert move if unsuccessful
            }
        }
    }


    /**
     * Moves the character to the down on the game map.
     * This method checks if the move is possible and performs the move if it is.
     * @param character The character to be moved.
     * @param gameMap The current state of the game map.
     */
    private static void moveDown(GameCharacter character, Map gameMap) {
        // Check if character is at the downmost edge of the map
        if (character.row == gameMap.layout.length - 1) {
            System.out.println(ANSI_ORANGE+"You can't go down. You lose a move."+ANSI_RESET);
        } else {
            // Move the character down and update the map accordingly
            int oldRow = character.row;
            character.row += 1;
            if (updateCharacterPosition(character, gameMap)) {
                gameMap.layout[oldRow][character.column] = ".";
            } else {
                character.row -= 1; // Revert move if unsuccessful
            }
        }
    }


    /**
     * Updates the character's position on the game map and handles interactions.
     * This method checks the new position and decides if the move is successful or if an interaction occurs.
     * @param character The character to move.
     * @param gameMap The current state of the game map.
     * @return true if the character successfully moved, false otherwise.
     */
    private static boolean updateCharacterPosition(GameCharacter character, Map gameMap) {
        String currentPosition = gameMap.layout[character.row][character.column];

        // Handles different scenarios of character interactions based on their position.

        // Scenario 1 & 5: Player or Monster encounters another character
        if (character instanceof Player && currentPosition.equals("%") ||
                character instanceof Monster && currentPosition.equals("*")) {
            attack(character, gameMap);   // Execute attack logic
            return false;// No move is made in case of an attack
        }

        // Scenario 2 & 6: Move to empty space
        if (currentPosition.equals(".")) {
            gameMap.layout[character.row][character.column] = character instanceof Player ? "*" : "%";
            return true;// Movement successful
        }

        // Scenario 3: Player encounters dead monster
        if (character instanceof Player && currentPosition.equals("x")) {
            System.out.println(ANSI_ORANGE+"Character already dead"+ANSI_RESET);
            return false;
        }

        // Scenario 4: Monster encounters any other monster
        if (character instanceof Monster && (currentPosition.equals("%") || currentPosition.equals("x"))) {
            System.out.println(ANSI_ORANGE+"Monster already there so can't move"+ANSI_RESET);
            return false;
        }

        return false; // Movement unsuccessful
    }


    /**
     * Handles the attack logic between characters.
     * This method is called when a character encounters another character on the map.
     * @param character The attacking character.
     * @param gameMap The current state of the game map.
     */
    private static void attack(GameCharacter character, Map gameMap){
        // Player attacks a living monster
        if(character instanceof Player && gameMap.layout[character.row][character.column].equals("%")) {
            for (int i=1;i<4;i++){
                // Check if the monster is at the attacked position
                if (gameMap.characters[i].column==character.column &&  gameMap.characters[i].row==character.row){
                    int initialHealth=gameMap.characters[i].getHealth();
                    character.hurtCharacter(gameMap.characters[i]);
                    // Check if the attack was successful
                    if (initialHealth>gameMap.characters[i].getHealth()){
                        System.out.println(ANSI_GREEN+"!!HIT!! " + ANSI_RESET+gameMap.characters[0].sayName()+" successfully attacked "+gameMap.characters[i].sayName());
                    }else {
                        System.out.println(ANSI_PURPLE+"!!MISS!! "+ANSI_RESET+gameMap.characters[i].sayName()+" successfully defended attack from "+gameMap.characters[0].sayName());
                    }
                    // Check if the target is dead and update the game map accordingly
                    if (gameMap.characters[i].getHealth()<=0) {
                        gameMap.layout[character.row][character.column]="x";
                    }
                }
            }
        } // Monster attacks the player
        else if(character instanceof Monster && gameMap.layout[character.row][character.column].equals("*")) {
            int  initialHealth =gameMap.characters[0].getHealth();
            character.hurtCharacter(gameMap.characters[0]);
            // Check if the attack was successful
            if (initialHealth > gameMap.characters[0].getHealth()) {
                System.out.println(ANSI_GREEN+"!!HIT!! " + ANSI_RESET + character.sayName() + " successfully attacked "+gameMap.characters[0].sayName());
            } else {
                System.out.println(ANSI_PURPLE+"!!MISS!! "+ANSI_RESET+gameMap.characters[0].sayName()+" successfully defended attack from " + character.sayName());
            }
        }
    }
}
