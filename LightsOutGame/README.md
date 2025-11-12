# Lights Out
![Title screen](https://github.com/AnotherUser269/JavaConsoleGames/blob/main/LightsOutGame/src/main/resources/Screenshot_2.png?raw=true)
Lights Out is a simple math puzzle. To guarantee a solution, use the Gaussian elimination method (solver included).

# Contents:
This project contains of 2 main components:

1. Actual game: /src/main/java/game/LightsOut.java
2. Solver: /src/main/java/solver/Solver.java

# How to build and run:
Go inside the "java" folder and write the following to the terminal:
```shell
javac -d out game/LightsOut.java
java -cp out game/LightsOut
```
This will compile and start the game.

```shell
javac -d out solver/Solver.java
java -cp out solver/Solver
```
This will compile and run the program to solve a specific field of the game.

# Customization:

Change constants in src/main/java/game/GameConfig.java to customize board size, initial state, etc.
![Constants](https://github.com/AnotherUser269/JavaConsoleGames/blob/main/LightsOutGame/src/main/resources/Screenshot_3.png?raw=true)

# How to play:
Just follow the instructions on the screen to play the LightsOut puzzle.

![Game](https://github.com/AnotherUser269/JavaConsoleGames/blob/main/LightsOutGame/src/main/resources/Screenshot_1.png?raw=true)

In order to use Solver, go to the MatrixConfig.java and provide your own input.

![Solver](https://github.com/AnotherUser269/JavaConsoleGames/blob/main/LightsOutGame/src/main/resources/Screenshot_4.png?raw=true)
