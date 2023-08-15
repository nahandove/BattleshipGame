package battleship;

import battleship.exceptions.ShipTooCloseException;
import battleship.exceptions.WrongCoordinatesException;
import battleship.exceptions.WrongLengthException;
import battleship.exceptions.WrongLocationException;
import battleship.ship.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Commander {
    private Direction direction;
    private Ship ship;
    boolean isWinner;
    static Scanner scanner = new Scanner(System.in);

    public BattleField startBoard(BattleField battleField, List<Ship> ships) throws Exception {
        battleField.drawField();
        for (int i = 0; i < ShipType.values().length; i++) {
            System.out.println("Enter the coordinates of the " + ShipType.appendString(ShipType.values()[i]) + "\n");
            ship = deploy(ShipType.values()[i]);
            ships.add(ship);
            System.out.println();
            battleField.shipParts.addAll(ship.getCells());
            battleField.drawField();
        }
        return battleField;
    }

    public Ship deploy(ShipType shipType) throws Exception {
        try {
        String response = scanner.nextLine();
        List<Integer> coordinates = parseResponse(response);

        switch(shipType) {
            case AIRCRAFT_CARRIER:
                ship = new AircraftCarrier(coordinates.get(1), coordinates.get(0));
                break;
            case BATTLESHIP:
                ship = new Battleship(coordinates.get(1), coordinates.get(0));
                break;
            case SUBMARINE:
                ship = new Submarine(coordinates.get(1), coordinates.get(0));
                break;
            case CRUISER:
                ship = new Cruiser(coordinates.get(1), coordinates.get(0));
                break;
            case DESTROYER:
                ship = new Destroyer(coordinates.get(1), coordinates.get(0));
                break;
        }

        direction = ship.getDirectionFromCoordinates(coordinates, shipType);
        ship.setCells(direction);

        } catch (WrongLocationException | WrongLengthException | ShipTooCloseException e) {
            deploy(shipType);
        }
        return ship;
    }

    public String getResponse() {
        return scanner.nextLine();
    }

    public List<Integer> parseResponse(String response) throws WrongLocationException {
        String[] positions = response.split(" ");
        String startPosition = positions[0];
        String endPosition = positions[1];

        List<Integer> coordinates = new ArrayList<>();

        int startX = Integer.parseInt(startPosition.substring(1));
        int startY = (startPosition.charAt(0)) - 64;

        int endX = Integer.parseInt(endPosition.substring(1));
        int endY = (endPosition.charAt(0)) - 64;

        if (checkCoordinates(startX, startY, endX, endY)) {
            coordinates.add(startX);
            coordinates.add(startY);
            coordinates.add(endX);
            coordinates.add(endY);

            return coordinates;
        }
        return null;
    }

    public boolean checkCoordinates(int startX, int startY, int endX, int endY) throws WrongLocationException {
        try {
            if (startX != endX && startY != endY) {
                throw new WrongLocationException();
            }
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new WrongLocationException();
        }
    }

    public void shoot(BattleField enemy, List<Ship> enemyShips) throws Exception {
        String targetInput = getResponse();
        boolean enemyIsHit = false;

        try {
            int yCoordinate = targetInput.charAt(0) - 64;
            int xCoordinate = Integer.parseInt(targetInput.substring(1));

            if (xCoordinate < 1 || xCoordinate > 10 || yCoordinate < 1 || yCoordinate > 10) {
                throw new WrongCoordinatesException();
            }
            System.out.println();
            BattleField.Cell target = enemy.enemyField[yCoordinate][xCoordinate];

            int enemyShipCount = 0;

            for (Ship enemyShip: enemyShips) {
                if (enemyShip.isAlive()) {
                    enemyShipCount++;
                }
            }

            for (Ship enemyShip : enemyShips) {
                for (BattleField.Cell shipPart : enemyShip.getCells()) {
                    if (shipPart.getX() == target.getX() && shipPart.getY() == target.getY()) {
                        enemyIsHit = true;
                        target.setHit(true);
                        shipPart.setValue('X');
                        enemyShip.checkShipLife();
                        if (!enemyShip.isAlive()) {
                            if (enemyShipCount == 1) {
                                isWinner = true;
                                continue;
                            }
                            System.out.println("You sank a ship!");
                        } else {
                            System.out.println("You hit a ship!");
                        }
                    }
                }
            }

            if (!enemyIsHit) {
                System.out.println("You missed!");
                target.setMissed(true);
            }

            } catch (WrongCoordinatesException e) {
                shoot(enemy, enemyShips);
            }
    }
}
