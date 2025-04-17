package uoa.assignment.character;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.lang.reflect.Field;


/**
 * Unit test for simple App.
 */
public class CharacterTests 
{
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream ();
    private final PrintStream originalOut = System.out;
    
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RESET = "\u001B[0m";
    
    @Before
    public void setUp() throws Exception {
       
        System.setOut (new PrintStream (outContent));
    }
    
    @After
    public void tearDown() throws Exception {
        System.setOut (originalOut);
        
    }
    
    
    
    /**
     * Task 1.2
     */
    @Test
    public void checkSayName()
    {
       class TestImplementation extends GameCharacter {
           public  TestImplementation(String name) {
		     super(name);
		   }
           @Override
           public void hurtCharacter  (GameCharacter character) {} 
           @Override
           public boolean successfulDefense() {return true;} 
       }
       try {
        assertEquals ("milan", new TestImplementation("milan").sayName());
    } catch (Exception | Error e) {
        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+"Make sure the constructor of GameCharacter class is modified and sayName() method exist as per T1.2 "+ ANSI_RESET+ e);
    }
    }
    
    /**
     * Task 1.3
     */
    @Test
    public void checkHealthFunctionality()
    {
       class TestImplementation extends GameCharacter {
           public  TestImplementation(String name) {
		     super(name);
		   }
           @Override
           public void hurtCharacter  (GameCharacter character) {} 
           @Override
           public boolean successfulDefense() {return true;} 
       }
       try {
           TestImplementation test = new TestImplementation ("milan");
           
           assertEquals (100, test.getHealth());
           test.setHealth(50);
           assertEquals (50, test.getHealth());
           
    } catch (Exception | Error e) {
        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+"Make sure the class GameCharacter contains methods getHealth()and setHealth() as per T1.3 "+ ANSI_RESET+ e);
    }
    }
    
    /**
     * Task 1.4 a)
     */
    @Test
    public void checkMonsterClassSuccesfulAttack()
    {
       class TestImplementation extends GameCharacter {
           public  TestImplementation(String name) {
		     super(name);
		   }
           @Override
           public void hurtCharacter  (GameCharacter character) {} 
           //it will never defend
           @Override
           public boolean successfulDefense() {return false;} 
       }
       try {
           Monster monster = new Monster ("monster1"); 
           
           //there shoudl not be any random element in hurtCharacter()
           for (int i =0; i< 100;i++) {
           TestImplementation test = new TestImplementation ("milan");
           monster.hurtCharacter(test);
           assertEquals (80, test.getHealth());
           monster.hurtCharacter(test);
           assertEquals (60, test.getHealth());
           }
           
    } catch (Exception | Error e) {
        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+"Make sure the class Monster implements method hurtCharacter() as per T1.4 and all previous tasks have been completed."+ ANSI_RESET+ e);
    }
    }
    
    /**
     * Task 1.4 b)
     */
    @Test
    public void checkMonsterClassFailedfulAttack()
    {
       class TestImplementation extends GameCharacter {
           public  TestImplementation(String name) {
		     super(name);
		   }
           @Override
           public void hurtCharacter  (GameCharacter character) {} 
           //it will always defend
           @Override
           public boolean successfulDefense() {return true;} 
       }
       try {
           Monster monster = new Monster ("monster1"); 
           
           //there shoudl not be any random element in hurtCharacter()
           for (int i =0; i< 100;i++) {
           TestImplementation test = new TestImplementation ("milan");
           monster.hurtCharacter(test);
           assertEquals (100, test.getHealth());
           monster.hurtCharacter(test);
           assertEquals (100, test.getHealth());
           }
           
    } catch (Exception | Error e) {
        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+"Make sure the class Monster implements method hurtCharacter() as per T1.4 and all previous tasks have been completed."+ ANSI_RESET+ e);
    }
    }
    
    /**
     * Task 1.4 c)
     */
    @Test
    public void checkIfMonsterDefenceLooksRandom()
    {
       
       try {
           Monster monster = new Monster ("monster1"); 
           int positiveResult = 0;
           int negativeResult = 0;
           //there shoudl not be any random element in hurtCharacter()
           for (int i =0; i< 100000;i++) {
           boolean result = monster.successfulDefense ();
           if (result) {
               positiveResult++;
           }
           else if (!result) {
               negativeResult++;
               }
           }
           
           if (negativeResult<15000||positiveResult <15000) {
            fail (ANSI_WHITE_BACKGROUND +ANSI_BLACK+"Is your method Monster.successfulDefence () really random?"+ ANSI_RESET);    
           }
           
           
    } catch (Exception | Error e) {
        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+"Make sure the class Monster implements method hurtCharacter() as per T1.4 and all previous tasks have been completed."+ ANSI_RESET+ e);
    }
    }
    
    /**
     * Task 1.5 a)
     */
    @Test
    public void checkPlayerClassSuccesfulAttack()
    {
       class TestImplementation extends GameCharacter {
           public  TestImplementation(String name) {
		     super(name);
		   }
           @Override
           public void hurtCharacter  (GameCharacter character) {} 
           //it will never defend
           @Override
           public boolean successfulDefense() {return false;} 
       }
       try {
           Player player = new Player ("player"); 
           
           //there shoudl not be any random element in hurtCharacter()
           for (int i =0; i< 100;i++) {
           TestImplementation test = new TestImplementation ("milan");
           player.hurtCharacter(test);
           assertEquals (50, test.getHealth());
           player.hurtCharacter(test);
           assertEquals (0, test.getHealth());
           }
           
    } catch (Exception | Error e) {
        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+"Make sure the class Monster implements method hurtCharacter() as per T1.4 and all previous tasks have been completed."+ ANSI_RESET+ e);
    }
    }
    
    /**
     * Task 1.4 b)
     */
    @Test
    public void checkPlayerClassFailedfulAttack()
    {
       class TestImplementation extends GameCharacter {
           public  TestImplementation(String name) {
		     super(name);
		   }
           @Override
           public void hurtCharacter  (GameCharacter character) {} 
           //it will always defend
           @Override
           public boolean successfulDefense() {return true;} 
       }
       try {
            Player player = new Player ("player");  
           
           //there shoudl not be any random element in hurtCharacter()
           for (int i =0; i< 100;i++) {
           TestImplementation test = new TestImplementation ("milan");
           player.hurtCharacter(test);
           assertEquals (100, test.getHealth());
           player.hurtCharacter(test);
           assertEquals (100, test.getHealth());
           }
           
    } catch (Exception | Error e) {
        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+"Make sure the class Monster implements method hurtCharacter() as per T1.4 and all previous tasks have been completed."+ ANSI_RESET+ e);
    }
    }
    
    /**
     * Task 1.4 c)
     */
    @Test
    public void checkIfPlayerDefenceLooksRandom()
    {
       
       try {
           Player player = new Player ("monster1"); 
           int positiveResult = 0;
           int negativeResult = 0;
           //there shoudl not be any random element in hurtCharacter()
           for (int i =0; i< 100000;i++) {
           boolean result = player.successfulDefense ();
           if (result) {
               positiveResult++;
           }
           else if (!result) {
               negativeResult++;
               }
           }
           
           if (negativeResult<60000||positiveResult >40000) {
            fail (ANSI_WHITE_BACKGROUND +ANSI_BLACK+"Is your method Player.successfulDefence () really 30/70? Our test on 100k tries shows "+positiveResult +":"+negativeResult +" See task 1.5 instructions"+ ANSI_RESET);    
           }
           
           
    } catch (Exception | Error e) {
        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+"Make sure the class Monster implements method hurtCharacter() as per T1.4 and all previous tasks have been completed."+ ANSI_RESET+ e);
    }
    }
    
    
     
   
    
    
    /**
     * Task 4.2
     */
    @Test
    public void checkIfMonsterDecidesRandomly()
    {
       
       try {
           Monster monster = new Monster ("monster1"); 
           int up = 0;
           int down = 0;
           int left = 0;
           int right = 0;
           
           for (int i =0; i< 100000;i++) {
           String result = monster.decideMove ();
           if (result.equals("up")) {
               up++;
           }
           else if (result.equals("down")) {
               down++;
           } else if (result.equals("left")) {
               left++;
           }else if (result.equals("right")) {
               right++;
           }
           }
           
           if (up<20000||down <20000||left<20000||right <20000) {
            fail (ANSI_WHITE_BACKGROUND +ANSI_BLACK+"Is your method Monster.decideMove() really random Our test on 100k tries shows that these many decisions were generated for each category up:"+up +" down:"+down +" left:"+left +" right:"+right +" See task 1.5 instructions"+ ANSI_RESET);    
           }
           
           
    } catch (Exception | Error e) {
        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+"Make sure the class Monster contains a method decideMove() as per T4.2 and all previous tasks have been completed."+ ANSI_RESET+ e);
    }
    }
    
}
