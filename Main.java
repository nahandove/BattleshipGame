package battleship;

import battleship.ship.Ship;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static Commander commander1 = new Commander();
    static Commander commander2 = new Commander();

    static BattleField battleField1 = new BattleField();
    static BattleField battleField2 = new BattleField();

    static List<Ship> commander1Ships = new ArrayList<>();
    static List<Ship> commander2Ships = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        System.out.println("Player 1, place your ships on the game field\n");
        battleField1 = commander1.startBoard(battleField1, commander1Ships);
        System.out.println("Press Enter and pass the move to another player");

        if (commander1.getResponse().equals("")) {
            BattleField.switchField();
            System.out.println("Player 2, place your ships on the game field\n");
            battleField2 = commander2.startBoard(battleField2, commander2Ships);
            System.out.println("Press Enter and pass the move to another player");
            if (commander2.getResponse().equals("")) {
                Main game = new Main();
                game.startGame();
            }
        }
    }

    public void startGame() throws Exception {

        while(!commander1.isWinner && !commander2.isWinner) {
            BattleField.switchField();
            drawFields(battleField1, battleField2);
            System.out.println("Player1, it's your turn:\n");
            commander1.shoot(battleField2, commander2Ships);
            if (commander1.isWinner) {
                break;
            }
            System.out.println("Press Enter and pass the move to another player");
            if (commander1.getResponse().equals("")) {
                BattleField.switchField();
                drawFields(battleField2, battleField1);
                System.out.println("Player 2, it's your turn:");
                commander2.shoot(battleField1, commander1Ships);
                if (commander2.isWinner) {
                    break;
                }
                System.out.println("Press Enter and pass the move to another player");
                if (commander2.getResponse().equals("")) {

                }
            }
        }

        if (commander1.isWinner) {
            drawFields(battleField1, battleField2);
        } else {
            drawFields(battleField2, battleField1);
        }
        System.out.println("You sank the last ship. You won. Congratulations!");
    }

    public void drawFields(BattleField battleField, BattleField enemyField) {
        enemyField.drawFoggyField();
        System.out.println("--------------------");
        battleField.drawField();
    }
}
