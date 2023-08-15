package battleship.exceptions;

import battleship.ship.ShipType;

public class WrongLengthException extends Exception {
    public WrongLengthException(ShipType shipType) {
        String[] appendedStringsParts = ShipType.appendString(shipType).split(" ");
        String shipName = appendedStringsParts[0];

        System.out.println("\nError! Wrong length of the " + shipName + "! Try again:\n");
    }
}
