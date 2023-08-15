package battleship.exceptions;

public class WrongLocationException extends Exception {
    public WrongLocationException() {
        System.out.println("\nError! Wrong ship location! Try again:\n");
    }
}
