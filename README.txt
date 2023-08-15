Project assignment from JetBrains Academy (www.hyperskill.org), Java Developer track.

The project stimulates a real-life Battleship game with two players. The players call out the coordinates to sink their
opponent's ships. 

The players are first asked to deploy their ships. Each player has a fleet of five ships of different lengths available 
to place on a 10 x 10 grid:

1) Aircraft Carrier (5 cells)
2) Battleship (4 cells)
3) Submarine (3 cells)
4) Cruiser (3 cells)
5) Destroyer (2 cells)

The grid is laid out with capital letter A - J as row and number 1 - 10 as columns. To deploy the ships, the players are
asked to input their coordinates. The format consists of a capital number followed by the number, e.g. A1. The call for
the start coordinates and the end coordinates are seperated by a space and the program parses the player response. The
program parses the coordinate and inform the player if the coordinates are valid. Three error conditions are recognized
and three different error messages are given, after which the player is given the opportunity to re-deploy the ship:

1) The start and end coordinates are neither in the same row nor the same column.
2) The coordinates do not fit the ship's length.
3) The ship is placed too closed to another ship. The ships should not occupy adjacent cells including diagonally.

After the first player deployed all their ships, the second player is invited to do the same on a separate grid. The grid
with ships is displayed to the player after each ship deployment with the ship's cells represented by a circle. Empty grids
are represented by the water symbol '~'.

The actual gameplay:

The players cannot see their opponents' ships (the ships are hidden by a "fog"). The program prompts the player to call out
a single coordinate (as letter-number combination) as their target turn by turn. The field is displayed on the console with 
the opponent's "foggy field" at the top and their own field with ships at the bottom. Invalid coordinate calls are checked 
and players are given the opportunity to correct their call.

If the player's target is occupied by an opponent's ship, the fog lifts and shows as a cross mark in the opponent's field, 
and the player is informed of the hit ("You hit a ship!"). If all cells of an opponent's ship are shot, the player is 
informed that their opponent's ship is sunken ("You sank a ship!"). Otherwise, the player is informed that they missed and 
the target cell is represented by the character 'M' instead.

After each target call, the grids reverses to the other player's view. The player is able to check the "hit marks" and "miss
marks" left by their opponent in their own grid. 

Once a player sinks all their opponent's ships, the player is congratulated with a winning message and declared the winner,
and the game ends.

August 15, 2023--description by E. Hsu (nahandove@gmail.com)
