package JAVA.OOPbyProf_Sirichai.Microwave;

public class Main {
    public static void main(String[] args) {
        Microwave m = new Microwave();
        Food f = new Food("Chicky", 800, 4);
        Food g = new Food("GUJU", 288, 3);
        
        //TEST CASE 1
        System.out.println("------------------DETAIL FOOD--------------------");
        System.out.println("FOOD IS "+f.getNameFood()+" WATT SHOULD BE IS "+f.getWatt()+"watt TIME SHOULD BE IS "+f.getTime()+"s.");
        m.open();
        m.put(f);
        m.setWattLevel(4);
        m.setTime(4);
        m.close();
        m.pressStart();
        System.out.println("Food is welldone ? : " + f.isWellDone());
        System.out.println("Food is burn ? : " + f.isBurn());
        System.out.println("=".repeat(50));

        System.out.println(g.getNameFood());
    }
    
}
