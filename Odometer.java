//*****************************************
//   Name: Troy Richardson
//   CSI 162-002
//   Lab 4
//*****************************************

public class Odometer
{

private int mileage; // number of miles the car has run
public int fuel;     // the amount of gallons of fuel
   
   
   /**
   	   This constructor creates an odometer object
         @param The current mileage
         @param the current fuel from the Fuel object
   */
   public Odometer(int numMileage, FuelGauge fuelObject)
   {
   mileage=numMileage;
   fuel= fuelObject.fuel;
   }
   
   /**
   	   This method increments the mileage by 1
         if mileage is greater than 999,999 it is 
         set to 0 
   */
   public void incrementMileage()
   {
      if(mileage<=999999)
      {
         mileage++;
      }
      else
      {
         mileage=0;
      }
   }
   
   /**
   	   returns the current mileage    
   */
   public int getMileage()
   {
   return mileage;
   }

}