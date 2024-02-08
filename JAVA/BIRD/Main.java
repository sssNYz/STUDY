package JAVA.OOPbyProf_Sirichai.BIRD_NEW_GENERATION;

public class Main {
    public static void main(String[] args) {
        /*
         * Argrument is (str:name, int:age, float:weight of bird, char: 'M'and'F' (M =
         * male : F = female))
         */
        BirdNewGeneration b1 = new BirdNewGeneration("SUN", 7, 10, 'M');
        BirdNewGeneration b2 = new BirdNewGeneration("DEER", 5, 7, 'F');
        BirdNewGeneration b3 = new BirdNewGeneration("JIMMY", 10, 15, 'M');

        System.out.println(
                "\n======TEST CASE 1 : TEST TALK WITH WORD======\n(THE RESULT SHOULD BE 'HI! HAVE A GOOD DAY')");
        b1.talk("HI! HAVE A GOOD DAY");
        System.out.println(
                "\n======TEST CASE 2 : TEST TALK WITH OUT WORD======\n      (THE RESULT SHOULD BE 5 YEARS OLD)");
        b2.talk();
        System.out.println(
                "\n======TEST CASE 3 : TEST FEED   ======\n(THE RESULT SHOULD BE THE BIRD THANK YOU FOR FEED)");
        b3.feed(10);
        System.out.println(
                "\n======TEST CASE 4 : TEST POOP   ======\n(THE RESULT SHOULD BE STOMACH WEIGHT = 5)");
        b3.poop(5);
        System.out.println(
                "\n======TEST CASE 5 : TEST POOP (MORE THAN THERE IS)======\n         (THE RESULT SHOULD BE 'CAN NOT DO' )");
        b3.poop(30);
        System.out.println(
                "\n======TEST CASE 6 : TEST FEED THEN POOP======\n(THE RESULT SHOULD BE THE BIRD POOP AND STOMACH = 0)");
        b3.feed(20);
        System.out.println(
                "\n======TEST CASE 7 : TEST MATING b1 and b2======\n      (THE NAME OF BIRD KID SHOULD BE SD)");
        b1.mating(b2);
        System.out.println(
                "\n======TEST CASE 8 : TEST MATING b2 and b1======\n      (THE NAME OF BIRD KID SHOULD BE DS)");
        b2.mating(b1);
        System.out.println(
                "\n======TEST CASE 9 : TEST MATING SAME GENDER======\n    (THE RESULT SHOULD BE SOMETHING WORNG)");
        b3.mating(b1);
        
        

        

    }

}
