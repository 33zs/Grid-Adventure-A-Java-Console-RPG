package uoa.assignment.game;

import java.util.Scanner;

/**
 * This class is the entry point for running the game.
 * It initializes the game, handles user input, and manages game rounds.
 */
public class RunGame {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RED = "\u001B[31m";
    private static boolean gameOver = false;

    private static String playerName = "Player"; // Default player name
    private static int difficulty = 1; // Default difficulty
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Initialize game dimensions
        int height=0, width=0;
        boolean firstRun = true;
        boolean exitGame = false; // Flag to indicate if the game should be exited
        // Main loop for playing multiple games
        while (true) {
            // User input for difficulty if it's not the first run
            if (!firstRun) {
                System.out.print("Please choose the difficulty (1-4): ");
                difficulty = sc.nextInt();
                sc.nextLine(); // Clear the newline character
            }
            // Extract and parse command line arguments if it's the first run
            if (firstRun && args.length > 0) {
                // Check if the arguments are in "height,width,[playername],[difficulty]" format
                if (args.length == 1 && args[0].contains(",")) {
                    String[] parts = args[0].split(",");
                    height = Integer.parseInt(parts[0]);
                    width = Integer.parseInt(parts[1]);

                    // Check if a player name is provided
                    if (parts.length >= 3) {
                        playerName = parts[2];
                    }

                    // Check if a difficulty level is provided
                    if (parts.length == 4) {
                        difficulty = Integer.parseInt(parts[3]);
                    }
                } else if (args.length == 2) {
                    // Handles "height width" format without player name and difficulty
                    height = Integer.parseInt(args[0]);
                    width = Integer.parseInt(args[1]);

                } else if (args.length == 3) {
                    // Handles "height width playername" format without difficulty
                    height = Integer.parseInt(args[0]);
                    width = Integer.parseInt(args[1]);
                    playerName = args[2];

                } else if (args.length == 4) {
                    // Handles "height width playername difficulty" format
                    height = Integer.parseInt(args[0]);
                    width = Integer.parseInt(args[1]);
                    playerName = args[2];
                    difficulty = Integer.parseInt(args[3]);

                } else {
                    // Handles incorrect argument format
                    throw new IllegalArgumentException("Invalid number of arguments. Please enter the dimensions in 'height, width, [playerName], [difficulty]' format.");
                }
            }
            // Welcome message and game initialization
            System.out.printf(ANSI_GREEN + "Welcome to the Game, %s! \nYou choose the difficulty level:%d\nAre you ready to challenge the monsters?" + ANSI_RESET,playerName,difficulty);
            // Initialize the game with the specified dimensions, player name, and difficulty
            Game game = new Game(height, width, playerName, difficulty);
            gameOver = false; // Reset the game over flag
            int round = 1;
            // Main game loop that continues until the game is over
            while (!gameOver) {
                System.out.println();// User input for the next move
                System.out.printf(ANSI_YELLOW+"Round %d%n"+ANSI_RESET, round);
                // Read user input for the next move
                String input = sc.nextLine();
                // Check if user wants to exit the game
                if ("0".equals(input)) {
                    System.out.println(ANSI_GREEN + "Exiting the game." + ANSI_RESET);
                    exitGame = true; // Set the exit flag
                    break;
                }
                // Process the round based on the user input and determine if the game is over
                gameOver = game.nextRound(input);
                // Increment round counter if the user's input was valid
                if (game.validateInput) {
                    round++;// Increment round if input was valid
                }
            }
            if (exitGame) {
                break; // Exit the outer loop if the exit flag is set
            }
            // Post-game actions for replaying
            System.out.print(ANSI_GREEN + "Game over! Do you want to play again? (yes/no): " + ANSI_RESET);
            String playAgainResponse = sc.nextLine().trim().toLowerCase();
            if (!playAgainResponse.equals("yes")) {
                break;
            }
            firstRun = false; // Set the flag to false as it's not the first run anymore

        }
        System.out.println(ANSI_GREEN + "Thanks for playing!" + ANSI_RESET);
        // Close the scanner to prevent resource leaks
        sc.close();
    }
}

