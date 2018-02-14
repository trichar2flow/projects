public class Essay extends GradedActivity
{

private double grammar; //holds value for grammar score
private double spelling; // holds value for spelling score
private double correctLength; // holds value for correctLength score
private double content; // holds value for content score
private double essayScore; // holds total score


   /**
      default constructor for an essay Object
   */
   public Essay()
   {
   grammar=0.0;
   spelling=0.0;
   correctLength=0.0;
   content=0.0;
   essayScore=0.0;
   }
   
   /**
      second constructor for an essay Object
      @param g value for grammar
      @param s value for spelling
      @param cl value for correct length
      @param c value for content
   */
   public Essay(double g, double s, double cl, double c)
   {
   grammar=g;
   spelling=s;
   correctLength=cl;
   content=c;
   essayScore= grammar + spelling + correctLength + content;
   setScore(essayScore); 
   }
   
   /**
      The setter method that sets a value for grammar
      @Param the value for grammar
   */
   public void setGrammar(double grammarScore)
   {
      grammar=grammarScore;
      if(grammar>30.0 || grammar<0.0)
      {
      grammar=0;
      }
      
   }
   
   /**
      The setter method that sets a value for spelling
      @Param the value for spelling
   */
   public void setSpelling(double spellingScore)
   {
      spelling=spellingScore;
      if(spelling>20.0 || spelling<0.0)
      {
      spelling=0;
      }
      
   }
   /**
      The setter method that sets a value for correctLength
      @Param the value for correctLength
   */
    public void setcorrectLength(double correctLengthScore)
   {
      correctLength=correctLengthScore;
      if(correctLength>20.0 || correctLength<0.0)
      {
      correctLength=0;
      }
      
   }
   /**
      The setter method that sets a value for contentScore
      @Param the value for contentScore
   */
   public void setContent(double contentScore)
   {
      content=contentScore;
      if(content>30.0 || content<0.0)
      {
      content=0;
      }
      
   }
   /**
      The setter method that sets a value for essayScore
      @Param the value for essayScore
   */
   public void setessayScore()
   {
   essayScore= grammar + spelling + correctLength + content;
   }
   
}