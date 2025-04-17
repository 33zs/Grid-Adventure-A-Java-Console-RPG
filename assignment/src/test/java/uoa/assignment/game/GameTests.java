package uoa.assignment.game;

import uoa.assignment.character.*;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.io.InputStream;

import java.lang.Runnable;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Unit test for simple App.
 */
public class GameTests 
   { 
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RESET = "\u001B[0m";
    
    private final InputStream sysInBackup = System.in; // backup System.in to restore it later
  

    
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream ();
    private final PrintStream originalOut = System.out;
    
     @Before
    public void setUp() throws Exception {
       
        System.setOut (new PrintStream (outContent));
        
        
        
    }
    
    @After
    public void tearDown() throws Exception {
        System.setOut (originalOut);
        
        
    }
    
   
    
     /**
     * Task 2.1 b)
     */
    @Test
    public void checkCharactersArray()
    {
       try {
        Map map = new Map (3,4);
        assertEquals (4, map.characters.length);
        assertEquals (3, map.layout.length);
        assertEquals (4, map.layout[0].length);
            
        map = new Map (4,2);
        assertEquals (4, map.characters.length);
        assertEquals (4, map.layout.length);
        assertEquals (2, map.layout[0].length);   
            
    } catch (Exception | Error e) {
        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+ "Did you create a constructor for the Map class? Is the characters array public and of size 4? Did you create teh 2D array layot as specified? Check Task 2.1"+ ANSI_RESET+ e);
    }
      
    }
    
   
 /**
     * Task 2.2 
     */
    @Test
    public void checkLayoutInitialization()
    {
       try {
        Map map = new Map (2,3);
        map.printLayout(); 
        String output = outContent.toString().trim(); 
        
        int count = 0;
        for (int i = 0 ; i< output.length();i++ ) {
            if (output.charAt(i) == '.') {
               count++; 
            }
        }
        //there should be at least (2*3 - 4 players = 2 ".")   
        assertTrue (count>=2);
        
        outContent.reset();   
        
        map = new Map (10,15);
        map.printLayout();    
           
        output = outContent.toString().trim(); 
        
        count = 0;
        for (int i = 0 ; i< output.length();i++ ) {
            if (output.charAt(i) == '.') {
               count++; 
            }
        }
        //there should be at least (10*15 - 4 players = 146 ".")   
        assertTrue (count>=146);
            
    } catch (Exception | Error e) {
        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+ "Something is not right with the layout variable. Are you populating teh empty fields with '.'? Check Task 2.2" + ANSI_RESET+ e);
    }
    }
    
    
    /**
     * Task 2.3 
     */
   
    @Test
    public void checkPlayerAndMonsterPosition()
    {
       try {
        Map map = new Map (2,3);
        //check player
        assertEquals( 2, map.characters[0].column);
        assertEquals( 1, map.characters[0].row);
        //check top left
        assertEquals( 0, map.characters[3].column);
        assertEquals( 0, map.characters[3].row);
        //check top right
        assertEquals( 2, map.characters[1].column);
        assertEquals( 0, map.characters[1].row);
        //check bottom left
        assertEquals( 0, map.characters[2].column);
        assertEquals( 1, map.characters[2].row);
           
            
    } catch (Exception | Error e) {
        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+ "Something is not right with initialising players and monsters.  Check Task 2.4" + ANSI_RESET + e);
    }
    }
   
    /**
     * Task 2.4 
     */
   
    @Test
    public void checkPlayerAndMonsterPositionInLayout()
    {
       try {
        
        Map map = new Map (10,15);
        map.printLayout();    
           
        String output = outContent.toString().trim(); 
        
        int count = 0;
        int countMonster = 0;
        int countPlayer = 0;
        for (int i = 0 ; i< output.length();i++ ) {
            if (output.charAt(i) == '.') {
               count++; 
            }
            if (output.charAt(i) == '%') {
               countMonster++; 
            }
            if (output.charAt(i) == '*') {
               countPlayer++; 
            }
        }
           
        //there should be exactly (10*15 - 4 players = 146 ".")   
        assertEquals (146,count);
        assertEquals (3,countMonster);
        assertEquals (1,countPlayer);
        
        assertTrue (map.layout[0][0] == "%");
        assertTrue (map.layout[0][map.layout[0].length-1] == "%");
        assertTrue (map.layout[map.layout.length-1][0] == "%");
       assertTrue (map.layout[map.layout.length-1][map.layout[0].length-1] == "*");
           
            
    } catch (Exception | Error e) {
        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+ "Something is not right.  Check Task 2.5" + ANSI_RESET + e);
    }
    }
    
    
  /**
     * Task 3.1 
     */
   
    @Test
    public void checkRunGameMain() throws InterruptedException 
    {
       try {
        String[] arg = {"10","15"};   
        Thread t1=new Thread(){  
        public void run(){  
           RunGame.main (arg);
        }  
        };
        t1.start();  
        
        Thread.currentThread().sleep(1000);
           
        t1.stop();
        String output = outContent.toString().trim(); 
        
        int count = 0;
        int countMonster = 0;
        int countPlayer = 0;
        for (int i = 0 ; i< output.length();i++ ) {
            if (output.charAt(i) == '.') {
               count++; 
            }
            if (output.charAt(i) == '%') {
               countMonster++; 
            }
            if (output.charAt(i) == '*') {
               countPlayer++; 
            }
        }
           
        //there should be exactly (10*15 - 4 players = 146 ".")   
        assertEquals (146,count);
        assertEquals (3,countMonster);
        assertEquals (1,countPlayer);
           
            
    } catch (Exception | Error e) {
        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+ "Something is not right.  Check Task 3.1" + ANSI_RESET + e);
    }
    }
    
 /**
     * Task 3.2 
     */
   
    @Test
    public void checkRunGameMainWithUserInput() throws InterruptedException 
    {
       try {
        String[] arg = {"10","15"};   
        Thread t1=new Thread(){  
        public void run(){  
           ByteArrayInputStream in = new ByteArrayInputStream("up".getBytes());
           System.setIn(in);
          try{ RunGame.main (arg);}
            catch (java.util.NoSuchElementException e) {
                //do nothing
            }
           System.setIn(sysInBackup);
            
           
        }  
        };
        t1.start();  
        
        Thread.currentThread().sleep(1000);
           
        t1.stop();
        String output = outContent.toString().trim(); 
        int count = 0;
        int countMonster = 0;
        int countPlayer = 0;
        for (int i = 0 ; i< output.length();i++ ) {
            if (output.charAt(i) == '.') {
               count++; 
            }
            if (output.charAt(i) == '%') {
               countMonster++; 
            }
            if (output.charAt(i) == '*') {
               countPlayer++; 
            }
        }
          
        
        assertTrue (output.contains("Round 1"));
        
            
    } catch (Exception | Error e) {
        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+ "Something is not right.  Check Task 3.2" + ANSI_RESET + e);
    }
    }
    
  
    
     /**
     * Task 3.3
     */
    @Test
    public void checkMoveCharacter()
    {
          class TestImplementation extends GameCharacter {
           public  TestImplementation(String name) {
		     super(name);
		   }
              public int column = 0;
              public int row = 0;
           @Override
           public void hurtCharacter  (GameCharacter character) {} 
           //it will always defend
           @Override
           public boolean successfulDefense() {return true;} 
       }
       try {
        Map map = new Map (3,4);
        TestImplementation character = new TestImplementation ("milan");
        GameLogic.moveCharacter(java.util.UUID.randomUUID().toString(), map, character);
        String output = outContent.toString().trim(); 
        assertTrue(output.contains("Use only keywords up, down, left, right"));
            
    } catch (Exception | Error e) {
        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+ "Something is not right. moveCharacter method must print exactly this sentence when the use rinput is incorrect: 'Use only keywords up, down, left, right' Check Task 3.3"+ ANSI_RESET+ e);
    }
      
    }
    
    /**
     * Task 3.4 
     */
    @Test
    public void checkMoveCharacterFull()
    {
          try {
        
        Map map = new Map (10,15);
        
        //check player movements
        Player player = (Player) map.characters[0];
        
        GameLogic.moveCharacter ("up",map, player );       
        
        assertEquals (map.layout.length-2, player.row) ;  
        assertEquals (map.layout[0].length-1, player.column);
        
        assertEquals ("*",map.layout [map.layout.length-2][map.layout[0].length-1] );
        
        GameLogic.moveCharacter ("down",map, player );       
        
        assertEquals (map.layout.length-1, player.row) ;  
        assertEquals (map.layout[0].length-1, player.column);
        
        assertEquals ("*",map.layout [map.layout.length-1][map.layout[0].length-1] );
              
        GameLogic.moveCharacter ("left",map, player );       
        
        assertEquals (map.layout.length-1, player.row) ;  
        assertEquals (map.layout[0].length-2, player.column);
        
        assertEquals ("*",map.layout [map.layout.length-1][map.layout[0].length-2] );
              
        GameLogic.moveCharacter ("right",map, player );
        
        assertEquals (map.layout.length-1, player.row) ;  
        assertEquals (map.layout[0].length-1, player.column);
        
        assertEquals ("*",map.layout [map.layout.length-1][map.layout[0].length-1] );
        
        //test monster movements
        Monster monster = (Monster) map.characters[3];
        
        GameLogic.moveCharacter ("down",map, monster );       
        
        assertEquals (1, monster.row) ;  
        assertEquals (0, monster.column);
        
        assertEquals ("%",map.layout [1][0] );
              
        GameLogic.moveCharacter ("up",map, monster );       
        
        assertEquals (0, monster.row) ;  
        assertEquals (0, monster.column);
        
        assertEquals ("%",map.layout [0][0] );
        
        GameLogic.moveCharacter ("right",map, monster );       
        
        assertEquals (0, monster.row) ;  
        assertEquals (1, monster.column);
        
        assertEquals ("%",map.layout [0][1] );
              
        GameLogic.moveCharacter ("left",map, monster );       
        
        assertEquals (0, monster.row) ;  
        assertEquals (0, monster.column);
        
        assertEquals ("%",map.layout [0][0] );
              
       
     } catch (Exception | Error e) {
        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+ "Something is not right.  Check Task 3.4" + ANSI_RESET + e);
    }
    }
    
    /**
     * Task 4.1 
     */
    @Test
    public void checkMoveIntoWall()
    {
          try {
        
        Map map = new Map (10,15);
        
        //check player movements
        Player player = (Player) map.characters[0];
        String output;
        
        GameLogic.moveCharacter ("down",map, player );       
        
        output = outContent.toString().trim(); 
       
        assertTrue (output.contains("You can't go down. You lose a move"));
                   
        outContent.reset();
              
        GameLogic.moveCharacter ("right",map, player );
        output = outContent.toString().trim(); 
        
        assertTrue (output.contains("You can't go right. You lose a move"));
      
        outContent.reset();
              
        //test monster movements
        Monster monster = (Monster) map.characters[3];
        
        GameLogic.moveCharacter ("left",map, monster );       
        output = outContent.toString().trim(); 
        assertTrue (output.contains("You can't go left. You lose a move"));
       
        outContent.reset();
              
        GameLogic.moveCharacter ("up",map, monster );       
        output = outContent.toString().trim(); 
        assertTrue (output.contains("You can't go up. You lose a move"));
       
        
             
     } catch (Exception | Error e) {
        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+ "Something is not right.  Check Task 4.1" + ANSI_RESET + e);
    }
    }
    
    /**
     * Task 4.3 
     */
    @Test
    public void checkMoveOfPlayers()
    {
     try {   
    Game game = new Game (50,50);
    
         int count = 0;
         int charactersMoved =0;
         
        
        do{
        charactersMoved =0;
        count ++;
        String[][] oldGameMap = Arrays.stream(game.getMap().layout).map(String[]::clone).toArray(String[][]::new); 
        
        game.nextRound ("up");
            
        String[][] newGameMap = game.getMap().layout;
            
            for (int i=0;i<newGameMap.length;i++) {
                for (int j=0;j<newGameMap[0].length;j++) {
                   
                    if (!oldGameMap[i][j].equals(newGameMap[i][j])) {
                        charactersMoved++;
                        
                    }
                }
            }
          
        }while (charactersMoved<6 && count <30);
        
      if (count>28) {
          fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+ "Something is not right. Your characters don't seem to be moving.  Check Task 4.3" + ANSI_RESET );
      }       
     } catch (Exception | Error e) {
        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+ "Something is not right.  Check Task 4.3" + ANSI_RESET + e);
    }
    }
    
    /**
     * Task 4.4 
     */
    @Test
    public void checkMoveToOccupiedField()
    {
     try {   
     Map map = new Map (10,10);
     Monster monster2 = (Monster) map.characters[2]; 
     
      monster2.row = 1;
      monster2.column = 0;
      map.layout [9][0] = ".";
      map.layout [1][0] = "%";
      
      GameLogic.moveCharacter ("up", map, monster2) ;  
      String output = outContent.toString().trim();   
      
      assertTrue (output.contains("Monster already there so can't move"));
            
     } catch (Exception | Error e) {
        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+ "Something is not right.    Check Task 4.4" + ANSI_RESET + e);
    }
    }
    
    
    /**
     * Task 5.1 
     */
    @Test
    public void checkHealthInformationPrinted()
    {
     try {   
     Game game = new Game (50,50);
     
     game.nextRound ("up");
         
     String output = outContent.toString().trim();
     String[] testWords = {"Health Monster1:1","Health Monster2:1","Health Monster3:1","Health Player:1","100:4"};
     for (int i=0;i<testWords.length;i++) { 
         String [] items= testWords[i].split(":");
         Pattern pattern = Pattern.compile(items[0]);
         Matcher matcher = pattern.matcher(output);
            int count = 0;
         while (matcher.find()) {
           count++;
         }
      assertEquals (Integer.parseInt(items[1]), count);   
     }
            
     } catch (Exception | Error e) {
        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+ "Something is not right. Are you printing the health information    Check Task 5.1" + ANSI_RESET + e);
    }
    }
    
    /**
     * Task 5.2
     */
     
    @Test
    public void checkKillingMonster()
    {
     try {   
     Map map = new Map (10,10);
     Player player = (Player) map.characters[0];
     Monster monster = (Monster) map.characters[3];
     
      player.row = 1;
      player.column = 0;
      map.layout [map.layout.length-1][map.layout[0].length-1] = ".";
      map.layout [1][0] = "*";
      
      
      for (int i=0; i<1000;i++) {
        GameLogic.moveCharacter ("up", map, player) ;  
      }
        
      assertTrue (monster.getHealth()<=0);
       
     
            
     } catch (Exception | Error e) {
        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+ "Something is not right. It seems that it is not possible to kill a monster...   Check Task 5.2" + ANSI_RESET + e);
    }
    }
    
     /**
     * Task 5.3
     */
    @Test
    public void checkKillingPlayer()
    {
     try {   
     Map map = new Map (10,10);
     Player player = (Player) map.characters[0];
     Monster monster = (Monster) map.characters[3];
     
      player.row = 1;
      player.column = 0;
      map.layout [map.layout.length-1][map.layout[0].length-1] = ".";
      map.layout [1][0] = "*";
      
      for (int i=0; i<1000;i++) {
        GameLogic.moveCharacter ("down", map, monster) ;  
      }
        
      assertTrue (player.getHealth()<=0);
       
      
     
            
     } catch (Exception | Error e) {
        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+ "Something is not right. It seems that monster can't kill the player...   Check Task 5.3" + ANSI_RESET + e);
    }
    }
    
   
    
    /**
     * Task 5.4
     */
    @Test
    public void checkMarkingKilledMonster()
    {
     try {   
     Map map = new Map (10,10);
     Player player = (Player) map.characters[0];
     Monster monster = (Monster) map.characters[3];
     
      player.row = 1;
      player.column = 0;
      map.layout [map.layout.length-1][map.layout[0].length-1] = ".";
      map.layout [1][0] = "*";
      
      for (int i=0; i<1000;i++) {
        GameLogic.moveCharacter ("up", map, player) ;  
      }
        
      assertTrue (monster.getHealth()<=0);
      assertEquals ("x",map.layout [0][0]); 
     
      String output = outContent.toString().trim();
      assertTrue (output.contains("Character already dead"));
    //    System.setOut (originalOut);
    //    System.out.println (output); 
            
     } catch (Exception | Error e) {
        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+ "Something is not right. It seems that it is not possible to kill a monster...   Check Task 5.2" + ANSI_RESET + e);
    }
    }
    
     /**
     * Task 5.5
     */
    @Test
    public void checkEndGame()
    {
     try {  
     Game game = new Game (50,50);
     
     Map map = game.getMap();
     Player player = (Player) map.characters[0];
     player.setHealth(0);
     boolean gameover = game.nextRound ("up");
     
     String output = outContent.toString().trim();
     assertTrue (output.contains("YOU HAVE DIED!"));
     
     assertTrue (gameover);
     
     outContent.reset();
    
         
     Monster monster = (Monster) map.characters[1];
     monster.setHealth(0);
     Monster monster1 = (Monster) map.characters[2];
     monster1.setHealth(0);
     Monster monster2 = (Monster) map.characters[3];
     monster2.setHealth(0);
     
     gameover =  game.nextRound ("up");    
         
      output = outContent.toString().trim();
     assertTrue (output.contains("YOU HAVE WON!"));
     assertTrue (gameover);
     
      
       
            
     } catch (Exception | Error e) {
        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+ "Something is not right. It seems that it is not possible to win or lose the game.   Check Task 5.5" + ANSI_RESET + e);
    }
    }
    
}