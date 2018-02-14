import java.awt.*;
import java.awt.event.*;
import java.sql.*;
//*****************************************
//   Name: Troy Richardson
//   CSI 162-002
//   Lab 11
//***************************************** 

public class JDBC extends Frame implements ActionListener
{
   static JDBC td;
   static TextField tfs1,tfs2,tfs3,tfs4,tfs5;
   static Button b1,b2,b3,b4,b5,b6,b7;
   static Label l1,l2,l3;
   static Label it, pr;
   static Connection con;   
   static ResultSet rs,rs1;
   static Statement st1,st2;  

   public static void main (String args[])
   {
      td = new JDBC();
      td.setTitle("JDBC Demo");
      td.setSize (500,500);
      td.setVisible(true);
        td.setLayout(null);
        
      WindowClosing wc = new WindowClosing();
      td.addWindowListener(wc);
      td.addComp();
      
      try
      {  
      //Loading the JDBC_ODBC Driver: call the forName() method from the Class class,
      		//it returns a Class object that is associated with the interface or class.
      		//"sun.jdbc.JdbcOdbcDriver" is the class that contains the JDBC-ODBC driver.
         Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); 
      
      //Making a connection: call the getConnection(url) method of the DriverManager  
      		//class to establish a connect with the database.
      		//con is the object of the Connection class that represents a connect         		
      		//between the database and client. url represents the location of the 
      		//data source and method to identify the database.
         con = DriverManager.getConnection ("jdbc:odbc:OrData");
      
      //Querying a database: you need to ensure that the database is able to process
      		//the statements. 
      
         st1=con.createStatement(); 
      
      //The ResultSet object provides you with methods to access data from a table.
         rs1=st1.executeQuery("Select * from CustOrder");
      }
      catch (Exception e)
      {
         System.out.println("There was some error in establishing connection" + e);
      }
   }

   void addComp()
   {
      l1=new Label("Name");
      l1.setBounds(60,70,100,15);
      td.add(l1);
      tfs1=new TextField();
      tfs1.setBounds(180,60,200,30);
      td.add(tfs1);
      
      l2=new Label("Company");
      l2.setBounds(60,110,100,15);
      td.add(l2);
      tfs2=new TextField();
      tfs2.setBounds(180,100,200,30);
      td.add (tfs2);
   
      l3=new Label("Specialization");
      l3.setBounds(60,150,100,15);
      td.add(l3);
      tfs3=new TextField();
      tfs3.setBounds(180,140,200,30);
      td.add(tfs3);
      
      it=new Label("Items");
      it.setBounds(60,190,100,15);
      td.add(it);
      tfs4=new TextField();
      tfs4.setBounds(180,180,200,30);
      td.add(tfs4);
      
      pr=new Label("Price");
      pr.setBounds(60,230,100,15);
      td.add(pr);
      tfs5=new TextField();
      tfs5.setBounds(180,220,200,30);
      td.add(tfs5);
     
   
      b1= new Button("Add");
      b1.setBounds(60,400,60,20);
      td.add(b1);
      b2= new Button("Modify");
      b2.setBounds(130,400,60,20);
      td.add(b2);
      b3= new Button("Delete");
      b3.setBounds(200,400,60,20);
      td.add(b3);
      b4= new Button("Next");
      b4.setBounds(270,400,60,20);
      td.add(b4);
      b5= new Button("Clear");
      b5.setBounds(340,400,60,20);
      td.add(b5);
      b6 = new Button("Search");
      b6.setBounds(60,440,60,20);
      td.add(b6);
      b7 = new Button("Previous");
      b7.setBounds(270,440,60,20);
      td.add(b7);
      
      b1.addActionListener(td);
      b2.addActionListener(td);
      b3.addActionListener(td);
      b4.addActionListener(td);
      b5.addActionListener(td);
      b6.addActionListener(td);
      b7.addActionListener(td);
   }

   public void actionPerformed (ActionEvent ae) 
   {									      
      if (ae.getActionCommand() == "Add")          
      {
         String name = tfs1.getText(); //getText() method is used to extract the data from the text field
         String company = tfs2.getText();
         String specialization = tfs3.getText();
         							                              //Integer.parseInt() and Float.parseFloat()for converting data type                                           
         int items = Integer.parseInt(tfs4.getText());
         float price = Float.parseFloat(tfs5.getText());
         
         try
         {
            st2=con.createStatement(); //st2 is used to establish a connection with the database
         
            String query="insert into CustOrder values("+name+","+company+","+specialization+","+items+","+price+")";
            st2.executeUpdate(query); //execute the SQL statement
            st2.close();
         }
         catch (Exception e)
         {
            System.out.println("There has been some errors in addition " + e);
         }
      }
   
      if (ae.getActionCommand() == "Clear") 
      {
         tfs1.setText("");
         tfs2.setText("");
         tfs3.setText("");
         tfs4.setText("");
         tfs5.setText("");
      }
      if (ae.getActionCommand() == "Next")          
      {
         try
         {
            if(rs1.next()==true) //ResultSet method next() points the cursor on the first row
            {                       
            							//if the cursor has pointed to a row, display the result on the text fields

            								
               tfs1.setText(rs1.getString("Name"));
               tfs2.setText(rs1.getString("Company").toString());
               tfs3.setText(rs1.getString("Specialization").toString());
               tfs4.setText(rs1.getString("Items").toString());
               tfs5.setText(rs1.getString("Price").toString());
            }
            else             
            {                    //if the cursor hasn't pointed to the first row,
            								//make sure the current Statement and ResultSet 
            								//objects are closed,and re-create those objects. 
            								//then type the info for browsing
            
               st1.close();
               rs1.close();
               st1=con.createStatement();
               rs1=st1.executeQuery("Select * from CustOrder");
               rs1.next();
               tfs1.setText(rs1.getString("Name").toString());
               tfs2.setText(rs1.getString("Company").toString());
               tfs3.setText(rs1.getString("Specialization").toString());
               tfs4.setText(rs1.getString("Items").toString());
               tfs5.setText(rs1.getString("Price").toString());
            
            }
         }
         catch (Exception e)
         {
            System.out.println("There has been some errors in browsing " + e);
         }
      }
      
      if (ae.getActionCommand() == "Previous")          
      {
         try
         {
            if(rs1.previous()==true) //ResultSet method next() points the cursor on the first row if the cursor has pointed to a row, display the result on the text fields
            {                       
            							

            								
               tfs1.setText(rs1.getString("Name"));
               tfs2.setText(rs1.getString("Company").toString());
               tfs3.setText(rs1.getString("Specialization").toString());
               tfs4.setText(rs1.getString("Items").toString());
               tfs5.setText(rs1.getString("Price").toString());
            }
            else             
            {                    //if the cursor hasn't pointed to the first row,
            								//make sure the current Statement and ResultSet 
            								//objects are closed,and re-create those objects. 
            								//then type the info for browsing
            
               st1.close();
               rs1.close();
               st1=con.createStatement();
               rs1=st1.executeQuery("Select * from CustOrder");
               rs1.previous();
               tfs1.setText(rs1.getString("Name").toString());
               tfs2.setText(rs1.getString("Company").toString());
               tfs3.setText(rs1.getString("Specialization").toString());
               tfs4.setText(rs1.getString("Items").toString());
               tfs5.setText(rs1.getString("Price").toString());
            
            }
         }
         catch (Exception e)
         {
            System.out.println("There has been some errors in browsing " + e);
         }
      }
   
      if (ae.getActionCommand() == "Delete")    
      {
         try
         {
            st2 = con.createStatement();
            String ts = tfs1.getText(); //delete which one? 
            String ss = "delete from CustOrder where Name =" + ts; //search a record
            st2.executeUpdate(ss); //execute the query (delete the record)
            st2.close();
         }                  
         catch (Exception e)
         {
            System.out.println("There has been some errors in deleting " + e);
         }
      }
      if (ae.getActionCommand() == "Search")    
      {
       
         try
         {
            
            String ts = tfs1.getText();  
            String ss = "Select * from CustOrder where Name =" + ts; //search a record
            
               st1=con.createStatement();
               rs1=st1.executeQuery(ss);
            if (rs.next()) 
            {
                String s = rs.getString(1).toString();
                String s1 = rs.getString(2).toString();
                String s2 = rs.getString(3).toString();
                String s3 = rs.getString(4).toString();
                String s4 = rs.getString(5).toString();
                
                //Sets Records in TextFields.
                tfs1.setText(s);
                tfs2.setText(s1);
                tfs3.setText(s2);
                tfs4.setText(s3);
                tfs5.setText(s4);
            }
            st2.close();
         }                  
         catch (Exception e)
         {
            System.out.println("There has been some errors in searching " + e);
         }
      }

      if (ae.getActionCommand() == "Modify")    
      {
         try
         {
            st2=con.createStatement();
         								            //get info from the text fields for modifing
            String name=tfs1.getText();
            String company = tfs2.getText();
            String specialization = tfs3.getText();  
            int items = Integer.parseInt(tfs4.getText()); 
            float price = Float.parseFloat(tfs5.getText());
         
            String ss="update CustOrder set Items = " + items +" ,Price= " + price + " ,Specialization= " + specialization + " ,Company= " + company +
               " where Name =" + name; //set new value for the record
            System.out.println(ss);
            st2.executeUpdate(ss); //execute the query (change a record)
            st2.close();
         }                  
         catch (Exception e)
         {
            System.out.println("There has been some errors in modifying " + e);
         }
      }
   
   }
}

class WindowClosing extends WindowAdapter   
{ 
   
   JDBC td =new JDBC();  															
   public void windowClosing (WindowEvent we)  
   {
      System.exit(0);
        
   }
}