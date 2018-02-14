//*****************************************
//   Name: Troy Richardson
//   CSI 162-002
//   Lab 4
//*****************************************

public class Month
{
public static int monthNumber; // the corresponding month number

public enum Mon  {JANUARY,FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, // new data type called Mon that contains the name of each month
                  SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER };


      /**
   	   The default constructor initializes an  class object 
      */
      public Month()
      {
      Mon month= Mon.valueOf(monthNumber);
      }
      
      /**
   	   The second constructor initializes an  class object 
      */
      public Month(int numMonth)
      {
       monthNumber=numMonth;
         if(numMonth>12 || numMonth<1)
         {
         monthNumber=1;
         }
       }
      
      /**
   	   The third constructor initializes an  class object 
      */
      public Month(Mon name)
       {
       monthNumber=name.ordinal(); 
       }

      /**
   	   This method sets the current value of the month number
         @param the number that will set 
      */
      public void setMonthNumber(int num)
      {
      monthNumber=num;
         if(num>12 || num<1)
         {
         monthNumber=1;
         }
      
      }
      
      /**
   	   This method gives you the current value of the month number
         @return Month Number 
      */
      public int getMonthNumber()
      {
      return monthNumber;
      }
      
      public Mon getMonthName()
      {
      return mon.valueOf();
      }
    
    
    
   
}