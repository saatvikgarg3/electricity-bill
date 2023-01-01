
package electricity.billing.system;
import java.awt.Choice;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class customerdeatils extends JFrame  implements ActionListener{
    Choice meternumber,cmonth;
    JButton print,search;
    JTable table;
    customerdeatils(){
        super("Deposit deatils");
        
        setSize(1200,650);
        setLocation(200,150);
        
        table=new JTable();
        
        try{
            conn c=new conn();
            ResultSet rs=c.s.executeQuery("select * from customer");
            
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        JScrollPane sp=new JScrollPane(table);
        add(sp);
        
        print=new JButton("Print");
        print.addActionListener(this);
        add(print,"South");
        
        setVisible(true);
    }
    public static void main(String[] args)
    {
        new customerdeatils();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
           try
           {
               //print krna ho agr 
               table.print();
           }
           catch(Exception ae)
           {
               ae.printStackTrace();
           }
    }
}

