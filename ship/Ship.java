package battleship.ship;

import battleship.BattleField;
import battleship.exceptions.ShipTooCloseException;
import battleship.exceptions.WrongLengthException;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    protected int startX;
    protected int startY;
    protected int length;
    protected boolean isDeployed;
    protected Direction direction;
    protected List<BattleField.Cell> cells;
    protected boolean isAlive;

    public Ship(int startX, int startY, int length) {
        this.startX = startX;
        this.startY = startY;
        this.length = length;
        isDeployed = true;
        isAlive = true;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void checkShipLife() {
        List<BattleField.Cell> cellsCopy = new ArrayList<>(cells);
        for (BattleField.Cell cell: this.getCells()) {
            if (cell.getValue() == 'X') {
                cellsCopy.remove(cell);
            }
            if (cellsCopy.size() == 0) {
                isAlive = false;
            }
        }
    }

    public void setCells(Direction direction) throws ShipTooCloseException {

        cells = new ArrayList<>();
        BattleField.Cell cell;

        switch(direction) {
            case RIGHT:
                for (int j = 0; j < length; j++) {
                    cell = new BattleField.Cell(startX, startY + j);
                    cells.add(cell);
                }
                break;
            case LEFT:
                for (int j = 0; j < length; j++) {
                    cell = new BattleField.Cell(startX, startY - j);
                    cells.add(cell);
                }
                break;
            case UP:
                for (int i = 0; i < length; i++) {
                    cell = new BattleField.Cell(startX - i, startY);
                    cells.add(cell);
                }
                break;
            case DOWN:
                for (int i = 0; i < length; i++) {
                    cell = new BattleField.Cell(startX + i, startY);
                    cells.add(cell);
                }
                break;
        }

        for (BattleField.Cell shipPart: cells) {
            for (BattleField.Cell neighbor: shipPart.getNeighbors()) {
                if (neighbor != null && neighbor.getValue() == 'O') {
                    throw new ShipTooCloseException();
                }
            }
        }

        for (BattleField.Cell shipPart: cells) {
            shipPart.setValue('O');
        }
    }

    public List<BattleField.Cell> getCells() {
        return cells;
    }

    public Direction getDirectionFromCoordinates(List<Integer> coordinates, ShipType shipType) throws WrongLengthException {
        int startXCoordinate = coordinates.get(0);
        int startYCoordinate = coordinates.get(1);
        int endXCoordinate = coordinates.get(2);
        int endYCoordinate = coordinates.get(3);

        if (Math.abs(endYCoordinate - startYCoordinate) != length - 1 && Math.abs(endXCoordinate - startXCoordinate) != length - 1) {
            throw new WrongLengthException(shipType);
        }

        if (endXCoordinate > startXCoordinate) {
            direction = Direction.RIGHT;
        } else if (endXCoordinate < startXCoordinate) {
            direction = Direction.LEFT;
        } else if (endYCoordinate < startYCoordinate) {
            direction = Direction.UP;
        } else if (endYCoordinate > startYCoordinate) {
            direction = Direction.DOWN;
        }

        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
