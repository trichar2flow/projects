import java.text.DecimalFormat;

//*****************************************
//   Name: Troy Richardson
//   CSI 162-002
//   Lab 6
//*****************************************

class WorkerDemo
{
public static void main(String[] args)
   {
// Create a ProductionWorker object with valid data.
createWorker("John Smith", "123", "11-15-2009", ProductionWorker.DAY_SHIFT, 16.50);

// Try to use an invalid employee number.
System.out.println("\n Attempting an invalid employee number...");
createWorker("Steven Friend", "10001", "11-19-2010", ProductionWorker.DAY_SHIFT, 16.50);

// Try to use an invalid shift number.
System.out.println("\n Attempting an invalid shift number...");
createWorker("Joe Johnson", "123", "1-20-2011",66, 16.50);

// Try to use a negative pay rate.
System.out.println("\n Attempting a negative pay rate...");
createWorker("Ann Jones", "123", "12-15-2009",ProductionWorker.DAY_SHIFT, -99.00);
   }

//Create the createWorker() method here!
   public static void createWorker(String name, String empNum, String hireDate, int shiftNum, double payRateNum)
   {
      
         try
         {
            ProductionWorker worker = new ProductionWorker(name, empNum, hireDate, shiftNum, payRateNum);
            System.out.print(worker);
         }
         catch(InvalidEmployeeNumber e)
         {
         System.out.print("ERROR: invalid employee number");
         }
         catch(InvalidShiftNumber e)
         {
         System.out.print("ERROR: invalid shift number");
         }
         catch(NegativePayRate e)
         {
         System.out.print("ERROR: invalid pay rate");
         }
      
      
      
         
   }

}






public class Employee
{
private String employeeName; // String that holds the name of the employee
private String employeeNum;  //  String that holds the employee number
private String date;         // String that holds the hire date of the employee  

    /**
      default constructor for an employee object. with all fields 
      initialized
   */
   public Employee()
   {
      employeeName="";
      employeeNum="";
      date="";
   }
   /**
      constructor for an employee object. with all fields 
      initialized
      @param Employee name
      @param Employee number
      @param Hire date of Employee
      @exception throws an invalid employee number exception
   */
   public Employee(String name, String num, String hireDate)throws InvalidEmployeeNumber
   {
      employeeName=name;
      employeeNum=num;
      date=hireDate;
      
      int check =Integer.parseInt(num);
      if(check<0 || check>9999)
      {
      throw new InvalidEmployeeNumber();
      }
   }
   
   /**
     setter method for setting the Employee's name with a string
     @param Name of the Employee
   */
   public void setName(String name)
   {
      employeeName=name;
   }
   
   /**
     setter method for setting the Employee's number with a string
     @param number of the Employee
     @exception throws an invalid employee number exception
   */
   public void setEmployeeNumber(String num)throws InvalidEmployeeNumber
   {
   employeeNum=num;
   
   if(!employeeNumCheck(num))
      {
      throw new InvalidEmployeeNumber();
      }
   }
   
   /**
     setter method for setting the Employee's hire date with a string
     @param Hire date of the Employee
   */
   public void setDate(String hireDate)
   {
   date=hireDate;
   }
   
   /**
     getter method for getting the Employee's name from an object
     @return Name of the Employee
   */
   public String getName()
   {
      return employeeName;
   }
   
   /**
     getter method for getting the Employee's number from an object
     @return number of the Employee
   */
   public String getEmployeeNumber()
   {
      return employeeNum;
   }
   
   /**
     getter method for getting the Employee's hire date from an object
     @return hire date of the Employee
   */
   public String getDate()
   {
      return date;
   }
   
   /**
     toString method that creates a string Representation of the object
     @return the string representation
   */
   public String toString()
   {
      String str = " Name: " + employeeName +
                   "\n Employee Number: " + employeeNum +
                   "\n Hire Date: " + date;
     return str; 
   }


}
   
class ProductionWorker extends Employee
{
public static int DAY_SHIFT=1;   // integer value for day shift
public static int NIGHT_SHIFT=2; // integer value for night shift
private int shift;               // integer that sets the employee shift
private double payRate;          // double that holds the value for the employee's pay rate
   
   /**
      default constructor for an ProductionWorker object. with all fields 
      initialized
   */
   public ProductionWorker()
   {
      super();
      shift=DAY_SHIFT;
      payRate=0.0;
   }
   
   /**
      constructor for an ProductionWorker object. with all fields 
      initialized
      @param Name of Worker
      @param number of worker
      @param hire date of worker
      @param shift number
      @param worker's pay rate
      @exception Throws a invalid shift number
      @exception Throws a negative Pay Rate
      @exception throws an invalid employee number
   */
   public ProductionWorker(String name, String number, String hireDate, int shiftNum, double rate) throws InvalidShiftNumber, NegativePayRate, InvalidEmployeeNumber
   {
      super(name, number, hireDate);
      shift=shiftNum;
      payRate=rate;
      
      if(shift<1 || shift>2)
      {
         throw new InvalidShiftNumber();
      }
      if(payRate<0)
      {
         throw new NegativePayRate();
      }
      
   }
   
   /**
      Setter method that sets the shift number
      @param The value of the shift number
      @exception Throws a invalid shift number
   */
   public void setShift(int num)throws InvalidShiftNumber
   {
      shift=num;
      if(shift<1 || shift>2)
      {
         throw new InvalidShiftNumber();
      } 
   }
   
   /**
      Setter method that sets the Pay Rate
      @param The value of the Pay Rate
      @exception Throws a negative Pay Rate
   */
   public void setPayRate(double rate)throws NegativePayRate
   {
      payRate=rate;
      if(payRate<0)
      {
         throw new NegativePayRate();
      }
   }
   
   /**
      getter method that retrieves the shift number
      @return the value of the shift number
   */
   public int getShift()
   {
      return shift;
   }
   
   /**
      Getter method that retrieves the Pay Rate
      @return The value of the Pay Rate
   */
   public double getPayRate()
   {
      return payRate; 
   }
   
   /**
     toString method that creates a string Representation of the object
     @return the string representation
   */
   public String toString()
   {
      DecimalFormat pay = new DecimalFormat("#,##0.00");
      
      String str= super.toString();
       str += "\nShift: ";
      if (shift == DAY_SHIFT)
      {
         str += "Day";
      }
      if(shift == NIGHT_SHIFT)
      {
         str += "Night";
      }
         str += ("\nHourly Pay Rate: $" + pay.format(payRate));
         
       return str;

   }


}

   /**
      Exception class for an Invalid Employee Number
   */
class InvalidEmployeeNumber extends Exception
{

   public InvalidEmployeeNumber()
   {
      super("\n Error: Invalid employee number.");
   }
}

   /**
      Exception class for an Invalid Shift Number
   */
class InvalidShiftNumber extends Exception
{

   public InvalidShiftNumber()
   {
      super("\n Error: Invalid shift number.");
   }
}
   
   /**
      Exception class for an Negative Pay Rate
   */
class NegativePayRate extends Exception
{

   public NegativePayRate()
   {
      super("\n Error: Negative Pay Rate.");
   }
}
