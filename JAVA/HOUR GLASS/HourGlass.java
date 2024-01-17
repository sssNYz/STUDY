package JAVA.OOPbyProf_Sirichai.HOURGLASS;
public class HourGlass 
{
    int sandA,sandB,sideA=4;
    void rotate90()
    {
        System.out.println("ROTATE TO THE RIGHT 90 DEGREES");
        sideA++;
        if(sideA == 5)
            sideA=1;
        System.out.println("SIDE A IS ON SIDE  = "+ sideA + "\n--------------------------------");
        sandMove();
    }
    void rotate180()
    {
        System.out.println("ROTATE TO THE RIGHT 180 DEGREES");
        sideA=sideA+2;
        if(sideA == 5)
            sideA=1;
        else if(sideA == 6)
            sideA=2;
        System.out.println("SIDE A IS ON SIDE  = "+ sideA + "\n--------------------------------");
        sandMove();
    }
    void sandMove()
    {
         if(sideA == 1)
         {
            if(sandA>0)//ถ้ามีทราย
            {
                System.out.println("START!!");
                while(sandA>0)
                {
                    delay();
                    sandA-=1000;
                    sandB+=1000;
                    System.out.println("sandA -> " + sandA + " sandB -> " + sandB );
                }

                System.out.println("FINISH!!\n--------------------------------");
            }
         }
        else if(sideA==3)
         {
            if(sandB>0)
            {
                System.out.println("START!!");
                while(sandB>0)
                {
                    delay();
                    sandA+=1000;
                    sandB-=1000;
                    System.out.println("sandB -> " + sandB + " sandA -> " + sandA );
                }
                System.out.println("FINISH\n-------------------------------");
            }
         }
         else
            System.out.println("NOTHING..\n--------------------------------");

    }
    void delay() {
        try {
            // Attempt to pause the thread for 1000 milliseconds (1 second)
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            // If the pause is interrupted, handle the exception here
            e.printStackTrace();
        }
    }
    

    
}

