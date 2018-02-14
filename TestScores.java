import java.util.Random;
import java.io.*;
import java.util.Scanner;
import java.text.*;

//*****************************************
//   Name: Troy Richardson
//   CSI 162-002
//   Lab 7
//*****************************************

public class TestScores implements java.io.Serializable  
{
	// Variable to reference an array holding test scores
	private double[] scores;
	
	/**
		The constructor initializes an object with
		an array of scores. If the array contains
		an invalid value (less than 0 or greater than
		100) an exception is thrown.
		@param s The array of test scores.
		@exception IllegalArgumentException When the
		           argument array contains an invalid
					  value.
	*/
		
	public TestScores(double[] s) throws IllegalArgumentException
	{
		// Create an array to hold the scores passed
		// as an argument.
		scores = new double[s.length];
		
		// Copy the scores passed as an argument into
		// the new array. Check for illegal values as
		// they are copied.
		for (int i = 0; i < s.length; i++)
		{
			if (s[i] < 0 || s[i] > 100)
				throw new IllegalArgumentException("Element: " + i + " Score: " + s[i]);
			else
				scores[i] = s[i];
		}
	}
	
	/**
		The getAverage method returns the average
		of the object's test scores.
		@return The average of the object's test scores.
	*/
	
	public double getAverage()
	{
		double total = 0.0;	// Accumulator
		
		// Accumulate the sum of the scores.
		for (int i = 0; i < scores.length; i++)
			total += scores[i];
		
		// return the average.
		return (total / scores.length);
	}
   
   /**
		The getScores method returns the an array
		of randomized test scores between 1 and 100.
		@return the array of test Scores.
	*/
   public static double[] getScores() 
      {
         Random randomGen= new Random();
         double[] testScore= new double[5];
         
         
         for(int index=0; index<5; index++)
         {
            double score=1.0+(100.0-1.0)*randomGen.nextDouble();
            testScore[index]=score;
         }
         
         return testScore;
        
      }
      
      
      /**
		   The writeObject method serialized an object and writes it to a file
         @param the Object output stream
         @param the test score Array    
	   */
      public static void writeObject(ObjectOutputStream stream, TestScores[] a)throws IOException
      {
         System.out.print("\n\n Write scores generated by random number generator to the file:");
         for(int i=0; i<a.length; i++)
         {
            stream.writeObject(a[i]);
            System.out.print("\n Serializing object #" +(i+1));
         }
      }
      
      /**
		   The readObject method deserializes an object and prints the objects
         @param the Object input stream
         @param the test score Array    
	   */
      
      public static void readObject(ObjectInputStream stream)throws IOException,ClassNotFoundException
      {
         TestScores[] g = new TestScores[5];
         System.out.print("\n\n Scores read from file:");
          for(int index=0; index<5; index++)
         {
           g[index]= (TestScores) stream.readObject();
           System.out.print(g[index]);
         }
      }
      
      
      /**
       toString method that creates a string Representation of the object
         @return the string representation
      */
      public String toString()
      {
         DecimalFormat df = new DecimalFormat("#.#");
         
         String str="\nScores: " + df.format(scores[0]) + " " +  df.format(scores[1]) + " " + df.format(scores[2]) + " " +  df.format(scores[3]) + " " + df.format(scores[4]);
                str+=" Average = " + df.format(this.getAverage()); 
                
                return str;
      }
      
   public static void main(String[] args)
   {
   
   /**
		An array of test scores      
	*/
   TestScores[] grades = new TestScores[5];
   
   
   
   /**
		Assigning random test scores to the array     
	*/
      for(int index=0; index<5; index++)
         {
         grades[index]= new TestScores(getScores());
         }
         
       
         
         System.out.print("\nScores generated by random number generator");
         System.out.print(grades[0]);
         System.out.print(grades[1]);
         System.out.print(grades[2]);
         System.out.print(grades[3]);
         System.out.print(grades[4]);
         
         
         /**
		      Writing the serialized object to the file  
	      */
         try
         {
         FileOutputStream outStream = new FileOutputStream("Objects.dat");
         ObjectOutputStream objectOutputFile = new ObjectOutputStream(outStream); 
         
         writeObject(objectOutputFile, grades);
         }
         catch(IOException e)
         {
          e.getMessage();
         }
         
         /**
		      Reading the serialized object from the file  
	      */
         try
         {
         FileInputStream inStream = new FileInputStream("Objects.dat");
         ObjectInputStream objectInputFile = new ObjectInputStream(inStream); 
         
         readObject(objectInputFile);
         }
         catch(IOException e)
         {
          e.getMessage();
         }
         catch(ClassNotFoundException e)
         {
          e.getMessage();
         }
         
         
   }
}
   