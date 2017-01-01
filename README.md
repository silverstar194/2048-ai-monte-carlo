## AI to play 2048 based on Monte Carlo Algorithm
#### Gameplay
2048 is played on a 4×4 grid, with numbered tiles that slide smoothly when a player moves them using the four arrow keys Every turn, a new tile will randomly appear in an empty spot on the board with a value of either 2 or 4. (Probability of 90%: 2, 10%: 4) Tiles slide as far as possible in the chosen direction until they are stopped by either another tile or the edge of the grid. If two tiles of the same number collide while moving, they will merge into a tile with the total value of the two tiles that collided. <i>(https://en.wikipedia.org/wiki/2048_(video_game))</i>

#### Scoring
A scoreboard on the upper-right keeps track of the user's score. The user's score starts at zero, and is incremented whenever two tiles combine, by the value of the new tile. <i>(https://en.wikipedia.org/wiki/2048_(video_game))</i>

#### Game Over and Winning
The game is won when a tile with a value of 2048 appears on the board, hence the name of the game. After reaching the 2048 tile, players can continue to play (beyond the 2048 tile) to reach higher scores. When the player has no legal moves (there are no empty spaces and no adjacent tiles with the same value), the game ends. <i>(https://en.wikipedia.org/wiki/2048_(video_game))</i>

======


### Intuition
The initial brute force recursive approach failed to provide significant results due to a lack of accurate heuristics. Judgeing which board state was better then another proved to be very difficult. From prior experience with gameplay I tried rating fittness based on:

1. Monotonically increaseing or decreasing values on board edges</br>
![Alt text](/Examples/mono.png?raw=true)

2. Emptiness of board</br>
![Alt text](/Examples/empty.png?raw=true)

3. Ability to merge tiles</br>
![Alt text](/Examples/merge.png?raw=true)

4. Kepping high values on edges</br>
![Alt text](/Examples/highsides.png?raw=true)

These failed to perform well even after weighing for tile score and total board score dynamically. Without the ability to rate fitness of board states accurately a more unsupervised appoached seemed in order. By randomly playing multiple games out to termination and tracking starting moves I was able to guess a "good" move.

======

### Algorithm
````
for i < CONGIF.DEPTH
  Clone current game state
  
  while cloned state game not over
    pick random move to play
    Record first move
  
  Record end score
  i++
  
Make starting move with highest average end score

````
======

### Results
100 games were played with different amount of random games played at each move. Random game depths included 50, 100, 500 and 1000.
On averege as more random games were played at each move the end score increased and seems to be maximised at a 8192 tile. A control game series of completly random moves resulted in no more then a 265 tile.

Game Depth 50:</br>
![Alt text](/Graphs/totalScoreFor50.png?raw=true)</br>
![Alt text](/Graphs/totalMovesFor50.png?raw=true)</br>
![Alt text](/Graphs/highTileFor50.png?raw=true)</br>

Game Depth 100:</br>
![Alt text](/Graphs/totalScoreFor100.png?raw=true)</br>
![Alt text](/Graphs/totalMovesFor100.png?raw=true)</br>
![Alt text](/Graphs/highTileFor100.png?raw=true)</br>


Game Depth 500:</br>
![Alt text](/Graphs/totalScoreFor500.png?raw=true)</br>
![Alt text](/Graphs/totalMovesFor500.png?raw=true)</br>
![Alt text](/Graphs/highTileFor500.png?raw=true)</br>

Game Depth 1000:</br>
![Alt text](/Graphs/totalScoreFor1000.png?raw=true)</br>
![Alt text](/Graphs/totalMovesFor1000.png?raw=true)</br>
![Alt text](/Graphs/highTileFor1000.png?raw=true)</br>

======

### Improvements
======

#### Credit and Sources
http://stackoverflow.com/questions/22342854/what-is-the-optimal-algorithm-for-the-game-2048</br>
https://github.com/gabrielecirulli/2048</br>
http://beej.us/blog/data/monte-carlo-method-game-ai/</br>
