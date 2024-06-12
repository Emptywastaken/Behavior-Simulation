# To run go to Game.java

# Idea
# Structure
  ## Player
  ## Gamelogic
  #### The simulation's structure is centered around managing a dynamic ecosystem on a board where human players interact with each other and their environment. The primary components of this structure include the players, the board, the food spawn dynamics.

At the begginig we initiate: the board, the number of initial players, amount of food and volatility. These parameters determine the dynamics of the simulation. The players are instantiated and placed on the board, each with specific attributes like speed, vision, and type of behaviour. The game progresses in turns, with each turn consisting of multiple sub-turns where players decide and execute their movements, and determine following actions.

#### Each turn follows a structured sequence:

* Resetting Player States: Players' speeds are reset, and the game averages for speed and vision are updated.
* Spawning Food: Food is randomly placed on the board, ensuring that players have resources to compete for.
* Player Movements: Players pick and execute their moves across multiple sub-turns.
* Resolving Conflicts: If players land on the same cell with food, conflicts are resolved based on their social or greedy nature.
* Managing Hunger and Death: Players who fail to find food are marked for death, and this is finalized by removing them from the game.
* Reproduction: Players who meet the criteria reproduce.
  
This cyclical process continues, with the game state evolving based on player interactions, resource availability, and player attributes. During execution the set of statistics is tracked, it contains the number of social and greedy players and  an average speed and vision, providing insights into the simulation's dynamics. The structure of the simulation in a self-sustaining and adaptive system where players must navigate survival and competition.
  ## UI
# Observations
