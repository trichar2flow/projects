class CourseDemo
{
   public static void main(String[] args)
   {
      CourseGrades course= new CourseGrades();
   
      PassFailActivity activity = new PassFailActivity(85);
      
      PassFailExam pfexam = new PassFailExam(20,3,70);
      
      FinalExam finalExam = new FinalExam(50,10);
       
      Essay essay= new Essay(25,18,17,20);
      
      
      course.setLab(activity);
      course.setPassFailExam(pfexam);
      course.setEssay(essay);
      course.setFinalExam(finalExam);
      System.out.print(course);
      
      
   }

}



public class CourseGrades implements Analyzable
{
 
GradedActivity[] grades; // an array of size 4 filled with references to GradedActivity objects
   
   /**
      default constructor that instantiates a graded activity array
   */
   public CourseGrades()
   {
   grades = new GradedActivity[4];
   }
   /**
      The setter method that sets a GradedActivity object
      at the 0 position in a array.
      @Param GradedActivity object
   */
   public void setLab(GradedActivity Lab)
   {
      grades[0] = Lab;
   }
   
   /**
      The setter method that sets a PassFailExam object
      at the 1 position in a array.
      @Param PassFailExam object
   */
   public void setPassFailExam(PassFailExam Exam)
   {
      grades[1] = Exam;
   }
   
   /**
      The setter method that sets a Essay object
      at the 2 position in a array.
      @Param Essay object
   */
   public void setEssay(Essay essay)
   {
      grades[2] = essay;
   }
   
   /**
      The setter method that sets a PassFailExam object
      at the 1 position in a array.
      @Param PassFailExam object
   */
   public void setFinalExam(FinalExam finalExam)
   {
      grades[3] = finalExam;
   }
   
   /**
      This method gets the average of all the scores
      in the array
      @return average of scores
   */
   public double getAverage()
   {
     double total=0.0;
     
     for(int i=0; i<=grades.length-1; i++)
     {
     total=total+grades[i].getScore();
     }
     
     return total/grades.length;     
   }
   /**
      This method finds the highest score
      in the array
      @return the activity with the highest score
   */
   public GradedActivity getHighest()
   {
      double highest=0.0; // highest score
      int index=0;     // index number of highest score
      
     for(int i=0; i<=grades.length-1; i++)
     {
         if(highest<grades[i].getScore())
         {
         highest=grades[i].getScore();
         }
     }
     
     for(int i=0; i<=grades.length-1; i++)
     {
         if(highest==grades[i].getScore())
         {
         index=i; 
         }
     }
     return grades[index];
      
   }
   
   
   /**
      This method finds the lowest score
      in the array
      @return the activity with the lowest score
   */
   public GradedActivity getLowest()
   {
      double lowest=1000.0; // holds value for lowest score
      int index=0;     // index number of lowest score
      
     for(int i=0; i<=grades.length-1; i++)
     {
         if(lowest>grades[i].getScore())
         {
         lowest=grades[i].getScore();
         }
     }
     
     for(int i=0; i<=grades.length-1; i++)
     {
         if(lowest==grades[i].getScore())
         {
         index=i; 
         }
     }
     return grades[index];
      
   }  
   
   
   /**
      ToString method that displays the scores and grade of each graded Activity in the array
   */
   public String toString()
   {
   return"\nLab Score: " + grades[0].getScore() + " Grade: " + grades[0].getGrade()+
         "\nPass/Fail Exam Score: " +grades[1].getScore() + " Grade: " +grades[1].getGrade()+
        "\nEssayScore: " + grades[2].getScore() + " Grade: " + grades[2].getGrade()+
        "\nFinal Exam Score: " + grades[3].getScore() + " Grade: " + grades[3].getGrade()+
        "\nAverage Score: " + this.getAverage() +
        "\nHighest Score: " + this.getHighest().getScore()+ 
        "\nLowest Score: " + this.getLowest().getScore();  
   }
  


}