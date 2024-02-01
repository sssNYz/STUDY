package JAVA.OOPbyProf_Sirichai.Microwave;

public class Microwave {

    int wattNumber[] = {0,200,400,600,800}; /*Array of watts */
    int wattLevel; /*Level of watts from input */
    double watt; /*watt from list that double type, use for math */
    int time;/*time for microwave working */
    int statusOpen = 0, /*if 0 is not open*/ statusClose  = 0, /*  if 0 is not close */ statusFoodInMicrowave = 0; /*if 0 is not food in microwave */
    Food f; /*detail of food */
    double mathWellDone; /* math for calculate food welldone */

    //------------------------------------------------------------------------------------------------------- 
    // Microwave Status
    void open ()
    {
        statusOpen += 1;
    }
    void close ()
    {
        statusClose +=1;
    }

    void pressStart()
    {
        if (statusOpen == 1 && statusClose == 1 && statusFoodInMicrowave == 1 )
        {
            this.mathWellDone = mathWellDone(watt);   
            process(time, this.f);
        }
        if (statusOpen == 1 && statusClose == 1 && statusFoodInMicrowave == 0 )
        {
            System.out.println("FOOD IS NOT HAVE IN MICROWAVE");
        }
    
        else if (statusOpen == 0)
        {
            System.out.println("MICROWAVE IS NOT OPEN");
            this.mathWellDone = mathWellDone(0);
            process(time);
            
        }
        else if (statusClose == 0)
        {
            System.out.println("MICROWAVE IS NOT CLOSE");
            this.mathWellDone = mathWellDone(0);
            process(time,this.f);
        }
    }
    //-------------------------------------------------------------------------------------------------------
    //Microwave Setting
    void setWattLevel(int watt)
    {
        this.watt = this.wattNumber[watt];
        this.wattLevel = watt;
    }
    void setTime(int time)
    {
        this.time = time;
    }
    //-------------------------------------------------------------------------------------------------------
    //Microwave Work
    void put(Food f)
    {
        this.f = f;
        this.statusFoodInMicrowave += 1;
    }

    void process(int time, Food f) //HAVE FOOD IN MICROWAVE
    {
        System.out.println("-----------------DETAIL PROCESS------------------");
        System.out.println(f.getNameFood()+" : WATT LEVEL IS " + this.wattLevel + ":"+ this.wattNumber[wattLevel] + " WATT TIME IS " + this.time+"s." );        
        System.out.println("---------------------START-----------------------");
        for (int i = time; i > 0;i--)
        {
         System.out.println((i)+" SECONDS BEFORE COOKED. "+"("+(time-i)*mathWellDone+"% COOKED)");   
        }
        System.out.println("---------------------DONE------------------------");
        f.percentOfDone = time*mathWellDone;
        System.out.println("PERCENT COOKED OF "+f.getNameFood()+" IS "+f.percentOfDone+"%");
    }
    void process(int time) // DON'T HAVE FOOD IN MICROWAVE
    { 
        System.out.println("-----------------DETAIL PROCESS------------------");
        System.out.println("NO FOOD IN MICROWAVE : WATT LEVEL IS " + this.wattLevel + ":"+ this.wattNumber[wattLevel] + " WATT TIME IS " + this.time+"s." );        
        System.out.println("---------------------START-----------------------");
        for (int i = time; i > 0;i--)
        {
         System.out.println((i)+" SECONDS BEFORE COOKED. "+"("+(time-i)*mathWellDone+"% COOKED)");   
        }
        System.out.println("---------------------DONE------------------------");
        f.percentOfDone = time*mathWellDone;
        System.out.println("PERCENT COOKED OF NULL IS 0%");
    }
    

    double mathWellDone(double watt)
    {
        return (((((f.wattShouldBe/f.timeShouldBe)/f.timeShouldBe)*100)/(f.wattShouldBe/f.timeShouldBe))*watt)/f.wattShouldBe;
    }

    //-------------------------------------------------------------------------------------------------------
    //Getter
    double getWatt()
    {
        return watt;
    }
    int getTime()
    {
        return time;
    }
}
