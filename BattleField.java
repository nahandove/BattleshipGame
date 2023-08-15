package battleship;

import java.util.ArrayList;
import java.util.List;

public class BattleField {

    static Cell[][] playerField = new Cell[11][11];
    static Cell[][] enemyField = new Cell[11][11];
    static List<Cell> shipParts = new ArrayList<>();
    static List<Cell> enemyShipParts = new ArrayList<>();

    public BattleField() {

    }

    public static void switchField() {
        Cell[][] temp = new Cell[11][11];
        for (int i = 0; i < playerField.length; i++) {
            for (int j = 0; j < playerField[0].length; j++) {
                temp[i][j] = playerField[i][j];
            }
        }

        for (int i = 0; i < enemyField.length; i++) {
            for (int j = 0; j < enemyField[0].length; j++) {
                playerField[i][j] = enemyField[i][j];
            }
        }

        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                enemyField[i][j] = temp[i][j];
            }
        }

        List<Cell> tempList = new ArrayList(shipParts);
        shipParts.clear();
        shipParts.addAll(enemyShipParts);
        enemyShipParts.clear();
        enemyShipParts.addAll(tempList);
    }

    public void drawField() {

        System.out.print("  ");

        for (int j = 1; j < playerField[0].length - 1; j++) {
            playerField[0][j] = new Cell(0, j);
            playerField[0][j].setValue((char) (j + 48));
            System.out.print(playerField[0][j].value + " ");
        }

        System.out.print("10");
        System.out.println();

        for (int i = 1; i < playerField.length; i++) {
            playerField[i][0] = new Cell(i, 0);
            playerField[i][0].setValue((char) (i + 64));
            System.out.print(playerField[i][0].value + " ");
            for (int j = 1; j < playerField[i].length; j++) {
                if (playerField[i][j] == null) {
                    playerField[i][j] = new Cell(i, j);
                }
                if (playerField[i][j].isMissed()) {
                    playerField[i][j].setValue('M');
                }
                for (Cell shipPart : shipParts) {
                    if (playerField[shipPart.getX()][shipPart.getY()].isHit()) {
                        playerField[shipPart.getX()][shipPart.getY()].setValue('X');
                    } else {
                        playerField[shipPart.getX()][shipPart.getY()].setValue('O');
                    }
                }
                System.out.print(playerField[i][j].getValue());
                System.out.print(' ');
            }
            System.out.println();
        }
        System.out.println();
    }

    public void drawFoggyField() {
        System.out.print("  ");

        for (int j = 1; j < enemyField[0].length - 1; j++) {
            System.out.print(enemyField[0][j].value + " ");
        }

        System.out.print("10");
        System.out.println();

        for (int i = 1; i < enemyField.length; i++) {
            System.out.print(enemyField[i][0].value + " ");
            for (int j = 1; j < enemyField[i].length; j++) {
                enemyField[i][j].setFoggyValue('~');
                if (enemyField[i][j].isMissed()) {
                    enemyField[i][j].setFoggyValue('M');
                }
                for (Cell shipPart : enemyShipParts) {
                    if (enemyField[shipPart.getX()][shipPart.getY()].isHit()) {
                        enemyField[shipPart.getX()][shipPart.getY()].setFoggyValue('X');
                    } else {
                        enemyField[shipPart.getX()][shipPart.getY()].setFoggyValue('~');
                    }
                }
                System.out.print(enemyField[i][j].getFoggyValue());
                System.out.print(' ');
            }
            System.out.println();
        }
    }

    public static class Cell {
        private int x;
        private int y;
        char value;
        private char foggyValue;
        private boolean isHit;
        private boolean isMissed;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
            value = '~';
        }

        public char getValue() {
            return value;
        }

        public void setValue(char value) {
            this.value = value;
        }

        public char getFoggyValue() {
            return foggyValue;
        }

        public void setFoggyValue(char foggyValue) {
            this.foggyValue = foggyValue;
        }

        public int getX() {
            return x;
        }

        public boolean isHit() { return isHit;
        }

        public void setHit(boolean isHit) {
            this.isHit = isHit;
        }

        public boolean isMissed() {
            return isMissed;
        }

        public void setMissed(boolean isMissed) {
            this.isMissed = isMissed;
        }

        public int getY() {
            return y;
        }

        public List<Cell> getNeighbors() {

            List<Cell> neighbors = new ArrayList<>();
            int y = getY();
            int x = getX();

            neighbors.add(playerField[x][y]);

            if (y < playerField.length - 1) {
                neighbors.add(playerField[x][y + 1]);
            }

            if (y > 0) {
                neighbors.add(playerField[x][y - 1]);
            }

            if (x < playerField[0].length - 1) {
                neighbors.add(playerField[x + 1][y]);
            }

            if (x > 0) {
                neighbors.add(playerField[x - 1][y]);
            }

            if (y < playerField.length - 1 && x > 0) {
                neighbors.add(playerField[x - 1][y + 1]);
            }

            if (y < playerField.length - 1 && x < playerField[0].length - 1) {
                neighbors.add(playerField[x + 1][y + 1]);
            }

            if (y > 0 && x < playerField[0].length - 1) {
                neighbors.add(playerField[x + 1][y - 1]);
            }

            if (y > 0 && x > 0) {
                neighbors.add(playerField[x - 1][y - 1]);
            }

            return neighbors;
        }
    }
}



