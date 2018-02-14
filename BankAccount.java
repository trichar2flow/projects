import java.text.DecimalFormat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

//*****************************************
//   Name: Troy Richardson
//   CSI 162-002
//   Project  
//*****************************************
   
/**
 Unfortunately after multiple attempts and different solutions I could not get the instanced class objects to write and read to a file
 I was also unable to do the database method because I lack the driver(even though I bought MS access and download the 2010 database with drivers).
 So I have set up the program so that all the components work but lack the functionality of changing the class object because I can't access the objects.
*/  
   
/**
 service class for a bank account object
*/  
class BankAccount
{

private String name;    // name of the account holder
private String id;      // id number of the account
private double balance; // balance of the account
   
   /**
      default constructor for an BankAccount object. with all fields 
      initialized
   */
   public BankAccount()
   {
      name="";
      id="";
      balance=0;
   
   }
   
   /**
      paramterized constructor for an BankAccount object. with all fields 
      initialized
      @param accName, name of the account holder
      @param accId, id number of the account
      @param accBalance, balance of the account 
   */
   public BankAccount(String accName, String accId, double accBalance)throws NumberFormatException, InvalidDepositAmount, InvalidBalance
   {
      
      name=accName;
      id=accId;
      this.deposit(accBalance);
      if(balance<0.0)
      {
      InvalidBalance r = new InvalidBalance();
         throw r;
      }
      
   }
   
   /**
      setter method that sets the name of the account
      @param account name
   */
   public void setName(String accName)
   {
      name=accName;
   }
   
   /**
      setter method that sets the id number of the account
      @param id number
   */
   public void setId(String accId)
   {
      id=accId;
   }
   
   
  
   
   /**
      getter method that retrieves the name of the account
      @return account name
   */
   public String getName()
   {
   return name;
   }
   
   /**
      getter method that retrieves the id number of the account
      @return id number
   */
   public String getId()
   {
      return id;
   }
   
   /**
      getter method that retrieves the balance of the account
      @return balance
   */
   public double getBalance()
   {
      return balance;
   }
   
   /**
      the deposit deposits money into the account, and changes the balance
      @param the amount of deposit
   */
   public void deposit(double depositAmt)throws InvalidDepositAmount
   {
      if(depositAmt<=0.0 || depositAmt>=10000.0)
      {
         InvalidDepositAmount r = new InvalidDepositAmount();
         throw r;
      }
      else
         balance=balance+depositAmt;
   }
   
   /**
      the withdraw method withdraws money out of the account, and changes the balance
      @param the amount of withdraw
   */
   public void withdraw(double withdrawAmt)throws InvalidWithdrawAmount
   {
      
      if(withdrawAmt<=0.0 || withdrawAmt>=10000.0 || withdrawAmt>balance)
      {
         InvalidWithdrawAmount r = new InvalidWithdrawAmount();
         throw r;
      }
      else
         balance=balance-withdrawAmt;
   }
   
   /**
      the transfer method withdraws money out of one account and changes the balance. 
      Then deposits the money into another account
      @param The account to transfer to 
      @param the amount of transfer
   */
   public void transfer(BankAccount account,double transferAmt)throws InvalidWithdrawAmount, InvalidDepositAmount
   {
      this.withdraw(transferAmt);
      account.deposit(transferAmt);
   }
   
   /**
     toString method that creates a string Representation of the object
     @return the string representation
   */
   public String toString()
   {
      DecimalFormat accountBalance = new DecimalFormat("#,##0.00");
      
      String str = " Name: " + name +
                   "\n Account Id Number: " + id +
                   "\n Balance: " + accountBalance.format(balance);
     return str; 
   }


}

/**
 service class for a Savings Account object
*/
class SavingsAccount extends BankAccount
{
private double interestRate; //Interest Rate for account

   /**
      default constructor for an ProductionWorker object. with all fields 
      initialized
   */
   public SavingsAccount()
   {
      super();
      interestRate=0.0;
   }
   
   /**
      paramterized constructor for an ProductionWorker object. with all fields 
      initialized
      @param accName, name of the account holder
      @param accId, id number of the account
      @param accBalance, balance of the account
      @param Interest Rate of account 
   */
   
   public SavingsAccount(String accName, String accId, double accBalance,double interestAmt)throws NumberFormatException, InvalidDepositAmount, InvalidBalance, InvalidRate 
   {
      super(accName, accId, accBalance);
      interestRate=interestAmt;
      
      if(interestRate<0)
      {
         InvalidRate r = new InvalidRate();
         throw r;
      }
   }
   
   /**
      setter method that sets the interest rate of the account
      @param interest of account
   */
   public void setInterest(double interest)throws InvalidRate
   {
       if(interest<0)
      {
         InvalidRate r = new InvalidRate();
         throw r;
      }
      else
      interestRate=interest;
     
   }
   
   /**
      getter method that sets the interest rate of the account
      @return interest of account
   */
   public double getInterest()
   {
      return interestRate;
   }
   
   /**
      calculate Interest medthod calculates the earned interest of the account and adds it
      to the balance
   */
   public void calculateInterest()throws InvalidDepositAmount
   {
         double monthlyInterest=interestRate/12.0;
         double interestAdded=(monthlyInterest*this.getBalance());
         double newBalance=this.getBalance()+interestAdded;
         deposit(newBalance);
   }
   
   /**
     toString method that creates a string Representation of the object
     @return the string representation
   */
   public String toString()
   {
      String str= super.toString();
      str += "\nInterest Rate: " + interestRate;
      return str;
   }

}

/**
 service class for a Checking Account object
*/
class CheckingAccount extends BankAccount
{

public final double charge=2.00;    //The ammount charged after three free transactions 
private static int   numberOfTrans=0; //Number of transactions on account

   /**
      default constructor for an ProductionWorker object. with all fields 
      initialized
   */
   public CheckingAccount()
   {
      super();
      numberOfTrans=0;
   } 
   
   /**
      paramterized constructor for an ProductionWorker object. with all fields 
      initialized
      @param accName, name of the account holder
      @param accId, id number of the account
      @param accBalance, balance of the account
      
   */
   
   public CheckingAccount(String accName, String accId, double accBalance) throws NumberFormatException, InvalidDepositAmount, InvalidBalance
   {
      super(accName, accId, accBalance);
      numberOfTrans=0;
   }
   
   /**
      this overriden deposit method deposits money into the account, and changes the balance
      then adds one to the Transaction number tracker
      @param the amount of deposit
   */
   public void deposit(double depositAmt)throws InvalidDepositAmount
   {  
      if(depositAmt<=0.0 || depositAmt>=10000.0)
      {
         InvalidDepositAmount r = new InvalidDepositAmount();
         throw r;
      }
      else
      super.deposit(depositAmt);
      numberOfTrans=numberOfTrans+1;
   }
   
   /**
      this orriden withdraw method withdraws money out of the account, and changes the balance
      then adds one to the Transaction number tracker
      @param the amount of withdraw
   */
   public void withdraw(double withdrawAmt)throws InvalidWithdrawAmount
   {  
   
      if(withdrawAmt<=0.0 || withdrawAmt>=10000.0 || withdrawAmt>this.getBalance())
      {
        InvalidWithdrawAmount r = new InvalidWithdrawAmount();
        throw r;
      }
      else
      withdraw(this.getBalance()- withdrawAmt);
      numberOfTrans=numberOfTrans+1;
   }
   
   /**
      this charge account method deducts the ammount of accumlated fees from the account after the the first free 3 transactions have been made
   */
   public void chargeAccount()throws InvalidWithdrawAmount,InvalidDepositAmount
   {
      if(numberOfTrans>=3)
      {
         withdraw(this.getBalance() - charge*(numberOfTrans-2));
         numberOfTrans=0;
      }     
   }
   
   /**
     toString method that creates a string Representation of the object
     @return the string representation
   */
   public String toString()
   {
      String str= super.toString();
      str += "\nNumber of Transactions before charges: " + numberOfTrans;
      return str;
   }
   
   
}

   /**
      Exception class for an Invalid Deposit Amount
   */
class InvalidDepositAmount extends Exception
{

   public InvalidDepositAmount()
   {
      super("\n Error: Invalid deposit amount.");
   }
}

   /**
      Exception class for an Invalid Deposit Amount
   */
class InvalidWithdrawAmount extends Exception
{

   public InvalidWithdrawAmount()
   {
      super("\n Error: Invalid withdraw amount.");
   }
   
   
}

/**
      Exception class for an Invalid Rate
*/
class InvalidBalance extends Exception
{

   public InvalidBalance()
   {
      super("\n Error: Invalid balance.");
   }
   
   
}

/**
      Exception class for an Invalid Rate
*/
class InvalidRate extends Exception
{

   public InvalidRate()
   {
      super("\n Error: Invalid interest RAte.");
   }
   
   
}

class LoginWindow extends JFrame
{
   private JPanel loginPanel;                   // to reference a panel
   private JPanel buttonPanel;             // to reference a panel
   private HeaderPanel headerPanel;        // to reference a panel
   private JButton exitButton;             // to reference a button
   private JButton logButton;              // to reference a button
   private JButton newButton;              // to reference a button
   private JTextField nameTextField;       // to reference a text field
   private JTextField passwordTextField;   // to reference a text field
   private JLabel nameLabel;               // to reference a message label
   private JLabel passwordLabel;           // to reference a message label
   
   /**
      Login window constructor that creates window and components
   */
   public LoginWindow()
   {
      setTitle("Bank Account Login");
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      setLayout(new BorderLayout());
      
      headerPanel = new HeaderPanel();
      
      
      buildLoginPanel();
      buildButtonPanel();
      
      add(headerPanel, BorderLayout.NORTH);
      add(loginPanel, BorderLayout.CENTER);
      add(buttonPanel, BorderLayout.SOUTH);
      
      pack();
      setVisible(true);
      
      
   }
      /**
         Main method
      */
      public  static void main(String[] args)
      {
         new LoginWindow();
      }
   
   /**
      private method that creates login panel
   */
   private void buildLoginPanel()
   {
      nameLabel = new JLabel("Account Name");
      nameTextField = new JTextField(10);
      passwordLabel = new JLabel("Password");
      passwordTextField = new JTextField(10);
      loginPanel = new JPanel();
      loginPanel.add(nameLabel);
      loginPanel.add(nameTextField);
      loginPanel.add(passwordLabel);
      loginPanel.add(passwordTextField);
   }
   
   
   
   
   
   /**
      private method that creates button panel
   */
   private void buildButtonPanel()
   {
      buttonPanel = new JPanel();
      
      logButton = new JButton("Login");
      exitButton = new JButton("Exit");
      newButton =  new JButton("Create New Account");
      
      logButton.addActionListener(new LogButtonListener());
      exitButton.addActionListener(new ExitButtonListener());
      newButton.addActionListener(new NewButtonListener());
      
      buttonPanel.add(logButton);
      buttonPanel.add(exitButton);
      buttonPanel.add(newButton);
   }
   
   /**
   innter class containing LogButtonListener tools
   */
   private class LogButtonListener implements ActionListener
   {
      SavingsAccount userS;
      CheckingAccount userC;
      
      public void actionPerformed(ActionEvent e)
      {
         String name;
         String password;
         
         name=nameTextField.getText();
         password=passwordTextField.getText();
         if(name.isEmpty() || password.isEmpty())
            {
             JOptionPane.showMessageDialog(null, "That is not a valid account");
            }
         else
         {
         try
         {
            FileInputStream savingsInStream = new FileInputStream("savings.dat");
            ObjectInputStream savingsInputFile = new ObjectInputStream(savingsInStream);
            FileInputStream checkingInStream = new FileInputStream("checking.dat");
            ObjectInputStream checkingInputFile = new ObjectInputStream(checkingInStream); 
            
             
           userS = (SavingsAccount) savingsInputFile.readObject();
            //savingsInputFile.close();
            
           userC = (CheckingAccount)checkingInputFile.readObject();
            //checkingInputFile.close();
            
            if(name==userS.getName() && password==userS.getId())
            {
               new ManualWindow();
            }
            
         }
         catch(IOException r)
         {
          r.getMessage();
         }
         catch(ClassNotFoundException r)
         {
          r.getMessage();
         }
         }
         new ManualWindow();
      }
   }

   /**
   inner class containing ExitButtonListener tools
   */
  private class ExitButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         System.exit(0);
      }
   }
   
   private class NewButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         new NewAcc();
        
      }
   }


}

class HeaderPanel extends JPanel
{
   private JLabel header; // to display header
   
   /**
      Header panel constuctor
   */
   public HeaderPanel()
   {
      header = new JLabel("Bank Account Login"); //create header
      
      add(header); 
   }
}


/**
   class containing New Account window and tools
*/
class NewAcc extends JFrame 
{
   private JButton createButton;
   private JButton exitButton;
   private JPanel createPanel;
   private JPanel buttonPanel;
   private JTextField newNameTextField;
   private JLabel newNameLabel;
   private JTextField newPasswordTextField;
   private JLabel newPasswordLabel;
   
   public NewAcc()
   {
      setTitle("Bank Account creation");
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      setLayout(new BorderLayout());
  
      buildCreatePanel();
      buildButtonPanel();
      
      
      add(createPanel, BorderLayout.CENTER);
      add(buttonPanel, BorderLayout.SOUTH);
      
      pack();
      setVisible(true);
      
      
   }
   
   private void buildCreatePanel()
   {
      newNameLabel = new JLabel("Create Account Name");
      newNameTextField = new JTextField(10);
      newPasswordLabel = new JLabel("Create Password");
      newPasswordTextField = new JTextField(10);
      createPanel = new JPanel();
      createPanel.add(newNameLabel);
      createPanel.add(newNameTextField);
      createPanel.add(newPasswordLabel);
      createPanel.add(newPasswordTextField);
   }
   
   private void buildButtonPanel()
   {
      buttonPanel = new JPanel();
      
      exitButton = new JButton("Exit");
      createButton =  new JButton("Create Account");
      
  
      exitButton.addActionListener(new ExitButtonListener());
      createButton.addActionListener(new CreateButtonListener());
      
      buttonPanel.add(exitButton);
      buttonPanel.add(createButton);
   }
   
   /**
   inner class containing ExitButtonListener tools
   */
  private class ExitButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         System.exit(0);
      }
   }
   
   /**
   inner class containing CreateButtonListener tools
   */
   private class CreateButtonListener implements ActionListener
   {
    
      public void actionPerformed(ActionEvent e)
      {
         String newName;
         String newPassword;
         
         newName=newNameTextField.getText();
         newPassword=newPasswordTextField.getText();
         
         if(newName.isEmpty() || newPassword.isEmpty())
            {
             JOptionPane.showMessageDialog(null, "That is not a valid account");
            }
         else
         {
         try
         {
           SavingsAccount userSavings = new SavingsAccount(newName, newPassword, 1.00, 0.0);
           CheckingAccount userChecking = new CheckingAccount(newName, newPassword, 1.00);
           
            FileOutputStream savingsoutStream = new FileOutputStream("savings.dat");
            FileOutputStream checkingoutStream = new FileOutputStream("checking.dat");
            ObjectOutputStream savingsOutputFile = new ObjectOutputStream(savingsoutStream);
            ObjectOutputStream checkingOutputFile = new ObjectOutputStream(checkingoutStream);
            
            savingsOutputFile.writeObject(userSavings);
            savingsOutputFile.close();
            checkingOutputFile.writeObject(userChecking);
            checkingOutputFile.close();
        
         }
         catch(InvalidDepositAmount r)
         {
            System.err.println("InvalidDepositAmount: " + r.getMessage());
         }
         catch(InvalidBalance r)
         {
            System.err.println("InvalidBalance: " + r.getMessage());
         }
         catch(InvalidRate r)
         {
            System.err.println("InvalidRate: " + r.getMessage());
         }
         catch(IOException r)
            {
               r.getMessage();
            }
          JOptionPane.showMessageDialog(null, "You have created a Savings account and Checking account successfully!" );
          new ManualWindow();
          setVisible(false); 
       }  
         
      }
   }

   
}


/**
   class containing ManualWindow and tools
*/
class ManualWindow extends JFrame
{
   private JButton balanceButton;
   private JButton depositButton;
   private JButton withdrawButton;
   private JButton transferButton;
   private JButton estimateButton;
   private JButton exitButton;
   private JPanel buttonPanel;
   
   public ManualWindow()
   {
      setTitle("Bank account Actions");
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      setLayout(new BorderLayout());
       
      buildButtonPanel();
      
      add(buttonPanel, BorderLayout.CENTER);
      
      pack();
      setVisible(true);    
   }
   
   private void buildButtonPanel()
   {
      buttonPanel = new JPanel();
      
      balanceButton = new JButton("View Balance");
      depositButton = new JButton("Deposit");
      withdrawButton = new JButton("Withdraw");
      transferButton = new JButton("Transfer");
      estimateButton =  new JButton("Estimate Investment");
      exitButton = new JButton("Exit");
  
      
      balanceButton.addActionListener(new BalanceButtonListener());
      depositButton.addActionListener(new DepositButtonListener());
      withdrawButton.addActionListener(new WithdrawButtonListener());
      transferButton.addActionListener(new TransferButtonListener());
      estimateButton.addActionListener(new EstimateButtonListener());
      exitButton.addActionListener(new ExitButtonListener());
      
      buttonPanel.add(balanceButton);
      buttonPanel.add(depositButton);
      buttonPanel.add(withdrawButton);
      buttonPanel.add(transferButton);
      buttonPanel.add(estimateButton);
      buttonPanel.add(exitButton);
      
      
   }
/**
inner class containing ExitButtonListener tools
*/
  private class ExitButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         System.exit(0);
      }
   }
   
/**
   inner class containing BalanceButtonListener tools
*/
  private class BalanceButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         new ViewBalanceWindow();
      }
   }
   
   
/**
   inner class containing DepositButtonListener tools
*/
  private class DepositButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         new DepositWindow();
      }
   }
   
/**
   inner class containing WithdrawButtonListener tools
*/
  private class WithdrawButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         new WithdrawWindow();
      }
   }
   
/**
   inner class containing TransferButtonListener tools
*/
  private class TransferButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         new TransferWindow();
      }
   }
   
/**
   inner class containing EstimateButtonListener tools
*/
  private class EstimateButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         new EstimateWindow();
      }
   }
}


/**
   class containing ViewBalanceWindow and tools
*/
class ViewBalanceWindow extends JFrame
{
      SavingsAccount userSavings;
      CheckingAccount userChecking;
      private JButton mainButton;
      private JPanel buttonPanel;
      private JPanel panel;
      private JLabel checkingLabel;
      private JLabel savingsLabel;
      private JTextField checkingTextField;
      private JTextField savingsTextField;
      
      public ViewBalanceWindow()
   {
      setTitle("Account Balance");
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      setLayout(new BorderLayout());
      
      buildPanel(); 
      buildButtonPanel();
      
      add(panel, BorderLayout.CENTER);
      add(buttonPanel, BorderLayout.SOUTH);
      
      pack();
      setVisible(true);    
   }
   
   private void buildButtonPanel()
   {
      buttonPanel = new JPanel();
      
      mainButton = new JButton("Back to Main Manual");
      mainButton.addActionListener(new MainButtonListener());
      buttonPanel.add(mainButton);
   }
   
   private void buildPanel()
   {
      panel = new JPanel();
       
      checkingLabel = new JLabel("Checking:$");
      checkingTextField = new JTextField(10);
      savingsLabel = new JLabel("Savings:$");
      savingsTextField = new JTextField(10);
      
      panel.add(checkingLabel);
      panel.add(checkingTextField);
      panel.add(savingsLabel);
      panel.add(savingsTextField);
   }
   
/**
inner class containing EstimateButtonListener tools
*/
  private class MainButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         new ManualWindow();
         setVisible(false);  
      }
   }
}



/**
   class containing DepositWindow and tools
*/
class DepositWindow extends JFrame
{     
      
      private JButton mainButton;
      private JButton depositButton;
      private JRadioButton save; 
      private JRadioButton check; 
      private ButtonGroup bg; 
      private JPanel depositPanel;
      private JPanel buttonPanel;
      private JPanel accountPanel;
      private JTextField depositTextField;
      private JLabel depositLabel;
      
      public DepositWindow()
   {
      setTitle("Bank Account Deposit");
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      setLayout(new BorderLayout());
      
      buildAccountPanel();
      buildDepositPanel();
      buildButtonPanel();
      
      add(accountPanel, BorderLayout.NORTH);
      add(depositPanel, BorderLayout.CENTER);
      add(buttonPanel, BorderLayout.SOUTH);
      
      pack();
      setVisible(true);  
   }
   
   private void buildDepositPanel()
   {
      depositLabel = new JLabel("Deposit Amount:$");
      depositTextField = new JTextField(10);
      
      depositPanel = new JPanel();
      depositPanel.add(depositLabel);
      depositPanel.add(depositTextField);
     
   }
   
   
   private void buildAccountPanel()
   {
      accountPanel = new JPanel();
      
      save = new JRadioButton("Savings Account", true); // Radio button for savings
      check = new JRadioButton("Checking account");       // Radio button for checking
      
      bg=new ButtonGroup();      // radio button group
      bg.add(save);
      bg.add(check);
      
      accountPanel.add(save); //adds button to panel
      accountPanel.add(check); // adds button to panel
      
   }
   
   
   private void buildButtonPanel()
   {
      buttonPanel = new JPanel();
      depositButton = new JButton("Deposit");
      mainButton = new JButton("Back to Main Manual");
      mainButton.addActionListener(new MainButtonListener());
      depositButton.addActionListener(new DepositButtonListener());
      buttonPanel.add(depositButton);
      buttonPanel.add(mainButton);
   }
   
/**
inner class containing MainButtonListener tools
*/
  private class MainButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
        new ManualWindow();
        setVisible(false);
      }
   }   

/**
inner class containing DepositButtonListener tools
*/
  private class DepositButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
        new ManualWindow();
      }
   }    
   
   
   
}

/**
   class containing WithdrawWindow and tools
*/
class WithdrawWindow extends JFrame
{     
      
      private JButton mainButton;
      private JButton withdrawButton;
      private JRadioButton save; 
      private JRadioButton check; 
      private ButtonGroup bg; 
      private JPanel withdrawPanel;
      private JPanel buttonPanel;
      private JPanel accountPanel;
      private JTextField withdrawTextField;
      private JLabel withdrawLabel;
      
      public WithdrawWindow()
   {
      setTitle("Bank Account Withdraw");
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      setLayout(new BorderLayout());
      
      buildAccountPanel();
      buildWithdrawPanel();
      buildButtonPanel();
      
      add(accountPanel, BorderLayout.NORTH);
      add(withdrawPanel, BorderLayout.CENTER);
      add(buttonPanel, BorderLayout.SOUTH);
      
      pack();
      setVisible(true);  
   }
   
   private void buildWithdrawPanel()
   {
      withdrawLabel = new JLabel("Withdraw Amount:$");
       withdrawTextField = new JTextField(10);
      
       withdrawPanel = new JPanel();
       withdrawPanel.add( withdrawLabel);
       withdrawPanel.add( withdrawTextField);
     
   }
   
   
   private void buildAccountPanel()
   {
      accountPanel = new JPanel();
      
      save = new JRadioButton("Savings Account", true); // Radio button for savings
      check = new JRadioButton("Checking account");       // Radio button for checking
      
      bg=new ButtonGroup();      // radio button group
      bg.add(save);
      bg.add(check);
      
      accountPanel.add(save); //adds button to panel
      accountPanel.add(check); // adds button to panel
      
   }
   
   
   private void buildButtonPanel()
   {
      buttonPanel = new JPanel();
       withdrawButton = new JButton("Withdraw");
      mainButton = new JButton("Back to Main Manual");
      mainButton.addActionListener(new MainButtonListener());
      withdrawButton.addActionListener(new WithdrawButtonListener());
      buttonPanel.add(withdrawButton);
      buttonPanel.add(mainButton);
   }
   
/**
inner class containing MainButtonListener tools
*/
  private class MainButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
        new ManualWindow();
        setVisible(false);
      }
   }   

/**
inner class containing WithdrawButtonListener tools
*/
  private class WithdrawButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
        new ManualWindow();
      }
   }      
}

class TransferWindow extends JFrame
{
      private JButton mainButton;
      private JButton transferButton;      
      private ButtonGroup bg; 
      private JPanel amtPanel;
      private JPanel buttonPanel;
      private JPanel transferPanel;
      private JLabel amtLabel;
      private JLabel amtLabel2;
      private JComboBox combo;
      private JComboBox combo2;
      private JTextField amtTextField;
      
      private String[] accounts = {"Savings Account", "Checking Account"};
      
      public TransferWindow()
   {
      setTitle("Bank Account Transfer");
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      setLayout(new BorderLayout());
      
      buildTransferPanel();
      buildAmtPanel();
      buildButtonPanel();
      
      add(transferPanel, BorderLayout.NORTH);
      add(amtPanel, BorderLayout.CENTER);
      add(buttonPanel, BorderLayout.SOUTH);
      
      pack();
      setVisible(true);  
   }
   
       public void buildTransferPanel()
      {
       transferPanel = new JPanel();
      
         combo= new JComboBox(accounts);
         combo2= new JComboBox(accounts);
      transferPanel.add(combo);
      transferPanel.add(combo2);
      
      combo.addActionListener(new ComboBoxListener());
      combo2.addActionListener(new ComboBoxListener2());
         
      
      }
   
   public void buildAmtPanel()
   {
       amtPanel = new JPanel();
       amtLabel = new JLabel("Transfer Amount:$");
       amtTextField = new JTextField(10);
      
       amtPanel = new JPanel();
       amtPanel.add(amtLabel);
       amtPanel.add(amtTextField);
      
   }
   
   private void buildButtonPanel()
   {
      buttonPanel = new JPanel();
      transferButton = new JButton("Transfer");
      mainButton = new JButton("Back to Main Manual");
      mainButton.addActionListener(new MainButtonListener());
      transferButton.addActionListener(new TransferButtonListener());
      buttonPanel.add(transferButton);
      buttonPanel.add(mainButton);
   }
/**
inner class containing MainButtonListener tools
*/
  private class MainButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
        new ManualWindow();
        setVisible(false);
      }
   }    
/**
inner class containing TransferButtonListener tools
*/
  private class TransferButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
        new ManualWindow();
      }
   } 

/**
inner class containing ComboBoxListener tools
*/   
   private class ComboBoxListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
        String selection = (String) combo.getSelectedItem();
      }
   } 
   
/**
inner class containing ComboBoxListener tools
*/   
   private class ComboBoxListener2 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
        String selection2 = (String) combo2.getSelectedItem();
      }
   }    
   
   
}

/**
   class containing EstimateWindow and tools
*/
class EstimateWindow extends JFrame
{
      private JButton calcButton;
      private JButton mainButton;
      private JPanel buttonPanel;
      private JPanel panel;
      private JLabel yearLabel;
      private JTextField yearTextField;
     
      
      public EstimateWindow()
   {
      setTitle("Account Balance");
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      setLayout(new BorderLayout());
      
      buildPanel(); 
      buildButtonPanel();
      
      add(panel, BorderLayout.CENTER);
      add(buttonPanel, BorderLayout.SOUTH);
      
      pack();
      setVisible(true);    
   }
   
   private void buildButtonPanel()
   {
      buttonPanel = new JPanel();
      
      calcButton = new JButton("Calculate");
      mainButton = new JButton("Back to Main Manual");
      mainButton.addActionListener(new MainButtonListener());
      calcButton.addActionListener(new CalcButtonListener());
      buttonPanel.add(calcButton);
      buttonPanel.add(mainButton);
   }
   
   private void buildPanel()
   {
      panel = new JPanel();
       
      yearLabel = new JLabel("Number of Years to estimate");
      yearTextField = new JTextField(10);
      
      
      panel.add(yearLabel);
      panel.add(yearTextField);
    
   }
   
/**
inner class containing MainButtonListener tools
*/
  private class MainButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         new ManualWindow();
         setVisible(false);  
      }
   }
/**
inner class containing CalcButtonListener tools
*/   
   private class CalcButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         new ManualWindow();
         setVisible(false);  
      }
   }
}