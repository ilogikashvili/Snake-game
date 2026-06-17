# 🐍 Snake Game (Java Swing)

A classic Snake game built using Java and Swing. This project demonstrates core concepts of game development such as real-time rendering, keyboard input handling, collision detection, and simple game loop architecture.

---

## 🎮 How to Play

Control the snake using your keyboard:

- ⬅️ Left Arrow → Move left  
- ➡️ Right Arrow → Move right  
- ⬆️ Up Arrow → Move up  
- ⬇️ Down Arrow → Move down  

### Objective:
- Eat the red apples to grow longer
- Each apple increases your score
- Avoid hitting:
  - The walls
  - Your own body

The game ends when a collision occurs.

---

## ✨ Features

- Real-time movement using Swing Timer
- Grid-based movement system
- Random apple spawning
- Snake growth system after eating food
- Self-collision detection
- Wall collision detection
- Score tracking system
- Game Over screen with final score

---

## 🧠 How the Game Works (Code Overview)

### Game Loop
The game runs using a `javax.swing.Timer` which continuously updates:
- Snake movement
- Collision checks
- Repainting the screen

### Input Handling
Keyboard input is handled using `KeyAdapter`, which listens for arrow key presses and updates the snake direction.

### Movement System
The snake body is stored in arrays (`x[]` and `y[]`), where each segment follows the previous one.

### Collision System
The game checks:
- If the head touches the body → Game Over
- If the head touches walls → Game Over
- If the head touches apple → Grow + increase score

---

## 🛠️ Technologies Used

- Java
- Swing (GUI)
- AWT (Graphics rendering)
- Timer (game loop)
- KeyListener (input system)

---

## 🚀 How to Run

### 1. Clone the repository

git clone https://github.com/ilogikashvili/Snake-game.git
2. Open in IDE

Open the project in:

IntelliJ IDEA (recommended)
Eclipse
NetBeans
3. Run the game

Run:

Main.java
📁 Project Structure
SnakeGame/
│
├── src/
│   ├── Main.java
│   ├── GameFrame.java
│   └── GamePanel.java
│
├── .gitignore
├── SnakeGame.iml
└── README.md


📌 Learning Goals

This project was built to understand:

Java Swing GUI development
Game loops and real-time updates
Event-driven programming
Collision detection logic
Basic game architecture design
🚀 Future Improvements
Pause / Resume feature
Increasing difficulty (speed increases over time)
High score saving system (file or database)
Better graphics / animations
Sound effects
👨‍💻 Author
GitHub: https://github.com/ilogikashvili
