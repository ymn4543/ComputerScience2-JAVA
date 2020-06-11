/**
 * A class to represent a single parking spot.
 *
 * @author Youssef Naguib <ymn4543@rit.edu>
 */
public class ParkingSpot {
    /** String symbolizing if ParkingSpot is occupied. */
    private static String OCCUPIED_STR;
    /** int symbolizing ParkingSpot's spot. */
    private int spot;
    /** Permit.Type symbolizing ParkingSpot's permit type. */
    private Permit.Type type;
    /** Vehicle symbolizing vehicle occupying spot (null if it is empty) */
    private Vehicle vehicle;


    //constructor
    /**
     * creates a new ParkingSpot.
     * @param spot is the spot number.
     * @param type is the permit type.
     */
    public ParkingSpot(int spot, Permit.Type type){
        this.spot = spot;
        this.type = type;
    }

    //getters
    /**
     * returns the int spot number of the parking spot.
     * @return spot
     */
    public int getSpot(){
        return spot;
    }
    /**
     * returns the permit.type of the parking spot.
     * @return type
     */
    public Permit.Type getType(){
        return type;
    }
    /**
     * returns the vehicle parked in the parking spot (null if none).
     * @return vehicle
     */
    public Vehicle getVehicle(){
        return vehicle;
    }
    //setters
    /**
     * parks a vehicle in a spot.
     */
    public void occupySpot(Vehicle vehicle){
        if(this.vehicle == null) {
            this.vehicle = vehicle;
            vehicle.setParked(true);
        }
        }
    /**
     * vacates a parking spot of any vehicles.
     * @return type
     */
    public void vacateSpot(){
        if(vehicle!=null) {
            vehicle.setParked(false);
            vehicle = null;
        }
    }
    /**
     * compares two parking spots and prints if they match.
     * @param spotVar the name you are giving to the lot you are comparing.
     * @param s the ParkingSpot you are comparing spotVar to
     * @param spot is the spot you are comparing with s' spot
     * @param type is the type you are comparing to s' type
     * @param vehicle is the vehicle you are comparing to s' vehicle
     */
    private static void verifySpot(String spotVar, ParkingSpot s, int spot, Permit.Type type, Vehicle vehicle) {
        System.out.println("Correct Spot? " + (s.spot == spot ? "Spots match" :
                "Not a Match! " + spotVar + ":" + spot + " ,actual spot:" + s.spot));
        System.out.println("Correct Type? " + (s.type == type ? "Types match" :
                "Not a Match! " + spotVar + ":" + type + " ,actual type:" + s.type));
        if (vehicle == null) {
            System.out.println("Correct Vehicle? " + (s.vehicle == null ? "Vehicles match" :
                    "Not a Match! "));
        }
        if (vehicle != null) {
            System.out.println("Correct Vehicle? " + (s.vehicle.equals(vehicle) ? "Vehicles match" :
                    "Not a Match! "));
        }
        if (vehicle != null) {
            System.out.println("Vehicle Parked? " + (s.vehicle.isParked() ? "Vehicle is parked" :
                    "Vehicle is not parked. "));
        }
        if (vehicle == null) {
            System.out.println("Vehicle Parked? Vehicle is not parked.");
        }
        System.out.println("Please visually verify that the parking spots match");
        System.out.println(s.toString());
        if (vehicle != null) {
            System.out.println(String.format("%02d", spot) + ":" + (type != null ? "*" : ""));
        } else {
            System.out.println(String.format("%02d", spot) + ":" + (type == Permit.Type.HANDICAPPED ? "H" : "" +
                    (type == Permit.Type.GENERAL ? "G" : "" + (type == Permit.Type.RESERVED ? "R" : ""))));
        }
    }

    @Override
    /**
     * Overrides the Object class' toString() method.
     * prints parking lots in following format: ##:? where ## is the
     * spot number and ? is the spot's type.
     * H --> HANDICAPPED
     * G --> GENERAL
     * R --> RESERVED
     * * --> OCCUPIED
     */
    public String toString(){
        if(vehicle != null){
            return String.format("%02d", spot) + ":" + (type != null ? "*" : "");
        }
        return String.format("%02d", spot) + ":" + (type == Permit.Type.HANDICAPPED ? "H" : "" +
                (type == Permit.Type.GENERAL ? "G" : "" + (type == Permit.Type.RESERVED ? "R" : ""))) ;
    }

    /**
     * The main method runs a suite of test cases for the ParkingSpot class.
     */
    public static void main(String args[]){
        //create spots and cars
        ParkingSpot spot1 = new ParkingSpot( 1, Permit.Type.HANDICAPPED );
        verifySpot("spot1", spot1, 1, Permit.Type.HANDICAPPED,null );
        Vehicle car1 = new Vehicle(34762);
        Vehicle car2 = new Vehicle(45323);
        //test occupySpot()
        spot1.occupySpot(car1);
        verifySpot("spot1", spot1,1, Permit.Type.HANDICAPPED,car2);
        spot1.vacateSpot();
        verifySpot("spot1", spot1,1, Permit.Type.HANDICAPPED,null);
        Vehicle car3 = new Vehicle(75653);
        Vehicle car4 = new Vehicle(88887);
        Vehicle car5 = new Vehicle(94953);
        Vehicle car6 = new Vehicle(23453);
        Vehicle car7 = new Vehicle(56444);
        ParkingSpot spot24 = new ParkingSpot( 24, Permit.Type.GENERAL );
        ParkingSpot spot99 = new ParkingSpot( 99, Permit.Type.RESERVED );
        ParkingSpot spot45 = new ParkingSpot( 45, Permit.Type.GENERAL );
        ParkingSpot spot56 = new ParkingSpot( 56, Permit.Type.HANDICAPPED );
        ParkingSpot spot22 = new ParkingSpot( 22, Permit.Type.GENERAL );
        ParkingSpot spot86 = new ParkingSpot( 86, Permit.Type.GENERAL );
        ParkingSpot spot34 = new ParkingSpot( 34, Permit.Type.RESERVED );
        ParkingSpot spot65 = new ParkingSpot( 65, Permit.Type.GENERAL );
        ParkingSpot spot43 = new ParkingSpot( 43, Permit.Type.HANDICAPPED );
        spot1.occupySpot(car2);
        spot24.occupySpot(car4);
        spot56.occupySpot(car6);
        spot45.occupySpot(car3);
        verifySpot("spot1", spot1,1, Permit.Type.HANDICAPPED,car2);
        verifySpot("spot24", spot24,24, Permit.Type.GENERAL,car4);
        verifySpot("spot56", spot56,56, Permit.Type.HANDICAPPED,car6);
        verifySpot("spot45", spot45,45, Permit.Type.GENERAL,car3);
        System.out.println("spot 86 has correct spot number?" + (spot86.getSpot()==86? " OK" : " FAIL"));
        System.out.println("spot 99 has correct spot number?" + (spot99.getSpot()==99? " OK" : " FAIL"));
        System.out.println("spot 22 has correct spot number?" + (spot22.getSpot()==22? " OK" : " FAIL"));
        System.out.println("The vehicle in spot86 is: " + spot86.getVehicle());
        System.out.println("The vehicle in spot56 is: " + spot56.getVehicle());
        System.out.println("The vehicle in spot45 is: " + spot45.getVehicle());
        System.out.println("The spot type of spot34 is: " + spot34.getType());
        System.out.println("The spot type of spot65 is: " + spot65.getType());
        System.out.println("The spot type of spot45 is: " + spot43.getType());
        spot56.vacateSpot();
        verifySpot("spot56", spot56,56, Permit.Type.HANDICAPPED,null);
        spot56.occupySpot(car7);
        verifySpot("spot56", spot56,56, Permit.Type.HANDICAPPED, car7);
        verifySpot("spot34", spot34,34, Permit.Type.RESERVED, null);
        spot24.vacateSpot();
        spot99.vacateSpot();
        spot45.vacateSpot();
        spot56.vacateSpot();
        spot22.vacateSpot();
        spot86.vacateSpot();
        spot34.vacateSpot();
        spot65.vacateSpot();
        spot43.vacateSpot();

    }
}
