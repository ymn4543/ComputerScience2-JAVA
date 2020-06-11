/**
 * A class to represent a single parking officer.
 *
 * @author Youssef Naguib <ymn4543@rit.edu>
 */
import java.util.ArrayList;

public class ParkingOfficer {
    /** ParkingLot symbolizing the lot where the officer will patrol*/
    private ParkingLot lot;
    /** int symbolizing how long the officer will pause after each ticket */
    private static int PAUSE_TIME;
    /** ArrayList containing the tickets the officer has given out */
    private ArrayList<Ticket>tickets;


    //constructor
    /**
     * ParkingOfficer constructor
     * creates a new ParkingOfficer with an empty tickets ArrayList and a
     * PAUSE_TIME of 1000
     */
    public ParkingOfficer(){
        PAUSE_TIME = 1000;
        tickets = new ArrayList<Ticket>();
    }

    /**
     * returns the correct Fine that the officer will give to a vehicle in a spot.
     * @param vehicle is the vehicle
     * @param spot is the spot number
     * @return Fine.fine
     */
    public static Fine getFineVehicleSpot(Vehicle vehicle, ParkingSpot spot){
        if(vehicle.getPermit() == null){
            if(spot.getType()!= Permit.Type.GENERAL){
                if(spot.getType()== Permit.Type.HANDICAPPED){
                    return Fine.PARKING_HANDICAPPED;
                }
                if(spot.getType()== Permit.Type.RESERVED){
                    return Fine.PARKING_RESERVED;
                }
            }
        }
        else{
            if (spot.getType() == Permit.Type.HANDICAPPED) {
                if (!vehicle.getPermit().getType().equals(Permit.Type.HANDICAPPED) || !vehicle.hasPermit()) {
                    return Fine.PARKING_HANDICAPPED;
                }
            }
            if (spot.getType() == Permit.Type.RESERVED) {
                if (!vehicle.hasPermit() || vehicle.getPermit().getType().equals(Permit.Type.HANDICAPPED) ||
                        !vehicle.getPermit().getType().equals(Permit.Type.RESERVED)) {
                    return Fine.PARKING_RESERVED;
                }
            }
            if (spot.getType() == Permit.Type.GENERAL) {
                if (!vehicle.hasPermit()) {
                    return Fine.NO_PERMIT;
                }
            }
        }

        return Fine.NO_FINE;
    }

    /**
     * returns the list of tickets the officer has given out.
     * @return ArrayList tickets
     */
    public ArrayList<Ticket> getTickets(){
        return tickets;
    }
    /**
     * issues a ticket to a vehicle if it deserves a fine.
     * @param vehicle is the vehicle
     * @param  spot is the spot number
     * @param fine is the Fine type.
     */
    private void issueTicket(Vehicle vehicle, int spot, Fine fine){
        if(!getFineVehicleSpot(vehicle, lot.getSpot(spot)).equals(Fine.NO_FINE)){
            System.out.println("Issuing ticket to: {" + vehicle + "} in spot {"+ spot +"} for fine {"+ fine + "}.");
            Ticket t = new Ticket(vehicle.getPlate(),fine);
            vehicle.giveTicket(t);
            tickets.add(t);
        }
    }
    /**
     * The officer goes through every spot in the lot and gives tickets if
     * vehicles are illegally parked.
     */
    public void patrolLot(){
    int s = 0;
    int c = lot.getCapacity();
    while(s<c){
        if(lot.getSpot(s).getVehicle() != null){
            if(!getFineVehicleSpot(lot.getSpot(s).getVehicle(), lot.getSpot(s)).equals(Fine.NO_FINE)){
                Fine fine = getFineVehicleSpot(lot.getSpot(s).getVehicle(), lot.getSpot(s));
                issueTicket(lot.getSpot(s).getVehicle(),s,fine);
                s++;
            }
            s++;
        }
        s++;
    }
    }
    /**
     * sets the ParkingOfficer's lot.
     * @param lot is the lot the officer will patrol.
     */
    public void setParkingLot(ParkingLot lot){
        this.lot = lot;
    }
    /**
     * The main method runs a suite of test cases for the ParkingOfficer class.
     */
    public static void main(String args[]){
        // create some officers and lots
        ParkingOfficer officer1 = new ParkingOfficer();
        ParkingLot lot1 = new ParkingLot(10,10,10);
        ParkingOfficer officer2 = new ParkingOfficer();
        ParkingLot lot2 = new ParkingLot(20,20,20);
        ParkingOfficer officer3 = new ParkingOfficer();
        ParkingLot lot3 = new ParkingLot(30,30,30);
        // test setParkingLot()
        officer1.setParkingLot(lot1);
        officer2.setParkingLot(lot2);
        officer3.setParkingLot(lot3);
        System.out.println("officer1 in lot1? " + (officer1.lot == lot1 ? "OK" :
                "FAIL, got: " + officer1.lot));
        System.out.println("officer2 in lot2? " + (officer2.lot == lot2 ? "OK" :
                "FAIL, got: " + officer1.lot));
        System.out.println("officer3 in lot3? " + (officer3.lot == lot3 ? "OK" :
                "FAIL, got: " + officer1.lot));
        //create some cars, give them permits, and park them
        Vehicle car1 = new Vehicle(1);
        Vehicle car2 = new Vehicle(2);
        Vehicle car3 = new Vehicle(3);
        Vehicle car4 = new Vehicle(4);
        Vehicle car5 = new Vehicle(5);
        Vehicle car6 = new Vehicle(6);
        Permit p1 = new Permit(1, Permit.Type.HANDICAPPED);
        Permit p2 = new Permit(2, Permit.Type.RESERVED);
        car6.setPermit(p1);
        car4.setPermit(p2);
        lot1.parkVehicle(car1,2); //illegal
        lot1.parkVehicle(car2,23); // legal
        lot1.parkVehicle(car3,24); //legal
        lot1.parkVehicle(car4,16); //legal
        lot1.parkVehicle(car5,13); //illegal
        lot1.parkVehicle(car6,4); //legal
        //test getFineVehicleSpot
        System.out.println("car1 correctly fined? " + (getFineVehicleSpot(car1,lot1.getSpot(2)).equals(Fine.PARKING_HANDICAPPED) ? "OK" : "FAIL"));
        System.out.println("car2 correctly fined? " + (getFineVehicleSpot(car2,lot1.getSpot(23)).equals(Fine.NO_FINE) ? "OK" : "FAIL"));
        System.out.println("car3 correctly fined? " + (getFineVehicleSpot(car3,lot1.getSpot(24)).equals(Fine.NO_FINE) ? "OK" : "FAIL"));
        System.out.println("car4 correctly fined? " + (getFineVehicleSpot(car4,lot1.getSpot(16)).equals(Fine.NO_FINE) ? "OK" : "FAIL"));
        System.out.println("car5 correctly fined? " + (getFineVehicleSpot(car5,lot1.getSpot(13)).equals(Fine.PARKING_RESERVED) ? "OK" : "FAIL"));
        System.out.println("car6 correctly fined? " + (getFineVehicleSpot(car6,lot1.getSpot(4)).equals(Fine.NO_FINE) ? "OK" : "FAIL"));
        // test patrolLot by using getTickets()
        officer1.patrolLot();
        System.out.println("tickets are: " +officer1.getTickets());

    }
}
