package JAVA.OOPbyProf_Sirichai.HOURGLASS;
import java.util.Scanner;
public class MainHourGlass 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        HourGlass hg = new HourGlass();
        hg.sandA = 5000;
        hg.sandB = 0;
       
        System.out.println("\nTHIS IS A HOURGLASS. YOU CAN CAN ONLY ROTATE TO THE RIGHT");
        while (true) 
        {
            System.out.print("PLZ SELECT\n1.ROTATE 90 DEGREE\n2.ROTATE 180 DEGREE\n---> ");
            int select = scanner.nextInt();
            scanner.nextLine();
            switch (select) {
                case 1:
                    hg.rotate90();
                    break;
                case 2:
                    hg.rotate180();
                    break;
                default:
                    System.out.println("NAHHH~~, U WRONG, JUST 1 AND 2. PLZ SECECT AGAIN.");
                    break;
            }
            
        }
    }
}
