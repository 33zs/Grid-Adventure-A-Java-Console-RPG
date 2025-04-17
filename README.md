
# Grid Adventure - Java RPG Game 🎮

> A simple text-based 2D Role-Playing Game (RPG) implemented in Java, based on object-oriented principles.

## 📌 Introduction

**Grid Adventure** is a Java-based, console role-playing game (RPG) where players navigate a 2D grid, battle monsters, and strategize their way to victory. Designed using Object-Oriented Programming principles, the game demonstrates class inheritance, encapsulation, polymorphism, and game state management in a modular fashion.

## 📌 Features
-🧭 Explore a 2D grid-based map
-👾 Battle AI-controlled monsters with randomized movement
-⚔️ Turn-based combat system with attack/defense strategies
-🧠 Object-Oriented design using Java abstract classes and inheritance
-🔄 Replayable gameplay with customizable map size, difficulty, and player name
-🎨 Color-coded console output for enhanced visualization



## 🕹️ Game Mechanics

- The player is placed on a 2D grid map.
- Monsters move randomly each round.
- The player can move in four directions and engage in combat.
- The game ends when all monsters are defeated or the player dies.

## 💻 Development Environment

- Language: Java 17+
- IDE: IntelliJ IDEA / Eclipse / VSCode
- No external libraries required (pure Java)

## 🧱 Project Structure

```
GridAdventure/
├── GameCharacter.java     # Abstract base class for all characters
├── Player.java            # Player subclass with attack/defense logic
├── Monster.java           # Monster subclass with AI movement
├── Map.java               # Handles grid layout and character positioning
├── GameLogic.java         # Controls movement, combat, and interactions
├── Game.java              # Manages game rounds and status
├── RunGame.java           # Main entry point with game loop and user input
```


![image](https://github.com/user-attachments/assets/b914dc36-4b73-40dc-8455-71576ec81131)
