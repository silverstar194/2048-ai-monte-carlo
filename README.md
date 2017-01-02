## AI to play 2048 based on Monte Carlo Algorithm
#### Gameplay
2048 is played on a 4×4 grid, with numbered tiles that slide smoothly when a player moves them using the four arrow keys Every turn, a new tile will randomly appear in an empty spot on the board with a value of either 2 or 4. (Probability of 90%: 2, 10%: 4) Tiles slide as far as possible in the chosen direction until they are stopped by either another tile or the edge of the grid. If two tiles of the same number collide while moving, they will merge into a tile with the total value of the two tiles that collided. <i>(https://en.wikipedia.org/wiki/2048_(video_game))</i>

#### Scoring
A scoreboard on the upper-right keeps track of the user's score. The user's score starts at zero, and is incremented whenever two tiles combine, by the value of the new tile. <i>(https://en.wikipedia.org/wiki/2048_(video_game))</i>

#### Game Over and Winning
The game is won when a tile with a value of 2048 appears on the board, hence the name of the game. After reaching the 2048 tile, players can continue to play (beyond the 2048 tile) to reach higher scores. When the player has no legal moves (there are no empty spaces and no adjacent tiles with the same value), the game ends. <i>(https://en.wikipedia.org/wiki/2048_(video_game))</i>

======

### AI Playing Game
[![AI Playing Game](http://img.youtube.com/vi/2Bu7sx4e85E/0.jpg)](https://youtu.be/2Bu7sx4e85E "")

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
</br>
</br>
On averege as more random games were played at each move the end score increased and seems to be maximised at a 8192 tile. A control game series of completly random moves resulted in no more then a 265 tile.

#### Differing Depth Results
Game Depth 50:</br>
42% of games where able to be won at a depth of 50</br>
![Alt text](/Graphs/totalScoreFor50.png?raw=true)</br>
![Alt text](/Graphs/totalMovesFor50.png?raw=true)</br>
![Alt text](/Graphs/highTileFor50.png?raw=true)</br>

Game Depth 100:</br>
61% of games where able to be won at a depth of 100</br>
![Alt text](/Graphs/totalScoreFor100.png?raw=true)</br>
![Alt text](/Graphs/totalMovesFor100.png?raw=true)</br>
![Alt text](/Graphs/highTileFor100.png?raw=true)</br>


Game Depth 500:</br>
94% of games where able to be won at a depth of 500</br>
![Alt text](/Graphs/totalScoreFor500.png?raw=true)</br>
![Alt text](/Graphs/totalMovesFor500.png?raw=true)</br>
![Alt text](/Graphs/highTileFor500.png?raw=true)</br>

Game Depth 1000:</br>
99% of games where able to be won at a depth of 1000</br>
![Alt text](/Graphs/totalScoreFor1000.png?raw=true)</br>
![Alt text](/Graphs/totalMovesFor1000.png?raw=true)</br>
![Alt text](/Graphs/highTileFor1000.png?raw=true)</br>

</br>
Plotting all the game depths together you can see the score, maximum tile and game length all increase as depth increases.</br>
![Alt text](/Graphs/highScoreForAll.png?raw=true)</br>
![Alt text](/Graphs/totalMovesForAll.png?raw=true)</br>
![Alt text](/Graphs/highTileForAll.png?raw=true)</br>


In summary as the depth increased the percentage of game won and score did also. The 8192 tile was also only achieved at the 500 and 1000 depths.

| Depth        	| Won           | Max score     |
| ------------- |:-------------:|:-------------:|
| 50	        | 42% 			| 47424			|
| 100     		| 61%      		| 76256			|
| 500 			| 94%   		| 103932		|
| 1000 			| 99%   		| 113072		|


#### Random Games within a Depth
Most intersting was the spread of the random games behind each move.</br>

The steady increase in score as well as the narrowing of score range can be seen graphing perdicted final score of each random games against percentage of game completed.</br>

![Alt text](/Graphs/scoreRangePerMovedepth1000.png?raw=true)</br>
</br>

Similarlly observing when the random games cross the next tile threshold shows that each games does so at nearly the same time.

![Alt text](/Graphs/highTilePerMovedepth1000.png?raw=true)</br>
</br>

Lastly looking at the predicted moves until game completion the correlation between the highest merged tiles and game length becomes clear. At each merged high tile predicted game length increases dramatically. The additional jumps I assume to be the merging of other high tiles.

![Alt text](/Graphs/moveRangePerMovedepth1000.png?raw=true)</br>
</br>
======

### Improvements
By inceasing the number of random games played the general trend was an increase in score as well as game length. This did seemed to be capped at a 8192 tile.</br>
Running 10 games with a depth of 10,000 seemed to comfirm this. None of the games where able to break 8192 and instead games became more consistent, resulting with 8/10 falling on 4096, 1/10 reaching 8192 and all at least acheiving 2048. Further increases beyond 10,000 while impractically slow to run without optimatzation are likely to yeld more results.
</br>

</br>
Another approach is likley needed to make drastic improvements. One approach would be to use the "moves until game over" as a fitness score for a supervised classification AI. Bootstapping with the best games as training data. I plan to investigate this later on.

======

#### Credit and Sources
http://stackoverflow.com/questions/22342854/what-is-the-optimal-algorithm-for-the-game-2048</br>
https://github.com/gabrielecirulli/2048</br>
http://beej.us/blog/data/monte-carlo-method-game-ai/</br>
https://en.wikipedia.org/wiki/2048_(video_game))
