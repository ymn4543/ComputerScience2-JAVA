/**
 * A class to represent a single parking lot.
 *
 * @author Youssef Naguib <ymn4543@rit.edu>
 */
import java.util.ArrayList;

public class ParkingLot {
    //fields
    /** int symbolizing ParkingLot's capacity (how many spots in the lot) */
    private int capacity;
    /** int symbolizing ParkingLot's amount of parking spots of type GENERAL */
    private int generalSpots;
    /** int symbolizing ParkingLot's amount of parking spots of type HANDICAPPED */
    private int handicappedSpots;
    /** String symbolizing an Illegal ParkingSpot in ParkingLot */
    public static int ILLEGAL_SPOT;
    /** ArrayList containing ParkingSpots in the ParkingLot */
    private ArrayList<ParkingSpot>lot;
    /** int symbolizing amount of vehicles parked in the ParkingLot */
    private int parkedVehicles;
    /** int symbolizing ParkingLot's amount of parking spots of type RESERVED */
    private int reservedSpots;
    /** int symbolizing how many ParkingSpots one line in of the ParkingLot can hold */
    private static int SPOTS_PER_LINE;

    //constructors
    /**
     * ParkingLot constructor
     * creates a new ParkingLot and initializes spots.
     * @param handicappedSpots is the number of handicapped spots in lot.
     * @param reservedSpots is the number of reserved spots in lot.
     * @param generalSpots is the number of general spots in lot.
     */
    public ParkingLot(int handicappedSpots, int reservedSpots, int generalSpots){
        this.handicappedSpots = handicappedSpots;
        this.reservedSpots = reservedSpots;
        this.generalSpots = generalSpots;
        capacity = handicappedSpots+reservedSpots+generalSpots;
        lot = new ArrayList<ParkingSpot>();
        parkedVehicles = 0;
        SPOTS_PER_LINE = 10;
        initializeSpots();
    }

    //getters
    /**
     * returns the capacity of the parking lot.
     * @return capacity
     */
    public int getCapacity(){
        return handicappedSpots + reservedSpots + generalSpots;
    }
    /**
     * returns the number of parked vehicles in the lot.
     * @return parkedVehicles
     */
    public int getNumParkedVehicles(){
        return parkedVehicles;
    }
    /**
     * returns the spot number of the spot in the parkingLot.
     * @return ParkingSpot's spot number
     */
   public ParkingSpot getSpot(int spot) {
       return lot.get(spot);
   }
    /**
     * initializes parking spots in the lot and adds them to the ArrayList lot
     */
    private void initializeSpots(){
        int i = 1;
        while(i <=  handicappedSpots){
            lot.add(new ParkingSpot(i-1, Permit.Type.HANDICAPPED));
            i++;
        }
        while(i <=  handicappedSpots+reservedSpots) {
            lot.add(new ParkingSpot(i-1, Permit.Type.RESERVED));
            i++;
        }
        while(i <=  handicappedSpots+ generalSpots +reservedSpots){
            lot.add(new ParkingSpot(i-1, Permit.Type.GENERAL));
            i++;
        }
    }
    /**
     * determines if a spot is vacant or not.
     * @return boolean
     */
    public boolean isSpotVacant(int spot){
        return lot.get(spot).getVehicle() == null;
    }
    /**
     * determines if a spot is valid or not.
     * @return boolean
     */
    public boolean isSpotValid(int spot){
        return spot <= getCapacity();
    }
    /**
     * parks a vehicle in a spot.
     * @param vehicle is the vehicle being parked.
     * @param spot is the spot where the vehicle will be parked.
     * @return boolean
     */
    public boolean parkVehicle(Vehicle vehicle, int spot){
        if(isSpotVacant(spot)){
            lot.get(spot).occupySpot(vehicle);
            parkedVehicles +=1;
            return true;
        }
        return false;
    }
    /**
     * removes a vehicle from a spot.
     * @param vehicle is the vehicle being parked.
     * @return spot where vehicle was parked
     */
    public int removeVehicle(Vehicle vehicle){
        if(vehicle.isParked()) {
            int plate = vehicle.getPlate();
            for(int x = 0; x < lot.size();) {
                if (!isSpotVacant(x)) {
                Vehicle c = lot.get(x).getVehicle();
                if (c.getPlate() == plate) {
                    vehicle.setParked(false);
                    lot.get(x).vacateSpot();
                    return lot.get(x).getSpot();
                }
            }
                x++;
            }
        }
        return ILLEGAL_SPOT;
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
     * Will print (lot.SPOTS_PER_LINE) spots per line.
     */
    public String toString(){
    int p = 0;
    int i = 0;
    int vacantSpots = 0;
    int capacity = this.capacity;
    String s = "";
        while(i < capacity ){
        if(isSpotVacant(lot.get(i).getSpot())){
            vacantSpots+=1;
        }
        if(p==SPOTS_PER_LINE) {
            s = s.concat("\n" + lot.get(i) + " ");
            p = 1;
            i++;
        }
        else{
            s = s.concat(lot.get(i) + " ");
            p++;
            i++;
            }
        }
    s = s.concat("\n" + "Vacant Spots: "+ vacantSpots);
    return s;
    }

    /**
     * The main method runs a suite of test cases for the ParkingLot class.
     */
    public static void main(String args[]){
        //create a lot and some cars , this tests the constructors and the initializeSpots() method
    ParkingLot lot1 = new ParkingLot(45,15,30);
    Vehicle car1 = new Vehicle(1);
    Vehicle car2 = new Vehicle(2);
    Vehicle car3 = new Vehicle(3);
    Vehicle car4 = new Vehicle(4);
    Vehicle car5 = new Vehicle(5);
    Vehicle car6 = new Vehicle(6);
    //test getSpot() and parkVehicle()
    lot1.parkVehicle(car1,1);
    lot1.parkVehicle(car2,4);
    lot1.parkVehicle(car3,48);
    lot1.parkVehicle(car4,65);
    lot1.parkVehicle(car5,27);
    lot1.parkVehicle(car6,55);
    System.out.println("car1 is parked at spot1: " + ((lot1.getSpot(1).getVehicle()).equals(car1)? "TRUE": "FALSE" ));
    System.out.println("car2 is parked at spot4: " + ((lot1.getSpot(4).getVehicle()).equals(car2)? "TRUE": "FALSE" ));
    System.out.println("car3 is parked at spot48: " + ((lot1.getSpot(48).getVehicle()).equals(car3)? "TRUE": "FALSE" ));
    System.out.println("car4 is parked at spot65: " + ((lot1.getSpot(65).getVehicle()).equals(car4)? "TRUE": "FALSE" ));
    System.out.println("car5 is parked at spot27: " + ((lot1.getSpot(27).getVehicle()).equals(car5)? "TRUE": "FALSE" ));
    System.out.println("car6 is parked at spot55: " + ((lot1.getSpot(55).getVehicle()).equals(car6)? "TRUE": "FALSE" ));
    //test getCapacity
    System.out.println("lot1 capacity is 90: " + (lot1.getCapacity()==90? "TRUE" : "FALSE"));
    //test removeVehicle()
    lot1.removeVehicle(car4);
    lot1.removeVehicle(car6);
    System.out.println("car4 has been removed from the lot? " + (lot1.getSpot(65).getVehicle()==null ?"TRUE" :"FALSE"));
    System.out.println("car6 has been removed from the lot? " + (lot1.getSpot(55).getVehicle()==null ?"TRUE" :"FALSE"));
    //test isSpotVacant()
    System.out.println("Is spot65 vacant? " + (lot1.isSpotVacant(65) ? "TRUE":"FALSE"));
    System.out.println("Is spot55 vacant? " + (lot1.isSpotVacant(55) ? "TRUE":"FALSE"));
    //test isSpotValid()
    System.out.println("Is spot97 valid? " + (!lot1.isSpotValid(97)? "TRUE":"FALSE"));
    System.out.println("Is spot97 valid? " + (!lot1.isSpotValid(94)? "TRUE":"FALSE"));
    System.out.println("Is spot97 valid? " + (lot1.isSpotValid(76)? "TRUE":"FALSE"));
    }



}
