import java.util.Scanner;
//*****************************************
//   Name: Troy Richardson
//   CSI 162-002
//   Lab 10
//*****************************************

public class Population
{
   double  organisms = 0.0;   // number of organisms
   double organismsNew= 0.0;  //number of organisims in after multiplying
   double increase = 0.0;  // the increase rate 
   int  days = 0; // number of days the organism multiplies
   int count=0;   //counts number of days in the recursive method
   
   
   
 /**
      population constructor to test methods
 */  
 public Population()
 {
 
 }
 
   /**
         Main method
   */
   public static void main(String[] arg)
   {
     Population tester = new Population();
     tester.populationLoop();
    
    System.out.print("This is the recursive solution to the population problem");
    tester.populationRecursive(100, 0.02, 10);
   }
   
   
   /**
      public method that uses loop to calculate population
   */
   public void populationLoop()
   {  
      System.out.print("This is the loop solution to the population problem");
      
      Scanner keyboard = new Scanner(System.in);
      
      System.out.print("\nEnter the starting number of organisms: ");
      organisms=keyboard.nextDouble();
      
      System.out.print("Enter the daily increase: ");
      increase=keyboard.nextDouble();
      
      System.out.print("Enter the  number of days the organisms will multiply: ");
      days=keyboard.nextInt();
      
      System.out.println("Day              Organisms");
      System.out.println("---------------------------");
     // organismsNew=organisms;
      for(int i=1; i<=days; i++)
      {
         organismsNew=organisms+(increase)*organismsNew;
         organisms=organismsNew;
      System.out.println(""+i+"               "+ organismsNew); 
      }
   }   
      
   
   /**
      public method that uses recurstion to calculate the population
   */
   public double populationRecursive(double org, double rate, int day)
   {  
      
      count=count+1;
      
      if(day>0)
      {
         organismsNew=org+rate*organismsNew;
         org=organismsNew;
         System.out.print("\nDay:"+count+ " Organisms:"+org);
         
         return  org+(populationRecursive(org,rate,day-1)*0);
      }
      else
      {
         return org;
      }
      
   }
   
}