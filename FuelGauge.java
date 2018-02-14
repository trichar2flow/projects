//*****************************************
//   Name: Troy Richardson
//   CSI 162-002
//   Lab 4
//*****************************************


public class FuelGauge
{
   public int fuel; // the amount of gallons of fuel
   
   /**
   	   This constructor creates an FuelGauge object
         @param The current fuel
        
   */
   public FuelGauge()
   {
   fuel=0;
   }
   
   /**
   	   returns the current mileage
   */
   public int getGallons()
   {
   return fuel;
   }
   
   /**
   	   This method increments the fuel by 1
         if fuel is less than or equal to 15 
   */
   public void incrementGallons()
   {
      if(fuel<=15)
      {
      fuel++;
      }
   }
   
   /**
   	   This method decrements the fuel by 1
         if fuel is greater than 0 
   */
   public void decrementGallons()
   {
      if(fuel>0)
      {
      fuel--;
      }
   }
   

}