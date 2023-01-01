
package electricity.billing.system;
import java.awt.Choice;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class depositdeatils extends JFrame  implements ActionListener{
    Choice meternumber,cmonth;
    JButton print,search;
    JTable table;
    depositdeatils(){
        super("Deposit deatils");
        
        setSize(700,700);
        setLocation(400,100);
        
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        
        JLabel lblmeternumber=new JLabel ("Search by meter number");
        lblmeternumber.setBounds(20,20,150,20);
        add(lblmeternumber);
        
        meternumber=new Choice();
        meternumber.setBounds(180,20,150,20);
        add(meternumber);
        
        try{
            conn c=new conn();
            ResultSet rs=c.s.executeQuery("Select * from Customer");
            while(rs.next()){
                meternumber.add(rs.getString("meter_no")); 
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        JLabel lblmonth=new JLabel ("Search by Month");
        lblmonth.setBounds(400,20,100,20);
        add(lblmonth);
        
        cmonth=new Choice();
        cmonth.setBounds(520,20,100,20);
        cmonth.add("January");
        cmonth.add("Feburary");
        cmonth.add("March");
        cmonth.add("April");
        cmonth.add("May");
        cmonth.add("June");
        cmonth.add("July");
        cmonth.add("August");
        cmonth.add("September");
        cmonth.add("October");
        cmonth.add("November");
        cmonth.add("December");
        add(cmonth);
        
        table=new JTable();
        
        try{
            conn c=new conn();
            ResultSet rs=c.s.executeQuery("select * from bill");
            
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        JScrollPane sp=new JScrollPane(table);
        sp.setBounds(0,100,700,600);
        add(sp);
        
        search=new JButton("Search");
        search.setBounds(20,70,80,20);
        search.addActionListener(this);
        add(search);
        
        print=new JButton("Print");
        print.setBounds(120,70,80,20);
        print.addActionListener(this);
        add(print);
        
        setVisible(true);
    }
    public static void main(String[] args){
        new depositdeatils();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==search){
           String query="select* from bill where meter_no='"+meternumber.getSelectedItem()+"' and month='"+cmonth.getSelectedItem()+"'";
           try{
               conn c=new conn();
               ResultSet rs=c.s.executeQuery(query);
               table.setModel(DbUtils.resultSetToTableModel(rs));
           }
           catch(Exception x){
               x.printStackTrace();
           }
       }
       else{
           try{
               //print krna ho agr 
               table.print();
           }catch(Exception ae){
               ae.printStackTrace();
           }
       }
    }
}
