//*****************************************
//   Name: Troy Richardson
//   CSI 162-002
//   Lab 3
//*****************************************

public class BenchmarkSorter
{
   static int numBubbleSwaps=0;     // Number of bubble swaps
   static int numSelectionSwaps=0;  // number of selection swaps
   static int numinsertionSwaps=0;  // number of insertion swaps
   
   public static void main(String[] args)
   {
   
   int numbers1[] = { 536, 289, 296, 429, 319, 142, 394,
                       101, 388, 147, 417, 199, 207, 222,
                         189, 310, 447, 521, 234, 600};
                         
   int numbers2[] = { 536, 289, 296, 429, 319, 142, 394,
                       101, 388, 147, 417, 199, 207, 222,
                         189, 310, 447, 521, 234, 600};
                         
   int numbers3[] = { 536, 289, 296, 429, 319, 142, 394,
                       101, 388, 147, 417, 199, 207, 222,
                         189, 310, 447, 521, 234, 600};
                         
   int numbers4[] = { 536, 289, 296, 429, 319, 142, 394,
                       101, 388, 147, 417, 199, 207, 222,
                         189, 310, 447, 521, 234, 600};
                      
   
   BenchmarkSorterTools tester=new BenchmarkSorterTools();
   
   numBubbleSwaps=tester.bubbleSort(numbers1);
   numSelectionSwaps=tester.selectionSort(numbers2);
   numinsertionSwaps=tester.inesrtionSort(numbers1);
   
   System.out.print("\n Number of swaps made with bubble sort: " + numBubbleSwaps);
   System.out.print("\n Number of swaps made with selection sort: "+ numSelectionSwaps);
   System.out.print("\n Number of swaps made with insertion sort: "+  numinsertionSwaps);

   
   
   
   
   
   
   
   System.exit(0);
   }


static class BenchmarkSorterTools
   {
         
       /**
   	   The default constructor initializes an  class object 
       */  
       public BenchmarkSorterTools()
        {
        
        }
        
        /**
   	   The bubbleSort method performs a Bubble sort on an array of integers 
         @param Array of integers
         @return the number of swaps perfromed by the method
        */
        public static int bubbleSort(int[] array)
        {
        int lastPos; // last position
        int index;   // index location 
        int temp;    // holds an element 
        int count=0; // counter
        
         for(lastPos = array.length-1; lastPos>=0; lastPos--)
          {
            for(index=0; index<=lastPos-1; index++)
            { 
               if(array[index] > array[index+1])
               {
                  temp= array[index+1];
                  array[index]= array[index+1];
                  array[index+1]= temp;
                  count++;
                  
               }
            }
          }
          return count; 
        }
        
        /**
   	   The selectionSort method performs a selection sort on an array of integers 
         @param Array of integers
         @return the number of swaps perfromed by the method
        */
        public static int selectionSort(int[] array)
        {
         int startScan;  // Starting position of sort
         int index;      // Position of value
         int minIndex;  // Element with the smallest value
         int minValue;  // Smallest value found
         int count=0;   // counter
         
            for(startScan=0; startScan<array.length-1; startScan++)
            {
               minIndex=startScan;
               minValue=array[startScan];
            
               for(index=startScan+1;index<array.length;index++)
               {
                  if(array[index]<minValue)
                  {
                     minValue=array[index];
                     minIndex=index;
                     
                  }
               }
                  array[minIndex]=array[startScan];
                  array[startScan]=minValue;
                  count=count+1;
                  
            }
               return count;
        }
        
        /**
   	   The insertionSort method performs a insertion sort on an array of integers 
         @param Array of integers
         @return the number of swaps perfromed by the method
        */
        public static int inesrtionSort(int[] array)
        {
         int unsortedValue;   //unsorted value   
         int scan;            //scan the array
         int count=0;         //counter
         for(int index=1; index<array.length; index++)
         {
            unsortedValue=array[index];
            scan=index;
            
            while((scan>0)&&(array[scan-1]>unsortedValue))
            {
               array[scan]= array[scan-1];
               scan--;
               count=count+1;
            }
               array[scan]=unsortedValue;
         }
         return count;
        }
        
   
   
   
   
   
   
   
   }














}