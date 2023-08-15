package battleship.ship;

public enum ShipType {
    AIRCRAFT_CARRIER,
    BATTLESHIP,
    SUBMARINE,
    CRUISER,
    DESTROYER;

    public static String appendString(ShipType shipType) {

        switch (shipType) {
            case AIRCRAFT_CARRIER:
                return "Aircraft Carrier (5 cells):";
            case BATTLESHIP:
                return "Battleship (4 cells):";
            case SUBMARINE:
                return "Submarine (3 cells)";
            case CRUISER:
                return "Cruiser (3 cells):";
            case DESTROYER:
                return "Destroyer (2 cells):";
            }

            return null;
    }
}
