import java.util.Scanner;

//*****************************************
//   Name: Troy Richardson
//   CSI 162-002
//   Lab 1
//*****************************************

public class SavingsAccount
{
     private double interestRate;             //Annual Insterest Rate
     private double balance;                 // Balance
     public static int months;               // number of months passed
     public static double depositAmt=0;      // Amount deposited for the month
     public static double withdrawAmt=0;     // Amount withdrawn for the month
     public static double totalDeposit=0;    // The sum of all deposits 
     public static double totalWithdraw=0;   // The sum of all withdraws
     public static double totalInterest=0;   // the sum of all dollars accumulated by interest
      
   public static void main(String[] args)
   { 
      /**
   	   New SavingsAccount object 
      */
      SavingsAccount account=new SavingsAccount();
      
      /**
   	   creates new scanner object and prompts user to enter thier account balance
      */
      Scanner input = new Scanner(System.in);
      System.out.print("\nEnter the savings account's starting balance:  ");
      account.balance=input.nextDouble();
      
       /**
   	   prompts user to enter thier account's annual Interest rate
       */
      System.out.print("\nEnter the savings account's annual interest rate:  ");
      account.interestRate=input.nextDouble();
      
       /**
   	   prompts user to enter the number of months that have passed since the account opened
         @variable months holds the user input  
       */
      System.out.print("\nHow many months have passed since the account was opened?  ");
      months=input.nextInt();
      
      /**
   	   for loop that runs a number of times equal to the number of months that have passed 
         since the account opened
      */
      for(int i=1; i<=months; i++)
      {
      
      /**
   	   prompts user to enter the dollar amount deposited into the account
         @variable depositAmt holds user input 
         account object calls the deposit method with @param depositAmt
         @variable totalDeposit keeps track of the accumulated sum of deposits  
      */
      System.out.print("\n\tEnter the amount deposited during month " +  i + ": ");
      depositAmt=input.nextDouble();
      account.deposit(depositAmt);
      totalDeposit=totalDeposit+depositAmt;
      
       /**
   	   prompts user to enter the dollar amount withdrawn from the account
         @variable withdrawAmt holds user input  
      */
      System.out.print("\n\tEnter the amount withdrawn during month " +  i + ": ");
      withdrawAmt=input.nextDouble();
         
       /**
   	   while loop that checks the amount that the user has withdrawn
         against the total amount in the balance 
         if the amount of the withdraw is greater than the balance,
         a message prompts the user to input a new  value until a valid value is entered  
       */
         while(withdrawAmt>account.balance)
         {
          System.out.print("\n\t Error: the amount withdrawn is greater than the current balance, please enter a new value: ");
          withdrawAmt=input.nextDouble();
         }
         
       /**
   	   account object calls the withdraw method with @param withdrawAmt
         @variable totalWithdraw hold the sum of all user withdraws  
       */
      account.withdraw(withdrawAmt);
      totalWithdraw=totalWithdraw+withdrawAmt;
      
       /**
   	   account object adds the monthly interest to the account balance
         @variable totalInterest holds the sum of all added interest  
       */
      account.balance=account.balance+account.interest(account.getBalance());  
      totalInterest=totalInterest+account.interest(account.getBalance());;

      }
      
       /**
   	   outputs the following,
         the total amount deposited
         the total amount withdrawn 
         the ending balance
         the class calls the format method to format the doubles into currency form  
       */
      System.out.print("\nTotal deposited:$" + String.format("%1$,.2f", totalDeposit));
      System.out.print("\nTotal withdrawn:$" + String.format("%1$,.2f", totalWithdraw));
      System.out.print("\nInterest earned:$" + String.format("%1$,.2f", totalInterest));
      System.out.print("\nEnding balance:$" + String.format("%1$,.2f", account.getBalance()));
      
      System.exit(0);
   }
   
    /**
   	   The default constructor initializes an object 
    */
      public SavingsAccount()
      {
      
      }
   
      /**
   	   The constructor initializes an object with the
		   annual interest rate and balance.
		   @param i is the annual interest rate.
		   @param b the balance.
      */
   
      public SavingsAccount(double i, double b)
      {
         i=interestRate;
         b=balance;
      }
   
      /**
   	   The withdraw method calculates the balance amount after
         a withdraw.
         @param the amount of the withdraw
         @return the balance after withdraw
      */
      public double withdraw(double withdrawAmount)
      {
         balance=balance-withdrawAmount;
         return balance;
      }
      
      /**
   	   The deposit method calculates the balance amount after
         a deposit.
         @param the amount of the deposit
         @return the balance after deposit
      */
      public double deposit(double depositAmount)
      {
         balance=balance+depositAmount;
         return balance;
      }
      
      /**
   	   The interest method calculates the amount of dollars added  
         to the balance after adding monthly interest.
         @param the balance
         @return the amount of dollars added by interest
      */
      public double interest(double balance)
      {
         double monthlyInterest=interestRate/12.0;
         double interestAdded=(monthlyInterest*balance);
         return interestAdded;
      }
      
      /**
   	   This mutator method sets the annual interest Rate
         @param the annual interest rate
         @return new annual interest rate
      */
      public void setInterest(double interestRate)
      {
      this.interestRate=interestRate;
      }
      
      /**
   	   This accessor method gives you the current interestRate
         a withdraw.
         @return the current interestRate
      */
      public double getInterestRate()
      {
      return interestRate;
      }
      
      /**
   	   This accessor method gives you the current balance
         a withdraw.
         @return the balance
      */
      public double getBalance()
      {
      return balance;
      }
}
