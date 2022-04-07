<!-- ABOUT THE PROJECT -->
## These instructions will show you how to use play the game called Tic-Tac-Toe,
   whose program was developed from the group project of CSCI 2121.
```

<!-- ABOUT THE AUTHORS --> 
## Daniel K Nguyen, Sunghyun Nam, Callie Nicole Marshall
```

<!-- ABOUT THE HISTORY -->
- This program is version 3. Started on March 1, 2022, it has been revised three times
  by adding functions--exception handling, counting score, and serialization/deserialization.
- The project was completed on April 18, 2022.
``` 

<!-- FILES -->
- There are six java files - TicTacToe, Game, Symbol, InvalidInputException, 
  Score, and Serializer.
- Play the game on the TicTacToe.
``` 

<!-- ABOUT THE GAME RULES -->
## Here are the rules of gameplay
- Tic-Tac-Toe is a two-player game. In the game, three by three board is given.
- Two players take turns marking the spaces with X or O. The player who succeeds 
  in placing three of their marks in a horizontal, vertical, or diagonal row is
  the winnder. 
- If the board was filled without winnding conditions, it will tie.

- The user can find more information in the below link:
  [!(https://en.wikipedia.org/wiki/Tic-tac-toe) 

- In this program, the opponent will be computer and will mark by a random choice.
``` 

<!-- ABOUT HOW TO PLAY -->
## Procedure

1. You will be asked playing a new game or loading an old(saved) game 
   by selecting numbers 1 and 0. 
   If you choose 1, an empty board will appear on the screen.
   If you choose 0, the saved old board will appear on the screen.

2. For a new game, you will be asked to choose your symbol between X and O
   by selecting 1 and 0.

3. Mark your symbol by typing 1 - 9, each of which is assigned to the space
   of the board from left to right and top to bottom.

4. As soon as you mark your space, the opponent(computer) will immediately
   mark its.

5. If you win, "You won!" will appear on the screen.

6. If you win, the score of the player will be incremented by one and appear 
   on the screen.

7. If you lose, "You lose!" will appear on the screen.

8. If you tie, "It's a Tie!" will appear on the screen.

9. After the game is finished, you will be asked whether you want to continue
   to play or quit by selecting numbers 1 and 0.

10. If you choose to quite, "Thanks for playing!" will appear and you will be 
    asked whether you want to save the game or not by selecting numbers 1 and 0. 

11. If you choose to save, the status of your game will be saved in the file 
    named Game.dat.

12. The saved game and score will be recovered by reading the game. The leading
    option will be asked in the next game.   
``` 

<!-- ABOUT EXCEPTION HANDLING -->
- All wrong choices in gaming, saving, and reading will be approprately handled by
  built-in and custom handlings. As a result, there is no crash of the program will occurr. 
``` 


