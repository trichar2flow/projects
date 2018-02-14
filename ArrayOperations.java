import java.util.*;
//*****************************************
//   Name: Troy Richardson
//   CSI 162-002
//   Lab 2
//*****************************************

public class ArrayOperations
{
   public static double[][] testArray; 
   private final static int row=2;
   private final static int column=3;
   public static int highestNum=0;

   

public static void main(String[] args)
   {
    
     double[][] testArray={{98.7,89.2,55.1},{77.6,99.9,62.2}};   
      ArrayTools tester=new ArrayTools();
      
      System.out.print("\nProcessing the double array.");
      System.out.print("\nTotal: "+ tester.getTotal(testArray));
      System.out.print("\nAverage: "+ tester.getAverage(testArray));
      System.out.print("\nTotal of row 0: "+ tester.getRowTotal(testArray,0));
      System.out.print("\nTotal of row 1: "+ tester.getRowTotal(testArray,1));
      System.out.print("\nTotal of col 0: "+ tester.getColumnTotal(testArray,0));
      System.out.print("\nTotal of col 2: "+ tester.getColumnTotal(testArray,2));
      System.out.print("\nHighest of row 0: "+ tester.getHighestinRow(testArray,0));
      System.out.print("\nHighest of row 1: "+ tester.getHighestinRow(testArray,1));
      System.out.print("\nLowest of row 0: "+ tester.getLowestinRow(testArray,0));
      System.out.print("\nLowest of row 1: "+ tester.getLowestinRow(testArray,1));
      System.out.print("\n");
      System.out.print("\nThis is the ArrayList part of the program");
      
      /**
   	   New ArrayList<Integer> object 
      */
      ArrayList<Integer> numbers = new ArrayList<Integer>();
       System.out.print("\n the number list is currently:");
      
      /**
   	   for loop that converts the strings that were passed into
         the args[] into integers. Then adds them to the numbers
         array list
      */ 
      for(int i=0; i<=9; i++)
      {
      int convert= Integer.parseInt(args[i]);
      numbers.add(convert);
      }
      
      /**
   	   for loop that displays the old set of numbers
         in the array and finds the highest value 
      */
      for (int index=0; index<numbers.size(); index++)
      {
      System.out.print(" "+numbers.get(index));
         if(highestNum<numbers.get(index))
         {
         highestNum=numbers.get(index);
         }
      }
      System.out.print("\n the Largest number is currently: "+highestNum);
      
      numbers.add(3, 15);    // Inserts the integer 15 at position 3 in the array
      numbers.remove(5);     // Removes the integer at position 5 
      numbers.set(2, 21);    // Replaces the integer at position 2 with the integer 21
      
      /**
   	   Displays new ArrayList of integers after calling
         the ArrayList methods
      */
      System.out.print("\n the new number list is now:");
      for (int index=0; index<numbers.size(); index++)
      {
      System.out.print(" "+numbers.get(index));  
      }

       
   
   
   
   
      System.exit(0);
   }
   
    /**
   	   Service Class contaning Accessor and Mutator methods 
    */ 
   static class ArrayTools
   {
   
      /**
   	   The default constructor initializes an  class object 
      */
      public ArrayTools()
      {
      }
     
 
      /**
   	   The getTotal method calculates the sum of all 
         elements in the array 
         @param 2 dimensional array of type double
         @return the sum of all elements 
      */
    public double getTotal(double[][] array)
      {
      double total=0.0;
         for (int i = 0; i<=row-1; i++)
         { 
            for (int j = 0; j<=column-1; j++)
            {
            total=total+array[i][j];
            }
         }    

         return total; 
      }
      
      /**
   	   The getAverage method calculates the average of all 
         elements in the array 
         @param 2 dimensional array of type double
         @return the average of all elements 
      */
   public double getAverage(double[][] array)
      {
      double average=0.0;
      double total=0.0;
      int numElements=0;
      
         for (int i = 0; i<=row-1; i++)
         { 
            for (int j = 0; j<=column-1; j++)
            {
            total=total+array[i][j];
            numElements=numElements+1;
            }
         }
         average=total/numElements;
         return average; 
      }
      
      /**
   	   The getRowTotal method calculates the sum of all 
         elements in a row specified by the parameter 
         @param 2 dimensional array of type double
         @param the Row number
         @return the sum of all elements in a row 
      */
   public double getRowTotal(double[][] array, int rowNum)
      {
      
      double totalRow=0.0;
  
            for (int j = 0; j<=column-1; j++)
            {
            totalRow=totalRow+array[rowNum][j];
            }
         return totalRow;
      }
      
       /**
   	   The getColTotal method calculates the sum of all 
         elements in a column specified by the parameter 
         @param 2 dimensional array of type double
         @param the column number
         @return the sum of all elements in a column 
      */
   public double getColumnTotal(double[][] array, int colNum)
      {
      
      double totalCol=0.0;
  
            for (int i = 0; i<=row-1; i++)
            {
            totalCol=totalCol+array[i][colNum];
            }
         return totalCol;
      }
      
      /**
   	   The getHighestinRow method finds the highest value of all 
         the elements in the row specified by the parameter 
         @param 2 dimensional array of type double
         @param the row number
         @return the highest value of all elements in a row 
      */
      public double getHighestinRow(double[][] array, int rowNum)
      {
      
      double highest=0.0;
      
       for (int j = 0; j<=column-1; j++)
            {
               if(highest<array[rowNum][j])
               {
               highest=array[rowNum][j];
               }
            }
         return highest;
      }
      
       /**
   	   The getLowestinRow method finds the lowest value of all 
         the elements in the row specified by the parameter 
         @param 2 dimensional array of type double
         @param the row number
         @return the lowest value of all elements in a row 
      */
       public double getLowestinRow(double[][] array, int rowNum)
      {
      
      double lowest=1000000000.0;
      
       for (int j = 0; j<=column-1; j++)
            {
               if(lowest>array[rowNum][j])
               {
               lowest=array[rowNum][j];
               }
            }
         return lowest;
      }
      
       
   }
   
}        