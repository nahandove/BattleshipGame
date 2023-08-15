package battleship.exceptions;

import battleship.Commander;

public class WrongCoordinatesException extends Exception {
    public WrongCoordinatesException() {
        System.out.println("\nError! You entered the wrong coordinates! Try again:\n");
    }
}
