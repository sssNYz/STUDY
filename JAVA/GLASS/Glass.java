package JAVA.OOPbyProf_Sirichai.Glass;

public class Glass {
    int capacity, water;

    void addWater(String waterFull) {
        water = capacity;
    }

    void addWater(int x) {
        if (water >= capacity)
            water = capacity;
        else
            water = x + water;
    }

    void poourWater(String waterPour) {
        water = 0;
    }

    void poourWater(int x) {
        if (x > water) {
            System.out.println("NOT ENOUGH FOR POUR!");
        } else {
            water = water - x;
        }
    }

    void pourWaterToAnotherGlass(Glass otherGlass, int x) {
        if (x > water) {
            System.out.println("NOT ENOUGH FOR POUR!");
        } else {
            if (x >= otherGlass.capacity) {
                otherGlass.water = otherGlass.capacity;
                water -= x;
            } else {
                otherGlass.water += x;
                water -= x;
            }
        }
    }

    void pourWaterToAnotherGlass(Glass otherGlass, String x) {
        if (water >= otherGlass.capacity) {
            otherGlass.water = otherGlass.capacity;
            water = 0;
        } else {
            otherGlass.water = water;
            water = 0;
        }
    }
}
