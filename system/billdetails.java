
package electricity.billing.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class billdetails extends JFrame{
    billdetails(String meter){
       setSize(700,650);
       setLocation(400,150);
       
       getContentPane().setBackground(Color.WHITE);
       
       JTable table=new JTable();
       try{
           conn c=new conn();
           String query="select* from bill where meter_no='"+meter+"'";
           ResultSet rs=c.s.executeQuery(query);
           
           table.setModel(DbUtils.resultSetToTableModel(rs));
       }catch(Exception e){
           e.printStackTrace();
       }
       
       JScrollPane sp=new JScrollPane(table);
       sp.setBounds(0,0,700,650);
       add(sp);
       
       setVisible(true);
  }
    public static void main(String[]args){
        new billdetails("");
    }
    
}
