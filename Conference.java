import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.text.DecimalFormat;

//*****************************************
//   Name: Troy Richardson
//   CSI 162-002
//   Lab 8
//*****************************************

public class Conference extends JFrame
{
   private Registration regPanel;
   private Keynote keyPanel;
   private Workshops workshopPanel;
   private JPanel buttonPanel;
   private HeaderPanel headerPanel;
   private JButton exitButton;
   private JButton calcButton;
   
   /**
      Conference constructor that creates window and components
   */
   public Conference()
   {
      setTitle("Conference Registration System");
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      setLayout(new BorderLayout());
      
      headerPanel = new HeaderPanel();
      regPanel = new Registration();
      keyPanel = new Keynote();
      workshopPanel = new Workshops();
      
      buildButtonPanel();
      
      add(headerPanel, BorderLayout.NORTH);
      add(regPanel, BorderLayout.CENTER);
      add(keyPanel, BorderLayout.WEST);
      add(workshopPanel, BorderLayout.EAST);
      add(buttonPanel, BorderLayout.SOUTH);
      
      pack();
      setVisible(true);
      
      
   }
      /**
         Main method
      */
      public  static void main(String[] args)
      {
         new Conference();
      }
   
   /**
      private method that creates button panel
   */
   private void buildButtonPanel()
   {
      buttonPanel = new JPanel();
      
      calcButton = new JButton("Calculate Charges");
      exitButton = new JButton("Exit");
      
      calcButton.addActionListener(new CalcButtonListener());
      exitButton.addActionListener(new ExitButtonListener());
      
      buttonPanel.add(calcButton);
      buttonPanel.add(exitButton);
      
   }
   
   /**
   innter class containing CalcButtonListener tools
   */
   private class CalcButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         double total = 0.0;
         
         total= regPanel.getRegistrationCost() + keyPanel.getKeynoteCost() + workshopPanel.getWorkshopCost();
         
         DecimalFormat dollar = new DecimalFormat("0.00");
         
         JOptionPane.showMessageDialog(null, "Total Charges: $" + dollar.format(total));
      
      
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


}



/**
 class containing Header panel and tools
*/
class HeaderPanel extends JPanel
{
   private JLabel header; // to display header
   
   /**
      Header panel constuctor
   */
   public HeaderPanel()
   {
      header = new JLabel("Select Registration Options"); //create header
      
      add(header); 
   }
}

/**
 class containing registration panel and tools
*/
class Registration extends JPanel
{
   public final double GENERAL_REGISTRATION = 895.00; // cost of general registration
   public final double STUDENT_REGISTRATION = 495.00; // cost of student registration
   
   private JRadioButton generalRegistration; // to select general registration
   private JRadioButton studentRegistration; // To select student registration
   private ButtonGroup bg;                   // Radio button group
  
   /**
      Registration panel constuctor
   */
   public Registration()
   {
      setLayout(new GridLayout(1, 2)); //grid layout mangaer with one row to columns
      
      generalRegistration = new JRadioButton("General Registration", true); // Radio button for general
      studentRegistration = new JRadioButton("Student Registration");       // Radio button for student
      
      bg=new ButtonGroup();      // radio button group
      bg.add(generalRegistration);
      bg.add(studentRegistration);
      
      setBorder(BorderFactory.createTitledBorder("Registration Type"));
      
      add(generalRegistration); //adds button to panel
      add(studentRegistration); // adds button to panel
   }
   
   /**
      gets the cost of the registration type
      @return cost of registration
   */
   public double getRegistrationCost()
   {
      double registrationCost = 0.0;
      
      if(generalRegistration.isSelected())
      {
         registrationCost = GENERAL_REGISTRATION;
      }
      else
      {
         registrationCost = STUDENT_REGISTRATION;
      }
      
      return registrationCost;
   }
}

/**
      Extra panel that contains dinner and keynote options
*/
class Keynote extends JPanel 
{
   public final double KEYNOTE_DINNER_SPEECH = 30.0; // cost of speech and dinner
   private JCheckBox keynote;  // To select keynote speech and dinner
   
   /**
      keynote panel constuctor
   */
   public Keynote()
   {
       setLayout(new GridLayout(1, 1)); //grid layout mangaer with one row to columns
       
       keynote = new JCheckBox("Dinner and Keynote Speech"); //creates the check box
       
       setBorder(BorderFactory.createTitledBorder("Keynote")); //creates border title
       
       add(keynote); // adds checkbox to the panel
   }
  
  /**
      gets the cost of the keynote speech and dinner
      @return keynoteCost
  */ 
  public double getKeynoteCost()
  {
      double keynoteCost = 0.0; 
      
      if(keynote.isSelected())
      {
         keynoteCost = KEYNOTE_DINNER_SPEECH;
      }
      
      return keynoteCost; 
  } 
}

/**
 class containing Workshops panel and tools
*/
class Workshops extends JPanel
{
   private final double INTRODUCTION = 295.00;
   private final double THE_FUTURE = 295.00;
   private final double ADVANCED_JAVA = 395.00;
   private final double NETWORK_SECURITY = 395.00;
   
   private JList list;
   
   private String[] workshops = {"Introduction to E-Commerce", "The Future of the Web", "Advanced Java Programming", "Network Security" };
   
   public Workshops()
   {
    setLayout(new GridLayout(1, 1)); //grid layout mangaer with one row to columns
      
    list = new JList(workshops);
       
    list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
       
    setBorder(BorderFactory.createTitledBorder("Workshops")); //creates border title
       
    add(list); // adds list to the panel
   }
   
   /**
      gets the cost of the Workshops
      @return WorkshopCost
  */ 
   public double getWorkshopCost()
  {
      double workshopCost = 0.0;
      
      if(list.isSelectedIndex(0))
      {
      workshopCost = workshopCost + INTRODUCTION;
      }
      if(list.isSelectedIndex(1))
      {
      workshopCost = workshopCost + THE_FUTURE;
      }
      if(list.isSelectedIndex(2))
      {
      workshopCost = workshopCost + ADVANCED_JAVA;
      }
      if(list.isSelectedIndex(3))
      {
      workshopCost = workshopCost + NETWORK_SECURITY;
      }
      
      return workshopCost; 
  }
   
   private class ListListener implements ListSelectionListener
   {
      public void valueChanged(ListSelectionEvent e)
      {
         String selection = (String) list.getSelectedValue();
         
      }
   }
}