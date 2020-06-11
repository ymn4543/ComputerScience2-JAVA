public class CarTest {
    public static void main(String args[]){
        car car1, car2;
        car1 = new car("honda",60);
        car2 = new car("ford", 35);


        //change the speed of car 1
        car1.setSpeed(45);
        //change maker of car 2
        car2.setMaker("Toyota");

        System.out.print(car1.toString());
    }
}
