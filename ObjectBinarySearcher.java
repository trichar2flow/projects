import java.util.Scanner;

//*****************************************
//   Name: Troy Richardson
//   CSI 162-002
//   Lab 3
//*****************************************

public class ObjectBinarySearcher
{
static int j=0;         // index number
static int first=0;     // first position in array
static int last=0;      // last position in array
static int middle=0;    // middle position in array
static int position=0;  // position of element in array
static boolean found;   // boolean variable for search method
static char question;   // holds user response for program
  
   public static void main(String[] args)
   {
    Scanner input = new Scanner(System.in);

   String[] names = { "Zeb","Deb","Will","Karen","Aaron","Chris","Barb","Kenny"};
   ObjectBinaryTools tester=new ObjectBinaryTools();
   String[] namesSorted=tester.sort(names);
   
   System.out.print("\n Array Contents:");
   for (int index=0; index<namesSorted.length-1; index++)
      {
      System.out.print(" "+namesSorted[index]);
      }
   System.out.print("\n");   
   System.out.print("\n Enter a value to search for: ");
   String userInput=input.nextLine();
   int location =tester.binarySearch(namesSorted, userInput);
      if(location==-1)
      {
      System.out.print("\n" + userInput + " was not found");
      }
      else
      {
      System.out.print("\n" + userInput + " was not found at position " + location);
      }
   
   
   System.out.print("\n Do you want to search again <Y or N>: ");
   question=input.next().charAt(0);
   System.out.print(question);
   
   while(question=='Y' || question=='y')
   { 
   System.out.print("\n");   
   System.out.print("\n Enter a value to search for: ");
   userInput=input.nextLine();
   location=tester.binarySearch(namesSorted, userInput);
    if(location==-1)
      {
      System.out.print("\n" + userInput + " was not found");
      }
      else
      {
      System.out.print("\n" + userInput + " was not found at position " + location);
      }
   
   System.out.print("\n Do you want to search again <Y or N>: ");
   question=input.next().charAt(0);
   }
    
      
   
   System.exit(0);
   }
   
   /**
   	   Service Class contaning sort and search methods
   */ 
   static class ObjectBinaryTools
   {
      /**
   	   The default constructor initializes an  class object 
      */
        public ObjectBinaryTools()
        {
        
        }
        
        /**
   	   The sort method sorts an array of Strings 
         @param Array of strings
         @return new sorted array
        */
        public String[] sort(String[] Array)
        {
         for(j=0; j<Array.length-1;j++)
         {
            for (int i=j+1 ; i<Array.length-1; i++)
            {
               if(Array[i].compareTo(Array[j])<0)
               {
                  String temp= Array[j];
                  Array[j]= Array[i]; 
                  Array[i]=temp;
               }
            } 
         }

        return Array;
       }
       
       /**
   	   The binarySearch method performs a binary search on 
         a sorted array of Strings 
         @param Sorted array of strings
         @param the name the user is searching for
         @return the position of the item searched for in array
        */
       public int binarySearch(String[] Array, String name)
       {
         first=0;
         last=Array.length-1;
         position=-1;
         found=false;
       
       while (!found && first <= last)
         {
            middle=(first+last)/2;
            
            if(Array[middle] == name)
            {
               found = true;
               position = middle;
            }
            else if(Array[middle].compareTo(name)>0)
            {
            last= middle-1;
            }
            else
            {
            first = middle+1;
            }
         }
         return position;
       
       } 
        
   }
        
        
        
} 
