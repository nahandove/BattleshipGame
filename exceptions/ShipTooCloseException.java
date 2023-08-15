package battleship.exceptions;

public class ShipTooCloseException extends Exception {
    public ShipTooCloseException() {
        System.out.println("\nError! You placed it too close to another one. Try again:\n");
    }
}
