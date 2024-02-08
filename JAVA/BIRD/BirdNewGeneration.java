package JAVA.OOPbyProf_Sirichai.BIRD_NEW_GENERATION;
public class BirdNewGeneration {
    String birdName;
    char[] birdNameArray = {};
    char[] testName = {'A','B'};
    int birdAge;
    float birdWeight, birdStomachWeight, foodWeight;
    char birdGender; /* m = male f = female */
    // Constructor
    BirdNewGeneration(String name, int age, float weight, char gender) {
        this.birdName = name;
        this.birdAge = age;
        this.birdWeight = weight;
        this.birdGender = gender;
        this.birdNameArray = name.toCharArray();
    }
    /* Overloading
    bird can repeat your word if u command it talk, But if u command it talk without word, bird will tell it age*/
    void talk() {
        System.out.println(this.birdName + ": I'M " + this.birdAge + " YEARS OLD");
        System.out.println("=".repeat(50));
    }
    void talk(String word) {
        System.out.println(this.birdName + " : " + word);
        System.out.println("=".repeat(50));
    }
    /*the food that bird eat will add to stomach, But if stomach have weight more than weight of bird, bird will poop (poop is weight of stomach = 0)*/
    void feed(float foodWeight) {
        this.foodWeight = foodWeight;
        this.birdStomachWeight += this.foodWeight;

        if (this.birdStomachWeight < this.birdWeight) {
            System.out.println(this.birdName + " : THX FOR YUMMY :)\n");
            System.out.println("**" + this.birdName + " WEIGTH IS " + this.birdWeight + "g.");
            System.out.println("**" + this.birdName + " STOMACH WEIGHT IS " + this.birdStomachWeight + "g.");
            System.out.println(
                    "**" + this.birdName + " CAN EAT ANOTHER " + (this.birdWeight - this.birdStomachWeight) + "g.");
            System.out.println("=".repeat(50));
        } else {
            System.out.println(this.birdName + " : I'M FULL :(\n");
            System.out.println("**" + this.birdName + " WEIGTH IS " + this.birdWeight + "g.");
            System.out.println("**" + this.birdName + " STOMACH WEIGHT IS " + this.birdStomachWeight + "g.");
            System.out.println("\n" + this.birdName + " : (Pooh~~!)\n");
            poop();
            System.out.println("**" + this.birdName + " IS POOP");
            System.out.println("**" + this.birdName + " WEIGTH IS " + this.birdWeight + "g.");
            System.out.println("**" + this.birdName + " STOMACH WEIGHT IS " + this.birdStomachWeight + "g.");
            System.out.println("=".repeat(50));
        }
    }
    void poop() {
        this.birdStomachWeight = 0;
    }
    void poop(float weight) {

        if (this.birdStomachWeight > weight) 
        {
            this.birdStomachWeight -= weight ;
            System.out.println("\n" + this.birdName + " : (Pooh~~!)\n");
            System.out.println("**" + this.birdName + " WEIGTH IS " + this.birdWeight + "g.");
            System.out.println("**" + this.birdName + " STOMACH WEIGHT IS " + this.birdStomachWeight + "g.");
            System.out.println(
                    "**" + this.birdName + " CAN EAT ANOTHER " + (this.birdWeight - this.birdStomachWeight) + "g.");
            System.out.println("=".repeat(50));
        }
        else 
        {
            System.out.println("** YOU CAN NOT DO THIS, BECOUSE OF THE AMOUNT YOU PUT IN MORE THAN THE WEIGHT THE BIRD CARRIES");
            System.out.println("=".repeat(50));
        }
    }
    /*bird can mating with another bird, But must not same gender. And a bird can have kid. The mating process like a bird send signal it wanna mating, that make a first letter of kid name is the bird that send signal and second letter of kid name is other bird that mating*/
    void mating(BirdNewGeneration lover) {
        // chack gender
        if (this.birdGender != lover.birdGender) {
            System.out.println("**" + this.birdName + " MATING WITH " + lover.birdName + "\nAND THEM AND A KID!!");
            birthBird(this.birdNameArray[0], lover.birdNameArray[0]);
        } else {
            System.out.println("**SOMETHING WORNG : SAME GENDER MATING IS UNNATURAL FOR BIRD\n" + this.birdName
                    + " CAN NOT MATING WITH " + lover.birdName);
            System.out.println("=".repeat(50));
        }

    }
    public BirdNewGeneration birthBird(char firstName, char secondName) {
        String nameKid = String.valueOf(firstName) + String.valueOf(secondName);
        BirdNewGeneration birdKid = new BirdNewGeneration(nameKid, 1, 6, this.birdGender);
        System.out.println(birdKid.birdName + ": HI PAPA, HI MAMA, I'M " + birdKid.birdName);
        System.out.println(birdKid.birdName + ": I'M " + birdKid.birdAge + " YEARS OLD (^o^)");
        System.out.println("=".repeat(50));
        return birdKid;
 
    }
}
