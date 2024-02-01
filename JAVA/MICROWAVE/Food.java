package JAVA.OOPbyProf_Sirichai.Microwave;

public class Food {
    double percentOfDone;
    String nameFood;
    double wattShouldBe;
    int timeShouldBe;
    int wellDone = 0; // 0 is mean not done
    int burn = 0; // 0 is mean not burn

    //Sand detail food ------------------------------------------------------------------------------------------
    Food (String name, int watt, int time)
    {
        this.nameFood = name;
        this.wattShouldBe = watt;
        this.timeShouldBe = time;
    }

    //Getter ----------------------------------------------------------------------------------------------------   
    String getNameFood()
    {
        return nameFood;
    }
    double getWatt()
    {
        return wattShouldBe;
    }
    int getTime()
    {
        return timeShouldBe;
    }
    
    //Food status ----------------------------------------------------------------------------------------------
    String isWellDone()
    {
        if(this.percentOfDone >= 100)
            return "true";
        else
            return "false";
    }
    String isBurn()
    {
        if (this.percentOfDone > 100)
            return "true";
        else
            return "false";
    }

}
