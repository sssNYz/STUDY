package JAVA.OOPbyProf_Sirichai.Glass;

import java.util.Scanner;

public class GlassMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Glass g1 = new Glass(); // g1 IS GLASS 1
        Glass g2 = new Glass(); // g2 IS GLASS 2
        // SET CAPACITY OF GLASS
        g1.capacity = 100;
        g2.capacity = 200;

        // TEST CASE1 : ADD WATER 25 ml. TO GLASS 1.
        // THE ANSWER SHOULD BE : -- GLASS 1 HAVE 25 ml.-- GLASS 2 HAVE 0 ml. --
        g1.addWater(25);
        System.out.println("TEST CASE 1\nGLASS 1 HAVE WATER " + g1.water + " ml.");
        System.out.println("GLASS 2 HAVE WATER " + g2.water + " ml.\n");

        // TEAE CASE2 : ADD FULL WATER TO GLASS 2.
        // THE ANSWER SHOULD BE : -- GLASS 1 HAVE 25 ml.(NO CHANG) -- GLASS 2 HAVE 200
        // ml.(EQUAL CAPACITY) --
        g2.addWater("FULL");
        System.out.println("TEST CASE 2\nGLASS 1 HAVE WATER " + g1.water + " ml.");
        System.out.println("GLASS 2 HAVE WATER " + g2.water + " ml.\n");

        // TEST CASE3 : POUR ALL WATER FROM GLASS 1 TO GROUND.
        // ANSWER SHOULD BE : -- GLASS 1 HAVE 0 ml. -- GLASS 2 HAVE 200 ml.(NO CHANG) --
        g1.poourWater("ALL");
        System.out.println("TEST CASE 3\nGLASS 1 HAVE WATER " + g1.water + " ml.");
        System.out.println("GLASS 2 HAVE WATER " + g2.water + " ml.\n");

        // TEST CASE4 : POUR WATER 10 ml. FROM GLASS 2 TO GLASS 1.
        // ANSWER SHOULD BE : -- GLASS 1 HAVE 10 ml. -- GLASS 2 HAVE 190 ml.--
        g2.pourWaterToAnotherGlass(g1, 10);
        System.out.println("TEST CASE 4\nGLASS 1 HAVE WATER " + g1.water + " ml.");
        System.out.println("GLASS 2 HAVE WATER " + g2.water + " ml.\n");

        // TEST CASE5 : POUR ALL WATER FROM GLASS 2 TO GLASS 1.
        // ANSWER SHOULD BE : -- GLASS 1 HAVE 100 ml.(OVERFLOW) -- GLASS 2 HAVE 0 ml.--
        g2.pourWaterToAnotherGlass(g1, "ALL");
        System.out.println("TEST CASE 5\nGLASS 1 HAVE WATER " + g1.water + " ml.");
        System.out.println("GLASS 2 HAVE WATER " + g2.water + " ml.\n");

        // TEST CASE6 : POUR WATER 110 ml. FROM GLASS 1 TO GLASS 2.
        // ANSWER SHOULD BE : "NOT ENOUGH WATER FOR POUR! "-- GLASS 1 HAVE 100 ml.--GLASS 2 HAVE 0 ml.--
        System.out.println("TEST CASE 6");
        g1.pourWaterToAnotherGlass(g2, 110);
        System.out.println("GLASS 1 HAVE WATER " + g1.water + " ml.");
        System.out.println("GLASS 2 HAVE WATER " + g2.water + " ml.");
    }
}
