package uoa.assignment.game;

import uoa.assignment.character.GameCharacter;
import uoa.assignment.character.Monster;
import uoa.assignment.character.Player;

/**
 * This class represents the game map, including the layout and the characters on it.
 * It is responsible for initializing and displaying the map layout and character positions.
 */
public class Map {

    public String [][] layout;
    public GameCharacter characters []=new GameCharacter[4] ;

    // ANSI Color Codes for enhanced console readability
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREY = "\u001B[37m";

    /**
     * Constructor for the Map class with specified dimensions.
     * Initializes the map layout and places a default player character.
     * @param height The height of the game map.
     * @param width  The width of the game map.
     */
    public Map (int height, int width) {
        this.layout=new String[height][width];
        initialiseArray();
        initialiseCharacter("Player");
    }


    /**
     * Constructor for the Map class with specified dimensions and player name.
     * Initializes the map layout and places a player character with the given name.
     * @param height     The height of the game map.
     * @param width      The width of the game map.
     * @param playerName The name of the player character.
     */
    public Map(int height, int width, String playerName) {
        this.layout = new String[height][width];
        initialiseArray();
        initialiseCharacter(playerName); // Pass the playerName to initialiseCharacter
    }


    /**
     * Prints the current layout of the game map to the console.
     * Each cell of the layout is printed with a space for readability.
     */
    /**
     * Prints the current layout of the game map.
     * Each cell of the layout is displayed with appropriate color coding for readability.
     */
    public void printLayout() {
        System.out.println(ANSI_YELLOW + "\nCurrent game map:" + ANSI_RESET);
        for (int i = 0; i < this.layout.length; i++) {
            for (int j = 0; j < this.layout[i].length; j++) {
                printCellWithColor(this.layout[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Prints a single cell of the layout with appropriate color based on its content.
     * @param cellContent The content of the cell to be printed.
     */
    private void printCellWithColor(String cellContent) {
        switch (cellContent) {
            case "*":
                System.out.print(ANSI_BLUE + "*" + ANSI_RESET + " ");
                break;
            case "%":
                System.out.print(ANSI_RED + "%" + ANSI_RESET + " ");
                break;
            case "x":
                System.out.print(ANSI_GREY + "x" + ANSI_RESET + " ");
                break;
            default:
                System.out.print(cellContent + " ");
        }
    }


    /**
     * Initializes the game map layout with empty spaces represented by '.'.
     * It fills each cell of the layout array with a dot.
     */
    private void initialiseArray(){
        for (int i=0;i<this.layout.length;i++){
            for(int j=0;j<this.layout[i].length;j++){
                this.layout[i][j]=".";
            }
        }
    }



    /**
     * Initializes the characters on the map, including one player with a given name and three monsters.
     * Sets their names and initial positions on the map.
     * @param playerName The name of the player.
     */
    private void initialiseCharacter(String playerName) {
        // Create player with the given name
        Player player = new Player(playerName); // Use playerName in Player constructor

        // Create and position characters
        Monster monsterRightTop = new Monster();
        Monster monsterLeftBottom = new Monster();
        Monster monsterLeftTop = new Monster();

        // Set names for monsters
        monsterRightTop.setName("Monster1");
        monsterLeftBottom.setName("Monster2");
        monsterLeftTop.setName("Monster3");

        // Place characters in the game characters array
        characters[0] = player;
        characters[1] = monsterRightTop;
        characters[2] = monsterLeftBottom;
        characters[3] = monsterLeftTop;

        // Position characters on the map and set their initial coordinates
        layout[0][0] = "%"; // Represents monsterLeftTop
        layout[0][layout[0].length - 1] = "%"; // Represents monsterRightTop
        layout[layout.length - 1][0] = "%"; // Represents monsterLeftBottom
        layout[layout.length - 1][layout[0].length - 1] = "*"; // Represents player

        // Set initial coordinates for each character
        player.column = layout[0].length - 1;
        player.row = layout.length - 1;
        monsterLeftTop.row = 0;
        monsterLeftTop.column = 0;
        monsterLeftBottom.row = layout.length - 1;
        monsterLeftBottom.column = 0;
        monsterRightTop.column = layout[0].length - 1;
        monsterRightTop.row = 0;
    }
}
